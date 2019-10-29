package businessLogic.impl;

import businessLogic.service.AccountBlService;
import common.AccountType;
import common.ResultMessage;
import dataService.dao.service.AccountDao;
import rmi.RemoteHelper;
import vo.AccountVo;

/**
 * 类AccountBlServiceImpl的职责是实现接口AccountBlService的方法，通过远程调用服务端的方法来完成请求
 */
public class AccountBlServiceImpl implements AccountBlService{

	/* 单件模式  */
    private AccountDao accountDao;

    private static AccountBlServiceImpl accountBlServiceImpl;

    public static AccountBlServiceImpl getInstance(){
        if( accountBlServiceImpl == null)
            accountBlServiceImpl = new AccountBlServiceImpl() ;
        return accountBlServiceImpl;
    }

    private AccountBlServiceImpl(){
        accountDao = RemoteHelper.getInstance().getAccountDao();
    }

    /** 通过ID和密码登入 */
    public AccountType login(String id, String password){
        /*先检查输入*/
        ResultMessage idValid = checkInput(id);
        ResultMessage passwordValid   = checkInput(password) ;
        if(idValid == ResultMessage.VALID
                && passwordValid == ResultMessage.VALID )
            return accountDao.login(id,password);
        else
            return AccountType.Fail; //输入非法
    }

    /** 用户登出  */
    public ResultMessage logout(String id) {
        return accountDao.logout(id);
    }

    /** 注册普通用户和企业用户 */
    public String register(String name,String password,boolean isMember) {
    	AccountType type;
    	if(isMember) 
    		type = AccountType.Member;
    	else type = AccountType.Enterprise;
    	
    	return insertAccount(name,password,type);
    }

    /** 对所有类型的用户开放的修改密码的方法 */
    public ResultMessage modifyPassword(String id,String newPassword){
    	if(newPassword.length()>=4 && newPassword.length()<=12)
    		return accountDao.modifyPassword(id,newPassword);
    	return ResultMessage.FAIL;
    }

    /** 为检查输入提供的方法，用于检查输入是否合法。
     * 规则为：
     * 长度限制为[4,12] ；
     * 字符限制为：大小写字母、数字 ；
	 * 是否敏感大小写   (未完成)；
	 * 注意：返回ResultMessage.VALID/INVALID
     */
    public ResultMessage checkInput(String input){
        if(input.length()<=3 || input.length()>12)
            return ResultMessage.INVALID;
        else
        {
        	for(int i=input.length()-1;i>=0;i--)
        	{
        		char ch = input.charAt(i); 
        		if( (48<=ch && ch<=57) || (65<=ch&& ch<=90) || 
        				(97<=ch&&ch<=122) )
        			continue;
        		else 
        			return ResultMessage.INVALID;
        	}
            return ResultMessage.VALID;
        }
    }

    /** 通过ID查找账号信息 */
    public AccountVo findAccount(String id) {
        return accountDao.findAccount(id);
    }

    /** 通过提供名称，密码和用户类型插入一个账号 */
    public String insertAccount(String name,String password,AccountType type) {
    	 /*先检查输入*/
        System.out.println("client insertAccount");
        ResultMessage nameValid = checkInput(name);
        ResultMessage passwordValid   = checkInput(password) ;
        if(nameValid == ResultMessage.VALID
                && passwordValid == ResultMessage.VALID )
        {
            System.out.println("before inserting");
        	String id = accountDao.insertAccount(name,password,type);
            System.out.println("after inserting");
        	if(type == AccountType.Hotel)
        		HotelBlServiceImpl.getInstance().addHotel(id);
        	return id;
        }
        return null;
    }

    /** 通过提供一个账号所有的信息更新一个账号 */
    public ResultMessage updateAccount(AccountVo vo) {
        return accountDao.updateAccount(vo);
    }

    /** 网站管理人员通过ID删除账号*/
    public ResultMessage deleteAccount(String id) {
        return accountDao.deleteAccount(id);
    }

    /** 通过ID获取账号类型的方法 */
    public AccountType getAccountTypeById(String id){
    	char label = id.charAt(0);
    	switch(label){
    	case 'N': 	
    	case 'E':   return AccountType.Member;
    	case 'A':   return AccountType.Manager;
    	case 'M':   return AccountType.Marketer;
    	case 'H':   return AccountType.Hotel;
    	default:    return AccountType.Fail;
    	}
    }
}
