package org.jerry.light4j.common.utils;

public class StringUtils {

	/** 8 λ UCS ת����ʽ */
	public static final String UTF_8 = "UTF-8";
	
	/** ISO ������ĸ�� No.1��Ҳ���� ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 7λASCII�ַ���Ҳ����ISO646-US��Unicode�ַ����Ļ��������� */
	public static final String US_ASCII = "US-ASCII";

	/** 16 λ UCS ת����ʽ��Big Endian����͵�ַ��Ÿ�λ�ֽڣ��ֽ�˳�� */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 λ UCS ת����ʽ��Little-endian����ߵ�ַ��ŵ�λ�ֽڣ��ֽ�˳�� */
	private static final String UTF_16LE = "UTF-16LE";

	/** 16 λ UCS ת����ʽ���ֽ�˳���ɿ�ѡ���ֽ�˳��������ʶ */
	public static final String UTF_16 = "UTF-16";

	/** ���ĳ����ַ��� */
	public static final String GBK = "GBK";

	/** ���ĳ����ַ��� */
	public static final String GB2312 = "GB2312";

	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(Object obj){
		return (null == obj || obj.toString().length() <= 0 || obj.toString().trim().equals(""));
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj){
		return !isBlank(obj);
	}
	
	/**
     * �ж��ַ����Ƿ�������ͬ�������ִ�Сд
     * @param s1  ��1�������ַ���
     * @param s2  ��2�������ַ���
     * @return ����ֵ=true�������ַ������
     *                =false:�����ַ��������
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }
    
    /**
     * ���ַ����ĵ�һ���ַ���Ϊ��д
     * @param s �����ַ���
     * @return ���ص�һ���ַ��Ǵ�д���ַ���
     */
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
    
    /**
     * ���ַ����ĵ�һ���ַ���ΪСд
     * @param s ������ַ���
     * @return ���صĵ�һ���ַ���Сд���ַ���
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
	* ָ���ַ������ֵĴ���.<br>
    *@param srcStr�����ҵ��ַ���
    *@param countStr��ָ��Ҫ���ҵ��ַ���
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
	 * �ж��ַ����ı���
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
	 * ���в���Ϊ�յ�ʱ�򷵻�true
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
     * ���ַ����У����µ��ַ����滻ָ�����ַ���
     * @param src ��Ҫ�滻���ַ�����
     * @param strOld ���滻���ַ���
     * @param strNew  �����滻���ַ���
     * @return �Ѿ����滻���ַ���
     */
    public static String replace(String src, String strOld, String strNew) {
        if (isBlankOne(src,strOld))return src;
        if(null == strNew)strNew = "";
        return src.replaceAll(strOld, strNew);
    }
	
	/**
	 * ��ȡһ���ַ���һ���ַ�������ֵĴ���
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
	 * ֻҪ��һ������Ϊ�վͷ���true
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
	 * �ж��ַ����Ƿ�Ϊָ����ʽ
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
	 * �����ֶ�����ת��Ϊ��������(���ڴ���������)
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
