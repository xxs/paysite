/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.device;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;

/**
 * 设备管理Entity
 * @author xxs
 * @version 2016-02-23
 */
public class PayDevice extends DataEntity<PayDevice> {
	
	private static final long serialVersionUID = 1L;
	private PayStore payStore;		// 所属门店
	private String number;		// 编号
	private String name;		// 名称
	private String status;		// 状态
	private String memo;		// 描述
	
	public PayDevice() {
		super();
	}

	public PayDevice(String id){
		super(id);
	}

	@JsonBackReference
	public PayStore getPayStore() {
		return payStore;
	}

	public void setPayStore(PayStore payStore) {
		this.payStore = payStore;
	}
	
	@Length(min=0, max=255, message="编号长度必须介于 0 和 255 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}