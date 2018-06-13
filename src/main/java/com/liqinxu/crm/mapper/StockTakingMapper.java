package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.StockTaking;
import com.liqinxu.crm.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockTakingMapper {
    /**
     * 删除库存
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(StockTaking record);

    StockTaking selectByPrimaryKey(Long id);

    List<StockTaking> selectAll();

    int updateByPrimaryKey(StockTaking record);
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


    StockTaking changStockTaking(@Param("productName") String productName,
                                 @Param("depotName") String depotName);
}