package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.OrderDataHelper;
import dataHelperImpl.OrderDataHelperImpl;
import po.OrderPO;
import utilities.Ciphertext;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

public class OrderDataHelperImplTest {

	OrderDataHelper helper = null;
	
	Ciphertext ciphertext = null;
	
	@Before
	public void setUp() throws Exception {
		helper = new OrderDataHelperImpl();
		ciphertext = new Ciphertext();
	}

	@Ignore
	@Test
	public void testAdd() {
		
		
		final LocalDateTime createTime = LocalDateTime.of(2016, 12, 17, 2, 56);
//		final LocalDateTime checkInTime = LocalDateTime.of(2016, 7, 16, 18, 21);
//		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 7, 18, 11, 47);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 12, 22, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 12, 23, 12, 00);

		final OrderState orderState = OrderState.UNEXECUTED;
		final RoomType roomType = RoomType.DOUBLE_BED;
		final String orderID = formateRandomNumber((int) (Math.random() * 10000)) + formateDate(createTime.toLocalDate());
		
		final String name = ciphertext.encrypt("冯俊杰");
		final String phone = ciphertext.encrypt("15205153110");
		
		OrderPO orderPO = new OrderPO(orderID, "1234567900", "98765443", "南京金陵饭店", "汉中路2号", 869, 869,
				createTime, null, null, expectExecuteTime, expectLeaveTime, orderState, false, false,
				roomType, 1, "", 2, name, phone, "无", 4.5, "地理位置特别好，交通、购物、吃饭、去景点都特别方便");
		
		assertEquals(ResultMessage.SUCCESS,helper.add(orderPO));
	}

	@Ignore
	@Test
	public void testSetState() {
		assertEquals(ResultMessage.SUCCESS,helper.setState("13342016112", OrderState.ABNORMAL));
	}

	@Ignore
	@Test
	public void testCheckIn() {
		LocalDateTime checkInTime = LocalDateTime.now();
		LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 12, 24, 13, 55, 1);
		assertEquals(ResultMessage.SUCCESS, helper.setState("435620161217", OrderState.EXECUTED));
		assertEquals(ResultMessage.SUCCESS,helper.setCheckIn("435620161217", "4777", checkInTime, expectLeaveTime));
	}
	
//	@Ignore
	@Test
	public void testCheckOut() {
		LocalDateTime checkOutTime = LocalDateTime.now();
		assertEquals(ResultMessage.SUCCESS,helper.setCheckOut("435620161217", checkOutTime));
	}
	
	@Ignore
	@Test
	public void testSetComment() {
		assertEquals(ResultMessage.SUCCESS,helper.setEvaluation("13342016112", 3, "notGood"));
	}

	@Ignore
	@Test
	public void testGetSingleOrder() {
		OrderPO orderPO = helper.getSingleOrder("123420161201");
		
		assertEquals("1000000001",orderPO.getGuestID());
		assertEquals("98765432",orderPO.getHotelID());
		assertEquals("学校",orderPO.getHotelName());
	}

	@Ignore
	@Test
	public void testGetAllOrderOfGuest() {
		List<OrderPO> list = helper.getAllOrderOfGuest("1000000001");
		
		assertEquals("1000000001",list.get(0).getGuestID());
		assertEquals("98765432",list.get(0).getHotelID());
		assertEquals("学校",list.get(0).getHotelName());
		assertEquals(LocalDateTime.of(2016, 1, 1, 19, 41,17),list.get(0).getCreateTime());
	}

	@Ignore
	@Test
	public void testGetAllOrderOfHotel() {
		List<OrderPO> list = helper.getAllOrderOfHotel("98765432");
		
		assertEquals("1000000001",list.get(0).getGuestID());
		assertEquals("98765432",list.get(0).getHotelID());
		assertEquals("学校",list.get(0).getHotelName());
		assertEquals(LocalDateTime.of(2016, 1, 1, 19, 41,17),list.get(0).getCreateTime());
	}

	@Ignore
	@Test
	public void testGetAbnormal() {
		List<OrderPO> list = helper.getAbnormal();
		
		assertEquals("1000000000",list.get(0).getGuestID());
		assertEquals("12345678",list.get(0).getHotelID());
		assertEquals("center",list.get(0).getHotelName());
		assertEquals(LocalDateTime.of(2016, 9, 1, 2, 34,51),list.get(0).getCreateTime());
	}

	@Ignore
	@Test
	public void testGetUnexecuted() {
		List<OrderPO> list = helper.getUnexecuted();
		
		assertEquals("1000000001",list.get(0).getGuestID());
		assertEquals("98765432",list.get(0).getHotelID());
		assertEquals("学校",list.get(0).getHotelName());
		assertEquals(LocalDateTime.of(2016, 1, 1, 19, 41,17),list.get(0).getCreateTime());
	}
	
	
	private String formateRandomNumber(int randomNumber) {
		if (randomNumber >= 1000 && randomNumber <= 9999) {
			return String.valueOf(randomNumber);
		} else if (randomNumber > 100 && randomNumber <= 999) {
			return "0" + String.valueOf(randomNumber);
		} else if (randomNumber > 10 && randomNumber <= 99) {
			return "00" + String.valueOf(randomNumber);
		} else {
			return "000" + String.valueOf(randomNumber);
		}
	}
	
	private String formateDate(LocalDate localDate) {
		String temp = localDate.toString();
		return temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
	}
}
