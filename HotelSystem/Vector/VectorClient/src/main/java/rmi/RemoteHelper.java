package rmi;

import java.rmi.Remote;

import dataService.dao.service.AccountDao;
import dataService.dao.service.CreditDao;
import dataService.dao.service.HotelDao;
import dataService.dao.service.HotelPromotionDao;
import dataService.dao.service.MarketPromotionDao;
import dataService.dao.service.MemberDao;
import dataService.dao.service.OrderDao;

/**
 * RMI
 */
public class RemoteHelper {

    private Remote remote;

    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance(){
        return remoteHelper;
    }

    private RemoteHelper(){
    }

    public void setRemote(Remote remote){
        this.remote = remote;
    }

    /* getDaoService 添加在下方  */
    public AccountDao getAccountDao(){
        return (AccountDao)remote;
    }

    public MemberDao getMemberDao(){
        return (MemberDao)remote;
    }

    public HotelDao getHotelDao() {
        return (HotelDao)remote;
    }
    
    public OrderDao getOrderDao() {
    	return (OrderDao)remote;
    }
    
    public HotelPromotionDao getHotelPromotionDao() {
    	return (HotelPromotionDao) remote;
    }
    
    public MarketPromotionDao getMarketPromotionDao() {
    	return (MarketPromotionDao) remote;
    }
    
    public CreditDao getCreditDao(){
    	return (CreditDao)remote;
    }
}
