package dataService.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import common.ResultMessage;
import dataService.dao.service.MarketPromotionDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MarketPromotionDataHelper;
import po.ActivityPromotionPo;
import po.BusinessProPo;
import po.LevelPo;
import po.MemberPo;
import vo.MemberVo;

public class
MarketPromotionDaoImpl implements MarketPromotionDao{
	
	private DataFactory dataFactory;

	private MarketPromotionDataHelper marketPromotionDataHelper;
	
	private static MarketPromotionDaoImpl marketPromotionDaoImpl;
	
	public static MarketPromotionDaoImpl getInstance(){
		if(marketPromotionDaoImpl == null)
			marketPromotionDaoImpl = new MarketPromotionDaoImpl();
		return marketPromotionDaoImpl;
	}
 
	private MarketPromotionDaoImpl(){
		super();
		dataFactory = new DataFactoryImpl();
		marketPromotionDataHelper = dataFactory.getMarketPromotionDataHelper();
	}
	
	
	@Override
	public ResultMessage addActivity(ActivityPromotionPo po){
		List<String> list = getActivity();
		if(!list.isEmpty()){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				//若存在该活动
				if(it.next().startsWith(po.getPromotionName()))
					return ResultMessage.FAIL;
			}
		}
		return marketPromotionDataHelper.addActivity(po);
	}
	
	
	@Override
	public ResultMessage updateActivity(ActivityPromotionPo po){
		List<String> list = getActivity();
		if(!list.isEmpty()){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				//若存在该活动
				if(it.next().startsWith(po.getPromotionName()))
					return marketPromotionDataHelper.updateActivity(po);
			}
		}
		//不存在该活动
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public List<String> getActivity(){
		return marketPromotionDataHelper.getActivity();
	}
	
	
	@Override
	public ResultMessage updateLevelRule(List<LevelPo> list){
		updateMemberVIP(list, true);     //更新普通客户VIP
		updateMemberVIP(list, false);    //更新企业客户VIP
		return marketPromotionDataHelper.updateLevelRule(list);
	}

	
	@Override
	public ResultMessage deleteActivity(ActivityPromotionPo po){
		List<String> activityList = getActivity();
		
		if(!activityList.isEmpty()){
			Iterator<String> it = activityList.iterator();
			
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(po.getPromotionName()))
					return marketPromotionDataHelper.deleteActivity(po);
			}	
		}
		
		return ResultMessage.FAIL;//不存在该活动
	}

	
	@Override
	public List<LevelPo> getLevelList(){
		return marketPromotionDataHelper.getLevelList();
	}
	
	
	/**
	 * 通过客户信用值得到其等级
	 * @param credit
	 * @return
	 */
	public int getMemberLevel(int credit){
		int level = 1;
		List<LevelPo> levelList = getLevelList();
		
		if(!levelList.isEmpty()){
			Iterator<LevelPo> it = levelList.iterator();
		
			while(it.hasNext()){
				LevelPo po = it.next();
				int level_credit = po.getCredit();     //某个等级对应的信用值
			
				//检查在哪个等级区间内
				if(credit >= level_credit)     level = po.getLevel();
				else                           return level;	
			}
		}
		return level;
	}

	
	/**
	 * 网站营销人员更新等级策略时，同时调用此方法修改所有客户的等级
	 * @param list
	 */
	private void updateMemberVIP(List<LevelPo> list, boolean isMember){
		TreeMap<String,MemberPo> map = MemberDaoImpl.getInstance().getMap(isMember);
		Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String, MemberPo> entry = iterator.next();
			MemberPo po = entry.getValue();
			
			//设置每个客户的等级
			Iterator<LevelPo> it = list.iterator();
			while(it.hasNext()){
				LevelPo level = it.next();
				int level_credit = level.getCredit();     //某个等级对应的信用值
				//检查在哪个等级区间内
				if(po.getCredit() >= level_credit){
					po.setVip(level.getLevel());
				}else{
					break;
				}
			}
			//更新数据层
			map.put(po.getId(), po);     
			MemberDaoImpl.getInstance().modifyInfo(new MemberVo(po));      //更新每个客户信息
		}
		MemberDaoImpl.getInstance().updateMap(map, isMember);                  //更新map
		
	}

	
	@Override
	public ResultMessage updateBusiness(BusinessProPo po) {
		return marketPromotionDataHelper.updateBusinessPro(po);
	}

	@Override
	public ResultMessage deleteBusiness(BusinessProPo po) {
		return marketPromotionDataHelper.deleteBusiness(po);
	}

	@Override
	public List<BusinessProPo> getBusinessList() {
		return marketPromotionDataHelper.getBusinessProList();
	}
}
