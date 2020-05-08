package org.linlinjava.litemall.service;

import org.linlinjava.litemall.entity.User;

/**
 * 处理用户相关功能的业务层接口
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param user 新用户数据
     */
    void reg(User user);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 成功登录的用户的信息
     */
    User login(String username, String password);

    /**
     * 修改用户密码
     *
     * @param uid         用户的id
     * @param username    用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,
                        String username, String oldPassword, String newPassword);

    /**
     * 修改用户头像
     *
     * @param uid      用户的id
     * @param username 用户名
     * @param avatar   新头像的路径
     */
    void changeAvatar(Integer uid, String username, String avatar);

    /**
     * 修改用户资料
     *
     * @param uid      被修改的用户的id
     * @param username 当前登录的用户名
     * @param user     封装了用户新资料的对象
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 根据用户id获取用户信息
     *
     * @param uid 用户id
     * @return 匹配的用户信息
     */
    User getByUid(Integer uid);

}






