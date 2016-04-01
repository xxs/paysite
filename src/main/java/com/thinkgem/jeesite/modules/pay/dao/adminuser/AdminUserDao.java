/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.adminuser;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.adminuser.AdminUser;

/**
 * 账号管理DAO接口
 * @author xxs
 * @version 2016-03-31
 */
@MyBatisDao
public interface AdminUserDao extends CrudDao<AdminUser> {
	public List<AdminUser> findAdminUserByUserId(String userId);
}