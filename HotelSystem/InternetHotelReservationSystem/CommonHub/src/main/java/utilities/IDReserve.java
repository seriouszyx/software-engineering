package utilities;

public class IDReserve {
	
	private static IDReserve idReserve = new IDReserve();
	
	private String userID;
	
	public static IDReserve getInstance(){
		return idReserve;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	
}
