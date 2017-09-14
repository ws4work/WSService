package personal.ws.util.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ToMapUtile {

	public static void main(String[] args) {
		System.out.println(string2HashMap("abc", "bcd", "asdb").toString());
	}

	/**
	 * �����б�תHashMap,keyΪ�����±�,valueΪ����
	 * 
	 * @param String...
	 * @return HashMap
	 */
	public static Map<String,String> string2HashMap(String... strings) {
		Map<String,String> map = new HashMap<String,String>();
		string2map(map, strings);
		return map;
	}

	/**
	 * ����תHashMap,keyΪList�±�,valueΪ����
	 * 
	 * @param Object[]
	 * @return HashMap
	 */
	public static Map<String,Object> array2HashMap(Object[] obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		array2map(map, obj);
		return map;
	}

	/**
	 * ListתHashMap,keyΪList�±�,valueΪ����
	 * 
	 * @param List<Object>
	 * @return HashMap
	 */
	public static Map<String,Object> list2HashMap(List<Object> list) {
		Map<String,Object> map = new HashMap<String,Object>();
		list2map(map, list);
		return map;
	}

	/**
	 * �����б�תTreeMap,keyΪ�����±�,valueΪ����
	 * 
	 * @param String...
	 * @return TreeMap
	 */
	public static Map<String,String> string2TreeMap(String... strings) {
		Map<String,String> map = new TreeMap<String,String>();
		string2map(map, strings);
		return map;
	}

	/**
	 * ����תTreeMap,keyΪList�±�,valueΪ����
	 * 
	 * @param List<Object>
	 * @return TreeMap
	 */
	public static Map<String,Object> list2TreeMap(Object[] obj) {
		Map<String,Object> map = new TreeMap<String,Object>();
		array2map(map, obj);
		return map;
	}

	/**
	 * ListתTreeMap,keyΪList�±�,valueΪ����
	 * 
	 * @param List<Object>
	 * @return TreeMap
	 */
	public static Map<String,Object> list2TreeMap(List<Object> list) {
		Map<String,Object> map = new TreeMap<String,Object>();
		list2map(map, list);
		return map;
	}

	/**
	 * �����б�תMap�㷨
	 * 
	 * @param Map<String,String>
	 * @param String...
	 */
	public static void string2map(Map<String,String> map, String... strings) {
		for (int i = 0; i < strings.length; i++) {
			map.put(String.valueOf(i), strings[i]);
		}
	}

	/**
	 * ����תMap�㷨
	 * 
	 * @param Map<String,Object>
	 * @param Object[]
	 */
	public static void array2map(Map<String,Object> map, Object[] obj) {
		for (int i = 0; i < obj.length; i++) {
			map.put(String.valueOf(i), obj[i]);
		}
	}

	/**
	 * ListתMap�㷨
	 * 
	 * @param Map<String,Object>
	 * @param List<Object>
	 */
	public static void list2map(Map<String,Object> map, List<Object> list) {
		for (int i = 0; i < list.size(); i++) {
			map.put(String.valueOf(i), list.get(i));
		}
	}
}
