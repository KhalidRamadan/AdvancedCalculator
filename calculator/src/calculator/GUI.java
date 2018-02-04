package calculator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.math.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GUI extends Application 
{
	private TextField txtInput, txtOutput;
	private String answer = "";
	private Drive drive = new Drive();
	// clipboard
	public void setClipboardContents(String aString)
	{
		StringSelection stringSelection = new StringSelection(aString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	public String getClipboardContents()
	{
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText =
				(contents != null) &&
				contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText)
		{
			try
			{
				result = (String)contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (Exception ex)
			{
				
			}
		}
		return result;
	}
	private void calculate()
	{
		BigDecimal answer = drive.execute(txtInput.getText());
		if (answer != null)
			txtOutput.setText(answer.toString());
	}
	private void addInput(String s)
	{
		txtInput.setText(txtInput.getText() + s);
		calculate();
	}
	private void evalAnswer()
	{
		calculate();
		answer = txtOutput.getText();
		clear();
	}
	private void insertAnswer()
	{
		addInput(answer);
	}
	private void paste()
	{
		txtInput.setText(getClipboardContents());
	}
	private void copy()
	{
		setClipboardContents(txtOutput.getText());
	}
	private void clear()
	{
		txtInput.setText("");
		txtOutput.setText("");
	}
	private void delete()
	{
		if (txtInput.getText().length() > 0)
		{
			StringBuilder sb = new StringBuilder(txtInput.getText());
			sb.deleteCharAt(sb.length() - 1);
			txtInput.setText(sb.toString());
		}
	}
	public void start(Stage primaryStage) throws Exception
	{
		// text fields
		txtInput = new TextField();
		txtInput.setMinWidth(645);
		txtInput.textProperty().addListener((e) -> calculate());
		txtOutput = new TextField();
		txtOutput.setMinWidth(645);
		txtOutput.setEditable(false);
		//buttons
		Button btnPaste = new Button("Paste");
		btnPaste.setMinWidth(60);
		btnPaste.setOnAction((e) -> paste());
		Button btnCopy = new Button("Copy");
		btnCopy.setMinWidth(60);
		btnCopy.setOnAction((e) -> copy());
		
		Button btnAbs = new Button("abs");
		btnAbs.setOnAction((e) -> addInput("abs("));
		Button btnSin = new Button("sin");
		btnSin.setOnAction((e) -> addInput("sin("));
		Button btnCos = new Button("cos");
		btnCos.setOnAction((e) -> addInput("cos("));
		Button btnTan = new Button("tan");
		btnTan.setOnAction((e) -> addInput("tan("));
		Button btnASin = new Button("asin");
		btnASin.setOnAction((e) -> addInput("asin("));
		Button btnACos = new Button("acos");
		btnACos.setOnAction((e) -> addInput("acos("));
		Button btnATan = new Button("atan");
		btnATan.setOnAction((e) -> addInput("atan("));
		Button btnMin = new Button("min");
		btnMin.setOnAction((e) -> addInput("min("));
		Button btnMax = new Button("max");
		btnMax.setOnAction((e) -> addInput("max("));
		Button btnP = new Button("nPr");
		btnP.setOnAction((e) -> addInput("P("));
		Button btnC = new Button("nCr");
		btnC.setOnAction((e) -> addInput("C("));
		Button btnGcd = new Button("gcd");
		btnGcd.setOnAction((e) -> addInput("gcd("));
		Button btnLcm = new Button("lcm");
		btnLcm.setOnAction((e) -> addInput("lcm("));
		
		Button btnAdd = new Button("+");
		btnAdd.setOnAction((e) -> addInput("+"));
		Button btnSub = new Button("-");
		btnSub.setOnAction((e) -> addInput("-"));
		Button btnMul = new Button("*");
		btnMul.setOnAction((e) -> addInput("*"));
		Button btnDiv = new Button("/");
		btnDiv.setOnAction((e) -> addInput("/"));
		Button btnMod = new Button("%");
		btnMod.setOnAction((e) -> addInput("%"));
		Button btnPow = new Button("x^y");
		btnPow.setOnAction((e) -> addInput("^"));
		Button btnShl = new Button("<<");
		btnShl.setOnAction((e) -> addInput("<<"));
		Button btnShr = new Button(">>");
		btnShr.setOnAction((e) -> addInput(">>"));
		Button btnAnd = new Button("AND");
		btnAnd.setOnAction((e) -> addInput("&"));
		Button btnOr = new Button("OR");
		btnOr.setOnAction((e) -> addInput("|"));
		Button btnXor = new Button("XOR");
		btnXor.setOnAction((e) -> addInput("^="));
		Button btnNot = new Button("NOT");
		btnNot.setOnAction((e) -> addInput("~"));
		Button btnFact = new Button("!");
		btnFact.setOnAction((e) -> addInput("!"));
		
		Button btn0 = new Button("0");
		btn0.setOnAction((e) -> addInput("0"));
		Button btn1 = new Button("1");
		btn1.setOnAction((e) -> addInput("1"));
		Button btn2 = new Button("2");
		btn2.setOnAction((e) -> addInput("2"));
		Button btn3 = new Button("3");
		btn3.setOnAction((e) -> addInput("3"));
		Button btn4 = new Button("4");
		btn4.setOnAction((e) -> addInput("4"));
		Button btn5 = new Button("5");
		btn5.setOnAction((e) -> addInput("5"));
		Button btn6 = new Button("6");
		btn6.setOnAction((e) -> addInput("6"));
		Button btn7 = new Button("7");
		btn7.setOnAction((e) -> addInput("7"));
		Button btn8 = new Button("8");
		btn8.setOnAction((e) -> addInput("8"));
		Button btn9 = new Button("9");
		btn9.setOnAction((e) -> addInput("9"));
		
		Button btnOpenBracket = new Button("(");
		btnOpenBracket.setOnAction((e) -> addInput("("));
		Button btnCloseBracket = new Button(")");
		btnCloseBracket.setOnAction((e) -> addInput(")"));
		Button btnComma = new Button(",");
		btnComma.setOnAction((e) -> addInput(","));
		Button btnPoint = new Button(".");
		btnPoint.setOnAction((e) -> addInput("."));
		Button btnAns = new Button("Ans");
		btnAns.setOnAction((e) -> insertAnswer());
		Button btnEqual = new Button("=");
		btnEqual.setOnAction((e) -> evalAnswer());
		Button btnDCLR = new Button("DCLR");
		btnDCLR.setOnAction((e) -> clear());
		Button btnDel = new Button("Del");
		btnDel.setOnAction((e) -> delete());
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(btnAbs, 0, 0);
		grid.add(btnSin, 0, 1);
		grid.add(btnCos, 0, 2);
		grid.add(btnTan, 0, 3);
		grid.add(btnFact, 1, 0);
		grid.add(btnASin, 1, 1);
		grid.add(btnACos, 1, 2);
		grid.add(btnATan, 1, 3);
		grid.add(btnMin, 2, 0);
		grid.add(btnMax, 2, 1);
		grid.add(btnP, 2, 2);
		grid.add(btnC, 2, 3);
		grid.add(btnGcd, 3, 0);
		grid.add(btnLcm, 3, 1);
		grid.add(btnShl, 3, 2);
		grid.add(btnShr, 3, 3);
		grid.add(btnAnd, 4, 0);
		grid.add(btnOr, 4, 1);
		grid.add(btnXor, 4, 2);
		grid.add(btnNot, 4, 3);
		grid.add(btnMod, 5, 0);
		grid.add(btnPow, 5, 1);
		grid.add(btnAdd, 5, 2);
		grid.add(btnSub, 5, 3);
		grid.add(btnOpenBracket, 6, 0);
		grid.add(btnComma, 6, 1);
		grid.add(btnMul, 6, 2);
		grid.add(btnDiv, 6, 3);
		grid.add(btnCloseBracket, 7, 0);
		grid.add(btnPoint, 7, 1);
		grid.add(btnDCLR, 7, 2);
		grid.add(btnDel, 7, 3);
		grid.add(btn7, 8, 0);
		grid.add(btn4, 8, 1);
		grid.add(btn1, 8, 2);
		grid.add(btn0, 8, 3);
		grid.add(btn8, 9, 0);
		grid.add(btn5, 9, 1);
		grid.add(btn2, 9, 2);
		grid.add(btnEqual, 9, 3);
		grid.add(btn9, 10, 0);
		grid.add(btn6, 10, 1);
		grid.add(btn3, 10, 2);
		grid.add(btnAns, 10, 3);
		for (Node a : grid.getChildren())
			((Button)a).setMinWidth(60);
		
		HBox inputPane = new HBox(5);
		inputPane.getChildren().addAll(txtInput, btnPaste);
		HBox outputPane = new HBox(5);
		outputPane.getChildren().addAll(txtOutput, btnCopy);
		
		VBox mainPane = new VBox(5);
		mainPane.setPadding(new Insets(5, 5, 5, 5));
		mainPane.getChildren().addAll(inputPane, outputPane, grid);
		
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String args[])
	{
		launch();
	}
}
