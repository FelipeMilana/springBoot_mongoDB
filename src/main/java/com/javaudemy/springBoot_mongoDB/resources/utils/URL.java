package com.javaudemy.springBoot_mongoDB.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); 
		} 
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Date convertStringtoDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {
			Date d = sdf.parse(textDate);
			return new Date(d.getTime() + 24 *60 *60 *1000);
			
		} 
		catch (ParseException e) {
			return defaultValue;
		}
	}
}