package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.IntegralChange;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IIntegralChangeService {

    /**
     * 删除积分变动信息
     * @param id 需删除对象的id
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加积分变动信息
     * @param record 积分变动对象
     * @return 影响的行数
     */
    int insert(IntegralChange record);

    /**
     * 查询单个积分变动信息
     * @param id 查询对象的id
     * @return 影响的行数
     */
    IntegralChange selectByPrimaryKey(Long id);

    /**
     * 查询所有积分变动信息
     * @return 封装所有积分变动数据的集合
     */
    List<IntegralChange> selectAll();

    /**
     * 编辑积分变动信息
     * @param record 要编辑的积分变动对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(IntegralChange record);

    /**
     * 分页查询积分变动信息
     * @param query 高级查询IntegralChangeQuery类的对象
     * @return PageResult对象
     */
    PageResult query(QueryObject query);

}
