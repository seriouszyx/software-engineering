package presentation.webMarketerUI.controller;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import presentation.Table.DatePromotionTable;
import vo.SpecialSpanPromotionVO;


/**
 * @Description:网站管理特定期间策略的界面控制器
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月7日 下午8:43:37
 */
public class DatePromotionController {
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

	private PromotionBLService promotionController;

	public DatePromotionController() {
		promotionController = PromotionBLController.getInstance();
	}

	/**
	 * @description 初始化网站的所有特定期间策略
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainCommonPromotion.png");
		table.getItems().clear();

		//调用promotion的方法获取网站的特定期间策略
		Iterator<SpecialSpanPromotionVO> specialSpanPromotions = promotionController.getWebSpecialSpanPromotions();

		ObservableList<DatePromotionTable> data = FXCollections.observableArrayList();
		while(specialSpanPromotions.hasNext()){
			SpecialSpanPromotionVO vo = specialSpanPromotions.next();
			data.add(new DatePromotionTable(vo.promotionName,String.valueOf(vo.discount),
					vo.startDate, vo.endDate));
		}

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDate);
		endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDate);
		discountColumn.setCellValueFactory(cellData -> cellData.getValue().discount);

		table.setItems(data);
		initDatePicker();

	}
	void initDatePicker(){
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
		startDatePicker.setDayCellFactory(dayCellFactory1);

		startDatePicker.setValue(LocalDate.now());
		Callback<DatePicker, DateCell> dayCellFactory = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(
								startDatePicker.getValue().plusDays(1))
								) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}   
					}
				};
			}
		};
		endDatePicker.setDayCellFactory(dayCellFactory);
		endDatePicker.setValue(LocalDate.now().plusDays(1));
	}
	String preName;
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 * @获取表中内容直接修改
	 */
	@FXML
	protected void modifyOne() {
		try{
			setModifyText(table.getSelectionModel().getSelectedItem().getName(),
					table.getSelectionModel().getSelectedItem().getDiscount(),
					table.getSelectionModel().getSelectedItem().getStartDate(),
					table.getSelectionModel().getSelectedItem().getEndDate());
			preName=table.getSelectionModel().getSelectedItem().getName();
			nameText.setDisable(true);
			modifyPane.setVisible(true);
			addBt.setVisible(false);
		} catch (Exception e) {
			System.out.println("请选定");
		}
	}
	/**
	 * @description 保存网站的特定期间策略
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 */
	@FXML
	protected void savePromotion() {
		try {

			// 调用promotion的更新特定期间策略的方法
			promotionController.updateWebSpecialSpanPromotion(encapsulateVO());
			nameText.setDisable(false);
			modifyPane.setVisible(false);
			addBt.setVisible(true);
			setModifyText("","", LocalDate.now(), LocalDate.now().plusDays(1));
			initialize();
		} catch (Exception e) {
			System.out.println("保存失败");
		}
	}


	/**
	 * @description 添加特定期间的策略
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 * @添加双十一策略
	 */
	@FXML
	protected void addPromotion() {
		try {
			promotionController.addWebSpecialSpanPromotion(encapsulateVO());
			initialize();
		} catch (Exception e) {
			System.out.println("保存失败");
		}

	}

	/**
	 * @description 取消修改
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/30
	 */
	@FXML
	protected void cancelModifyPromotion() {
		modifyPane.setVisible(false);
		nameText.setDisable(false);
		addBt.setVisible(true);
		setModifyText("","", LocalDate.now(), LocalDate.now().plusDays(1));
		initialize();	
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
		//通过hotelID和唯一promotionName删除此条promotion
		promotionController.deleteWebSpecialSpanPromotion(promotionName);

		initialize();	
	}

	private void setModifyText(String name,String discount,LocalDate startDate ,LocalDate endDate) {

		nameText.setText(name);
		discountText.setText(discount);
		startDatePicker.setValue(startDate);
		endDatePicker.setValue(endDate);
	}

	/**
	 * @Description:将修改或添加的信息封装成一个vo
	 * @return
	 * SpecialSpanPromotionVO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月7日 下午9:02:46
	 */
	private SpecialSpanPromotionVO encapsulateVO(){
		SpecialSpanPromotionVO vo = new SpecialSpanPromotionVO();

		vo.discount=Double.parseDouble(discountText.getText());
		vo.promotionName = nameText.getText();
		vo.startDate =  startDatePicker.getValue();
		vo.endDate = endDatePicker.getValue();
		return vo;
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("marketerImage/datePromotionPane/"+path)));	
	}
}