package reseau;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

import javafx.util.Duration;

public class Voiture {
	Rectangle r = new Rectangle();
	Rectangle j = new Rectangle();
	boolean stop = false;
	int direction;
	int start;
	double vitesse;
	Timeline  timeline = new Timeline();
	
	public Voiture(Pane p, Scene s,int direction, int start, double vitesse) {
		this.direction = direction;
		this.start = start;
		this.vitesse = vitesse;
		
		r.setWidth(10);
		r.setHeight(15);
		j.setWidth(10);
		j.setHeight(5);
		
		r.setStrokeWidth(0.5);
		r.setStrokeType(StrokeType.INSIDE);
		r.setStroke(Color.BLACK);
		r.setFill(Color.RED);
		
		j.setStrokeWidth(0.5);
		j.setStrokeType(StrokeType.INSIDE);
		j.setStroke(Color.BLACK);
		j.setFill(Color.YELLOW);
		
		Rotate rotate = new Rotate();
		rotate.setAngle(90);
		
		switch(direction) {
		case 0:
			r.setLayoutX(95+175*start);
			r.setLayoutY(705);
			j.setLayoutX(95+175*start);
			j.setLayoutY(700);
			break;
		case 1:
			r.setLayoutX(80+175*start);
			r.setLayoutY(-15);
			j.setLayoutX(80+175*start);
			j.setLayoutY(0);
			break;
		case 2:
			r.setLayoutX(4);
			r.setLayoutY(95+175*start);
			j.setLayoutX(9);
			j.setLayoutY(95+175*start);
			
			r.getTransforms().addAll(rotate);
			j.getTransforms().addAll(rotate);
			break;
		case 3:
			r.setLayoutX(725);
			r.setLayoutY(80+175*start);
			j.setLayoutX(710);
			j.setLayoutY(80+175*start);
			
			r.getTransforms().addAll(rotate);
			j.getTransforms().addAll(rotate);
		}
		s.getStylesheets().add(getClass().getResource("reseau.css").toExternalForm());
		p.getChildren().addAll(r,j);
	}
	
	
	void move(Voiture v, Pane p/*,Timeline timeline*/) {
		//Timeline timeline = new Timeline();
		
		final KeyValue kv;
		final KeyValue kv2;
		final KeyFrame kf;
		if(direction==0) {
			kv = new KeyValue(r.translateYProperty(), -730);
			kv2 = new KeyValue(j.translateYProperty(), -730);
		}
		else if(direction==2){
			kv = new KeyValue(r.translateXProperty(), 730);
			kv2 = new KeyValue(j.translateXProperty(), 730);
		}
		else if(direction==1) {
			kv = new KeyValue(r.translateYProperty(), 730);
			kv2 = new KeyValue(j.translateYProperty(), 730);
		}
		else {
			kv = new KeyValue(r.translateXProperty(), -730);
			kv2 = new KeyValue(j.translateXProperty(), -730);
		}
		kf = new KeyFrame(Duration.millis(v.vitesse), kv, kv2);
		
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				p.getChildren().remove(r);
				p.getChildren().remove(j);
			}
		});
	}
	
	
	void wait5(Pane p/*,Timeline timeline*/) {
		final KeyValue kv;
		final KeyValue kv2;
		final KeyFrame kf;
		if(direction==0) {
			kv = new KeyValue(r.translateYProperty(), -730);
			kv2 = new KeyValue(j.translateYProperty(), -730);
		}
		else if(direction==2){
			kv = new KeyValue(r.translateXProperty(), 730);
			kv2 = new KeyValue(j.translateXProperty(), 730);
		}
		else if(direction==1) {
			kv = new KeyValue(r.translateYProperty(), 730);
			kv2 = new KeyValue(j.translateYProperty(), 730);
		}
		else {
			kv = new KeyValue(r.translateXProperty(), -730);
			kv2 = new KeyValue(j.translateXProperty(), -730);
		}
		kf = new KeyFrame(Duration.millis(10000), kv, kv2);
		final KeyFrame kf2 = new KeyFrame(Duration.millis(5000));
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(kf2);
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				timeline.setCycleCount(1);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				p.getChildren().remove(r);
        				p.getChildren().remove(j);
        			}
				});
			}
		});
	}
	
	
	boolean recherche(ArrayList<Voiture> l/*, double x*/) {
		for(int i=0; i<l.size()-1; i++) {/*
			if(x <= l.get(i).j.getLayoutX()+5 && x >= l.get(i).j.getLayoutX()-15) {
				return true;
			}*/
			if(r.getLayoutBounds().intersects(l.get(i).r.getLayoutBounds())) {
				return true;
			}
			
		}
		return false;
	}
	
	
	void collision(Pane p,Route route) {
		final KeyFrame keyF = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() { 
		    @Override 
		    public void handle(ActionEvent actionEvent) { 
		    	
		    } 
		});
	}
	
	boolean detectCollision(double y1, double y2) {
		
		
		return true;
	}
	
	void position() {
		
	}
	
	/*
	void droite(Timer minuteur, Timeline timeline) {
		timeline.stop();
		
        TimerTask tache = new TimerTask() {
            public void run() {
            	
            	Rotate rotation = new Rotate();
            	
            	Timeline timeline2 = new Timeline();
            	final KeyValue kv1;
            	final KeyValue kv2;
            	if(h_v && !sens) {
            		h_v=false; sens=true;
            		
            		rotation.setAngle(90);
                	r.getTransforms().addAll(rotation);
                	j.getTransforms().addAll(rotation);
                	
            		j.setLayoutX(j.getLayoutX()+5);
            		j.setLayoutY(j.getLayoutY()+1);
            		
        			kv1 = new KeyValue(r.translateXProperty(), 695-r.getLayoutX());
            		kv2 = new KeyValue(j.translateXProperty(), 700-j.getLayoutX());
            		timeline2.setCycleCount(1);
            		timeline2.setOnFinished(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent e) {
            				r.setLayoutX(-630);
            				j.setLayoutX(-630);
            				Timeline timeline3 = new Timeline();
            				final KeyValue kv1 = new KeyValue(r.translateXProperty(), 695-r.getLayoutX());
                    		final KeyValue kv2 = new KeyValue(j.translateXProperty(), 700-j.getLayoutX());
                    		final KeyFrame kf = new KeyFrame(Duration.millis(10000), kv1,kv2);
                    		timeline3.getKeyFrames().add(kf);
            				timeline3.setCycleCount(Timeline.INDEFINITE);
            				timeline3.play();
            			}
            		});
        		}
            	else if(!h_v && sens) {
            		rotation.setAngle(90);
                	r.getTransforms().addAll(rotation);
                	j.getTransforms().addAll(rotation);
            		
                	h_v=true; sens=true;
            		
            		j.setLayoutY(j.getLayoutY()+5);
            		j.setLayoutX(j.getLayoutX()-1);
            		kv1 = new KeyValue(r.translateYProperty(), 695-r.getLayoutY());
            		kv2 = new KeyValue(j.translateYProperty(), 700-j.getLayoutY());
            		timeline2.setCycleCount(1);
            		timeline2.setOnFinished(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent e) {
            				r.setLayoutY(-70);
            				j.setLayoutY(-70);
            				Timeline timeline3 = new Timeline();
            				final KeyValue kv1 = new KeyValue(r.translateYProperty(), 695-r.getLayoutY());
                    		final KeyValue kv2 = new KeyValue(j.translateYProperty(), 700-j.getLayoutY());
                    		final KeyFrame kf = new KeyFrame(Duration.millis(10000), kv1,kv2);
                    		timeline3.getKeyFrames().add(kf);
            				timeline3.setCycleCount(Timeline.INDEFINITE);
            				timeline3.play();
            			}
            		});
            	}
            	else if(h_v && sens){
            		h_v=false; sens=false;
            		
            		rotation.setAngle(270);
                	r.getTransforms().addAll(rotation);
                	j.getTransforms().addAll(rotation);
            		
            		r.setLayoutY(j.getLayoutY()+10);
            		r.setLayoutX(j.getLayoutX()+5);
            		j.setLayoutY(j.getLayoutY()+5);
            		kv1 = new KeyValue(r.translateXProperty(), -r.getLayoutX());
            		kv2 = new KeyValue(j.translateXProperty(), -j.getLayoutX());
            		timeline2.setCycleCount(1);
            		timeline2.setOnFinished(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent e) {
            				r.setLayoutX(700);
            				j.setLayoutX(690);
            				Timeline timeline3 = new Timeline();
            				final KeyValue kv1 = new KeyValue(r.translateXProperty(), -r.getLayoutX());
                    		final KeyValue kv2 = new KeyValue(j.translateXProperty(), -j.getLayoutX());
                    		final KeyFrame kf = new KeyFrame(Duration.millis(10000), kv1,kv2);
                    		timeline3.getKeyFrames().add(kf);
            				timeline3.setCycleCount(Timeline.INDEFINITE);
            				timeline3.play();
            			}
            		});
            	}
            	else {
            		h_v=true; sens=false;
            		
            		rotation.setAngle(-90);
                	r.getTransforms().addAll(rotation);
                	j.getTransforms().addAll(rotation);
            		
            		j.setLayoutY(j.getLayoutY()-6);
            		j.setLayoutX(j.getLayoutX()-5);
            		r.setLayoutX(r.getLayoutX()-19);
            		
            		kv1 = new KeyValue(r.translateYProperty(), -r.getLayoutY());
            		kv2 = new KeyValue(j.translateYProperty(), -j.getLayoutY());
            		timeline2.setCycleCount(1);
            		timeline2.setOnFinished(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent e) {
            				r.setLayoutY(700);
            				j.setLayoutY(690);
            				Timeline timeline3 = new Timeline();
            				final KeyValue kv1 = new KeyValue(r.translateYProperty(), -r.getLayoutY());
                    		final KeyValue kv2 = new KeyValue(j.translateYProperty(), -j.getLayoutY());
                    		final KeyFrame kf = new KeyFrame(Duration.millis(10000), kv1,kv2);
                    		timeline3.getKeyFrames().add(kf);
            				timeline3.setCycleCount(Timeline.INDEFINITE);
            				timeline3.play();
            			}
            		});
            	}
            	
				final KeyFrame kf = new KeyFrame(Duration.millis(10000), kv1,kv2);
        		timeline2.getKeyFrames().add(kf);
        		timeline2.play();
    			
            }
        };
        minuteur.schedule(tache, 13675, 10000);
	}*/
	
	void acceleration() {
		
	}
	
	Voiture clicStop(Voiture v/*, Pane p,Route route, Timeline timeline*/) {
		r.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent e) {
		    	if(stop) stop=false;
		    	else if(!stop) stop=true;
		    		if(stop==false) {
		    			timeline.play();
		    		}
		    		else if(stop==true) {
		    			timeline.stop();
		    		}
		    }
		});
		return v;
	}
	
	/*
	static void lancerVoiture(Pane p, Scene s,Route r) {
		
		TimerTask tache = new TimerTask() {
	            public void run() {
	            	int i = (int)(Math.random()*4);
	            	int j = (int)(Math.random()*4);
	            	double vitesse = 7000 + (int)(Math.random()*5000);
	            	
					Voiture v = new Voiture(p, s, i, j, vitesse);
					ArrayList<Voiture> l = new ArrayList<Voiture>();
					l.add(v);
					r.listeVoiture.add(l);
					v.move(v,p);
					v = v.clicStop(v);
	            }
		 };
		 r.minuteur.schedule(tache, 6000, 5000);
	}
	*/
	
	static void creerVoiture(Pane p, Scene s,Route r) {
		int i = (int)(Math.random()*4);
    	int j = (int)(Math.random()*4);
    	double vitesse = 7000 + (int)(Math.random()*5000);
    	
		Voiture v = new Voiture(p, s, i, j, vitesse);
		ArrayList<Voiture> l = new ArrayList<Voiture>();
		l.add(v);
		r.listeVoiture.add(l);
		v.move(v,p);
		v = v.clicStop(v);
	}
	
}
