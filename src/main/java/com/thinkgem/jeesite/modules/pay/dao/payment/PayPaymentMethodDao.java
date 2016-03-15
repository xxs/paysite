/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.dao.payment;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pay.entity.payment.PayPaymentMethod;

/**
 * 支付方式管理DAO接口
 * @author xxs
 * @version 2016-03-14
 */
@MyBatisDao
public interface PayPaymentMethodDao extends CrudDao<PayPaymentMethod> {
	
}