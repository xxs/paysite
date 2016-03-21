/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.pay.service.company.CompanyService;

/**
 * 商户管理Controller
 * @author xde
 * @version 2016-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/company/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute
	public Company get(@RequestParam(required=false) Long pk) {
		Company entity = null;
		if (pk!=null){
			entity = companyService.get(pk);
		}
		if (entity == null){
			entity = new Company();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:company:company:view")
	@RequestMapping(value = {"list", ""})
	public String list(Company company, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Company> page = companyService.findPage(new Page<Company>(request, response), company); 
		model.addAttribute("page", page);
		return "modules/pay/company/companyList";
	}

	@RequiresPermissions("pay:company:company:view")
	@RequestMapping(value = "form")
	public String form(Company company, Model model) {
		model.addAttribute("company", company);
		return "modules/pay/company/companyForm";
	}

	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "save")
	public String save(Company company, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, company)){
			return form(company, model);
		}
		if(company.getPk()!=null){
			company.setIsNewRecord(false);
		}
		companyService.save(company);
		addMessage(redirectAttributes, "保存商户成功");
		return "redirect:"+Global.getAdminPath()+"/pay/company/company/?repage";
	}
	
	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "delete")
	public String delete(Company company, RedirectAttributes redirectAttributes) {
		companyService.delete(company);
		addMessage(redirectAttributes, "删除商户成功");
		return "redirect:"+Global.getAdminPath()+"/pay/company/company/?repage";
	}

}