package com.nbrt.kybao.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收货信息表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
@Data
@TableName("tab_receive")
public class ReceiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 收货信息编号
	 */
	private String receiveNo;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 收货人姓名
	 */
	private String receiveName;
	/**
	 * 收货人电话
	 */
	private String receivePhone;
	/**
	 * 收货人手机号
	 */
	private String receiveMobile;
	/**
	 * 收货人省份
	 */
	private String receiveProvince;
	/**
	 * 收货人市
	 */
	private String receiveCity;
	/**
	 * 收货人区/县
	 */
	private String receiveDistrict;
	/**
	 * 详细地址
	 */
	private String receiveAddress;
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
	private Integer deleted;
	/**
	 * 是否默认选中
	 */
	private Integer defaultOn;

}
