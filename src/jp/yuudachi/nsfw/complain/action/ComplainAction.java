package jp.yuudachi.nsfw.complain.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.opensymphony.xwork2.ActionContext;

import jp.yuudachi.core.action.BaseAction;
import jp.yuudachi.core.util.QueryHelper;
import jp.yuudachi.nsfw.complain.entity.Complain;
import jp.yuudachi.nsfw.complain.entity.ComplainReply;
import jp.yuudachi.nsfw.complain.service.ComplainService;

public class ComplainAction extends BaseAction {
	
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply reply;
	
	//列表
	public String listUI(){
		try {
			//加载状态集合
			ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
			QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
			if(StringUtils.isNotBlank(startTime)){//起始时间之后的数据
				startTime = URLDecoder.decode(startTime, "utf-8");
				queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime+":00", "yyyy-MM-dd HH:mm:ss"));
			}
			if(StringUtils.isNotBlank(endTime)){//结束时间之前的数据
				startTime = URLDecoder.decode(endTime, "utf-8");
				queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime+":59", "yyyy-MM-dd HH:mm:ss"));
			}
			if(complain != null){
				if(StringUtils.isNotBlank(complain.getState())){
					queryHelper.addCondition("c.state=?", complain.getState());
				}
				if(StringUtils.isNotBlank(complain.getCompTitle())){
					complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
					queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
				}				
			}
			//状态 按照状态升序
			queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
			//排序 按照时间升序
			queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
			
			pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUI";
	}
	
	//跳转到受理页面
	public String dealUI(){
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(complain != null){
			complain = complainService.findObjectById(complain.getCompId());
		}
		return "dealUI";
	}
	
	//受理
	public String deal(){
		if(complain != null){
			Complain tem = complainService.findObjectById(complain.getCompId());
			//更新投诉状态为已受理
			if(Complain.COMPLAIN_STATE_UNDONE.equals(tem.getState())){
				tem.setState(Complain.COMPLAIN_STATE_DONE);
			}
			//保存回复信息
			if(reply != null){
				reply.setComplain(tem);
				reply.setReplyTime(new Timestamp(new Date().getTime()));
				tem.getComplainReplies().add(reply);
			}
			complainService.update(tem);
		}		
		return "list";
	}

	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ComplainReply getReply() {
		return reply;
	}

	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}
	
}
