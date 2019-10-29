package dataService.dao.impl;


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.ResultMessage;
import dataService.dao.service.MemberDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MemberDataHelper;
import po.MemberPo;
import vo.MemberVo;

/**
 * 类MemberDaoImpl的职责是实现接口MemberDao的方法，通过与数据层的交互、处理数据完成请求
 */

public class MemberDaoImpl implements MemberDao {


    /* 持有数据 map<String,MemberPo> */
    private TreeMap<String,MemberPo> map_member;
    private TreeMap<String,MemberPo> map_enterprise;
    
    private MemberDataHelper memberDataHelper;

    private DataFactory dataFactory;
    
    /*单件模式*/
    private static MemberDaoImpl memberDataServiceImpl;

    public static MemberDaoImpl getInstance(){
        if(memberDataServiceImpl == null){
            memberDataServiceImpl = new MemberDaoImpl();
        }

        return memberDataServiceImpl;
    }

    public MemberDaoImpl(){
        if(dataFactory==null){
            dataFactory = new DataFactoryImpl();
            memberDataHelper = dataFactory.getMemberDataHelper();
            this.map_member		= memberDataHelper.getMemberData(true);
            this.map_enterprise = memberDataHelper.getMemberData(false);
        }
    }
    
    /**
	 * 获取用户的信用值
	 * @param id
	 * @return credit:int
	 * @author lienming
	 * @version 2016-11-27
	 */
    public int getCredit(String id) {
    	MemberVo vo = getInfo(id);
    	if(!vo.equals(null))
    		return vo.getCredit();
    	else
    		return -1;
    }
    
    
    /**
	 * 为客户充值信用
	 * @param id :int, amount :int充值的信用数量 可为负数
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage chargeCredit(String id, int amount){
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String,MemberPo> entry = iterator.next();
            if( entry.getKey().equals(id) ) {
                MemberPo po = entry.getValue();
                //更新会员信息中的credit和vip
                po.setCredit(po.getCredit()+amount);
                int correctVip = MarketPromotionDaoImpl.getInstance().getMemberLevel(po.getCredit());
                po.setVip(correctVip);
                MemberVo vo = new MemberVo(po);
                updateInfo(vo);
                
                //更新信用变化记录
                CreditDaoImpl.getInstance().addCreditByOrder(id, amount, null);
                
                return ResultMessage.SUCCEED;
            }
        }
        return ResultMessage.FAIL;
    }

    /** 检查 VIP等级是否正确 */ 
    public boolean checkVip(int credit){
    	return true;
    }
    
     /**
	 * 显示用户的信息
	 * @param id
	 * @return MemberVo/null
	 * @author lienming
	 * @version 2016-11-27
	 */
    public MemberVo getInfo(String id){
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, MemberPo> entry = iterator.next();
            if (entry.getKey().equals(id)) {
                MemberPo po = entry.getValue();
                MemberVo vo = new MemberVo(po);
                return vo;
            }
        }
        return null;
    }

    /**
	 * 修改用户信息
	 * @param vo 
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage modifyInfo(MemberVo vo){
    	TreeMap<String,MemberPo> map = getMap(isMember(vo.getId())) ;
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, MemberPo> entry = iterator.next();
            if ( entry.getKey().equals(vo.getId())) {
            	updateInfo(vo);
                return ResultMessage.SUCCEED;
            }
        }
        return ResultMessage.FAIL;
    }

    /**
	 * 插入一个用户的基本信息 
	 * @param vo 
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage insertInfo(MemberVo vo){
    	TreeMap<String,MemberPo> map = getMap(isMember(vo.getId())) ;
    	 if(!map.containsKey(vo.getId())) {
    		 MemberPo po = new MemberPo(vo.getId(),vo.getName(),vo.getPhone(),
    				 vo.getAddress(),vo.getSex(),vo.getCredit(),
    				 vo.getBirthday(),vo.getVip());
             map.put(vo.getId(), po);
             updateMap(map,isMember(vo.getId()));
             memberDataHelper.updateMemberData(map,isMember(vo.getId()));
             return ResultMessage.SUCCEED;
         }
         else
             return ResultMessage.FAIL; //已存在
    }
    
    /**
	 * 更新一个用户的基本信息 
	 * @param vo 
	 * @return ResultMessage.SUCCEED/FAIL
	 * @author lienming
	 * @version 2016-11-27
	 */
    public ResultMessage updateInfo(MemberVo vo){
    	String id = vo.getId() ;
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
    	if(map.containsKey(id))
        { 
    		MemberPo po = new MemberPo(vo.getId(),vo.getName(),vo.getPhone(),
				 vo.getAddress(),vo.getSex(),vo.getCredit(),
				 vo.getBirthday(),vo.getVip());
            map.put(id, po);
            updateMap(map,isMember(id));
            memberDataHelper.updateMemberData(map,isMember(id));
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }
    
    /**
   	 * 删除一个用户的基本信息 
   	 * @param vo 
   	 * @return ResultMessage.SUCCEED/FAIL
   	 * @author lienming
   	 * @version 2016-11-27
   	 */
    public ResultMessage deleteInfo(String id){
    	  TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
    	  if(map.containsKey(id))
          {
              map.remove(id);
              updateMap(map,isMember(id));
              memberDataHelper.updateMemberData(map,isMember(id));
              return ResultMessage.SUCCEED;
          }
          else
              return ResultMessage.FAIL;
    }
    
    /** 根据用户类型取原表进行下一步操作 */
    public TreeMap<String,MemberPo> getMap(boolean isMember){
    	TreeMap<String,MemberPo> map;
    	if(isMember)
    		map = this.map_member;
    	else 
    		map = this.map_enterprise;
    	return map;
    }
    
    /** 将新表覆盖原表 */
    public void updateMap(TreeMap<String,MemberPo> map,boolean isMember){
    	if(isMember)
    		this.map_member=map;
    	else
    		this.map_enterprise=map;
    }
    
    /** 根据ID判断是普通用户还是企业用户的简单方法 */
    public boolean isMember(String id){
    	char label = id.charAt(0);
    	if(label=='N')
    		return true;
    	else
    		return false;
    }
}
