package utilities.enums;

public enum CreditRecord {

	//	信用充值，未执行正常订单执行，逾期订单执行，订单逾期（最晚订单执行时间），异常订单撤销
	CHARGE("信用充值"), EXECUTE("未执行->已执行"), ABNORMAL_EXECUTE("异常->已执行"), 
	OVERDUE("未执行->异常"), UNDO_ABNORMAL("异常->已撤销"), CANCEL_IN_SIX_HOURS("未执行->已撤销（不足6小时）");
	
	private String reason;
	
	private CreditRecord(String a) {
		this.reason = a;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * enum TO 汉字
	 * 便于界面调用，写入数据库
	 */
	public String getChineseCreditRecord() {
		return this.reason;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * 汉字 TO enum
	 * 便于从数据库读入
	 */
	public static CreditRecord getEnum(String a) {
		for (CreditRecord creditRecord : values()) {
			if (creditRecord.reason.equals(a)) {
				return creditRecord;
			}
		}
		return null;
	}
}
