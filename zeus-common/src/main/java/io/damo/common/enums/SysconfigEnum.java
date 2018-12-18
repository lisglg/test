package io.damo.common.enums;

import java.util.Objects;

public enum SysconfigEnum {
    CONFIG(0,"配置"),
    RECORD(1,"记录");

    private Integer type;

    private String desc;

    SysconfigEnum(Integer type, String desc) {
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

    public static SysconfigEnum getValue(int value) {
        for (SysconfigEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
