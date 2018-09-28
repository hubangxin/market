package com.wz.cashloan.core.common.context;

import java.util.Map;

import tool.util.NumberUtil;
import tool.util.StringUtil;


public class Global {

	public static Map<String, Object> configMap;

	public static Map<String, Object> msg_template_Map;
	
	public static Integer payImageIndex  = 0;

	public static int getInt(String key){
		return NumberUtil.getInt(StringUtil.isNull(configMap.get(key)));
	}

	public static double getDouble(String key){
		return NumberUtil.getDouble(StringUtil.isNull(configMap.get(key)));
	}

	public static String getValue(String key) {
		return StringUtil.isNull(configMap.get(key));
	}

	public static Object getObject(String key){
		return configMap.get(key);
	}

	public static String getMsgTempLate(String key) {
		return StringUtil.isNull(msg_template_Map.get(key));
	}

}
