package vo;

import java.io.Serializable;
import java.util.Date;

import common.Sex;
import po.MemberPo;

public class
MemberVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String id;
	private  int credit;
	private  String name;
	private  String phone;
	private  String address;
	private  Sex sex;
	private  Date birthday ;
    private  int vip ;

	public MemberVo(MemberPo po){
		this.id=po.getId();
		this.credit=po.getCredit();
		this.name=po.getName();
		this.phone=po.getPhone();
		this.address=po.getAddress();
		this.sex=po.getSex();
		this.birthday=po.getBirthday();
		this.vip=po.getVip();
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
