package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.WebMarketerDataHelper;
import po.WebMarketerPO;
import utilities.Ciphertext;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy 董金玉 updateTime 2016/11/28
 *
 */
public class WebMarketerDataHelperImpl implements WebMarketerDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28 构造函数，初始化成员变量conn
	 */
	public WebMarketerDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public WebMarketerPO add(WebMarketerPO webMarketerPO) {
		sql = "INSERT INTO webmarketer(webmarketer.`password`) VALUES(?)";

		try {
			ps = conn.prepareStatement(sql); // 插入数据的准备工作，1-2对应sql语句中问号的顺序
			ps.setString(1, new Ciphertext().encrypt("qwertyuiop123456")); // 在使用setObject方法是必须注意，我们应该使用对应数据类型,
															// 虽然Object可以替代所有该set方法，但会影响效率所以尽量使用对应数据类型的set方法

			ps.execute(); // 执行sql语句，返回值为boolean
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		webMarketerPO.setWebMarketerID(this.getTheLastID());
		webMarketerPO.setPassword(new Ciphertext().encrypt("qwertyuiop123456"));
		return webMarketerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webMarketerInfo
	 */
	public ResultMessage modify(final WebMarketerPO webMarketerPO) {
		sql = "UPDATE webmarketer SET webmarketer.`password` = ? WHERE webmarketer.webMarketerID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, webMarketerPO.getPassword());
			ps.setString(2, webMarketerPO.getWebMarketerID());

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
	 * @param webMarketerID 网站营销人员ID
	 * @return ResultMessage 是否成功删除指定网站营销人员信息
	 */
	public ResultMessage delete(final String webMarketerID) {
		sql = "DELETE FROM webmarketer WHERE webmarketer.webMarketerID = ?;";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, webMarketerID);
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
	 * @param webMarketerID 网站营销人员ID
	 * @return WebMarketerPO 数据库中的指定webMarketerInfo载体
	 */
	public WebMarketerPO getSingle(final String webMarketerID) {
		WebMarketerPO webMarketerPO = null;
		sql = "SELECT webmarketer.`password` FROM webmarketer " + "WHERE webmarketer.webMarketerID = ?"; // 获取一条数据根据webMarketerID

		try {
			ps = conn.prepareStatement(sql); // 该处与getAll方法相同
			ps.setString(1, webMarketerID);
			rs = ps.executeQuery();

			if (rs.next()) {
				webMarketerPO = new WebMarketerPO(webMarketerID, rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return webMarketerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/28
	 * @param
	 * @return List<WebMarketerPO> 获取所有webMarketerInfo载体
	 */
	public List<WebMarketerPO> getAll() {
		sql = "SELECT * FROM webmarketer"; // sql语句，挑选所有webMarketer数据
		final List<WebMarketerPO> list = new ArrayList<WebMarketerPO>(); // 封装多条数据

		try {
			ps = conn.prepareStatement(sql); // 执行sql语句
			rs = ps.executeQuery(); // 获得执行后的结果

			while (rs.next()) {
				final WebMarketerPO map = new WebMarketerPO(rs.getString(1), rs.getString(2)); // 封装一条数据
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
		
		sql = "SELECT LAST_INSERT_ID() from webmarketer";
		
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
