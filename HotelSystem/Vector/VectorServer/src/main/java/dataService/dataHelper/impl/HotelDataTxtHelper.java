package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.ResultMessage;
import common.RoomType;
import dataService.dataHelper.service.HotelDataHelper;
import po.HotelPo;
import po.HotelTypeRoomPo;

public class HotelDataTxtHelper implements HotelDataHelper{
	
	private String rootPath = this.getClass().getResource("/textData/hotel/").getPath();    //hotel文件根目录
	
	@Override
	public Map<String, HotelPo> getHotelData() {
		//读取数据
		File file = new File(rootPath + "hotelList.txt");
		Map<String,HotelPo> map = new HashMap<String, HotelPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String[] data = str.split("/");
				HotelPo po = new HotelPo(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
						Integer.valueOf(data[7]), Double.valueOf(data[8]), Integer.valueOf(data[9]),
						data[10], getComments(data[0]), getTypeRoom(data[0]));
				map.put(data[0], po);
				str = br.readLine();
			}

			br.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public ResultMessage addHotelData(HotelPo po){
		File file = new File(rootPath + "hotelList.txt");
		try{
			File rootFile = new File(rootPath + po.getId());	
			rootFile.mkdir();
	
			/*
			 * 创建酒店信息的相关文件
			 */
			//存储评论的文件
			File commentfile = new File(rootFile.getAbsolutePath(),"comments.txt");	
			commentfile.createNewFile();
		
			//存储房间信息的文件
			File initRoomFile = new File(rootFile.getAbsolutePath(),"initRoom.txt");
			initRoomFile.createNewFile();
			
			//直接写在最后一行
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			String str = po.getId() + "/" + po.getHotelName() + "/" + po.getProvince() + "/" + po.getHotelCity()
		              	 + "/" + po.getHotelPosition() + "/" + po.getInBusiness() + "/" + po.getHotelTel()
		              	 + "/" + (po.getStars()+"") + "/" + (po.getPoStrings()+"") + "/"
		              	 + (po.getNumOfpoint()+"") + "/" + po.getHotelInfo();
			writer.write(str);
			writer.newLine();
			writer.close();
			
			//初始化酒店促销策略文件
			new HotelPromotionDataTxtHelper().initDefaultData(po.getId());
			
			return ResultMessage.SUCCEED;
		}catch(Exception e){
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}
	
	
	@Override
	public ResultMessage updateHotelListData(Map<String, HotelPo> map) {
		//写入数据  更新hotelList列表
		File file = new File(rootPath + "hotelList.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			//对map进行遍历
			Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,HotelPo> entry = iterator.next();
				HotelPo po = entry.getValue();
				
				String str = po.getId() + "/" + po.getHotelName() + "/" + po.getProvince() + "/" + po.getHotelCity()
             	 + "/" + po.getHotelPosition() + "/" + po.getInBusiness() + "/" + po.getHotelTel()
             	 + "/" + (po.getStars()+"") + "/" + (po.getPoStrings()+"") + "/"
             	 + (po.getNumOfpoint()+"") + "/" + po.getHotelInfo();
				
				writer.write(str);
				writer.newLine();
			}
			writer.close();	
			return ResultMessage.SUCCEED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}	
	}

	
	@Override
	public ResultMessage deleteHotelData(String hotelId) {
		//在酒店列表中删除酒店信息
		Map<String, HotelPo> map = getHotelData();
		if(!map.containsKey(hotelId))
			return ResultMessage.FAIL;
		
		Map<String,HotelPo> updateMap = new HashMap<String, HotelPo>();
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String,HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			if( !po.getId().equals(hotelId) ){
				updateMap.put(po.getId(), po);	
			}	
		}
		updateHotelListData(updateMap);
		
		//删除该酒店房间、评价文件
		File hotelfile = new File(rootPath + hotelId);
		hotelfile.mkdir();	
		for(File files : hotelfile.listFiles()){
			files.delete();
		
		}		
		//删除促销策略文件
		File profile = new File(hotelfile.getAbsolutePath() + "/promotion");	
		profile.mkdir();
		for(File files : profile.listFiles()){
			files.delete();
		}
		profile.delete();
		
		hotelfile.delete();

