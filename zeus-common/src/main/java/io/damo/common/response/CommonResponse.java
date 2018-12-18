package io.damo.common.response;

import io.damo.common.json.Code;
import io.damo.common.utils.U;
import io.swagger.annotations.ApiModelProperty;

/**
 *  返回结果
 *
 * @param <T>
 */
public class CommonResponse <T> {

    @ApiModelProperty("返回码: 0-成，非0-失败")
    protected int code = Code.FAIL.getFlag();
    @ApiModelProperty("消息")
    protected String msg = U.EMPTY;
    @ApiModelProperty("数据")
    protected T content;

    public CommonResponse(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    /**
     * 返回成功,自定义消息和数据
     * @param msg
     * @param content
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(String msg, T content){
        return new CommonResponse(Code.SUCCESS.getFlag(),msg,content);
    }

    /**
     * 返回成功,自定义消息,无内容
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(String msg){
        return success(msg,null);
    }

    /**
     * 返回成功,默认成功消息,自定义数据
     * @param content
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(T content){
        return success("success",content);
    }

    /**
     * 返回失败,自定义消息,无内容
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>CommonResponse<T> fail(String msg){
        return new CommonResponse(Code.FAIL.getFlag(),msg,null);
    }

    /**
     * 返回失败,自定义消息,无内容
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>CommonResponse<T> fail(int code,String msg){
        return new CommonResponse(Code.NO_CERTIFIED.getFlag(),msg,null);
    }

    /**
     * 返回失败,自定义消息和数据
     * @param msg
     * @param content
     * @param <T>
     * @return
     */
    public static <T>CommonResponse<T> fail(String msg,T content){
        return fail(msg,content);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
