package personal.ws.util.map;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTransUtil {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "123");
        map.put("b", "345");
        map.put("c", "567");
        List<Map<String, Object>> map2List = map2ListMap(map);
        System.out.println(map2List.toString());
    }

    /**
     * 将map中的值遍历到list中
     */
    public static List<Map<String, Object>> map2ListMap(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, Object> entrymap : map.entrySet()) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(entrymap.getKey(), entrymap.getValue());
            list.add(resultMap);
        }
        return list;
    }

    /**
     * 将子map遍历到list中
     */
    public static List<Map<String, Object>> childrenMap2ListMap(Map<Object, Map<String, Object>> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map.Entry<Object, Map<String, Object>> entrymap : map.entrySet()) {
            list.add(entrymap.getValue());
        }
        return list;
    }

    public static Object[] map2ArrayMap(Map<String, Object> map) {
        Object[] array = new Object[]{};
        if(null != map && map.size() > 0){
            array = new Object[map.size()];
            for (Map.Entry<String, Object> entrymap : map.entrySet()) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put(entrymap.getKey(), entrymap.getValue());
//				array[i](resultMap);
            }
        }
        return array;
    }

    public static JSONObject requestParaMap2Json(Map<String, String[]> map) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            json.put(entry.getKey(),entry.getValue()[0]);
        }
        return json;
    }

}
