package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataHelper.GuestDataHelper;
import po.GuestPO;
import utilities.JDBCUtil;
import utilities.TimeChange;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy 董金玉 updateTime 2016/11/29
 *
 */
public class GuestDataHelperImpl implements GuestDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28 构造函数，初始化成员变量conn
	 */
	public GuestDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30
	 * @param guestPO guestInfo载体
	 * @return guestPO 是否成功添加到数据库中
	 */
	public GuestPO add(GuestPO guestPO) {
		sql = "INSERT INTO guest(guest.`name`,guest.nickName,guest.`password`,guest.phone)"
				+ "values(?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql); // 插入数据的准备工作，1-8对应sql语句中问号的顺序
			ps.setString(1, guestPO.getName()); // 虽然Object可以替代所有该set方法，但会影响效率
			ps.setString(2, guestPO.getNickName()); // 所以尽量使用对应数据类型的set方法
			ps.setString(3, guestPO.getPassword());// 在使用setObject方法是必须注意，
			ps.setString(4, guestPO.getPhone());// 我们应该使用对应数据类型

			ps.execute(); // 执行sql语句，返回值为boolean
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		guestPO.setGuestID(this.getTheLastID());
		guestPO.setCredit(1000);
		return guestPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30
	 * @param guestPO guestInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定guestInfo
	 */
	public ResultMessage modify(GuestPO guestPO) {
		sql = "UPDATE guest SET guest.birthday= ?,guest.enterprise = ?,guest.`name`= ?,guest.nickName = ?,"
				+ "guest.`password` = ?,guest.credit = ?,guest.phone = ? WHERE guest.guestID = ?";
		try {
			ps = conn.prepareStatement(sql);

			
			
			if(guestPO.getBirthday()==null){
				ps.setString(1, "0002-02-02"); 
			} else {
				ps.setString(1, TimeChange.date2String(guestPO.getBirthday())); 
			}
			
			if(guestPO.getEnterprise()==null){
				ps.setString(2, "");
			} else{
				ps.setString(2, guestPO.getEnterprise());
			}
			ps.setString(3, guestPO.getName());//此处硬编码1-8对应sql语句中元素的位置，已确定
			ps.setString(4, guestPO.getNickName());
			ps.setString(5, guestPO.getPassword());
			ps.setDouble(6, guestPO.getCredit());
			ps.setString(7, guestPO.getPhone());
			ps.setInt(8, Integer.parseInt(guestPO.getGuestID()));

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
	 * @param guestID 客户ID
	 * @return GuestPO 数据库中的指定guestInfo载体
	 */
	public GuestPO getSingle(final String guestID) {
		GuestPO guestPO = null;
		sql = "SELECT * FROM guest WHERE guest.guestID = ?"; // 获取一条数据根据guestID

		try {
			ps = conn.prepareStatement(sql); // 该处与getAll方法相同
			ps.setString(1, guestID);
			rs = ps.executeQuery();

			if (rs.next()) { // 存在一行数据

				guestPO = new GuestPO();

				guestPO.setGuestID(guestID);
				guestPO.setBirthday(TimeChange.string2Date(rs.getString(2))); // 此处硬编码2-8对应sql语句中元素
				guestPO.setEnterprise(rs.getString(3));
				guestPO.setName(rs.getString(4));
				guestPO.setNickName(rs.getString(5));
				guestPO.setPassword(rs.getString(6));
				guestPO.setCredit(rs.getDouble(7));
				guestPO.setPhone(rs.getString(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return this.change(guestPO);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param
	 * @return List<GuestPO> 获取所有guestInfo载体
	 */
	public List<GuestPO> getAll() {
		sql = "SELECT * FROM guest"; // sql语句，挑选所有guest数据
		final List<GuestPO> list = new ArrayList<GuestPO>(); // 封装多条数据

		try {
			ps = conn.prepareStatement(sql); // 获取数据的准备工作
			rs = ps.executeQuery(); // 得到执行语句后的结果集合

			while (rs.next()) {
				GuestPO result = new GuestPO(); // 封装一条数据
				result.setGuestID(String.valueOf(rs.getObject(1))); // 1-8的硬编码对应表中的表项
				result.setBirthday(TimeChange.string2Date(rs.getString(2)));
				result.setEnterprise(rs.getString(3));
				result.setName(rs.getString(4));
				result.setNickName(rs.getString(5));
				result.setPassword(rs.getString(6));
				result.setCredit(rs.getDouble(7));
				result.setPhone(rs.getString(8));
				
				list.add(this.change(result));
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
		
		sql = "SELECT LAST_INSERT_ID() from guest";
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
	
	private GuestPO change(GuestPO guestPO){
		
		if(guestPO==null){return null;}
		
		GuestPO resultPO = guestPO;
		if(resultPO.getBirthday().isEqual(LocalDate.of(2, 2, 2))){
			resultPO.setBirthday(null);
		}
		if(resultPO.getEnterprise().equals("")){
			resultPO.setEnterprise(null);
		}
		return resultPO;
	}
}
