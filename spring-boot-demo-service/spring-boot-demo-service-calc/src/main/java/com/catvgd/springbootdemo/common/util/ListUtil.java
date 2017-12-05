package com.catvgd.springbootdemo.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;

/**
 * List工具
 * 
 * @author zhuyong
 * @date 2016年11月29日
 */
public class ListUtil extends ListUtils {

	public static boolean isEmpty(List<?> list) {
		return ((list == null) || (list.isEmpty()));
	}

	public static boolean isNotEmpty(List<?> list) {
		return (!(isEmpty(list)));
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> asList(T... t) {
		if (t != null) {
			return Arrays.asList(t);
		}
		return new ArrayList<T>();
	}
}
