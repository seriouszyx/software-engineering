package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.AccountType;
import dataService.dataHelper.service.AccountDataHelper;
import po.AccountPo;

/**
 * Updated by lienming on 2016-12-31.
 * 类AccountDataHelper的职责是实现接口AccountDataHelper的方法,
 * 通过直接读写存储Account数据的Txt文本，完成请求
 */
public class AccountDataTxtHelper implements AccountDataHelper {
	/*根据账号类型分为5个txt文本存储Account数据*/

	File memberFile    = new File(this.getClass().getResource("/textData/account/memberAccount.txt").getPath());
	File enterpriseFile= new File(this.getClass().getResource("/textData/account/enterpriseAccount.txt").getPath());
	File hotelFile     = new File(this.getClass().getResource("/textData/account/hotelAccount.txt").getPath());
	File marketerFile  = new File(this.getClass().getResource("/textData/account/marketerAccount.txt").getPath());
	File managerFile   = new File(this.getClass().getResource("/textData/account/managerAccount.txt").getPath());

	/**
	 * 获取所有同一个类型的账号信息
	 * @param AccountType 
	 * @return TreeMap<String,AccountPo>
	 */
    public TreeMap<String, AccountPo> getAccountData(AccountType type) {
        TreeMap<String, AccountPo> TreeMap = new TreeMap<String, AccountPo>();
        File file = getFile(type) ; 
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(
                    file), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            
            while (str != null) {
           // 	str = SecurityHelper.decode(str);
                String[] data = str.split(";");

                String username=data[0];
                String password=data[1];
                String Id = data[2];
                int logState=  Integer.parseInt(data[3]);

                AccountPo accountPo=new AccountPo(username,password,Id,logState);

                TreeMap.put(Id, accountPo);

                str = br.readLine();

            }

            br.close();

            return TreeMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将同一个类型的账号数据一次写入
     * @param TreeMap<String, AccountPo> 
     * @param AccountType 
     * @return void
     */
    public void updateAccountData(TreeMap<String, AccountPo> TreeMap,AccountType type) {
        //写入用户数据
    	File file = getFile(type);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);
            //对TreeMap进行遍历
            Iterator iterator = TreeMap.entrySet().iterator();
            while(iterator.hasNext()){ 
                Map.Entry entry = (Map.Entry)iterator.next();
                AccountPo accountPo = (AccountPo)entry.getValue();
                String str = accountPo.getMemberName()+";"+accountPo.getPassword()+";"
                        +accountPo.getId()+";"+accountPo.getLogState();
                
           //     str = SecurityHelper.encode(str);
                writer.write(str);
                writer.write("\r\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 根据用户类型获得文件的方法
     * @param AccountType
     * @return File
     */
    public File getFile(AccountType type){
    	switch(type){
        case Member		: return this.memberFile;   
        case Enterprise : return this.enterpriseFile;
        case Hotel 		: return this.hotelFile;
        case Marketer	: return this.marketerFile;
        case Manager	: return this.managerFile;
        default			: return null;
        }
    }
    
}
