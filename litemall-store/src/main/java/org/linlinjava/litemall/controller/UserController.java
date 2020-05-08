package org.linlinjava.litemall.controller;

import org.linlinjava.litemall.controller.ex.*;
import org.linlinjava.litemall.entity.User;
import org.linlinjava.litemall.service.IUserService;
import org.linlinjava.litemall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 处理用户数据相关请求的控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        System.out.println(1111);
        System.out.println(2222);
        System.out.println(3333);
        System.out.println(4444);
        System.out.println(1111);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        // 调用业务层方法执行登录
        User data = userService.login(username, password);
        // 将uid、username存入到session中
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        // 向客户端响应
        return new JsonResult<>(OK, data);
    }

    @PostMapping("change_password")
    public JsonResult<Void> changePassword(
            @RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword,
            HttpSession session) {
        // 从session中取出uid
        Integer uid = getUidFromSession(session);
        // 从session中取出username
        String username = getUsernameFromSession(session);
        // 执行修改密码
        userService.changePassword(uid, username, oldPassword, newPassword);
        // 响应
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        User data = userService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @GetMapping("logout")
    public JsonResult<Void> logout(HttpSession session) {
        session.invalidate();
        return new JsonResult<>(OK);
    }

    /**
     * 允许上传的头像的最大大小
     */
    public static final long AVATAR_MAX_SIZE = 1 * 1024 * 1024;
    /**
     * 允许上传的文件的类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        // AVATAR_TYPES.add("application/x-zip-compressed");
        // AVATAR_TYPES.add("image/gif");
        // AVATAR_TYPES.add("image/bmp");
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(
            @RequestParam("avatar") MultipartFile avatar,
            HttpSession session) {
        // 检查上传的文件是否为空
        if (avatar.isEmpty()) {
            throw new FileEmptyException("请选择有效的头像文件");
        }

        // 检查上传的文件的大小是否超出了限制
        if (avatar.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("不允许使用超过" + AVATAR_MAX_SIZE / 1024 + "KB的头像文件");
        }

        // 检查上传的文件的类型是否超出了限制
        if (!AVATAR_TYPES.contains(avatar.getContentType())) {
            throw new FileTypeException("上传的文件类型(" + avatar.getContentType() + ")有误，仅允许上传以下格式的头像文件：" + AVATAR_TYPES);
        }

        // 获取原始文件名
        String originalFilename = avatar.getOriginalFilename();

        // 文件夹
        String parentPath = session.getServletContext().getRealPath("upload");
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }

        // 文件名
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 用于保存上传的文件的对象
        File dest = new File(parent, filename);
        // 保存客户端上传的文件
        try {
            avatar.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件状态异常，请重新选择文件并再次上传");
        } catch (IOException e) {
            throw new FileIOException("上传时出现读写错误，请重新上传");
        }

        // 将文件的路径存储到数据表中
        String avatarPath = "/upload/" + filename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeAvatar(uid, username, avatarPath);

        // 响应：OK, 头像路径
        return new JsonResult<>(OK, avatarPath);
    }

}






