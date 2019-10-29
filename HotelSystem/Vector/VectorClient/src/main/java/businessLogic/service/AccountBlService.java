package businessLogic.service;


import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * AccountBlService接口的职责是处理相应的presentation层和businesslogic层中其他的逻辑模块发来的请求,
 * 由类AccountBlServiceImpl来实现这个接口中的方法。
 * 
 * AccountBlService的接口包含的方法:
 * 登入、登出、注册、修改密码、检查输入、查找账户、插入、更新、删除账户.
 */
public interface AccountBlService {

	/* 1.interface to Member Client*/

	/**
	 * 账号登入：Id + 密码
	 * @param id :String  如：Member的为:"N00001",Hotel的为:"H00001"
	 * @param password : String
	 * @return  AccountType/Fail (只有第一个字母大写)
	 */
	public AccountType login(String id, String password);

	/**
	 * 账号通过Id登出
	 * @param id : String
	 * @return ResultMessage
	 */
	public ResultMessage logout(String id);

	/**
	 * 普通用户/企业用户（Member）账号注册 
	 * @param memberName
	 * @param password
	 * @return ID（成功）/"FAIL"（失败）
	 */
	public String register(String name, String password,boolean isMember) ;

	/**
	 * 账号密码修改
	 * @param id
	 * @param newPassword
	 * @return ResultMessage.SUCCEED/FAIL
	 */
	public ResultMessage modifyPassword(String id, String newPassword) ;

	/**
	 * 长度[4,12] 只能由大小写字符和数字组成
	 * @param input
	 * @return ResultMessage.VALID/INVALID
	 */
	public ResultMessage checkInput(String input) ;

	/* 2.interface to Manager Client  */
	/**
	 * 根据ID查找账号
	 * @param id
	 * @return AccountVo / null（失败时）
	 */
	public AccountVo findAccount(String id);
	
	/**
	 * 管理员插入任意类型的账号
	 * @param name
	 * @param password
	 * @param type
	 * @return Id/"FAIL"
	 */
	public String insertAccount(String name,String password,AccountType type) ;

	/**
	 * 更新账号信息
	 * @param vo
	 * @return ResultMessage.SUCCEED/FAIL
	 */
	public ResultMessage updateAccount(AccountVo vo) ;

	/**
	 * 删除账号
	 * @param id
	 * @return ResultMessage.SUCCEED/FAIL
	 */
	public ResultMessage deleteAccount(String id) ;
	
	/**
	 * 根据Id获取用户类型
	 * @param id
	 * @return AccountType
	 */
	public AccountType getAccountTypeById(String id);
	

}
