package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NewsMapper extends BaseMapper<News> {

    List<String> getNewsTypeAll();

    Page<News> getNewsList(
            @Param("page") Page<News> page,
            @Param("type") String type,
            @Param("newsName") String newsName
    );


    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Transactional
    boolean addNews(News news);

    /**
     * (批量)删除新闻-根据新闻id
     * @param id
     * @return
     */
    int deleteNewsById(Integer id);

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
