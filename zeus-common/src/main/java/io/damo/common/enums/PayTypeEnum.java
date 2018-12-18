package io.damo.common.enums;

import java.util.Objects;

public enum  PayTypeEnum {

    KGC(0,"NP"),
    GKT(1,"数字资产");

    private Integer type;

    private String desc;

    PayTypeEnum(Integer type, String desc) {
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

    public static PayTypeEnum getValue(Integer value) {
        for (PayTypeEnum payTypeEnum : values()) {
            if (Objects.equals(payTypeEnum.getType(), value)) {
                return payTypeEnum;
            }
        }
        return null;
    }
}
