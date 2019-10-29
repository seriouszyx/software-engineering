package businessLogic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import dataService.dao.service.MarketPromotionDao;
import po.ActivityPromotionPo;
import po.BusinessProPo;
import po.LevelPo;
import rmi.RemoteHelper;
import vo.ActivityPromotionVo;
import vo.BusinessProVo;
import vo.LevelVo;

public class MarketPromotionBlServiceImpl implements MarketPromotionBlService{
	private MarketPromotionDao marketPromotionDao;
	private static MarketPromotionBlServiceImpl marketPromotionBlServiceImpl;
	
	public static MarketPromotionBlServiceImpl getInstance(){
		if(marketPromotionBlServiceImpl == null)
			marketPromotionBlServiceImpl = new MarketPromotionBlServiceImpl();
		return marketPromotionBlServiceImpl;
	}

	private MarketPromotionBlServiceImpl(){
		marketPromotionDao = RemoteHelper.getInstance().getMarketPromotionDao();
	}
	
	@Override
	public ResultMessage addActivityStrategy(ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| vo.getStartDate().after(vo.getEndDate()))
			return ResultMessage.INVALID;
		
		return marketPromotionDao.addActivity(new ActivityPromotionPo(vo));
	}
	
	@Override
	public ResultMessage upActivityStrategy(ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| vo.getStartDate().after(vo.getEndDate()))
			return ResultMessage.INVALID;
		
		return marketPromotionDao.updateActivity(new ActivityPromotionPo(vo));
	}
	
	@Override
	public ResultMessage delActivityStrategy(ActivityPromotionVo vo){
		return marketPromotionDao.deleteActivity(new ActivityPromotionPo(vo));
	}
	

	@Override
	public List<Double> getCurrentActDiscount(){
		List<ActivityPromotionVo> list = getCurrentActStrategy();
		List<Double> discountList = new ArrayList<Double>();
		if(list.isEmpty())  return discountList;
		
		Iterator<ActivityPromotionVo> it = list.iterator();
		
		while(it.hasNext()){
			discountList.add(it.next().getDiscount());
		}
		return discountList;
	}

	
	@Override
	public List<ActivityPromotionVo> getCurrentActStrategy(){
		List<ActivityPromotionVo> list = new ArrayList<ActivityPromotionVo>();
		List<String> actStr = marketPromotionDao.getActivity();
		
		if(actStr != null){
			Iterator<String> it = actStr.iterator();
				
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
			Date now = new Date();
				
			//得到当前时期有效的活动促销列表
			while(it.hasNext()){
				String [] token = it.next().split("/");
				/*
				 * 活动开始日期 token[1]
				 * 活动结束日期 token[2]
				 * 若当前在某次活动期间内
				 */
				try {
					Date start = df.parse(token[1]);
					Date end = df.parse(token[2]);
					
					if(now.before(end) && now.after(start)){
						ActivityPromotionVo vo = new ActivityPromotionVo(token[0], start, end,
									Double.parseDouble(token[3]));
						
						list.add(vo);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}

	@Override
	public ResultMessage updateLevelStrategy(List<LevelVo> list){
		List<LevelPo> poList = new ArrayList<LevelPo>();
		
		if( !list.isEmpty() ){
			Iterator<LevelVo> it = list.iterator();
			
			LevelVo vo1 = it.next();
			LevelPo po1 = new LevelPo(vo1.getLevel(), vo1.getCredit(), vo1.getDiscount());
			
			poList.add(po1);
		
			while(it.hasNext()){	
				LevelVo vo2 = it.next();
				LevelPo po2 = new LevelPo(vo2.getLevel(), vo2.getCredit(), vo2.getDiscount());
				
				/*
		         * 检查折扣，信用值，等级有效性
		         * 信用值，等级必须递增
		         */
				if(vo1.getDiscount()<=0 || vo1.getDiscount()>1 || vo1.getCredit()<0 || vo1.getLevel()<=0)
					return ResultMessage.INVALID;
			
				if(vo2.getCredit() <= vo1.getCredit() || (vo2.getLevel() != (vo1.getLevel()+1)))
					return ResultMessage.INVALID;
			
				vo1 = vo2;
				poList.add(po2);
			}
		}

		/*
		 * 当网站营销人员修改等级策略后，所有的客户等级必须随之变化
		 */
		return marketPromotionDao.updateLevelRule(poList);
	}
		
	@Override
	public double getLevelStrategy(int level){
		List<LevelPo> levelList = marketPromotionDao.getLevelList();
		
		if( !levelList.isEmpty()){
			Iterator<LevelPo> it = levelList.iterator();
			
			while(it.hasNext()){
				LevelPo po = it.next();
				if(po.getLevel() == level)
					return po.getDiscount();
			}
		}
		
		return 1;
	}
	

	@Override
	public ResultMessage addBusinessStrategy(BusinessProVo vo) {
		if(vo.getDiscount() <= 0 || vo.getDiscount() > 1)
			return ResultMessage.INVALID;
			
		List<BusinessProPo> businessList = marketPromotionDao.getBusinessList();
			
		if(!businessList.isEmpty()){
			Iterator<BusinessProPo> it = businessList.iterator();
			//检查商圈名称有无重复
			while(it.hasNext()){
				BusinessProPo po = it.next();
				if(po.getBusinessName().equals(vo.getBusinessName()))
					return ResultMessage.INVALID;     //名称重复，无法添加
			}	
		}
		
		return marketPromotionDao.updateBusiness
				(new BusinessProPo(vo.getBusinessName(),vo.getDiscount()));
	}

	
	@Override
	public ResultMessage updateBusinessStrategy(BusinessProVo vo) {
		if(vo.getDiscount() <= 0 || vo.getDiscount() > 1)
			return ResultMessage.INVALID;
		
		List<BusinessProPo> businessList = marketPromotionDao.getBusinessList();
		
		if(!businessList.isEmpty()){
			Iterator<BusinessProPo> it = businessList.iterator();
			//检查有无该商圈
			while(it.hasNext()){
				BusinessProPo po = it.next();
				if(po.getBusinessName().equals(vo.getBusinessName()))
					return marketPromotionDao.updateBusiness
							(new BusinessProPo(vo.getBusinessName(),vo.getDiscount()));
			}	
		}
		return ResultMessage.FAIL;
	}

	
	@Override
	public ResultMessage deleteBusinessStrategy(BusinessProVo vo) {
		List<BusinessProPo> businessList = marketPromotionDao.getBusinessList();
		
		if(!businessList.isEmpty()){
			Iterator<BusinessProPo> it = businessList.iterator();
			//检查有无该商圈
			while(it.hasNext()){
				BusinessProPo po = it.next();
				if(po.getBusinessName().equals(vo.getBusinessName()))
					return marketPromotionDao.deleteBusiness(new BusinessProPo(vo.getBusinessName(),vo.getDiscount()));
			}	
			
			return ResultMessage.INVALID;    //不存在该活动
		}
		
		return ResultMessage.FAIL;	
	}

	
	@Override
	public BusinessProVo getBusinessStrategy(String businessName) {
		List<BusinessProPo> businessList = marketPromotionDao.getBusinessList();
		
		if(!businessList.isEmpty()){
			Iterator<BusinessProPo> it = businessList.iterator();
			//检查有无该商圈
			while(it.hasNext()){
				BusinessProPo po = it.next();
				if(po.getBusinessName().equals(businessName))
					return new BusinessProVo(po);
			}	
			
			return null;    //不存在该活动
		}
		
		return null;	
	}
	
	
	public double getBusinessDiscount(String businessName) {
		BusinessProVo vo = getBusinessStrategy(businessName);
		if(vo != null)   return vo.getDiscount();
		return 1;
	}
}
