package presentation.hotelWorkerUI.controller;

import java.io.IOException;


import businessLogic.hotelBL.HotelBLController;
import businessLogicService.hotelBLService.HotelBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.signUpUI.controller.StageController;
import utilities.IDReserve;
import vo.HotelVO;
/**
 * @description 控制酒店工作人员所有界面的跳转
 * @author 61990
 * @lastChangedBy Harvey
 */
public class HotelWorkerViewController {

	@FXML 
	private AnchorPane right;
	@FXML 
	private AnchorPane mainPane;

	@FXML
	private ImageView leftImage,rightImage,hotelBT,orderBT,offlineBT,roomBT,promotionBT,signUpBT,mainBT;
	
	private Parent currentParent;
	
	@FXML
	private Label infoBt;
	
	HotelBLService hotelBLController;
	String hotelID = IDReserve.getInstance().getUserID();
	
	public HotelWorkerViewController() {
		hotelBLController = HotelBLController.getInstance();
	}
	/**
	 * @description:
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/7 
	 */
	@FXML
	private void initialize() {
		
		HotelVO hotelVO = hotelBLController.getHotelInfo(hotelID);
		changePicture(rightImage,"mainHomeHW.png");
		infoBt.setText(hotelVO.hotelID);
		initImage();
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @查看酒店信息 
	 */    
	@FXML 
	protected void openHotelInfo() throws IOException{
		jump("HotelDetail");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @查看酒店订单信息 
	 */    
	@FXML 
	protected void openOrderInfo() throws IOException{
		jump("HotelOrderCheck");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @线下处理 
	 */    
	@FXML 
	protected void openOffline() throws IOException{
		jump("HotelOffline");
	}

	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @查看酒店策略信息 
	 */    
	@FXML 
	protected void openPromotion() throws IOException{
		jump("HotelPromotion");
	}
	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @查看酒店房间信息 
	 */    
	@FXML 
	protected void openRoomInfo() throws IOException{
		jump("HotelRoomInfo");
	}
	/**
	 * @author 61990
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @查看主面板信息 
	 */    
	@FXML 
	protected void openMain() throws IOException{
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
	 * @Description:封装界面跳转逻辑
	 * @param parent
	 * @param path
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 下午2:54:27
	 */
	private void jump(String path){
		right.getChildren().clear();
		try {
			currentParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlHotel/"+path+".fxml"));
		} catch (IOException e) {
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
		
		changePicture(hotelBT,"hotelInfo.png");
		changePicture(orderBT,"order.png");
		changePicture(offlineBT,"offline.png");
		changePicture(roomBT,"room.png");
		changePicture(promotionBT,"promotion.png");
		changePicture(signUpBT,"signOut.png");
		changePicture(mainBT,"home.png");
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("hotelImage/mainPane/"+path)));	
	}
	@FXML
	protected void enter1(){
		changePicture(hotelBT,"hotelInfoEnter.png");
	}
	@FXML
	protected void exited1(){
		changePicture(hotelBT,"hotelInfo.png");
	}
	@FXML
	protected void enter2(){
		changePicture(orderBT,"orderEnter.png");
	}
	@FXML
	protected void exited2(){
		changePicture(orderBT,"order.png");
	}
	@FXML
	protected void enter3(){
		changePicture(offlineBT,"offlineEnter.png");
	}
	@FXML
	protected void exited3(){
		changePicture(offlineBT,"offline.png");
	}
	@FXML
	protected void enter4(){
		changePicture(roomBT,"roomEnter.png");
	}
	@FXML
	protected void exited4(){
		changePicture(roomBT,"room.png");
	}
	@FXML
	protected void enter5(){
		changePicture(promotionBT,"promotionEnter.png");
	}
	@FXML
	protected void exited5(){
		changePicture(promotionBT,"promotion.png");
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
