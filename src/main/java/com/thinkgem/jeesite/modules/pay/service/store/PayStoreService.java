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
import com.thinkgem.jeesite.modules.pay.dao.store.PayStoreDao;
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;

/**
 * 门店管理Service
 * @author xxs
 * @version 2016-02-23
 */
@Service
@Transactional(readOnly = true)
public class PayStoreService extends CrudService<PayStoreDao, PayStore> {

	@Autowired
	private PayStoreDao payStoreDao;
	
	public PayStore get(String id) {
		PayStore payStore = super.get(id);
		return payStore;
	}
	
	public List<PayStore> findList(PayStore payStore) {
		return super.findList(payStore);
	}
	public List<PayStore> findAllList(PayStore payStore) {
		return super.findAllList(payStore);
	}
	
	public Page<PayStore> findPage(Page<PayStore> page, PayStore payStore) {
		return super.findPage(page, payStore);
	}
	public List<PayFunction> findPayFuncationByPayStoreId(PayStore payStore) {
		return payStoreDao.findPayFuncationByPayStoreId(payStore);
	}
	public List<PayFunction> findPayFuncationListByPayStoreId(PayStore payStore) {
		return payStoreDao.findPayFuncationListByPayStoreId(payStore);
	}
	@Transactional(readOnly = false)
	public PayFunction assignPayFunctionToPayStore(PayStore payStore,PayFunction payFunction) {
		if(null==payStore||null==payFunction){
			return null;
		}
		int result = payStoreDao.insertPayFunctionToPayStore(payStore.getId(),payFunction.getId());
		if(result>0){
			return payFunction;
		}else{
			return null;
		}
	}
	@Transactional(readOnly = false)
	public int outpayStore(String payStoreId,String payFunctionId) {
		return payStoreDao.outpayStore(payStoreId,payFunctionId);
	}
	
	@Transactional(readOnly = false)
	public void save(PayStore payStore) {
		super.save(payStore);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayStore payStore) {
		super.delete(payStore);
	}
	
}