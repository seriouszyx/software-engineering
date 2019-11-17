package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.HotelWorkerDataHelper;
import dataHelperImpl.HotelWorkerDataHelperImpl;
import po.HotelWorkerPO;
import utilities.Ciphertext;
import utilities.enums.ResultMessage;

public class HotelWorkerDataHelperImplTest {

	HotelWorkerDataHelper helper = null;
	Ciphertext code = null;
	
	@Before
	public void setUp() throws Exception {
		helper  = new HotelWorkerDataHelperImpl();
		code  =  new Ciphertext();
	}

	@Test
	public void testAdd() {
		String password  = "asdfghjkl123456";
		String hotelName = "桔子水晶酒店（南京新街口店）";
		
		String kk = code.encrypt(password);
		HotelWorkerPO hotelWorkerPO  = new HotelWorkerPO("", code.encrypt(password), hotelName); 
		helper.add(hotelWorkerPO);
		System.out.println(code.decode(kk));
	}

	@Test
	public void testModify() {
		HotelWorkerPO hotelWorkerPO  = new HotelWorkerPO("12345678", "111111","centerOfNanJing");
		assertEquals(ResultMessage.SUCCESS,helper.modify(hotelWorkerPO));
		
	}

	@Ignore
	@Test
	public void testDelete() {
		assertEquals(ResultMessage.SUCCESS,helper.delete("11110000"));
	}

	@Ignore
	@Test
	public void testGetSingle() {
		HotelWorkerPO hotelWorkerPO  = helper.getSingle("98765432");
		assertEquals("000000",hotelWorkerPO.getPassword());
		assertEquals("如家",hotelWorkerPO.getHotelName());
	}

	@Ignore
	@Test
	public void testGetAll() {
		List<HotelWorkerPO> list = helper.getAll();
		
		assertEquals("000000",list.get(2).getPassword());
		assertEquals("如家",list.get(2).getHotelName());
	}
}
