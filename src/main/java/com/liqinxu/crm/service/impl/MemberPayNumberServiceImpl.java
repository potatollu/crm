package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.domain.MemberPayNumber;
import com.liqinxu.crm.mapper.MemberPayNumberMapper;
import com.liqinxu.crm.qo.MenberPayNumberQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IMemberPayNumberService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MemberPayNumberServiceImpl implements IMemberPayNumberService {

    @Autowired
    private MemberPayNumberMapper memberPayNumberMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return memberPayNumberMapper.deleteByPrimaryKey(id);
    }


    @Override
    public MemberPayNumber selectByPrimaryKey(Long id) {
        return memberPayNumberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberPayNumber> selectAll() {
        return memberPayNumberMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(MemberPayNumber memberPayNumber) {
        if (memberPayNumber.getId() != null) {
            memberPayNumberMapper.updateByPrimaryKey(memberPayNumber);
        } else {
            memberPayNumberMapper.insert(memberPayNumber);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = memberPayNumberMapper.queryForCount(qo);
        List<?> list = memberPayNumberMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    /**
     * 会员充次根据用户信息查询对应的会员
     *
     * @param menberPayNumberQueryObject 用户信息
     * @return 用户
     */
    @Override
    public MemberPayNumber searchMember(MenberPayNumberQueryObject menberPayNumberQueryObject) {
        return memberPayNumberMapper.searchMember(menberPayNumberQueryObject);
    }

    @Override
    //根据会员卡号查询页面所需数据
    public List<MemberPayNumber> listClientItem(String clientNumber) {
        return memberPayNumberMapper.listClientItem(clientNumber);
    }

    public List<MemberPayNumber> selectNumberBySn(String sn,String number) {
        return memberPayNumberMapper.selectNumberBySn(sn,number);
    }
}
