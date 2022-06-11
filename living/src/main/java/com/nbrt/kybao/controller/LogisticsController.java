package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Logistics;
import com.nbrt.kybao.mapper.LogisticsMapper;
import com.nbrt.kybao.service.LogisticsService;
import com.nbrt.kybao.utils.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@CrossOrigin
@Api(tags = "物流中心" )
@RestController
@RequestMapping("/user")
@Accessors(chain = true)
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @ApiOperation("物流中心：查询所有物流公司")
    @GetMapping("/selectLogistics")
    public AjaxResponse<Page<Logistics>> selectLogistics(Integer pageNum, Integer pageSize) {
        Page<Logistics> page = new Page<>(pageNum, pageSize);
        Page<Logistics> pages=logisticsService.getPageList(page);
        return AjaxResponse.success(pages);
    }

    @ApiOperation("物流中心：增加物流公司")
    @PostMapping("/insertLogistics")
    public AjaxResponse insertLogistics(@RequestBody Logistics logistics) {
        logisticsService.save(logistics);
        return AjaxResponse.success();
    }

    @ApiOperation("物流中心：删除物流公司")
    @GetMapping("/deleteLogistics")
    public AjaxResponse deleteLogistics(int id) {
        return AjaxResponse.success(logisticsService.removeById(id));
    }

    @ApiOperation("物流中心：更新物流公司")
    @PostMapping("/updateLogistics")
    public AjaxResponse updateLogistics(@RequestBody Logistics logistics) {
        logisticsService.updateById(logistics);
        return AjaxResponse.success();
    }

    @ApiOperation("物流中心：查询所有物流公司名称")
    @GetMapping("/selectLogisticsForName")
    public AjaxResponse<List<Logistics>> selectLogisticsForName() {
        List<Logistics> logistics = logisticsService.getBaseMapper().selectList(null);
        return AjaxResponse.success(logistics);
    }

}
