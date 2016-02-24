/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.device;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pay.entity.device.PayDevice;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;
import com.thinkgem.jeesite.modules.pay.service.device.PayDeviceService;
import com.thinkgem.jeesite.modules.pay.service.store.PayStoreService;

/**
 * 设备管理Controller
 * @author xxs
 * @version 2016-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/device/payDevice")
public class PayDeviceController extends BaseController {

	@Autowired
	private PayDeviceService payDeviceService;
	@Autowired
	private PayStoreService payStoreService;
	
	@ModelAttribute
	public PayDevice get(@RequestParam(required=false) String id) {
		PayDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payDeviceService.get(id);
		}
		if (entity == null){
			entity = new PayDevice();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:device:payDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayDevice payDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		PayStore payStore = new PayStore();
		List<PayStore> list = payStoreService.findAllList(payStore);
		model.addAttribute("list", list);
		Page<PayDevice> page = payDeviceService.findPage(new Page<PayDevice>(request, response), payDevice); 
		model.addAttribute("page", page);
		return "modules/pay/device/payDeviceList";
	}

	@RequiresPermissions("pay:device:payDevice:view")
	@RequestMapping(value = "form")
	public String form(PayDevice payDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayDevice> page = payDeviceService.findPage(new Page<PayDevice>(request, response), payDevice); 
		PayStore payStore = new PayStore();
		List<PayStore> list = payStoreService.findAllList(payStore);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("payDevice", payDevice);
		
		return "modules/pay/device/payDeviceForm";
	}

	@RequiresPermissions("pay:device:payDevice:edit")
	@RequestMapping(value = "save")
	public String save(PayDevice payDevice, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payDevice)){
			return form(payDevice,request,response, model);
		}
		payDeviceService.save(payDevice);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/pay/device/payDevice/?repage";
	}
	
	@RequiresPermissions("pay:device:payDevice:edit")
	@RequestMapping(value = "delete")
	public String delete(PayDevice payDevice, RedirectAttributes redirectAttributes) {
		payDeviceService.delete(payDevice);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/pay/device/payDevice/?repage";
	}

}