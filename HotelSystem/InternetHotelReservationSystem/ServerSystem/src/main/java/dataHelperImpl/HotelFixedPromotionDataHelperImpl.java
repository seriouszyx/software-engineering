package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelFixedPromotionDataHelper;
import po.HotelFixedPromotionPO;
import utilities.JDBCUtil;
import utilities.enums.PromotionType;
import utilities.enums.ResultMessage;

/**
 * @Description:对数据库中hotelFixedPromotion表的具体操作
 * @author:Harvey Gong
 * @time:2016年12月3日 下午12:41:27
 */
public class HotelFixedPromotionDataHelperImpl implements HotelFixedPromotionDataHelper {
	
	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;
	
	public HotelFixedPromotionDataHelperImpl() {
		conn = JDBCUtil.getConnection();
	}
	
	/**
	 * @Description:根据酒店工作人员id获取酒店固定的策略，会员生日、企业会员、三间及以上特惠折扣
	 * @param hotelWorkerID
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午1:21:02
	 */
	@Override
	public List<HotelFixedPromotionPO> getHotelFixedPromotion(String hotelWorkerID) {
		
		List<HotelFixedPromotionPO> hotelFixedPromotionPOList = new ArrayList<HotelFixedPromotionPO>();
		sql = "select * from hotelfixedpromotion where hotelID = ?";
		HotelFixedPromotionPO hotelFixedPromotionPO;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, hotelWorkerID);
			rs = ps.executeQuery();
			
			while(rs.next()){
				hotelFixedPromotionPO = new HotelFixedPromotionPO();
				hotelFixedPromotionPO.setHotelID(rs.getString(1));
				hotelFixedPromotionPO.setPromotionType(PromotionType.getEnum(rs.getString(2)));
				hotelFixedPromotionPO.setDiscount(rs.getDouble(3));
				hotelFixedPromotionPOList.add(hotelFixedPromotionPO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotelFixedPromotionPOList;
	}

	/**
	 * @Description:更新一条酒店固定策略的折扣
	 * @param hotelFixedPromotionPO
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午1:43:36
	 */
	@Override
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionPO hotelFixedPromotionPO) {
		
		sql = "update hotelfixedpromotion set discount = ? "
				+ "where hotelID = ? and promotionType = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, hotelFixedPromotionPO.getDiscount());
			ps.setString(2, hotelFixedPromotionPO.getHotelID());
			ps.setString(3, hotelFixedPromotionPO.getPromotionType().getChinesePromotiontype());
			ps.execute();
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
		
	}

}
