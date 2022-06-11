package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.entity.PayinfoEntity;
import com.nbrt.kybao.homecare.service.PayinfoService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import org.springframework.web.bind.annotation.*;


/**
 * 支付信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-01 13:58:47
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/payinfo")
public class PayinfoController {
    private final PayinfoService payinfoService;

    public PayinfoController(PayinfoService payinfoService) {
        this.payinfoService = payinfoService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = payinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PayinfoEntity payinfo = payinfoService.getById(id);

        return R.ok().put("payinfo", payinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PayinfoEntity payinfo){
		payinfoService.save(payinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PayinfoEntity payinfo){
		payinfoService.updateById(payinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		payinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
