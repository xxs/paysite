/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.service.function;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.entity.function.Function;
import com.thinkgem.jeesite.modules.pay.dao.function.FunctionDao;

/**
 * 功能管理Service
 * @author xde
 * @version 2016-03-21
 */
@Service
@Transactional(readOnly = true)
public class FunctionService extends CrudService<FunctionDao, Function> {

	public Function get(String id) {
		return super.get(id);
	}
	
	public List<Function> findList(Function function) {
		return super.findList(function);
	}
	
	public Page<Function> findPage(Page<Function> page, Function function) {
		return super.findPage(page, function);
	}
	
	@Transactional(readOnly = false)
	public void save(Function function) {
		super.save(function);
	}
	
	@Transactional(readOnly = false)
	public void delete(Function function) {
		super.delete(function);
	}
	
}