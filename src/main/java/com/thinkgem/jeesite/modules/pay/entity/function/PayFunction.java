/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.function;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 功能按钮管理Entity
 * @author xxs
 * @version 2016-02-25
 */
public class PayFunction extends DataEntity<PayFunction> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 按钮编号
	private String name;		// 名称
	private String status;		// 按钮状态
	private String memo;		// 备注信息
	
	public PayFunction() {
		super();
	}

	public PayFunction(String id){
		super(id);
	}

	@Length(min=0, max=11, message="按钮编号长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=255, message="按钮状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="备注信息长度必须介于 0 和 255 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}