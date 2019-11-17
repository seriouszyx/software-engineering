package dataHelperImpl.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataHelper.GuestDataHelper;
import po.GuestPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * @lastChangedBy Harvey Gong
 * @ updateTime 2016/12/5
 *
 */
public class GuestDataHelperImpl_Stub implements GuestDataHelper {


	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param guestPO guestInfo载体
	 * @return guestPO 是否成功添加到数据库中
	 */
	public GuestPO add(GuestPO guestPO) {
		LocalDate birthday = LocalDate.of(1995, 1, 1);
		return new GuestPO("1234567890", birthday, "school", "zhangsan", "xiaosan", "000000", "13523456789", 100);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param guestPO guestInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定guestInfo
	 */
	public ResultMessage modify(final GuestPO guestPO) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param guestID 客户ID
	 * @return GuestPO 数据库中的指定guestInfo载体
	 */
	public GuestPO getSingle(final String guestID) {
		
		LocalDate birthday = LocalDate.of(1995, 1, 1);
		
		return new GuestPO("1234567890", birthday, "school", "zhangsan", "xiaosan", "000000", "13523456789", 100);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return List<GuestPO> 获取所有guestInfo载体
	 */
	public List<GuestPO> getAll() {
		List<GuestPO> list = new ArrayList<GuestPO>();
		
		list.add(new GuestPO("1234567890", LocalDate.of(1995, 1, 1), "school", "zhangsan", "xiaosan",
				"000000", "13523456789",100));
		list.add(new GuestPO("1234567891", LocalDate.of(1995, 1, 2), "school1", "zhangsan1", "xiaosan1",
				"000001", "13523456788",100));
		list.add(new GuestPO("1234567892", LocalDate.of(1995, 1, 3), "school2", "zhangsan2", "xiaosan2",
				"000002", "13523456787",100));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
	}
}
