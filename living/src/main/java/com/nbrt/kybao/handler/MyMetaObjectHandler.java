package com.nbrt.kybao.mall.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 对数据库每条记录的创建时间和更新时间自动进行填充
 * @author hjh
 * @date 2022/5/5 17:44
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date=new Date();
        this.setFieldValByName("createTime",date,metaObject);
        this.setFieldValByName("updateTime",date,metaObject);
        this.setFieldValByName("version",1,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date=new Date();
        this.setFieldValByName("updateTime",date,metaObject);
    }
}
