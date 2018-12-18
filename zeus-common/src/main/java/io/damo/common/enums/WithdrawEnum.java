package io.damo.common.enums;

import java.util.Objects;

public enum WithdrawEnum {
    UN_AUDIT(0,"待审核"),
    SUCCESS(1,"已通过"),
    FAIL(2,"未通过"),
    UNDETERMINED(3,"待定");

    private Integer type;

    private String desc;

    WithdrawEnum(Integer type, String desc) {
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

    public static WithdrawEnum getValue(int value) {
        for (WithdrawEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
