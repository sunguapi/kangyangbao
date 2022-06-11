package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 服务分类项关联
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_server_catitem_relation")
@ApiModel("服务分类项关联实体")
public class ServerCatitemRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务分类项关联id
	 */
	@TableId
	private Long id;
	/**
	 * 服务id
	 */
	private Long serverId;
	/**
	 * 分类项id
	 */
	private Long catItemId;
	/**
	 * 服务名称
	 */
	private String serverName;
	/**
	 * 分类项名称
	 */
	private String catItemName;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;

}
