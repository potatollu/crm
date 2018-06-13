package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.domain.MemberPayNumber;
import com.liqinxu.crm.qo.MenberPayNumberQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IMemberPayNumberService {
    int deleteByPrimaryKey(Long id);

    MemberPayNumber selectByPrimaryKey(Long id);

    List<MemberPayNumber> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(MemberPayNumber memberPayNumber);

    MemberPayNumber searchMember(MenberPayNumberQueryObject menberPayNumberQueryObject);

    List<MemberPayNumber> listClientItem(String clientNumber);

    List<MemberPayNumber> selectNumberBySn(String sn,String number);
}
