package com.hxb.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConvertor implements Converter<String, Date> {
	private SimpleDateFormat sdf;
	
	public StringToDateConvertor(String format) {
		sdf = new SimpleDateFormat(format);
	}

	@Override
	public Date convert(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
