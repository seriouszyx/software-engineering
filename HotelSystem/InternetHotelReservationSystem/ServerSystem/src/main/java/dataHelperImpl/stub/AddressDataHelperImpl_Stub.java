package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.AddressDataHelper;
import po.AddressPO;
import utilities.enums.ResultMessage;


/**
 * @Description:AddressDataHelper的桩
 * @author:Harvey Gong
 * @time:2016年12月2日 下午9:03:03
 */
public class AddressDataHelperImpl_Stub implements AddressDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @return List<String> 所有城市
	 */
	public List<String> getCity() {
		List<String> list = new ArrayList<String>();
		list.add("南京");
		list.add("南京");
		list.add("南京");
		list.add("武汉");
		list.add("北京");
		list.add("北京");

		return this.deletDuplicate(list);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param city
	 *            城市
	 * @return List<String> 指定城市的所有商圈
	 */
	public List<String> getCircle(final String city) {
		List<String> list = new ArrayList<String>();
		list.add("新街口");
		list.add("仙林");
		list.add("浦口");
		list.add("大行宫");

		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param city
	 *            城市
	 * @param cycle
	 *            商圈
	 * @return double 指定城市和商圈的折扣
	 */
	public double getDiscout(final String city, final String cycle) {

		return 0.9;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30
	 * @param
	 * @return
	 */
	public void close() {
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/30
	 * @param list
	 *            含有重复字符串的list
	 * @return List<String> 去除重复字符串的list
	 */
	private List<String> deletDuplicate(List<String> list) {

		// 判断相邻元素是否重复，重复则删除
		for (int i = 0; i < list.size() - 1;) {
			if (list.get(i).equals(list.get(i + 1))) {
				list.remove(i + 1);
			} else {
				i++;
			}
		}
		return list;
	}

	/**
	 * @Description:根据所选城市，返回该城市所有的商圈及其折扣
	 * @param city
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月2日 下午9:03:06
	 */
	@Override
	public List<AddressPO> getAll(String city) {
		List<AddressPO> list = new ArrayList<AddressPO>();
		list.add(new AddressPO("南京","新街口",0.5));
		list.add(new AddressPO("南京","大行宫",0.8));
		list.add(new AddressPO("上海","浦东",0.5));
		list.add(new AddressPO("上海","浦西",0.7));
		return list;
	}

	/**
	 * @Description:更新对应所传参数addressPO中的城市商圈的折扣
	 * @param addressPO
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月2日 下午9:03:49
	 */
	@Override
	public ResultMessage modifyDiscout(AddressPO addressPO) {
		return ResultMessage.SUCCESS;
	}

}
