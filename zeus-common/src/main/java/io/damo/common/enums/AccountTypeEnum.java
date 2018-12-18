package io.damo.common.enums;


import java.util.Objects;

public enum AccountTypeEnum {

    //类型（0.注册 1.兑换 2.转账 3.到账 4.C2C买入 5.C2C卖出 6.兑换算力 7.开采算力 8.流通算力）
    REGISTER(0,"注册"),
    EXCHANGE_GKT(1,"兑换数字资产"),
    TRANSFER(2,"转账"),
    TRANSFER_IN(3,"到账"),
    PAY_IN(4,"C2C买入"),
    PAYOUT(5,"C2C卖出"),
    EXCHANGE_CALC(6,"兑换算力"),
    EXPLOITATION_CALC(7,"开采算力"),
    CIRCULATION_CALC(8,"流通算力"),
    EXCHANGE_POOL(9,"兑换矿石积分"),
    RECHANGE(10,"充值"),
    MALL(11,"充值");

    private Integer type;

    private String desc;

    AccountTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AccountTypeEnum getValue(int value) {
        for (AccountTypeEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
