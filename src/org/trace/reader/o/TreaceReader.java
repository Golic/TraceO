package org.trace.reader.o;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Ognjen Lazic
 *
 */
public class TreaceReader extends Application {

	static String filePath = null;
	static Stage window;
	Scene traceScane;
	final static TextArea textArea = new TextArea();

	public class RunMeTask extends TimerTask {
		@Override
		public void run() {
			read(textArea.getText().split("\n").length);
		}
	}

	public static void main(String[] args) {

		launch(args);
		System.exit(0);

	}

	@Override
	public void start(Stage primaryStage) {

		// svi gridovi za scene
		GridPane mainGrid = new GridPane();

		// podesavanje gridova -------------------------
		mainGrid.setAlignment(Pos.CENTER);
		mainGrid.setHgap(1);
		mainGrid.setVgap(1);
		mainGrid.setPadding(new Insets(25, 25, 25, 25));

		// sve scene -------------------------
		traceScane = new Scene(mainGrid, 800, 600);

		// traceScene
		// ---------------------------------------------------------
		primaryStage.setTitle("TraceO");

		// tekst are gde punimo trace.
		textArea.setStyle("-fx-background-color: black");
		// textArea.setDisable(true);
		textArea.setPrefRowCount(50);
		textArea.setPrefColumnCount(100);
		textArea.setWrapText(true);
		textArea.setPrefWidth(700);
		GridPane.setHalignment(textArea, HPos.CENTER);
		mainGrid.add(textArea, 0, 0);

		// izaberi trace file
		// dobi adresu tracefajla
		// ---------------------------------------------------------
		File selectedFile = null;

		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");

			selectedFile = fileChooser.showOpenDialog(window);
			filePath = selectedFile.getCanonicalPath();
			// System.out.println(filePath);

		} catch (Exception e) {
			// System.out.println(e);
			System.exit(0);
		}

		// ucitavaj fajl
		// SVE LINIJE
		// ---------------------------------------------------------
		read(0);
		primaryStage.setScene(traceScane);
		primaryStage.show();

		try {
			TimerTask task = new RunMeTask();
			Timer timer = new Timer();
			timer.schedule(task, 1000, 5000);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void read(int ignoreLines) {

		BufferedReader bufferReader = null;
		String line = "";
		try {

			bufferReader = new BufferedReader(new FileReader(filePath));

			// ignore lines that are allrdy in
			for (int i = 0; i < ignoreLines; i++) {
				line = bufferReader.readLine();
			}
			// read new lines
			while ((line = bufferReader.readLine()) != null) {

				String[] splitedLine = line.split(",");
				String result = "";
				if (splitedLine[1].equals("1")) {

					result = "-!!!!!!!!!!- " + splitedLine[0] + " : "
							+ splitedLine[2];

				} else if (splitedLine[1].equals("2")) {
					result = "-!!!!      - " + splitedLine[0] + " : "
							+ splitedLine[2];
				} else {
					result = "-          - " + splitedLine[0] + " : "
							+ splitedLine[2];
				}
				// use comma as separator
				textArea.appendText(result + "\n");

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
