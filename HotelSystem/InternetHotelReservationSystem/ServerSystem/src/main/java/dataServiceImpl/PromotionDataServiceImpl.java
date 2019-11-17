package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.AddressDataHelper;
import dataHelper.HotelFixedPromotionDataHelper;
import dataHelper.SpecialSpanPromotionDataHelper;
import dataHelperImpl.AddressDataHelperImpl;
import dataHelperImpl.HotelFixedPromotionDataHelperImpl;
import dataHelperImpl.SpecialSpanPromotionDataHelperImpl;
import dataService.promotionDataService.PromotionDataService;
import po.AddressPO;
import po.HotelFixedPromotionPO;
import po.SpecialSpanPromotionPO;
import utilities.enums.ResultMessage;

public class PromotionDataServiceImpl extends UnicastRemoteObject implements PromotionDataService{

	private static final long serialVersionUID = -1787757905596672195L;
	
	private HotelFixedPromotionDataHelper hotelFixedPromotionDataHelper;
	private SpecialSpanPromotionDataHelper specialSpanPromotionDataHelper;
	private AddressDataHelper addressDataHelper;
	
	public PromotionDataServiceImpl() throws RemoteException {
		super();
		hotelFixedPromotionDataHelper = new HotelFixedPromotionDataHelperImpl();
		specialSpanPromotionDataHelper = new SpecialSpanPromotionDataHelperImpl();
		addressDataHelper = new AddressDataHelperImpl();
	}


	@Override
	public List<HotelFixedPromotionPO> getHotelFixedPromotion(String hotelWorkerID) throws RemoteException {
		return hotelFixedPromotionDataHelper.getHotelFixedPromotion(hotelWorkerID);
	}


	@Override
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionPO hotelFixedPromotionPO) throws RemoteException {
		return hotelFixedPromotionDataHelper.updateHotelFixedPromotion(hotelFixedPromotionPO);
	}

	@Override
	public List<SpecialSpanPromotionPO> getHotelSpecialSpanPromotion(String hotelID) throws RemoteException {
		return specialSpanPromotionDataHelper.getHotelSpecialSpanPromotion(hotelID);
	}


	@Override
	public List<SpecialSpanPromotionPO> getWebSpecialSpanPromotion() throws RemoteException {
		return specialSpanPromotionDataHelper.getWebSpecialSpanPromotion();
	}


	@Override
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) throws RemoteException {
		return specialSpanPromotionDataHelper.addSpecialSpanPromotion(specialSpanPromotionPO);
	}


	@Override
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) throws RemoteException {
		return specialSpanPromotionDataHelper.updateSpecialSpanPromotion(specialSpanPromotionPO);
	}

	@Override
	public ResultMessage deleteSpecialSpanPromotion(String userID,String promotionName)throws RemoteException {
		return specialSpanPromotionDataHelper.deleteSpecialSpanPromotion(userID, promotionName);
	}

	@Override
	public List<AddressPO> getSpecialCirclePromotion(String city) throws RemoteException {
		return addressDataHelper.getAll(city);
	}


	@Override
	public ResultMessage updateSepecialCirclePromotion(AddressPO addressPO) throws RemoteException {
		return addressDataHelper.modifyDiscout(addressPO);
	}


	@Override
	public double getSpecialCircleDiscount(String city, String cycle) throws RemoteException {
		return addressDataHelper.getDiscout(city, cycle);
	}

}
