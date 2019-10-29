package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.ResultMessage;
import vo.MemberVo;

/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * MemberDao接口的职责是处理RemoteHelper模块发来的远程调用请求
 * 由类MemberDaoImpl来实现这个接口中的方法。
 */

public interface MemberDao extends Remote{

	/**
	 * 获取用户的信用值
	 * @param id
	 * @return credit:int
	 * @author lienming
	 * @version 2016-11-27
	 */
    public int getCredit(String id)throws RemoteException  ;

    /**
	 * 为客户充值信用
	 * @param id :int, amount :int充值的信用数量 可为负数
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage chargeCredit(String id, int amount)throws RemoteException ;

    /**
	 * 显示用户的信息
	 * @param id
	 * @return MemberVo/null
	 * @author lienming
	 * @version 2016-11-27
	 */
    public MemberVo getInfo(String id)throws RemoteException ;

    /**
	 * 修改用户信息
	 * @param vo 
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage modifyInfo( MemberVo vo)throws RemoteException ;

    /** 检查 VIP等级是否正确 */ 
    public boolean checkVip(int credit)throws RemoteException ;
}	
