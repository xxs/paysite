/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.txdevice;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.txdevice.TxDevice;

/**
 * 交易流水DAO接口
 * @author xde
 * @version 2016-03-21
 */
@MyBatisDao
public interface TxDeviceDao extends CrudDao<TxDevice> {
	
}