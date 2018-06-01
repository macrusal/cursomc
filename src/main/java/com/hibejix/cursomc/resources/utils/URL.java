/**
 * 
 */
package com.hibejix.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author msalvador
 *
 */
public class URL {

	public static String decodeParam(String str) {
		
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeList(String str) {
		String[] vetor = str.split(",");
		List<Integer> retorno = new ArrayList<>();
		for (int i = 0; i < vetor.length; i++) {
			retorno.add(Integer.parseInt(vetor[i]));
		}
		return retorno;
	}
}
