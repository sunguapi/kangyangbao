package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.ServerServeritemRelationEntity;
import com.nbrt.kybao.homecare.service.ServerServeritemRelationService;




/**
 * 服务服务项关联
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/serverserveritemrelation")
//@Api(tags = "服务服务项")
public class ServerServeritemRelationController {
    private final ServerServeritemRelationService serverServeritemRelationService;

    public ServerServeritemRelationController(ServerServeritemRelationService serverServeritemRelationService) {
        this.serverServeritemRelationService = serverServeritemRelationService;
    }

    /**
     * 列表
     */
    @ApiOperation("查询服务服务项关联信息")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serverServeritemRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("查询服务服务项关联信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ServerServeritemRelationEntity serverServeritemRelation = serverServeritemRelationService.getById(id);

        return R.ok().put("serverServeritemRelation", serverServeritemRelation);
    }

    /**
     * 保存
     */
    @ApiOperation("保存服务服务项关联信息")
    @PostMapping("/save")
    public R save(@RequestBody ServerServeritemRelationEntity serverServeritemRelation){
		serverServeritemRelationService.save(serverServeritemRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改服务服务项关联信息")
    @PostMapping("/update")
    public R update(@RequestBody ServerServeritemRelationEntity serverServeritemRelation){
		serverServeritemRelationService.updateById(serverServeritemRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除服务服务项关联信息")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		serverServeritemRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
