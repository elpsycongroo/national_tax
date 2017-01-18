package jp.yuudachi.nsfw.complain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.core.service.BaseService;
import jp.yuudachi.core.service.impl.BaseServiceImpl;
import jp.yuudachi.nsfw.complain.dao.ComplainDao;
import jp.yuudachi.nsfw.complain.entity.Complain;
import jp.yuudachi.nsfw.complain.service.ComplainService;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {
	
	private ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

}
