package jp.yuudachi.nsfw.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Map> getAnnualStatisticDataByYear(int year) {
		List<Map> resList = new ArrayList<Map>();
		//1.获取统计数据
		List<Object[]> list = complainDao.getAnnualStatisticDataByYear(year);
		if(list != null && list.size() > 0){
			Calendar cal = Calendar.getInstance();
			//是否当前年份
			boolean isCurYear = (cal.get(Calendar.YEAR) == year);
			int curMonth = cal.get(Calendar.MONTH)+1;
			//2.格式化统计结果
			int temMonth = 0;
			Map<String, Object> map = null;
			for(Object[] obj : list){
				temMonth = Integer.valueOf((obj[0])+"");
				map = new HashMap<String, Object>();
				map.put("label", temMonth+" 月");
				if(isCurYear){
					//当前年份:如果月份已过则直接取投诉数并且值为空或者Null时为0 如果月份未过则全部置为空
					if(temMonth > curMonth){
						//月份已过 全部置为空
						map.put("value", "");
					}else{
						map.put("value", obj[1]==null?"":obj[1]);
					}
				}else{
					//非当前年份 已过则直接取投诉数并且值为空或者Null时为0
					map.put("value", obj[1]==null?"":obj[1]);
				}
				resList.add(map);
			}
		}
		return resList;
	}

}
