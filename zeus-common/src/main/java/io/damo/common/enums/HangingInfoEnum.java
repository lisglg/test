package io.damo.common.enums;

import java.util.Objects;

public enum HangingInfoEnum {
    BUY(0,"挂卖"),
    SELL(1,"卖出"),
    BUYING(2,"交易中"),
    CANCEL(3,"已取消");

    private Integer type;

    private String desc;

    HangingInfoEnum(Integer type, String desc) {
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

    public static HangingInfoEnum getValue(int value) {
        for (HangingInfoEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
