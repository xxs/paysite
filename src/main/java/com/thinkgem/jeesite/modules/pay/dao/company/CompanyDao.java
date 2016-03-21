/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.company;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 商户管理DAO接口
 * @author xde
 * @version 2016-03-21
 */
@MyBatisDao
public interface CompanyDao extends CrudDao<Company> {
	
	public List<User> findUserByCompanyId(Company company);
	
	public int deleteUserCompany(String user_id,Long company_id);
	
}