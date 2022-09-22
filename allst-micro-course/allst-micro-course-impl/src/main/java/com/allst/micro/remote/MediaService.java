package com.allst.micro.remote;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.allst.micro.config.AliyunConfig;
import com.allst.micro.dto.MediaDTO;
import com.allst.micro.dto.VideoPlayDto;
import com.allst.micro.entity.po.Lesson;
import com.allst.micro.entity.po.Media;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.ILessonService;
import com.allst.micro.service.IMediaService;
import com.allst.micro.service.UserCourseOrderRemoteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:45
 */
@Slf4j
@Service
public class MediaService {

    @Autowired
    IMediaService mediaService;

    @Autowired
    ILessonService lessonService;

    @Autowired
    UserCourseOrderRemoteService userCourseOrderRemoteService;

    @Autowired
    AliyunConfig aliyunConfig;

    DefaultAcsClient aliClient = null;


    public MediaDTO getByLessonId(Integer lessonId) {
        QueryWrapper<Media> mediaQueryWrapper = new QueryWrapper<>();
        mediaQueryWrapper.eq("lesson_id", lessonId);
        mediaQueryWrapper.eq("is_del", Boolean.FALSE);
        List<Media> mediaList = this.mediaService.list(mediaQueryWrapper);
        if (CollectionUtils.isEmpty(mediaList)) {
            return null;
        }
        MediaDTO mediaDTO = new MediaDTO();
        BeanUtil.copyProperties(mediaList.get(0), mediaDTO);
        return mediaDTO;
    }


    public byte[] getCourseMediaDKByFileId(String fileId, String edk, Integer userId) {
        QueryWrapper<Media> query = new QueryWrapper<Media>().eq("file_id", fileId).eq("is_del", Boolean.FALSE);
        List<Media> courseMediaList = mediaService.list(query);
        if (CollectionUtils.isEmpty(courseMediaList)) {
            log.info("fileId:{}对应的媒体资源不存在", fileId);
            return null;
        }

        List<Integer> courseIdList = new ArrayList<>(courseMediaList.size());
        List<Integer> lessonIdList = new ArrayList<>(courseMediaList.size());
        for (Media courseMedia : courseMediaList) {
            courseIdList.add(courseMedia.getCourseId());
            lessonIdList.add(courseMedia.getLessonId());
        }


        List<Lesson> courseLessonList = lessonService.listByIds(lessonIdList);
        if (CollectionUtils.isEmpty(courseLessonList)) {
            log.info("lessonIdList:{}所对应的的课时信息不存在", lessonIdList);
            return null;
        }

        Media courseMedia = courseMediaList.get(0);
        String mediaEdk = courseMedia.getFileEdk();
        if (!Objects.equals(mediaEdk, edk)) {
            log.info("媒体资源的EDK不匹配，fileId:{},fromEDK:{}", fileId, edk);
            return null;
        }

        String mediaDk = courseMedia.getFileDk();
        if (StringUtils.isBlank(mediaDk)) {
            log.info("fileId:{}对应的媒体资源DK不存在", fileId);
            return null;
        }

        byte[] dkBytes = Base64.getDecoder().decode(mediaDk);

        //遍历课时列表，检查是否存在免费课时
        for (Lesson lesson : courseLessonList) {
            //存在一个免费课时，直接返回
            if (lesson.getIsFree() != null && lesson.getIsFree()) {
                log.info("lessonId:{}是免费课时,fileId:{},mediaDk:{},dkBytes:{} ", lesson.getId(), fileId, mediaDk, dkBytes);
                return dkBytes;
            }
        }

        //当用户ID为null，又不是免费课时，直接返回null
        if (userId == null) {
            log.info("用户为空 并且课程不为 免费课程");
            return null;
        }
        ResponseDTO<Integer> responseDTO = userCourseOrderRemoteService.countUserCourseOrderByCoursIds(userId, courseIdList);
        if (!responseDTO.isSuccess()) {
            log.info("调用用户订单失败 responseDTO：{}", JSON.toJSONString(responseDTO));
            return null;
        }
        Integer orderCount = responseDTO.getContent();
        if (orderCount <= 0) {
            log.info("用户未购买该课程,courseIdList:{}, fileId:{}，userId:{}", courseIdList, fileId, userId);
            return null;
        }

        return dkBytes;
    }

