package io.damo.common.json;

/** 返回码 */
public enum Code {
    /** 成功 */
    SUCCESS(0),
    /** 失败 */
    FAIL(1),
    /** 未认证 */
    NO_CERTIFIED(2),
    /** 无权限 */
    NO_PERMISSION(3),
    /** 未登录 */
    NO_LOGIN(10);

    int flag;

    Code(int flag){
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
