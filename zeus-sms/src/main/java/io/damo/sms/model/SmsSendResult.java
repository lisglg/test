package io.damo.sms.model;

/**
 * 短信接口调用结果
 */
public class SmsSendResult {
    /** 响应时间：yyyyMMddHHmmss */
    private String time;

    /** 响应状态：0 成功 */
    private String code;

    /** 失败时没有msgId */
    private String msgId;

    public SmsSendResult(String content) {
        super();
        String[] res = content.split("\n");
        String[] resp = res[0].split(",");
        this.time = resp[0];
        this.code = resp[1];
        this.msgId = res != null && res.length > 1 ? res[1] : null;
    }

    public SmsSendResult(String time, String code, String msgId) {
        this.time = time;
        this.code = code;
        this.msgId = msgId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
