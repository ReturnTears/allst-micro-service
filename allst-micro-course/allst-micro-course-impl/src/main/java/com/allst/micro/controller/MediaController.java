package com.allst.micro.controller;

import com.allst.micro.dto.MediaDTO;
import com.allst.micro.dto.VideoPlayDto;
import com.allst.micro.remote.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:09
 */
@RestController
@RequestMapping("/course/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @GetMapping("/getByLessonId")
    public MediaDTO getByLessonId(@RequestParam("lessonId") Integer lessonId) {
        return mediaService.getByLessonId(lessonId);
    }

    /**
     * 更新或者保存媒体
     */
    @PostMapping(value = "/updateOrSaveMedia")
    void updateOrSaveMedia(@RequestBody MediaDTO mediaDTO) {
        mediaService.updateOrSaveMedia(mediaDTO);
    }

    /**
     * 获取是媒体播放信息
     */
    @GetMapping("/getVideoPlayInfo")
    VideoPlayDto getVideoPlayInfo(Integer lessonId, Integer userId) {
        return mediaService.getVideoPlayInfo(lessonId, userId);
    }

    @GetMapping("/alikey")
    byte[] getCourseMediaDKByFileId(@RequestParam("fileId") String fileId, @RequestParam("edk") String edk, @RequestParam("userId") Integer userId) {
        return mediaService.getCourseMediaDKByFileId(fileId, edk, userId);
    }
}
