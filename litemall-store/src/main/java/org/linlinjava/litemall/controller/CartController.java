package org.linlinjava.litemall.controller;

import org.linlinjava.litemall.service.ICartService;
import org.linlinjava.litemall.util.JsonResult;
import org.linlinjava.litemall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(
            Integer pid, Integer num,
            HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid, username, pid, num);
        return new JsonResult<>(OK);
    }

    @GetMapping({"/", ""})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVO> data = cartService.getVOByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(
            @PathVariable("cid") Integer cid,
            HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer data = cartService.addNum(cid, uid, username);
        return new JsonResult<>(OK, data);
    }

    @GetMapping("get_by_cids")
    public JsonResult<List<CartVO>> getVOByCids(
            Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVO> data = cartService.getVOByCids(cids, uid);
        return new JsonResult<>(OK, data);
    }

}





