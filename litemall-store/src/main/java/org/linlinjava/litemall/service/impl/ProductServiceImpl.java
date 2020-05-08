package org.linlinjava.litemall.service.impl;

import org.linlinjava.litemall.entity.Product;
import org.linlinjava.litemall.mapper.ProductMapper;
import org.linlinjava.litemall.service.IProductService;
import org.linlinjava.litemall.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理商品数据的业务层实现类
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getById(Integer id) {
        Product product = findById(id);
        if (product == null) {
            throw new ProductNotFoundException(
                    "尝试访问的商品数据不存在");
        }

        product.setPriority(null);
        product.setCreatedTime(null);
        product.setCreatedUser(null);
        product.setModifiedTime(null);
        product.setModifiedUser(null);

        return product;
    }

    @Override
    public List<Product> getHostList() {
        List<Product> products = findHostList();
        for (Product product : products) {
            product.setStatus(null);
            product.setPriority(null);
            product.setCreatedTime(null);
            product.setCreatedUser(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
        }
        return products;
    }

    @Override
    public List<Product> getNewList() {
        List<Product> products = findNewList();
        for (Product product : products) {
            product.setStatus(null);
            product.setPriority(null);
            product.setCreatedTime(null);
            product.setCreatedUser(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
        }
        return products;
    }

    /**
     * 根据商品id查询商品详情
     *
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据，则返回null
     */
    private Product findById(Integer id) {
        return productMapper.findById(id);
    }

    /**
     * 获取热销排行的前4个商品
     *
     * @return 热销排行的前4个商品
     */
    private List<Product> findHostList() {
        return productMapper.findHostList();
    }

    /**
     * 获取新品排行的前4个商品
     *
     * @return 热销排行的前4个商品
     */
    private List<Product> findNewList() {
        return productMapper.findNewList();
    }


}





