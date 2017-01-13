package jp.yuudachi.core.util;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	
	private List<Object> parameters;
	private String fromClause = "";
	private String whereClause = "";
	private String orderByClause = "";
	public static String ORDER_BY_DESC = "DESC";
	public static String ORDER_BY_ASC = "ASC";
	
	/** 
	 * 构造from子句
	 * @param clazz 实体类
	 * @param alias 实体类对应的别名
	 */
	public QueryHelper(Class clazz,String alias){
		fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 构造where子句
	 * @param condition 查询条件语句：例如 i.title like ?
	 * @param params 查询条件语句中?对应的查询条件值：例如: %标题%
	 */
	public void addCondition(String condition, Object... params){
		if (whereClause.length() > 1) {//非第一个查询条件
			whereClause += " AND " + condition;
		}else{//第一个查询条件
			whereClause += " WHERE " + condition;
		}
		//设置查询条件值到查询条件值集合中
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		if(params != null){
			for(Object param : params){
				parameters.add(param);
			}
		}
	}
	
	/**
	 * 构造order by 子句
	 * @param propertyString 排序属性 如 i.createTime
	 * @param order 排序顺序 如DESC 或者 ASC
	 */
	public void addOrderByProperty(String property, String order){
		if(orderByClause.length() > 1){//非第一个排序属性
			orderByClause += " , " + property + " " + order;
		}else{//第一个排序属性
			orderByClause = " ORDER BY " + property + " " + order;
		}
	}
	
	//查询hql语句
	/**
	 * 查询条件语句HQL：
	 * from子句：必定出现 而且只出现一次
	 * where子句：可选；但关键字where出现一次；可添加多个查询条件
	 * order by子句：可选；但关键字order by出现一次；可添加多个排序属性
	 */
	public String getQueryListHql(){	
		return fromClause + whereClause + orderByClause;
	}
	
	//hql语句中？对应的查询条件值的集合
	public List<Object> getParameters(){
		/**
		 * 出现时机：再添加查询条件的时候 ？对应的查询条件值
		 */
		return parameters;
	}
	
}
