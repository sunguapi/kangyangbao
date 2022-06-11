package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 服务分类项
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_cat_item")
@ApiModel("分类项实体")
public class CatItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类项id
	 */
	@TableId
	private Long catItemId;
	/**
	 * 分类项名称
	 */
	private String catItemName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标地址
	 */
	private String icon;
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

}
