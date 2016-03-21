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
import com.thinkgem.jeesite.modules.pay.entity.function.Function;
import com.thinkgem.jeesite.modules.pay.service.function.FunctionService;

/**
 * 功能管理Controller
 * @author xde
 * @version 2016-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/function/function")
public class FunctionController extends BaseController {

	@Autowired
	private FunctionService functionService;
	
	@ModelAttribute
	public Function get(@RequestParam(required=false) Long pk) {
		Function entity = null;
		if (pk!=null){
			entity = functionService.get(pk);
		}
		if (entity == null){
			entity = new Function();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:function:function:view")
	@RequestMapping(value = {"list", ""})
	public String list(Function function, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Function> page = functionService.findPage(new Page<Function>(request, response), function); 
		model.addAttribute("page", page);
		return "modules/pay/function/functionList";
	}

	@RequiresPermissions("pay:function:function:view")
	@RequestMapping(value = "form")
	public String form(Function function, Model model) {
		model.addAttribute("function", function);
		return "modules/pay/function/functionForm";
	}

	@RequiresPermissions("pay:function:function:edit")
	@RequestMapping(value = "save")
	public String save(Function function, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, function)){
			return form(function, model);
		}
		functionService.save(function);
		addMessage(redirectAttributes, "保存功能成功");
		return "redirect:"+Global.getAdminPath()+"/pay/function/function/?repage";
	}
	
	@RequiresPermissions("pay:function:function:edit")
	@RequestMapping(value = "delete")
	public String delete(Function function, RedirectAttributes redirectAttributes) {
		functionService.delete(function);
		addMessage(redirectAttributes, "删除功能成功");
		return "redirect:"+Global.getAdminPath()+"/pay/function/function/?repage";
	}

}