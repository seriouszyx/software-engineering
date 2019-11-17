package vo;

import po.WebManagerPO;

public class WebManagerVO extends UserVO {

	
	public WebManagerVO(String webManagerID, String password) {
		super(webManagerID,password);
	}
	
	public WebManagerVO(WebManagerPO webManagerPO) {
		super(webManagerPO.getWebManagerID(),webManagerPO.getPassword());
	}
}