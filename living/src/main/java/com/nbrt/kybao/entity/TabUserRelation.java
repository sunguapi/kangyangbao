package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "查询子女信息")
@TableName("tab_user")
public class TabUserRelation {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String primaryUserNo;
    private String childrenUserNo;
    private Date createTime;
    private Date updateTime;
}
