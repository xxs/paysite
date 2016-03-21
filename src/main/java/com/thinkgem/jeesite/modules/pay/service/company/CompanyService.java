/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.dao.company.CompanyDao;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 商户管理Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class CompanyService extends CrudService<CompanyDao, Company> {

	@Autowired
	private CompanyDao companyDao;
	
	public Company get(Long pk) {
		return super.get(pk);
	}
	
	public List<User> findUserByCompanyId(Company company){
		return companyDao.findUserByCompanyId(company);
	}
	public int deleteUserCompany(User user,Company company){
		return companyDao.deleteUserCompany(user.getId(),company.getPk());
	}
	
	public List<Company> findList(Company company) {
		return super.findList(company);
	}
	
	public Page<Company> findPage(Page<Company> page, Company company) {
		return super.findPage(page, company);
	}
	
	@Transactional(readOnly = false)
	public void save(Company company) {
		super.save(company);
	}
	
	@Transactional(readOnly = false)
	public void delete(Company company) {
		super.delete(company);
	}
	
}