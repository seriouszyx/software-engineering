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
import dataService.dataHelper.service.MarketPromotionDataHelper;
import po.ActivityPromotionPo;
import po.BusinessProPo;
import po.LevelPo;

public class MarketPromotionDataTxtHelper implements MarketPromotionDataHelper{
	private File rootFile;

	public MarketPromotionDataTxtHelper(){
		rootFile = new File(this.getClass().getResource("/textData/market/").getPath());
		rootFile.mkdir();
	}

	
	@Override
	public ResultMessage addActivity(ActivityPromotionPo po){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		
		try {
			actFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile(), true));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
			
			// 格式：名称/开始时间/结束时间/折扣  （名称不得重复）
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
	public ResultMessage updateActivity(ActivityPromotionPo po){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		
		try {
			actFile.createNewFile();
			List<String> actList = getActivity();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
			
			// 格式：名称/开始时间/结束时间/折扣  （名称不得重复）
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
		
			actList.set(count, str);       //替换List中相应元素
			
			
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
	public ResultMessage deleteActivity(ActivityPromotionPo po){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		
		try {
			actFile.createNewFile();

			List<String> actList = getActivity();
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
	public List<String> getActivity(){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
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
	public ResultMessage updateLevelRule(List<LevelPo> list){
		File levelFile = new File(rootFile.getAbsolutePath() + "/rank.txt");
		
		try {
			levelFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(levelFile.getAbsoluteFile()));
			
			Iterator<LevelPo> it = list.iterator();
			while(it.hasNext()){
				LevelPo po = it.next();
				//格式：等级/信用值/折扣
				String str = (po.getLevel()+"") + "/" + (po.getCredit()+"") + "/" + (po.getDiscount()+"");
				writer.write(str);
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
	public List<LevelPo> getLevelList() {
		File levelFile = new File(rootFile.getAbsolutePath() + "/rank.txt");
		List<LevelPo> levelList = new ArrayList<LevelPo>();
		
		try {
			levelFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(levelFile), "UTF-8"));
			
			String str = br.readLine();
			while(str != null){
				String [] token = str.split("/");
				LevelPo po = new LevelPo(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
				levelList.add(po);
				str = br.readLine();
			}
			br.close();
			
			return levelList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return levelList;
	}
	
	
	@Override
	public ResultMessage updateBusinessPro(BusinessProPo po){
		File busiFile = new File(rootFile.getAbsolutePath() + "/business.txt");
		
		try {
			busiFile.createNewFile();
			List<BusinessProPo> list = getBusinessProList();//得到原文件中的商圈列表
			List<String> writeList = new ArrayList<String>();//将要写入文件的list
			
			String str = po.getBusinessName() + "/" + (po.getDiscount()+"");
			
			if(list.isEmpty()) writeList.add(str);
			else{
				Iterator<BusinessProPo> it = list.iterator();
		
				int count = 0;
				while(it.hasNext()){
					BusinessProPo tempPo = it.next();
					//若存在该商圈，则更新
					if(tempPo.getBusinessName().equals(po.getBusinessName())){
						writeList.add(str);
						continue;
					}
					
					//格式：商圈名/折扣
					String tempStr = tempPo.getBusinessName() + "/" + (tempPo.getDiscount()+"");
					writeList.add(tempStr);
					count ++;
				}
				//若不存在则添加
				if(count == list.size())  writeList.add(str);
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(busiFile.getAbsoluteFile()));
			
			Iterator<String> it = writeList.iterator();
			while(it.hasNext()){
				writer.write(it.next());
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
	public ResultMessage deleteBusiness(BusinessProPo po){
		File busiFile = new File(rootFile.getAbsolutePath() + "/business.txt");
		
		try {
			busiFile.createNewFile();

			List<BusinessProPo> list = getBusinessProList();//得到原文件中的商圈列表
			
			if(list.isEmpty()){
				return ResultMessage.FAIL;
			}
			
			Iterator<BusinessProPo> it = list.iterator();
		
			BufferedWriter bw = new BufferedWriter(new FileWriter(busiFile.getAbsoluteFile()));
			
			//检查是否存在该商圈，若存在则跳过写入文件
			while(it.hasNext()){
				BusinessProPo temp = it.next();
				if(temp.getBusinessName().equals(po.getBusinessName()))
					continue;
				bw.write(temp.getBusinessName()+"/"+ (temp.getDiscount()+""));
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
	public List<BusinessProPo> getBusinessProList(){
		File busiFile = new File(rootFile.getAbsolutePath() + "/business.txt");
		List<BusinessProPo> list = new ArrayList<BusinessProPo>();
		
		try {
			busiFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(busiFile), "UTF-8"));
			
			String str = br.readLine();
			while(str != null){
				String [] token = str.split("/");
				BusinessProPo po = new BusinessProPo(token[0], Double.parseDouble(token[1]));
				list.add(po);
				str = br.readLine();
			}
			br.close();
			
			return list;
		} catch (IOException e) {
		}
		return list;
	}
}
