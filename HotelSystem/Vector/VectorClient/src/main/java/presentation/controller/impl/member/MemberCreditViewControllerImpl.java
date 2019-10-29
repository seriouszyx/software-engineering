package presentation.controller.impl.member;

import businessLogic.impl.CreditBlServiceImpl;
import businessLogic.service.CreditBlService;
import presentation.controller.service.member.MemberCreditViewControllerService;
import vo.CreditRecordVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description  Member 信用查看界面控制器接口实现类
 */
public class MemberCreditViewControllerImpl implements MemberCreditViewControllerService {
    private static MemberCreditViewControllerService INSTANCE = new MemberCreditViewControllerImpl();
    private CreditBlService credit;

    private MemberCreditViewControllerImpl() {
        credit = CreditBlServiceImpl.getInstance();
    }

    public static MemberCreditViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CreditRecordVo> getCreditRecordList(String id) {
        return credit.getCreditRecordList(id);
    }
}
