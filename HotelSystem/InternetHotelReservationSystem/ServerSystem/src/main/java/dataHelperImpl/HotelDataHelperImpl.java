package dataHelperImpl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelDataHelper;
import po.HotelPO;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

public class HotelDataHelperImpl implements HotelDataHelper{

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	private String sql;

	public HotelDataHelperImpl() {
		conn = JDBCUtil.getConnection();
	}

	/**
	 * @Description:根据城市商圈信息，获取在该城市商圈内的所有酒店
	 * @param city
	 * @param circle
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月5日 上午12:08:43
	 */
	@Override
	public List<HotelPO> getHotels(String city, String circle) {

		sql = "select * from hotel where city = ? and circle = ?";

		List<HotelPO> list = new ArrayList<HotelPO>();
		HotelPO generalPO;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			ps.setString(2, circle);
			rs = ps.executeQuery();

			while(rs.next()){
				generalPO = setHotelPO();
				list.add(generalPO);
			}
			rs = null;
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @Description:根据酒店id取得该酒店的基本信息
	 * @param hotelID
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午3:35:50
	 */
	@Override
	public HotelPO getHotelInfo(String hotelID){
		sql = "select * from hotel where hotelID = ?";

		HotelPO hotelPO = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hotelID);
			rs = ps.executeQuery();
			if(rs.next()){
				hotelPO = setHotelPO();
			}
			rs = null;
		} catch (SQLException e) {
		}
		return hotelPO;
	}


	/**
	 * @Description:更新一条酒店基本信息
	 * @param hotelPO
	 * @return
	 * @throws RemoteException
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午3:43:15
	 */
	@Override
	public ResultMessage updateHotelInfo(HotelPO hotelPO){
		sql = "update hotel set hotelName = ?,city = ?,circle = ?,address = ?,level = ?,"
				+"score = ?, introduction = ?,equipment = ?,commentsNum = ? where hotelID = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, hotelPO.getHotelName());
			ps.setString(2, hotelPO.getCity());
			ps.setString(3, hotelPO.getCircle());
			ps.setString(4, hotelPO.getAddress());
			ps.setString(5, hotelPO.getLevel());
			ps.setDouble(6, hotelPO.getScore());
			ps.setObject(7, hotelPO.getIntroduction());
			ps.setObject(8, hotelPO.getEquipment());
			ps.setInt(9, hotelPO.getCommentsNum());
			ps.setString(10, hotelPO.getHotelID());

			ps.execute();
			return ResultMessage.SUCCESS;
		}
		catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	} 

	/**
	 * @Description:添加一条酒店基本信息
	 * @param hotelPO
	 * @return
	 * @throws RemoteException
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午3:49:44
	 */
	@Override
	public ResultMessage addHotelInfo(HotelPO hotelPO){
		sql = "insert into hotel(hotelID,hotelName,city,circle,address,level,score,introduction,equipment,commentsNum)"
				+"values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			setPreparedStatement(hotelPO);
			ps.execute();
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}
	
	private void setPreparedStatement(HotelPO hotelPO){
		try {
			ps.setString(1, hotelPO.getHotelID());
			ps.setString(2, hotelPO.getHotelName());
			ps.setString(3, hotelPO.getCity());
			ps.setString(4, hotelPO.getCircle());
			ps.setString(5, hotelPO.getAddress());
			ps.setString(6, hotelPO.getLevel());
			ps.setDouble(7, hotelPO.getScore());
			ps.setString(8, hotelPO.getIntroduction());
			ps.setString(9, hotelPO.getEquipment());
			ps.setInt(10, hotelPO.getCommentsNum());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private HotelPO setHotelPO(){
		HotelPO po = new HotelPO();
		try {
			po.setHotelID(rs.getString(1));
			po.setHotelName(rs.getString(2));
			po.setCity(rs.getString(3));
			po.setCircle(rs.getString(4));
			po.setAddress(rs.getString(5));
			po.setLevel(rs.getString(6));
			po.setScore(rs.getDouble(7));
			po.setIntroduction(rs.getString(8));
			po.setEquipment(rs.getString(9));
			po.setCommentsNum(rs.getInt(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return po;
	}
}
