package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.SpecialSpanPromotionDataHelper;
import dataHelperImpl.SpecialSpanPromotionDataHelperImpl;
import po.SpecialSpanPromotionPO;

public class SpecialSpanPromotionDataHelperImplTest {

	List<SpecialSpanPromotionPO> list;
	SpecialSpanPromotionDataHelper helper;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<SpecialSpanPromotionPO>();
		helper = new SpecialSpanPromotionDataHelperImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHotelSpecialSpanPromotion() {
		list = helper.getHotelSpecialSpanPromotion("12345678");
		assertEquals("清明节", list.get(0).getPromotionName());
		assertEquals("双十一", list.get(1).getPromotionName());
		assertEquals("春节", list.get(2).getPromotionName());
	}

	@Test
	public void testGetWebSpecialSpanPromotion() {
		list = helper.getWebSpecialSpanPromotion();
		assertEquals("双十一",list.get(0).getPromotionName());
//		assertEquals("春节折扣",list.get(1).getPromotionName());
	}

	@Ignore //测试已通过,以免后面在数据库中重复添加数据，故ignore
	@Test
	public void testAddSpecialSpanPromotion() {
		SpecialSpanPromotionPO po = new SpecialSpanPromotionPO();
		po.setUserID("99999999");
		po.setPromotionName("春节折扣");
		po.setDiscount(0.9);
		po.setStartDate(LocalDate.of(2016, 12, 30));
		po.setEndDate(LocalDate.of(2017, 1, 5));
		helper.addSpecialSpanPromotion(po);
		
		list = helper.getHotelSpecialSpanPromotion("99999999");
		assertEquals("春节折扣", list.get(1).getPromotionName());
	}

	@Test
	public void testUpdateSpecialSpanPromotion() {
		SpecialSpanPromotionPO po = new SpecialSpanPromotionPO();
		po.setUserID("12345678");
		po.setPromotionName("春节");
		po.setDiscount(0.8);
		po.setStartDate(LocalDate.of(2016, 11, 11));
		po.setEndDate(LocalDate.of(2017, 1, 6));
		
		helper.updateSpecialSpanPromotion(po);
		list = helper.getHotelSpecialSpanPromotion("12345678");
		assertEquals(0.8, list.get(2).getDiscount(),0.1);
	}

	@Ignore //方法已经通过检测，避免重复删除，故ignore
	@Test
	public void testDeleteSpecialSpanPromotion() {
		helper.deleteSpecialSpanPromotion("99999999", "春节折扣");
		list = helper.getWebSpecialSpanPromotion();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		assertEquals(1, list.size(),0.1);
	}

}
