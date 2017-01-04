package jp.yuudachi.core.exception;

public class ActionException extends SysException {

	public ActionException() {
		super("【业务逻辑出现异常】");
	}

	public ActionException(String message) {
		super(message);
	}
	
}
