package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.MarketDataHelper;
import po.MarketPO;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/11/30
 *
 */
public class MarketDataHelperImpl implements MarketDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;
	
	private String sql;
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30 构造函数，初始化成员变量conn
	 */
	public MarketDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @return List<MarketPO> 所有marketInfo载体
	 */
	public List<MarketPO> getAll() {
		sql = "SELECT * FROM market";
		final List<MarketPO> result = new ArrayList<MarketPO>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				final MarketPO marketPO = new MarketPO(rs.getString(1), rs.getDouble(2), rs.getDouble(3));
				result.add(marketPO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @param list 所有需要修改的marketInfo载体
	 * @return ResultMessage 是否成功修改所有marketInfo
	 */
	public ResultMessage modifyAll(final List<MarketPO> list) {
		
		if (list.isEmpty()) {
			return ResultMessage.FAIL;
		}
		
		sql = "UPDATE market SET market.marketCredit = ?, market.marketBenefit = ? WHERE market.marketName = ?";
		
		try {
			for (MarketPO marketPO :list) {
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, marketPO.getMarketCredit());
				ps.setDouble(2, marketPO.getMarketBenefit());
				ps.setString(3, marketPO.getMarketName());
					
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @param
	 * @return
	 */
	public void close() {
		JDBCUtil.close(rs, ps, conn);
		this.sql = null;
	}
}
