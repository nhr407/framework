package priv.zhouhuayi.framework.util.sql;

import java.util.Map;

public class SqlUtil {
	/**
	 * 拼接SQL实现预处理语句
	 * 
	 * @author zhy
	 * @param sql 预处理的语句
	 * @param param 拼接的参数
	 * @return
	 */
	public static String sqlAppend(String sql, Map<String, Object> searchParams) {
		for (String key : searchParams.keySet()) {
			sql = sql.replaceAll(':' + key, SqlUtil.validateValue(searchParams.get(key).toString()));
		}
		return sql;
	}
	
	/**
	 * 直接拼接参数
	 * 
	 * @author zhy
	 * @param searchParams 查询参数
	 * @return
	 */
	public static StringBuffer appendWhereSql(Map<String, Object> searchParams) {
		StringBuffer sqlBuffer = new StringBuffer("");
		if(searchParams != null && searchParams.size() > 0) {
			sqlBuffer.append(" where ");
			for (String key : searchParams.keySet()) {
				sqlBuffer.append(key).append(" = ").append(SqlUtil.validateValue(searchParams.get

(key).toString())).append(" or ");
			}
			int length = sqlBuffer.length();
			return sqlBuffer.delete(length - 3, length);
		} else {
			return sqlBuffer;
		}
	}
	
	/**
	 * 验证字符串防止sql注入
	 * 
	 * @author zhy
	 * @param value 要过滤的值
	 * @return
	 */
	public static String validateValue(String value) {
		if(isEscapeNeededForString(value)) {
			return dealSql(value);
		} else {
			return '\'' + value + '\'';
		}
	}
	
	/**
	 * 验证是否该值是否需要处理
	 * 
	 * @author zhy
	 * @param value 要验证的值
	 * @return
	 */
	private static boolean isEscapeNeededForString(String value) {
        boolean needsHexEscape = false;
        int valueLength = value.length();
        for (int i = 0; i < valueLength; ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '\n': /* Must be escaped for logs */
                    needsHexEscape = true;
                    break;
                case '\r':
                    needsHexEscape = true;
                    break;
                case '\\':
                    needsHexEscape = true;
                    break;
                case '\'':
                    needsHexEscape = true;
                    break;
                case '"': /* Better safe than sorry */
                    needsHexEscape = true;
                    break;
                case '\032': /* This gives problems on Win32 */
                    needsHexEscape = true;
                    break;
            }

            if (needsHexEscape) {
                break; // no need to scan more
            }
        }
        return needsHexEscape;
    }
	
	/**
	 * 处理sql防止注入
	 * 
	 * @author zhy
	 * @param value 要处理的值
	 * @return
	 */
	private static String dealSql(String value) {
		int sqlLength = value.length();
		StringBuilder buf = new StringBuilder((int) (sqlLength * 1.1));
        buf.append('\'');
        for (int i = 0; i < sqlLength; ++i) {
            char c = value.charAt(i);
            switch (c) {
                case 0: /* Must be escaped for 'mysql' */
                    buf.append('\\');
                    buf.append('0');
                    break;
                case '\n': /* Must be escaped for logs */
                    buf.append('\\');
                    buf.append('n');
                    break;
                case '\r':
                    buf.append('\\');
                    buf.append('r');
                    break;
                case '\\':
                    buf.append('\\');
                    buf.append('\\');
                    break;
                case '\'':
                    buf.append('\\');
                    buf.append('\'');
                    break;
                case '"': /* Better safe than sorry */
                    buf.append('"');
                    break;
                case '\032': /* This gives problems on Win32 */
                    buf.append('\\');
                    buf.append('Z');
                    break;
                default:
                    buf.append(c);
            }
        }
        buf.append('\'');
        return buf.toString();
	}
}