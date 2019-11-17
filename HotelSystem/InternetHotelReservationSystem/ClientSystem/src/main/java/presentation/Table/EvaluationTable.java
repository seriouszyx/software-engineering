package presentation.Table;

import javafx.beans.property.SimpleStringProperty;

public class EvaluationTable {
	public final SimpleStringProperty guestID;
	public final SimpleStringProperty score;
	public final SimpleStringProperty comment;

	public EvaluationTable(String guestID,String score, String comment) {
		this.guestID = new SimpleStringProperty(guestID);
		this.score = new SimpleStringProperty(score);
		this.comment = new SimpleStringProperty(comment);
	}

}
