package dataService.dataHelper.impl;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.Sex;
import dataService.dataHelper.service.MemberDataHelper;
import po.MemberPo;
/**
 * Updated by lienming on 2016-12-31.
 * 类MemberDataHelper的职责是实现接口MemberDataHelper的方法,
 * 通过直接读写存储Member数据的Txt文本，完成请求
 */
public class MemberDataTxtHelper implements MemberDataHelper {
	/*根据账号类型分为2个txt文本存储Account数据*/
	File file_member	 = new File(this.getClass().getResource("/textData/member/memberInfo.txt").getPath());
	File file_enterprise = new File(this.getClass().getResource("/textData/member/enterpriseInfo.txt").getPath());
    //File file = new File(getClass().getResource("/textData/member.txt").getPath());
	
	/**
	 * 获取所有同一个类型的账号的个人基本信息
	 * @param isMember
	 * @return TreeMap<String, MemberPo>
	 */
    public TreeMap<String, MemberPo> getMemberData(boolean isMember){
        TreeMap<String, MemberPo> map = new TreeMap<String, MemberPo>();
        File file;
        if(isMember)	
        	file = file_member;
        else 
        	file = file_enterprise;
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            
            while (str != null) {
            //	str = SecurityHelper.decode(str);
                String[] data = str.split(";");

                String Id = data[0];
                String username=data[1];
                String phone=data[2];
                String address = data[3];
                Sex sex;
                if(data[4].equals("MALE"))
                	 sex = Sex.MALE;
                else sex = Sex.FEMALE;
                int credit=Integer.parseInt(data[5]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = sdf.parse(data[6]) ;
                int vip = Integer.parseInt(data[7]) ;
                

                MemberPo memberPo=new MemberPo(Id,username,phone,address,sex,credit,
                		birthday,vip);

                map.put(Id, memberPo);

                str = br.readLine();

            }

            br.close();

            return map;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将同一个类型的个人基本信息一次写入
     * @param TreeMap<String, MemberPo> 
     * @param boolean
     * @return void
     */
    public void updateMemberData(TreeMap<String, MemberPo> map,boolean isMember){
    	//写入用户数据
    	 File file;
         if(isMember)	
         	file = file_member;
         else 
         	file = file_enterprise;
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);

            //对map进行遍历
            Iterator  iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                MemberPo memberPo = (MemberPo)entry.getValue();
                String str = memberPo.getId()+";"+memberPo.getName()+";"+memberPo.getPhone()+";"
                        +memberPo.getAddress()+";"+memberPo.getSex()+";"+memberPo.getCredit()+";"
                        +sdf.format(memberPo.getBirthday())+";"+memberPo.getVip();
                
         //       str = SecurityHelper.encode(str);
                
                writer.write(str);
                writer.write("\r\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
