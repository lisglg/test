package io.damo.common.enums;

import java.util.Objects;

public enum EnabledDisableStatusEnum {

    ENABLED(0,"启用"),
    DISABLE(1,"禁用");
    private Integer type;

    private String desc;

    EnabledDisableStatusEnum(Integer type, String desc) {
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

    public static EnabledDisableStatusEnum getValue(Integer value) {
        for (EnabledDisableStatusEnum enabledDisableStatusEnum : values()) {
            if (Objects.equals(enabledDisableStatusEnum.getType(), value)) {
                return enabledDisableStatusEnum;
            }
        }
        return null;
    }
}
