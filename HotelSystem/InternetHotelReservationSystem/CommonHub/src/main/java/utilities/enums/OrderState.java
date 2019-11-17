package utilities.enums;

import java.io.Serializable;

/**
 * 
 * @author charles
 * @lastChangedBy charles
 * @updateTime 2016/12/10
 * 
 * 优先级：已取消 < 异常 < 未执行 < 已执行
 */
public enum OrderState implements Serializable{
	
	CANCELLED("已撤销"), ABNORMAL("异常"), UNEXECUTED("未执行"), EXECUTED("已执行"),NULL("");
	
	private String thisOrderStateString;
	
	private OrderState(String a) {
		this.thisOrderStateString = a;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * enum TO 汉字
	 * 便于界面调用，写入数据库
	 */
	public String getChineseOrderState() {
		return thisOrderStateString;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * 汉字 TO enum
	 * 便于从数据库读入
	 */
	public static OrderState getEnum(String a) {
		for (OrderState thisOrderState : values()) {
			if (thisOrderState.thisOrderStateString.equals(a)) {
				return thisOrderState;
			}
		}
		return null;
	}
	
}
