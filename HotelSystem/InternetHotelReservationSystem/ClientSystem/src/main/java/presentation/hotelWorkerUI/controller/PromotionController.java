package presentation.hotelWorkerUI.controller;

import java.time.LocalDate;
import java.util.Iterator;

import businessLogic.promotionBL.PromotionBLController;
import businessLogicService.promotionBLService.PromotionBLService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import presentation.PopUp.PopUp;
import presentation.Table.DatePromotionTable;
import utilities.IDReserve;
import utilities.enums.PromotionType;
import utilities.enums.ResultMessage;
import vo.HotelFixedPromotionVO;
import vo.SpecialSpanPromotionVO;

/**
 * @Description:酒店查看promotion的controller
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月7日 上午10:36:35
 */
public class PromotionController {
	@FXML
	private Pane modifyPane;
	@FXML
	private Button addBt;
	@FXML
	private TableView<DatePromotionTable> table;
	@FXML
	private TableColumn<DatePromotionTable, String> nameColumn,  discountColumn , startDateColumn , endDateColumn;
	@FXML
	private TextField nameText,discountText;
	@FXML
	private DatePicker startDatePicker, endDatePicker;
	@FXML
	private ImageView rightImage;

	PromotionBLService promotionBLController;
	String hotelID;

	/**
	 * @Description:在初始化该controller时，初始化promotionBLController
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 上午10:36:11
	 */
	public PromotionController() {
		promotionBLController = PromotionBLController.getInstance();
		hotelID = IDReserve.getInstance().getUserID();
	}

