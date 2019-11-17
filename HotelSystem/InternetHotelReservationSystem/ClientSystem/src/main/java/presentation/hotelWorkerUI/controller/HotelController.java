package presentation.hotelWorkerUI.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import businessLogic.hotelBL.HotelBLController;
import businessLogic.orderBL.OrderBLController;
import businessLogic.sourceBL.SourceBLController;
import businessLogicService.hotelBLService.HotelBLService;
import businessLogicService.orderBLService.OrderBLService;
import businessLogicService.sourceBLService.SourceBLService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.Table.EvaluationTable;
import utilities.IDReserve;
import vo.HotelEvaluationVO;
import vo.HotelVO;

/**
 * @author 61990
 * @lastChangedBy Harvey
 *
 */
public class HotelController {

	@FXML
	private Label hotelIDText,scoreText;
	@FXML	
	private TextArea equipmentText;
	@FXML
	private TextField hotelNameText,hotelAddressText,introductionText;
	@FXML
	private ComboBox<String> cityText,cycleText,levelText;
	@FXML
	private ImageView rightImage,rightImage1;


	HotelBLService hotelBLController;
	OrderBLService orderBLController;
	SourceBLService sourceBLController;
	String hotelID = IDReserve.getInstance().getUserID();

	public HotelController() {
		hotelBLController = HotelBLController.getInstance();
		orderBLController = OrderBLController.getInstance();
		sourceBLController = SourceBLController.getInstance();
	}

	@FXML
	private Pane hotelModifyPane;
	@FXML
	private Pane hotelInfoPane;


	@FXML
	private Label hotelNameInDetail, hotelIDInDetail, cityInDetail, levelInDetail, scoreInDetail, cycleInDetail,
	introductionInDetail, equipmentInDetail, hotelAddressInDetail;

	List<HotelEvaluationVO> commentList;
	/**
	 * @description: 在查看酒店详情时，需要显示酒店详情及酒店的所有评论
	 * @author 61990
	 * @lastChangedBy Harvey Gong
	 * @updateTime 2016/12/7 
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainHotelInfo.png");
		changePicture(rightImage1, "mainHotelInfoEdit.png");
		
		//显示酒店详情
		HotelVO hotelVO = hotelBLController.getHotelInfo(hotelID);
		initHotelDetail(hotelVO);

		//显示酒店的所有评论
		Iterator<HotelEvaluationVO> evaluations = orderBLController.getEvaluations(hotelID);

		commentList=new ArrayList<HotelEvaluationVO>();
		while(evaluations.hasNext()){
			commentList.add(evaluations.next());
		}
		initCommentTable(commentList);
	}	


	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param hotelVO
	 * @describe 初始化酒店详情
	 */
	private void initHotelDetail(HotelVO hotelVO) {

		hotelNameInDetail.setText(hotelVO.hotelName);
		hotelIDInDetail.setText(hotelVO.hotelID);
		hotelAddressInDetail.setText(hotelVO.address);
		cityInDetail.setText(hotelVO.city);
		cycleInDetail.setText(hotelVO.circle);
		levelInDetail.setText(hotelVO.level);
		scoreInDetail.setText(Double.toString(hotelVO.score));
		equipmentInDetail.setText(hotelVO.equipment);
		introductionInDetail.setText(hotelVO.introduction);
	}

	@FXML
	TableView<EvaluationTable> evaluationTable;
	@FXML
	private TableColumn<EvaluationTable, String> guestIDColumn,scoreColumn, commentColumn;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param orderVO
	 * @describe 初始化评价详情
	 */
	private void initCommentTable(List<HotelEvaluationVO> commentList) {
		evaluationTable.getItems().clear();
		List<EvaluationTable> dataList = new LinkedList<EvaluationTable>();
		for (int i = 0; i < commentList.size(); i++) {
			HotelEvaluationVO temp = commentList.get(i);
			dataList.add(new EvaluationTable(temp.guestID, Double.toString(temp.score), temp.comment));
		}

		ObservableList<EvaluationTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < dataList.size(); i++) {
			data.add(dataList.get(i));
		}
		guestIDColumn.setCellValueFactory(cellData -> cellData.getValue().guestID);
		scoreColumn.setCellValueFactory(cellData -> cellData.getValue().score);
		commentColumn.setCellValueFactory(cellData -> cellData.getValue().comment);

