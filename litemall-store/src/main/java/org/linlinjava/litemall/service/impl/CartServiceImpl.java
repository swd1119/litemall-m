package org.linlinjava.litemall.service.impl;

import org.linlinjava.litemall.entity.Cart;
import org.linlinjava.litemall.entity.Product;
import org.linlinjava.litemall.mapper.CartMapper;
import org.linlinjava.litemall.service.ICartService;
import org.linlinjava.litemall.service.IProductService;
import org.linlinjava.litemall.service.ex.AccessDeniedException;
import org.linlinjava.litemall.service.ex.CartNotFoundException;
import org.linlinjava.litemall.service.ex.InsertException;
import org.linlinjava.litemall.service.ex.UpdateException;
import org.linlinjava.litemall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 处理购物车数据的业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    @Override
    public void addToCart(Integer uid, String username, Integer pid, Integer num) {
        // 创建时间对象
        Date now = new Date();

        // 根据参数uid和pid查询
        Cart result = findByUidAndPid(uid, pid);

        // 判断查询结果是否为null
        if (result == null) {
            // 是：需要插入数据
            // -- 创建Cart对象
            Cart cart = new Cart();
            // -- 封装数据：uid, pid, num,
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            // -- 封装数据：4项日志
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedTime(now);
            // -- 封装数据：基于参数pid通过productService的方法查询商品数量，得到商品价格，并封装
            Product product = productService.getById(pid);
            cart.setPrice(product.getPrice());
            // -- 执行插入数据
            addnew(cart);
        } else {
            // 否：需要修改商品数量
            // -- 从查询结果中取出cid, num
            // -- 基于参数num和以上取出的num，得到新的数量newNum
            Integer cid = result.getCid();
            Integer newNum = num + result.getNum();
            // -- 执行更新数量
            updateNumByCid(cid, newNum, username, now);
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 根据参数cid查询购物车数据
        Cart result = findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException(
                    "尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid不一致
        if (!result.getUid().equals(uid)) {
            // 是：AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 从查询结果中取出原数量，将该数量值加1
        Integer newNum = result.getNum() + 1;
        // 执行更新数量
        updateNumByCid(cid, newNum, username, new Date());
        // 返回新的数量值
        return newNum;
    }

    @Override
    public List<CartVO> getVOByCids(Integer[] cids, Integer uid) {
        // 基于参数cids查询数据
        List<CartVO> carts = findVOByCids(cids);
        // 通过Iterator遍历查询结果
        Iterator<CartVO> it = carts.iterator();
        while (it.hasNext()) {
            // 检查列表项中的uid与参数uid是否一致
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                // 不一致，则从查询结果中移除该数据
                it.remove();
            }
        }
        // 返回查询结果
        return carts;
    }

    /**
     * 插入购物车数据
     *
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    private void addnew(Cart cart) {
        Integer rows = cartMapper.addnew(cart);
        if (rows != 1) {
            throw new InsertException(
                    "插入购物车数据时出现未知错误，请联系系统管理员");
        }
    }

    /**
     * 更新某购物车数据中商品的数量
     *
     * @param cid          购物车数据的id
     * @param num          新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    private void updateNumByCid(Integer cid, Integer num,
                                String modifiedUser, Date modifiedTime) {
        Integer rows = cartMapper.updateNumByCid(cid, num, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException(
                    "更新购物车数据时出现未知错误，请联系系统管理员");
        }
    }

    /**
     * 根据购物车数据id查询购物车数据
     *
     * @param cid 购物车数据id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    private Cart findByCid(Integer cid) {
        return cartMapper.findByCid(cid);
    }

    /**
     * 根据用户id和商品id查询购物车数据
     *
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    private Cart findByUidAndPid(Integer uid, Integer pid) {
        return cartMapper.findByUidAndPid(uid, pid);
    }

    /**
     * 查询某用户的购物车数据
     *
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    private List<CartVO> findVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    /**
     * 查询多个购物车数据
     *
     * @param cids 多个购物车数据的id
     * @return 匹配的购物车数据列表
     */
    private List<CartVO> findVOByCids(Integer[] cids) {
        return cartMapper.findVOByCids(cids);
    }

}





