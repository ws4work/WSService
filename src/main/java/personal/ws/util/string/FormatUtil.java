package personal.ws.util.string;

/**
 * 数据格式化�?�用�?
 *
 * @author gaozhenhai
 * @version 1.0.0_1
 * @since 2013.01.15
 */
public class FormatUtil {

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
     * 将字符串类型的json格式
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}
		return sb.toString();
	}

	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}
}