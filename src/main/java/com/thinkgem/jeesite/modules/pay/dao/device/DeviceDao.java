/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.device;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.device.Device;

/**
 * 设备管理DAO接口
 * @author xde
 * @version 2016-03-21
 */
@MyBatisDao
public interface DeviceDao extends CrudDao<Device> {
	
}