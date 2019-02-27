package io.damo.web.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import io.damo.common.aspect.OpLogger;
import io.damo.common.response.CommonResponse;
import io.damo.user.entity.AppPromotionRecordEntity;
import io.damo.user.service.AppPromotionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * app版本升级配置表
 *
 * @author ives
 * @date 2018-05-05 10:20:44
 */
@RestController
@RequestMapping("AppPromotionRecordController")
@Api(tags = "app版本升级配置表")
public class AppPromotionRecordController {
    @Autowired
    private AppPromotionRecordService appPromotionRecordService;

    @GetMapping("/appPromotionVersion")
    @ApiOperation("app版本信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "Integer", name = "platForm", value = "平台类型（1.android、2.ios）", required = true)})
    @OpLogger
    public CommonResponse<AppPromotionRecordEntity> getAppPromotionVersion(Integer platForm) {
        AppPromotionRecordEntity appPromotionRecordEntity = new AppPromotionRecordEntity() ;
        EntityWrapper<AppPromotionRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("type = {0}", platForm);
        entityWrapper.orderBy("create_time",false);
        List<AppPromotionRecordEntity> appPromotionRecordEntityList =  appPromotionRecordService.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(appPromotionRecordEntityList)){
            appPromotionRecordEntity = appPromotionRecordEntityList.get(0);
        }
        return CommonResponse.success(appPromotionRecordEntity);
    }

}
