/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.payment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.payment.PayPaymentMethod;
import com.thinkgem.jeesite.modules.pay.dao.payment.PayPaymentMethodDao;

/**
 * 支付方式管理Service
 * @author xxs
 * @version 2016-03-14
 */
@Service
@Transactional(readOnly = true)
public class PayPaymentMethodService extends CrudService<PayPaymentMethodDao, PayPaymentMethod> {

	public PayPaymentMethod get(String id) {
		return super.get(id);
	}
	
	public List<PayPaymentMethod> findList(PayPaymentMethod payPaymentMethod) {
		return super.findList(payPaymentMethod);
	}
	
	public Page<PayPaymentMethod> findPage(Page<PayPaymentMethod> page, PayPaymentMethod payPaymentMethod) {
		return super.findPage(page, payPaymentMethod);
	}
	
	@Transactional(readOnly = false)
	public void save(PayPaymentMethod payPaymentMethod) {
		super.save(payPaymentMethod);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayPaymentMethod payPaymentMethod) {
		super.delete(payPaymentMethod);
	}
	
}