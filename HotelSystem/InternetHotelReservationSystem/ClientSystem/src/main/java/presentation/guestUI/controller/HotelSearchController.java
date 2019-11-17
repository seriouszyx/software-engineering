package presentation.guestUI.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import businessLogic.hotelBL.HotelBLController;
import businessLogic.orderBL.OrderBLController;
import businessLogic.sourceBL.SourceBLController;
import businessLogic.userBL.UserController;
import businessLogicService.hotelBLService.HotelBLService;
import businessLogicService.orderBLService.OrderBLService;
import businessLogicService.sourceBLService.SourceBLService;
import businessLogicService.userBLService.UserBLService;
import exception.verificationException.UserInexistException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import presentation.PopUp.PopUp;
import presentation.Table.EvaluationTable;
import presentation.Table.HotelTable;
import presentation.Table.OrderTable;
import presentation.Table.TypeTable;
import utilities.IDReserve;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import utilities.enums.SearchCriteriaType;
import vo.GuestVO;
import vo.HotelEvaluationVO;
import vo.HotelVO;
import vo.OrderGeneralVO;
import vo.OrderVO;
import vo.PreOrderVO;
import vo.RoomInfoVO;
import vo.SearchCriteriaVO;
/**
 * @author 61990
 * @控制酒店预定界面
 * @version 11.27
 * 
 * lastChangeBy charles
 * updateTime 2016/12/7
 */
public class HotelSearchController {

	
	
	// 加载HotelSearch相关界面
	@FXML
	private Pane cityAndCircle, hotelCheck, hotelDetail, hotelChoose, createPane;

	
	
	// city and cycle choose 内容
	@FXML
	private ComboBox<String> cycleChoose;
	@FXML
	private ComboBox<String> cityChoose;

	private String guestID;

	//当点击酒店详情或直接在酒店列表预订酒店时，保存当前选择的酒店的id
	private String selectedHotelID ;
	private String selectedRoomType ;
	private String remainNumOfselectedRoomType;

	//当房型改变时，改变此originPrice的值
	private int originPrice;

	//预期执行时间
	private LocalDate executeDate;
	//预期离开时间
	private LocalDate leaveDate;
	//用于存储客户入住的天数，当入住日期和退房日期改变时，改变此变量的值
	private int lastDays;
	@FXML
	private ImageView rightImage,rightImage1,rightImage2,rightImage3,rightImage5;
	

	//BLcontroller
	private OrderBLService orderBLController;
	private SourceBLService sourceBLController;
	private HotelBLService hotelBLController;
	private UserBLService userBLController;

	public HotelSearchController() {
		guestID = IDReserve.getInstance().getUserID();

		orderBLController = OrderBLController.getInstance();
		sourceBLController = SourceBLController.getInstance();
		userBLController = UserController.getInstance();

		//将hotelBL的浏览的guestID设置为当前用户的id
		hotelBLController = HotelBLController.getInstance();
		hotelBLController.setGuestID(guestID);
	}

	@FXML
	private ComboBox<Integer> guestNumInOrder,roomCountInOrder, hourInOrder, hourInOrder2,minuteInOrder, minuteInOrder2;

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8 
	 */
	@FXML
	private void initialize() {
		initCityAndCircles();
		changePicture(rightImage, "mainCircleChoose.png");
		changePicture(rightImage1, "mainHotelDetail.png");
		changePicture(rightImage2, "mainHotelList.png");
		changePicture(rightImage3, "mainOrderCommit.png");
		changePicture(rightImage5, "mainHotelFilter.png");
		
	}

	//TODO 城市商圈选择界面
	
	private void initCityAndCircles() {
		String fistCity = sourceBLController.getCities().next();
		cityChoose.setValue(fistCity);
		cycleChoose.setValue(sourceBLController.getCircles(fistCity).next());

		//初始化商圈列表
		Iterator<String> circles = sourceBLController.getCircles(fistCity);
		while(circles.hasNext()){
			cycleChoose.getItems().add(circles.next());
		}

		cityChoose.setOnShowing(new CityChooseHandler());
		cityChoose.valueProperty().addListener(new CityChangedListener());
	}

