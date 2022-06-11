package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.entity.RefundEntity;
import com.nbrt.kybao.homecare.service.RefundService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;

import org.springframework.web.bind.annotation.*;


/**
 * 退款信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 11:11:42
 */
@CrossOrigin
@RestController
@RequestMapping("user/refund")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = refundService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		RefundEntity refund = refundService.getById(id);

        return R.ok().put("refund", refund);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RefundEntity refund){
		refundService.save(refund);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RefundEntity refund){
		refundService.updateById(refund);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		refundService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
