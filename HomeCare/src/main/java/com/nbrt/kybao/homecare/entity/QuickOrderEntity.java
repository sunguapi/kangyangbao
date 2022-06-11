package com.nbrt.kybao.homecare.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 快捷订单表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-05 13:00:35
 */
@Data
@TableName("homecare_quick_order")
public class QuickOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单主键
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 服务项编号
	 */
	private String propertyNo;
	/**
	 * 支付编号
	 */
	private String payNo;
	/**
	 * 收货信息编号
	 */
	private String receiveNo;
	/**
	 * 运费
	 */
	private BigDecimal postage;
	/**
	 * 购买的服务项数量

	 */
	private Integer proQuantity;
	/**
	 * 订单状态：-1 已取消，0 创建，1 未付款，2 已付款，3 未发货，4 已发货，5 已到货，6 待评价
	 */
	private Integer orderStatus;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 订单完成时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * mp逻辑删除
	 */
	private Integer showStatus;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 服务时间
	 */
	private String serverTime;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 下单用户
	 */
	private String userName;
	/**
	 * 服务项总价
	 */
	private BigDecimal totalPrice;
	/**
	 * 退款编号
	 */
	private String refundNo;
	/**
	 * 真实价格
	 */
	private BigDecimal realPrice;
	/**
	 * 花费积分
	 */
	private Integer integral;
	/**
	 * 积分折扣
	 */
	private BigDecimal integralDiscount;
	/**
	 * 会员优惠
	 */
	private BigDecimal memberPrice;
	/**
	 * 快捷订单名称
	 */
	private String quickName;
	/**
	 * 快捷订单图标
	 */
	private String icon;
}
