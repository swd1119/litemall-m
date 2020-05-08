package org.linlinjava.litemall.controller;

import org.linlinjava.litemall.entity.District;
import org.linlinjava.litemall.service.IDistrictService;
import org.linlinjava.litemall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

    @Autowired
    private IDistrictService districtService;

    @GetMapping({"/", ""})
    public JsonResult<List<District>> getByParent(String parent) {
        // 查询
        List<District> data = districtService.getByParent(parent);
        // 返回
        return new JsonResult<>(OK, data);
    }

}



