package presentation.signUpUI.controller;

import javafx.stage.Stage;



public class StageController {
	
	private static StageController stageController = new StageController();
	public Stage stage;

	public static StageController getInstance(){
		return stageController;
	}
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
