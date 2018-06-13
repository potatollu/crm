package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Gift;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface GiftMapper {

    /**
     * 删除礼品信息
     * @param id 需删除对象的id
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加礼品信息
     * @param record 礼品对象
     * @return 影响的行数
     */
    int insert(Gift record);

    /**
     * 查询单个礼品信息
     * @param id 查询对象的id
     * @return 影响的行数
     */
    Gift selectByPrimaryKey(Long id);

    /**
     * 查询所有礼品信息
     * @return 封装所有礼品数据的集合
     */
    List<Gift> selectAll();

    /**
     * 编辑礼品信息
     * @param record 要编辑的礼品对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(Gift record);

    /**
     * 查询礼品总数
     * @param query 高级查询GiftQuery类的对象
     * @return 数据总数
     */
    int queryCount(QueryObject query);

    /**
     * 每页要显示的礼品数据
     * @param query 高级查询GiftQuery类的对象
     * @return 每页显示数据的集合
     */
    List<?> queryList(QueryObject query);

    /**
     * 修改兑换数量
     * @param gift
     */
    void updateCurrentNum(Gift gift);

    List<Gift> queryAll();
}