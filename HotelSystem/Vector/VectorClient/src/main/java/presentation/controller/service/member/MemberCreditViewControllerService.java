package presentation.controller.service.member;

import vo.CreditRecordVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member 信用查看界面控制器接口
 */
public interface MemberCreditViewControllerService {

    //得到信用记录列表
    List<CreditRecordVo> getCreditRecordList(String id);

}
