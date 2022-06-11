package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 服务服务项关联
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_server_serveritem_relation")
@ApiModel("服务服务项关联实体")
public class ServerServeritemRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务服务项关联id
	 */
	@TableId
	private Long id;
	/**
	 * 服务id
	 */
	private Long serverId;
	/**
	 * 服务项id
	 */
	private Long serverItemId;
	/**
	 * 服务名称
	 */
	private String serverName;
	/**
	 * 服务项名称
	 */
	private String serverItemName;

}
