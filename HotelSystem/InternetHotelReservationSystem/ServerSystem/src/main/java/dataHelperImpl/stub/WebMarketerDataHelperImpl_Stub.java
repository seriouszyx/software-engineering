package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.WebMarketerDataHelper;
import po.WebMarketerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey
 * @updateTime 2016/12/5
 *
 */
public class WebMarketerDataHelperImpl_Stub implements WebMarketerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public WebMarketerPO add(WebMarketerPO webMarketerPO) {
		return  new WebMarketerPO("000001", "123456");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webMarketerInfo
	 */
	public ResultMessage modify(final WebMarketerPO webMarketerPO) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerID 网站营销人员ID
	 * @return ResultMessage 是否成功删除指定网站营销人员信息
	 */
	public ResultMessage delete(final String webMarketerID) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerID 网站营销人员ID
	 * @return WebMarketerPO 数据库中的指定webMarketerInfo载体
	 */
	public WebMarketerPO getSingle(final String webMarketerID) {
		return new WebMarketerPO("000001", "123456");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/5
	 * @param
	 * @return List<WebMarketerPO> 获取所有webMarketerInfo载体
	 */
	public List<WebMarketerPO> getAll() {
		List<WebMarketerPO> list= new ArrayList<WebMarketerPO>();
		list.add(new WebMarketerPO("000001", "123456"));
		list.add(new WebMarketerPO("000002", "123457"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
	}
}
