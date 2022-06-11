package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.entity.ConsumptionEntity;
import com.nbrt.kybao.homecare.service.ConsumptionService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 消费标准表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 18:23:48
 */
@CrossOrigin
@RestController
@RequestMapping("user/consumption")
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = consumptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ConsumptionEntity consumption = consumptionService.getById(id);

        return R.ok().put("consumption", consumption);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ConsumptionEntity consumption){
		consumptionService.save(consumption);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ConsumptionEntity consumption){
		consumptionService.updateById(consumption);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		consumptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
