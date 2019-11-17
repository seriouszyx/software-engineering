package presentation.webManagerUI.controller;

import businessLogic.userBL.UserController;
import businessLogicService.userBLService.UserBLService;
import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.PopUp.PopUp;
import utilities.enums.ResultMessage;
import vo.HotelWorkerVO;

/**
 * @author 61990s
 *
 */
public class HotelWorkerController {

	HotelWorkerVO hotelWorkerVO;
	@FXML
	private Pane hotelInfoPane;
	@FXML
	private Pane hotelModifyPane;
	@FXML
	private TextField inputID, hotelName2;
	@FXML
	private PasswordField password2;
	@FXML
	private Label hotelID, hotelName, password;
	
	private UserBLService userBLController;
	@FXML
	private ImageView rightImage;
	
	@FXML
	private void initialize() {
		userBLController = UserController.getInstance();
		changePicture(rightImage, "mainHotelWorker.png");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @搜索酒店并初始化界面
	 */
	@FXML
	protected void search() {
		System.out.println(inputID.getText());
		try {
			hotelWorkerVO = (HotelWorkerVO) userBLController.getSingle(inputID.getText());
			hotelInfoPane.setVisible(true);
		} catch (UserInexistException e1) {
			e1.printStackTrace();
			new PopUp("请检查输入内容", "sorry");
		}
		
		
		
		try {
			hotelID.setText(hotelWorkerVO.userID);
			hotelName.setText(hotelWorkerVO.hotelName);
			changePicture(rightImage, "mainHotelWorkerAfter.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/2
	 * @初始化酒店工作人員信息界面
	 */
	@FXML
	protected void modifyHotel() {
		if (hotelWorkerVO != null) {
			
			hotelName2.setText(hotelWorkerVO.hotelName);
			password2.setText(hotelWorkerVO.password);

			hotelModifyPane.setVisible(true);
			hotelInfoPane.setVisible(false);
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @保存酒店工作人員信息
	 */
	@FXML
	protected void saveModify() {
		
		HotelWorkerVO tempHotelWorkerVO = hotelWorkerVO;
		
		tempHotelWorkerVO.hotelName = hotelName2.getText();
		tempHotelWorkerVO.password = password2.getText();
		
		ResultMessage message;
		try {
			message = userBLController.modify(tempHotelWorkerVO);
			
			new PopUp(message.toString(), "congratulation");	

			hotelModifyPane.setVisible(false);
			hotelInfoPane.setVisible(true);
			hotelName.setText(hotelName2.getText());
//			password.setText(password2.getText());
		} catch (InvalidInputException e) {
			e.printStackTrace();
			new PopUp("酒店名称不能含有无效标识符或不能为空", "更改失败");
		} catch (PasswordInputException e) {
			e.printStackTrace();
			new PopUp("密码必须含有一个数字和密码或不能为空", "更改失败");
		} catch (UpdateFaiedException |InvalidLengthInputException e) {
			e.printStackTrace();
		} catch (UserInexistException e) {
			e.printStackTrace();
			new PopUp("请检查输入的编号", "sorry");
		}
		
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/2
	 * @取消更改酒店工作人員信息
	 */
	@FXML
	protected void cancelModify() {
		hotelModifyPane.setVisible(false);
		hotelInfoPane.setVisible(true);
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("managerImage/hotelWorkerPane/"+path)));	
	}

	
}
