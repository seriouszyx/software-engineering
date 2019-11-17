package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.GuestDataHelper;
import dataHelperImpl.GuestDataHelperImpl;
import po.GuestPO;
import utilities.Ciphertext;
import utilities.enums.ResultMessage;

public class GuestDataHelperImplTest {

	GuestDataHelper helper = null;
	Ciphertext code = null;
	
	@Before
	public void setUp() throws Exception {
		helper = new GuestDataHelperImpl();
		code  =  new Ciphertext();
		
	}
	
	@Ignore
	@Test
	public void testAdd() {
		LocalDate birthday = LocalDate.of(1997, 10, 1);
		String name = "高源";
		String phone  ="15951953939";
		String nickName = "hhjhjhj";
		String password  ="asdfghjkl";
		
		GuestPO guestPO = new GuestPO("", birthday, "", 
				code.encrypt(name), nickName,
				code.encrypt(password), code.encrypt(phone), 107);
		GuestPO tempGuestPO = helper.add(guestPO);
		
	}

	@Ignore
	@Test
	public void testModify() {
		GuestPO guestPO = new GuestPO("1000000000", LocalDate.of(1997, 8, 1), "home", 
				"B", "BC", "100000", "12345098761", 207);
		assertEquals(ResultMessage.SUCCESS,helper.modify(guestPO));
	}

	@Test
	public void testGetSingle() {
		GuestPO guestPO = helper.getSingle("1234567910");
		System.out.println(guestPO.getBirthday());
		
		assertEquals(LocalDate.of(2, 2, 2),guestPO.getBirthday());
//		assertEquals("学校",guestPO.getEnterprise());
//		assertEquals("董金玉",code.decode(guestPO.getName()));
//		assertEquals("kkkkkkkkk",code.decode(guestPO.getNickName()));
//		assertEquals("123456DJYF",code.decode(guestPO.getPassword()));
//		assertEquals("13523456789",code.decode(guestPO.getPhone()));
	}

	@Ignore
	@Test
	public void testGetAll() {
		List<GuestPO> list = helper.getAll();
		
		assertEquals(LocalDate.of(2016, 3, 13),list.get(1).getBirthday());
		assertEquals("学校",list.get(1).getEnterprise());
		assertEquals("张三",list.get(1).getName());
		assertEquals(233,list.get(1).getCredit(),0);
		
		assertEquals(LocalDate.of(1996, 1, 1),list.get(2).getBirthday());
		assertEquals("钢铁",list.get(2).getEnterprise());
		assertEquals("A",list.get(2).getName());
		assertEquals(100,list.get(2).getCredit(),0);
		
	}
}
