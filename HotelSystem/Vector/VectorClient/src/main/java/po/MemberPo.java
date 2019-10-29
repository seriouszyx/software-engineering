package po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Sex;

/**
 * @ author lienming
 * @ version 2016-11-27
 * @ description
 */
public class MemberPo {
    private  String id;
    private  int credit;
    private  String name;
    private  String phone;
    private  String address;
    private  Sex sex;
    private  Date birthday ;
    private  int vip ;
    
    public MemberPo(String id,String name) throws ParseException{
   	 this.id=id;
        this.name=name;
        this.phone="00011112222";
        this.address="Nanjing";
        this.sex=Sex.MALE;
        this.credit=100;
        this.birthday=new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
        this.vip=1;
   }
    
    public MemberPo(String id,String name, String phone, String address,
                      Sex sex,int credit,Date birthday,int vip)
    {
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.address=address;
        this.sex=sex;
        this.credit=credit;
        this.birthday=birthday;
        this.vip=vip;
    }

    public String getId(){ return this.id; }
    public void setId(String id){ this.id=id;}

    public int getCredit(){ return this.credit; }
    public void setCredit(int credit){this.credit=credit; }

    public String getName(){ return this.name; }
    public void setName(String name){this.name=name; }

    public String getPhone(){ return this.phone; }
    public void setPhone(String phone){ this.phone=phone; }

    public String getAddress(){ return this.address; }
    public void setAddress(String address){ this.address=address; }

    public Sex getSex(){ return this.sex; }
    public void setSex(Sex sex){ this.sex = sex ; }

    public Date getBirthday(){ return this.birthday ; }
    public void setBirthday(Date birthday){ this.birthday=birthday ;}

    public int getVip(){ return this.vip ; }
    public void setVip(int vip){ this.vip=vip ; }

}
