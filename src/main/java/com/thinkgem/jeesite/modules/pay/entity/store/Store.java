/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.store;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 门店管理Entity
 * @author xde
 * @version 2016-03-21
 */
public class Store extends DataEntity<Store> {
	
	private static final long serialVersionUID = 1L;
	private Long pk;		// 主键
	private Long company;		// 商户外键
	private String name;		// 名称
	private String storeId;		// 门店标志
	private String status;		// 状态
	private String tel;		// 电话
	private String addr;		// 地址
	private String memo;		// 备注
	private Date createTime;		// 创建时间
	
	public Store() {
		super();
	}

	public Store(String id){
		super(id);
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@NotNull(message="商户外键不能为空")
	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}
	
	@Length(min=0, max=128, message="名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="门店标志长度必须介于 0 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=32, message="电话长度必须介于 0 和 32 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=256, message="地址长度必须介于 0 和 256 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}