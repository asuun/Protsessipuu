package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * Created by The Dog on 30.12.2016.
 */
public class Objektid {

    public static Text ringiNimi( int counter,double xx, double yy, double menuuxx){
        Text nimi=new Text();
        String a="lalal";

        nimi.setUserData(a);
        nimi.setText(a);
        nimi.setTranslateX(xx-menuuxx);
        nimi.setTranslateY(yy);
        return nimi;

    }
    public static Rectangle ringLisamine (double xx, double yy, double menuuxx, TextField etapp){
        Rectangle ring=new Rectangle(130,140, Color.BLUE);
        ring.setTranslateX(xx-menuuxx);
        ring.setTranslateY(yy);
        ring.setUserData("loll");
       return ring;
    };
    public static Group tegevuseLisamine(double xx, double yy, TextField text1,int counter){
        counter=counter+1;
        Text etappLisamine=new Text();
        etappLisamine.setText(text1.toString());

        Text kirjeldusLisamine=new Text();
        kirjeldusLisamine.setText(text1.toString());
        kirjeldusLisamine.setLayoutY(30);

        Rectangle etappKere=new Rectangle(150,130,Color.AZURE);
        etappKere.setLayoutX(60);
        etappKere.setLayoutY(50);



        final Popup lisamine=new Popup();
        lisamine.setX(600);
        lisamine.setY(300);
        lisamine.setHeight(300);
        lisamine.setWidth(400);
        TextField esimene=new TextField();
        esimene.setPromptText("etapp");
        TextField teine=new TextField();
        teine.setPromptText("kirjeldus");
        Rectangle chasis=new Rectangle(300,300,Color.BLUE);
        Button tagasi=new Button();
        tagasi.setText("Tagasi");
        tagasi.setLayoutY(40);

        Button kinnita=new Button();
        tagasi.setText("kinnita");

        Group tegevuse=new Group();
        tegevuse.getChildren().addAll(etappKere,kirjeldusLisamine,etappLisamine);
        tegevuse.setTranslateX(xx);
        tegevuse.setTranslateY(yy);

        return tegevuse;

    }
};

