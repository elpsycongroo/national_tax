package jp.yuudachi.core.exception;

public class ServiceException extends SysException {

	public ServiceException() {
		super("【业务操作出现异常】");
	}

	public ServiceException(String message) {
		super(message);
	}
	
}
