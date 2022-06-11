package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.News;
import com.nbrt.kybao.mapper.NewsMapper;
import com.nbrt.kybao.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper,News> implements NewsService {
    @Autowired
    private NewsMapper newsmapper;


    @Override
    public List<String> getNewsTypeAll() {
        return newsmapper.getNewsTypeAll();
    }

    @Override
    public Page<News> getNewsList(String type, Integer pageNumber, Integer pageSize, String newsName) {
        Page<News> page=new Page<>(pageNumber,pageSize);
        //查询所有
        return newsmapper.getNewsList(page,type,newsName);
    }


    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Override
    public boolean addNews(News news) {
        return newsmapper.addNews(news);
    }

    /**
     * (批量)删除新闻-根据新闻id
     * @param id
     * @return
     */
    @Override
    public boolean deleteNewsById(Integer[] id) {//循环
        int count =0;
        for (Integer integer : id) {
            int i=newsmapper.deleteNewsById(integer);
            count+=i;
        }
        return count==id.length;
    }

    /**
     * 修改新闻-根据新闻id
     * @param news
     * @return
     */
    @Override
    public boolean updateNewsById(News news) {
        return newsmapper.updateNewsById(news);
    }

    /**
     * 查询新闻-根据新闻id
     * @param id
     * @return
     */
    @Override
    public News getNewsById(int id) {
        return newsmapper.getNewsById(id);
    }

    /**
     * 首页新闻查询显示简略-根据新闻类别
     * @param newsType
     * @return
     */
    @Override
    public List<News> getAllNewsByNewsType(int newsType) {
        return newsmapper.getAllNewsByNewsType(newsType);
    }

}
