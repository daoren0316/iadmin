package cc.kokoko.server.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
/**
 * 系统通用工具类。
 * @author robin
 * 
 */
public class StdLib {

	private static MessageDigest	MESSAGEDIGEST			= null;
	
	public final static Random		RANDOM						= new Random();
	
	
	public final static Map parseCookies(Cookie[] c)
	{
		Map rslt = new HashMap(5);

		if (c != null)
			for (int i = c.length - 1; i >= 0; i--)
				rslt.put(c[i].getName(), c[i]);

		return rslt;
	}
	
	//得到系统服务名等信息
	public static String getBasePath()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	/**
	 * 获得当前web容器的绝对路径,以后如将上传路径 uploadImage目录
	 * 移至web容器路径以外,该方法直接返回空字符串即可
	 */
	public static String getBaseServletRealPath()
	{
	  return ServletActionContext.getServletContext().getRealPath("/").replace("\\", "/");
	}

	/**
	 * Convert a string according to message digest algorithms md5.
	 *
	 * @param name the orginal String need to be converted.
	 *
	 * @return the md5 value for the orginal String.
	 */
	public final static String md5(String name)
	{
		if (MESSAGEDIGEST == null)
		{
			try
			{
				MESSAGEDIGEST = MessageDigest.getInstance("MD5");
			}
			catch (NoSuchAlgorithmException e)
			{
				throw new RuntimeException(e.getMessage());
			}
		}

		byte bytes[] = name.getBytes();
		byte hash[] = null;

		synchronized (MESSAGEDIGEST)
		{
			MESSAGEDIGEST.reset();
			MESSAGEDIGEST.update(bytes);

			hash = MESSAGEDIGEST.digest();
		}

		StringBuffer hexString = new StringBuffer(32);

		for (int i = 0; i < hash.length; i++)
		{
			if ((0xFF & hash[i]) < 0x10)
				hexString.append("0");

			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}

		return hexString.toString();
	}
	
	
	/**
	 * Replace all substring with another string.
	 *
	 * @param s String you need to analyse.
	 * @param oldS a String you need to replace in the orginal String,
	 *      please make sure the value of oldS is really exist in the orginal String,
	 *      if not this method will take no effect.
	 * @param newS a String to replace the old String in the orginal String.
	 *
	 * @return the reslut after replace.
	 */
	public static String replace(String s, String oldS, String newS)
	{
		StringBuffer buf = new StringBuffer();
		int i = 0;

		while (true)
		{
			int pos = s.indexOf(oldS, i);

			if (pos == -1)
			{
				break;
			}

			buf.append(s.substring(i, pos));
			buf.append(newS);
			i = pos + oldS.length();

			if (i >= s.length())
			{
				break;
			}
		}

		buf.append(s.substring(i));

		return buf.toString();
	}

	
	/**
	 * Add leading zero-character and convert into string.
	 * <P>
	 * e.g. add0(36, 6) will return 000036 NOTES: result is undetermine if
	 * number of decimal of given integer is large then desired length of string.
	 *
	 * @param v the number you want to add zero.
	 * @param l the number of string length after call this method
	 *
	 * @return the result.
	 */
	public static String add0(int v, int l)
	{
		long lv = (long) Math.pow(10, l);

		return String.valueOf(lv + v).substring(1);
	}
	
	
	final static double	pow[]	= { 0, 10, 100, 1000, 10000, 100000, 1000000};
	/**
	 * 
	 * @param d
	 * @param scale
	 * @return Number 对数进行几位的四舍五入计算。
	 */
	public static Number round(double d, int scale)
	{
		if (scale == 0)
			return Math.round(d);

		double b = pow[scale];
		double v = Math.round(d * b) / b;

		return scale == 0 ? new Integer((int) v) : new Double(v);
	}
	
	private static char getNumberSystemValue(long value)
	{
		if (value < 10)
		{
			return (char) ('0' + value);
		}

		if ((value >= 10) && (value < 36))
		{
			return (char) ('A' - 10 + value);
		}

		return (char) ('a' - 36 + value);
	}
	
	public static String convertBase(long value, int base)
	{
		long newValue = value;
		long result = 0;
		StringBuffer strBuffer = new StringBuffer();

		do
		{
			long modValue = newValue % base;
			result = newValue / base;

			strBuffer.append(getNumberSystemValue(modValue));
			newValue = result;
		}
		while (result >= base);

		strBuffer.append(getNumberSystemValue(newValue));

		return strBuffer.toString();
	}
	
	public final static String getEncodingType(String path) throws IOException
	{
		FileInputStream fs = null;

		try
		{
			fs = new FileInputStream(path);
			byte[] bytes = new byte[2];
			int cnt = fs.read(bytes, 0, 2);

			if (cnt == 2)
			{
				if ((0xFF & bytes[0]) == 239 && (0xFF & bytes[1]) == 187)
					return "UTF-8";

				if ((0xFF & bytes[0]) == 255 && (0xFF & bytes[1]) == 254)
					return "Unicode";
			}
		}
		finally
		{
			if (fs != null)
				fs.close();
		}

		return null;
	}
	
	/**
	 * Return a global unique string (using the timestamp and a random number) allow character: A-Z, a-z, 0-9.
	 *
	 * @return a global unique string.
	 */
	public static String uniqueStr()
	{
		return convertBase((long) RANDOM.nextInt(238328), 62) + convertBase(System.currentTimeMillis(), 62);
	}
	
}
