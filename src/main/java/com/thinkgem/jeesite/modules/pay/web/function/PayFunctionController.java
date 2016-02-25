/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.function;

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
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;
import com.thinkgem.jeesite.modules.pay.service.function.PayFunctionService;

/**
 * 功能按钮管理Controller
 * @author xxs
 * @version 2016-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/function/payFunction")
public class PayFunctionController extends BaseController {

	@Autowired
	private PayFunctionService payFunctionService;
	
	@ModelAttribute
	public PayFunction get(@RequestParam(required=false) String id) {
		PayFunction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payFunctionService.get(id);
		}
		if (entity == null){
			entity = new PayFunction();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:function:payFunction:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayFunction payFunction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayFunction> page = payFunctionService.findPage(new Page<PayFunction>(request, response), payFunction); 
		model.addAttribute("page", page);
		return "modules/pay/function/payFunctionList";
	}

	@RequiresPermissions("pay:function:payFunction:view")
	@RequestMapping(value = "form")
	public String form(PayFunction payFunction, Model model) {
		model.addAttribute("payFunction", payFunction);
		return "modules/pay/function/payFunctionForm";
	}

	@RequiresPermissions("pay:function:payFunction:edit")
	@RequestMapping(value = "save")
	public String save(PayFunction payFunction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payFunction)){
			return form(payFunction, model);
		}
		payFunctionService.save(payFunction);
		addMessage(redirectAttributes, "保存功能成功");
		return "redirect:"+Global.getAdminPath()+"/pay/function/payFunction/?repage";
	}
	
	@RequiresPermissions("pay:function:payFunction:edit")
	@RequestMapping(value = "delete")
	public String delete(PayFunction payFunction, RedirectAttributes redirectAttributes) {
		payFunctionService.delete(payFunction);
		addMessage(redirectAttributes, "删除功能成功");
		return "redirect:"+Global.getAdminPath()+"/pay/function/payFunction/?repage";
	}

}