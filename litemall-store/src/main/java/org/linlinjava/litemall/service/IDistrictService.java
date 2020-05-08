package org.linlinjava.litemall.service;

import org.linlinjava.litemall.entity.District;

import java.util.List;

/**
 * 处理省/市/区数据的业务层接口
 */
public interface IDistrictService {

    /**
     * 根据省/市/区的代号，获取省/市/区的详情
     *
     * @param code 省/市/区的代号
     * @return 匹配的省/市/区的详情，如果没有匹配的数据，则返回null
     */
    District getByCode(String code);

    /**
     * 获取全国所有省/某省所有市/某市所有区的列表
     *
     * @param parent 父级的行政代号，如果获取全国所有省的列表，则使用"86"
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<District> getByParent(String parent);

}
