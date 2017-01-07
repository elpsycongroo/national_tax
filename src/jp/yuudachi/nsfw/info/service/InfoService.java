package jp.yuudachi.nsfw.info.service;

import java.io.Serializable;
import java.util.List;

import jp.yuudachi.nsfw.info.entity.Info;

public interface InfoService {
	// save
	public void save(Info info);

	// update
	public void update(Info info);

	// delete by id
	public void delete(Serializable id);

	// find by id
	public Info findObjectById(Serializable id);

	// find list
	public List<Info> findObjects();
}
