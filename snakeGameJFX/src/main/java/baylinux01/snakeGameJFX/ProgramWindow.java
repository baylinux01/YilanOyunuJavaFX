package baylinux01.snakeGameJFX;



import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class ProgramWindow extends Application {
	static int fieldSize=600;
	static int partSize=20;
	static List<Part> snakeParts=new ArrayList<Part>();
	static int length=2;
	static boolean gameOver=false;
	static Rectangle clip;
	static Pane pane;
	static Timer timer;
	static int speed=70;
	static int score=0;
	//static Part apple;
	static Circle apple;
	static Label labelPause,labelScore;
	static Timeline timeline;
	static boolean paused=false;
	static Random random=new Random();
	static EventHandler<ActionEvent> eh2;
	
	public static void formHead()
	{
		snakeParts.clear();
		Part part=new Part();
		part.setPrefWidth(partSize);
		part.setPrefHeight(partSize);
		part.setStyle("-fx-background-color:black;");
		part.setLayoutX(random.nextInt((fieldSize-partSize)/partSize)*partSize);
		part.setLayoutY(random.nextInt((fieldSize-partSize)/partSize)*partSize);
		snakeParts.add(part);
		pane.getChildren().add(part);
	}
	
	public static void passWalls() 
	{
		if(snakeParts.get(0).getLayoutX()<0) 
		{
			snakeParts.get(0).setLayoutX(fieldSize-partSize);
			snakeParts.get(0).setLayoutY(snakeParts.get(0).getLayoutY());
			
		}
		else if(snakeParts.get(0).getLayoutX()>fieldSize-2*partSize) 
		{
			snakeParts.get(0).setLayoutX(0);
			snakeParts.get(0).setLayoutY(snakeParts.get(0).getLayoutY());
			
			
		}
		else if(snakeParts.get(0).getLayoutY()<0) 
		{
			snakeParts.get(0).setLayoutX(snakeParts.get(0).getLayoutX());
			snakeParts.get(0).setLayoutY(fieldSize-partSize);
			
			
		}
		else if(snakeParts.get(0).getLayoutY()>fieldSize-2*partSize) 
		{
			snakeParts.get(0).setLayoutX(snakeParts.get(0).getLayoutX());
			snakeParts.get(0).setLayoutY(0);
			
			
		}
		else {}
	}
	public static void moveHead() 
	{

		if(snakeParts.get(0).getDirection().equals("UP")) 
		{
			snakeParts.get(0).setLayoutY(snakeParts.get(0).getLayoutY()-partSize);
				
		}
		else if(snakeParts.get(0).getDirection().equals("DOWN")) 
		{
			snakeParts.get(0).setLayoutY(snakeParts.get(0).getLayoutY()+partSize);	
		}
		else if(snakeParts.get(0).getDirection().equals("LEFT")) 
		{
			snakeParts.get(0).setLayoutX(snakeParts.get(0).getLayoutX()-partSize);	
		}
		else if(snakeParts.get(0).getDirection().equals("RIGHT")) 
		{
			snakeParts.get(0).setLayoutX(snakeParts.get(0).getLayoutX()+partSize);	
		}

		else {}
	}
	public static void putApple()
	{	
		
		apple=new Circle(partSize/2,Color.RED);
		apple.setStyle("-fx-background-color:red;");
		//apple.setPrefWidth(partSize);
		//apple.setPrefHeight(partSize);
		apple.setLayoutX(random.nextInt((fieldSize-partSize)/partSize)*partSize);
		apple.setLayoutY(random.nextInt((fieldSize-partSize)/partSize)*partSize);
		for(int i=0;i<snakeParts.size();i++)
		{
			if((snakeParts.get(i).getLayoutX()>= apple.getLayoutX()-partSize
					&& snakeParts.get(i).getLayoutX()<= apple.getLayoutX()+partSize*0.5)
					&& (snakeParts.get(i).getLayoutY()<=apple.getLayoutY()+partSize*0.5
				    && snakeParts.get(i).getLayoutY()>=apple.getLayoutY()-partSize)
					||apple.getLayoutX()<partSize||apple.getLayoutX()>fieldSize-partSize
					||apple.getLayoutY()<partSize||apple.getLayoutY()>fieldSize-partSize)
			{
				
				putApple();
			}
			
		}
		
		 pane.getChildren().add(apple);
		
	}
	public static void appleControl()
	{
		
		if((snakeParts.get(0).getLayoutX()>= apple.getLayoutX()-partSize 
				&& snakeParts.get(0).getLayoutX()<= apple.getLayoutX()+partSize*0.5)
				&& (snakeParts.get(0).getLayoutY()<=apple.getLayoutY()+partSize*0.5 
			    && snakeParts.get(0).getLayoutY()>=apple.getLayoutY()-partSize)) 
		{
			pane.getChildren().remove(apple);
			score++;
			length++;
			formParts();
			putApple();
//			if(speed>1) 
//			{
//				speed-=1;
//				timeline=new Timeline(new KeyFrame(Duration.millis((long)speed),eh2));
//				timeline.setCycleCount(Timeline.INDEFINITE);
//				timeline.play();
//				
//			}
			
			
			
			
		}
		
		

	}
	
	public static void formParts() 
	{
		
		for(int i=snakeParts.size(); i<length;i++) 
		{
			Part part=new Part();
			part.setPrefWidth(partSize);
			part.setPrefHeight(partSize);
			part.setStyle("-fx-background-color:black;");
			snakeParts.add(part);
			
		}
	}
	
	public static void moveParts()
	{
		for(int i=length-1; i>0 ;i--) 
		{
			snakeParts.get(i).setDirection(snakeParts.get(i-1).getDirection());
			snakeParts.get(i).setLayoutX(snakeParts.get(i-1).getLayoutX());
			snakeParts.get(i).setLayoutY(snakeParts.get(i-1).getLayoutY());
		}
			if(!pane.getChildren().contains(snakeParts.size()-1))
			pane.getChildren().add(snakeParts.get(snakeParts.size()-1));
	}
	public static void eatItselfControl()
	{
		for(int i=3; i<length;i++)
		{
			if(snakeParts.get(0).getLayoutX() == 
					snakeParts.get(i).getLayoutX() 
					&& snakeParts.get(0).getLayoutY()==snakeParts.get(i).getLayoutY())
		    {
				
				gameOver=true;
				
			}
		}
	 }
	public void pauseGame() 
	{
		
		
		labelPause.setVisible(true);
		
		
		
		
		
		timeline.stop();
		//paused=true;
	}
	public void continueGame() 
	{
		labelPause.setVisible(false);
		timeline.play();
		//paused=false;
	}
	public void showPoint() 
	{
		
		labelScore.setText("Score: "+score);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,800,650);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			Image image=new Image("file:./aidatTakipLogo.jpg");
//			primaryStage.getIcons().add(image);
			primaryStage.getIcons().add(new Image(ProgramWindow.class.
					getResourceAsStream("snake.png")));
			primaryStage.setTitle("Yılan Oyunu");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			pane=new Pane();
			pane.setPrefWidth(fieldSize);
			pane.setPrefHeight(fieldSize);
			pane.setLayoutX(10);
			pane.setLayoutY(10);
			pane.setStyle("-fx-background-color:blue;");
			root.getChildren().add(pane);
			
			clip=new Rectangle(fieldSize,fieldSize);
			clip.setLayoutX(10);
			clip.setLayoutY(10);
			clip.setStyle("-fx-border-color:blue;");
			pane.setClip(clip);
			
			
			labelPause=new Label("PAUSED");
			labelPause.setPrefWidth(200);
			labelPause.setPrefHeight(100);
			labelPause.setLayoutX(240);
			labelPause.setLayoutY(240);
			labelPause.setStyle("-fx-text-fill:red;-fx-font-size:40;");
			labelPause.setVisible(false);
			root.getChildren().add(labelPause);
			
			labelScore=new Label("Score: "+score);
			labelScore.setPrefWidth(175);
			labelScore.setPrefHeight(100);
			labelScore.setLayoutX(620);
			labelScore.setLayoutY(10);
			labelScore.setStyle("-fx-text-fill:blue;-fx-font-size:40;-fx-border-color:blue;");
			root.getChildren().add(labelScore);
			
			EventHandler<KeyEvent> eh=new EventHandler<KeyEvent>()
			{

				@Override
				public void handle(KeyEvent e) {
					if(e.getCode() == KeyCode.W && !snakeParts.get(0).getDirection().equals("DOWN")) 
					{
						snakeParts.get(0).setDirection("UP");
						
					}
					else if(e.getCode() == KeyCode.S && !snakeParts.get(0).getDirection().equals("UP")) 
					{
						snakeParts.get(0).setDirection("DOWN");
						
					}
					else if(e.getCode() == KeyCode.A && !snakeParts.get(0).getDirection().equals("RIGHT")) 
					{
						snakeParts.get(0).setDirection("LEFT");
					}
						
					else if(e.getCode() == KeyCode.D && !snakeParts.get(0).getDirection().equals("LEFT")) 
					{
						snakeParts.get(0).setDirection("RIGHT");
					}
					else if (e.getCode() == KeyCode.SPACE && paused==false) 
		            {	
							pauseGame();
							paused=true;

		            }
					
					else if (e.getCode() == KeyCode.SPACE && paused==true) 
		            {
						continueGame();
						paused=false;
							
		            }
					else {}
					
					
				}
				
			};
			
			scene.setOnKeyPressed(eh);
			
			
			
			formHead();
			putApple();
			
			eh2=new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(gameOver==false) 
					{
						
						eatItselfControl();
						passWalls();
						moveHead();
						appleControl();
						moveParts();
						
						showPoint();
						
						
						
						
					}
						
					
						
						if(gameOver==true) 
						{
							
							
							
							Group group=new Group();
							Scene scene=new Scene(group);
							Stage stage=new Stage();
							stage.setScene(scene);
							OyunSonu os=new OyunSonu();
							os.score=score;
							os.start(stage);
							timeline.stop();
							pane.getChildren().clear();
							snakeParts.clear();
							length=2;
							score=0;
							speed=70;
							gameOver=false;
							paused=false;
							primaryStage.hide();
						}
					
				}

				

				
				
			};
			
			timeline=new Timeline(new KeyFrame(Duration.millis(speed),eh2));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
