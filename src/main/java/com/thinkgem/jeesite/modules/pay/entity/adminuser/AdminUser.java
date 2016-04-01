/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.entity.adminuser;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 账号管理Entity
 * @author xxs
 * @version 2016-03-31
 */
public class AdminUser extends DataEntity<AdminUser> {
	
	private static final long serialVersionUID = 1L;
	private Long pk;		// 主键
	private String pass;		// 密码
	private String flag;		// 类型
	private String status;		// 状态
	private String userId;		// 用户主键
	private Date createTime;		// 创建时间
	
	public AdminUser() {
		super();
	}

	public AdminUser(String id){
		super(id);
	}

	public Long getPk() {
		return pk;
	}
	
	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@NotNull(message="用户主键为空")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=128, message="密码长度必须介于 0 和 128 之间")
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Length(min=0, max=11, message="类型长度必须介于 0 和 11 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}