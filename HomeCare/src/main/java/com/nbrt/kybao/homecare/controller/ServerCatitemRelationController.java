package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.ServerCatitemRelationEntity;
import com.nbrt.kybao.homecare.service.ServerCatitemRelationService;




/**
 * 服务分类项关联
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/servercatitemrelation")
//@Api(tags = "服务分类项")
public class ServerCatitemRelationController {
    private final ServerCatitemRelationService serverCatitemRelationService;

    public ServerCatitemRelationController(ServerCatitemRelationService serverCatitemRelationService) {
        this.serverCatitemRelationService = serverCatitemRelationService;
    }

//    /**
//     * 列表
//     */
//    @ApiOperation("查询服务分类项关联列表信息")
//    @GetMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = serverCatitemRelationService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @ApiOperation("查询服务分类项关联信息")
//    @GetMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		ServerCatitemRelationEntity serverCatitemRelation = serverCatitemRelationService.getById(id);
//
//        return R.ok().put("serverCatitemRelation", serverCatitemRelation);
//    }

//    /**
//     * 保存
//     */
//    @ApiOperation("保存服务分类项关联信息")
//    @PostMapping("/save")
//    public R save(@RequestBody ServerCatitemRelationEntity serverCatitemRelation){
//		serverCatitemRelationService.save(serverCatitemRelation);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @ApiOperation("修改服务分类项关联信息")
//    @PostMapping("/update")
//    public R update(@RequestBody ServerCatitemRelationEntity serverCatitemRelation){
//		serverCatitemRelationService.updateById(serverCatitemRelation);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation("删除服务分类项关联信息")
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] ids){
//		serverCatitemRelationService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
