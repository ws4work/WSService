package personal.ws.learning.jdk.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LumbdaTest {
	public static void main(String[] args) {
		// Thread t = new Thread(new Runnable()->);
		List<String> names = new ArrayList<String>();
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		Collections.sort(names, (a, b) -> b.compareTo(a));
		Arrays.asList( "a", "b", "d" ).forEach( adba -> System.out.println( adba ) );
	}
	
	public int test(){
		int a=0;
		return a;
	}
	public void testm(){
		//System.out.println(LumbdaTest::test);
	}
}
