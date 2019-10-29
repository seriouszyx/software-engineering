package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dataHelper.service.HotelPromotionDataHelper;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;

public class HotelPromotionDataTxtHelper implements HotelPromotionDataHelper{
	private File rootFile;
	
	/**
	 * 酒店创建时调用此方法，初始化促销策略默认数据:生日优惠，合作企业优惠
	 * @param hotelId
	 * @return ResultMessage.SUCCEED 初始化成功； ResultMessage.FAIL 初始化失败
	 */
	public ResultMessage initDefaultData(String hotelId){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File birthFile = new File(rootFile.getAbsolutePath() + "/birthPro.txt");
		File comPro = new File(rootFile.getAbsolutePath() + "/cooperPro.txt");
		
		try {
			comPro.createNewFile();     
			birthFile.createNewFile();
			
			//折扣默认值 1 ;  减价默认值 0 ;
			BirthdayProPo bpo = new BirthdayProPo(1.0);
			updateBirthPromotion(hotelId, bpo);
			
			List<String> companyList = new ArrayList<String>();
			CompanyProPo cpo = new CompanyProPo(1.0, companyList);
			updateCompanyPro(hotelId, cpo);
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage addActivity(String hotelId, ActivityPromotionPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File actFile = new File(rootFile.getAbsolutePath() + "/activityPro.txt");
		
		try {
			actFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile(), true));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
			String str = po.getPromotionName() + "/" + sdf.format(po.getStartDate()) + "/" +
			             sdf.format(po.getEndDate()) + "/" + (po.getDiscount()+"");
			writer.write(str);
			writer.newLine();
			writer.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage updateActivity(String hotelId, ActivityPromotionPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File actFile = new File(rootFile.getAbsolutePath() + "/activityPro.txt");
		
		try {
			actFile.createNewFile();
			List<String> actList = getActivity(hotelId);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
			
			// 格式：名称/开始时间/结束时间/折扣
			String str = po.getPromotionName() + "/" + sdf.format(po.getStartDate()) + "/" +
			             sdf.format(po.getEndDate()) + "/" + (po.getDiscount()+"");
			
			
			Iterator<String> it = actList.iterator();
			int count = 0;     //确定该活动策略在List中的位置
			while(it.hasNext()){
				if(it.next().startsWith(po.getPromotionName())){
					break;
				}
				count ++;
			}
		
			actList.set(count, str);
			
			//写入文件
			BufferedWriter writer = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile()));
			
			Iterator<String> it1 = actList.iterator();
			while(it1.hasNext()){
				writer.write(it1.next());
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
	public ResultMessage deleteActivity(String hotelId, ActivityPromotionPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File actFile = new File(rootFile.getAbsolutePath() + "/activityPro.txt");
		
		try {
			actFile.createNewFile();

			List<String> actList = getActivity(hotelId);
			Iterator<String> it = actList.iterator();
		
			BufferedWriter bw = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile()));
			
			while(it.hasNext()){
				String str = it.next();
				//若碰到需要删除的，则跳过不写入
				if(str.startsWith(po.getPromotionName()))
					continue;
				bw.write(str);
				bw.newLine();
			}
			bw.close();
			
			return ResultMessage.SUCCEED;
		}catch(IOException e){
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public List<String> getActivity(String hotelId){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File actFile = new File(rootFile.getAbsolutePath() + "/activityPro.txt");
		List<String> list = new ArrayList<String>();
		
		try {
			actFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(actFile), "UTF-8"));
			String str = br.readLine();
			
			while(str != null){
				list.add(str);
				str = br.readLine();
			}
			
			br.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public ResultMessage updateBirthPromotion(String hotelId, BirthdayProPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File birthFile = new File(rootFile.getAbsolutePath() + "/birthPro.txt");
		
		try {
			birthFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(birthFile.getAbsoluteFile()));
			
			//格式：只存储折扣
			writer.write((po.getDiscount()+""));
			writer.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public BirthdayProPo getBirthPromotion(String hotelId){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File birthFile = new File(rootFile.getAbsolutePath() + "/birthPro.txt");
		
		try {
			birthFile.createNewFile();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(birthFile), "UTF-8"));
			
			String str = br.readLine().trim();
			br.close();
			double discount = Double.parseDouble(str);
			
			BirthdayProPo po = new BirthdayProPo(discount);
			
			return po;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public ResultMessage updateCompanyPro(String hotelId, CompanyProPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File comPro = new File(rootFile.getAbsolutePath() + "/cooperPro.txt");
		File comList = new File(rootFile.getAbsolutePath() + "/cooperAcc.txt");
		
		try {
			comPro.createNewFile();
			comList.createNewFile();
			
			//更新促销优惠
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(comPro.getAbsoluteFile()));
			
			String str = (po.getDiscount()+"");
			bw1.write(str);
			bw1.close();
			
			//更新合作企业列表
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(comList.getAbsoluteFile()));

			if( !po.getCompanyList().isEmpty()){
				Iterator<String> it = po.getCompanyList().iterator();
				while(it.hasNext()){
					bw2.write(it.next());
					bw2.newLine();
				}
			}
			bw2.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public CompanyProPo getCompanyPro(String hotelId){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File comPro = new File(rootFile.getAbsolutePath() + "/cooperPro.txt");//促销策略文件
		File comList = new File(rootFile.getAbsolutePath() + "/cooperAcc.txt");//合作企业账号文件
		
		try {
			comPro.createNewFile();
			comList.createNewFile();
			
			BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(comPro), "UTF-8"));
			String [] token = br1.readLine().split("/");
			br1.close();
			
			double discount = Double.parseDouble(token[0]);
			List<String> list = new ArrayList<String>();
			
			BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(comList), "UTF-8"));
			
			String str = br2.readLine();
			while(str != null){
				list.add(str);
				str = br2.readLine();
			}
			br2.close();
			
			CompanyProPo po = new CompanyProPo(discount,list);
			
			return po;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public ResultMessage updateRoomPromotion(String hotelId, RoomPromotionPo po){
		rootFile = new File(this.getClass().getResource("/textData/hotel/").getPath() + hotelId + "/promotion");
		rootFile.mkdir();
		File roomProFile = new File(rootFile.getAbsolutePath() + "/orderPro.txt");
		
		try {
			roomProFile.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(roomProFile.getAbsoluteFile()));
			
			//格式：促销名称；预订数量；折扣
			String str = po.getPromotionName() + "/" + (po.getNumOfRoom()+"") + "/" + (po.getDiscount()+"");
					     
			bw.write(str);
			bw.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public RoomPromotionPo getRoomPromotion(String hotelId){
		rootFile.mkdir();
		File roomProFile = new File(rootFile.getAbsolutePath() + "/orderPro.txt");
		
		try {
			roomProFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(roomProFile), "UTF-8"));
			String str = br.readLine();
			if(str != null){
				String [] token = str.split("/");
				br.close();
			
				RoomPromotionPo po = new RoomPromotionPo(token[0], Integer.parseInt(token[1]), 
					             Double.parseDouble(token[2]));
				return po;
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
