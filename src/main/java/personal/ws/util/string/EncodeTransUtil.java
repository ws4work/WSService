package personal.ws.util.string;

import org.apache.tomcat.util.digester.DocumentProperties;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.nio.charset.Charset;

/**
 * ISO-8859-1与其他编码方式的互相转换中:先进行ISO到其他编码方式的转换,再进行其他编码方式到ISO的转换,可以将中文复原;反之不行。
 * Java进行转码时,均会先将字符转换为Unicode码,然后再进行编码
 */
public class EncodeTransUtil {

	//ISO-8859-1转GB2312
	public static String ISOToGB2312(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "GB2312");
	}

	//GB2312转ISO-8859-1
	public static String GB2312ToISO(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GB2312"), "ISO-8859-1");
	}

	//ISO-8859-1转GBK
	public static String ISOToGBK(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "GBK");
	}

	//GBK转ISO-8859-1
	public static String GBKToISO(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GBK"), "ISO-8859-1");
	}

	//ISO-8859-1转UTF-8
	public static String ISOToUTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}

	//UTF-8转ISO-8859-1
	public static String UTF8ToISO(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "ISO-8859-1");
	}

	//ISO-8859-1转UTF-16
	public static String ISOToUTF16(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "UTF-16");
	}

	//UTF-16转ISO-8859-1
	public static String UTF16ToISO(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes(str), "ISO-8859-1");
	}

	//GB2312转GBK
	public static String GB2312ToGBK(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GB2312"), "GBK");
	}

	//GBK转GB2312
	public static String GBKToGB2312(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GBK"), "GB2312");
	}

	//GB2312转UTF-8
	public static String GB2312ToUTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GB2312"), "UTF-8");
	}

	//UTF-8转GB2312
	public static String UTF8ToGB2312(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "GB2312");
	}

	//GB2312转UTF-16
	public static String GB2312ToUTF16(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GB2312"), "UTF-16");
	}

	//UTF-8转GB2312
	public static String UTF16ToGB2312(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes(str), "GB2312");
	}

	//GBK转UTF-8
	public static String GBKToUTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GBK"), "UTF-8");
	}

	//UTF-8转GBK
	public static String UTF8ToGBK(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "GBK");
	}

	//GBK转UTF-16
	public static String GBKToUTF16(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GBK"), "UTF-16");
	}

	//UTF-8转GBK
	public static String UTF16ToGBK(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes(str), "GBK");
	}

	//UTF-8转UTF-16
	public static String UTF8ToUTF16(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "UTF-16");
	}

	//UTF-16转UTF-8
	public static String UTF16ToUTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-16"), "UTF-8");
	}

	//GBK转Unicode
	public static String GBKToUnicode(String str) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char chr1 = (char) str.charAt(i);
			if (!isNeedConvert(chr1)) {
				result.append(chr1);
				continue;
			}
			result.append("\\u" + Integer.toHexString((int) chr1));
		}
		return result.toString();
	}

	//unicode转GBK
	public static String UnicodeToGBK(String dataStr) {
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		int li_len = dataStr.length();
		while (index < li_len) {
			if (index >= li_len - 1
					|| !"\\u".equals(dataStr.substring(index, index + 2))) {
				buffer.append(dataStr.charAt(index));
				index++;
				continue;
			}
			String charStr = "";
			charStr = dataStr.substring(index + 2, index + 6);
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(letter);
			index += 6;
		}
		return buffer.toString();
	}

	private static boolean isNeedConvert(char para) {
		return ((para & (0x00FF)) != para);
	}

	//UTF-8 转Unicode
	public static String UTF8ToUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {
			UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				sb.append(myBuffer[i]);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				int j = (int) myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				short s = (short) myBuffer[i];
				String hexS = Integer.toHexString(s);
				String unicode = "\\u" + hexS;
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

	//Unicode转UTF-8
	public static String UnicodeToUTF8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "中文";
		System.out.println(new String(new String(str.getBytes("GBK"),"UTF-8").getBytes("UTF-8"),"GBK"));
		System.out.println(new String(new String(str.getBytes("UTF-8"),"GBK").getBytes("GBK"),"UTF-8"));
	}
}
