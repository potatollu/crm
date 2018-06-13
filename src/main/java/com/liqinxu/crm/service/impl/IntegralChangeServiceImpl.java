package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.IntegralChange;
import com.liqinxu.crm.mapper.IntegralChangeMapper;
import com.liqinxu.crm.mapper.MemberMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IIntegralChangeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IntegralChangeServiceImpl implements IIntegralChangeService {

    @Autowired
    private IntegralChangeMapper integralChangeMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 删除礼品信息
     * @ param id 需删除对象的id
     * @return 影响的行数
     */
    public int deleteByPrimaryKey(Long id) {
        return integralChangeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加礼品信息
     * @param record 礼品对象
     * @return 影响的行数
     */
    public int insert(IntegralChange record) {
        //改变会员表的积分数据
        //查询会员当前积分
        int Integral = memberMapper.selectIntegralNumber(record.getMemberNumber());
        //把积分转换为int类型
        int changeIntegral = Integer.parseInt(record.getAmount());
        //判断是改变类型增加还是扣除
        int newIntegral = 0;
        if (record.getChangeType().equals("1")){
            record.setAmount("+" + record.getAmount());
            //积分增加
            newIntegral = Integral + changeIntegral;
        }else{
            record.setAmount("-" + record.getAmount());
            //积分扣除
            newIntegral = Integral - changeIntegral;
        }
        //修改会员积分
        memberMapper.updateIntegralByNumber(record.getMemberNumber(),newIntegral);
        //积分变动时间
        record.setChaneTime(new Date());
        //记录积分变动
        return integralChangeMapper.insert(record);
    }

    /**
     * 查询单个礼品信息
     * @param id 查询对象的id
     * @return 影响的行数
     */
    public IntegralChange selectByPrimaryKey(Long id) {
        return integralChangeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有礼品信息
     * @return 封装所有礼品数据的集合
     */
    public List<IntegralChange> selectAll() {
        return integralChangeMapper.selectAll();
    }

    /**
     * 编辑礼品信息
     * @param record 要编辑的礼品对象
     * @return 影响的行数
     */
    public int updateByPrimaryKey(IntegralChange record) {
        return integralChangeMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询礼品信息
     * @param query 高级查询IntegralChangeQuery类的对象
     * @return PageResult对象
     */
    public PageResult query(QueryObject query) {
        int count = integralChangeMapper.queryCount(query);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count,integralChangeMapper.queryList(query));
    }
}
