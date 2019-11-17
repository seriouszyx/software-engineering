package dataService.sourceDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SourceDataService extends Remote {


	/**
	 * @Description:获取该系统覆盖的所有城市
	 * @return 城市集合
	 * Iterator<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午4:59:17
	 */
	public List<String> getCities() throws RemoteException;


	/**
	 * @Description:根据选中的城市，获取该城市内，系统支持的所有商圈
	 * @param city 城市
	 * @return 商圈集合
	 * Iterator<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午6:46:45
	 */
	public List<String> getCircles(String city) throws RemoteException;

	/**
	 * @Description:获取该系统支持的所有酒店的星级
	 * @return 酒店星级集合
	 * Iterator<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午4:59:54
	 */
	public List<String> getLevels() throws RemoteException;


	/**
	 * @Description:获取该系统支持的所有房间类型
	 * @return 房间类型集合
	 * Iterator<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午5:00:19
	 */
	public List<String> getRoomTypes() throws RemoteException;

	/**
	 * @Description:获取系统允许的每一订单最大人数
	 * @return 订单最大人数
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月13日 下午6:50:26
	 */
	public int getMaxGuestNumEachOrder() throws RemoteException;

	/**
	 * @Description:获取系统允许的每一订单最大房间数
	 * @return 订单最大房间数
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月13日 下午6:50:47
	 */
	public int getMaxRoomNumEachOrder()throws RemoteException;
	
	
	
	//重复登录暂时没有实现，故不在文档里列出来
	/**
	 * @Description:客户登录，记录客户登录信息
	 * @param guestID 用户id
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:51:19
	 */
	public void guestLogInRecord(String guestID)throws RemoteException;

	/**
	 * @Description:客户注销，移除客户登录信息
	 * @param guestID 用户id
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:51:43
	 */
	public void guestLogOut(String guestID)throws RemoteException;

	/**
	 * @Description:检查客户是否登录
	 * @param guestID 用户id
	 * @return
	 * boolean
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:51:59
	 */
	public boolean guestHasLogged(String guestID)throws RemoteException;
	
}
