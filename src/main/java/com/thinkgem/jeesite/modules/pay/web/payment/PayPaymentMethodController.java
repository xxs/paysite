/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.payment;

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
import com.thinkgem.jeesite.modules.pay.entity.payment.PayPaymentMethod;
import com.thinkgem.jeesite.modules.pay.service.payment.PayPaymentMethodService;

/**
 * 支付方式管理Controller
 * @author xxs
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/payment/payPaymentMethod")
public class PayPaymentMethodController extends BaseController {

	@Autowired
	private PayPaymentMethodService payPaymentMethodService;
	
	@ModelAttribute
	public PayPaymentMethod get(@RequestParam(required=false) String id) {
		PayPaymentMethod entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payPaymentMethodService.get(id);
		}
		if (entity == null){
			entity = new PayPaymentMethod();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:payment:payPaymentMethod:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayPaymentMethod payPaymentMethod, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayPaymentMethod> page = payPaymentMethodService.findPage(new Page<PayPaymentMethod>(request, response), payPaymentMethod); 
		model.addAttribute("page", page);
		return "modules/pay/payment/payPaymentMethodList";
	}

	@RequiresPermissions("pay:payment:payPaymentMethod:view")
	@RequestMapping(value = "form")
	public String form(PayPaymentMethod payPaymentMethod, Model model) {
		model.addAttribute("payPaymentMethod", payPaymentMethod);
		return "modules/pay/payment/payPaymentMethodForm";
	}

	@RequiresPermissions("pay:payment:payPaymentMethod:edit")
	@RequestMapping(value = "save")
	public String save(PayPaymentMethod payPaymentMethod, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payPaymentMethod)){
			return form(payPaymentMethod, model);
		}
		payPaymentMethodService.save(payPaymentMethod);
		addMessage(redirectAttributes, "保存支付方式成功");
		return "redirect:"+Global.getAdminPath()+"/pay/payment/payPaymentMethod/?repage";
	}
	
	@RequiresPermissions("pay:payment:payPaymentMethod:edit")
	@RequestMapping(value = "delete")
	public String delete(PayPaymentMethod payPaymentMethod, RedirectAttributes redirectAttributes) {
		payPaymentMethodService.delete(payPaymentMethod);
		addMessage(redirectAttributes, "删除支付方式成功");
		return "redirect:"+Global.getAdminPath()+"/pay/payment/payPaymentMethod/?repage";
	}

}