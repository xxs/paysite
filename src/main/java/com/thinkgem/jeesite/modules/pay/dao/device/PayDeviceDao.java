/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.device;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.device.PayDevice;

/**
 * 设备管理DAO接口
 * @author xxs
 * @version 2016-02-23
 */
@MyBatisDao
public interface PayDeviceDao extends CrudDao<PayDevice> {
	
}