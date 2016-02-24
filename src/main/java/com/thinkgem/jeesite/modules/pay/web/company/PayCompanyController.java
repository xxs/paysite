/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.company;

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
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.company.PayCompany;
import com.thinkgem.jeesite.modules.pay.service.company.PayCompanyService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	
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
	
	/**
	 * 商户分配页面
	 * @param payCompany
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "assign")
	public String assign(PayCompany payCompany, Model model) {
		//List<User> userList = systemService.findUser(new User(new PayCompany(payCompany.getId())));
		List<User> userList = payCompanyService.findUserByPayCompanyId(payCompany);
		model.addAttribute("userList", userList);
		return "modules/pay/company/payCompanyAssign";
	}
	
	/**
	 * 商户分配 -- 打开商户分配对话框
	 * @param payCompany
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "usertopayCompany")
	public String selectUserToPayCompany(PayCompany payCompany, Model model) {
//		List<User> userList = systemService.findUser(new User(new PayCompany(payCompany.getId())));
		List<User> userList = payCompanyService.findUserByPayCompanyId(payCompany);
		model.addAttribute("payCompany", payCompany);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeService.findAll());
		return "modules/pay/company/selectUserToPayCompany";
	}
	
	/**
	 * 商户分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("pay:company:payCompany:edit")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);			
		}
		return mapList;
	}
	
	/**
	 * 商户分配 -- 从商户中移除用户
	 * @param userId
	 * @param payCompanyId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "outpayCompany")
	public String outpayCompany(String userId, String payCompanyId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/company/payCompany/assign?id="+payCompanyId;
		}
		PayCompany payCompany = systemService.getPayCompany(payCompanyId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从商户【" + payCompany.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			List<User> userList = payCompanyService.findUserByPayCompanyId(payCompany);
			if (userList.size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + payCompany.getName() + "】中移除失败！这已经是该用户的唯一商户，不能移除。");
			}else{
				Boolean flag = systemService.outUserInPayCompany(payCompany, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + payCompany.getName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + payCompany.getName() + "】中移除成功！");
				}
			}		
		}
		return "redirect:" + adminPath + "/pay/company/payCompany/assign?id="+payCompany.getId();
	}
	
	/**
	 * 商户分配
	 * @param payCompany
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:company:payCompany:edit")
	@RequestMapping(value = "assignpayCompany")
	public String assignPayCompany(PayCompany payCompany, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/company/payCompany/assign?id="+payCompany.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToPayCompany(payCompany, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到商户【" + payCompany.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/pay/company/payCompany/assign?id="+payCompany.getId();
	}

}