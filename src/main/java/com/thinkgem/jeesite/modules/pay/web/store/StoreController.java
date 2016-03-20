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
import com.thinkgem.jeesite.modules.pay.entity.store.Store;
import com.thinkgem.jeesite.modules.pay.service.store.StoreService;

/**
 * 门店管理Controller
 * @author xde
 * @version 2016-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/store/store")
public class StoreController extends BaseController {

	@Autowired
	private StoreService storeService;
	
	@ModelAttribute
	public Store get(@RequestParam(required=false) String id) {
		Store entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storeService.get(id);
		}
		if (entity == null){
			entity = new Store();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:store:store:view")
	@RequestMapping(value = {"list", ""})
	public String list(Store store, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Store> page = storeService.findPage(new Page<Store>(request, response), store); 
		model.addAttribute("page", page);
		return "modules/pay/store/storeList";
	}

	@RequiresPermissions("pay:store:store:view")
	@RequestMapping(value = "form")
	public String form(Store store, Model model) {
		model.addAttribute("store", store);
		return "modules/pay/store/storeForm";
	}

	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "save")
	public String save(Store store, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, store)){
			return form(store, model);
		}
		storeService.save(store);
		addMessage(redirectAttributes, "保存门店成功");
		return "redirect:"+Global.getAdminPath()+"/pay/store/store/?repage";
	}
	
	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "delete")
	public String delete(Store store, RedirectAttributes redirectAttributes) {
		storeService.delete(store);
		addMessage(redirectAttributes, "删除门店成功");
		return "redirect:"+Global.getAdminPath()+"/pay/store/store/?repage";
	}

}