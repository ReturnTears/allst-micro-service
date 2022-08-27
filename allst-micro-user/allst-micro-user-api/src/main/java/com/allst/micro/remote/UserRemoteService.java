package com.allst.micro.remote;

import com.allst.micro.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户远程调用服务类
 *
 * @author Hutu
 * @since 2022-08-27 下午 05:37
 */
@FeignClient(name = "allst-micro-user", path = "/user")
public interface UserRemoteService {
    /**
     * 通过id获取用户
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    @GetMapping("/getUserById")
    UserDTO getUserById(@RequestParam("userId") Integer userId);

    /**
     * 通过手机号获取用户
     *
     * @param phone 手机号
     *
     * @return 结果
     */
    @GetMapping("/getUserByPhone")
    UserDTO getUserByPhone(@RequestParam("phone") String phone);

    /**
     * 判断手机号是否注册
     *
     * @param phone 手机号
     *
     * @return 结果
     */
    @GetMapping("/isRegister")
    boolean isRegister(@RequestParam("phone") String phone);

    /**
     * 保存用户
     *
     * @param userDTO 参数
     *
     * @return 结果
     */
    @PostMapping("/saveUser")
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    /**
     * 修改用户
     *
     * @param userDTO 参数
     *
     * @return 结果
     */
    @PostMapping("/updateUser")
    boolean updateUser(@RequestBody UserDTO userDTO);

    /**
     * 是否更新用户密码
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    @GetMapping("/isUpdatedPassword")
    boolean isUpdatedPassword(@RequestParam("userId") Integer userId);

    /**
     * 设置密码
     *
     * @param userId         用户id
     * @param password       密码
     * @param configPassword 确认密码
     *
     * @return 结果
     */
    @PostMapping("/setPassword")
    boolean setPassword(@RequestParam("userId") Integer userId, @RequestParam("password") String password, @RequestParam("configPassword") String configPassword);

    /**
     * 修改密码
     *
     * @param userId         用户id
     * @param oldPassword    旧密码
     * @param newPassword    新密码
     * @param configPassword 确认命名
     *
     * @return 结果
     */
    @PostMapping("/updatePassword")
    boolean updatePassword(@RequestParam("userId") Integer userId, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("configPassword") String configPassword);

    /**
     * 禁用用户
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    @PostMapping("/forbidUser")
    boolean forbidUser(@RequestParam("userId") Integer userId);
}
