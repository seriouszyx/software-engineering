package vo;

import po.WebMarketerPO;

public class WebMarketerVO extends UserVO {

	
	public WebMarketerVO(String webMarketerID, String password) {
		super(webMarketerID,password);
	}
	
	public WebMarketerVO(WebMarketerPO webMarketerPO) {
		super(webMarketerPO.getWebMarketerID(),webMarketerPO.getPassword());
	}
}

