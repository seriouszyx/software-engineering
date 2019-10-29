package dataService.dataHelper.service;

import java.util.TreeMap;

import common.AccountType;
import po.AccountPo;

/**
 * @ author lienming
 * @ version 2016-12-31
 * @ description AccountDataHelper接口的职责是负责处理与Account数据相关的读写请求
 *  由类AccountDataTxtHelper来实现这个接口中的方法。
 */
public interface AccountDataHelper {
    /**
     * @return	从数据文件中读取用户数据
     */
    public TreeMap<String, AccountPo> getAccountData(AccountType type);

    /**
     * 向数据文件中写入用户数据
     * @param TreeMap<String, AccountPo>
     */
    public void updateAccountData(TreeMap<String, AccountPo> map,AccountType type);

}
