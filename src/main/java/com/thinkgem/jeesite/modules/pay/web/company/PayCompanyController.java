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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pay.entity.company.PayCompany;
import com.thinkgem.jeesite.modules.pay.service.company.PayCompanyService;

/**
 * 商户管理功能Controller
 * @author xxs
 * @version 2016-02-22
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/company/payCompany")
public class PayCompanyController extends BaseController {

	@Autowired
	private PayCompanyService payCompanyService;
	
	@ModelAttribute
	public PayCompany get(@RequestParam(required=false) String id) {
		PayCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payCompanyService.get(id);
		}
		if (entity == null){
			entity = new PayCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:company:payCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayCompany payCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayCompany> page = payCompanyService.findPage(new Page<PayCompany>(request, response), payCompany); 
		model.addAttribute("page", page);
		return "modules/pay/company/payCompanyList";
	}

	@RequiresPermissions("pay:company:payCompany:view")
	@RequestMapping(value = "form")
	public String form(PayCompany payCompany, Model model) {
		model.addAttribute("payCompany", payCompany);
		return "modules/pay/company/payCompanyForm";
	}

	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "save")
	public String save(PayCompany payCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payCompany)){
			return form(payCompany, model);
		}
		payCompanyService.save(payCompany);
		addMessage(redirectAttributes, "保存商户成功");
		return "redirect:"+Global.getAdminPath()+"/pay/company/payCompany/?repage";
	}
	
	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(PayCompany payCompany, RedirectAttributes redirectAttributes) {
		payCompanyService.delete(payCompany);
		addMessage(redirectAttributes, "删除商户成功");
		return "redirect:"+Global.getAdminPath()+"/pay/company/payCompany/?repage";
	}

}