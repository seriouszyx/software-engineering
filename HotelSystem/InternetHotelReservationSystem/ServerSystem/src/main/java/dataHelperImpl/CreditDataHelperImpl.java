package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.CreditDataHelper;
import po.CreditPO;
import utilities.JDBCUtil;
import utilities.TimeChange;
import utilities.enums.CreditRecord;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy charles updateTime 20167/1/1
 *
 */
public class CreditDataHelperImpl implements CreditDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	private String sql;


	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30 构造函数，初始化成员变量conn
	 */
	public CreditDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @Description:获取该客户所有的信用变化记录
	 * @param guestID
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月5日 下午4:11:57
	 */
	@Override
	public List<CreditPO> getAllCreditDetail(String guestID) {
		sql = "select * from credit where guestID = ?";
		final List<CreditPO> result = new ArrayList<CreditPO>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, guestID);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				final CreditPO creditPO = new CreditPO();
				creditPO.setGuestID(guestID);
				creditPO.setTime(TimeChange.string2DateTime(rs.getString(2))); //此处硬编码2-6对应表项中元素的位置，已确定
				if(String.valueOf(rs.getObject(3)).equals("0")) {
					creditPO.setOrderID("");
				}else {
					creditPO.setOrderID(String.valueOf(rs.getObject(3)));					
				}
				
				creditPO.setPreCredit(rs.getDouble(4));
				creditPO.setAfterCredit(rs.getDouble(5));
				creditPO.setCreditRecord(CreditRecord.getEnum(rs.getString(6)));
				
				result.add(creditPO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Description:添加一条信用变化记录
	 * @param creditPO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月5日 下午4:12:24
	 */
	@Override
	public ResultMessage addCredit(CreditPO creditPO) {
		sql = "insert into credit(guestID,orderID,time,"
				+ "previousCredit,afterCredit,reason) "
				+ "values(?,?,?,?,?,?)";

		try {

			ps = conn.prepareStatement(sql);
			ps.setObject(1, creditPO.getGuestID()); //此处硬编码1-6对应语句中元素的位置，已确定
			if(creditPO.getOrderID().equals("")) {
				ps.setObject(2, "0");
			} else {
				ps.setObject(2, creditPO.getOrderID());				
			}
			ps.setString(3, TimeChange.dateTime2String(creditPO.getTime()));
			ps.setDouble(4, creditPO.getPreCredit()); 
			ps.setDouble(5, creditPO.getAfterCredit());
			ps.setObject(6, creditPO.getCreditRecord().getChineseCreditRecord());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		return ResultMessage.SUCCESS;
	}

//	@Override
//	public List<CreditPO> getCreditOfOneOrder(String orderID) {
//		sql = "select * from credit where guestID = ?";
//		final List<CreditPO> result = new ArrayList<CreditPO>();
//
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, orderID);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				final CreditPO creditPO = new CreditPO();
//				creditPO.setGuestID(orderID);
//				creditPO.setTime(TimeChange.string2DateTime(rs.getString(2))); //此处硬编码2-6对应表项中元素的位置，已确定
//				creditPO.setOrderID(String.valueOf(rs.getObject(3)));
//				creditPO.setPreCredit(rs.getDouble(4));
//				creditPO.setAfterCredit(rs.getDouble(5));
//				creditPO.setCreditRecord(CreditRecord.getEnum(rs.getString(6)));
//
//				result.add(creditPO);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30
	 * @param
	 * @return
	 */
	public void close() { //当决定抛弃该对象时调用
		JDBCUtil.close(rs, ps, conn);
		this.sql = null;
	}

}
