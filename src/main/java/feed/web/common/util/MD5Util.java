package feed.web.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类，生成MD5字符串
 * @author Boxbox
 *
 */
public class MD5Util {

	public static String generate(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();

			int temp;
			StringBuilder sb = new StringBuilder("");
			for (int offset = 0; offset < b.length; offset++) {
				temp = b[offset];
				if (temp < 0)
					temp += 256;
				if (temp < 16)
					sb.append("0");
				sb.append(Integer.toHexString(temp));
			}
			str = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(generate("123"));
	}
	
}
