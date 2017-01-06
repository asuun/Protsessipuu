package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Created by The Dog on 30.12.2016.
 */
public class Objektid {

    public static Kast etapiLisamine(Rectangle protsessialgusTaust, int counterRuut, String eNimi, String eKirjeldus) {
        Kast ruut = new Kast();
        ruut.setWidth(140);
        ruut.setHeight(70);
        ruut.setFill(Color.TRANSPARENT);
        ruut.setStroke(Color.BLUE);
        ruut.setStrokeWidth(10);

        ruut.setTranslateX(protsessialgusTaust.getLocalToSceneTransform().getTx());
        ruut.setTranslateY(protsessialgusTaust.getLocalToSceneTransform().getTy());
        //ruut.setTranslateY(yy - 260);
        ruut.id = counterRuut + 1;
        ruut.kastNimi=(String.valueOf(eNimi));
        ruut.kastKirjeldus=(String.valueOf(eKirjeldus));

        return ruut;


    }
    public static Liide ylemine( Kast algusobjekt,int counterpunkt){
        Liide ylemine=new Liide();
        ylemine.setRadius(5);
        ylemine.setTranslateX(algusobjekt.getLocalToParentTransform().getTx());
        ylemine.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()-algusobjekt.getHeight()/2-4);
        ylemine.liiteID=counterpunkt+1;
        return ylemine;
    }
    public static Liide alumine(Kast algusobjekt,int counterpunkt){
        Liide alumine=new Liide();
        alumine.setRadius(5);
        alumine.setTranslateX(algusobjekt.getLocalToParentTransform().getTx());
        alumine.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()+algusobjekt.getHeight()/2+4);
        return alumine;


    }

    public static Liitmine ühendused(double x1,double x2, double y1, double y2){
        Liitmine joon=new Liitmine();
        joon.setStartX(x1);
        joon.setStartY(y1);
        joon.setEndX(x2);
        joon.setEndY(x2);
        return joon;
    }
}



