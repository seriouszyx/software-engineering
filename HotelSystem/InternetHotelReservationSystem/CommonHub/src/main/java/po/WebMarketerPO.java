package po;

import java.io.Serializable;

import vo.WebMarketerVO;

public class WebMarketerPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8577895472775494103L;

	//	用户编号
	private String webMarketerID;
	
	//	密码
	private String password;
	
	public WebMarketerPO(String userID, String password) {
		this.webMarketerID = userID;
		this.password = password;
	}
	
	public WebMarketerPO(WebMarketerVO webMarketerVO) {
		this.webMarketerID = webMarketerVO.userID;
		this.password = webMarketerVO.password;
	}
	
	public String getWebMarketerID() {
		return webMarketerID;
	}
	
	public void setWebMarketerID(String webMarketerID) {
		this.webMarketerID = webMarketerID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
