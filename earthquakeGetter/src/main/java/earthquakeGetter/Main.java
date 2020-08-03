package earthquakeGetter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(final Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			final TextField tf = new TextField();
//			root.getChildren().add(tf);
			HBox hbox = new HBox();
			Button btn = new Button("提交邮箱地址");
			hbox.getChildren().addAll(tf,btn);
			root.setCenter(hbox);
			root.setPadding(new Insets(5));
			primaryStage.setScene(scene);
			primaryStage.show();
			btn.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String mailAddress = tf.getText();
					System.out.println(mailAddress);
					try {
						JavaRun.main(mailAddress);
						primaryStage.close();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
