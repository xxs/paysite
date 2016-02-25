/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.function;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;
import com.thinkgem.jeesite.modules.pay.dao.function.PayFunctionDao;

/**
 * 功能按钮管理Service
 * @author xxs
 * @version 2016-02-25
 */
@Service
@Transactional(readOnly = true)
public class PayFunctionService extends CrudService<PayFunctionDao, PayFunction> {

	public PayFunction get(String id) {
		return super.get(id);
	}
	
	public List<PayFunction> findList(PayFunction payFunction) {
		return super.findList(payFunction);
	}
	
	public Page<PayFunction> findPage(Page<PayFunction> page, PayFunction payFunction) {
		return super.findPage(page, payFunction);
	}
	
	@Transactional(readOnly = false)
	public void save(PayFunction payFunction) {
		super.save(payFunction);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayFunction payFunction) {
		super.delete(payFunction);
	}
	
}