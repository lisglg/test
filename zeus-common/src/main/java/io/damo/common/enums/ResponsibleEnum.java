package io.damo.common.enums;


import java.util.Objects;

public enum ResponsibleEnum {

    BUY_RESPONSIBLE(0,"买方责任"),
    SELL_RESPONSIBLE(1,"卖方责任"),
    ALL_NOT_RESPONSIBLE(2,"双方无责任");

    private Integer type;

    private String desc;

    ResponsibleEnum(Integer type, String desc) {
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

    public static ResponsibleEnum getValue(int value) {
        for (ResponsibleEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
