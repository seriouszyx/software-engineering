package vo;

import java.io.Serializable;

import po.CreditRecordPo;

public class CreditRecordVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int creditNow;
	private String reason ;
	private String time ;
	private int delta;
	
	public CreditRecordVo(CreditRecordPo po){
		this.creditNow = po.getCredit();
		this.reason=po.getReason();
		this.time=po.getTime();
		this.delta=po.getDelta();
	}
	
	public int getCredit(){
		return this.creditNow;
	}
	public String getReason(){
		return this.reason;
	}
	public String getTime(){
		return this.time;
	}
	public int getDelta(){
		return this.delta;
	}
}
