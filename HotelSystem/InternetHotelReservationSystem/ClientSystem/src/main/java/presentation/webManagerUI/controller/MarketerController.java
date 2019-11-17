package presentation.webManagerUI.controller;

import businessLogic.userBL.UserController;
import businessLogicService.userBLService.UserBLService;
import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.ParameterInvalidException;
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
import utilities.enums.UserType;
import vo.WebMarketerVO;

/**
 * @author 61990
 *
 */
public class MarketerController {

	WebMarketerVO marketerVO;
	
	@FXML
	private Pane modifyPane,marketerInfoPane,pane;
	@FXML
	private TextField inputID;
	@FXML
	private PasswordField password2;
	@FXML
	private Label password, marketerID, marketerID2,yourID,yourPassword;
	
	private UserBLService userBLController;
	@FXML
	private ImageView rightImage,rightImage1;
	@FXML
	private void initialize() {
		userBLController = UserController.getInstance();
		changePicture(rightImage, "mainWebMarketerSearch.png");
		changePicture(rightImage1, "mainWebMarketerAdd.png");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @搜营销人员并初始化界面        
	 */
	@FXML
	protected void search() {
		try {
			marketerVO = (WebMarketerVO) userBLController.getSingle(inputID.getText());
			
			marketerID.setText(marketerVO.userID);
	
			changePicture(rightImage, "mainWebMarketerSearchAfter.png");
			marketerInfoPane.setVisible(true);
		} catch (Exception e) {
			new PopUp("请检查输入的编号", "sorry");
		}
		
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/2
	 * @初始化营销人员信息界面
	 */
	@FXML
	protected void modifyHotel() {
		if (marketerVO != null) {
			marketerID2.setText(marketerVO.userID);
			password2.setText(marketerVO.password);

			modifyPane.setVisible(true);
			marketerInfoPane.setVisible(false);
		}
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @保存营销人員信息
	 */
	@FXML
	protected void saveModify() {

		WebMarketerVO tempMarketerVO = marketerVO;
		
		tempMarketerVO.password = password2.getText();
		
		ResultMessage message;
		try {
			message = userBLController.modify(tempMarketerVO);
			
			new PopUp(message.toString(), "congratulation");	

			modifyPane.setVisible(false);
			marketerInfoPane.setVisible(true);
		} catch (InvalidLengthInputException | InvalidInputException | PasswordInputException
				| UpdateFaiedException e) {
			e.printStackTrace();
			new PopUp("密码必须含有一个数字和密码或不能为空", "更改失败");
		} catch (UserInexistException e) {
			e.printStackTrace();
			new PopUp("请检查输入的编号", "sorry");
		}
		 		
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/2
	 * @取消更改人員信息
	 */
	@FXML
	protected void cancelModify() {
		modifyPane.setVisible(false);
		marketerInfoPane.setVisible(true);
	}
	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @添加营销人員信息
	 */
	@FXML
	protected void addMarketer() {
		
		WebMarketerVO webMarketerVO = new WebMarketerVO("","");
		try {
			webMarketerVO = (WebMarketerVO) userBLController.add(webMarketerVO, UserType.WEB_MARKETER);
			pane.setVisible(true);
			yourID.setText(webMarketerVO.userID);
			yourPassword.setText(webMarketerVO.password);
			changePicture(rightImage1, "mainWebMarketerAddAfter.png");
		} catch (ParameterInvalidException e) {
			e.printStackTrace();
		} catch (UserInexistException e) {
			e.printStackTrace();
			new PopUp("请检查输入的编号", "sorry");
		}
	}
	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @隐藏营销人員信息
	 */
	@FXML
	protected void cancel() {
		pane.setVisible(false);
		changePicture(rightImage1, "mainWebMarketerAdd.png");
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("managerImage/marketerPane/"+path)));	
	}
}
