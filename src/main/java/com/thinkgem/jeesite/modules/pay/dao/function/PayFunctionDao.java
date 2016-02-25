/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.function;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;

/**
 * 功能按钮管理DAO接口
 * @author xxs
 * @version 2016-02-25
 */
@MyBatisDao
public interface PayFunctionDao extends CrudDao<PayFunction> {
	
}