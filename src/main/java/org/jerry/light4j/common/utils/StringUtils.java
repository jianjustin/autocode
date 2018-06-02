package org.jerry.light4j.common.utils;

public class StringUtils {

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";
	
	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	private static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/** 中文超大字符集 */
	public static final String GB2312 = "GB2312";

	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(Object obj){
		return (null == obj || obj.toString().length() <= 0 || obj.toString().trim().equals(""));
	}
	
	/**
	 * 判断对象是否不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj){
		return !isBlank(obj);
	}
	
	/**
     * 判断字符串是否内容相同，不区分大小写
     * @param s1  第1个输入字符串
     * @param s2  第2个输入字符串
     * @return 布尔值=true：两个字符串相等
     *                =false:两个字符串不相等
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }
    
    /**
     * 把字符串的第一个字符变为大写
     * @param s 输入字符串
     * @return 返回第一个字符是大写的字符串
     */
    public static String upperFirst(String s) {
        String str = null;
        if (null != s) {
            if (s.length() == 1) {
                str = s.toUpperCase();
            } else {
                str = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
        return str;
    }
    
    /**
     * 把字符串的第一个字符变为小写
     * @param s 输入的字符串
     * @return 返回的第一个字符是小写的字符串
     */
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
    
  /**
	* 指定字符串出现的次数.<br>
    *@param srcStr：查找的字符串
    *@param countStr：指定要查找的字符串
    *@return
    */
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
	
    /**
	 * 判断字符串的编码
	 * @param str
	 * @return
	 */
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
	
	/**
	 * 所有参数为空的时候返回true
	 * @param args
	 * @return true false
	 */
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
	
	/**
     * 在字符串中，用新的字符串替换指定的字符串
     * @param src 需要替换的字符对象
     * @param strOld 被替换的字符串
     * @param strNew  用于替换的字符串
     * @return 已经被替换的字符串
     */
    public static String replace(String src, String strOld, String strNew) {
        if (isBlankOne(src,strOld))return src;
        if(null == strNew)strNew = "";
        return src.replaceAll(strOld, strNew);
    }
	
	/**
	 * 获取一个字符在一个字符串里出现的次数
	 * @param tagetStr
	 * @param str
	 * @return 
	 */
	public static int indexOfAll(String tagetStr,String str){
		int i=0;
		if(null!=tagetStr){
			i=tagetStr.length()-tagetStr.replace(str, "").length();
		}
		return i;
	}
	
	/**
	 * 只要有一个参数为空就返回true
	 * @param args
	 * @return true false
	 */
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
	
	
	/**
	 * 判断字符串是否为指定格式
	 * @param str
	 * @param encode
	 * @return
	 */
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
    
	/**
	 * 将表字段名称转换为属性名称(用于代码生成器)
	 * @param regex
	 * @param oldString
	 * @return
	 */
	public static String toModelName(String regex,String oldString){
		if(isBlank(oldString))
			return "";
		String[] oldStringArr = oldString.split(regex);
		String newString = "";
		for (int i=0 ; i<oldStringArr.length ; i++) {
			if(isBlank(oldStringArr[i]))
				continue;
			if(i == 0)
				newString = oldStringArr[i].toLowerCase();
			else
				newString += upperFirst(oldStringArr[i]);
		}
		return newString;
	}
	
	public static void main(String[] args) {
		System.out.println(toModelName("_", "basic_member_code"));
	}
}
