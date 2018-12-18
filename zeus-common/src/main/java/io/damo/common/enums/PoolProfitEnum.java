package io.damo.common.enums;

import java.util.Objects;

public enum PoolProfitEnum {

    EXCHANGE_KGC(1,"NP兑换"),
    EXCHANGE_CALC(2,"兑换算力"),
    CIRCULATION_CALC(3,"流通算力"),
    EXPLOITATION_CALC(4,"开采算力"),
    SHARE_CALC(5,"分享算力"),
    ORE_PROFIT(6,"矿石积分奖励"),
    SHOPING_PROFIT(7,"购物奖励"),
    TRANSFER_PROFIT(8,"转账"),
    SELLER_SHOP_PROFIT(10,"购物奖励"),//app端显示卖家购物奖励
    TRANSFER_IN_PROFIT(11,"转账");//转入NP

    private Integer type;

    private String value;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    PoolProfitEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static PoolProfitEnum getValue(int value) {
        for (PoolProfitEnum msg : values()) {
            if (Objects.equals(msg.getType(), value)) {
                return msg;
            }
        }
        return null;
    }
}
