package po;

import java.io.Serializable;

import vo.MarketVO;

public class MarketPO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795676331405404584L;

	//	等级名称
	private String marketName;
	
	//	等级信用度
	private double marketCredit;
	
	//	等级优惠
	private double marketBenefit;

	public MarketPO(MarketVO marketVO) {
		this.marketName = marketVO.marketName;
		this.marketCredit =marketVO.marketCredit;
		this.marketBenefit = marketVO.marketBenefit;
	}
	public MarketPO(String marketName, double marketCredit, double marketBenefit) {
		super();
		this.marketName = marketName;
		this.marketCredit = marketCredit;
		this.marketBenefit = marketBenefit;
	}
	
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	public double getMarketCredit() {
		return marketCredit;
	}
	public void setMarketCredit(double marketCredit) {
		this.marketCredit = marketCredit;
	}
	
	public double getMarketBenefit() {
		return marketBenefit;
	}
	public void setMarketBenefit(double marketBenefit) {
		this.marketBenefit = marketBenefit;
	}
	
}
