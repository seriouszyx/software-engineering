package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.HotelDataHelper;
import dataHelperImpl.HotelDataHelperImpl;
import po.HotelPO;

public class HotelDataHelperImplTest {

	List<HotelPO> list;
	HotelDataHelper helper;

	@Before
	public void setUp() throws Exception {
		helper = new HotelDataHelperImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHotels() {

		list = helper.getHotels("南京", "仙林中心");

		assertEquals("如家", list.get(0).getHotelName());
		assertEquals("7天", list.get(1).getHotelName());
	}

	@Test
	public void testGetHotelInfo() {
		HotelPO po = helper.getHotelInfo("12345678");
		assertEquals("桔子", po.getHotelName());
	}

	@Test
	public void testUpdateHotelInfo() {
		HotelPO po = new HotelPO();
		po.setHotelID("98765441");
		po.setHotelName("南京英尊假日酒店");
		po.setCity("南京");
		po.setCircle("仙林中心");
		po.setAddress("仙林大道168号");
		po.setLevel("3");
		po.setScore(5);
		po.setIntroduction("2013年开业，近九乡河东路，距地铁站步行3分钟");
		po.setEquipment("24小时热水，拖鞋，独立淋浴间，吹风机，免费洗漱用品，多规格电源插座，中央空调，闹钟，针线包，遮光窗帘，手动窗帘，电话，房间内高速上网，客房WIFI覆盖免费，液晶电视，电热水壶，免费瓶装水，唤醒服务");
		po.setCommentsNum(2);
		helper.updateHotelInfo(po);
	}

	@Ignore //避免重复添加
	@Test
	public void testAddHotelInfo() {
		HotelPO po = new HotelPO();
		po.setHotelID("12345677");
		po.setHotelName("桔子水晶");
		po.setCity("南京");
		po.setCircle("马群");
		helper.addHotelInfo(po);
		po = helper.getHotelInfo("12345677");
		assertEquals("桔子水晶", po.getHotelName());
	}

}
