/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.store;

import java.util.List;

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
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.pay.entity.function.Function;
import com.thinkgem.jeesite.modules.pay.entity.store.Store;
import com.thinkgem.jeesite.modules.pay.service.company.CompanyService;
import com.thinkgem.jeesite.modules.pay.service.function.FunctionService;
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
	@Autowired
	private FunctionService functionService;
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute
	public Store get(@RequestParam(required=false) Long pk) {
		Store entity = null;
		if (pk!=null){
			entity = storeService.get(pk);
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
		List<Company> companyList = companyService.findAllList(new Company());
		model.addAttribute("companyList", companyList);
		return "modules/pay/store/storeForm";
	}

	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "save")
	public String save(Store store, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, store)){
			return form(store, model);
		}
		if(store.getPk()!=null){
			store.setIsNewRecord(false);
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
	
	/**
	 * 功能按钮分配页面
	 * @param payStore
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "assign")
	public String assign(Store store, Model model) {
		List<Function> functions = storeService.findFuncationByStoreId(store);
		model.addAttribute("functions", functions);
		return "modules/pay/store/storeAssign";
	}
	
	/**
	 * 功能按钮分配 -- 打开功能按钮分配对话框
	 * @param payStore
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "selectFunctionToStore")
	public String selectFunctionToStore(Store store, Model model) {
		List<Function> functions = storeService.findFuncationByStoreId(store);
		List<Function> allFunctions = storeService.findFuncationListByStoreId(store);
		model.addAttribute("store", store);
		model.addAttribute("functions", functions);
		model.addAttribute("allFunctions", allFunctions);
		model.addAttribute("selectIds", Collections3.extractToString(functions, "name", ","));
		return "modules/pay/store/selectFunctionToStore";
	}
	
	/**
	 * 功能按钮分配 -- 从功能按钮中移除用户
	 * @param userId
	 * @param payStoreId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "outStore")
	public String outStore(String functionId, String storeId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/store/store/assign?id="+storeId;
		}
		storeService.outpayStore(storeId, functionId);
		return "redirect:" + adminPath + "/pay/store/store/assign?pk="+storeId;
	}
	
	/**
	 * 功能按钮分配
	 * @param payStore
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:store:store:edit")
	@RequestMapping(value = "assignStore")
	public String assignStore(Store store, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/store/store/assign?pk="+store.getPk();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			Function function = functionService.get(idsArr[i]);
			function = storeService.assignFunctionToStore(store.getPk(), function);
			if (null != function) {
				msg.append("<br/>新增按钮功能【" + function.getName() + "】到门店【" + store.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个功能按钮"+msg);
		return "redirect:" + adminPath + "/pay/store/store/assign?pk="+store.getPk();
	}

}