package dataHelperImpl.dataHelperImplTest;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataHelper.RoomDataHelper;
import dataHelperImpl.RoomDataHelperImpl;
import po.RoomInfoPO;
import utilities.enums.RoomType;
import vo.RoomInfoVO;

public class RoomDataHelperImplTest {

	List<RoomInfoPO> list;
	RoomDataHelper helper;


	@Before
	public void setUp() throws Exception {
		helper = new RoomDataHelperImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateRoomInfo() {
		RoomInfoVO vo = new RoomInfoVO();
		vo.hotelID = "98765441";
		vo.price = 300;
		vo.roomNum = 20;
		vo.remainNum = 20;
		vo.roomType = RoomType.SINGLE_BED;
		RoomInfoPO po = new RoomInfoPO(vo);
		
		helper.updateRoomInfo(po);
	}
	
	

}
