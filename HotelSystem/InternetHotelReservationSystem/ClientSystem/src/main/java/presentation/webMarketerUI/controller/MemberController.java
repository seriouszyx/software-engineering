package presentation.webMarketerUI.controller;

import java.util.LinkedList;
import java.util.List;

import businessLogic.marketBL.MarketController;
import businessLogicService.marketBLService.MarketBLService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.PopUp.PopUp;
import utilities.enums.ResultMessage;
import vo.MarketVO;

/**
 * @author 61990
 *
 */
public class MemberController {

	@FXML
	private Button modifyButton;

	@FXML
	private Pane marketCheckPane,marketModifyPane;

	//加载查看的显示框
	@FXML
	private Label name1, name2, name3, name4, name5,  name6, name7, name8,
	credit1, credit2, credit3, credit4, credit5,credit6, credit7, credit8,
	discount1, discount2,discount3, discount4, discount5, discount6, discount7, discount8;
	@FXML
	private ImageView rightImage;
	List<MarketVO> listMarket;
	
	//加载维护的输入框
	@FXML
	private TextField market1,market2,market3,market4,market5,market6,market7,market8,
	needCredit1,needCredit2,needCredit3,needCredit4,needCredit5,needCredit6,needCredit7,needCredit8,
	modifyDiscount1,modifyDiscount2,modifyDiscount3,modifyDiscount4,modifyDiscount5,modifyDiscount6,modifyDiscount7,modifyDiscount8;
	private MarketBLService marketBLController = MarketController.getInstance();

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7 构造函数，初始化成员变量
	 */
	@FXML
	private void initialize() {
		changePicture(rightImage, "mainMarket.png");
		listMarket = marketBLController.getMemberFormulation();
		credit1.setText(Double.toString(listMarket.get(0).marketCredit));
		credit2.setText(Double.toString(listMarket.get(1).marketCredit));
		credit3.setText(Double.toString(listMarket.get(2).marketCredit));
		credit4.setText(Double.toString(listMarket.get(3).marketCredit));
		credit5.setText(Double.toString(listMarket.get(4).marketCredit));
		credit6.setText(Double.toString(listMarket.get(5).marketCredit));
		credit7.setText(Double.toString(listMarket.get(6).marketCredit));
		credit8.setText(Double.toString(listMarket.get(7).marketCredit));
		discount1.setText(Double.toString(listMarket.get(0).marketBenefit));
		discount2.setText(Double.toString(listMarket.get(1).marketBenefit));
		discount3.setText(Double.toString(listMarket.get(2).marketBenefit));
		discount4.setText(Double.toString(listMarket.get(3).marketBenefit));
		discount5.setText(Double.toString(listMarket.get(4).marketBenefit));
		discount6.setText(Double.toString(listMarket.get(7).marketBenefit));
		discount7.setText(Double.toString(listMarket.get(6).marketBenefit));
		discount8.setText(Double.toString(listMarket.get(5).marketBenefit));
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @跳转并初始化会员等级维护界面
	 */
	@FXML
	protected void modifyMarket() {
		
		needCredit1.setText(credit1.getText());
		needCredit2.setText(credit2.getText());
		needCredit3.setText(credit3.getText());
		needCredit4.setText(credit4.getText());
		needCredit5.setText(credit5.getText());
		needCredit6.setText(credit6.getText());
		needCredit7.setText(credit7.getText());
		needCredit8.setText(credit8.getText());
		modifyDiscount1.setText(discount1.getText());
		modifyDiscount2.setText(discount2.getText());
		modifyDiscount3.setText(discount3.getText());
		modifyDiscount4.setText(discount4.getText());
		modifyDiscount5.setText(discount5.getText());
		modifyDiscount6.setText(discount6.getText());
		modifyDiscount7.setText(discount7.getText());
		modifyDiscount8.setText(discount8.getText());
		
		marketModifyPane.setVisible(true);
		marketCheckPane.setVisible(false);
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @跳转会员等级查看界面，取消维护
	 */
	@FXML
	protected void cancelModify() {
		marketCheckPane.setVisible(true);
		marketModifyPane.setVisible(false);
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7
	 * @保存维护界面
	 */
	@FXML
	protected void saveModify() {
		
		List<MarketVO> list = new LinkedList<MarketVO>();
		try {
			list.add(new MarketVO(listMarket.get(0).marketName, Double.parseDouble(needCredit1.getText()) , Double.parseDouble(modifyDiscount1.getText())));
			list.add(new MarketVO(listMarket.get(1).marketName, Double.parseDouble(needCredit2.getText()) , Double.parseDouble(modifyDiscount2.getText())));
			list.add(new MarketVO(listMarket.get(2).marketName, Double.parseDouble(needCredit3.getText()) , Double.parseDouble(modifyDiscount3.getText())));
			list.add(new MarketVO(listMarket.get(3).marketName, Double.parseDouble(needCredit4.getText()) , Double.parseDouble(modifyDiscount4.getText())));
			list.add(new MarketVO(listMarket.get(4).marketName, Double.parseDouble(needCredit5.getText()) , Double.parseDouble(modifyDiscount5.getText())));
			list.add(new MarketVO(listMarket.get(5).marketName, Double.parseDouble(needCredit6.getText()) , Double.parseDouble(modifyDiscount6.getText())));
			list.add(new MarketVO(listMarket.get(6).marketName, Double.parseDouble(needCredit7.getText()) , Double.parseDouble(modifyDiscount7.getText())));
			list.add(new MarketVO(listMarket.get(7).marketName, Double.parseDouble(needCredit8.getText()) , Double.parseDouble(modifyDiscount8.getText())));
		} catch (NumberFormatException e) {
			new PopUp("修改失败，请确认填写格式", "");
		}
		ResultMessage message = marketBLController.setMemberFormulation(list);
		if(message==ResultMessage.FAIL)
			new PopUp("修改失败", "");
		
		initialize();
		marketCheckPane.setVisible(true);
		marketModifyPane.setVisible(false);
	}
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @图片效果
	 */
	void changePicture(ImageView image, String path){
		image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("marketerImage/marketPane/"+path)));	
	}
}
