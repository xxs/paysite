/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.payment;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 支付方式管理Entity
 * @author xxs
 * @version 2016-03-14
 */
public class PayPaymentMethod extends DataEntity<PayPaymentMethod> {
	
	private static final long serialVersionUID = 1L;
	private String pk;		// 主键
	private String name;		// 名称
	private String type;		// 类型
	
	public PayPaymentMethod() {
		super();
	}

	public PayPaymentMethod(String id){
		super(id);
	}

	@Length(min=1, max=255, message="主键长度必须介于 1 和 255 之间")
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}