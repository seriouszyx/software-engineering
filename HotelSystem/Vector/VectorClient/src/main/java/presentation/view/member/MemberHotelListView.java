package presentation.view.member;

import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.unity.Hotel;
import presentation.controller.impl.member.MemberHotelListViewControllerImpl;
import presentation.controller.service.member.MemberHotelListViewControllerService;
import presentation.view.unity.StyleUnity;
import vo.HotelVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description
 */
public class MemberHotelListView implements Initializable {
    @FXML
    private ComboBox<String> province_choice;
    @FXML
    private ComboBox<String> city_choice;
    @FXML
    private ComboBox<String> cbd_choice;
    @FXML
    private ComboBox<Integer> star_choice;
    @FXML
    private ComboBox<RoomType> roomType_choice;

    @FXML
    private TextField keyword_field;
    @FXML
    private TextField lowPrice_field;
    @FXML
    private TextField highPrice_field;
    @FXML
    private TextField lowPoint_field;
    @FXML
    private TextField highPoint_field;

    @FXML
    private TableView<Hotel> hotel_list;
    @FXML
    private TableColumn<Hotel, String> hotelName_column;
    @FXML
    private TableColumn<Hotel, Number> hotelStar_column;
    @FXML
    private TableColumn<Hotel, Number> hotelPoint_column;
    @FXML
    private TableColumn<Hotel, String> hotelAddress_column;
    @FXML
    private TableColumn<Hotel, Number> hotelPrice_column;

    @FXML
    private Group other_group;

    @FXML
    private AnchorPane missionPane;

    private MemberHotelListViewControllerService controller;

    private ArrayList<HotelVo> hotelList = new ArrayList<>();

    private ArrayList<HotelVo> hotelSubList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        controller = MemberHotelListViewControllerImpl.getInstance();

        other_group.setDisable(true);
        province_choice.setPromptText("省");
        city_choice.setPromptText("市");
        cbd_choice.setPromptText("商圈");

        StyleUnity.numeric(lowPoint_field);
        StyleUnity.numeric(lowPrice_field);
        StyleUnity.numeric(highPoint_field);
        StyleUnity.numeric(highPrice_field);

