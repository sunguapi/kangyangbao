package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Consumption;
import com.nbrt.kybao.entity.VipType;
import com.nbrt.kybao.mapper.VipTypeMapper;
import com.nbrt.kybao.service.ConsumptionService;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.vo.ConsumptionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


/**
 * @author sunjinbao
 * @date 2022/6/2
 */
@CrossOrigin
@Api(tags = "积分中心" )
@RestController
@RequestMapping("/consumption")
@Slf4j
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @Autowired
    private VipTypeMapper vipTypeMapper;


    @ApiOperation("积分中心：查询场地的会员列表信息")
    @GetMapping("/searchConsumptionList")
    @ApiImplicitParam(name = "consumerSite", value = "消费场地")
    public AjaxResponse<Page<ConsumptionVo>> searchConsumptionList(String consumerSite, int pageNum, int pageSize) {

//        PageHelper.startPage(pageNum, pageSize);

//        QueryWrapper<Consumption> wrapper = new QueryWrapper<Consumption>().eq("consumer_site", consumerSite);
//        List<Consumption> consumptions = consumptionService.getBaseMapper().selectList(wrapper);
//        List<ConsumptionVo> collect = consumptions.stream().map(item -> {
//            ConsumptionVo consumptionVo = new ConsumptionVo();
//            BeanUtils.copyProperties(item, consumptionVo);
//            Integer memberId = item.getMemberId();
//            VipType vipType = vipTypeMapper.selectById(memberId);
//            consumptionVo.setVipType(vipType.getVipType());
//            consumptionVo.setAmount_con(vipType.getAmountCon());
//            return consumptionVo;
//        }).collect(Collectors.toList());


        return AjaxResponse.success(consumptionService.searchConsumptionList(consumerSite, pageNum, pageSize));
    }

    @ApiOperation("积分中心：修改积分抵扣")
    @GetMapping("/updateConsumerSiteByVipId")
    public AjaxResponse updateConsumerSiteByVipId(
                                                  String vipType,
                                                  BigDecimal consumerDiscount,
                                                  String consumerSite,
                                                  BigDecimal consumerBack,
                                                  BigDecimal integralRate,
                                                  BigDecimal consumerBili) {
            QueryWrapper<Consumption> wrapper = new QueryWrapper<Consumption>()
                    .eq("vip_type", vipType)
                    .eq("consumer_site", consumerSite);

            Consumption consumption = new Consumption();
            consumption.setIntegralRate(integralRate);
            consumption.setConsumerBack(consumerBack);
            consumption.setConsumerBili(consumerBili);
            consumption.setConsumerDiscount(consumerDiscount);

            consumptionService.getBaseMapper().update(consumption, wrapper);

        return AjaxResponse.success();
    }

}
