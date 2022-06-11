package com.nbrt.kybao.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
@Data
@TableName("tab_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员名称
	 */
	private String memberName;
	/**
	 * 达到该会员的金额标准
	 */
	private BigDecimal amountStandard;
	/**
	 * 会员卡颜色
	 */
	private String memberColor;
	/**
	 * 会员卡图片路径
	 */
	private String memberImage;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 逻辑删除
	 */
	private Integer deleted;

}
