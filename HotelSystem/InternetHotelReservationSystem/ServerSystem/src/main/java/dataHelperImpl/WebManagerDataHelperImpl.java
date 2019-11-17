package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.WebManagerDataHelper;
import po.WebManagerPO;
import utilities.Ciphertext;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy 董金玉 updateTime 2016/11/29
 *
 */
public class WebManagerDataHelperImpl implements WebManagerDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29 构造函数，初始化成员变量conn
	 */
	public WebManagerDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/9
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public WebManagerPO add(WebManagerPO webManagerPO) {
		sql = "INSERT INTO webmanager(webmanager.`password`) VALUES(?)";

		try {
			ps = conn.prepareStatement(sql); // 插入数据的准备工作，1-2对应sql语句中问号的顺序
			ps.setString(1, new Ciphertext().encrypt("qwertyuiop123456")); // 在使用setObject方法是必须注意，我们应该使用对应数据类型,
															// 虽然Object可以替代所有该set方法，但会影响效率所以尽量使用对应数据类型的set方法
			ps.execute(); // 执行sql语句，返回值为boolean
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		webManagerPO.setWebManagerID(this.getTheLastID());
		webManagerPO.setPassword(new Ciphertext().encrypt("qwertyuiop123456"));
		return webManagerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webManagerInfo
	 */
	public ResultMessage modify(final WebManagerPO webManagerPO) {
		sql = "UPDATE webmanager SET webmanager.`password` = ? WHERE webmanager.webManagerID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, webManagerPO.getPassword());
			ps.setString(2, webManagerPO.getWebManagerID());

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
	 * @updateTime 2016/11/29
	 * @param webManagerID 网站管理人员ID
	 * @return WebManagerPO 数据库中的指定webManagerInfo载体
	 */
	public WebManagerPO getSingle(final String webManagerID) {
		WebManagerPO webManagerPO = null;
		sql = "SELECT webmanager.`password` FROM webmanager " + "WHERE webmanager.webManagerID = ?"; // 获取一条数据根据webManagerID

		try {
			ps = conn.prepareStatement(sql); // 该处与getAll方法相同
			ps.setString(1, webManagerID);
			rs = ps.executeQuery();

			if (rs.next()) {
				webManagerPO = new WebManagerPO(webManagerID, rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return webManagerPO;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @return List<WebManagerPO> 获取所有webManagerInfo载体
	 */
	public List<WebManagerPO> getAll() {
		sql = "SELECT * FROM webmanager"; // sql语句，挑选所有webManager数据
		final List<WebManagerPO> list = new ArrayList<WebManagerPO>(); // 封装多条数据

		try {
			ps = conn.prepareStatement(sql); // 执行sql语句
			rs = ps.executeQuery(); // 获得执行后的结果

			while (rs.next()) {
				final WebManagerPO map = new WebManagerPO(rs.getString(1), rs.getString(2)); // 封装一条数据
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
	 * @updateTime 2016/11/29
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
		JDBCUtil.close(rs, ps, conn);
		this.sql = null;
	}
	
	private String getTheLastID(){
		
		sql = "SELECT LAST_INSERT_ID() from webmanager";
		
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
