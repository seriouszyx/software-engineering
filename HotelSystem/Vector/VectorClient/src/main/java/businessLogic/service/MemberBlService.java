package businessLogic.service;


import common.InfoType;
import common.ResultMessage;
import vo.MemberVo;
/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * MemberBlService接口的职责是处理相应的presentation层和businesslogic层中其他的逻辑模块发来的请求,
 * 由类MemberBlServiceImpl来实现这个接口中的方法。
 * 账号信息的属性有： 用户名、ID、地址、联系方式、性别、信用值、生日、VIP等级
 */

public interface MemberBlService {

	/**
	 * 获取用户的信用值
	 * @param id
	 * @return credit:int
	 * @author lienming
	 * @version 2016-11-27
	 */
	 public int getCredit(String id) ;

	/**
	 * 为客户充值信用
	 * @param id :int, amount :int充值的信用数量 可为负数
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
	public ResultMessage chargeCredit(String id, int amount) ;

	/**
	 * 显示用户的信息
	 * @param id
	 * @return MemberVo/null
	 * @author lienming
	 * @version 2016-11-27
	 */
	public MemberVo getInfo(String id) ;

	/**
	 * 检查输入信息的合法性
	 * @param info:String
	 * @return ResultMessage.VALID/INVALID
	 * @author lienming
	 * @version 2016-11-27
	 */
	public ResultMessage checkInfo(String info,InfoType infoType) ;

	/**
	 * 修改用户信息
	 * @param vo 
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
	public ResultMessage modifyInfo(MemberVo vo);
	
}
