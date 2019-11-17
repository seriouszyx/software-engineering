package presentation.hotelWorkerUI.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import businessLogic.orderBL.OrderBLController;
import businessLogicService.orderBLService.OrderBLService;
import exception.verificationException.CheckInException;
import exception.verificationException.CheckOutException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.PopUp.PopUp;
import presentation.Table.OrderTable;
import utilities.IDReserve;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.OrderGeneralVO;
import vo.OrderVO;

/**
 * @description 酒店查看订单的界面
 * @author 61990
 *
 *         lastChangeBy charles updateTime 2016/12/8
 * 
 * 
 *         TODO
 *         冯俊杰：checkIn、checkOut后刷新orderGenerals，获取当前焦点位置<异常／未执行>，或者背景虚化了看不出来就不用区分了
 */
public class OrderController {

	private OrderBLService orderBLController;

	private final UserType hotelWorker = UserType.HOTEL_WORKER;

	/*
	 * 订单概况
	 */
	private final String hotelID = IDReserve.getInstance().getUserID();

	private Iterator<OrderGeneralVO> orderGenerals;

	/*
	 * 订单详情
	 */
	private String orderID;

	private OrderVO orderVO;

	// 订单概况
	@FXML
	private Pane orderCheck;

	@FXML
	private TableView<OrderTable> table;
	@FXML
	private ImageView rightImage, rightImage1,rightImage3,rightImage4;

	List<OrderGeneralVO> orderVOlist;

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7 构造函数，初始化成员变量
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainOrderList.png");
		changePicture(rightImage3, "mainCheckIn.png");
		changePicture(rightImage4, "mainCheckOut.png");
		changePicture(rightImage1, "mainOrderDetail.png");
		orderBLController = OrderBLController.getInstance();

		// 通过hotelID得到orderGeneralVOs
		Iterator<OrderGeneralVO> orderGenerals = orderBLController.getAllOrderGenerals(hotelID, hotelWorker);
		initOrderCheck(orderGenerals);

