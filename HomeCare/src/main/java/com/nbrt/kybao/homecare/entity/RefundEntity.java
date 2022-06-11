package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 退款信息表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 11:11:42
 */
@Data
@TableName("tab_refund")
public class RefundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 退款编号
	 */
	private String refundNo;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 退款金额
	 */
	private BigDecimal amount;
	/**
	 * 退款方式 微信、支付宝、银行、其他
	 */
	private String paymentType;
	/**
	 * 退款状态 -1 取消，0 进行中，1退款成功
	 */
	private Integer refundStatus;
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
	/**
	 * 退款说明
	 */
	private String remark;

}
