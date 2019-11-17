package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.SpecialSpanPromotionPO;
import utilities.JDBCUtil;
import utilities.TimeChange;
import utilities.enums.ResultMessage;

public class SpecialSpanPromotionDataHelperImpl implements dataHelper.SpecialSpanPromotionDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	private String sql;

	public SpecialSpanPromotionDataHelperImpl() {
		conn = JDBCUtil.getConnection();
	}

	/**
	 * @Description:根据酒店id查询该酒店所有特定期间的策略
	 * @param hotelID
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午3:59:14
	 */
	@Override
	public List<SpecialSpanPromotionPO> getHotelSpecialSpanPromotion(String hotelID) {
		return getSpecialSpanPromotion(hotelID);
	}

	/**
	 * @Description:直接查询网站所有特定期间的策略，网站的id规定为99999999
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午4:01:28
	 */
	@Override
	public List<SpecialSpanPromotionPO> getWebSpecialSpanPromotion() {
		String webID = "99999999";
		return getHotelSpecialSpanPromotion(webID);
	}

	/**
	 * @Description:在数据库中添加一条特定期间策略
	 * @param specialSpanPromotionPO
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午4:02:19
	 */
	@Override
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) {
		
		sql = "insert into specialspanpromotion(userID,promotionName,discount,startDate,endDate)"
				+"values(?,?,?,?,?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, specialSpanPromotionPO.getUserID());
			ps.setString(2, specialSpanPromotionPO.getPromotionName());
			ps.setDouble(3, specialSpanPromotionPO.getDiscount());
			ps.setString(4, TimeChange.date2String(specialSpanPromotionPO.getStartDate()));
			ps.setString(5, TimeChange.date2String(specialSpanPromotionPO.getEndDate()));
			ps.execute();
			
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:更新数据库里一条特定期间策略
	 * @param specialSpanPromotionPO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午4:31:57
	 */
	@Override
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) {
		
		sql = "update specialspanpromotion set "
				+ "discount = ? , startDate = ? , endDate = ?"
				+ "where userID = ? and promotionName = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, specialSpanPromotionPO.getDiscount());
			ps.setString(2, TimeChange.date2String(specialSpanPromotionPO.getStartDate()));
			ps.setString(3, TimeChange.date2String(specialSpanPromotionPO.getEndDate()));
			ps.setString(4, specialSpanPromotionPO.getUserID());
			ps.setString(5, specialSpanPromotionPO.getPromotionName());
			
			ps.execute();
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:删除一条特定期间策略
	 * @param specialSpanPromotionPO
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午4:39:59
	 */
	@Override
	public ResultMessage deleteSpecialSpanPromotion(String userID,String promotionName) {
		sql = "delete from specialspanpromotion where userID = ? and promotionName = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userID);
			ps.setString(2, promotionName);
			ps.execute();
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}
	
	
	private List<SpecialSpanPromotionPO> getSpecialSpanPromotion(String userID){
		List<SpecialSpanPromotionPO> specialSpanPromotionPOList = new ArrayList<SpecialSpanPromotionPO>();
		sql = "select * from specialspanpromotion where userID = ?";
		SpecialSpanPromotionPO specialSpanPromotionPO;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();

			while(rs.next()){
				
				specialSpanPromotionPO = new SpecialSpanPromotionPO();
				specialSpanPromotionPO.setUserID(userID);
				specialSpanPromotionPO.setPromotionName(rs.getString(2));
				specialSpanPromotionPO.setDiscount(rs.getDouble(3));
				specialSpanPromotionPO.setStartDate(TimeChange.string2Date(rs.getString(4)));
				specialSpanPromotionPO.setEndDate(TimeChange.string2Date(rs.getString(5)));
				
				specialSpanPromotionPOList.add(specialSpanPromotionPO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specialSpanPromotionPOList;
	}

}
