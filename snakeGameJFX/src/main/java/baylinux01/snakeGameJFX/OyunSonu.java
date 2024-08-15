package baylinux01.snakeGameJFX;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OyunSonu extends Application {
	static int score;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,600,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			Image image=new Image("file:./aidatTakipLogo.jpg");
//			primaryStage.getIcons().add(image);
			primaryStage.getIcons().add(new Image(OyunSonu.class.
					getResourceAsStream("snake.png")));
			primaryStage.setTitle("Yılan Oyunu");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			EventHandler eh=new EventHandler()
			{

				@Override
				public void handle(Event event) {
					Group group=new Group();
					Scene scene=new Scene(group);
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.getIcons().add(new Image(OyunSonu.class.
							getResourceAsStream("snake.png")));
					stage.setTitle("Yılan Oyunu");
					
					ProgramWindow pw=new ProgramWindow();
					pw.start(stage);
					primaryStage.hide();
					
				}
				
			};
			Label labelScore=new Label("Oyun Bitti. Puanınız: "+score);
			labelScore.setPrefWidth(500);
			labelScore.setPrefHeight(100);
			labelScore.setLayoutX(50);
			labelScore.setLayoutY(10);
			labelScore.setStyle("-fx-text-fill:blue;-fx-font-size:40;-fx-border-color:blue;");
			labelScore.setAlignment(Pos.BASELINE_CENTER);
			root.getChildren().add(labelScore);
			
			Button button=new Button("Yeni Oyun");
			button.setPrefWidth(100);
			button.setPrefHeight(20);
			button.setLayoutX(250);
			button.setLayoutY(130);
			root.getChildren().add(button);
			button.setOnAction(eh);
			
			
			EventHandler eh2=new EventHandler()
			{

				@Override
				public void handle(Event event) {
					System.exit(0);
					
				}
				
			};
			Button button2=new Button("Çıkış");
			button2.setPrefWidth(100);
			button2.setPrefHeight(20);
			button2.setLayoutX(250);
			button2.setLayoutY(170);
			root.getChildren().add(button2);
			button2.setOnAction(eh2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
