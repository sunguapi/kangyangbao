package com.nbrt.kybao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)//可以链式调用
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收货地址")
@TableName("receiving_address")
public class ReceivingAddress implements Serializable {

    private String name;
    private String phone;
    private String province;
    private String city;
    private String address;

}
