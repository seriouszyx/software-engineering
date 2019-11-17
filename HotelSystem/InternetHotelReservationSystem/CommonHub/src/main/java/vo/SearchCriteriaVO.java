package vo;

import java.util.List;

import utilities.enums.RoomType;

/**
 * @Description:将搜索条件界面的搜索信息封装到SearchCriteriaVO中
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午2:13:40
 */
public class SearchCriteriaVO {

	// 酒店名称
	public String keyHotelName;
	
	// 只查看已预定的酒店
	public boolean bookedOnly;

	// 星级区间，最低星级
	public int minLevel;
	
	// 星级区间，最高星级
	public int maxLevel;
	
	// 价格区间，最低价格
	public double minPrice;
	
	// 价格区间，最高价格
	public double maxPrice;
	
	// 评分区间，最低价格
	public double minScore;
	
	// 评分区间，最高价格
	public double maxScore;
	
	// 房间类型列表
	public List<RoomType> roomTypes;

	// 剩余房间数量
	public int remainRoomNum;

	public SearchCriteriaVO() {
	}

	public SearchCriteriaVO(String keyHotelName, boolean bookedOnly, int minLevel, int maxLevel, double minPrice,
			double maxPrice, double minScore, double maxScore, List<RoomType> roomTypes, int remainRoomNum) {
		super();
		this.keyHotelName = keyHotelName;
		this.bookedOnly = bookedOnly;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minScore = minScore;
		this.maxScore = maxScore;
		this.roomTypes = roomTypes;
		this.remainRoomNum = remainRoomNum;
	}
	
	
}
