/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.device;

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
import com.thinkgem.jeesite.modules.pay.entity.device.Device;
import com.thinkgem.jeesite.modules.pay.service.device.DeviceService;

/**
 * 设备管理Controller
 * @author xde
 * @version 2016-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/device/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;
	
	@ModelAttribute
	public Device get(@RequestParam(required=false) Long pk) {
		Device entity = null;
		if (pk!=null){
			entity = deviceService.get(pk);
		}
		if (entity == null){
			entity = new Device();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:device:device:view")
	@RequestMapping(value = {"list", ""})
	public String list(Device device, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Device> page = deviceService.findPage(new Page<Device>(request, response), device); 
		model.addAttribute("page", page);
		return "modules/pay/device/deviceList";
	}

	@RequiresPermissions("pay:device:device:view")
	@RequestMapping(value = "form")
	public String form(Device device, Model model) {
		model.addAttribute("device", device);
		return "modules/pay/device/deviceForm";
	}

	@RequiresPermissions("pay:device:device:edit")
	@RequestMapping(value = "save")
	public String save(Device device, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, device)){
			return form(device, model);
		}
		deviceService.save(device);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/pay/device/device/?repage";
	}
	
	@RequiresPermissions("pay:device:device:edit")
	@RequestMapping(value = "delete")
	public String delete(Device device, RedirectAttributes redirectAttributes) {
		deviceService.delete(device);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/pay/device/device/?repage";
	}

}