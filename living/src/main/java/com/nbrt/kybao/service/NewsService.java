package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.News;

import java.util.List;

public interface NewsService  extends IService<News> {

    List<String> getNewsTypeAll();

    Page<News> getNewsList(String type, Integer pageNumber, Integer pageSize, String newsName);

    /**
     * 添加新闻
     * @param news
     * @return
     */
    boolean addNews(News news);

    /**
     * (批量)删除新闻-根据新闻id
     * @param id
     * @return
     */
    boolean deleteNewsById(Integer[] id);

    /**
     * 修改新闻-根据新闻id
     * @param news
     * @return
     */
    boolean updateNewsById(News news);

    /**
     * 查询新闻-根据新闻id
     * @param id
     * @return
     */
    News getNewsById(int id);

    /**
     * 首页新闻查询显示简略-根据新闻类别
     * @param newsType
     * @return
     */
    List<News> getAllNewsByNewsType(int newsType);
}
