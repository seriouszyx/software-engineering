package dataService.dataHelper.impl;

import dataService.dataHelper.service.AccountDataHelper;
import dataService.dataHelper.service.CreditDataHelper;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.HotelDataHelper;
import dataService.dataHelper.service.HotelPromotionDataHelper;
import dataService.dataHelper.service.MarketPromotionDataHelper;
import dataService.dataHelper.service.MemberDataHelper;
import dataService.dataHelper.service.OrderDataHelper;

/**
 * Created by Administrator on 2016-11-13.
 */
public class DataFactoryImpl implements DataFactory {

    public AccountDataHelper getAccountDataHelper(){
        AccountDataHelper accountDao  = new AccountDataTxtHelper();
        return accountDao;
    }

    public MemberDataHelper getMemberDataHelper(){
        MemberDataHelper memberDao = new MemberDataTxtHelper();
        return memberDao;
    }

    public HotelDataHelper getHotelDataHelper(){
    	HotelDataHelper hotelDao = new HotelDataTxtHelper();
    	return hotelDao;
    }

    public OrderDataHelper getOrderDataHelper() {
    	OrderDataHelper orderDao = new OrderDataTxtHelper();
    	return orderDao;
    }
    
    public HotelPromotionDataHelper getHotelPromotionDataHelper(){
		HotelPromotionDataHelper hotelPromotionDao = new HotelPromotionDataTxtHelper();
		return hotelPromotionDao;
	}
    
    public MarketPromotionDataHelper getMarketPromotionDataHelper(){
    	MarketPromotionDataHelper marketPromotionDao = new MarketPromotionDataTxtHelper();
    	return marketPromotionDao;
    }
    
    public CreditDataHelper getCreditDataHelper(){
    	CreditDataHelper creditDao = new CreditDataTxtHelper();
    	return creditDao;
    }
    
}
