/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.store;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;

/**
 * 门店管理DAO接口
 * @author xxs
 * @version 2016-02-23
 */
@MyBatisDao
public interface PayStoreDao extends CrudDao<PayStore> {
	
	public List<PayStore> findAllList();
	
	public List<PayFunction> findPayFuncationByPayStoreId(PayStore payStore);
	
	public int assignPayFunctionToPayStore(String payStore,String payFunction);
}