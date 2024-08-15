package baylinux01.snakeGameJFX;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			Image image=new Image("file:./aidatTakipLogo.jpg");
//			primaryStage.getIcons().add(image);
			primaryStage.getIcons().add(new Image(Main.class.
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
					stage.getIcons().add(new Image(Main.class.
							getResourceAsStream("snake.png")));
					stage.setTitle("Yılan Oyunu");
					
					ProgramWindow pw=new ProgramWindow();
					pw.start(stage);
					primaryStage.hide();
					
				}
				
			};
			
			Button button=new Button("Yeni Oyun");
			button.setPrefWidth(100);
			button.setPrefHeight(20);
			button.setLayoutX(150);
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
			button2.setLayoutX(150);
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
