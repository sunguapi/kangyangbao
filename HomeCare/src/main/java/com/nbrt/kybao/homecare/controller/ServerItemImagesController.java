package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.entity.ServerItemImagesEntity;
import com.nbrt.kybao.homecare.service.ServerItemImagesService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 服务项图片
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 14:51:20
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/serveritemimages")
public class ServerItemImagesController {
    private final ServerItemImagesService serverItemImagesService;

    public ServerItemImagesController(ServerItemImagesService serverItemImagesService) {
        this.serverItemImagesService = serverItemImagesService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serverItemImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ServerItemImagesEntity serverItemImages = serverItemImagesService.getById(id);

        return R.ok().put("serverItemImages", serverItemImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ServerItemImagesEntity serverItemImages){
		serverItemImagesService.save(serverItemImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ServerItemImagesEntity serverItemImages){
		serverItemImagesService.updateById(serverItemImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		serverItemImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
