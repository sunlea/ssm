package com.tgb.tool;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ToolUtil {
	/**
	 * 将对象转为Map类型
	 * 
	 * @param obj
	 *            需要转化的对象
	 * @return
	 */
	public static Map<String, Object> getMapFromObject(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			map.put(name, getFieldValueByName(name, obj));
		}
		return map;
	}

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param fieldName
	 *            属性名
	 * @param o
	 *            属性所属的对象
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * 
	 * @param o
	 *            需要转化的对象
	 * @return List中map包含的键为：属性类型(type)，属性名(name)，属性值(value)
	 */
	private static List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

	public static Map<String, String> getMapFromJSONObject(JSONObject jsonObj) {
		Map<String, String> map = new HashMap<String, String>();
		List<String> arr = jsonObj.names();
		for (int i = 0; i < arr.size(); i++) {
			String name = arr.get(i);
			map.put(name, jsonObj.getString(name));
		}
		return map;
	}

	public static Map<String, String> getBillDetailMap(String str_billDetals) {
		Map<String, String> billDetailMap = new HashMap<String, String>();
		String billDetailArr[] = str_billDetals.split(";");
		for (String billDetail : billDetailArr) {
			String tempBillDetail[] = billDetail.split(",");
			billDetailMap.put(tempBillDetail[0], tempBillDetail[1]);
		}
		return billDetailMap;
	}

	public static String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
		String result = URLEncoder.encode(str, charset);
		return result;
	}

	/**
	 * 电商Sign签名生成
	 * 
	 * @param content
	 *            内容
	 * @param keyValue
	 *            Appkey
	 * @param charset
	 *            编码方式
	 * @throws UnsupportedEncodingException
	 *             ,Exception
	 * @return DataSign签名
	 */
	public static String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
		if (keyValue != null) {
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            内容
	 * @param charset
	 *            编码方式
	 * @throws Exception
	 */
	public static String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val <= 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * base64编码
	 * 
	 * @param str
	 *            内容
	 * @param charset
	 *            编码方式
	 * @throws UnsupportedEncodingException
	 */
	public static String base64(String str, String charset) throws UnsupportedEncodingException {
		String encoded = Base64.encode(str.getBytes(charset));
		return encoded;
	}

	/**
	 * 根据list中每个map的某个字段的value将list进行排序
	 * 
	 * @param list
	 *            需要排序的list
	 * @param key
	 *            排序比较的字段
	 * @param sort_type
	 *            排序类型 1：升序，2：降序
	 * @return
	 */
	public static List<Map<String, Object>> SortListByKey(List<Map<String, Object>> list, String key, int sort_type) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> obj : list) {
			if (result.size() == 0) {
				result.add(obj);
			} else {
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> result_obj = result.get(i);
					boolean hasAdd = false;
					if (sort_type == 1) {
						if (String.valueOf(obj.get(key)).compareTo(String.valueOf(result_obj.get(key))) <= 0) {
							result.add(i, obj);
							break;
						}
					} else if (sort_type == 2) {
						if (String.valueOf(obj.get(key)).compareTo(String.valueOf(result_obj.get(key))) >= 0) {
							result.add(i, obj);
							break;
						}
					}
					result.add(i + 1, obj);
					break;
				}
			}
		}
		if (result.size() == 0) {
			return list;
		} else {
			return result;
		}
	}

	/**
	 * 根据给定数返回从0开始到该数减1的计数列表
	 * 
	 * @param period
	 * @return
	 */
	public static List<String> getListByPeriod(String period) {
		int period_length = 0;
		if (StringUtils.isNumeric(period)) {
			period_length = Integer.valueOf(period);
			List<String> period_list = new ArrayList<String>(period_length);
			for (int i = 0; i < period_length; i++) {
				period_list.add(i + "");
			}
			return period_list;
		}
		return null;
	}

	/**
	 * 将original_str中${id}格式的字符串替换为param中key为id的值
	 * 
	 * @param original_str
	 * @param param
	 * @return
	 */
	public static String getReplacedStr(String original_str, Map<String, String> param) {
		Pattern pattern = Pattern.compile("\\$\\{[\\w\\.\\-_]* *\\}");
		Matcher matcher = pattern.matcher(original_str);
		while (matcher.find()) {
			String group = matcher.group(0);
			String name = group.replaceAll("\\$\\{| *}", "");
			if (param.containsKey(name) && param.get(name) != null) {
				original_str = original_str.replace(group, param.get(name));
			}
		}
		return original_str;
	}

	/**
	 * 将下标方式分页或者页码方式分页的参数转为limit方式分页的参数
	 * 
	 * @param condition
	 *            下标方式分页参数如下,其中start_index和end_index为一组，page和count为一组；并且page和count组的优先级高
	 * @param start_index
	 *            分页开始下标，从1开始，包含输入序号
	 * @param end_index
	 *            分页结束下标，从1开始，包含输入序号；页码分页方式参数如下
	 * @param page
	 *            页码，从1开始
	 * @param count
	 *            每页记录数，从1开始
	 * @return
	 * @param start
	 *            分页开始下标，从0开始，包含输入序号;
	 * @param num
	 *            该页记录数量
	 */
	public static Map<String, String> changePageInfo(Map<String, String> condition) {
		if (StringUtils.isNumeric(String.valueOf(condition.get("start_index")))) {
			condition.put("start", (Integer.valueOf(condition.get("start_index")) - 1) + "");
			if (StringUtils.isNumeric(String.valueOf(condition.get("end_index")))) {
				condition.put("num", (Integer.valueOf(String.valueOf(condition.get("end_index"))) - Integer.valueOf(String.valueOf(condition.get("start_index"))) + 1) + "");
			}
		} else {
			if (StringUtils.isNumeric(String.valueOf(condition.get("end_index")))) {
				condition.put("start", "0");
				condition.put("num", Integer.valueOf(String.valueOf(condition.get("end_index"))) + "");
			}
		}
		if (StringUtils.isNumeric(String.valueOf(condition.get("page"))) && StringUtils.isNumeric(String.valueOf(condition.get("count")))) {
			condition.put("start", (Integer.valueOf(String.valueOf(condition.get("page"))) - 1) * Integer.valueOf(String.valueOf(condition.get("count"))) + "");
			condition.put("num", String.valueOf(condition.get("count")));
		}
		return condition;
	}

}
