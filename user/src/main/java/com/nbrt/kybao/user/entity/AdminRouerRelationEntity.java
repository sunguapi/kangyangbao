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
 * @date 2022-05-25 13:59:09
 */
@Data
@TableName("user_admin_rouer_relation")
public class AdminRouerRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 路由id
	 */
	private Integer routerId;
	/**
	 * 用户名
	 */
	private String username;

}
