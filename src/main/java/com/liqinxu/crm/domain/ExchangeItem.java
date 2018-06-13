package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jinyanfeng on 2018/3/28.
 */
@Getter
@Setter
public class ExchangeItem {
    private Long id;
    private String memberCard; //会员卡号
    private Long giftId; //礼品id
    private Integer exchangeNum; //兑换数量
}
