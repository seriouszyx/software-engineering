package dataService.dao.service;

import java.rmi.Remote;

import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * AccountDao接口的职责是处理AccountBlServiceImpl模块发来的请求,并通过rmi调用其实现方法
 * 由server端的类AccountBlServiceImpl来实现这个接口中的方法。
 */
public interface AccountDao extends Remote {
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
    public ResultMessage logout(String id)  ;

    /**
	 * 账号密码修改
	 * @param id
	 * @param newPassword
	 * @return ResultMessage.SUCCEED/FAIL
	 */
    public ResultMessage modifyPassword(String id,String newPassword) ;

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
    public ResultMessage updateAccount(AccountVo vo);

    /**
	 * 删除账号
	 * @param id
	 * @return ResultMessage.SUCCEED/FAIL
	 */
    public ResultMessage deleteAccount(String id);

}
