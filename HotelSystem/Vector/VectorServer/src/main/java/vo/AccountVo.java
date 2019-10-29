package vo;

import java.io.Serializable;

import common.AccountType;
import po.AccountPo;

/**
 * @ author Lienming
 * @ version 2016/11/27
 * @ description
 * 账号的基本属性有：ID、密码、名称、登录状态、用户类型
 */
public class
AccountVo  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberName;
    private String password;
    private String id;
    private int logState=0;
    private AccountType accountType;
    public AccountVo(AccountPo po){
        this.memberName=po.getMemberName();
        this.password=po.getPassword();
        this.id=po.getId();
        this.logState=po.getLogState();
    }


    public String getMemberName(){ return this.memberName; }
    public void setMemberName(String memberName){ this.memberName=memberName; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password=password; }

    public String getId(){ return this.id; }
    public void setId(String id){ this.id = id;}

    public int getLogState(){ return this.logState; }
    public void setLogState(int set){ this.logState=set; }

    public AccountType getAccountType(){
        char symbol = id.charAt(0);
        switch (symbol){
            case 'A':return AccountType.Manager ;
            case 'M':return AccountType.Marketer;
            case 'H':return AccountType.Hotel;
            case 'N':return AccountType.Member;
            case 'E':return AccountType.Member;
            default :return AccountType.Fail;
        }
    }
    public void setAccountType(AccountType type){
        this.accountType=type;
    }
}
