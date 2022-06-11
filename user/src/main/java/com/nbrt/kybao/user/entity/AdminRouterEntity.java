package com.nbrt.kybao.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
@Data
@TableName("user_admin_router")
public class AdminRouterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 路由id
	 */
	@TableId
	private Integer routerId;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 组件名称
	 */
	private String commponent;
	/**
	 * 组件标题
	 */
	private String name;
	/**
	 * 转发地址
	 */
	private String redirect;
	/**
	 * 目标数据id
	 */
	private Integer metaId;
	/**
	 * 是否隐藏
	 */
	private Integer hidden;
	/**
	 * 孩子id
	 */
	private Integer childrenId;

}
