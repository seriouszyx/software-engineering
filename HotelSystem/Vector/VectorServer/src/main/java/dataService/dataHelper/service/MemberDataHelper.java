package dataService.dataHelper.service;

import java.util.TreeMap;

import po.MemberPo;

/**
 * @ author lienming
 * @ version 2016-12-31
 * @ description MemberDataHelper接口的职责是负责处理与Member数据相关的读写请求
 *  由类MemberDataTxtHelper来实现这个接口中的方法。
 */
public interface MemberDataHelper {
	 /**
     * @return	从数据文件中读取用户数据
     */
    public TreeMap<String, MemberPo> getMemberData(boolean isMember);
    /**
     * 向数据文件中写入用户数据
     * @param TreeMap<String, MemberPo>
     */
    public void updateMemberData(TreeMap<String, MemberPo> map,boolean isMember);
}
