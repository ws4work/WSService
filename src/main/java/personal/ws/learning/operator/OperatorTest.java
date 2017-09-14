package personal.ws.learning.operator;

public class OperatorTest {
	public static void main(String[] args) {
		intAddAndEqualOperator();
		bitOper();
	}

	public static void intAddAndEqualOperator() {
		System.out.println("---------intAddAndEqualOperator()---------start");
		int b = 2;
		int c = 1;
		int d = 50;
		c += b;
		d = +b;
		System.out.println(c);
		System.out.println(d);
		System.out.println("---------intAddAndEqualOperator()---------stop");
	}

	public static void bitOper() {
		System.out.println("---------bitOper()---------start");
		int a = 100;
		int b = 200;
		//十进制转二进制
		System.out.println(Integer.toBinaryString(b));
		//十进制转八进制
		System.out.println(Integer.toOctalString(b));
		//十进制转十六进制
		System.out.println(Integer.toHexString(b));
		// 十六进制转成十进制
		// Integer.valueOf("FFFF",16).toString()
		// 八进制转成十进制
		// Integer.valueOf("876",8).toString()
		// 二进制转十进制
		// Integer.valueOf("0101",2).toString()
		//b*4
		System.out.println(b << 2);
		//b/4
		System.out.println(b >> 2);
		//a&b
		System.out.println(a & b);
		System.out.println(Integer.toBinaryString(a & b));
		//a|b
		System.out.println(a | b);
		System.out.println(Integer.toBinaryString(a | b));
		//a^b
		System.out.println(a ^ b);
		System.out.println(Integer.toBinaryString(a ^ b));
		//~b
		System.out.println(~b);
		System.out.println(Integer.toBinaryString(~b));
		//奇偶性验证
		System.out.println(b&0x1);
		System.out.println(b >> 1 == a);
		System.out.println("---------bitOper()---------stop");
	}
	
}
