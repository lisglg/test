package io.damo.common.enums;


import java.util.Objects;

public enum UserTypeEnum {

    VIP_1(1,"注册会员"),
    VIP_2(2,"有效节点"),
    VIP_3(3,"超级节点"),
    VIP_4(4,"全球节点");

    private Integer type;

    private String desc;

    UserTypeEnum(Integer type, String desc) {
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

    public static UserTypeEnum getValue(int value) {
        for (UserTypeEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
