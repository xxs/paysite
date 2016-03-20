/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.device;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.device.Device;
import com.thinkgem.jeesite.modules.pay.dao.device.DeviceDao;

/**
 * 设备管理Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class DeviceService extends CrudService<DeviceDao, Device> {

	public Device get(String id) {
		return super.get(id);
	}
	
	public List<Device> findList(Device device) {
		return super.findList(device);
	}
	
	public Page<Device> findPage(Page<Device> page, Device device) {
		return super.findPage(page, device);
	}
	
	@Transactional(readOnly = false)
	public void save(Device device) {
		super.save(device);
	}
	
	@Transactional(readOnly = false)
	public void delete(Device device) {
		super.delete(device);
	}
	
}