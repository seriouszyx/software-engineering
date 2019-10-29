package myTest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

public class TestAccount {
	
	private AccountBlService test ;
	private TestClient clientRunner ;
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new TestClient();
		/*注：必须先开Client再开Service插件*/
		test = AccountBlServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		clientRunner = null;
		test=null;
	}

	@Test
	public void testRegister() {
		//test member
		Assert.assertEquals("N00007", test.register("HuJinTao", "20160890z",true));
		Assert.assertEquals("N00008", test.register("WenJiaBao", "q204900q",true));
		Assert.assertEquals("N00009", test.register("BoXiLai", "qwertasdfg",true));
		Assert.assertEquals("FAIL"  , test.register("BoXiLai", "qwertasdfg",true));
		//test enterprise
		Assert.assertEquals("E00005", test.register("XinLang", "0009998",false));
		Assert.assertEquals("E00006", test.register("Tencent", "268889z",false));
		Assert.assertEquals("E00007", test.register("Baidu", "2mmmmm0z",false));
		Assert.assertEquals("FAIL"  , test.register("Baidu", "2mmmmm0z",false));
		Assert.assertEquals("FAIL"  , test.register("Tencent", "268889z",false));
		
		//error test
		Assert.assertEquals("FAIL"  , test.register("", "",false));
		Assert.assertEquals("FAIL"  , test.register("", "",true));
	}

	@Test
	public void testLogin() {
		//test member 
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "00123456"));
		Assert.assertEquals(AccountType.Member,test.login("N00001", "1234567"));
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "1234567")); 
		
		//test enterprise
		Assert.assertEquals(AccountType.Fail,test.login("E00001", "00123456"));
		Assert.assertEquals(AccountType.Member,test.login("E00001", "123456"));
		Assert.assertEquals(AccountType.Fail,test.login("E00001", "123456")); 
		
		//test hotel
		Assert.assertEquals(AccountType.Hotel,test.login("H00001", "123456"));
		
		//test marketer
		Assert.assertEquals(AccountType.Marketer,test.login("M00001", "00980808az"));
		
		//test manager
		Assert.assertEquals(AccountType.Manager,test.login("A00001", "00000001"));
		
		//error test 
		Assert.assertEquals(AccountType.Fail,test.login("QT0001", "00123456"));
		Assert.assertEquals(AccountType.Fail,test.login("G 0001", "0dqwwa56"));
		Assert.assertEquals(AccountType.Fail,test.login("", "0dqwwa56"));
		Assert.assertEquals(AccountType.Fail,test.login("0dqwwa56", ""));
	}

	@Test
	public void testLogout() {
		//test member
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("N00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("N00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("N00002"));
		//test enterprise
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("E00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("E00001"));
		
		//test hotel
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("H00001"));
		
		//test marketer
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("M00001"));
		
		 //test manager
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("A00001"));
	}


	@Test
	public void testFind() {
		//test member
		Assert.assertEquals("MaoZeDong",test.findAccount("N00001").getMemberName());
		
		//test enterprise
		Assert.assertEquals("Wanda",test.findAccount("E00001").getMemberName());
		
		//test hotel
		Assert.assertEquals("汉庭",test.findAccount("H00000").getMemberName());
		
		//test marketer
		Assert.assertEquals("Ivanca",test.findAccount("M00001").getMemberName());
		
		//test manager
		Assert.assertEquals("Administrator",test.findAccount("A00001").getMemberName());
	}
	
	
	@Test
	public void testModify() {
		//test member
		test.modifyPassword("N00003","99999999");
		Assert.assertEquals("99999999",test.findAccount("N00002").getPassword());
		//test enterprise
		test.modifyPassword("E00003","99999999");
		Assert.assertEquals("99999999",test.findAccount("E00003").getPassword());
		
		// hotel
		test.modifyPassword("H00003","99999999");
		Assert.assertEquals("99999999",test.findAccount("E00003").getPassword());
		
		//marketer
		test.modifyPassword("M00002","99999999");
		Assert.assertEquals("99999999",test.findAccount("M00002").getPassword());
		
		//manager
		test.modifyPassword("A00002","99999999");
		Assert.assertEquals("99999999",test.findAccount("A00002").getPassword());
		
	}


	@Test
	public void testInsert() {
		//test insert member
		test.insertAccount("guojimilan", "xiaoguoji", AccountType.Member);
		Assert.assertEquals(0,test.findAccount("N00009").getLogState());
		
		//test insert enterprise
		test.insertAccount("Watt", "England", AccountType.Enterprise);
		Assert.assertEquals(0,test.findAccount("E00008").getLogState());
		
		// hotel
		test.insertAccount("TrumpHotel", "american", AccountType.Hotel);
		Assert.assertEquals(0,test.findAccount("H00005").getLogState());
		
		//marketer
		test.insertAccount("JackJones", "Monica", AccountType.Marketer);
		Assert.assertEquals("Monica",test.findAccount("M00003").getPassword());
		
		//manager
		test.insertAccount("Admin3", "Admin", AccountType.Manager);
		Assert.assertEquals("Admin3",test.findAccount("A00003").getMemberName());
	}

	@Test
	public void testUpdate() {
		// test update member logState
		AccountVo vo = test.findAccount("N00001");
		vo.setLogState(1);
		test.updateAccount(vo);
		Assert.assertEquals(1, test.findAccount("N00001").getLogState());
		test.logout("N00001");
		//test update enterprise logState
		
		//test update hotel logState
	    vo = test.findAccount("H00001");
		vo.setLogState(1);
		test.updateAccount(vo);
		Assert.assertEquals(1, test.findAccount("H00001").getLogState());
		test.logout("H00001");
		
		//marketer
		vo = test.findAccount("M00001");
		vo.setLogState(1);
		test.updateAccount(vo);
		Assert.assertEquals(1, test.findAccount("M00001").getLogState());
		test.logout("M00001");
		
		//manager
		vo = test.findAccount("A00001");
		vo.setLogState(1);
		test.updateAccount(vo);
		Assert.assertEquals(1, test.findAccount("A00001").getLogState());
		test.logout("A00001");
	}
	

	@Test
	public void testDelete() {
		//test delete member
		test.deleteAccount("N00009");
		Assert.assertEquals(null, test.findAccount("N00009"));
		
		//enterprise
		test.deleteAccount("E00008");
		Assert.assertEquals(null, test.findAccount("E00008"));
		
		// hotel
		test.deleteAccount("H00005");
		Assert.assertEquals(null, test.findAccount("H00005"));
		
		//marketer
		test.deleteAccount("M00003");
		Assert.assertEquals(null, test.findAccount("M00003"));
		
		//manager
		test.deleteAccount("A00003");
		Assert.assertEquals(null, test.findAccount("A00003"));
		
		//error test 
		ResultMessage msg = test.deleteAccount("G00003");
		Assert.assertEquals(ResultMessage.FAIL, msg);
	}

}
