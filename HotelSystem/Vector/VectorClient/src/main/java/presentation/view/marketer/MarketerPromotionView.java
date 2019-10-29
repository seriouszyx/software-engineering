package presentation.view.marketer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.marketer.MarketerPromotionViewControllerImpl;
import presentation.controller.service.marketer.MarketerPromotionViewControllerService;
import presentation.controller.unity.Promotion;
import vo.ActivityPromotionVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description
 */
public class MarketerPromotionView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private TableView<Promotion> promotion_List;
    @FXML
    private TableColumn<Promotion, ActivityPromotionVo> promotionName_column;
    @FXML
    private TableColumn<Promotion, Date> promotionStartDate_column;
    @FXML
    private TableColumn<Promotion, Date> promotionEndDate_column;
    @FXML
    private TableColumn<Promotion, Number> promotionDiscount_column;

    private ArrayList<ActivityPromotionVo> promotionList;
    private MarketerPromotionViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MarketerPromotionViewControllerImpl.getInstance();
        promotionList = (ArrayList<ActivityPromotionVo>) controller.getCurrentActStrategy();

        initTable();
    }

    private void initTable() {
        ObservableList<Promotion> data = FXCollections.observableArrayList();
        for(ActivityPromotionVo vo : promotionList) {
            data.add(new Promotion(vo, vo.getStartDate(), vo.getEndDate(), vo.getDiscount()));
        }
        promotion_List.setItems(data);

        promotionStartDate_column.setCellValueFactory(cellData -> cellData.getValue().promotionStartDateProperty());
        promotionEndDate_column.setCellValueFactory(cellData -> cellData.getValue().promotionEndDateProperty());
        promotionDiscount_column.setCellValueFactory(cellData -> cellData.getValue().promotionDiscountProperty());
        promotionName_column.setCellValueFactory(cellData -> cellData.getValue().promotionNameProperty());
        promotionName_column.setCellFactory(param -> new PromotionBtnCell());
    }

    class PromotionBtnCell extends TableCell<Promotion, ActivityPromotionVo> {
        @Override
        public void updateItem(ActivityPromotionVo item, boolean empty) {
            super.updateItem(item, empty);
            Button order_btn;
            if (item != null) {
                order_btn = new Button(item.getPromotionName());
                SingletonItem.getInstance().setActivityPromotionVo(item);
                setGraphic(order_btn);
                order_btn.setOnAction((ActionEvent event) -> {
                    switcher(ViewFxmlPath.MarketerProInfo_View_Path);
                });
            }
        }
    }

    @FXML
    private void handleAddPro() {
        switcher(ViewFxmlPath.MarketerProAdd_View_Path);
    }

    private void switcher(String fxml) {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
