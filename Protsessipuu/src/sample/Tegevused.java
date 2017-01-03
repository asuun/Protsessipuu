package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by The Dog on 02.01.2017.
 */

/*SEDA KLASSI HETKEL EI KASUTATA*/
public class Tegevused {
    public static void vajutameNuppuVaade(Button nupp){
            nupp.setOnAction(event ->
            System.out.println("yoyooy"));
           // etapp.setText("tehtud");
            Text l=new Text("töötas");

            System.out.println(l);


        };
    }



