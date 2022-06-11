package com.nbrt.kybao.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 消费标准表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 18:23:48
 */
@Data
@TableName("tab_consumption")
public class ConsumptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 消费编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员消费折扣
	 */
	private BigDecimal consumerDiscount;
	/**
	 * 消费返还积分比例
	 */
	private BigDecimal consumerBack;
	/**
	 * 功能场景
	 */
	private String consumerSite;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 积分兑换率
	 */
	private BigDecimal integralRate;
	/**
	 * 会员级别
	 */
	private String vipType;
	/**
	 * 积分抵扣占比
	 */
	private BigDecimal consumerBili;
	/**
	 * 会员编号
	 */
	private Long userGrade;

}