		return ResultMessage.SUCCEED;		
	}
		
	
	@Override
	public List<String> getComments(String hotelId){
		File file = new File(rootPath + hotelId + "/comments.txt");
		List<String> comment = new ArrayList<String>();
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String result = "";
				//每个人的评论以单行的*隔开为标志
				while( !str.trim().equals("*") ){
					result += str + "\n";
					str = br.readLine();
				}
				comment.add(result);
				str = br.readLine();
			}
			
			br.close();
			return comment;
		}catch(Exception e){
			e.printStackTrace();
		}
		return comment;
	}
	
	
	@Override
	public ResultMessage updateComments(String hotelId,List<String> commentList){
		try {
			File rootFile = new File(rootPath + hotelId);
			rootFile.mkdir();
		
			File file = new File(rootFile.getAbsolutePath(),"comments.txt");	
			if(!file.exists())
				file.createNewFile();
			
			if( !commentList.isEmpty()){
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter writer = new BufferedWriter(fw);
				
				Iterator<String> iterator = commentList.iterator();
			
				while(iterator.hasNext()){
					writer.write(iterator.next());
					writer.newLine();
					writer.write("*");//每个人的评论以单行的*隔开为标志
					writer.newLine();
				}	
				
				writer.close();
				return ResultMessage.SUCCEED;
			}
			
			return ResultMessage.FAIL;
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage initRoom(String hotelId, RoomType type, int number, int price){
		File rootFile = new File(rootPath + hotelId);
		rootFile.mkdir();
	
		String type_str = getRoomTypeName(type);    //获取房间类型的string表示
		//文件中存储的格式
		String result = type_str + "/" + (number + "") + "/" + (price + "");
		
		File file = new File(rootFile.getAbsolutePath(),"initRoom.txt");	
		File type_f = new File(rootFile.getAbsolutePath(),type_str + ".txt");
		
		try {
			file.createNewFile();       type_f.createNewFile();
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			
			/*
			 * 先读取原文件中的内容
			 */
			List<String> list = new ArrayList<String>();
			String str = br.readLine();
			while (str != null) {
				//检查该酒店是否有该类型的酒店，若没有则添加；若有，则跳过原来的内容再添加新的内容
				if( !str.startsWith(type_str) )
					list.add(str);
				str = br.readLine();
			}
			br.close();
			list.add(result);
			
			//再重新写入房间信息
			FileWriter fw = new FileWriter(file.getAbsoluteFile());	
			BufferedWriter writer = new BufferedWriter(fw);
			
			Iterator<String> iterator = list.iterator();
			while(iterator.hasNext()){
				writer.write(iterator.next());
				writer.newLine();
			}
			writer.close();
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	
	@Override
	public ResultMessage updateOrderedRoom(String hotelId, RoomType type, int number, boolean isCheckIn){
		File file = new File(rootPath + hotelId + "/" + getRoomTypeName(type) + ".txt");
		
		try {
			file.createNewFile();
		
			int num = 0;     //该类型房间被预定的数量
			if(isCheckIn)  num = number + getOrderedRoom(hotelId, type);    //预定房间
			else           num = getOrderedRoom(hotelId, type) - number;    //退房
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));			
			bw.write( (num+""));
			bw.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public int getOrderedRoom(String hotelId, RoomType type){
		File file = new File(rootPath + hotelId + "/" + getRoomTypeName(type) + ".txt");
		
		try {
			file.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String str = br.readLine();
			br.close();
			
			if(str == null) return 0;
			return Integer.parseInt(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 根据酒店编号返回酒店房间信息
	 * @param hotelId
	 * @return
	 */
	private List<HotelTypeRoomPo> getTypeRoom(String hotelId){
		List<HotelTypeRoomPo> list = new ArrayList<HotelTypeRoomPo>();
		
		File rootFile = new File(rootPath + hotelId);
		rootFile.mkdir();
		
		File file = new File(rootFile.getAbsolutePath(),"initRoom.txt");	
		
		
		try {
			file.createNewFile();
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			
			String str = br.readLine();
			while (str != null) {
				String [] data = str.split("/");
				RoomType rt = null;
				//字符串转化为房间类型
				switch(data[0]){
				case "SINGLE": rt = RoomType.SINGLE; break;
				case "DOUBLE": rt = RoomType.DOUBLE; break;
				case "FAMILY": rt = RoomType.FAMILY; break;
				}
				
				HotelTypeRoomPo po = new HotelTypeRoomPo(rt,Integer.valueOf(data[1]),
						Integer.valueOf(data[2]));
				
				list.add(po);
				str = br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 将房间类型转为对应的String
	 * @param type
	 * @return
	 */
	private String getRoomTypeName(RoomType type){
		String type_str = new String();
		switch(type){
		case SINGLE: type_str = "SINGLE"; break;
		case DOUBLE: type_str = "DOUBLE"; break;
		case FAMILY: type_str = "FAMILY"; break;
		}
		return type_str;
	}
	
	@Override
	public List<String> getProvinceList(){
		List<String> list = new ArrayList<String>();

		File addressFile = new File(this.getClass().getResource("/textData/address/").getPath());
		addressFile.mkdir();
		
		for(File f: addressFile.listFiles()){
			list.add(f.getName());
		}
		return list;
	}
	
	@Override
	public List<String> getCityList(String province){
		List<String> list = new ArrayList<String>();
		
		File cityFile = new File(this.getClass().getResource("/textData/address/").getPath() + province);
		cityFile.mkdir();
		
		for(File f: cityFile.listFiles()){
			list.add(f.getName());
		}
		return list;
	}
	
	@Override
	public List<String> getBusinessList(String province, String city){
		List<String> list = new ArrayList<String>();
		
		File businessFile = new File(this.getClass().getResource("/textData/address/").getPath() + province + "/" + city);
		businessFile.mkdir();
		
		for(File f: businessFile.listFiles()){
			String name = f.getName();
			list.add(name.substring(0,name.lastIndexOf(".")));   //只读取文件名
		}
		return list;
	}
}