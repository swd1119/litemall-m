package org.linlinjava.litemall.controller;

import org.linlinjava.litemall.entity.Product;
import org.linlinjava.litemall.service.IProductService;
import org.linlinjava.litemall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理商品相关请求的控制器类
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @GetMapping("hot")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.getHostList();
        return new JsonResult<>(OK, data);
    }

    @GetMapping("new")
    public JsonResult<List<Product>> getNewList() {
        List<Product> data = productService.getNewList();
        return new JsonResult<>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.getById(id);
        return new JsonResult<>(OK, data);
    }

}







