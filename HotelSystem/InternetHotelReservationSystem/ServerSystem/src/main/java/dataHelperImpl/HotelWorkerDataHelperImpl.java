package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelWorkerDataHelper;
import po.HotelWorkerPO;
import utilities.Ciphertext;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy 董金玉 updateTime 2016/11/28
 *
 */
public class HotelWorkerDataHelperImpl implements HotelWorkerDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28 构造函数，初始化成员变量conn
	 */
	public HotelWorkerDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public HotelWorkerPO add(HotelWorkerPO hotelWorkerPO) {
		sql = "INSERT INTO hotelworker(hotelworker.hotelName,hotelworker.`password`) "
				+ "values (?,?)";

		try {
			ps = conn.prepareStatement(sql); // 插入数据的准备工作，1-3对应sql语句中问号的顺序
			ps.setString(1, hotelWorkerPO.getHotelName()); // 在使用setObject方法是必须注意，我们应该使用对应数据类型
			ps.setString(2, new Ciphertext().encrypt("qwertyuiop123456")); // 虽然Object可以替代所有该set方法，但会影响效率所以尽量使用对应数据类型的set方法

			ps.execute(); // 执行sql语句，返回值为boolean
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		hotelWorkerPO.setHotelWorkerID(this.getTheLastID());
		hotelWorkerPO.setPassword(new Ciphertext().encrypt("qwertyuiop123456"));
		return hotelWorkerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定hotelWorkerInfo
	 */
	public ResultMessage modify(final HotelWorkerPO hotelWorkerPO) {
		sql = "UPDATE hotelworker SET hotelworker.hotelName = ?, hotelworker.`password` = ? "
				+ "WHERE hotelworker.hotelWorkerID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, hotelWorkerPO.getHotelName()); //此处硬编码1-3对应语句中元素的位置，已确定
			ps.setString(2, hotelWorkerPO.getPassword());
			ps.setInt(3, Integer.parseInt(hotelWorkerPO.getHotelWorkerID()));

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		return ResultMessage.SUCCESS;

	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param hotelWorkerID 酒店工作人员ID
	 * @return ResultMessage 是否成功删除指定酒店工作人员信息
	 */
	public ResultMessage delete(final String hotelWorkerID) {
		sql = "DELETE FROM hotelworker WHERE hotelworker.hotelWorkerID = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(hotelWorkerID)); //对应sql语句中ID的位置
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param hotelWorkerID 酒店工作人员ID
	 * @return HotelWorkerPO 数据库中的指定hotelWorkerInfo载体
	 */
	public HotelWorkerPO getSingle(final String hotelWorkerID) {
		HotelWorkerPO hotelWorkerPO = null;
		sql = "SELECT hotelworker.hotelName,hotelworker.`password` "
				+ "FROM hotelworker WHERE hotelworker.hotelWorkerID = ?"; // 获取一条数据根据hotelWorkerID

		try {
			ps = conn.prepareStatement(sql); // 该处与getAll方法相同
			ps.setString(1, hotelWorkerID);
			rs = ps.executeQuery();

			if (rs.next()) {
				hotelWorkerPO = new HotelWorkerPO();
				hotelWorkerPO.setHotelWorkerID(hotelWorkerID);
				hotelWorkerPO.setHotelName(rs.getString(1)); // 此处硬编码1-2对应sql语句中元素
				hotelWorkerPO.setPassword(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return hotelWorkerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param
	 * @return List<HotelWorkerPO> 获取所有hotelWorkerInfo载体
	 */
	public List<HotelWorkerPO> getAll() {
		sql = "SELECT * FROM hotelworker"; // sql语句，挑选所有hotelWorker数据
		final List<HotelWorkerPO> list = new ArrayList<HotelWorkerPO>(); // 封装多条数据

		try {
			ps = conn.prepareStatement(sql); // 获取数据的准备工作
			rs = ps.executeQuery(); // 得到执行语句后的结果集合

			while (rs.next()) {
				final HotelWorkerPO result = new HotelWorkerPO(); // 封装一条数据
				result.setHotelWorkerID(String.valueOf(rs.getInt(1))); // 1-3的硬编码对应表中的表项
				result.setPassword(rs.getString(2));
				result.setHotelName(rs.getString(3));

				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
		JDBCUtil.close(rs, ps, conn);
		this.sql = null;
	}
	
	private String getTheLastID(){
		
		sql = "SELECT LAST_INSERT_ID() from hotelWorker";
		
		try {
			ps  = conn.prepareStatement(sql);
			rs  = ps.executeQuery();
			
			if(rs.next()){
				return String.valueOf(rs.getObject(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
