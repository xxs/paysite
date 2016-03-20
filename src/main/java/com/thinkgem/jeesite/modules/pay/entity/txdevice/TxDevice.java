/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.txdevice;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 交易流水Entity
 * @author xde
 * @version 2016-03-21
 */
public class TxDevice extends DataEntity<TxDevice> {
	
	private static final long serialVersionUID = 1L;
	private Long pk;		// 主键
	private String funcId;		// 功能
	private String storeId;		// 门店
	private String txCode;		// 号码
	private String txTime;		// 时间
	private User user;		// 用户
	private String deviceId;		// 设备
	private String merchantId;		// merchant_id
	private String orderNo;		// 编号
	private String orderReqNo;		// order_req_no
	private String orderDate;		// order_date
	private String ourTransNo;		// our_trans_no
	private String amount;		// amount
	private String transStatus;		// trans_status
	private String refundFlag;		// refund_flag
	private String customerId;		// customer_id
	private Date payTime;		// pay_time
	
	public TxDevice() {
		super();
	}

	public TxDevice(String id){
		super(id);
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@Length(min=0, max=64, message="功能长度必须介于 0 和 64 之间")
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	@Length(min=0, max=64, message="门店长度必须介于 0 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=8, message="号码长度必须介于 0 和 8 之间")
	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}
	
	@Length(min=0, max=32, message="时间长度必须介于 0 和 32 之间")
	public String getTxTime() {
		return txTime;
	}

	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="设备长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=64, message="merchant_id长度必须介于 0 和 64 之间")
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=32, message="编号长度必须介于 0 和 32 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=32, message="order_req_no长度必须介于 0 和 32 之间")
	public String getOrderReqNo() {
		return orderReqNo;
	}

	public void setOrderReqNo(String orderReqNo) {
		this.orderReqNo = orderReqNo;
	}
	
	@Length(min=0, max=32, message="order_date长度必须介于 0 和 32 之间")
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	@Length(min=0, max=32, message="our_trans_no长度必须介于 0 和 32 之间")
	public String getOurTransNo() {
		return ourTransNo;
	}

	public void setOurTransNo(String ourTransNo) {
		this.ourTransNo = ourTransNo;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=8, message="trans_status长度必须介于 0 和 8 之间")
	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	@Length(min=0, max=8, message="refund_flag长度必须介于 0 和 8 之间")
	public String getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	
	@Length(min=0, max=64, message="customer_id长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
}