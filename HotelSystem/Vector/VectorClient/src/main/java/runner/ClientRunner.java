package runner;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.common.ViewFxmlPath;
import rmi.RemoteHelper;

/**
 * @ author lienming
 * @ version 2016/11/20
 * @ description 带RMI版的利用这个类，对自己写的方法进行了简单测试，写在这个类的main方法里
 */
public class ClientRunner extends Application {
    private RemoteHelper remoteHelper;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        linkToServer();
        TimedTask timedTask = new TimedTask();

        //自定义窗体
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource(ViewFxmlPath.Sign_View_Path));
        //窗体拖动
        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();});

        root.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            primaryStage.setX(event.getScreenX() - xOffset);

        if (event.getScreenY() - yOffset < 0) {
            primaryStage.setY(0);
        } else {
            primaryStage.setY(event.getScreenY() - yOffset);
        }});

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void linkToServer(){
        try{
            remoteHelper=RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
            System.out.println("get RemoteHelper SUCCEED,set Remote SUCCEED");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }

}
