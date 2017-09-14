package personal.ws.util.string;

import java.math.BigDecimal;
import java.math.BigInteger;

//
//											                   _ooOoo_
//											                  o8888888o
//											                  88" . "88
//											                  (| -_- |)
//											                  O\  =  /O
//											               ____/`---'\____
//											             .'  \\|     |//  `.
//											            /  \\|||  :  |||//  \
//											           /  _||||| -:- |||||-  \
//											           |   | \\\  -  /// |   |
//											           | \_|  ''\---/''  |   |
//											           \  .-\__  `-`  ___/-. /
//											         ___`. .'  /--.--\  `. . __
//											      ."" '<  `.___\_<|>_/___.'  >'"".
//											     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//											     \  \ `-.   \_ __\ /__ _/   .-` /  /
//											======`-.____`-.___\_____/___.-`____.-'======
//											                   `=---='
//											^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//      													佛祖保佑       永无BUG
//															           佛曰:  
//													  	 写字楼里写字间，写字间里程序员；  
//													 	 程序人员写程序，又拿程序换酒钱。  
//													 	 酒醒只在网上坐，酒醉还来网下眠；  
//													  	 酒醉酒醒日复日，网上网下年复年。  
//													  	 但愿老死电脑间，不愿鞠躬老板前；  
//													  	 奔驰宝马贵者趣，公交自行程序员。  
//													  	 别人笑我忒疯癫，我笑自己命太贱；  
//													  	 不见满街漂亮妹，哪个归得程序员？ 
public class StrUtil {

	/**
	 * Object转int 类型不全 要转哪个 自己加
	 * 
	 * @param o
	 * @return
	 */
	public static int obj2int(Object o) {
		if (o == null)
			return 0;
		else if (o instanceof String)
			return str2int(o.toString());
		else if (o instanceof Number)
			return ((Number) o).intValue();
		else
			return 0;
	}

	public static long obj2long(Object o) {
		if (o == null)
			return 0;
		else if (o instanceof String)
			return str2long(o.toString());
		else if (o instanceof Number)
			return ((Number) o).longValue();
		else
			return 0;
	}

	public static long str2long(String string) {
		if ("".equals(stringBlank(string)))
			return 0L;
		return Integer.parseInt(string);
	}

	public static String obj2str(Object o) {
		if (o == null)
			return "";
		else if ("".equals(o) || "".equals(o.toString().trim()))
			return "";
		else
			return o.toString();
	}

	/**
	 * 转换去空格
	 * 
	 * @param o
	 * @return
	 */
	public static String obj2strTrim(Object o) {
		if (o == null)
			return "";
		else if ("".equals(o) || "".equals(o.toString().trim()))
			return "";
		else
			return o.toString().trim();
	}

	public static boolean obj2boolean(Object o) {
		if (o == null)
			return false;
		if (o instanceof Boolean)
			return (boolean) o;
		else if (o instanceof String && "true".equals(obj2str(o)))
			return true;
		else
			return false;
	}

	public static int str2int(String str) {
		if ("".equals(stringBlank(str)))
			return 0;
		return Integer.parseInt(str);
	}

	/**
	 * null "" 格式化为""
	 * 
	 * @param value
	 * @return ""
	 */
	public static String stringBlank(String value) {
		if (value == null || value.equals("")) {
			value = "";
		}
		return value.replaceAll("\r|\n", "");
	}

	/**
	 * int转str
	 * 
	 * @param value
	 * @return
	 */
	public static String int2str(int value) {
		return String.valueOf(value);
	}

	/**
	 * obj2BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal obj2big(Object value) {
		BigDecimal ret = null;
		if (value != null) {
			if ("".equals(value.toString()))
				return BigDecimal.ZERO;
			if (value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if (value instanceof String) {
				ret = new BigDecimal((String) value);
			} else if (value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) value);
			} else if (value instanceof Number) {
				ret = new BigDecimal(((Number) value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
			}
		} else {
			return BigDecimal.ZERO;
		}
		return ret;
	}

	/**
	 * 功能描述：人民币转成大写
	 * 
	 * @param str
	 *            数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public static String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}
		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
}
