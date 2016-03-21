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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.company.Company;
import com.thinkgem.jeesite.modules.pay.service.company.CompanyService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	
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
	
	/**
	 * 商户分配页面
	 * @param company
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "assign")
	public String assign(Company company, Model model) {
		//List<User> userList = systemService.findUser(new User(new Company(company.getId())));
		List<User> userList = companyService.findUserByCompanyId(company);
		model.addAttribute("userList", userList);
		return "modules/pay/company/companyAssign";
	}
	
	/**
	 * 商户分配 -- 打开商户分配对话框
	 * @param company
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "usertocompany")
	public String selectUserToCompany(Company company, Model model) {
//		List<User> userList = systemService.findUser(new User(new Company(company.getId())));
		List<User> userList = companyService.findUserByCompanyId(company);
		model.addAttribute("company", company);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeService.findAll());
		return "modules/pay/company/selectUserToCompany";
	}
	
	/**
	 * 商户分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("pay:company:company:edit")
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
	 * @param companyId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "outcompany")
	public String outcompany(String userId, Long companyId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/company/company/assign?pk="+companyId;
		}
		Company company = systemService.getCompany(companyId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从商户【" + company.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			List<User> userList = companyService.findUserByCompanyId(company);
			if (userList.size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + company.getName() + "】中移除失败！这已经是该用户的唯一商户，不能移除。");
			}else{
				Boolean flag = systemService.outUserInCompany(company, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + company.getName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从商户【" + company.getName() + "】中移除成功！");
				}
			}		
		}
		return "redirect:" + adminPath + "/pay/company/company/assign?pk="+company.getPk();
	}
	
	/**
	 * 商户分配
	 * @param company
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pay:company:company:edit")
	@RequestMapping(value = "assigncompany")
	public String assignCompany(Company company, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/company/company/assign?id="+company.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToCompany(company, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到商户【" + company.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/pay/company/company/assign?pk="+company.getPk();
	}

}