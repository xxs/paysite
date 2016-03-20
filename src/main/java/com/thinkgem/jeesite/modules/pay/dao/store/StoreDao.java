/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.store;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.function.Function;
import com.thinkgem.jeesite.modules.pay.entity.store.Store;

/**
 * 门店管理DAO接口
 * @author xde
 * @version 2016-03-21
 */
@MyBatisDao
public interface StoreDao extends CrudDao<Store> {
	
	public List<Store> findAllList();
	
	public List<Function> findFuncationByStoreId(Store store);
	
	public List<Function> findFuncationListByStoreId(Store store);
	
	public int outStore(String storeId,String functionId);
	
	public int insertFunctionToStore(String storeId,String functionId);
}