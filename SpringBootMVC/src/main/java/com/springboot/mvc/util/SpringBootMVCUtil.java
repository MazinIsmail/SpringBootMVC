package com.springboot.mvc.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpringBootMVCUtil {

	public static Map<String, Locale> initCountryCodeMapping() {
		String[] countries = Locale.getISOCountries();
		Map<String, Locale> localeMap = new HashMap<String, Locale>(countries.length);
		for (String country : countries) {
			Locale locale = new Locale("", country);
			localeMap.put(locale.getISO3Country().toUpperCase(), locale);
		}
		return localeMap;
	}

	public static boolean isAlpha(String name) {
		char[] chars = name.toCharArray();
		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
