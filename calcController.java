package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class calcController {

	@FXML
	TextField calctf;

	public void btnClicked(ActionEvent e) {
		Button b = (Button) e.getSource();
		calctf.setText(calctf.getText().concat(b.getText()));
		
	}

	public void cntrlClicked(ActionEvent e) {
		Button b = (Button) e.getSource();
		if (b.getText().equals("B")) {
			if (!calctf.getText().isEmpty()) {
				calctf.setText(calctf.getText().substring(0, calctf.getText().length() - 1));
			}
		} else if (b.getText().equals("C")) {
			calctf.setText("");
		}
	}

	public void startCalculation(ActionEvent e) {
		String equation = calctf.getText();
		Calculator calculator = new Calculator();
		calctf.setText(Double.toString(calculator.calculate(equation)));
	}
	
	

}
