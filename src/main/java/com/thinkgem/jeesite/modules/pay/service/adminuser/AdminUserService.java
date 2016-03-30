/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.adminuser;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.adminuser.AdminUser;
import com.thinkgem.jeesite.modules.pay.dao.adminuser.AdminUserDao;

/**
 * 账号管理Service
 * @author xxs
 * @version 2016-03-31
 */
@Service
@Transactional(readOnly = true)
public class AdminUserService extends CrudService<AdminUserDao, AdminUser> {

	public AdminUser get(String id) {
		return super.get(id);
	}
	
	public List<AdminUser> findList(AdminUser adminUser) {
		return super.findList(adminUser);
	}
	
	public Page<AdminUser> findPage(Page<AdminUser> page, AdminUser adminUser) {
		return super.findPage(page, adminUser);
	}
	
	@Transactional(readOnly = false)
	public void save(AdminUser adminUser) {
		super.save(adminUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(AdminUser adminUser) {
		super.delete(adminUser);
	}
	
}