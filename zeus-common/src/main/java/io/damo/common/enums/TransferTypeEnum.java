package io.damo.common.enums;

import java.util.Objects;

public enum TransferTypeEnum {

    PROFIT(1,"转账"),

    EXPENDITURE(2,"到账");

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

    TransferTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static TransferTypeEnum getValue(int value) {
        for (TransferTypeEnum msg : values()) {
            if (Objects.equals(msg.getType(), value)) {
                return msg;
            }
        }
        return null;
    }
}
