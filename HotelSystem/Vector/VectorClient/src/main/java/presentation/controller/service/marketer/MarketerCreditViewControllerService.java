package presentation.controller.service.marketer;

import common.ResultMessage;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MarketerCreditViewControllerService {

    ResultMessage chargeCredit(String memberId, int amount);
}
