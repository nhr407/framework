package priv.zhouhuayi.framework.util.backMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息响应体
 * 
 * @author zhy
 *
 */
public class BackMsgUtil {
	/******************************公共响应码**********************************/
	/**操作成功*/
	public final static int SUCCESS = 100;
	
	/**操作失败*/
	public final static int FAIL = 101;
	
	
	/******************************业务响应码**********************************/
	
	
	/********************************信息提示*********************************/
	public final static Map<Integer, String> codeMessage = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 9129859292809731011L;
		{
			put(SUCCESS, "操作成功");
			put(FAIL, "操作失败");
		}
	};
	
	/**
	 * 传递参数的默认提示信息
	 * 
	 * @author zhy
	 * @param result 返回的结果
	 * @param code 响应码
	 * @return
	 */
	public static Map<String, Object> backMsg(Object result, String code) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", result);
		backMsg.put("code", code);
		backMsg.put("message", codeMessage.get(code));
		return backMsg;
	}
	
	/**
	 * 传递参数的自定义提示信息
	 * 
	 * @author zhy
	 * @param result 返回的结果
	 * @param code 响应码
	 * @param message 自定义响应消息
	 * @return
	 */
	public static Map<String, Object> backMsg(Object result, String code, String message) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", result);
		backMsg.put("code", code);
		backMsg.put("message", message);
		return backMsg;
	}
	
	/**
	 * 失败的自定义提示信息
	 * 
	 * @author zhy
	 * @param message 自定义响应消息
	 * @return
	 */
	public static Map<String, Object> fail(String message) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("code", FAIL);
		backMsg.put("message", message);
		return backMsg;
	}
	
	/**
	 * 失败的默认提示信息
	 * 
	 * @author zhy
	 * @return
	 */
	public static Map<String, Object> fail() {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", "");
		backMsg.put("code", FAIL);
		backMsg.put("message", codeMessage.get(FAIL));
		return backMsg;
	}
	
	/**
	 * 成功默认的提示信息，不返回结果
	 * 
	 * @author zhy
	 * @param result 返回的结果
	 * @return
	 */
	public static Map<String, Object> success() {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", "");
		backMsg.put("code", SUCCESS);
		backMsg.put("message", codeMessage.get(SUCCESS));
		return backMsg;
	}
	
	/**
	 * 成功的自定义提示信息,不返回结果
	 * 
	 * @author zhy
	 * @param message 自定义响应消息
	 * @return
	 */
	public static Map<String, Object> success(String message) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", "");
		backMsg.put("code", SUCCESS);
		backMsg.put("message", message);
		return backMsg;
	}
	
	/**
	 * 成功的默认提示信息,返回自定义结果
	 * 
	 * @author zhy
	 * @param result 返回的结果
	 * @return
	 */
	public static Map<String, Object> success(Object result) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", result);
		backMsg.put("code", SUCCESS);
		backMsg.put("message", codeMessage.get(SUCCESS));
		return backMsg;
	}
	
	/**
	 * 成功的自定义提示信息,返回自定义结果
	 * 
	 * @author zhy
	 * @param result 返回的结果
	 * @param message 自定义响应消息
	 * @return
	 */
	public static Map<String, Object> success(Object result, String message) {
		Map<String, Object> backMsg = new HashMap<String, Object>();
		backMsg.put("result", result);
		backMsg.put("code", SUCCESS);
		backMsg.put("message", message);
		return backMsg;
	}
}
