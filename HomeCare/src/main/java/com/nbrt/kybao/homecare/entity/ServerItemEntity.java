package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 服务项
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_server_item")
@ApiModel("服务项实体")
public class ServerItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务项id
	 */
	@TableId
	private Long serverItemId;
	/**
	 * 服务项名称
	 */
	private String serverItemName;
	/**
	 * 服务项价格
	 */
	private BigDecimal price;
	/**
	 * 销售总量
	 */
	private Long saleCount;
	/**
	 * 服务项描述
	 */
	private String descript;
	/**
	 * 描述
	 */
	private String explainServerItem;
	/**
	 * 上门时间
	 */
	private String arrivalTime;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 好评率
	 */
	private Integer praiseRate;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 服务时间
	 */
	private BigDecimal serverTime;

	@TableField(exist = false)
	private String serverType;

	private String dgxz;

}
