package utilities.enums;

import java.io.Serializable;

public enum RoomType implements Serializable{
	SINGLE_BED("单人间"), DOUBLE_BED("双人间"), TRIPLE_BED("三人间"), 
	PRESIDENTIAL_SUITE("总统套房"), BUSINESS_SUITE("商务套房");
	
	private String thisRoomtype;
	
	private RoomType(String a) {
		this.thisRoomtype = a;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * enum TO 汉字
	 * 便于界面调用，写入数据库
	 */
	public String getChineseRoomType() {
		return thisRoomtype;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * 汉字 TO enum
	 * 便于从数据库读入
	 */
	public static RoomType getEnum(String a) {
		for (RoomType roomType : values()) {
			if (roomType.thisRoomtype.equals(a)) {
				return roomType;
			}
		}
		return null;
	}

}
