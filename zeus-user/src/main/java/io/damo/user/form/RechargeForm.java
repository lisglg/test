package io.damo.user.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@Data
public class RechargeForm {

    @ApiModelProperty("用户ID")
    @NotBlank(message="用户ID不能为空")
    private String userId;

    @ApiModelProperty("NP")
    @NotBlank(message="NP不能为空")
    private BigDecimal kgc;

    @ApiModelProperty("矿石积分")
    @NotBlank(message="矿石积分不能为空")
    private BigDecimal minePoolPoints;

    @ApiModelProperty("数字资产")
    @NotBlank(message="数字资产不能为空")
    private BigDecimal gkt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getKgc() {
        return kgc;
    }

    public void setKgc(BigDecimal kgc) {
        this.kgc = kgc;
    }

    public BigDecimal getMinePoolPoints() {
        return minePoolPoints;
    }

    public void setMinePoolPoints(BigDecimal minePoolPoints) {
        this.minePoolPoints = minePoolPoints;
    }

    public BigDecimal getGkt() {
        return gkt;
    }

    public void setGkt(BigDecimal gkt) {
        this.gkt = gkt;
    }

    @Override
    public String toString() {
        return "RechargeForm{" +
                "userId='" + userId + '\'' +
                ", kgc=" + kgc +
                ", minePoolPoints=" + minePoolPoints +
                ", gkt=" + gkt +
                '}';
    }
}
