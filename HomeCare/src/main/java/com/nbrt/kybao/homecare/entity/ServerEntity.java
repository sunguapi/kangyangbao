package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 服务
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Data
@TableName("homecare_server")
@ApiModel("服务实体")
public class ServerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务id
	 */
	@TableId
	private Long serverId;
	/**
	 * 服务名称
	 */
	private String serverName;
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
	/**
	 * 是否默认选中
	 */
	private Integer defaultOn;

}
