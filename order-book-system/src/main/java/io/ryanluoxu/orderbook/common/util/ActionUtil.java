package io.ryanluoxu.orderbook.common.util;

public class ActionUtil {
	public static String getString(String actionType, String className, Long objectId, String loginUserName){
		return loginUserName + " " + actionType + " " + className.substring(className.lastIndexOf(".")+1) + " (id=" + objectId + ").";
	}

}
