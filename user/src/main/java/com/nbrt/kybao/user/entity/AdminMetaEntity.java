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
@TableName("user_admin_meta")
public class AdminMetaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 目标数据id
	 */
	@TableId
	private Integer metaId;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 标题
	 */
	private String title;

}
