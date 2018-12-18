package io.damo.common.json;

import io.swagger.annotations.ApiModelProperty;

public class JsonResult<T> {

    @ApiModelProperty("返回码")
    private int code = Code.FAIL.flag;

    @ApiModelProperty("返回说明")
    private String msg = "failed";

    @ApiModelProperty("返回数据")
    private T data;

    public JsonResult(Code code, String msg, T data) {
        this.code = code.flag;
        this.msg = msg;
        this.data = data;
    }


    // ---------- 下面的静态方法, 在 controller 中调用 ----------

    /**
     * 成功时要有 msg 说明
     */
    public static <T> JsonResult<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * 返回的数据不需要过滤属性时
     */
    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult(Code.SUCCESS, msg, data);
    }

    /**
     * 失败时要有 msg 说明
     */
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult(Code.FAIL, msg, null);
    }

}
