package presentation.signUpUI.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utilities.enums.UserType;

public class RootFactory {

	private Parent root = null;

	public Parent createRoot(UserType userType) {

		try {
			if (userType == UserType.GUEST) {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlView/GuestView.fxml"));
			}
			
			if (userType == UserType.HOTEL_WORKER) {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlView/HotelView.fxml"));
			}
			
			if (userType == UserType.WEB_MARKETER) {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlView/MarketerView.fxml"));
			}
			
			if (userType == UserType.WEB_MANAGER) {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlView/ManagerView.fxml"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return root;
		
	}

}
