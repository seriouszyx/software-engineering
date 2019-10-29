package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dataHelper.service.CreditDataHelper;
import po.CreditRecordPo;

/**
 * Updated by lienming on 2016-12-31.
 * 类CreditDataHelper的职责是实现接口CreditDataHelper的方法,
 * 通过直接读写存储Credit数据的Txt文本，完成请求
 */
public class CreditDataTxtHelper implements CreditDataHelper{
	/*根据账号类型分为2个txt文本存储CreditRecord数据*/
	String memberFile = this.getClass().getResource("/textData/creditRecord/member/").getPath();
	String enterpriseFile =	this.getClass().getResource("/textData/creditRecord/enterprise/").getPath();

	/**
	 * 获取所有同一个类型的Credit信息
	 * @param String 
	 * @return List<CreditRecordPo>
	 */
	public List<CreditRecordPo> getCreditRecordData(String id){
		List<CreditRecordPo> list = new ArrayList<CreditRecordPo>();
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(
                    txtFile), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            
            while (str != null) {
                String[] data = str.split(";");

                int creditNow = Integer.parseInt(data[0]);
                String reason    = data[1];
                String str_time  = data[2];
                int delta = Integer.parseInt(data[3]);
                
                CreditRecordPo po = new CreditRecordPo(creditNow,reason,str_time,delta);
                list.add(po);

                str = br.readLine();
            }

            br.close();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	 /**
     * 将同一个类型的Credit数据一次写入
     * @param List<CreditRecordPo>
     * @param String
     * @return ResultMessage
     */
	public ResultMessage updateCreditRecordData(List<CreditRecordPo> list,String id){
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
			if(!txtFile.exists())
				return ResultMessage.FAIL;//txtFile.createNewFile();
			
			FileWriter fw = new FileWriter(txtFile);
			BufferedWriter br = new BufferedWriter(fw);
				
			Iterator<CreditRecordPo> iterator = list.iterator();
			while(iterator.hasNext()){
				br.write(iterator.next().parseString());
				br.newLine();
			}		
			br.close();	
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		return ResultMessage.SUCCEED;
	}
	
	/**
	 * 新建用户同时新建Credit
	 * @param String
	 * @return ResultMessage
	 */
	public ResultMessage newCredit(String id){
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
			if(txtFile.exists())
				return ResultMessage.FAIL;
			txtFile.createNewFile();
			FileWriter fw = new FileWriter(txtFile);
			BufferedWriter br = new BufferedWriter(fw);
			CreditRecordPo po = new CreditRecordPo();
			br.write(po.parseString());
			br.newLine();
			br.close();
		}catch (Exception e) {
			e.printStackTrace();	
		}	
		return ResultMessage.SUCCEED;
	}
	
	  /**
     * 根据用户ID获得其文件路径
     * @param id
     * @return String
     */
	public String getMemberTxtFile(String id){
		if(id.charAt(0)=='N')
			return memberFile + id ; 
		else if(id.charAt(0)=='E')
			return enterpriseFile + id;
		else return null;
	}
	
}
