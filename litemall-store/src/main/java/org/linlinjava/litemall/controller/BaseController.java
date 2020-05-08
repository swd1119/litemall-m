package org.linlinjava.litemall.controller;

import org.linlinjava.litemall.controller.ex.*;
import org.linlinjava.litemall.service.ex.*;
import org.linlinjava.litemall.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 */
public abstract class BaseController {

    /**
     * 响应状态：成功
     */
    protected static final int OK = 2000;

    /**
     * 从Session中获取uid
     *
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从Session中获取username
     *
     * @param session HttpSession对象
     * @return 当前登录的用户的用户名
     */
    protected String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> jr = new JsonResult<>(e);

        if (e instanceof UsernameDuplicateException) {
            jr.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            jr.setState(4001);
        } else if (e instanceof PasswordNotMatchException) {
            jr.setState(4002);
        } else if (e instanceof AddressCountException) {
            jr.setState(4003);
        } else if (e instanceof AddressNotFoundException) {
            jr.setState(4004);
        } else if (e instanceof AccessDeniedException) {
            jr.setState(4005);
        } else if (e instanceof ProductNotFoundException) {
            jr.setState(4006);
        } else if (e instanceof CartNotFoundException) {
            jr.setState(4007);
        } else if (e instanceof InsertException) {
            jr.setState(5000);
        } else if (e instanceof UpdateException) {
            jr.setState(5001);
        } else if (e instanceof DeleteException) {
            jr.setState(5002);
        } else if (e instanceof FileEmptyException) {
            jr.setState(6000);
        } else if (e instanceof FileSizeException) {
            jr.setState(6001);
        } else if (e instanceof FileTypeException) {
            jr.setState(6002);
        } else if (e instanceof FileStateException) {
            jr.setState(6003);
        } else if (e instanceof FileIOException) {
            jr.setState(6004);
        }

        // switch(e.getClass().getSimpleName()) {
        // case "UsernameDuplicateException":
        // 	jr.setState(4000);
        // 	break;
        // case "UserNotFoundException":
        // 	jr.setState(4001);
        // 	break;
        // }

        return jr;
    }

}
