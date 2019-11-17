package vo;

import po.MarketPO;

public class MarketVO {

	//	等级名称
	public String marketName;
	
	//	等级信用度
	public double marketCredit;
	
	//	等级优惠
	public double marketBenefit;
	
	public MarketVO(MarketPO marketPO) {
		this.marketName = marketPO.getMarketName();
		this.marketCredit =marketPO.getMarketCredit();
		this.marketBenefit = marketPO.getMarketBenefit();
	}
	
	public MarketVO(String marketName, double marketCredit, double d) {
		this.marketName = marketName;
		this.marketCredit = marketCredit;
		this.marketBenefit = d;
	}
}
