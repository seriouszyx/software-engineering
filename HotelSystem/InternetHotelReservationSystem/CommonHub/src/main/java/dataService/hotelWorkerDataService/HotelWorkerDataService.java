package dataService.hotelWorkerDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.HotelWorkerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author cuihua
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 * 
 * 对数据库中酒店工作人员的信息进行操作
 */
public interface HotelWorkerDataService extends Remote{

	/**
	 * 
	 * @Description: 获取某一特定酒店工作人员的所有信息
	 * @param hotelWorkerID 酒店工作人员编号
	 * @return 酒店工作人员信息载体
	 * @throws RemoteException RMI异常
	 * 
	 */
	public HotelWorkerPO getSingleHotelWorker(String hotelWorkerID) throws RemoteException;
	
	
	/**
	 * @Description:获取所有酒店工作人员信息
	 * @return 所有酒店工作人员信息的列表
	 * @throws RemoteException RMI异常
	 * List<HotelWorkerPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:42:55
	 */
	public List<HotelWorkerPO> getAllHotelWorker() throws RemoteException;
	
	/**
	 * @Description 添加酒店工作人员
	 * @param newHotelWorkerPO 新酒店工作人员的信息载体
	 * @return 添加成功的该酒店工作人员
	 * @throws RemoteException RMI异常
	 * 
	 */
	public HotelWorkerPO add(HotelWorkerPO newHotelWorkerPO) throws RemoteException;

	/**
	 * 
	 * @Description： 修改酒店工作人员信息
	 * @param hotelWorkerPO 修正的酒店工作人员信息载体
	 * @return 是否修改成功
	 * @throws RemoteException RMI异常
	 */
	public ResultMessage modify(HotelWorkerPO hotelWorkerPO) throws RemoteException;
	

}
