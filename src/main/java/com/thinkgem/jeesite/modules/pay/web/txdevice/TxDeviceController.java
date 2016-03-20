/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pay.web.txdevice;

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
import com.thinkgem.jeesite.modules.pay.entity.txdevice.TxDevice;
import com.thinkgem.jeesite.modules.pay.service.txdevice.TxDeviceService;

/**
 * 交易流水Controller
 * @author xde
 * @version 2016-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/txdevice/txDevice")
public class TxDeviceController extends BaseController {

	@Autowired
	private TxDeviceService txDeviceService;
	
	@ModelAttribute
	public TxDevice get(@RequestParam(required=false) String id) {
		TxDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = txDeviceService.get(id);
		}
		if (entity == null){
			entity = new TxDevice();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:txdevice:txDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(TxDevice txDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TxDevice> page = txDeviceService.findPage(new Page<TxDevice>(request, response), txDevice); 
		model.addAttribute("page", page);
		return "modules/pay/txdevice/txDeviceList";
	}

	@RequiresPermissions("pay:txdevice:txDevice:view")
	@RequestMapping(value = "form")
	public String form(TxDevice txDevice, Model model) {
		model.addAttribute("txDevice", txDevice);
		return "modules/pay/txdevice/txDeviceForm";
	}

	@RequiresPermissions("pay:txdevice:txDevice:edit")
	@RequestMapping(value = "save")
	public String save(TxDevice txDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, txDevice)){
			return form(txDevice, model);
		}
		txDeviceService.save(txDevice);
		addMessage(redirectAttributes, "保存交易流水成功");
		return "redirect:"+Global.getAdminPath()+"/pay/txdevice/txDevice/?repage";
	}
	
	@RequiresPermissions("pay:txdevice:txDevice:edit")
	@RequestMapping(value = "delete")
	public String delete(TxDevice txDevice, RedirectAttributes redirectAttributes) {
		txDeviceService.delete(txDevice);
		addMessage(redirectAttributes, "删除交易流水成功");
		return "redirect:"+Global.getAdminPath()+"/pay/txdevice/txDevice/?repage";
	}

}