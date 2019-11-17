package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.RoomDataHelper;
import po.RoomInfoPO;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * @Description:更新数据库中的客房信息
 * @author:Harvey Gong
 * @time:2016年12月4日 上午11:31:26
 */
public class RoomDataHelperImpl implements RoomDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	private String sql;


	public RoomDataHelperImpl() {
		conn = JDBCUtil.getConnection();
	}	

	/**
	 * @Description:根据酒店id获取该酒店的客房信息
	 * @param hotelID
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午1:23:50
	 */
	@Override
	public List<RoomInfoPO> getRoomInfo(String hotelID) {
		sql = "select * from roominfo where hotelID = ?";
		List<RoomInfoPO> list = new ArrayList<RoomInfoPO>();
		RoomInfoPO po;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hotelID);
			rs = ps.executeQuery();
			while(rs.next()){
				po = new RoomInfoPO();
				po.setHotelID(hotelID);
				po.setRoomType(RoomType.getEnum(rs.getString(2)));
				po.setRoomNum(rs.getInt(3));
				po.setRemainNum(rs.getInt(4));
				po.setPrice(rs.getInt(5));
				list.add(po);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description:根据新的客房信息和该信息修改前的客房类型，在数据库中找到旧数据并更新
	 * @param roomInfoPO
	 * @param oldRoomType
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午1:34:10
	 */
	@Override
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO) {
		sql = "update roominfo set roomNum = ? , remainRoomNum = ? , price = ? "
				+"where hotelID = ? and roomType = ?";

		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, roomInfoPO.getRoomNum());
			ps.setInt(2, roomInfoPO.getRemainNum());
			ps.setInt(3, roomInfoPO.getPrice());
			ps.setString(4, roomInfoPO.getHotelID());
			ps.setString(5, roomInfoPO.getRoomType().getChineseRoomType());

			ps.execute();
			
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:根据新增的传入的po，在数据库中插入一条数据
	 * @param roomInfoPO
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午1:39:21
	 */
	@Override
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO) {
		sql = "insert into roominfo(hotelID,roomType,roomNum,remainRoomNum,price)"
				+"values(?,?,?,?,?)";

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, roomInfoPO.getHotelID());
			ps.setString(2, roomInfoPO.getRoomType().getChineseRoomType());
			ps.setInt(3, roomInfoPO.getRoomNum());
			ps.setInt(4, roomInfoPO.getRemainNum());
			ps.setInt(5, roomInfoPO.getPrice());
			ps.execute();

			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}

	
	/**
	 * @Description:根据hotelID和roomType，删除一条具体的客房信息
	 * @param hotelID
	 * @param roomType
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午1:45:29
	 */
	@Override
	public ResultMessage deleteRoomInfo(String hotelID,RoomType roomType) {
		sql = "delete from roominfo where hotelID = ? and roomType = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, hotelID);
			ps.setString(2, roomType.getChineseRoomType());
			
			ps.execute();
			return ResultMessage.SUCCESS;
		} catch (SQLException e) {
			return ResultMessage.FAIL;
		}
	}

}
