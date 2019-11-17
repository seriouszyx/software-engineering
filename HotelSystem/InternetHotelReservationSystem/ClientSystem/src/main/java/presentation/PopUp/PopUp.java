package presentation.PopUp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import presentation.signUpUI.controller.StageController;
/**
 * @author 61990
 * @lastChangedBy 61990
 * @updateTime 2016/12/10
 * @弹框类
 */
public class PopUp {
	Pane root;
	Stage stage;
	Scene scene;
	ImageView immage;
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/12/10
	 * @只显示一条提示信息的通用方法
	 */
	public PopUp(String message, String operation){
		 immage=new ImageView();
		Stage stage2=StageController.getInstance().getStage();
		stage2.setOpacity(0.6);
		initWindow();
//		immage.setY(200);
//		immage.setX(320);
		immage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("popUp.png")));	
		Label result = new Label(message);
		VBox vbox = new VBox(); 
		vbox.setPrefHeight(200);
		vbox.setPrefWidth(320);
		StackPane pane1=new StackPane();
		pane1.setPrefHeight(200);
		pane1.getChildren().add(result);
		System.out.println("ss");
		Button btn = new Button();
	       btn.setText("关闭");
	       btn.setLayoutX(150);
	       btn.setLayoutY(130);
	       btn.setOnAction(new EventHandler<ActionEvent>() {
	           @Override
	           public void handle(ActionEvent event) {
	             stage.close();
	             stage2.setOpacity(1);
	           }
	    });
	  StackPane pane2=new StackPane();
	  pane2.setPrefHeight(110);
		pane2.getChildren().add(btn);
		vbox.getChildren().addAll(pane1,pane2);
		vbox.setBlendMode(BlendMode.MULTIPLY);
		root.getChildren().add(immage);
		root.getChildren().add(vbox);
		
		stage.setTitle(operation);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent event) {
				 stage2.setOpacity(1); 
			}
		});
		
	}

	private void initWindow() {
		
		root = new Pane();
		scene = new Scene(root,320,200); // 创建场景；
		stage = new Stage();// 创建舞台
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene); // 将场景载入舞台；
		stage.setAlwaysOnTop(true);
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));
		stage.setResizable(false);
		stage.show(); // 显示窗口；
	}
}
