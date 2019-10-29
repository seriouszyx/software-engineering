package businessLogic.impl;

import businessLogic.service.MemberBlService;
import common.InfoType;
import common.ResultMessage;
import dataService.dao.service.MemberDao;
import rmi.RemoteHelper;
import vo.MemberVo;

/**
 * 类MemberBlServiceImpl的职责是实现接口MemberBlService的方法，通过远程调用服务端的方法来完成请求
 */
public class MemberBlServiceImpl implements MemberBlService{
	/* 单件模式  */
    private MemberDao memberDao ;
    
    private static MemberBlServiceImpl memberBlServiceImpl;
    
    public static MemberBlServiceImpl getInstance(){
    	if(memberBlServiceImpl==null)
    		memberBlServiceImpl=new MemberBlServiceImpl();
    	return memberBlServiceImpl;
    }
    
    public MemberBlServiceImpl(){
        memberDao = RemoteHelper.getInstance().getMemberDao();
    }

    public int getCredit(String id)  {
        return memberDao.getCredit(id);
    }

    /** 线下充值调用接口实现,amount为正整数 */
    public ResultMessage chargeCredit(String id, int amount){
    	if(amount<=0)
			return ResultMessage.FAIL;
        return memberDao.chargeCredit(id,amount);
    }
    /** 根据id获取用户基本信息 */
    public MemberVo getInfo(String id){
        return memberDao.getInfo(id);
    }

    /** 检查输入是否合法
     * 数据格式要求：
     * InfoType   
     *  NAME     :   长度为4~12
     *  PHONE    :   长度为11位的数字
     *  ADDRESS  :   暂时无要求
     *  SEX      :   FEMALE / MALE 
     */
    public ResultMessage checkInfo(String info, InfoType infoType){
    	switch(infoType){
    	case NAME :	   
    		if(info.length()>=4 && info.length()<=12)
    			return ResultMessage.VALID;
    		else 
    			return ResultMessage.INVALID;
    	case PHONE: 	
    		if(info.length()==11)
    		{
    			for(int i=0;i<11;i++)
    			{
    				if(info.charAt(i)<48 || info.charAt(i)>57)
    					return ResultMessage.INVALID;
    			}
    			return ResultMessage.VALID;
    		}
    		else 
    			return ResultMessage.INVALID;
    	case ADDRESS:	return ResultMessage.VALID;
    	case SEX:
    		if(info.equals("MALE") || info.equals("FEMALE"))
    			return ResultMessage.VALID;
    		else return ResultMessage.INVALID;
    	}
    	
        return ResultMessage.SUCCEED;
    }

    /** 修改用户个人基本信息 */
    public ResultMessage modifyInfo(MemberVo vo){
    	if(checkInfo(vo.getName(),InfoType.NAME) == ResultMessage.VALID
    			&& checkInfo(vo.getPhone(),InfoType.PHONE)== ResultMessage.VALID)
    		return memberDao.modifyInfo(vo);
    	else return ResultMessage.FAIL;
    }

	/**
	 * 用户信用值发生改变时要确认是否改变VIP等级
	 */
    public boolean checkVip(int credit){
    	return memberDao.checkVip(credit);
    }

}
