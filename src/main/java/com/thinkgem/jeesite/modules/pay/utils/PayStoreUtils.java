package com.thinkgem.jeesite.modules.pay.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pay.dao.store.PayStoreDao;
import com.thinkgem.jeesite.modules.pay.entity.store.PayStore;

/**
 * store工具类
 * @author XXS
 * @version 2013-12-05
 */
public class PayStoreUtils {

	private static PayStoreDao payStoreDao = SpringContextHolder.getBean(PayStoreDao.class);

	public static final String STORE_CACHE = "storeCache";
	public static final String STORE_CACHE_ID_ = "id_";
	public static final String STORE_CACHE_LOGIN_NAME_ = "ln";
	public static final String STORE_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	/**
	 */
	public static PayStore get(String id){
		PayStore payStore = (PayStore)CacheUtils.get(STORE_CACHE, STORE_CACHE_ID_ + id);
		if (payStore ==  null){
			payStore = payStoreDao.get(id);
			if (payStore == null){
				return null;
			}
			CacheUtils.put(STORE_CACHE, STORE_CACHE_ID_ + payStore.getId(), payStore);
			CacheUtils.put(STORE_CACHE, STORE_CACHE_LOGIN_NAME_ + payStore.getName(), payStore);
		}
		return payStore;
	}
	
}
