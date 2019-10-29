package presentation.controller.impl.marketer;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import common.ResultMessage;
import presentation.controller.service.marketer.MarketerCreditViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MarketerCreditViewControllerImpl implements MarketerCreditViewControllerService {
    private static MarketerCreditViewControllerService INSTANCE = new MarketerCreditViewControllerImpl();
    private MemberBlService credit;

    private MarketerCreditViewControllerImpl() {
        credit = MemberBlServiceImpl.getInstance();
    }

    public static MarketerCreditViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public ResultMessage chargeCredit(String memberId, int amount) {
        return credit.chargeCredit(memberId, amount);
    }
}
