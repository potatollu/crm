package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.IntegralChange;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface IntegralChangeMapper {

    /**
     * 删除积分数据
     * @param id 要删除积分对象数据的id
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加积分数据
     * @param record 积分数据对象
     * @return 影响的行数
     */
    int insert(IntegralChange record);

    /**
     * 查询一条积分数据
     * @param id 要查询积分对象的id
     * @return 影响的行数
     */
    IntegralChange selectByPrimaryKey(Long id);

    /**
     * 查询所有积分数据
     * @return 查询积分结果集合
     */
    List<IntegralChange> selectAll();

    /**
     * 编辑积分数据
     * @param record 要编辑的积分对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(IntegralChange record);
    /**
     * 查询积分变动总数
     * @param query 高级查询GiftQuery类的对象
     * @return 数据总数
     */
    int queryCount(QueryObject query);

    /**
     * 每页要显示的积分变动数据
     * @param query 高级查询GiftQuery类的对象
     * @return 每页显示数据的集合
     */
    List<?> queryList(QueryObject query);
}