        initChoice();
    }

    //初始化选择框
    private void initChoice() {
        //根据星级查找酒店
        star_choice.getItems().addAll(0, 1, 2, 3, 4, 5);
        star_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(hotelList != null) {
                        System.out.println("hptelList:\t" + hotelList);
                        if(star_choice.getItems().get(new_val.intValue()) != 0 && star_choice.getItems().get(new_val.intValue()) != null) {
                            System.out.println(1);
                            hotelSubList = (ArrayList<HotelVo>) controller
                                    .findByStars(star_choice.getItems().get(new_val.intValue()), hotelList);
                            initTable(hotelSubList);
                        }else {
                            System.out.println(2);
                            initTable(hotelList);
                        }
                    }
                }
        );

        //根据房间类型查找酒店
        roomType_choice.getItems().addAll(RoomType.ALL, RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        roomType_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(hotelList != null) {
                        if((roomType_choice.getItems().get((Integer)new_val) != RoomType.ALL) && (roomType_choice.getItems().get((Integer)new_val) != null)) {
                            hotelSubList = (ArrayList<HotelVo>) controller
                                    .findByRoomType(roomType_choice.getItems().get((Integer)new_val), hotelList);
                            initTable(hotelSubList);
                        }else {
                            initTable(hotelList);
                        }
                    }
                }
        );

        //根据省、市、商圈查找酒店
        ArrayList<String> province = (ArrayList<String>) controller.getProvinceList();
        for(String item : province) {
            province_choice.getItems().add(item);
        }
        province_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    ArrayList<String> city = (ArrayList<String>) controller
                            .getCityList(province.get(new_val.intValue()));
                    if(!city_choice.getItems().isEmpty())
                        city_choice.getItems().clear();
                    if(!cbd_choice.getItems().isEmpty())
                        cbd_choice.getItems().clear();
                    other_group.setDisable(true);
                    for(String item : city) {
                        city_choice.getItems().add(item);
                    }
                }
        );
        //市
        city_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> oV, Number oldVal, Number newVal) -> {
                    if(province_choice.getValue() != null) {
                        ArrayList<String> cbd = (ArrayList<String>) controller
                                .getCbdList(province_choice.getValue(), city_choice.getItems().get(newVal.intValue()));
                        cbd_choice.getItems().clear();
                        other_group.setDisable(true);
                        for (String item : cbd) {
                            cbd_choice.getItems().add(item);
                        }
                    }
                }
        );
        //商圈
        cbd_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> one, Number oldOne, Number newOne) -> {
                    if(province_choice.getValue() != null && city_choice.getValue() != null) {
                        hotelList = (ArrayList<HotelVo>) controller.findByAddress(province_choice.getValue(), city_choice.getValue(), cbd_choice.getItems().get(newOne.intValue()));
                        hotelSubList = hotelList;
                        initTable(hotelList);
                        other_group.setDisable(false);
                    }
                }
        );
    }

    //根据价格范围查找酒店
    @FXML
    private void selectHotelByPriceRange() {
        if(!lowPrice_field.getText().equals("") && !highPrice_field.getText().equals("")) {
            int low = Integer.valueOf(lowPrice_field.getText());
            int high = Integer.valueOf(highPrice_field.getText());
            RoomType type = roomType_choice.getValue();
            if(hotelList != null && type != null ) {
                if(low > high) {

                }else {
                    ArrayList<HotelVo> list = (ArrayList<HotelVo>) controller.findByOriginalPrice(type, low, high, hotelList);
                    initTable(list);
                }
            }
        }
    }

    //根据评分查找酒店
    @FXML
    private void selectHotelByPoint() {
        if(!lowPoint_field.getText().equals("") && !highPoint_field.getText().equals("")) {
            double low = Double.valueOf(lowPoint_field.getText());
            double high = Double.valueOf(highPoint_field.getText());
            if(hotelList != null) {
                ArrayList<HotelVo> list = (ArrayList<HotelVo>) controller.findByPoint(low, high, hotelList);
                initTable(list);
            }
        }
    }

    //根据关键字查找酒店
    @FXML
    private void selectHotelByKeyWord() {
        ArrayList<HotelVo> ist = (ArrayList<HotelVo>) controller.findByKeyword(keyword_field.getText());
        initTable(ist);
    }

    //重置搜索条件
    @FXML
    private void handleRefresh() {
        initTable(hotelList);
        hotelSubList = hotelList;
        highPoint_field.clear();
        lowPoint_field.clear();
        highPrice_field.clear();
        lowPrice_field.clear();
        star_choice.setValue(null);
        roomType_choice.setValue(null);
    }

    //初始化表格
    private void initTable(ArrayList<HotelVo> hotelList) {
        System.out.println("initTable");
        if(hotelList != null) {
            List<Hotel> propertyList = new ArrayList<>();
            for (HotelVo hotelVo : hotelList) {
                propertyList.add(
                        new Hotel(hotelVo.getHotelName() + "#" + hotelVo.getId(),
                        hotelVo.getStars(),
                        hotelVo.getPoStrings(),
                        hotelVo.getHotelPosition(),
                        hotelVo.getOriginPrice(RoomType.SINGLE)));
            }
            ObservableList<Hotel> data = FXCollections.observableArrayList();
            for (Hotel hotel : propertyList) {
                data.add(hotel);
            }
            System.out.println("data:\t" + data);
            hotel_list.setItems(data);

            hotelName_column.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
            hotelStar_column.setCellValueFactory(cellData -> cellData.getValue().hotelStarProperty());
            hotelPoint_column.setCellValueFactory(cellData -> cellData.getValue().hotelPointProperty());
            hotelPrice_column.setCellValueFactory(cellData -> cellData.getValue().hotelPriceProperty());
            hotelAddress_column.setCellValueFactory(cellData -> cellData.getValue().hotelAddressProperty());

            hotelName_column.setCellFactory(param -> new HotelBtnCell());
        }

    }

    class HotelBtnCell extends TableCell<Hotel, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Button hotel_btn;
            if(item != null) {
                String name = item.split("#")[0];
                String id = item.split("#")[1];
                hotel_btn = new Button(name);
                setGraphic(hotel_btn);
                hotel_btn.setOnAction(event -> {
                    SingletonItem.getInstance().setHotelId(id);
                    try {
                        missionPane.getChildren().clear();
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelInfo_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                setGraphic(null);
            }
        }
    }

}
