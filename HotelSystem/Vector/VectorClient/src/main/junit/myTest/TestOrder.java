package myTest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import vo.OrderVo;

public class TestOrder {
	private OrderBlService test;
	private TestClient clientRunner;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new TestClient();
		test = OrderBlServiceImpl.getInstance() ;
	}
	
	@Test 
	public void testSubmit() {
		
		//N00012信用值100,H00003酒店可用单人房10,客户入住人数未填 (0) 
		ResultMessage msg = test.submit("N00012", "2016-09-30 00:00:00", "H00003",
				0, RoomType.SINGLE, 1, 1, false);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
		//N00012信用值100,H00003酒店可用单人房10
		msg = test.submit("N00012", "2016-09-30 00:00:00", "H00003", 1, RoomType.SINGLE
				, 1, 1, false);
		Assert.assertEquals(ResultMessage.SUCCEED,msg);
		
		//N00013信用值0,H00003酒店可用单人房10
		msg = test.submit("N00013", "2016-09-30 00:00:00", "H00003", 1, RoomType.SINGLE
				, 1, 1, false);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
		//N00014信用值50,H00023酒店可用单人房0
		msg = test.submit("N00014", "2016-09-30 00:00:00", "H00023", 1, RoomType.SINGLE
				, 1, 1, false);
		Assert.assertEquals(ResultMessage.FAIL,msg);
	}
	
	@Test
	public void testGetOrderList() {
		String ID= "E00005" ;
		List<OrderVo> list0 = test.getOrdersInConditionByMember(ID, null);
		Assert.assertEquals(null, list0);
		
		List<OrderVo> list1 = test.getOrdersInConditionByMember(ID, OrderCondition.WAITING);
		Assert.assertEquals(2, list1.size());
		
		List<OrderVo> list2 = test.getOrdersInConditionByMember(ID, OrderCondition.EXECUTED);
		Assert.assertEquals(5, list2.size());
		
		List<OrderVo> list3 = test.getOrdersInConditionByMember(ID, OrderCondition.ABNORMAL);
		Assert.assertEquals(0, list3.size());
		
		List<OrderVo> list4 = test.getOrdersInConditionByMember(ID, OrderCondition.CANCELED);
		Assert.assertEquals(0, list4.size());
		
		List<OrderVo> list5 = test.getNotExecutedOrderByWebSite();
		Assert.assertEquals(150, list5.size());
		
		List<OrderVo> list6 = test.getAbnormalByWebSite();
		Assert.assertEquals(202, list5.size());
	}

	@Test
	public void testGetOrderCondition() {
		Assert.assertEquals("WAITING", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testGetMemberId() {
		Assert.assertEquals("N00007", test.getMemberId("2016121812345"));
	}

	@Test
	public void testGetMemberName() {
		Assert.assertEquals("HuJinTao", test.getMemberName("2016121812345"));
	}

	@Test
	public void testGetCreateTime() {
		Assert.assertEquals("2016-12-18 00:00:00", sdf.format(test.getCreateTime("2016121812345")));
	}

	@Test
	public void testGetCheckInTime() {
		Assert.assertEquals("1970-01-01 00:00:00", sdf.format(test.getCheckInTime("2016121812345")));
	}

	@Test
	public void testGetCheckOutTime() {
		Assert.assertEquals("1970-01-01 00:00:00", sdf.format(test.getCheckOutTime("2016121812345")));
	}

	@Test
	public void testGetHotelName() {
		Assert.assertEquals("布丁酒店", test.getHotelName("2016121812345"));
	}

	@Test
	public void testGetHotelId() {
		Assert.assertEquals("H00001", test.getHotelId("2016121812345"));
	}

	@Test
	public void testGetRoomType() {
		Assert.assertEquals(RoomType.DOUBLE, test.getRoomType("2016121812345"));
	}

	@Test
	public void testGetNumOfRoom() {
		Assert.assertEquals(2, test.getNumOfRoom("2016121812345"));
	}

	@Test
	public void testGetNumOfGuest() {
		Assert.assertEquals(4, test.getNumOfGuest("2016121812345"));
	}

	@Test
	public void testGetChildExist() {
		Assert.assertEquals(false, test.getChildExist("2016121812345"));
	}

	@Test
	public void testGetOriginalPrice() {
		Assert.assertEquals(130, test.getOriginalPrice("2016121812345"));
	}

	@Test
	public void testGetDiscount() {
		Assert.assertEquals(0.5, test.getDiscount("2016121812345"), 0.1);
	}

	@Test
	public void testGetDiscountedPrice() {
		Assert.assertEquals(260, test.getDiscountedPrice("2016121812345"));
	}
//////
	
	@Test
	public void testSetToAbnormal() {
		Assert.assertEquals("ABNORMAL", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testCancel() {
		//test1 
		Assert.assertEquals("CANCEL", test.getOrderCondition("2016121812345"));
		
		
		//test2
		Assert.assertEquals("CANCEL", test.getOrderCondition("2017010112345"));
	}

	@Test
	public void testCheckIn() {
		 //酒店仅剩一间房间
		ResultMessage msg = test.checkIn("2016121812345"); 
		Assert.assertEquals(ResultMessage.SUCCEED,msg);
		Assert.assertEquals(OrderCondition.EXECUTING, test.getOrderCondition("2016121812345"));
		
		 msg = test.checkIn("2016121812##45");
		 Assert.assertEquals(ResultMessage.FAIL,msg);
		 
		 msg = test.checkIn("2016121812346");
		 Assert.assertEquals(ResultMessage.FAIL,msg);
	}

	@Test
	public void testAbnormalCheckIn() {
		Assert.assertEquals("EXCUTING", test.getOrderCondition("201#####12345"));
		Assert.assertEquals("EXCUTING", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testCheckOut() {
		Assert.assertEquals("EXCUTED", test.getOrderCondition("201####@#$345"));
		Assert.assertEquals("EXCUTED", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testRevoke() {
		test.revoke("20161205154409", 0.50);
		Assert.assertEquals("REVOKED", test.getOrderCondition("2016121812345"));
		
		test.revoke("20161205070459", 0.50);
		Assert.assertEquals("REVOKED", test.getOrderCondition("2016121812345"));
		
		test.revoke("20161205154604", 1);
		Assert.assertEquals("REVOKED", test.getOrderCondition("2016121812341"));
		
		//err test 
		ResultMessage msg = test.revoke("", 0);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
	    msg = test.revoke("2017170265898", 0);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
		 msg = test.revoke("2016#&au%@#$", 0);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
	    msg = test.revoke("33184515646512534", 0);
		Assert.assertEquals(ResultMessage.FAIL,msg);
		
		 msg = test.revoke("2016090935611", 0);
	 	Assert.assertEquals(ResultMessage.FAIL,msg);
		
		 msg = test.revoke("2016081953625", 0);
	  	 Assert.assertEquals(ResultMessage.FAIL,msg);
			
		 msg = test.revoke("2016112657014", 0);
	     Assert.assertEquals(ResultMessage.FAIL,msg);
			
	}
	
	@Test
	public void testSetToFinished() {
		Assert.assertEquals("FINISHED", test.getOrderCondition("2016121812345"));
	}

}
