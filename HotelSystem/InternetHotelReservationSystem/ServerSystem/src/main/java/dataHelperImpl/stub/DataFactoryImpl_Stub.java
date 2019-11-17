package dataHelperImpl.stub;

import dataHelper.AddressDataHelper;
import dataHelper.CreditDataHelper;
import dataHelper.GuestDataHelper;
import dataHelper.HotelWorkerDataHelper;
import dataHelper.MarketDataHelper;
import dataHelper.OrderDataHelper;
import dataHelper.WebManagerDataHelper;
import dataHelper.WebMarketerDataHelper;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/11/30
 * 
 * 以工厂的方式得到每个DataHelper的实例
 * Factory 单例化
 */
public final class DataFactoryImpl_Stub{

	private static DataFactoryImpl_Stub dataFactory = new DataFactoryImpl_Stub();
	
	private AddressDataHelper addressDataHelper = new AddressDataHelperImpl_Stub(); 
	
	private CreditDataHelper creditDataHelper = new CreditDataHelperImpl_Stub();
	
	private GuestDataHelper guestDataHelper = new GuestDataHelperImpl_Stub();
	
	private HotelWorkerDataHelper hotelWorkerDataHelper = new HotelWorkerDataHelperImpl_Stub();
	
	private MarketDataHelper marketDataHelper = new MarketDataHelperImpl_Stub();
	
	private OrderDataHelper orderDataHelper = new OrderDataHelperImpl_Stub();
	
	private WebManagerDataHelper webManagerDataHelper = new WebManagerDataHelperImpl_Stub();
	
	private WebMarketerDataHelper webMarketerDataHelper = new WebMarketerDataHelperImpl_Stub();
	
//	private HotelFixedPromotionDataHelper hotelFixedPromotionDataHelper = new HotelFixedPromotionDataHelperImpl();
	
//	private SpecialSpanPromotionDataHelperImpl specialSpanPromotionDataHelper = new SpecialSpanPromotionDataHelperImpl();
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * 
	 * 为了单例化，私有化构造方法
	 */
	private DataFactoryImpl_Stub() {	

	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的DataFactory
	 * 
	 * 单例化，提供访问唯一对象的接口
	 */
	public static DataFactoryImpl_Stub getInstance() {
		return dataFactory;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的AddressDataHelper
	 */
	public AddressDataHelper getAddressDataHelper() {
		return addressDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30 
	 * @return 单例化的CreditDataHelper
	 */
	public CreditDataHelper getCreditDataHelper() {
		return creditDataHelper;
	}

	/**
	 * 
	 * @return 单例化的GuestDataHelper
	 */
	public GuestDataHelper getGuestDataHelper() {
		return guestDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的HotelWorkerDataHelper
	 */
	public HotelWorkerDataHelper getHotelWorkerDataHelper() {
		return hotelWorkerDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的MarketDataHelper
	 */
	public MarketDataHelper getMarketDataHelper() {
		return marketDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的OrderDataHelper
	 */
	public OrderDataHelper getOrderDataHelper() {
		return orderDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的WebManagerDataHelper
	 */
	public WebManagerDataHelper getWebManagerDataHelper() {
		return webManagerDataHelper;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/30
	 * @return 单例化的WebMarketerDataHelper
	 */
	public WebMarketerDataHelper getWebMarketerDataHelper() {
		return webMarketerDataHelper;
	}
	
	
//	public HotelFixedPromotionDataHelper getHotelFixedPromotionDataHelper(){
//		return hotelFixedPromotionDataHelper;
//	}
//	
//	public SpecialSpanPromotionDataHelper getSpecialSpanPromotionDataHelper(){
//		return specialSpanPromotionDataHelper;
//	}

}
