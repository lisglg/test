package io.damo.common.enums;


import java.util.Objects;

public enum MallOrderPayTypeEnum {

    //类型 0.待支付 1.待发货 2.待收货 3.已完成 4.已取消
    PAY(0,"待支付"),
    SNED(1,"待发货"),
    RECEIVE(2,"待收货"),
    SUCCESS(3,"已完成"),
    CANCEL(4,"已取消");

    private Integer type;

    private String desc;

    MallOrderPayTypeEnum(Integer type, String desc) {
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

    public static MallOrderPayTypeEnum getValue(int value) {
        for (MallOrderPayTypeEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
