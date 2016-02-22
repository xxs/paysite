/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.company;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户管理功能Entity
 * @author xxs
 * @version 2016-02-22
 */
public class PayCompany extends DataEntity<PayCompany> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商户名称
	private String regName;		// 营业执照商户名称
	private String status;		// 商户状态
	private String tel;		// 电话
	private String addr;		// 地址
	private String memo;		// 描述
	private String image;		// logo
	
	public PayCompany() {
		super();
	}

	public PayCompany(String id){
		super(id);
	}

	@Length(min=0, max=128, message="商户名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=128, message="营业执照商户名称长度必须介于 0 和 128 之间")
	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}
	
	@Length(min=0, max=128, message="商户状态长度必须介于 0 和 128 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="电话长度必须介于 0 和 255 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=255, message="logo长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}