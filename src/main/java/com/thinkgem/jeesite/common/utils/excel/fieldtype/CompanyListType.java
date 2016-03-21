/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 字段类型转换
 * @author xxs
 */
public class CompanyListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<Company> payCompanieList = Lists.newArrayList();
		List<Company> allPayCompanieList = systemService.findAllCompany();
		for (String s : StringUtils.split(val, ",")){
			for (Company e : allPayCompanieList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					payCompanieList.add(e);
				}
			}
		}
		return payCompanieList.size()>0?payCompanieList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Company> payCompanieList = (List<Company>)val;
			return Collections3.extractToString(payCompanieList, "name", ", ");
		}
		return "";
	}
	
}
