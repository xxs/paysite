/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.store;

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
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;
import com.thinkgem.jeesite.modules.pay.service.store.PayStoreService;

/**
 * 门店管理Controller
 * @author xxs
 * @version 2016-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/store/payStore")
public class PayStoreController extends BaseController {

	@Autowired
	private PayStoreService payStoreService;
	
	@ModelAttribute
	public PayStore get(@RequestParam(required=false) String id) {
		PayStore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payStoreService.get(id);
		}
		if (entity == null){
			entity = new PayStore();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:store:payStore:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayStore payStore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayStore> page = payStoreService.findPage(new Page<PayStore>(request, response), payStore); 
		model.addAttribute("page", page);
		return "modules/pay/store/payStoreList";
	}

	@RequiresPermissions("pay:store:payStore:view")
	@RequestMapping(value = "form")
	public String form(PayStore payStore, Model model) {
		model.addAttribute("payStore", payStore);
		return "modules/pay/store/payStoreForm";
	}

	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "save")
	public String save(PayStore payStore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payStore)){
			return form(payStore, model);
		}
		payStoreService.save(payStore);
		addMessage(redirectAttributes, "保存门店成功");
		return "redirect:"+Global.getAdminPath()+"/pay/store/payStore/?repage";
	}
	
	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "delete")
	public String delete(PayStore payStore, RedirectAttributes redirectAttributes) {
		payStoreService.delete(payStore);
		addMessage(redirectAttributes, "删除门店成功");
		return "redirect:"+Global.getAdminPath()+"/pay/store/payStore/?repage";
	}

}