	/**
	 * @Description:点击城市选择框时，初始化城市列表
	 * @author:Harvey Gong
	 * @lastChangedBy:Harvey Gong
	 * @time:2016年12月12日 下午12:33:24
	 */
	class CityChooseHandler implements EventHandler<Event>{
		@Override
		public void handle(Event arg0) {
			if(cityChoose.getItems().size() <= 1){
				Iterator<String> cities = sourceBLController.getCities();
				while(cities.hasNext()){
					cityChoose.getItems().add(cities.next());
				}
			}
		}
	}

	/**
	 * @Description:当改变城市时，同时改变商圈combobox的值
	 * @author:Harvey Gong
	 * @lastChangedBy:Harvey Gong
	 * @time:2016年12月12日 下午12:32:44
	 */
	class CityChangedListener implements ChangeListener<String>{
		@Override
		public void changed(ObservableValue<? extends String> arg0, String preCity, String newCity) {
			cycleChoose.getItems().clear();
			Iterator<String> circles = sourceBLController.getCircles(newCity);
			while(circles.hasNext()){
				cycleChoose.getItems().add(circles.next());
			}
			cycleChoose.setValue(cycleChoose.getItems().get(0));
		}
	}


	//TODO Hotel概况浏览界面

	/**
	 * 返回城市商圈的选择
	 */
	@FXML
	protected void openSwitchCityCircle(){
		cityAndCircle.setVisible(true);
		hotelCheck.setVisible(false);
	}

	@FXML
	TableView<HotelTable> hotelTable;
	@FXML
	private TableColumn<HotelTable, String>	hotelIDColumn3,hotelNameColumn3, addressColumn3, cityColumn3, cycleColumn3, minPriceColumn3,hasOrderColumn3, levelColumn3, scoreColumn3;	
	
	
	/**
	 * @查看以往预定的酒店
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/23
	 */
	@FXML
	protected void openHasOrderedPane(){
		Iterator<HotelVO> hotels = hotelBLController.getAllBookedHotels();
		
		returnBT.setVisible(true);
		cyclePane.setVisible(false);
		
		cityAndCircle.setVisible(false);
		hotelCheck.setVisible(true);
		hotelChoose.setVisible(false);
		
		initHotelTable(hotels);
	}
	/**
	 * @酒店浏览界面初始化
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/23
	 */
	@FXML
	protected void openHotelCheck() {

		//通过城市和商圈,调用hotelBL的方法获得所有的酒店
		Iterator<HotelVO> hotels = hotelBLController.getHotels(cityChoose.getValue(), cycleChoose.getValue());
		
		returnBT.setVisible(false);
		cyclePane.setVisible(true);
		
		cityAndCircle.setVisible(false);
		hotelCheck.setVisible(true);
		hotelChoose.setVisible(false);

		initHotelTable(hotels);
	}

	
	/**
	 * @description 返回订单概况
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void returnHotelCheck() {
		hotelCheck.setVisible(true);
		hotelDetail.setVisible(false);
		createPane.setVisible(false);
		hotelChoose.setVisible(false);
	}


	//TODO HotelDetail界面

	@FXML
	private Label hotelNameInDetail, hotelIDInDetail, cityInDetail, LevelInDetail, ScoreInDetail, cycleInDetail,
	introductionInDetail, equipmentInDetail, hotelAddressInDetail;

	List<HotelEvaluationVO> commentList;
	List<RoomInfoVO> roomList;
	Iterator<RoomInfoVO> rooms;
	List<OrderGeneralVO> orderVOlist;
	HotelVO hotelVO;
	@FXML
	private Pane commentPane, orderPane,roomPane;
	
	/**
	 * @description 打开订单评论
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/27
	 */
	@FXML
	protected void openComment() {
		commentPane.setVisible(true);
		roomPane.setVisible(false);
		orderPane.setVisible(false);
	}
	
