package jp.yuudachi.nsfw.complain.service;

import jp.yuudachi.core.service.BaseService;
import jp.yuudachi.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
	
	//自动受理投诉
	public void autoDeal();
	
}
