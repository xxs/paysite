/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.company;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.company.PayCompany;

/**
 * 商户管理功能DAO接口
 * @author xxs
 * @version 2016-02-22
 */
@MyBatisDao
public interface PayCompanyDao extends CrudDao<PayCompany> {
	
}