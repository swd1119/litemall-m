package org.linlinjava.litemall.mapper;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * 处理收货地址数据的持久层接口
 */
public interface AddressMapper {

    /**
     * 插入收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据收货地址数据的id删除数据
     *
     * @param aid 收货地址数据的id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 将指定的收货地址数据设置为默认
     *
     * @param aid          收货地址数据的id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 将某用户的所有收货地址全部设置为非默认
     *
     * @param uid 用户的id
     * @return 受影响的行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 统计某用户的收货地址数据的数量
     *
     * @param uid 用户的id
     * @return 该用户的收货地址数据的数量
     */
    Integer countByUid(Integer uid);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     *
     * @param aid 收货地址数据的id
     * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
     */
    Address findByAid(Integer aid);

    /**
     * 查询某用户最后修改的收货地址详情
     *
     * @param uid 用户的id
     * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
     */
    Address findLastModifiedByUid(Integer uid);

    /**
     * 获取某用户的收货地址列表
     *
     * @param uid 用户的id
     * @return 该用户的收货地址列表
     */
    List<Address> findByUid(Integer uid);

}




