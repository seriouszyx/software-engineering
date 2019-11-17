package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataHelper.HotelFixedPromotionDataHelper;
import dataHelperImpl.HotelFixedPromotionDataHelperImpl;
import po.HotelFixedPromotionPO;
import utilities.enums.PromotionType;

public class HotelFixedPromotionDataHelperImplTest {

	List<HotelFixedPromotionPO> list;
	HotelFixedPromotionDataHelper helper;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<HotelFixedPromotionPO>();
		helper = new HotelFixedPromotionDataHelperImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHotelFixedPromotion() {
		list = helper.getHotelFixedPromotion("12345678");
		assertEquals(PromotionType.HOTEL_BIRTHDAY,list.get(0).getPromotionType());
		assertEquals(PromotionType.HOTEL_ENTERPRISE, list.get(1).getPromotionType());
	}

	@Test
	public void testUpdateHotelFixedPromotion() {
		HotelFixedPromotionPO po = new HotelFixedPromotionPO();
		po.setHotelID("12345678");
		po.setDiscount(0.6);
		po.setPromotionType(PromotionType.HOTEL_ENTERPRISE);
		helper.updateHotelFixedPromotion(po);
		
		list = helper.getHotelFixedPromotion("12345678");
		assertEquals(0.6, list.get(1).getDiscount(),0.1);
	}

}
