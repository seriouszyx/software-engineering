package dataService.dao.service;
/*123*/
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * AccountDao接口的职责是处理RemoteHelper模块发来的远程调用请求
 * 由类AccountDaoImpl来实现这个接口中的方法。
 */
public interface AccountDao extends Remote {
	/**
	 * 账号登入：Id + 密码
	 * @param id :String  如：Member的为:"N00001",Hotel的为:"H00001"
	 * @param password : String
	 * @return  AccountType/Fail (只有第一个字母大写)
	 */
    public AccountType login(String id, String password)  throws RemoteException;

    /**
	 * 账号通过Id登出
	 * @param id : String
	 * @return ResultMessage
	 */
    public ResultMessage logout(String id) throws RemoteException;

    /**
   	 * 账号密码修改
   	 * @param id
   	 * @param newPassword
   	 * @return ResultMessage.SUCCEED/FAIL
   	 */
    public ResultMessage modifyPassword(String id, String newPassword) throws RemoteException;

    /**
   	 * 根据ID查找账号
   	 * @param id
   	 * @return AccountVo / null（失败时）
   	 */
    public AccountVo findAccount(String id) throws RemoteException;

    /**
	 * 管理员插入任意类型的账号
	 * @param name
	 * @param password
	 * @param type
	 * @return Id/"FAIL"
	 */
    public String insertAccount(String name,String password,AccountType type)
    		throws RemoteException;

    /**
	 * 更新账号信息
	 * @param vo
	 * @return ResultMessage.SUCCEED/FAIL
	 */
    public ResultMessage updateAccount(AccountVo vo) throws RemoteException;

    /**
   	 * 删除账号
   	 * @param id
   	 * @return ResultMessage.SUCCEED/FAIL
   	 */
    public ResultMessage deleteAccount(String id) throws RemoteException;

}
