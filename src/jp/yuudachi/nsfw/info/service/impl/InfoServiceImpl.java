package jp.yuudachi.nsfw.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.core.service.impl.BaseServiceImpl;
import jp.yuudachi.nsfw.info.dao.InfoDao;
import jp.yuudachi.nsfw.info.entity.Info;
import jp.yuudachi.nsfw.info.service.InfoService;
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

	private InfoDao infoDao;

	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
}
