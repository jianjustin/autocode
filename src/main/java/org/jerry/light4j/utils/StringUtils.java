package org.jerry.light4j.utils;

public class StringUtils {

	public static final String UTF_8 = "UTF-8";
	
	public static final String ISO_8859_1 = "ISO-8859-1";

	public static final String US_ASCII = "US-ASCII";

	public static final String UTF_16BE = "UTF-16BE";

	private static final String UTF_16LE = "UTF-16LE";

	public static final String UTF_16 = "UTF-16";

	public static final String GBK = "GBK";

	public static final String GB2312 = "GB2312";

	public static boolean isBlank(Object obj){
		return (null == obj || obj.toString().length() <= 0 || obj.toString().trim().equals(""));
	}
	
	public static boolean isNotBlank(Object obj){
		return !isBlank(obj);
	}
	
    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }
    
    public static String upperFirst(String s) {
        String str = null;
        s = s.toLowerCase();
        if (null != s) {
            if (s.length() == 1) {
                str = s.toUpperCase();
            } else {
                str = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
        return str;
    }
    
    public static String lowerFirst(String s) {
        String str = null;
        if (null != s) {
            if (s.length() == 1) {
                str = s.toLowerCase();
            } else {
                str = s.substring(0, 1).toLowerCase() + s.substring(1);
            }
        }
        return str;
    }
    
    public static int countStringNumber(String srcStr,String countStr){
        int indexCount = 0;
        int index = 0;
        int count=0;
        for(;;) {
            index = srcStr.indexOf(countStr, indexCount);
            if(index == -1){
                break;
            }
            count++;
            indexCount = (index += countStr.length());
        }
        return count;
    } 
	
	public static String getEncoding(String str) {
		String[] encodeStr={UTF_8,ISO_8859_1,GBK,GB2312,US_ASCII,UTF_16BE,UTF_16LE,UTF_16};
		String encode="";
		for (String string : encodeStr) {
			encode=checkEncoding(str, string);
			if(isNotBlank(encode)){
				return encode;
			}
		}
		return "";
	}  
	
	public static Boolean isBlankAll(Object... args){
		Boolean flag=true;
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof String){
				if(!isBlank((String) args[i])){
					flag=false;
				}
			}else{
				if(null!=args[i]){
					flag=false;
				}	
			}
		}
		return flag;
	}
	
    public static String replace(String src, String strOld, String strNew) {
        if (isBlankOne(src,strOld))return src;
        if(null == strNew)strNew = "";
        return src.replaceAll(strOld, strNew);
    }
	
	public static int indexOfAll(String tagetStr,String str){
		int i=0;
		if(null!=tagetStr){
			i=tagetStr.length()-tagetStr.replace(str, "").length();
		}
		return i;
	}
	
	public static Boolean isBlankOne(Object... args){
		Boolean flag=false;
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof String){
				if(isBlank((String) args[i])){
					flag=true;
				}
			}else{
				if(null==args[i]){
					flag=true;
				}
			}
		}
		return flag;
	}
	
	public static String checkEncoding(String str,String encode){
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		return "";
	}
    
	public static String toModelName(String regex,String oldString){
		if(isBlank(oldString))
			return "";
		String[] oldStringArr = oldString.split(regex);
		String newString = "";
		for (int i=0 ; i<oldStringArr.length ; i++) {
			if(isBlank(oldStringArr[i]))continue;
			newString += upperFirst(oldStringArr[i]);
		}
		return newString;
	}
	
	/**
	 * 获取类型
	 * @param regex
	 * @param oldString
	 * @return
	 */
	public static String toSQLTypeName(String oldString) {
		return oldString.replaceAll("\\([^)]+\\)","");
	}
	
	public static void main(String[] args) {
		System.out.println(toSQLTypeName("int(20)"));
	}
}
