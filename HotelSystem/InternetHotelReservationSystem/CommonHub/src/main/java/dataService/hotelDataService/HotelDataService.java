package dataService.hotelDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.HotelPO;
import po.RoomInfoPO;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * 
 * @author cuihua
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 * 
 * 对数据库中酒店的操作
 */
public interface HotelDataService extends Remote {

	/**
	 * 
	 * @param hotelID 酒店编号
	 * @return 此酒店的所有数据载体
	 * @throws RemoteException RMI异常
	 * 
	 * 获取酒店的基本信息
	 */
	public HotelPO getHotelInfo (String hotelID) throws RemoteException;
	
	/**
	 * 
	 * @param city 特定的城市
	 * @param circle 特定的商圈
	 * @return 此特定城市商圈的酒店信息列表
	 * @throws RemoteException RMI异常
	 * 
	 * 获取一特定城市商圈的所有酒店
	 */
	public List<HotelPO> getHotels(String city, String circle) throws RemoteException;
	
	/**
	 * 
	 * @param hotelPO 酒店的载体
	 * @return 是否更新成功
	 * @throws RemoteException RMI异常
	 * 
	 * 更新酒店的基本信息
	 */
	public ResultMessage updateHotelInfo(HotelPO hotelPO) throws RemoteException;
	
	/**
	 * 
	 * @param hotelPO 酒店的载体
	 * @return 是否添加成功
	 * @throws RemoteException RMI异常
	 * 
	 * 添加酒店信息
	 */
	public ResultMessage addHotelInfo(HotelPO hotelPO) throws RemoteException;

	/**
	 * @Description:提供修改roomInfo数据的方法，add、delete、update、get
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午1:57:07
	 */
	public List<RoomInfoPO> getRoomInfo(String hotelID) throws RemoteException;

	/**
	 * 
	 * @param roomInfoPO 房间信息
	 * @return 是否更新成功
	 * @throws RemoteException RMI异常
	 * 
	 * 更新酒店房间信息
	 */
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException;

	/**
	 * 
	 * @param roomInfoPO 房间信息
	 * @return 是否添加成功
	 * @throws RemoteException RMI异常
	 * 
	 * 添加酒店房间信息
	 */
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException;

}
