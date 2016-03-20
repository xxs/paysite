/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.device;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备管理Entity
 * @author xde
 * @version 2016-03-21
 */
public class Device extends DataEntity<Device> {
	
	private static final long serialVersionUID = 1L;
	private Long pk;		// 主键
	private Long store;		// 门店外键
	private String deviceId;		// 设备标志
	private String name;		// 名称
	private String status;		// 状态
	private String remark;		// 备注
	private Date createTime;		// 创建时间
	
	public Device() {
		super();
	}

	public Device(String id){
		super(id);
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@NotNull(message="门店外键不能为空")
	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}
	
	@Length(min=0, max=64, message="设备标志长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=128, message="名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}