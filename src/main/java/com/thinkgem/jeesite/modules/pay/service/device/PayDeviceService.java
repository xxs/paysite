/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.device;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.device.PayDevice;
import com.thinkgem.jeesite.modules.pay.dao.device.PayDeviceDao;

/**
 * 设备管理Service
 * @author xxs
 * @version 2016-02-23
 */
@Service
@Transactional(readOnly = true)
public class PayDeviceService extends CrudService<PayDeviceDao, PayDevice> {

	public PayDevice get(String id) {
		return super.get(id);
	}
	
	public List<PayDevice> findList(PayDevice payDevice) {
		return super.findList(payDevice);
	}
	
	public Page<PayDevice> findPage(Page<PayDevice> page, PayDevice payDevice) {
		return super.findPage(page, payDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(PayDevice payDevice) {
		super.save(payDevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayDevice payDevice) {
		super.delete(payDevice);
	}
	
}