package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Gift;
import com.liqinxu.crm.domain.TakeGiftItem;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IGiftService {

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
     * 分页查询礼品信息
     * @param query 高级查询GiftQuery类的对象
     * @return PageResult对象
     */
    PageResult query(QueryObject query);

    void makeExchage(TakeGiftItem takeGiftItems);

    Object queryAll();
}
