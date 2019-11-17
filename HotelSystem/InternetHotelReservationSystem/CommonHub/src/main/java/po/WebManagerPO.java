package po;

import java.io.Serializable;

import vo.WebManagerVO;

public class WebManagerPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3713445002016120118L;

	//	管理人员编号
	private String webManagerID;
	
	//	密码
	private String password;
	
	public WebManagerPO(String userID, String password) {
		this.webManagerID = userID;
		this.password = password;
	}
	
	public WebManagerPO(WebManagerVO  webManagerVO) {
		this.webManagerID = webManagerVO.userID;
		this.password = webManagerVO.password;
	}
	
	
	public String getWebManagerID() {
		return webManagerID;
	}
	
	public void setWebManagerID(String webManagerID) {
		this.webManagerID = webManagerID;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
