package org.linlinjava.litemall.service.impl;

import org.linlinjava.litemall.entity.District;
import org.linlinjava.litemall.mapper.DistrictMapper;
import org.linlinjava.litemall.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理省/市/区数据的业务层实现类
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public District getByCode(String code) {
        return districtMapper.findByCode(code);
    }

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }


}




