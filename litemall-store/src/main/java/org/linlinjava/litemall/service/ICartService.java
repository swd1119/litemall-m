package org.linlinjava.litemall.service;

import org.linlinjava.litemall.vo.CartVO;

import java.util.List;

/**
 * 处理购物车数据的业务层接口
 */
public interface ICartService {

    /**
     * 将某商品添加到购物车
     *
     * @param uid      当前登录的用户id
     * @param username 当前登录的用户名
     * @param pid      需要添加到购物车中的商品的id
     * @param num      需要添加到购物车中的商品的数量
     */
    void addToCart(Integer uid, String username, Integer pid, Integer num);

    /**
     * 查询某用户的购物车数据
     *
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 将购物车中某数据的商品数量增加1
     *
     * @param cid      购物车数据id
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加后的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 查询某用户的多个购物车数据
     *
     * @param cids 多个购物车数据的id
     * @param uid  当前登录的用户的id
     * @return 匹配的购物车数据列表
     */
    List<CartVO> getVOByCids(Integer[] cids, Integer uid);

}





