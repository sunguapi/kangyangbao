package com.nbrt.kybao.homecare.controller;

import java.util.List;

import com.nbrt.kybao.homecare.utils.R;
import com.nbrt.kybao.homecare.vo.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.ServerVo;
import com.nbrt.kybao.homecare.vo.server.ServerChangeVo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.service.ServerService;




/**
 * 服务
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/server")
@Api(tags = "服务")
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

//    /**
//     * 服务信息列表条件带分页
//     */
//    @ApiOperation("(管理端)查询服务列表信息")
//    @GetMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = serverService.queryPage(params);
//
//        return R.ok().put("data", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @ApiOperation("查询服务信息")
//    @GetMapping("/info/{serverId}")
//    public R info(@PathVariable("serverId") Long serverId){
//		ServerEntity server = serverService.getById(serverId);
//
//        return R.ok().put("server", server);
//    }

    @ApiOperation("(移动端)查询服务列表页面数据")
    @GetMapping("/server/data/{catItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catItemId", value = "分类项id"),
            })
    public R getServerData(
            @PathVariable(value = "catItemId") Long catItemId) {
        ServerVo serverVo = serverService.getServerData(catItemId);
        return R.ok().put("data",serverVo);
    }

    @ApiOperation("(移动端)排序切换")
    @GetMapping("/order/change/{serverId}/{orderNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverId", value = "服务id"),
            @ApiImplicitParam(name = "orderNum", value = "1好评率降序2价格升序3销量降序4到达时间升序5价格升序"),
    })
    public R orderChange(@PathVariable Long serverId,@PathVariable Long orderNum) {
        List<ServerItemSaveVo> serverItemVos = serverService.orderChange(serverId,orderNum);
        return R.ok().put("data",serverItemVos);
    }


    @ApiOperation("(移动端)服务切换")
    @GetMapping("change/server/{serverId}/{catItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverId", value = "服务id"),
    })
    public R changeServer(
            @ApiParam("服务id") @PathVariable(value = "serverId")Long serverId,
            @ApiParam("分类项id") @PathVariable(value = "catItemId") Long catItemId) {
        ServerChangeVo serverChangeVo = serverService.changeServer(serverId,catItemId);
         return R.ok().put("data",serverChangeVo);
    }



//    /**
//     * 保存
//     */
//    @ApiOperation("保存服务信息")
//    @PostMapping("/save")
//    public R save(@RequestBody ServerEntity server){
//		serverService.save(server);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @ApiOperation("修改服务信息")
//    @PostMapping("/update")
//    public R update(@RequestBody ServerEntity server){
//		serverService.updateById(server);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation("删除服务信息")
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] serverIds){
//		serverService.removeByIds(Arrays.asList(serverIds));
//
//        return R.ok();
//    }

}
