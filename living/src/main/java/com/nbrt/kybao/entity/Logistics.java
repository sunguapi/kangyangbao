package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nbrt.kybao.mapper.LogisticsMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("logistics")
public class Logistics {

    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String logo;
    private String couName;
}
