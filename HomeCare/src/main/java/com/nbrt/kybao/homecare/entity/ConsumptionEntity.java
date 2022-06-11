package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

import javax.swing.plaf.basic.BasicIconFactory;

/**
 * 消费标准表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 17:35:22
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
	 * 佣金折扣
	 */
	private BigDecimal consumerDiscount;
	/**
	 * 消费积分
	 */
	private Integer consumerBack;
	/**
	 * 功能场景
	 */
	private String consumerSite;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 金额积分
	 */
	private Integer integralRate;
	/**
	 * 会员级别
	 */
	private String vipType;
	/**
	 * 金额积分占比
	 */
	private BigDecimal consumerBili;
	/**
	 * 会员编号
	 */
	private Long userGrade;

}
