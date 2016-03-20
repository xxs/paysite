/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.store;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.store.Store;
import com.thinkgem.jeesite.modules.pay.dao.store.StoreDao;

/**
 * 门店管理Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class StoreService extends CrudService<StoreDao, Store> {

	public Store get(String id) {
		return super.get(id);
	}
	
	public List<Store> findList(Store store) {
		return super.findList(store);
	}
	
	public Page<Store> findPage(Page<Store> page, Store store) {
		return super.findPage(page, store);
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