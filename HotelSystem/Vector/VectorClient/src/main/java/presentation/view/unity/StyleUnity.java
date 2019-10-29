package presentation.view.unity;

import javafx.scene.control.TextField;

/**
 * @author Molloh
 * @version 2017/1/2
 * @description
 */
public class StyleUnity {

    public static void numeric(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
