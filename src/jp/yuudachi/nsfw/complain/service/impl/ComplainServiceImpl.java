package jp.yuudachi.nsfw.complain.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.core.service.BaseService;
import jp.yuudachi.core.service.impl.BaseServiceImpl;
import jp.yuudachi.core.util.QueryHelper;
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

	@Override
	public void autoDeal() {
		Calendar cal = Calendar.getInstance();
		//控制时间点 详见API
		cal.set(Calendar.DAY_OF_MONTH, 1);//1日
		cal.set(Calendar.HOUR_OF_DAY, 0);//0时
		cal.set(Calendar.MINUTE, 0);//0分
		cal.set(Calendar.SECOND, 0);//0秒
		
		//1.查询本月之前待受理的投诉列表
		QueryHelper queryHelper = new QueryHelper(Complain.class,"c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime());
		
		List<Complain> list = findObjects(queryHelper);
		if(list != null && list.size() > 0){
			//2.更新投诉信息为已失效
			for(Complain comp : list){
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				update(comp);
			}
		}
		
	}

}
