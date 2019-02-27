package reseau;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Route {
	ArrayList<ArrayList<Voiture>> listeVoiture = new ArrayList<ArrayList<Voiture>>(16);
	
	public Route(Pane p) {
		Line[] horizontal = new Line[4];
		Line[] vertical = new Line[4];
		
		for(int i=0; i<4 ;i++) {
			horizontal[i] = new Line(0,92.5+150*(i)+25*(i),700,92.5+150*(i)+25*(i));
			horizontal[i].setStrokeWidth(26);
			horizontal[i].setStroke(Color.DARKBLUE);
			p.getChildren().add(horizontal[i]);	
			for(int j=0; j<24; j++) {
				Rectangle r = new Rectangle(30*j, 90.5+150*(i)+25*(i),10,4);
				r.setFill(Color.YELLOW);
				p.getChildren().add(r);
			}
			
		}
		
		for(int i=0; i<4; i++) {
			vertical[i] = new Line(92.5+150*(i)+25*(i),0,92.5+150*(i)+25*(i),700);
			vertical[i].setStrokeWidth(26);
			vertical[i].setStroke(Color.DARKBLUE);
			p.getChildren().add(vertical[i]);
			for(int j=0; j<24; j++) {
				Rectangle r = new Rectangle(90.5+150*(i)+25*(i),30*j,4,10);
				r.setFill(Color.YELLOW);
				p.getChildren().add(r);
			}
		}
	}
	
	
	
	
	
}