		evaluationTable.setItems(data);
	}


	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/6
	 * @describe 点击修改按钮，显示可编辑的界面，内容为酒店详情
	 * 同时得初始化城市的下拉框以供选择
	 */
	@FXML
	protected void modify() {

		HotelVO hotelVO = hotelBLController.getHotelInfo(hotelID);

		System.out.println(hotelVO.circle);

		hotelNameText.setText(hotelVO.hotelName);
		hotelIDText.setText(hotelVO.hotelID);
		hotelAddressText.setText(hotelVO.address);
		cityText.setValue(hotelVO.city);
		cycleText.setValue(hotelVO.circle);
		levelText.setValue(hotelVO.level);
		scoreText.setText(Double.toString(hotelVO.score));
		equipmentText.setText(hotelVO.equipment);
		introductionText.setText(hotelVO.introduction);

		cityText.setOnShowing(new CityShowingHandler());
		cityText.valueProperty().addListener(new CityChangedListener());
		levelText.setOnShowing(new LevelShowingHandler());
		
		Iterator<String> circles = sourceBLController.getCircles(hotelVO.city);
		while(circles.hasNext()){
			cycleText.getItems().add(circles.next());
		}

		hotelModifyPane.setVisible(true);
		hotelInfoPane.setVisible(false);
	}

	class CityShowingHandler implements EventHandler<Event>{
		@Override
		public void handle(Event arg0) {
			if(cityText.getItems().size() <= 1){
				Iterator<String> cities = sourceBLController.getCities();
				while(cities.hasNext()){
					cityText.getItems().add(cities.next());
				}
			}
		}
	}

	class CityChangedListener implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue arg0, String preCity, String selectedCity) {
			cycleText.getItems().clear();
			Iterator<String> circles = sourceBLController.getCircles(selectedCity);
			while(circles.hasNext()){
				cycleText.getItems().add(circles.next());
			}
			cycleText.setValue(cycleText.getItems().get(0));
		}
	}

	class LevelShowingHandler implements EventHandler<Event>{
		@Override
		public void handle(Event arg0) {
			if(levelText.getItems().size() <= 1){
				Iterator<String> levels = sourceBLController.getLevels();
				while(levels.hasNext()){
					levelText.getItems().add(levels.next());
				}	
			}
		}
	}

	/**
	 * @description 点击保存按钮
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/6
	 * @describe 点击保存按钮
	 */
	@FXML
	protected void save() {

		HotelVO hotelVO = new HotelVO();

		hotelVO.hotelID = hotelID;
		hotelVO.hotelName=hotelNameText.getText();
		hotelVO.address=hotelAddressText.getText();
		hotelVO.city=cityText.getValue();
		hotelVO.circle=cycleText.getValue();
		hotelVO.level=levelText.getValue();
		hotelVO.equipment=equipmentText.getText();
		hotelVO.introduction=introductionText.getText();

		hotelBLController.updateHotelInfo(hotelVO);
		

		initHotelDetail(hotelVO);

		hotelModifyPane.setVisible(false);
		hotelInfoPane.setVisible(true);
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/6
	 * @describe 点击取消按钮
	 */
	@FXML
	protected void cancel() {
		hotelModifyPane.setVisible(false);
		initHotelDetail(hotelBLController.getHotelInfo(hotelID));
		hotelInfoPane.setVisible(true);
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("hotelImage/hotelPane/"+path)));	
	}
}
