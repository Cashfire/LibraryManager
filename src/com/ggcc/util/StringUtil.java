package com.ggcc.util;
/**
 * String Util Class
 * @author Mao
 *
 */
public class StringUtil {
	
	public static boolean isEmpty(String str){
		if(str ==null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}		
	}

	public static boolean isNotEmpty(String str){
		if(str !=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}	
	}
}
