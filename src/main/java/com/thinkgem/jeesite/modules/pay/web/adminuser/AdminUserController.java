/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.adminuser;

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
import com.thinkgem.jeesite.modules.pay.entity.adminuser.AdminUser;
import com.thinkgem.jeesite.modules.pay.service.adminuser.AdminUserService;

/**
 * 账号管理Controller
 * @author xxs
 * @version 2016-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/adminuser/adminUser")
public class AdminUserController extends BaseController {

	@Autowired
	private AdminUserService adminUserService;
	
	@ModelAttribute
	public AdminUser get(@RequestParam(required=false) String id) {
		AdminUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = adminUserService.get(id);
		}
		if (entity == null){
			entity = new AdminUser();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:adminuser:adminUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(AdminUser adminUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AdminUser> page = adminUserService.findPage(new Page<AdminUser>(request, response), adminUser); 
		model.addAttribute("page", page);
		return "modules/pay/adminuser/adminUserList";
	}

	@RequiresPermissions("pay:adminuser:adminUser:view")
	@RequestMapping(value = "form")
	public String form(AdminUser adminUser, Model model) {
		model.addAttribute("adminUser", adminUser);
		return "modules/pay/adminuser/adminUserForm";
	}

	@RequiresPermissions("pay:adminuser:adminUser:edit")
	@RequestMapping(value = "save")
	public String save(AdminUser adminUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, adminUser)){
			return form(adminUser, model);
		}
		adminUserService.save(adminUser);
		addMessage(redirectAttributes, "保存账号成功");
		return "redirect:"+Global.getAdminPath()+"/pay/adminuser/adminUser/?repage";
	}
	
	@RequiresPermissions("pay:adminuser:adminUser:edit")
	@RequestMapping(value = "delete")
	public String delete(AdminUser adminUser, RedirectAttributes redirectAttributes) {
		adminUserService.delete(adminUser);
		addMessage(redirectAttributes, "删除账号成功");
		return "redirect:"+Global.getAdminPath()+"/pay/adminuser/adminUser/?repage";
	}

}