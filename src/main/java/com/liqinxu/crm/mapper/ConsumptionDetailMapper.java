package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.ConsumptionDetail;
import java.util.List;

public interface ConsumptionDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumptionDetail entity);

    ConsumptionDetail selectByPrimaryKey(Long id);

    List<ConsumptionDetail> selectAll();

    int updateByPrimaryKey(ConsumptionDetail entity);

    int selectBytotalCount();

    int selectByMemberCount();

    /**
     * 查询累计消费
     * @return 累计消费
     */
    int selectBytotalExpense();

    List<ConsumptionDetail> selectByTOP3();
}