	/**
	 * @description 打开预定过得订单
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/27
	 */
	@FXML
	protected void openHotelOrder() {
		commentPane.setVisible(false);
		roomPane.setVisible(false);
		orderPane.setVisible(true);
	}
	/**
	 * @description 初始化酒店详情界面
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void openHotelDetail() {
		try{

			selectedHotelID = hotelTable.getSelectionModel().getSelectedItem().getHotelID();

			// 获取hotelVO，调用hotelBL的方法
			hotelVO = hotelBLController.getHotelInfo(selectedHotelID);
			hotelCheck.setVisible(false);
			hotelDetail.setVisible(true);
			initHotelDetail(hotelVO);

			rooms = hotelBLController.getHotelRoomInfo(selectedHotelID);
			initRoomTable(rooms);

			// 此客户在此目标酒店的所有订单
			orderVOlist = new ArrayList<OrderGeneralVO>();
			Iterator<OrderGeneralVO> myOrdersOfThisHotel = orderBLController.getMyOrdersOfThisHotel(IDReserve.getInstance().getUserID(), selectedHotelID);
			while (myOrdersOfThisHotel.hasNext()) {
				orderVOlist.add(myOrdersOfThisHotel.next());
			}
			initOrderCheck(orderVOlist);
			commentPane.setVisible(false);
			roomPane.setVisible(true);
			orderPane.setVisible(false);
			// 初始化此酒店的评论
			commentList = new ArrayList<HotelEvaluationVO>();
			Iterator<HotelEvaluationVO> thisHotelEvaluation = orderBLController.getEvaluations(selectedHotelID);
			while (thisHotelEvaluation.hasNext()) {
				commentList.add(thisHotelEvaluation.next());
			}
			initCommentTable(commentList);
		} catch (Exception e) {
			new PopUp("请选定酒店", "");
		}
	}

	@FXML
	TableView<TypeTable> roomTable;
	@FXML
	private TableColumn<TypeTable, String> typeColumn, roomNameColumn,roomNumColumn,remainRoomColumn, priceColumn;

	@FXML
	TableView<EvaluationTable> evaluationTable;
	@FXML
	private TableColumn<EvaluationTable, String> guestIDColumn,scoreColumn, commentColumn;

	@FXML
	private TableView<OrderTable> orderTable;
	@FXML
	private TableColumn<OrderTable, String> orderIDColumn, hotelNameColumn, addressColumn, priceColumn1,
	checkInTimeColumn, stateColumn,checkOutTimeColumn;


	/**
	 * @description 初始化房间详情界面
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 * @param Iterator<RoomInfoVO> rooms
	 */
	private void initRoomTable(Iterator<RoomInfoVO> rooms) { 	
		roomTable.getItems().clear();
		List<TypeTable> dataList = new ArrayList<TypeTable>();
		while(rooms.hasNext()){
			RoomInfoVO temp = rooms.next();

			dataList.add(new TypeTable(temp.roomType.getChineseRoomType(), 
					String.valueOf(temp.roomNum),String.valueOf(temp.remainNum),
					Double.toString(temp.price)));
		}

		ObservableList<TypeTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < dataList.size(); i++) {
			data.add(dataList.get(i));
		}
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().roomType);
		roomNumColumn.setCellValueFactory(cellData -> cellData.getValue().roomNum);
		remainRoomColumn.setCellValueFactory(cellData -> cellData.getValue().remainRoomNum);
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().price);

		roomTable.setItems(data);
	}


	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param orderVO
	 * @describe 初始化评价详情界面
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

	@FXML
	private Button returnBT;
	@FXML
	private Pane cyclePane;
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param orderVO
	 * @describe 选择并初始化订单概况界面
	 */
	private void initOrderCheck(List<OrderGeneralVO> orderVOlist) {
		orderTable.getItems().clear();
		List<OrderTable> orderList = new LinkedList<OrderTable>();
		for (int i = 0; i < orderVOlist.size(); i++) {
			OrderGeneralVO temp = orderVOlist.get(i);
			orderList.add(new OrderTable(temp.orderID, temp.hotelName, temp.hotelAddress,
					temp.expectExecuteTime.toString(),temp.expectLeaveTime.toString(),temp.price + "", temp.state.getChineseOrderState()));
		}

		ObservableList<OrderTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < orderList.size(); i++) {
			data.add(orderList.get(i));
		}
		orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().orderID);
		hotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().hotelName);
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().address);
		checkInTimeColumn.setCellValueFactory(cellData -> cellData.getValue().checkInTime);
		checkOutTimeColumn.setCellValueFactory(cellData -> cellData.getValue().checkOutTime);
		priceColumn1.setCellValueFactory(cellData -> cellData.getValue().price);
		stateColumn.setCellValueFactory(cellData -> cellData.getValue().state);

		orderTable.setItems(data);
	}


	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param hotelVO
	 * @describe 初始化酒店详情界面
	 */
	private void initHotelDetail(HotelVO hotelVO) {

		hotelNameInDetail.setText(hotelVO.hotelName);
		hotelIDInDetail.setText(hotelVO.hotelID);
		hotelAddressInDetail.setText(hotelVO.address);
		cityInDetail.setText(hotelVO.city);
		cycleInDetail.setText(hotelVO.circle);
		LevelInDetail.setText(hotelVO.level);
		ScoreInDetail.setText(Double.toString(hotelVO.score));
		equipmentInDetail.setText(hotelVO.equipment);
		introductionInDetail.setText(hotelVO.introduction);
	}


	/*
	 *  Hotel筛选界面
	 */

	@FXML
	private CheckBox box1,box2,box3,box4,box5,boxOnly;
	@FXML
	private ComboBox<Integer> minLevelInput,maxLevelInput;
	@FXML
	private ComboBox<Double> minScoreInput,maxScoreInput;
	@FXML
	private ComboBox<Integer> roomInput;
	@FXML
	private TextField minpriceInput,maxpriceInput,hotelNameInput;

	private List<CheckBox> roomTypeBox = new ArrayList<CheckBox>();
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @describe 打开酒店筛选
	 */
	@FXML
	protected void openChoose() {
		hotelCheck.setVisible(false);
		hotelChoose.setVisible(true);
		initLevelAndScore();
		initRoomTypeBox();
		
	}

	private void initRoomTypeBox() {
		//先清空一次
		roomTypeBox.clear();

		//将5种房型的box加进来
		roomTypeBox.add(box1);
		roomTypeBox.add(box2);
		roomTypeBox.add(box3);
		roomTypeBox.add(box4);
		roomTypeBox.add(box5);		
	}

	private void initLevelAndScore() {

		int maxLevel = 5;
		int maxScore = 5;
		roomInput.getItems().clear();
		for (int i = 1; i < 11; i++) {
			roomInput.getItems().add(i);
		}
		roomInput.setValue(1);
		minLevelInput.getItems().clear();
		maxLevelInput.getItems().clear();
		for (int i = 1; i <= maxLevel; i++) {
			minLevelInput.getItems().add(i);
			maxLevelInput.getItems().add(i);	
		}
		minLevelInput.setValue(1);
		maxLevelInput.setValue(5);
		
		minScoreInput.getItems().clear();
		maxScoreInput.getItems().clear();
		for (int i = 0; i <= maxScore; i++) {
			minScoreInput.getItems().add(i+0.0);
			maxScoreInput.getItems().add(i+0.0);	
		}
		minScoreInput.setValue(0.0);
		maxScoreInput.setValue(5.0);		
	}

	List<SearchCriteriaType> criteria;
	SearchCriteriaVO vo;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @describe 筛选
	 */
	@FXML
	protected void selectHotel() {

		//获取到各种搜索条件和用了哪些搜索方法
		setVOAndCriteria();

		//调用hotelBL的方法，根据搜索标准列表搜索酒店,并初始化酒店列表
		Iterator<HotelVO> searchedHotels = hotelBLController.searchHotels(criteria, vo);
		initHotelTable(searchedHotels);

		hotelCheck.setVisible(true);
		hotelChoose.setVisible(false);
	}

	/**
	 * @Description:setSearchCriteriaVO的值
	 * @return
	 * SearchCriteriaVO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午10:18:27
	 */
	private void setVOAndCriteria(){
		vo = new SearchCriteriaVO();
		criteria = new ArrayList<SearchCriteriaType>();

		if(hotelNameInput.getText().equals("")){
			criteria.add(SearchCriteriaType.HOTEL_NAME);
			vo.keyHotelName = hotelNameInput.getText();
		}
		if(boxOnly.isSelected()){
			criteria.add(SearchCriteriaType.BOOKED_ONLY);
			vo.bookedOnly = boxOnly.isSelected();
		}
		if(minLevelInput.getValue()!=null){
			criteria.add(SearchCriteriaType.LEVEL_SPAN);
			vo.minLevel = minLevelInput.getValue();
			vo.maxLevel =  maxLevelInput.getValue();
		}

		if(!minpriceInput.getText().equals("")){
			criteria.add(SearchCriteriaType.ORGIN_PRICE_SPAN);
			vo.minPrice = Double.valueOf(minpriceInput.getText());
			vo.maxPrice = Double.valueOf(maxpriceInput.getText());
		}
		if(minScoreInput.getValue()!=null){
			criteria.add(SearchCriteriaType.SCORE_SPAN);
			vo.minScore = minScoreInput.getValue();
			vo.maxScore = maxScoreInput.getValue();
		}
		if(roomInput.getValue()!=null){
			criteria.add(SearchCriteriaType.REMAIN_ROOM_NUM);
			vo.remainRoomNum = roomInput.getValue();
		}

		//选择房间类型
		List<RoomType> selectedRoomTypes = new ArrayList<RoomType>();
		for(int i = 0;i<roomTypeBox.size();i++){
			if(roomTypeBox.get(i).isSelected()){
				selectedRoomTypes.add(RoomType.getEnum(roomTypeBox.get(i).getText()));
			}
		}
		if(selectedRoomTypes.size()>0){
			vo.roomTypes = selectedRoomTypes;
			criteria.add(SearchCriteriaType.ROOM_TYPE);
		}

		criteria.add(SearchCriteriaType.NULL);

	}


	//TODO 订单生成界面
	 
	@FXML
	private ComboBox<String> roomTypeInOrder;
	@FXML
	private Label hotelNameInOrder, hotelIDInOrder, hotelAddressInOrder, previousPriceInOrder, priceOfOrder,remainNumInOrder;
	@FXML
	private TextField nameInOrder, phoneInOrder;
	@FXML
	private DatePicker expectExecuteDateInOrder, expectLeaveDateInOrder;
	@FXML
	private TextArea messageInOrder;
	GuestVO guestVO;

	/**
	 * @dscription 点击预订酒店按钮
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/11/27
	 */
	@FXML
	protected void openCreateOrder() {
		try{
		selectedHotelID = hotelTable.getSelectionModel().getSelectedItem().getHotelID();
		selectedRoomType = roomTable.getSelectionModel().getSelectedItem().getRoomType();
		remainNumOfselectedRoomType = roomTable.getSelectionModel().getSelectedItem().getRemainRoomNum();
	
		remainNumInOrder.setText(remainNumOfselectedRoomType);

		initCreateOrder();
		initEveryBox();

		roomTypeInOrder.setValue(selectedRoomType);
		
		//显示原始价格和计算折扣后的价格
		showPrice();
			}catch (Exception e) {
			new PopUp( "请选择房型" , "" );
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @点击概况预定酒店按钮
	 */
	@FXML
	protected void createOrderIncheck(){
		try{
		selectedHotelID = hotelTable.getSelectionModel().getSelectedItem().getHotelID();
		initCreateOrder();
		initEveryBox();
		remainNumInOrder.setText("");
		previousPriceInOrder.setText("");
		priceOfOrder.setText("");
	}catch (Exception e) {
		new PopUp( "请选择酒店" , "" );
	}
	}

	private void initCreateOrder(){	

		roomTypeInOrder.getItems().clear();

		hotelVO = hotelBLController.getHotelInfo(selectedHotelID);
		rooms = hotelBLController.getHotelRoomInfo(selectedHotelID);

		try {
			guestVO = (GuestVO) userBLController.getSingle(IDReserve.getInstance().getUserID());
		} catch (UserInexistException e) {
			e.printStackTrace();
		}
		nameInOrder.setText(guestVO.name);
		phoneInOrder.setText(guestVO.phone);

		while(rooms.hasNext()){
			roomTypeInOrder.getItems().add(rooms.next().roomType.getChineseRoomType());
		}

		hotelNameInOrder.setText(hotelVO.hotelName);
		hotelIDInOrder.setText(hotelVO.hotelID);
		hotelAddressInOrder.setText(hotelVO.address);

		hotelDetail.setVisible(false);
		createPane.setVisible(true);
		hotelCheck.setVisible(false);
	}

	//初始化订单生成界面中的combobox：预计最晚执行时间、预计离店时间、入住日期、离店日期、
	private void initEveryBox(){

		int minutes = 60;
		int hours = 24;
		minuteInOrder.getItems().clear();
		minuteInOrder2.getItems().clear();
		for (int i = 0; i < minutes; i++) {
			minuteInOrder.getItems().add(i);
			minuteInOrder2.getItems().add(i);
		}
		minuteInOrder.setValue(0);
		minuteInOrder2.setValue(0);

		hourInOrder.getItems().clear();
		hourInOrder2.getItems().clear();
		for (int i = 0; i < hours; i++) {
			hourInOrder.getItems().add(i);
			hourInOrder2.getItems().add(i);
		}
		hourInOrder.setValue(18);
		hourInOrder2.setValue(12);

		guestNumInOrder.getItems().clear();
		for (int i = 1; i <= sourceBLController.getMaxGuestNumEachOrder(); i++) {
			guestNumInOrder.getItems().add(i);	
		}
		guestNumInOrder.setValue(1);
	
		roomCountInOrder.getItems().clear();
		for (int i = 1; i <= sourceBLController.getMaxRoomNumEachOrder(); i++) {
			roomCountInOrder.getItems().add(i);	
		}
		roomCountInOrder.setValue(1);

		//初始入住日期和离店日期,初始入住天数为1
		executeDate = LocalDate.now();
		leaveDate = LocalDate.now().plusDays(1);
		lastDays = 1;

		expectExecuteDateInOrder.setValue(executeDate);
		expectLeaveDateInOrder.setValue(leaveDate);
		Callback<DatePicker, DateCell> dayCellFactory1 = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(
								LocalDate.now())
								) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}   
					}
				};
			}
		};
		expectExecuteDateInOrder.setDayCellFactory(dayCellFactory1);
		Callback<DatePicker, DateCell> dayCellFactory = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(
								expectExecuteDateInOrder.getValue().plusDays(1))
								) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}   
					}
				};
			}
		};
		expectLeaveDateInOrder.setDayCellFactory(dayCellFactory);
		roomTypeInOrder.valueProperty().addListener(new RoomTypeChangeListener());
		roomCountInOrder.valueProperty().addListener(new RoomNumChangeListener());
		expectExecuteDateInOrder.valueProperty().addListener(new ExpectExecuteDateChangedListener());
		expectLeaveDateInOrder.valueProperty().addListener(new ExpectLeaveDateChangedListener());
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @点击提交订单按钮
	 */
	OrderVO orderVO;

	@FXML 
	StackPane right;

	@FXML
	protected void commitOrder() {

		final LocalDateTime expectExecuteTime = LocalDateTime.of(expectExecuteDateInOrder.getValue(), 
				LocalTime.of(hourInOrder.getValue(), minuteInOrder.getValue()));
		final LocalDateTime expectLeaveTime = LocalDateTime.of(expectLeaveDateInOrder.getValue(), 
				LocalTime.of(hourInOrder2.getValue(), minuteInOrder2.getValue()));

		int price = Integer.valueOf(priceOfOrder.getText());
		OrderGeneralVO createOrderGeneral = new OrderGeneralVO(IDReserve.getInstance().getUserID(), hotelIDInOrder.getText(), 
				hotelNameInOrder.getText(), hotelAddressInOrder.getText(), expectExecuteTime, expectLeaveTime, 
				nameInOrder.getText(), phoneInOrder.getText());
		createOrderGeneral.price = price;

		int previousPrice = Integer.valueOf(previousPriceInOrder.getText());

		OrderVO createVO = new OrderVO(createOrderGeneral,previousPrice, 
				RoomType.getEnum(roomTypeInOrder.getValue()), roomCountInOrder.getValue(), guestNumInOrder.getValue(), 
				messageInOrder.getText());

		final ResultMessage msg = orderBLController.createOrder(createVO);

		if (msg == ResultMessage.SUCCESS) {
			new PopUp("订单生成成功", "订单");
		}else {
			new PopUp("房间不足或信用值不足", "订单");
		}
		hotelCheck.setVisible(true); 
		createPane.setVisible(false);
	}


	class ExpectExecuteDateChangedListener implements ChangeListener<LocalDate> {
		@Override
		public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
			//更改执行时间
			executeDate = newValue;

			//将离店时间置为入住时间的前一天
			expectLeaveDateInOrder.setValue(executeDate.plusDays(1));

			//计算入住天数
			calculateLastDays();
			//重新显示价格
			showPrice();
		}
	}

	class ExpectLeaveDateChangedListener implements ChangeListener<LocalDate> {
		@Override
		public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
			//更改离店时间
			leaveDate = newValue;
			//计算入住天数
			calculateLastDays();
			//重新显示价格
			showPrice();
		}
	}

	/**
	 * @Description:改变房间类型时，改变房间剩余数目，并改变价格的显示
	 * @author:Harvey Gong
	 * @lastChangedBy:Harvey Gong
	 * @time:2016年12月12日 下午12:34:27
	 */
	class RoomTypeChangeListener implements ChangeListener<String>{
		public void changed(ObservableValue ov, String oldValue, String roomType) {
			if(roomType != null){
				//变更所选的房间类型
				selectedRoomType = roomType;

				//显示还剩多少房间
				int remainRoomNum = hotelBLController.getRemainRoomNum(selectedHotelID,RoomType.getEnum(selectedRoomType));
				remainNumInOrder.setText(remainRoomNum+"");

				//预订房间数量初始为1
				roomCountInOrder.setValue(1);

				showPrice();				
			}
		}    
	}

	/**
	 * @Description:改变预订的房间数目时，改变价格显示
	 * @author:Harvey Gong
	 * @lastChangedBy:Harvey Gong
	 * @time:2016年12月12日 下午12:33:49
	 */
	class RoomNumChangeListener implements ChangeListener<Integer>{
		public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer roomNum) {
			showPrice();
		}    
	}

	/**
	 * @Description:计算入住天数
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:40:23
	 */
	private void calculateLastDays(){
		lastDays = executeDate.until(leaveDate).getDays();
	}

	/**
	 * @Description:显示最新价格
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午12:43:24
	 */
	private void showPrice(){
		//显示原始价格
		previousPriceInOrder.setText(Integer.toString(calculateOriginPrice()));
		//显示计算的价格
		priceOfOrder.setText(Integer.toString(calculatePrice()));
	}

	//根据客户id，选择的酒店id，入住天数，预计入住时间以及房间数量计算打折后的价格
	private int calculatePrice(){
		PreOrderVO preOrderVO = new PreOrderVO();
		preOrderVO.guestID = guestID;
		preOrderVO.hotelID = selectedHotelID;
		preOrderVO.lastDays = lastDays;
		preOrderVO.expectExecuteDate = expectExecuteDateInOrder.getValue();
		preOrderVO.roomNum = roomCountInOrder.getValue();
		preOrderVO.roomType = RoomType.getEnum(selectedRoomType);
		return orderBLController.getCalculatedPrice(preOrderVO);
	}

	//根据选择的酒店id，房间类型和入住天数，计算原始价格（打折前）
	private int calculateOriginPrice(){
		originPrice = hotelBLController.getOriginPrice(selectedHotelID, RoomType.getEnum(selectedRoomType));
		return originPrice*lastDays*roomCountInOrder.getValue();
	}
	
	private void initHotelTable(Iterator<HotelVO> hotels){
		hotelTable.getItems().clear();

		ObservableList<HotelTable> data = FXCollections.observableArrayList();
		while(hotels.hasNext()){
			HotelVO temp = hotels.next();
			data.add(new HotelTable(temp.hotelID, temp.hotelName,temp.address,
					temp.city,temp.circle,temp.orderState.getChineseOrderState(),
					Double.toString(temp.minPrice),temp.level,Double.toString(temp.score)));
		}

		hotelIDColumn3.setCellValueFactory(cellData -> cellData.getValue().hotelID);
		hotelNameColumn3.setCellValueFactory(cellData -> cellData.getValue().hotelName);
		addressColumn3.setCellValueFactory(cellData -> cellData.getValue().address);
		cityColumn3.setCellValueFactory(cellData -> cellData.getValue().city);
		cycleColumn3.setCellValueFactory(cellData -> cellData.getValue().cycle);
		minPriceColumn3.setCellValueFactory(cellData -> cellData.getValue().minPrice);
		hasOrderColumn3.setCellValueFactory(cellData -> cellData.getValue().hasOrder);
		levelColumn3.setCellValueFactory(cellData -> cellData.getValue().level);
		scoreColumn3.setCellValueFactory(cellData -> cellData.getValue().score);

		hotelTable.setItems(data);
	}

	
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("guestImage/hotelPane/"+path)));	
	}
}

