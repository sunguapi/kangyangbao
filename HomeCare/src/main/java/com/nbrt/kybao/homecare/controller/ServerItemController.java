package com.nbrt.kybao.homecare.controller;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import com.nbrt.kybao.homecare.vo.ServerItemDetailVo;

import com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.severItem.ServerItemUpdateVo;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.service.ServerItemService;

import java.util.Map;


/**
 * 服务项
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/serveritem")
@Api(tags = "服务项模块")
public class ServerItemController {
    private final ServerItemService serverItemService;

    public ServerItemController(ServerItemService serverItemService) {
        this.serverItemService = serverItemService;
    }

    /**
     * 服务项列表
     */
    @ApiOperation("(管理端)查询服务项列表信息")
    @GetMapping("/list/")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serverItemService.queryPage(params);

        return R.ok().put("data", page);
    }

    @ApiOperation("(移动端)查询服务项详情页信息")
    @GetMapping("/detailInfo/{serverItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverItemId", value = "服务项id"),
    })
    public R getServerItemDetail(@PathVariable Long serverItemId) {
        ServerItemDetailVo serverItemDetail = serverItemService.getServerItemDetail(serverItemId);
        return R.ok().put("data",serverItemDetail);
    }

    /**
     * 查询服务项信息
     */
    @ApiOperation("(管理端)查询服务项信息")
    @GetMapping("/info/{serverItemId}")
    public R info(@PathVariable("serverItemId") Long serverItemId){
		ServerItemUpdateVo serverItemVo = serverItemService.getServerItemById(serverItemId);

        return R.ok().put("data", serverItemVo);
    }

    /**
     * 保存服务项
     */
    @ApiOperation("(管理端)保存服务项信息")
    @PostMapping("/serverItem/save")
    public R save(@RequestBody com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItemVo){
		serverItemService.saveSeverItem(serverItemVo);
        return R.ok();
    }

    /**
     * 修改服务项
     */
    @ApiOperation("(管理端)修改服务项信息")
    @PostMapping("/update")
    public R update(@RequestBody com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItem){
		serverItemService.updateServerItemById(serverItem);
        return R.ok();
    }

    /**
     * 删除服务项
     */
    @ApiOperation("(管理端)删除服务项信息")
    @DeleteMapping("/delete/{serverItemId}")
    public R delete(@PathVariable Long serverItemId){
		serverItemService.removeServerItemById(serverItemId);
        return R.ok();
    }

}
