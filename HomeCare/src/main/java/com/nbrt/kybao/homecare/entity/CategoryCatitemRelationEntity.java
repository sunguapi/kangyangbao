package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 分类分类项关联
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_category_catitem_relation")
@ApiModel("分类分类项关联实体")
public class CategoryCatitemRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类分类项关联id
	 */
	@TableId
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 分类项id
	 */
	private Long catItemId;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 分类项名称
	 */
	private String catItemName;

}
