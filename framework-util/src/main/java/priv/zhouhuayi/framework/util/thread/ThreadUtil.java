package priv.zhouhuayi.framework.util.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * 多线程工具类
 * 
 * @author zhy
 *
 */
public class ThreadUtil implements Runnable {
	// 调用的class
	private Class<?> className;
	
	// 处理的方法名
	private String methodName;
	
	// 回调参数
	private Object[] params;
	
	// 日志对象
	private static Logger log = Logger.getLogger(ThreadUtil.class);
	
	/**
	 * 线程的构造方法
	 * 
	 * @author zhy
	 * @param className 调用的类class
	 * @param methodName 回调的方法名
	 * @param params 回调传递的参数
	 */
	public ThreadUtil(Class<?> className, String methodName, Object... params) {
		this.className = className;
		this.methodName = methodName;
		this.params = params;
	}
	
	@Override
	public void run() {
		Method method = null;
		try {
			// 获取参数长度
			int paramsLen = params.length;
			
			/* 获取方法参数类型 */
			Class<?>[] paramsClass = new Class<?>[params.length];
			for (int i = 0; i < paramsLen; i++) {
				paramsClass[i] = params[i].getClass();
			}
			
			// 获取回调方法
			method = className.getMethod(methodName, paramsClass);
			// 调用回电方法
			method.invoke(className.newInstance(), params);
		} catch (NoSuchMethodException e) {
			log.error("找不到该方法：" + e.getMessage(), e);
		} catch (SecurityException e) {
			log.error("安全出现异常：" + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			log.error("非法进入异常：" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			log.error("非法争议异常：" + e.getMessage(), e);
		} catch (InvocationTargetException e) {
			log.error("调用目标异常：" + e.getMessage(), e);
		} catch (InstantiationException e) {
			log.error("实例化异常：" + e.getMessage(), e);
		}
	}
}
