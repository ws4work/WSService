package personal.ws.util.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapComparator {

	public static void main(String[] args) {
	}

	/**
	 * �ж�����Map���Ƿ�����ȫ��ͬԪ��
	 * 
	 * @param Map<String,Object>
	 * @param Map<String,Object>
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> allSame2Map(Map<String,Object> map1, Map<String,Object> map2) {
		Map<String,Object> sameMap = new HashMap<String,Object>();
		for (String same1 : map1.keySet()) {
			if (map2.containsKey(same1)) {
				Object value1 = map1.get(same1);
				Object value2 = map2.get(same1);
				if (value1.equals(value2)) {
					sameMap.put(same1, value1);
				}
			}
		}
		return sameMap;
	}

	/**
	 * �ж�����Map���Ƿ���Key��ͬԪ��
	 * 
	 * @param Map<String,Object>
	 * @param Map<String,Object>
	 * @return Map<String,List<Object>>
	 */
	public static Map<String,List<Object>> keySame2Map(Map<String,Object> map1, Map<String,Object> map2) {
		Map<String,List<Object>> sameMap = new HashMap<String,List<Object>>();
		for (String same1 : map1.keySet()) {
			if (map2.containsKey(same1)) {
				List<Object> list = new ArrayList<Object>();
				list.add(map1.get(same1));
				list.add(map2.get(same1));
				sameMap.put(same1, list);
			}
		}
		return sameMap;
	}

	/**
	 * �ж�����Map���Ƿ���Key��ͬԪ��
	 * 
	 * @param Map<String,Object>
	 * @param Map<String,Object>
	 * @return Map<String,List<Object>>
	 */
	public static Map<String,List<Object>> valueSame2Map(Map<String,Object> map1, Map<String,Object> map2) {
		Map<String,List<Object>> sameMap = new HashMap<String,List<Object>>();
		List<Object> keyList = new ArrayList<Object>();
		for (Entry<String,Object> entryMap1 : map1.entrySet()) {
			for (Entry<String,Object> entryMap2 : map2.entrySet()) {
				Object value1 = entryMap1.getValue();
				//				Object value2 = entryMap2.getValue();
				if (map2.containsValue(value1)) {
					int size = keyList.size();
					List<Object> list = new ArrayList<Object>();
					list.add(entryMap1.getKey());
					list.add(entryMap2.getKey());
					sameMap.put(String.valueOf(size + 2), list);
					keyList.add(String.valueOf(size + 2));
				}
			}
		}
		sameMap.put("key", keyList);
		return sameMap;
	}
}
