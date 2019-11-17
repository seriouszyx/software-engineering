package presentation.guestUI.controller;

import java.io.IOException;

import businessLogic.userBL.UserController;
import businessLogicService.userBLService.UserBLService;
import exception.verificationException.UserInexistException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.signUpUI.controller.StageController;
import utilities.IDReserve;
import vo.GuestVO;

/**
 * @author 61990
 * @lastChangedBy Harvey
 *
 */
public class GuestViewController {

	String userID = IDReserve.getInstance().getUserID();
	UserBLService userBLController = UserController.getInstance();
	@FXML
	private StackPane right;

	@FXML
	private Pane mainPane;

	@FXML
	private Label nickName;
	
	@FXML
	private ImageView leftImage,rightImage,guestBT,hotelBT,orderBT,memberBT,creditBT,signUpBT,mainBT;
	
	private Parent currentParent ;

	public GuestViewController() {
		currentParent = mainPane;
	}
	/**
	 * @author 61990
	 * @throws UserInexistException 
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7 构造函数，初始化成员变量
	 */
	@FXML
	private void initialize() throws UserInexistException {
		GuestVO guestVO = (GuestVO) userBLController.getSingle(userID);
		nickName.setText(guestVO.nickName);
		changePicture(rightImage,"mainHomeGuest.png");
		initImage();
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转客户信息界面
	 */
	@FXML
	protected void openGuestInfo() throws IOException {
		jump("GuestInfo");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转酒店查询界面
	 */
	@FXML
	protected void openHotel() throws IOException {
		jump("GuestSearchHotel");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转订单查询界面
	 */
	@FXML
	protected void openOrder() throws IOException {
		jump("GuestOrderCheck");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转会员信息界面
	 */
	@FXML
	protected void openMember() throws IOException {
		jump("GuestMemberCheck");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转信用信息界面
	 */
	@FXML
	protected void openCredit() throws IOException {
		jump("GuestCredit");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @throws IOException 跳转主界面
	 */
	@FXML
	protected void openMain() {
		right.getChildren().clear();
		right.getChildren().add(mainPane);
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy  61990
	 * @updateTime 2016/12/11
	 * @注销
	 */  

	@FXML 
	protected void logout() throws IOException{
		Stage stage=StageController.getInstance().getStage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logIn.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	/**
	 * @Description:封装跳转逻辑
	 * @param parent
	 * @param path
	 * void
	 * @author: Harvey Gong
	 * @throws IOException 
	 * @lastChangedBy: 61990
	 * @time:2016年12月8日 下午2:49:52
	 */
	private void jump(String path){
		right.getChildren().clear();
		try {
			currentParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlGuest/"+path+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		right.getChildren().add(currentParent);
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy  61990
	 * @updateTime 2016/12/21
	 * @初始化界面的图标
	 */  
	private void initImage() {
		changePicture(leftImage,"left.png");
//		changePicture(rightImage,"right.png");
		
		changePicture(guestBT,"basicInfo.png");
		changePicture(hotelBT,"hotel.png");
		changePicture(orderBT,"order.png");
		changePicture(memberBT,"member.png");
		changePicture(creditBT,"credit.png");
		changePicture(signUpBT,"signOut.png");
		changePicture(mainBT,"home.png");
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("guestImage/mainPane/"+path)));	
	}
	@FXML
	protected void enter1(){
		changePicture(guestBT,"basicInfoEnter.png");
	}
	@FXML
	protected void exited1(){
		changePicture(guestBT,"basicInfo.png");
	}
	@FXML
	protected void enter2(){
		changePicture(hotelBT,"hotelEnter.png");
	}
	@FXML
	protected void exited2(){
		changePicture(hotelBT,"hotel.png");
	}
	@FXML
	protected void enter3(){
		changePicture(orderBT,"orderEnter.png");
	}
	@FXML
	protected void exited3(){
		changePicture(orderBT,"order.png");
	}
	@FXML
	protected void enter4(){
		changePicture(memberBT,"memberEnter.png");
	}
	@FXML
	protected void exited4(){
		changePicture(memberBT,"member.png");
	}
	@FXML
	protected void enter5(){
		changePicture(creditBT,"creditEnter.png");
	}
	@FXML
	protected void exited5(){
		changePicture(creditBT,"credit.png");
	}
	@FXML
	protected void enter6(){
		changePicture(signUpBT,"signOutEnter.png");
	}
	@FXML
	protected void exited6(){
		changePicture(signUpBT,"signOut.png");
	}
	@FXML
	protected void enter7(){
		changePicture(mainBT,"homeEnter.png");
	}
	@FXML
	protected void exited7(){
		changePicture(mainBT,"home.png");
	}
}
