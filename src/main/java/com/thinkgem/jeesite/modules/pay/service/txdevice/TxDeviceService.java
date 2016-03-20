/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.txdevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.txdevice.TxDevice;
import com.thinkgem.jeesite.modules.pay.dao.txdevice.TxDeviceDao;

/**
 * 交易流水Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class TxDeviceService extends CrudService<TxDeviceDao, TxDevice> {

	public TxDevice get(String id) {
		return super.get(id);
	}
	
	public List<TxDevice> findList(TxDevice txDevice) {
		return super.findList(txDevice);
	}
	
	public Page<TxDevice> findPage(Page<TxDevice> page, TxDevice txDevice) {
		return super.findPage(page, txDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(TxDevice txDevice) {
		super.save(txDevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(TxDevice txDevice) {
		super.delete(txDevice);
	}
	
}