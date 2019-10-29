package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import common.AccountType;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.impl.AccountDaoImpl;
import dataService.dao.impl.CreditDaoImpl;
import dataService.dao.impl.HotelDaoImpl;
import dataService.dao.impl.HotelPromotionDaoImpl;
import dataService.dao.impl.MarketPromotionDaoImpl;
import dataService.dao.impl.MemberDaoImpl;
import dataService.dao.impl.OrderDaoImpl;
import dataService.dao.service.AccountDao;
import dataService.dao.service.CreditDao;
import dataService.dao.service.HotelDao;
import dataService.dao.service.HotelPromotionDao;
import dataService.dao.service.MarketPromotionDao;
import dataService.dao.service.MemberDao;
import dataService.dao.service.OrderDao;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.BusinessProPo;
import po.CompanyProPo;
import po.HotelPo;
import po.LevelPo;
import po.OrderPo;
import po.RoomPromotionPo;
import vo.AccountVo;
import vo.CreditRecordVo;
import vo.MemberVo;
import vo.OrderVo;

/**
 * RMI
 */
public class DataRemoteObject extends UnicastRemoteObject
implements AccountDao,MemberDao,HotelDao,OrderDao,HotelPromotionDao,MarketPromotionDao,CreditDao{


	private static final long serialVersionUID = 1L;

    private AccountDao accountDao;
    private MemberDao memberDao ;
    private HotelDao hotelDao;
    private OrderDao orderDao;
    private HotelPromotionDao hotelPromotionDao;
    private MarketPromotionDao marketPromotionDao;
    private CreditDao creditDao;
    
    protected DataRemoteObject() throws RemoteException{
        accountDao = AccountDaoImpl.getInstance();
        memberDao = MemberDaoImpl.getInstance();
        hotelDao = HotelDaoImpl.getInstance();
        orderDao = OrderDaoImpl.getInstance();
        hotelPromotionDao = HotelPromotionDaoImpl.getInstance();
        marketPromotionDao = MarketPromotionDaoImpl.getInstance();
        creditDao = CreditDaoImpl.getInstance();
    }


    /* AccountDao 接口方法 */
    public AccountType login(String id, String password)  throws RemoteException {
        return accountDao.login(id,password);
    }

    public ResultMessage logout(String id) throws RemoteException{
        return accountDao.logout(id);
    }

    public ResultMessage modifyPassword(String id, String newPassword)
    		throws RemoteException{
        return accountDao.modifyPassword(id,newPassword);
    }

    public AccountVo findAccount(String id) throws RemoteException{
        return accountDao.findAccount(id);
    }

    public String insertAccount(String name,String password,AccountType type)
    		throws RemoteException{
        return accountDao.insertAccount(name,password,type);
    }

    public ResultMessage updateAccount(AccountVo vo) throws RemoteException{
        return accountDao.updateAccount(vo);
    }

    public ResultMessage deleteAccount(String id) throws RemoteException{
        return accountDao.deleteAccount(id);
    }

    /* MemberDao接口方法  */

    public int getCredit(String id)throws RemoteException {
    	return memberDao.getCredit(id);
    }

    public ResultMessage chargeCredit(String id, int amount)throws RemoteException {
    	return memberDao.chargeCredit(id, amount);
    }

    public MemberVo getInfo(String id)throws RemoteException {
    	return memberDao.getInfo(id);
    }

    public ResultMessage modifyInfo(MemberVo vo)throws RemoteException {
    	return memberDao.modifyInfo(vo);
    }

    public boolean checkVip(int credit) throws RemoteException{
    	return memberDao.checkVip(credit);
    }

    /*HotelDao 接口方法*/
   	public ResultMessage addHotelPO(HotelPo po) throws RemoteException{
   		return hotelDao.addHotelPO(po);
   	}

   	public ResultMessage updateHotelList(HotelPo po) throws RemoteException{
   		return hotelDao.updateHotelList(po);
   	}

   	public ResultMessage deleteHotelPO(String hotelId) throws RemoteException{
   		return hotelDao.deleteHotelPO(hotelId);
   	}

   	public HotelPo findHotel(String hotelId) throws RemoteException{
   		return hotelDao.findHotel(hotelId);
   	}

   	public List<HotelPo> keyFind(String key) throws RemoteException{
   		return hotelDao.keyFind(key);
   	}

   	public ResultMessage updateComment(HotelPo po) throws RemoteException{
   		return hotelDao.updateComment(po);
   	}

   	public ResultMessage initHotelTypeRoom(String hotelId, RoomType type, int number, int price) throws RemoteException {
   		return hotelDao.initHotelTypeRoom(hotelId, type, number, price);
   	}
   	
   	public List<String> getProvinceList() throws RemoteException {
		return hotelDao.getProvinceList();
	}

	public List<String> getCityList(String province) throws RemoteException {
		return hotelDao.getCityList(province);
	}

	public List<String> getBusinessList(String province, String city) throws RemoteException {
		return hotelDao.getBusinessList(province, city);
	}

	public int getReadyRoom(String hotelId, RoomType type) throws RemoteException {
		return hotelDao.getReadyRoom(hotelId, type);
	}

	public ResultMessage updateOrderedRoom(String hotelId, RoomType type, int number, boolean isCheckIn)
			throws RemoteException {
		return hotelDao.updateOrderedRoom(hotelId, type, number, isCheckIn);
	}
	
   	/*HotelPromotionDao 接口方法*/
	public ResultMessage addActPromotion(String hotelId, ActivityPromotionPo po) throws RemoteException{
		return hotelPromotionDao.addActPromotion(hotelId, po);
	}
	
	public ResultMessage upActPromotion(String hotelId, ActivityPromotionPo po) throws RemoteException{
		return hotelPromotionDao.upActPromotion(hotelId, po);
	}

	public ResultMessage delActPromotion(String hotelId, ActivityPromotionPo po) throws RemoteException{
		return hotelPromotionDao.delActPromotion(hotelId, po);
	}
	
	public List<String> getActProList(String hotelId) throws RemoteException{
		return hotelPromotionDao.getActProList(hotelId);
	}

	public ResultMessage upBirthPromotion(String hotelId, BirthdayProPo po) throws RemoteException{
		return hotelPromotionDao.upBirthPromotion(hotelId, po);
	}

	public BirthdayProPo getBirthPromotion(String hotelId) throws RemoteException{
		return hotelPromotionDao.getBirthPromotion(hotelId);
	}

	public ResultMessage updateCooperPro(String hotelId, CompanyProPo po) throws RemoteException{
		return hotelPromotionDao.updateCooperPro(hotelId, po);
	}

	public CompanyProPo getCooperPro(String hotelId) throws RemoteException{
		return hotelPromotionDao.getCooperPro(hotelId);
	}

	public ResultMessage upRoomPromotion(String hotelId, RoomPromotionPo po) throws RemoteException{
		return hotelPromotionDao.upRoomPromotion(hotelId, po);
	}

	public RoomPromotionPo getRoomPromotion(String hotelId) throws RemoteException{
		return hotelPromotionDao.getRoomPromotion(hotelId);
	}


   	   	/*OrderDao 接口方法*/

	public ResultMessage insertOrder(OrderPo po) throws RemoteException {
		return orderDao.insertOrder(po);
	}

	public OrderPo findOrder(String orderId) throws RemoteException {
		return orderDao.findOrder(orderId);
	}

	public ResultMessage updateOrder(OrderPo po) throws RemoteException {
		return orderDao.updateOrder(po);
	}

	public ResultMessage deleteOrder(String orderId) throws RemoteException {
		return orderDao.deleteOrder(orderId);
	}

	public List<OrderPo> getAllByHotel(String hotelId) throws RemoteException {
		return orderDao.getAllByHotel(hotelId);
	}

	public List<OrderPo> getAllByMember(String memberId) throws RemoteException {
		return orderDao.getAllByMember(memberId);
	}

	//网站营销人员查看未执行订单
	public List<OrderPo> getNotExecuted() throws RemoteException {
		return orderDao.getNotExecuted();
	}

	//网站营销人员查看异常订单
	public List<OrderPo> getAbnormal() throws RemoteException {
		return orderDao.getAbnormal();
	}


	/*MarketPromotionDao 接口方法*/
	public ResultMessage addActivity(ActivityPromotionPo po) throws RemoteException{
		return marketPromotionDao.addActivity(po);
	}
	
	public ResultMessage updateActivity(ActivityPromotionPo po) throws RemoteException{
		return marketPromotionDao.updateActivity(po);
	}

	public List<String> getActivity() throws RemoteException{
		return marketPromotionDao.getActivity();
	}

	public ResultMessage updateLevelRule(List<LevelPo> list) throws RemoteException{
		return marketPromotionDao.updateLevelRule(list);
	}

	public ResultMessage deleteActivity(ActivityPromotionPo po) throws RemoteException {
		return marketPromotionDao.deleteActivity(po);
	}

	public List<LevelPo> getLevelList() throws RemoteException {
		return marketPromotionDao.getLevelList();
	}

	public ResultMessage updateBusiness(BusinessProPo po) throws RemoteException {
		return marketPromotionDao.updateBusiness(po);
	}

	public ResultMessage deleteBusiness(BusinessProPo po) throws RemoteException {
		return marketPromotionDao.deleteBusiness(po);
	}

	public List<BusinessProPo> getBusinessList() throws RemoteException {
		return marketPromotionDao.getBusinessList();
	}
	
	/* CreditDao 接口方法 */
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo)
			throws RemoteException{
		return creditDao.addCreditByOrder(id, amount, vo);
	}
		
	public List<CreditRecordVo> getCreditRecordList(String id)throws RemoteException{
		return creditDao.getCreditRecordList(id);
	}
}