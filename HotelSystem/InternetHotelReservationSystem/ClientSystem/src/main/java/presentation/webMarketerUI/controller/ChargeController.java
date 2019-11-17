package presentation.webMarketerUI.controller;

import java.io.IOException;

import businessLogic.creditBL.CreditController;
import businessLogic.userBL.UserController;
import businessLogicService.userBLService.UserBLService;
import exception.inputException.InvalidInputException;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.PopUp.PopUp;
import utilities.Detector;
import vo.GuestVO;

/**
 * @author 61990
 * @控制信用充值界面
 * @version 11.27
 */
public class ChargeController {

	//user
	private UserBLService userBLService;

	//credit
	private CreditController creditController;
	
	GuestVO guestVO;
	
	@FXML
	private Pane chargePane;

	@FXML
	private Label guestID , name , credit;
	
	@FXML
	private TextField searchGuestID , chargeNum;
	
	@FXML
	private Button searchBtn;
	@FXML
	private ImageView rightImage;
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27 构造函数，初始化成员变量
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainCharge.png");
		userBLService = UserController.getInstance();
		creditController = CreditController.getInstance();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @ 点击查询按钮，返回客户信息，初始化界面
	 */	
	@FXML
	protected void search() {
		
		if (!searchGuestID.getText().equals("")) {
			//输入不为空时才响应
			try {
				guestVO = (GuestVO) userBLService.getSingle(searchGuestID.getText());
				chargePane.setVisible(true);
				guestID.setText(guestVO.userID);
				name.setText(guestVO.name);
				credit.setText(Double.toString(guestVO.credit));
				changePicture(rightImage, "mainChargeAfter.png");
			} catch (UserInexistException e) {
				new PopUp("该用户不存在", "搜索失败");
			}
		}
	}
	
	/**
	 * @author 61990
	 * @throws IOException
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @点击保存按钮，更改信用值信息
	 */
	@FXML
	protected void saveCharge() throws IOException {
		
		if (!chargeNum.getText().equals("")) {
			try {
				new Detector().chargeDetector(chargeNum.getText());
				
				double afterCredit = creditController.charge(guestID.getText(), Double.parseDouble(chargeNum.getText()));
				
				credit.setText(Double.toString(afterCredit));
				chargeNum.setText("");
			} catch (InvalidInputException e1) {
				e1.printStackTrace();
				new PopUp("请勿输入无效符号", "充值失败");
			} catch (UserInexistException e) {
				e.printStackTrace();
				new PopUp("该用户不存在，请检查输入的ID", "充值失败");
			} catch (UpdateFaiedException e) {
				e.printStackTrace();
				new PopUp("充值失败，请重试", "充值失败");
			}
		}
	}
	 
	/**
	 * @author 61990
	 * @throws IOException
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @点击返回按钮
	 */
	@FXML
	protected void cancelCharge() {
		chargePane.setVisible(false);
		chargeNum.setText("");
		changePicture(rightImage, "mainCharge.png");
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("marketerImage/chargePane/"+path)));	
	}
}
