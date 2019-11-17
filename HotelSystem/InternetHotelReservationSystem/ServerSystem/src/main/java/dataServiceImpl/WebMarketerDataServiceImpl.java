package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.WebMarketerDataHelper;
import dataHelperImpl.WebMarketerDataHelperImpl;
import dataService.webMarketerDataService.WebMarketerDataService;
import po.WebMarketerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey 
 * @updateTime 2016/12/6
 *
 */
public class WebMarketerDataServiceImpl extends UnicastRemoteObject implements WebMarketerDataService{

	
	private static final long serialVersionUID = 5005778431917933128L;
	
	private WebMarketerDataHelper webMarketerHelper;
	
	/**
	 * @author 董金玉
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/6 构造函数
	 */
	public WebMarketerDataServiceImpl() throws RemoteException {
		webMarketerHelper = new WebMarketerDataHelperImpl();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerID 网站营销人员ID
	 * @return WebMarketerPO webMarketerInfo载体
	 * @throws UserInexistException 
	 */
	public WebMarketerPO getSingleWebMarketer(String webMarketerID) throws RemoteException {
		WebMarketerPO webMarketerPO = this.webMarketerHelper.getSingle(webMarketerID);
		return webMarketerPO;// 从数据库中得到webMarketerPO，若不存在则为空
	
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @return List<WebMarketerPO> 所有webMarketerInfo载体
	 */
	public List<WebMarketerPO> getAllWebMarketer() throws RemoteException {
		
		List<WebMarketerPO> list = this.webMarketerHelper.getAll();
		// 从数据库中得到所有webMarketerWorkerPO，若不存在则为空
		if(list.isEmpty()){return null;}
		
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param newWebMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功添加webMarketerInfo
	 */
	public WebMarketerPO add(WebMarketerPO newWebMarketerPO) throws RemoteException {
		return this.webMarketerHelper.add(newWebMarketerPO);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功修改webMarketerInfo
	 */
	public ResultMessage modify(WebMarketerPO webMarketerPO) throws RemoteException {
		return this.webMarketerHelper.modify(webMarketerPO);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param webMarketerID 网站营销人员ID
	 * @return ResultMessage 是否成功删除webMarketerInfo
	 */
	public ResultMessage deleteWebMarketer(String webMarekterID) throws RemoteException {
		return this.webMarketerHelper.delete(webMarekterID);
	}

}
