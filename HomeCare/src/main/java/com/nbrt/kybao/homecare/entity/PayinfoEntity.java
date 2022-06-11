package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付信息表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-01 13:58:47
 */
@Data
@TableName("tab_payinfo")
public class PayinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 支付编号
	 */
	private String payNo;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 支付金额
	 */
	private BigDecimal amount;
	/**
	 * 支付方式 微信、支付宝、银行、其他
	 */
	private String paymentType;
	/**
	 * 支付状态 -1 取消，0 进行中，1支付成功
	 */
	private Integer payStatus;
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
