package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.domain.MemberPayNumber;
import com.liqinxu.crm.qo.MenberPayNumberQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberPayNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberPayNumber entity);

    MemberPayNumber selectByPrimaryKey(Long id);

    List<MemberPayNumber> selectAll();

    int updateByPrimaryKey(MemberPayNumber entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    MemberPayNumber searchMember(MenberPayNumberQueryObject keyword);

    List<MemberPayNumber> listClientItem(String clientNumber);

    List<MemberPayNumber> selectNumberBySn(@Param("sn") String sn, @Param("number") String number);
}