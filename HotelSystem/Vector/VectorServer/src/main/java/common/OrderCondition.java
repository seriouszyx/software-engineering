package common;

public enum OrderCondition {
	WAITING,   //待执行
	CANCELED,  //客户取消
	EXECUTING, //正在执行
	EXECUTED,  //已执行
	FINISHED,  //已评价
	ABNORMAL,  //异常
	REVOKED;   //异常恢复
}
