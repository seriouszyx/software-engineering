package po;

import common.AccountType;

/**
 * @ author Lienming
 * @ version 2016/11/27
 * @ description
 * 账号的基本属性有：ID、密码、名称、登录状态、用户类型
 */

public class AccountPo {
    private String memberName;
    private String password;
    private String id;
    private int logState=0;
    private AccountType accountType;

    public AccountPo(String memberName,String password,String id,int logState){
        this.memberName=memberName;
        this.password=password;
        this.id=id;
        this.logState=logState;
        this.accountType=getAccountType(id);
    }

    public String getMemberName(){ return this.memberName; }
    public void setMemberName(String memberName){ this.memberName=memberName; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password=password; }

    public String getId(){ return this.id; }
    public void setId(String id){ this.id = id;}

    public int getLogState(){ return this.logState; }
    public void setLogState(int set){ this.logState=set; }

    public AccountType getAccountType(String id){
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
