package io.damo.common.enums;

import java.util.Objects;

public enum TransferEnum {
    KGC(0,"NP") ;
    private Integer type;
    private String desc;
    TransferEnum(Integer type, String desc) {
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

    public static TransferEnum getValue(Integer value) {
        for (TransferEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
