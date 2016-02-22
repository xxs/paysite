/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.company;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.company.PayCompany;
import com.thinkgem.jeesite.modules.pay.dao.company.PayCompanyDao;

/**
 * 商户管理功能Service
 * @author xxs
 * @version 2016-02-22
 */
@Service
@Transactional(readOnly = true)
public class PayCompanyService extends CrudService<PayCompanyDao, PayCompany> {

	public PayCompany get(String id) {
		return super.get(id);
	}
	
	public List<PayCompany> findList(PayCompany payCompany) {
		return super.findList(payCompany);
	}
	
	public Page<PayCompany> findPage(Page<PayCompany> page, PayCompany payCompany) {
		return super.findPage(page, payCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(PayCompany payCompany) {
		super.save(payCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayCompany payCompany) {
		super.delete(payCompany);
	}
	
}