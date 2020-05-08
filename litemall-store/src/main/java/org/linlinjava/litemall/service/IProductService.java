package org.linlinjava.litemall.service;

import org.linlinjava.litemall.entity.Product;

import java.util.List;

/**
 * 处理商品数据的业务层接口
 */
public interface IProductService {

    /**
     * 根据商品id查询商品详情
     *
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据，则返回null
     */
    Product getById(Integer id);

    /**
     * 获取热销排行的前4个商品
     *
     * @return 热销排行的前4个商品
     */
    List<Product> getHostList();

    /**
     * 获取新品排行的前4个商品
     *
     * @return 热销排行的前4个商品
     */
    List<Product> getNewList();
}






