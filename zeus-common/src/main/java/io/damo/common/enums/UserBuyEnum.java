package io.damo.common.enums;


import java.util.Objects;

public enum UserBuyEnum {

    UN_AUDIT(0,"待确定"),
    SUCCESS(1,"成功"),
    REJECT(2,"拒绝");

    private Integer type;

    private String desc;

    UserBuyEnum(Integer type, String desc) {
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

    public static UserBuyEnum getValue(int value) {
        for (UserBuyEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
