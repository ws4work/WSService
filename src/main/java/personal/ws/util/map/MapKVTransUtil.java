package personal.ws.util.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapKVTransUtil {

	public static void main(String[] args) {
	}

	/**
	 * MapתArrayList����,��ȥkey,ȡvalue����list
	 * 
	 * @param Map<String,Object>
	 * @return List<Object>
	 */
	public static List<Object> mapValue2List(Map<String,Object> map) {
		List<Object> list = new ArrayList<Object>();
		for (Entry<String,Object> entryMap : map.entrySet()) {
			list.add(entryMap.getValue());
		}
		return list;
	}

	/**
	 * Mapת���鷽��,��ȥkey,ȡvalue����list
	 * 
	 * @param Map<String,Object>
	 * @return Object[]
	 */
	public static Object[] mapValue2Array(Map<String,Object> map) {
		Object[] obj = new Object[map.size()];
		for (Entry<String,Object> entryMap : map.entrySet()) {
			for (int i = 0; i < obj.length; i++) {
				if (null == obj[i]) {
					obj[i] = entryMap.getValue();
				}
			}
		}
		return obj;
	}

	/**
	 * MapתArrayList����,��ȥvalue,ȡkey����list
	 * 
	 * @param Map<String,Object>
	 * @return List<String>
	 */
	public static List<String> mapKey2List(Map<String,Object> map) {
		List<String> list = new ArrayList<String>();
		for (String entryMap : map.keySet()) {
			list.add(entryMap);
		}
		return list;
	}

	/**
	 * Mapת���鷽��,��ȥvalue,ȡkey����list
	 * 
	 * @param Map<String,Object>
	 * @return Object[]
	 */
	public static Object[] mapKey2Array(Map<String,Object> map) {
		Object[] obj = new Object[map.size()];
		for (String entryMap : map.keySet()) {
			for (int i = 0; i < obj.length; i++) {
				if (null == obj[i]) {
					obj[i] = entryMap;
				}
			}
		}
		return obj;
	}
}
