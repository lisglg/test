package io.damo.common.enums;

import java.util.Objects;

/**
 * 矿石开采算力枚举类
 */
public enum IncomTypeEnum {

    KGC_EXCHANAGE(1,"NP兑换"),
    EXCHANAGE_CALC(2,"兑换算力"),
    CIRCULATION_CALC(3,"流通算力");

    private Integer type;

    private String desc;

    IncomTypeEnum(Integer type, String desc) {
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

    public static IncomTypeEnum getValue(int value) {
        for (IncomTypeEnum incomTypeEnum : values()) {
            if (Objects.equals(incomTypeEnum.getType(), value)) {
                return incomTypeEnum;
            }
        }
        return null;
    }
}
