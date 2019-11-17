package dataHelperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataHelper.AddressDataHelper;
import po.AddressPO;
import utilities.JDBCUtil;
import utilities.enums.ResultMessage;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/11/30
 *
 */
public class AddressDataHelperImpl implements AddressDataHelper {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	private String sql;

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30 构造函数，初始化成员变量conn
	 */
	public AddressDataHelperImpl() {
		this.conn = JDBCUtil.getConnection();
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @return List<String> 所有城市
	 */
	public List<String> getCity() {
		sql = "SELECT address.city FROM `address`";
		final List<String> result = new ArrayList<String>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.close(rs, ps);
		return this.deletDuplicate(result);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @param city 城市
	 * @return List<String> 指定城市的所有商圈
	 */
	public List<String> getCircle(String city) {
		
		sql = "select cycle from address where city = ?";
		final List<String> result = new ArrayList<String>();

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, city); //对应问号的位置
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(rs.getString(1)); //对应表项的位置
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.close(rs, ps);
		return result;  //若未找到对应项，返回空list
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @param city 城市
	 * @param cycle 商圈
	 * @return double 指定城市和商圈的折扣
	 */
	public double getDiscout(String city,String circle) {
		sql = "SELECT address.discout FROM `address` WHERE city = ? AND cycle = ?";
		double discout = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, city); //对应问号的位置
			ps.setString(2, circle);
			rs = ps.executeQuery();

			if (rs.next()) {
				discout = rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return discout; //若未找到对应项，返回空0
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/4
	 * @return List<AddressPO> 对应城市的商圈及其折扣
	 */
	public List<AddressPO> getAll(String city) {
		sql = "SELECT * FROM address where city = ?";
		final List<AddressPO> result = new ArrayList<AddressPO>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			rs = ps.executeQuery();

			while (rs.next()) {
				final AddressPO addressPO = new AddressPO(rs.getString(1), rs.getString(2), rs.getDouble(3));
				//此处硬编码对应表项的位置，已确定
				result.add(addressPO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; //若未找到对应项，返回空list
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Harvey Gong
	 * @updateTime 2016/12/2
	 * @param addressPO 需要修改的一条记录
	 * @return ResultMessage 是否成功修改折扣
	 */
	public ResultMessage modifyDiscout(AddressPO addressPO) {
		String city = addressPO.getCity();
		String circle = addressPO.getCircle();
		double discount = addressPO.getDiscout();
		sql = "UPDATE address SET address.discout = ? WHERE address.city = ? AND address.cycle = ?;";

		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, discount); //此处硬编码对应问号的位置
			ps.setString(2, city);
			ps.setString(3, circle);

			ps.execute();
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

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/30
	 * @param list 含有重复字符串的list
	 * @return List<String> 去除重复字符串的list
	 */
	private List<String> deletDuplicate(List<String> list) {

		//删除重复元素
		for (int i = 0; i < list.size();i++) {
			for(int j = i+1;j<list.size();){
				if(list.get(i).equals(list.get(j))){
					list.remove(j);
				}
				else{
					j++;
				}
			}
		}
		return list;
	}

}
