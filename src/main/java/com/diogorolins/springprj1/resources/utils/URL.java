package com.diogorolins.springprj1.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.diogorolins.springprj1.domain.Category;

public class URL {

	public static String encodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static  List<Integer> decodeIntList(String str){
		try {
		return Arrays.asList(str.split(","))
				.stream()
				.map((x) -> Integer.parseInt(x)).collect(Collectors.toList());
		} catch(NumberFormatException e) {
			throw new IllegalStateException("Parâmetro inválido");
		}
	}

	public static String getEmptyCategory(List<Category> list) {
		return list.stream()
				.reduce("", (partialAgeResult, user) -> partialAgeResult + ","+  user.getId(), String::concat).substring(1);
	}

}
