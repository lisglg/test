package io.damo.common.enums;

import java.util.Objects;

public enum ExchangeEnum {

    //兑换的目标类型 对应表中-destination_type字段
    POOL_INTEGRAL(1,"矿石积分"),
    //兑换的目标类型 对应表中-destination_type字段
    GKT(2,"数字资产") ,
    //兑换前的类型 兑换表中对应的exchange_type字段
    KGC(1,"NP");

    private Integer type;

    private String desc;

    ExchangeEnum(Integer type, String desc) {
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

    public static ExchangeEnum getValue(Integer value) {
        for (ExchangeEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
