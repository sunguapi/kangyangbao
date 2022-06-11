package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjinbao
 * @date 2022/5/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User_Vip {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private int vipId;
    private int userId;
}
