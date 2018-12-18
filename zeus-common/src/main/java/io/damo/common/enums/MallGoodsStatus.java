package io.damo.common.enums;


import java.util.Objects;

public enum MallGoodsStatus {

    // 状态（0.已上架、1.已下架）
    SHELF(0,"已上架"),
    OBTAINED(1,"已下架"),
    REVIEW(2,"审核中"),
    REVIEWFAIL(3,"审核不通过");


    private Integer type;

    private String desc;

    MallGoodsStatus(Integer type, String desc) {
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

    public static MallGoodsStatus getValue(int value) {
        for (MallGoodsStatus withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }

}
