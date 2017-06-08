package priv.zhouhuayi.framework.service.rollback;

import org.apache.log4j.Logger;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class RollBackException extends RuntimeException {
	private static final long serialVersionUID = -6079146169710614099L;
	
	//初始化日志文件
	Logger logger = Logger.getLogger(RollBackException.class);
	
	/**
	 * 自定义回滚异常
	 * @param e 异常
	 */
	public RollBackException(Throwable e) {
		try {
			//先回滚事物在做其他操作，防止二次异常
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			//打印异常轨迹
			logger.error(e.getMessage(), e);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}