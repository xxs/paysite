/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.store;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.function.PayFunction;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;
import com.thinkgem.jeesite.modules.pay.service.function.PayFunctionService;
import com.thinkgem.jeesite.modules.pay.service.store.PayStoreService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

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
	@Autowired
	private PayFunctionService payFunctionService;
	
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
	
	/**
	 * 功能按钮分配页面
	 * @param payStore
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "assign")
	public String assign(PayStore payStore, Model model) {
		List<PayFunction> payFunctions = payStoreService.findPayFuncationByPayStoreId(payStore);
		model.addAttribute("payFunctions", payFunctions);
		return "modules/pay/store/payStoreAssign";
	}
	
	/**
	 * 功能按钮分配 -- 打开功能按钮分配对话框
	 * @param payStore
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "selectFunctionToPayStore")
	public String selectFunctionToPayStore(PayStore payStore, Model model) {
		List<PayFunction> payFunctions = payStoreService.findPayFuncationByPayStoreId(payStore);
		List<PayFunction> allPayFunctions = payFunctionService.findAllList(new PayFunction());
		model.addAttribute("payStore", payStore);
		model.addAttribute("payFunctions", payFunctions);
		model.addAttribute("allPayFunctions", allPayFunctions);
		model.addAttribute("selectIds", Collections3.extractToString(payFunctions, "name", ","));
		return "modules/pay/store/selectFunctionToPayStore";
	}
	
	/**
	 * 功能按钮分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("pay:store:payStore:edit")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
//		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
//		for (User e : page.getList()) {
//			Map<String, Object> map = Maps.newHashMap();
//			map.put("id", e.getId());
//			map.put("pId", 0);
//			map.put("name", e.getName());
//			mapList.add(map);			
//		}
		return mapList;
	}
	
	/**
	 * 功能按钮分配 -- 从功能按钮中移除用户
	 * @param userId
	 * @param payStoreId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "outpayStore")
	public String outpayStore(String payFunctionId, String payStoreId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/store/payStore/assign?id="+payStoreId;
		}
		PayStore payStore = payStoreService.get(payStoreId);
		PayFunction payFunction = payFunctionService.get(payFunctionId);
//		User user = systemService.getUser(userId);
//		if (UserUtils.getUser().getId().equals(userId)) {
//			addMessage(redirectAttributes, "无法从功能按钮【" + payStore.getName() + "】中移除用户【" + user.getName() + "】自己！");
//		}else {
//			List<User> userList = payStoreService.findUserByPayStoreId(payStore);
//			if (userList.size() <= 1){
//				addMessage(redirectAttributes, "用户【" + user.getName() + "】从功能按钮【" + payStore.getName() + "】中移除失败！这已经是该用户的唯一功能按钮，不能移除。");
//			}else{
//				Boolean flag = systemService.outUserInPayStore(payStore, user);
//				if (!flag) {
//					addMessage(redirectAttributes, "用户【" + user.getName() + "】从功能按钮【" + payStore.getName() + "】中移除失败！");
//				}else {
//					addMessage(redirectAttributes, "用户【" + user.getName() + "】从功能按钮【" + payStore.getName() + "】中移除成功！");
//				}
//			}		
//		}
		return "redirect:" + adminPath + "/pay/store/payStore/assign?id="+payStore.getId();
	}
	
	/**
	 * 功能按钮分配
	 * @param payStore
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:store:payStore:edit")
	@RequestMapping(value = "assignpayStore")
	public String assignPayStore(PayStore payStore, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/store/payStore/assign?id="+payStore.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			PayFunction function = payFunctionService.get(idsArr[i]);
			 payStoreService.assignPayFunctionToPayStore(payStore, );
			if (null != user) {
				msg.append("<br/>新增按钮功能【" + user.getName() + "】到门店【" + payStore.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个功能按钮"+msg);
		return "redirect:" + adminPath + "/pay/store/payStore/assign?id="+payStore.getId();
	}

}