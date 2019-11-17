package dataHelperImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dataHelper.SourceDataHelper;

/**
 * @Description:资源文件的读取：
 * 系统支持城市的获取、支持星级的获取、支持酒店类型的获取
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月7日 下午5:04:19
 */
public class SourceDataHelperImpl implements SourceDataHelper {
	
	/**
	 * @Description:获取系统支持酒店星级
	 * @return
	 * List<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 下午1:07:01
	 */
	@Override
	public List<String> getLevels() {
		return getSources("level");
	}

	/**
	 * @Description:获取系统支持的所有酒店客房类型
	 * @return
	 * List<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 下午1:10:44
	 */
	@Override
	public List<String> getRoomTypes() {
		return getSources("roomType");
	}
	
	/**
	 * @Description:获取系统允许的每一订单的最大人数
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月13日 下午6:46:25
	 */
	public int getMaxGuestNumEachOrder(){
		return getMaxNum().get(0);
	}
	
	/**
	 * @Description:获取系统允许的每一订单的最大房间数
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月13日 下午6:46:59
	 */
	public int getMaxRoomNumEachOrder(){
		return getMaxNum().get(1);
	}
	
	/**
	 * @Description:当客户登陆时，记录客户的id，为查重做准备
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:26:58
	 */
	@Override
	public void guestLogInRecord(String guestID) {
		Properties source = new Properties();
		try {
			source.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("guestLogInRecord.propertis"));
			//向资源文件中加入一对键值和value;
			source.put(source.size()+1, guestID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:客户登陆时，取得所有客户得id，查重
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:27:38
	 */
	@Override
	public boolean guestHasLogged(String guestID) {
		Properties source = new Properties();
		return source.contains(guestID);
	}
	
	/**
	 * @Description:客户等出时，在资源文件中将客户的id移除
	 * @param guestID
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月16日 下午8:34:38
	 */
	public void guestLogOut(String guestID){
		Properties source = new Properties();
		try {
			source.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("guestLogInRecord.propertis"));
			for(int i = 0;i<source.size();i++){
				if(source.getProperty((i+1)+"").equals(guestID)){
					source.remove(i+1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	private List<Integer> getMaxNum(){
		List<Integer> list = new ArrayList<Integer>();
		for(String string: getSources("maxNum")){
			list.add(Integer.valueOf(string));
		}
		return list;
	}
	
	private List<String> getSources(String sourceName){
		Properties source = new Properties();
		List<String> values = new ArrayList<String>();
		try {
			source.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(sourceName+".properties"));
			for(int i = 0;i < source.size();i++){
				values.add(source.getProperty(""+(i+1)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

}
