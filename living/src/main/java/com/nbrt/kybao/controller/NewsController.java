package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.News;
import com.nbrt.kybao.service.NewsService;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.utils.Respones;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "首页新闻模块")
@RequestMapping("/news")
@CrossOrigin
public class NewsController {
    @Autowired
    NewsService newsService;

    @ApiOperation("获取新闻类型")
    @GetMapping("/getNewsType")
    public Respones getNewsType(){
        return new Respones(newsService.getNewsTypeAll());
    }

    @ApiOperation("获取新闻列表")
    @GetMapping("/getNewsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")})
    public AjaxResponse<Page<News>> getNewsList(String type, Integer pageNumber, Integer pageSize, String newsName){
        Page<News> page=newsService.getNewsList(type,pageNumber,pageSize,newsName);
        return AjaxResponse.success(page);
    }


    /**
     * 新闻类-增删改查：
     */
    @ApiOperation("添加新闻")
    @PostMapping("/addNews")
    public Respones addNews(@RequestBody News news){
        if(newsService.addNews(news)){
            return Respones.success("添加成功！");
        }
        return Respones.error("添加失败！");
    }

    @ApiOperation("(批量)删除新闻-根据新闻id")
    @DeleteMapping("/deleteNewsByIds")
    public Respones deleteNewsById(Integer[] id){
        if(newsService.deleteNewsById(id)){
            return Respones.success("删除成功！");
        }
        return Respones.error("删除失败！");
    }

    @ApiOperation("修改新闻-根据新闻id")
    @PostMapping("/updateNewsById")
    public Respones updateNewsById(@RequestBody News news){
        if(newsService.updateNewsById(news)){
            return Respones.success("修改新闻信息成功！");
        }
        return Respones.error("修改新闻信息失败！");
    }

    @ApiOperation("查询新闻-根据新闻id")
    @GetMapping("/getNewsById/{id}")  //此处因模块等限制需加/{id}，与下方@PathVariable("id")对应，可以两处都删除
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "新闻id")})
    public Respones getNewsById(@PathVariable("id") int id){
        return Respones.success(newsService.getNewsById(id));
    }

    /**
     * 首页新闻 查询显示简略-根据新闻类别：
     */
    @ApiOperation("首页新闻查询显示简略-根据新闻类别")
    @GetMapping("/getAllNewsByNewsType/{newsType}")
    @ApiImplicitParams({@ApiImplicitParam(name = "newsType", value = "新闻类别")})
    public AjaxResponse<List<News>> getAllNewsByNewsType(@PathVariable("newsType") int newsType){
        return AjaxResponse.success(newsService.getAllNewsByNewsType(newsType));
    }




}
