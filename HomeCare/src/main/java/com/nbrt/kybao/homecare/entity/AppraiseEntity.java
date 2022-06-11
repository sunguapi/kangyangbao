package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 14:51:20
 */
@Data
@TableName("homecare_appraise")
public class AppraiseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
