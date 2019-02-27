package reseau;


import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
	try {
		Pane pageDeGarde = new Pane();
		Scene scene = new Scene(pageDeGarde);
		
		primaryStage.setWidth(700);
		primaryStage.setHeight(700);
		primaryStage.setScene(scene);
		
		scene.getStylesheets().add(getClass().getResource("reseau.css").toExternalForm());
		
		primaryStage.show();
		
		Route r = new Route(pageDeGarde);
		
		
		for(int i=0 ; i<4 ; i++) {
			for(int j=0 ; j<4 ; j++) {
				Voiture v = new Voiture(pageDeGarde, scene, i, j,0);
				ArrayList<Voiture> l = new ArrayList<Voiture>();
				l.add(v);
				r.listeVoiture.add(l);
				v = v.clicStop(v);
				v.wait5(pageDeGarde);
			}
		}
		
		
		
		final KeyFrame keyF = new KeyFrame(Duration.millis(5500));
		Timeline timeline = new Timeline(); 
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(keyF);
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				timeline.getKeyFrames().remove(keyF);
				
				final KeyFrame keyF = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() { 
				    @Override 
				    public void handle(ActionEvent actionEvent) { 
				    	int i = (int)(Math.random()*4);
		            	int j = (int)(Math.random()*4);
		            	double vitesse = 7000 + (int)(Math.random()*5000);
		            	
						Voiture v = new Voiture(pageDeGarde, scene, i, j, vitesse);
						ArrayList<Voiture> l = new ArrayList<Voiture>();
						l.add(v);
						r.listeVoiture.get(4*i+j).add(v);
						v.move(v,pageDeGarde);
						v = v.clicStop(v);
				    } 
				});
				timeline.setCycleCount(timeline.INDEFINITE);
				timeline.getKeyFrames().add(keyF);
				timeline.play();
			}
		});
	
		
		
		
		
		
		
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void main(String... args) { 
		Application.launch(args); 
	}
}
