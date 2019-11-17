package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.WebManagerDataHelper;
import po.WebManagerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey
 * @updateTime 2016/12/5
 *
 */
public class WebManagerDataHelperImpl_Stub implements WebManagerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public WebManagerPO add(WebManagerPO webManagerPO) {
		return new WebManagerPO("0001", "123456");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webManagerInfo
	 */
	public ResultMessage modify(final WebManagerPO webManagerPO) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webManagerID 网站管理人员ID
	 * @return WebManagerPO 数据库中的指定webManagerInfo载体
	 */
	public WebManagerPO getSingle(final String webManagerID) {
		return new WebManagerPO("0001", "123456");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/5
	 * @return List<WebManagerPO> 获取所有webManagerInfo载体
	 */
	public List<WebManagerPO> getAll() {
		List<WebManagerPO> list = new  ArrayList<WebManagerPO>();
		 list.add(new WebManagerPO("0001", "123456"));
		 list.add(new WebManagerPO("0002", "123457"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
	}
}
