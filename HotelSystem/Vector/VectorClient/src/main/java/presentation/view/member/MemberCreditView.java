package presentation.view.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.common.SingletonItem;
import presentation.controller.unity.Credit;
import presentation.controller.impl.member.MemberCreditViewControllerImpl;
import presentation.controller.service.member.MemberCreditViewControllerService;
import vo.CreditRecordVo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member 信用记录查看界面
 */
public class MemberCreditView implements Initializable {
    @FXML
    private TableView<Credit> credit_list;
    @FXML
    private TableColumn<Credit, String> creditNow_column;
    @FXML
    private TableColumn<Credit, String> reason_colum;
    @FXML
    private TableColumn<Credit, String> time_column;
    @FXML
    private TableColumn<Credit, String> delta_column;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MemberCreditViewControllerService controller = MemberCreditViewControllerImpl.getInstance();
        ArrayList<CreditRecordVo> creditRecordVos = (ArrayList<CreditRecordVo>) controller.getCreditRecordList(SingletonItem.getInstance().getActivateId());

        //初始化信用记录表格
        List<Credit> propertyList = new ArrayList<>();
        for(CreditRecordVo creditRecordVo : creditRecordVos) {
            propertyList.add(new Credit(String.valueOf(creditRecordVo.getCredit()),
                    creditRecordVo.getReason(),
                    creditRecordVo.getTime(),
                    String.valueOf(creditRecordVo.getDelta() > 0 ? "+" + creditRecordVo.getDelta() : creditRecordVo.getDelta())));
        }
        ObservableList<Credit> data = FXCollections.observableArrayList();
        for (Credit credit : propertyList) {
            data.add(credit);
        }
        credit_list.setItems(data);

        creditNow_column.setCellValueFactory(cellData -> cellData.getValue().creditNowProperty());
        reason_colum.setCellValueFactory(cellData -> cellData.getValue().reasonProperty());
        time_column.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        delta_column.setCellValueFactory(cellData -> cellData.getValue().deltaProperty());
    }


}
