package runner;
/**
 * @author Molloh
 * @version 2016/12/31
 * @description
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import rmi.RemoteHelper;

public class ServerRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        final RemoteHelper helper = RemoteHelper.getInstance();
        final Label label = new Label("");
        Button start = new Button("启动");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(helper.initServer());
            }
        });

        Button stop = new Button("停止");
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(helper.stopServer());
            }
        });


        FlowPane root = new FlowPane();
        root.getChildren().addAll(start, stop, label);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
}
