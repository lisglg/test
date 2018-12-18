package io.damo.common.enums;

import java.util.Objects;

public enum ComplaintsStatusEnum {
    NONE(0,"未投诉"),
    WAIT(1,"已投诉"),
    FINISH(2,"已处理");

    private Integer type;

    private String desc;

    ComplaintsStatusEnum(Integer type, String desc) {
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

    public static ComplaintsStatusEnum getValue(int value) {
        for (ComplaintsStatusEnum withdrawEnum : values()) {
            if (Objects.equals(withdrawEnum.getType(), value)) {
                return withdrawEnum;
            }
        }
        return null;
    }
}