    public void updateOrSaveMedia(MediaDTO mediaDTO) {
        Media media = new Media();
        BeanUtils.copyProperties(mediaDTO, media);
        Integer lessonId = mediaDTO.getLessonId();
        Lesson lesson = lessonService.getById(lessonId);
        if (lesson == null) {
            log.error("没有对应的lesson信息 mediaDTO:{}", JSON.toJSONString(mediaDTO));
            return;
        }
        lesson.setDuration(media.getDurationNum() / 60);
        lessonService.saveOrUpdate(lesson);
        Integer courseId = lesson.getCourseId();
        Integer sectionId = lesson.getSectionId();
        media.setCourseId(courseId);
        media.setSectionId(sectionId);
        QueryWrapper<Media> query = new QueryWrapper<Media>();
        query.eq("lesson_id", lessonId);
        query.eq("course_id", courseId);
        query.eq("section_id", sectionId);
        query.eq("is_del", Boolean.FALSE);
        List<Media> medias = mediaService.list(query);
        if (!CollectionUtils.isEmpty(medias)) {
            Integer id = medias.get(0).getId();
            media.setId(id);
        } else {
            media.setCreateTime(LocalDateTime.now());
        }
        media.setUpdateTime(LocalDateTime.now());
        mediaService.saveOrUpdate(media);
    }

    public VideoPlayDto getVideoPlayInfo(Integer lessonId, Integer userId) {
        MediaDTO mediaDTO = getByLessonId(lessonId);
        if (mediaDTO == null) {
            log.info("对应的课时视频不存在,lessonId={},userId={}", lessonId, userId);
            return null;
        }
        //获取阿里云视频播放的必要信息
        String fileId = mediaDTO.getFileId();

        VideoPlayDto aliYunVideoPlayInfo = new VideoPlayDto();
        try {
            String playAuth = getAliVideoPlayAuth(fileId);
            aliYunVideoPlayInfo.setPlayAuth(playAuth);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            log.info("获取阿里云视频播放PlayAuth出错！", e);
            return null;
        }

        aliYunVideoPlayInfo.setFileId(fileId);
        Lesson lesson = lessonService.getById(lessonId);


        if (lesson.getIsFree()) {
            log.info("对应的课时视频免费,lessonId={},userId={}", lessonId, userId);
            return aliYunVideoPlayInfo;
        }

        if (userId == null) {
            log.info("用户ID为空,lessonId={},userId={}", lessonId, userId);
            return null;
        }

        Integer courseId = lesson.getCourseId();
        if (courseId == null) {
            log.info("课时媒体资源对应的课程ID为空, fileId:{}，userId:{}", fileId, userId);
            return null;
        }
        ResponseDTO<Integer> responseDTO = userCourseOrderRemoteService.countUserCourseOrderByCoursIds(userId, Arrays.asList(courseId));
        if (responseDTO.isSuccess() && responseDTO.getContent().compareTo(0) > 0) {
            return aliYunVideoPlayInfo;
        }

        return null;
    }

    /**
     * 查询阿里auth
     */
    public String getAliVideoPlayAuth(String vid) throws com.aliyuncs.exceptions.ClientException {
        GetVideoPlayAuthRequest playAuthRequest = new GetVideoPlayAuthRequest();
        playAuthRequest.setVideoId(vid);
        GetVideoPlayAuthResponse acsResponse = getAliVodClient().getAcsResponse(playAuthRequest);
        return acsResponse.getPlayAuth();
    }

    protected DefaultAcsClient getAliVodClient() {
        if (aliClient != null) {
            return aliClient;
        }
        // 点播服务接入区域
        String regionId = "cn-beijing";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, aliyunConfig.getAccessKeyId(), aliyunConfig.getAccessKeySecret());
        aliClient = new DefaultAcsClient(profile);
        return aliClient;
    }
}