	/**
	 * @description 初始化酒店特定期间折扣
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/6
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainPromotion.png");
		initDatePromotion();
		initFixedPromotion();
	}
	/**
	 * @description 初始化特定期间折扣的tableView
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	protected void initDatePromotion(){
		Callback<DatePicker, DateCell> dayCellFactory1 = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		startDatePicker.setDayCellFactory(dayCellFactory1);
		Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(startDatePicker.getValue().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		endDatePicker.setDayCellFactory(dayCellFactory);
		
		Iterator<SpecialSpanPromotionVO> spanVOs = promotionBLController.getHotelSpecialSpanPromotions(hotelID);

		table.getItems().clear();
		ObservableList<DatePromotionTable> data = FXCollections.observableArrayList();
		while(spanVOs.hasNext()){
			SpecialSpanPromotionVO spanVO = spanVOs.next();
			data.add(new DatePromotionTable(spanVO.promotionName,Double.toString(spanVO.discount),spanVO.startDate,spanVO.endDate));
		}
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDate);
		endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDate);
		discountColumn.setCellValueFactory(cellData -> cellData.getValue().discount);

		table.setItems(data);
	}


	/**
	 * @description 获取表中内容直接修改双十一  
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void modifyOne() {
		try{
			
			setModifyText(table.getSelectionModel().getSelectedItem().getName(),
					table.getSelectionModel().getSelectedItem().getDiscount(),
					table.getSelectionModel().getSelectedItem().getStartDate(),
					table.getSelectionModel().getSelectedItem().getEndDate());
			modifyPane.setVisible(true);
			nameText.setDisable(true);
			addBt.setVisible(false);
		} catch (Exception e) {
			new PopUp("请选定","");
		}
	}

	/**
	 * @description 保存特定期间策略
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void savePromotion() {
		try {

			SpecialSpanPromotionVO vo = new SpecialSpanPromotionVO();
			vo.promotionName = nameText.getText();
			vo.startDate = startDatePicker.getValue();
			vo.discount = Double.valueOf(discountText.getText());
			vo.endDate = endDatePicker.getValue();
			vo.userID = hotelID;
			// 调用promotionBLController的更新特定期间折扣的方法
			promotionBLController.updateHotelSpecialSpanPromotion(vo);
			nameText.setDisable(false);
			modifyPane.setVisible(false);
			addBt.setVisible(true);
			setModifyText("","",null,null);
			initDatePromotion();
		} catch (Exception e) {
			System.out.println("保存失败");
		}
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @添加双十一策略
	 */
	@FXML
	protected void addPromotion() {
		try {
			SpecialSpanPromotionVO vo = new SpecialSpanPromotionVO();
			vo.userID = hotelID;
			vo.promotionName = nameText.getText();
			vo.discount = Double.valueOf(discountText.getText());
			vo.startDate = startDatePicker.getValue();
			vo.endDate = endDatePicker.getValue();
			ResultMessage msg = promotionBLController.addHotelSpecialSpanPromotion(vo);
			if(msg == ResultMessage.SUCCESS)
			{
				new PopUp("促销策略添加成功","促销策略添加");
			}
			else
			{
				new PopUp("促销策略添加失败","促销策略添加");
			}
			//更新显示特定期间策略
			initDatePromotion();
			setModifyText("","",null,null);
		} catch (Exception e) {
			System.out.println("保存失败");
		}
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @取消修改双十一策略
	 */
	@FXML
	protected void cancelModifyPromotion() {
	
			modifyPane.setVisible(false);
			nameText.setDisable(false);
			addBt.setVisible(true);
			setModifyText("","",null,null);
	
	}
	private void setModifyText(String name,String discount,LocalDate startDate ,LocalDate endDate) {
		nameText.setText(name);
		discountText.setText(discount);
		startDatePicker.setValue(startDate);
		endDatePicker.setValue(endDate);
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @删除双十一策略
	 */
	@FXML
	protected void deleteOne() {
		String promotionName = table.getSelectionModel().getSelectedItem().getName(); 
		promotionBLController.deleteHotelSpecialSpanPromotion(hotelID, promotionName);
		initDatePromotion();	
	}

	@FXML
	private TableView<DatePromotionTable> table1;
	@FXML
	private TableColumn<DatePromotionTable, String> nameColumn1,  discountColumn1 ;
	@FXML
	private TextField discountText1;
	@FXML
	private Label name;
	@FXML
	private Pane modifyPane1;
	/**
	 * @description 初始化其他三类
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7
	 */
	protected void initFixedPromotion(){
		table1.getItems().clear();
		Iterator<HotelFixedPromotionVO> fixedVOs = promotionBLController.getHotelFixedPromotions(hotelID);
		ObservableList<DatePromotionTable> data = FXCollections.observableArrayList();
		while(fixedVOs.hasNext()){
			HotelFixedPromotionVO fixedVO = fixedVOs.next();
			data.add(new DatePromotionTable(fixedVO.promotionType.getChinesePromotiontype(),Double.toString(fixedVO.discount)));			
		}

		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().name);
		discountColumn1.setCellValueFactory(cellData -> cellData.getValue().discount);

		table1.setItems(data);
	}
	/**
	 * @description 修改三种策略之一的折扣
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void modify(){
		name.setText(table1.getSelectionModel().getSelectedItem().getName());
		discountText1.setText(table1.getSelectionModel().getSelectedItem().getDiscount());
		modifyPane1.setVisible(true);
		initFixedPromotion();
	}
	/**
	 * @description 取消修改三种策略
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void cancel(){
		modifyPane1.setVisible(false);
		name.setText("");
		discountText1.setText("");
	}
	/**
	 * @description 保存三种策略之一的折扣
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void save(){

		HotelFixedPromotionVO vo = new HotelFixedPromotionVO();
		vo.hotelID = hotelID;
		vo.promotionType = PromotionType.getEnum(name.getText());
		vo.discount = Double.valueOf(discountText1.getText());

		promotionBLController.updateHotelFixedPromotion(vo);
		initFixedPromotion();
		 modifyPane1.setVisible(false);
		 name.setText("");
		 discountText1.setText("");
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("hotelImage/promotionPane/"+path)));	
	}

}