		checkInBt1.setVisible(false);
		checkOutBt1.setVisible(false);
	}

	@FXML
	private TableColumn<OrderTable, String> orderIDColumn, guestIDColumn, nameColumn, phoneColumn, priceColumn,
			checkInTimeColumn, stateColumn, checkOutTimeColumn;

	@FXML
	private Button checkInBt1, checkOutBt1;

	/*
	 * 订单概况界面可分别查看各个类型的订单
	 */
	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @打开所有订单概况
	 */
	@FXML
	protected void searchAlldOrder() {
		orderGenerals = orderBLController.getAllOrderGenerals(hotelID, hotelWorker);
		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @打开未执行订单概况
	 */
	@FXML
	protected void searchUnexecutedOrder() {

		orderGenerals = orderBLController.getSpecialOrderGenerals(hotelID, hotelWorker, OrderState.UNEXECUTED);

		checkInBt1.setVisible(true);
		checkOutBt1.setVisible(false);

		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @打开已执行订单概况
	 */
	@FXML
	protected void searchExecutedOrder() {

		orderGenerals = orderBLController.getSpecialOrderGenerals(hotelID, hotelWorker, OrderState.EXECUTED);

		checkInBt1.setVisible(false);
		checkOutBt1.setVisible(false);

		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy 619901
	 * @updateTime 2016/12/16
	 * @打开未退房订单概况
	 */
	@FXML
	protected void searchNotCheckOutOrder() {
		orderGenerals = orderBLController.getAllHotelCheckOutOrderGeneral(hotelID, false);

		checkInBt1.setVisible(false);
		checkOutBt1.setVisible(true);

		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @打开异常订单概况
	 */
	@FXML
	protected void searchAbnormalOrder() {

		orderGenerals = orderBLController.getSpecialOrderGenerals(hotelID, hotelWorker, OrderState.ABNORMAL);

		checkInBt1.setVisible(true);
		checkOutBt1.setVisible(false);

		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @打开已撤销订单概况
	 */
	@FXML
	protected void searchCancelledOrder() {

		orderGenerals = orderBLController.getSpecialOrderGenerals(hotelID, hotelWorker, OrderState.CANCELLED);

		checkInBt1.setVisible(false);
		checkOutBt1.setVisible(false);

		initOrderCheck(orderGenerals);
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param orderVO
	 * @describe 选择并初始化订单概况界面
	 */
	private void initOrderCheck(Iterator<OrderGeneralVO> orderGenerals) {
		table.getItems().clear();

		ObservableList<OrderTable> data = FXCollections.observableArrayList();

		while (orderGenerals.hasNext()) {
			OrderGeneralVO vo = orderGenerals.next();
			OrderTable orderTable = new OrderTable(vo.orderID, vo.guestID, vo.name, vo.phone,
					vo.expectExecuteTime.toString(), vo.expectLeaveTime.toString(), vo.price + "",
					vo.state.getChineseOrderState());
			data.add(orderTable);
		}

		orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().orderID);
		guestIDColumn.setCellValueFactory(cellData -> cellData.getValue().guestID);
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
		phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phone);
		checkInTimeColumn.setCellValueFactory(cellData -> cellData.getValue().checkInTime);
		checkOutTimeColumn.setCellValueFactory(cellData -> cellData.getValue().checkOutTime);
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().price);
		stateColumn.setCellValueFactory(cellData -> cellData.getValue().state);

		table.setItems(data);
	}

	/*
	 * 订单详情初始化
	 */
	@FXML
	private Pane orderDetail;
	@FXML
	private TextField orderScore;
	@FXML
	private TextArea orderComment;

	@FXML
	private Button detail_state, checkInBt, checkOutBt;
	@FXML
	private Label detail_ID, detail_Hotel, detail_address, detail_roomType, detail_roomNum, detail_personNum,
			detail_roomNumber, detail_price, detail_personName, detail_phone, detail_createTime, detail_expectLeaveTime,
			detail_expectTime, detail_message, detail_checkInTime, detail_checkOutTime;

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @跳转订单详情界面
	 */
	@FXML
	protected void orderDetail() {
		orderID = table.getSelectionModel().getSelectedItem().getOrderID();

		orderDetail.setVisible(true);
		orderCheck.setVisible(false);

		orderComment.setDisable(true);
		orderScore.setDisable(true);

		orderVO = orderBLController.getOrderDetail(orderID);
		initOrderDetail(orderVO);

		// show 相应的button
		if (orderVO.orderGeneralVO.state == OrderState.EXECUTED) {
			checkOutBt.setVisible(true);
			checkInBt.setVisible(false);
			if (orderVO.orderGeneralVO.hasCheckOut) {
				checkOutBt.setDisable(true);
			} else {
				checkOutBt.setDisable(false);
			}
		}
		if (orderVO.orderGeneralVO.state == OrderState.UNEXECUTED
				|| orderVO.orderGeneralVO.state == OrderState.ABNORMAL) {
			checkOutBt.setVisible(false);
			checkInBt.setVisible(true);
		}
	}

	@FXML
	private Label checkInOrderID, checkInName;
	@FXML
	private TextField checkInRoomNum, checkInMinute, checkInHour;
	@FXML
	private DatePicker checkInLeaveDate;
	@FXML
	private Pane checkInPane;

	/**
	 * @author 61990
	 * @throws IOException
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @办理入住
	 */
	// 订单详情执行
	@FXML
	protected void checkIn() {

		checkInPane.setVisible(true);
		orderDetail.setDisable(true);
		orderDetail.setOpacity(0.2);

		initCheckInWindow(orderVO.orderGeneralVO.orderID, orderVO.orderGeneralVO.name,
				orderVO.orderGeneralVO.expectLeaveTime.toLocalDate(),
				orderVO.orderGeneralVO.expectLeaveTime.getHour() + "",
				orderVO.orderGeneralVO.expectLeaveTime.getMinute() + "");
	}

	void initCheckInWindow(String orderID, String name, LocalDate date, String hour, String minute) {
		checkInOrderID.setText(orderID);
		checkInName.setText(name);
		checkInLeaveDate.setValue(date);
		checkInMinute.setText(minute);
		checkInHour.setText(hour);
	}

	// 订单概况执行
	@FXML
	protected void checkIn2() {
		try {
			checkInPane.setVisible(true);
			orderCheck.setDisable(true);
			orderCheck.setOpacity(0.2);

			initCheckInWindow(table.getSelectionModel().getSelectedItem().getOrderID(),
					table.getSelectionModel().getSelectedItem().getName(),
					table.getSelectionModel().getSelectedItem().getCheckOutTime().toLocalDate(),
					table.getSelectionModel().getSelectedItem().getCheckOutTime().getHour() + "",
					table.getSelectionModel().getSelectedItem().getCheckOutTime().getMinute() + "");
		} catch (Exception e) {
			new PopUp("请选择订单", "");
		}
	}

	/**
	 * @author 61990
	 * @throws IOException
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @确定入住
	 */
	@FXML
	protected void sureCheckIn() {
		if (!checkInRoomNum.getText().equals("")) {
			final LocalDateTime checkInTime = LocalDateTime.now();
			final LocalDateTime expectLeaveTime = LocalDateTime.of(checkInLeaveDate.getValue(),
					LocalTime.of(Integer.parseInt(checkInHour.getText()), Integer.parseInt(checkInMinute.getText())));

			final CheckInVO checkInVO = new CheckInVO(checkInOrderID.getText(), checkInRoomNum.getText(), 
					checkInTime, expectLeaveTime);
			
			ResultMessage result = ResultMessage.FAIL;
			try {
				result = orderBLController.updateCheckIn(checkInVO);

				searchUnexecutedOrder();
				cancel();
				cancelCheckIn();
				if (result == ResultMessage.SUCCESS) {
					new PopUp("入住成功", "congratulation");
					
					checkInRoomNum.setText("");
					checkInHour.setText("12");
					checkInMinute.setText("00");
				} else {
					new PopUp("入住失败", "so sorry");
					
				}
			} catch (CheckInException e) {
				e.printStackTrace();
				new PopUp("该客户预计入住日期与当前日期不符合，请检查后重试", "");
			}
		} else {
			new PopUp("请填写相关信息，谢谢！", "so soory");
		}

	}

	@FXML
	protected void cancelCheckIn() {
		checkInPane.setVisible(false);
		orderCheck.setDisable(false);
		orderDetail.setDisable(false);
		orderCheck.setOpacity(1);
		orderDetail.setOpacity(1);
		checkInRoomNum.setText("");
		checkInHour.setText("12");
		checkInMinute.setText("00");
	}

	@FXML
	private Label checkOutOrderID, checkOutName;
	@FXML
	private Pane checkOutPane;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @办理退房
	 */
	// 订单详情执行
	@FXML
	protected void checkOut() {
		checkOutOrderID.setText(orderVO.orderGeneralVO.orderID);
		checkOutName.setText(orderVO.orderGeneralVO.name);
		checkOutPane.setVisible(true);
		orderDetail.setDisable(true);
		orderDetail.setOpacity(0.2);
	}

	// 订单概况执行
	@FXML
	protected void checkOut2() {
		try {
			checkOutOrderID.setText(table.getSelectionModel().getSelectedItem().getOrderID());
			checkOutName.setText(table.getSelectionModel().getSelectedItem().getName());
			checkOutPane.setVisible(true);
			orderCheck.setDisable(true);
			orderCheck.setOpacity(0.2);
		} catch (Exception e) {
			new PopUp("请选择订单", "");
		}
	}

	@FXML
	protected void cancelCheckOut() {
		checkOutPane.setVisible(false);
		orderCheck.setDisable(false);
		orderDetail.setDisable(false);
		orderCheck.setOpacity(1);
		orderDetail.setOpacity(1);
	}

	@FXML
	protected void sureCheckOut(){

		
		final LocalDateTime checkOutTime = LocalDateTime.now();
		final CheckOutVO checkOutVO = new CheckOutVO(checkOutOrderID.getText(), checkOutTime);
		
		System.out.println(checkOutVO.orderID);
		System.out.println(checkOutVO.checkOutTime);
		
		ResultMessage result = ResultMessage.FAIL;
		try {
			result = orderBLController.updateCheckOut(checkOutVO);
		} catch (CheckOutException e) {
			e.printStackTrace();
			new PopUp("该客户预计离开日期与当前日期不符合，请检查后重试", "");
		}
		searchNotCheckOutOrder();
		cancel();
		cancelCheckOut();
			//	TODO 后期可以把这些放在界面里，不弹框
		if (result == ResultMessage.SUCCESS) {
			new PopUp("退房成功", "congratulation");	
						
		}else {
			new PopUp("退房失败", "");	
			
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @跳转订单概况界面
	 */
	@FXML
	protected void cancel() {
		orderDetail.setVisible(false);
		orderCheck.setVisible(true);
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @param orderVO
	 * @describe 初始化订单详情界面
	 */
	private void initOrderDetail(OrderVO orderVO) {
		detail_ID.setText(orderVO.orderGeneralVO.hotelID);
		detail_Hotel.setText(orderVO.orderGeneralVO.hotelName);
		detail_address.setText(orderVO.orderGeneralVO.hotelAddress);
		detail_roomType.setText(orderVO.roomType.getChineseRoomType());
		detail_roomNum.setText(orderVO.roomNumCount + "");
		detail_roomNumber.setText(orderVO.roomNumber);
		detail_personNum.setText(orderVO.expectGuestNumCount + "");
		detail_personName.setText(orderVO.orderGeneralVO.name);
		detail_phone.setText(orderVO.orderGeneralVO.phone);
		detail_createTime.setText(orderVO.createTime.toString());
		detail_expectTime.setText(orderVO.orderGeneralVO.expectExecuteTime.toString());
		detail_expectLeaveTime.setText(orderVO.orderGeneralVO.expectLeaveTime.toString());
		detail_checkInTime.setText(orderVO.checkInTime.toString());
		detail_checkOutTime.setText(orderVO.checkOutTime.toString());
		detail_price.setText(Double.toString(orderVO.orderGeneralVO.price));
		detail_state.setText(orderVO.orderGeneralVO.state.getChineseOrderState());
		detail_message.setText(orderVO.message);

		if (orderVO.score == -1.0) {
			orderScore.setText("订单未评价");
		} else {
			orderScore.setText(Double.toString(orderVO.score));
			orderComment.setText(orderVO.comment);
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path) {
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("hotelImage/orderPane/" + path)));
	}
}
