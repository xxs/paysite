/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.dao.store.StoreDao;
import com.thinkgem.jeesite.modules.pay.entity.function.Function;
import com.thinkgem.jeesite.modules.pay.entity.store.Store;

/**
 * 门店管理Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class StoreService extends CrudService<StoreDao, Store> {

	@Autowired
	private StoreDao storeDao;
	
	public Store get(String id) {
		return super.get(id);
	}
	
	public List<Store> findList(Store store) {
		return super.findList(store);
	}
	
	public Page<Store> findPage(Page<Store> page, Store store) {
		return super.findPage(page, store);
	}
	public List<Function> findFuncationByStoreId(Store store) {
		return storeDao.findFuncationByStoreId(store);
	}
	public List<Function> findFuncationListByStoreId(Store store) {
		return storeDao.findFuncationListByStoreId(store);
	}
	@Transactional(readOnly = false)
	public Function assignFunctionToStore(Store store,Function function) {
		if(null==store||null==function){
			return null;
		}
		int result = storeDao.insertFunctionToStore(store.getId(),function.getId());
		if(result>0){
			return function;
		}else{
			return null;
		}
	}
	@Transactional(readOnly = false)
	public int outpayStore(String storeId,String functionId) {
		return storeDao.outStore(storeId,functionId);
	}
	
	@Transactional(readOnly = false)
	public void save(Store store) {
		super.save(store);
	}
	
	@Transactional(readOnly = false)
	public void delete(Store store) {
		super.delete(store);
	}
	
}