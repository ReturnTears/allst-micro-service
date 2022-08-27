package com.allst.micro.remote;

import cn.hutool.core.bean.BeanUtil;
import com.allst.micro.Util.ConverUtil;
import com.allst.micro.date.DateUtil;
import com.allst.micro.dto.UserDTO;
import com.allst.micro.entity.User;
import com.allst.micro.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户远程调用服务实现类
 *
 * @author Hutu
 * @since 2022-08-27 下午 05:58
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    IUserService userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/getUserById")
    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userService.getById(userId);
        if (user == null) {
            return new UserDTO();
        }
        return ConverUtil.convert(user, UserDTO.class);
    }

    @GetMapping("/getUserByPhone")
    @Override
    public UserDTO getUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User user = this.userService.getOne(queryWrapper);
        if (user == null) {
            return new UserDTO();
        }
        return ConverUtil.convert(user, UserDTO.class);
    }

    @GetMapping("/isRegister")
    @Override
    public boolean isRegister(String phone) {
        UserDTO userDTO = getUserByPhone(phone);
        return userDTO != null && !Boolean.TRUE.equals(userDTO.getDel()); // 返回对象不为空且未被删除， 即表示已注册
    }

    @PostMapping("/saveUser")
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        if (StringUtils.isNotBlank(user.getPhone())) {
            String phone = userDTO.getPhone();
            user.setName("用户" + phone.substring(phone.length() - 4));
        }
        this.userService.save(user);
        BeanUtil.copyProperties(user, userDTO);
        log.info("用户[{}]保存成功", user.toString());
        return userDTO;
    }

    @PostMapping("/updateUser")
    @Override
    public boolean updateUser(UserDTO userDTO) {
        if (null == userDTO.getId() || userDTO.getId() <= 0) {
            log.info("用户id为空，无法更新");
            return false;
        }
        User user = new User();
        BeanUtil.copyProperties(userDTO, user, "create_time");
        if (StringUtils.isNotBlank(userDTO.getPassword())) {
            user.setPassword(encoder.encode(userDTO.getPassword()));
        }
        this.userService.updateById(user);
        log.info("用户[{}]更新成功", user.toString());
        return true;
    }

    @GetMapping("/isUpdatedPassword")
    @Override
    public boolean isUpdatedPassword(Integer userId) {
        User user = this.userService.getById(userId);
        if (null == user) {
            return false;
        }
        boolean matches = encoder.matches(user.getPhone(), user.getPassword());
        log.info("用户[{}]是否有修改过初始密码[{}]", userId, matches);
        return true;
    }

    @PostMapping("/setPassword")
    @Override
    public boolean setPassword(Integer userId, String password, String configPassword) {
        User user = this.userService.getById(userId);
        if (null == user) {
            return false;
        }
        if (!StringUtils.equals(password, configPassword)) {
            return false;
        }
        user.setPassword(encoder.encode(password));
        this.userService.updateById(user);
        log.info("用户[{}]设置密码成功", userId);
        return true;
    }

    @PostMapping("/updatePassword")
    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword, String configPassword) {
        User user = this.userService.getById(userId);
        if (null == user) {
            return false;
        }
        if (!StringUtils.equals(newPassword, configPassword)) {
            return false;
        }
        if (!encoder.matches(oldPassword, user.getPassword())) {
            log.info("用户[{}]旧密码错误", userId);
            return false;
        }
        user.setPassword(encoder.encode(newPassword));
        this.userService.updateById(user);
        log.info("用户[{}]更新密码成功", userId);
        return true;
    }

    @PostMapping("/forbidUser")
    @Override
    public boolean forbidUser(Integer userId) {
        User user = this.userService.getById(userId);
        if (null == user) {
            return false;
        }
        user.setUpdateTime(DateUtil.getNowDate());
        user.setIsDel(true);
        user.setStatus("DISABLE");
        boolean result = this.userService.updateById(user);
        if (result) {
            // TODO 发送mq消息，让用户登录失效
            log.info("---forbidUser---");
        }
        return result;
    }
}
