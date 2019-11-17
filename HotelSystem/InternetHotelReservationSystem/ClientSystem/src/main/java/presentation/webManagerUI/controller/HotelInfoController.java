package presentation.webManagerUI.controller;

import java.util.Iterator;

import businessLogic.sourceBL.SourceBLController;
import businessLogic.userBL.UserController;
import businessLogicService.sourceBLService.SourceBLService;
import businessLogicService.userBLService.UserBLService;
import exception.verificationException.UserInexistException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import presentation.PopUp.PopUp;
import vo.HotelVO;

public class HotelInfoController {

	@FXML
	private ComboBox<String> cityInput,cycleInput;

	@FXML
	private ComboBox<String>levelInput;
	@FXML
	private TextField hotelName,address;
	@FXML
	private TextArea equipment;
//TODO equipment的你直接用
	private SourceBLService sourceBLController;
	private UserBLService userBLController;
	@FXML
	private ImageView rightImage;
	
	public HotelInfoController() {
		sourceBLController = SourceBLController.getInstance();
		userBLController = UserController.getInstance();
	}
	
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainHotelAdd.png");
		String firstCity = sourceBLController.getCities().next();
		String firstCircle = sourceBLController.getCircles(firstCity).next();
		cityInput.setValue(firstCity);
		cycleInput.setValue(firstCircle);
		levelInput.setValue("1");
		
		cityInput.setOnShowing(new CityShowingHandler());
		cityInput.valueProperty().addListener(new CityChangedListener());
		levelInput.setOnShowing(new LevelShowingHandler());
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/9
	 * @添加酒店信息
	 */
	@FXML
	protected void addHotel(){
		
		try {
			
			HotelVO newHotel = new HotelVO();
			newHotel.hotelName = hotelName.getText();
			newHotel.city = cityInput.getValue();
			newHotel.circle = cycleInput.getValue();
			newHotel.level = String.valueOf(levelInput.getValue());
			newHotel.address = address.getText();
			newHotel.equipment=equipment.getText();
			
			if(hotelName.getText().equals("")||address.getText().equals("")){
				new PopUp("请填写酒店名和地址", "");	
			}else{
				HotelVO hotelVO = userBLController.addHotel(newHotel);
				new PopUp("添加成功\n你的账号是"+hotelVO.hotelID+"\n你的密码是qwertyuiop123456", "");	
			}
		} catch (UserInexistException e) {
			e.printStackTrace();
			new PopUp("请检查输入的编号", "sorry");
		}
	}
	
	class CityShowingHandler implements EventHandler<Event>{
		@Override
		public void handle(Event arg0) {
			if(cityInput.getItems().size() <= 1){
				Iterator<String> cities = sourceBLController.getCities();
				while(cities.hasNext()){
					cityInput.getItems().add(cities.next());
				}
			}
		}
	}

	class CityChangedListener implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue arg0, String preCity, String selectedCity) {
			cycleInput.getItems().clear();
			Iterator<String> circles = sourceBLController.getCircles(selectedCity);
			while(circles.hasNext()){
				cycleInput.getItems().add(circles.next());
			}
			cycleInput.setValue(cycleInput.getItems().get(0));
		}
	}

	class LevelShowingHandler implements EventHandler<Event>{
		@Override
		public void handle(Event arg0) {
			if(levelInput.getItems().size() <= 1){
				Iterator<String> levels = sourceBLController.getLevels();
				while(levels.hasNext()){
					levelInput.getItems().add(levels.next());
				}	
			}
		}
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("managerImage/hotelInfoPane/"+path)));	
	}
}
