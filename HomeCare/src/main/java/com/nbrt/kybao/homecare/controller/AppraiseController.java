package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.AppraiseEntity;
import com.nbrt.kybao.homecare.service.AppraiseService;




/**
 * 评论表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 14:51:20
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/appraise")
public class AppraiseController {
    private final AppraiseService appraiseService;

    public AppraiseController(AppraiseService appraiseService) {
        this.appraiseService = appraiseService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appraiseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		AppraiseEntity appraise = appraiseService.getById(id);

        return R.ok().put("appraise", appraise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppraiseEntity appraise){
		appraiseService.save(appraise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppraiseEntity appraise){
		appraiseService.updateById(appraise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		appraiseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
