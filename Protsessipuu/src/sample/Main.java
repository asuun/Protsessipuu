package sample;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.Date;


public class Main extends Application{


    /*üle vaadata*/
    Button vaade = new Button("vaate muutmine");
    double xx;
    double yy;
    int counterRuut = 0;
    int counterLiide=0;
    double x1;
    double y1;
    double x2;
    double y2;
    Object aktiivne;

    Kast algusobjekt = new Kast();
    Liide aOliide1 =new Liide();
    Liide aOliide2=new Liide();

    Liitmine joon=new Liitmine();
    int id;

    LocalDate alustamine;
    LocalDate lõpetamine ;

    String eNimi=new String();
    String eKirjeldus=new String();


    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane kaart = new StackPane();
        kaart.setPrefSize(600, 600);
        TextField koordinaadid = new TextField();
        kaart.getChildren().addAll(koordinaadid);
        kaart.setStyle("-fx-background-color: #FFFAAF;");


        /*menuu objektide defineerimine*/
        Rectangle protsessiAlgusTaust = new Rectangle(140, 70, Color.TRANSPARENT);
        protsessiAlgusTaust.setStroke(Color.BLUEVIOLET);
        protsessiAlgusTaust.setStrokeWidth(10);


        Rectangle protsessiEtappTaust = new Rectangle(140, 70, Color.TRANSPARENT);
        protsessiEtappTaust.setStroke(Color.RED);
        protsessiEtappTaust.setStrokeWidth(10);
        Text protsessiEtappNimi = new Text("Protsessi Etapp");
        Group protsessiEtapp = new Group();
        protsessiEtapp.getChildren().addAll(protsessiEtappNimi, protsessiEtappTaust);

        Rectangle protsessivaheetappTaust = new Rectangle(140, 70, Color.TRANSPARENT);
        protsessivaheetappTaust.setStroke(Color.GREEN);
        protsessivaheetappTaust.setStrokeWidth(10);
        Text protsessivaheetappNimi = new Text("Protsessi vaheetapp");
        Group protsessiVaheetapp = new Group();
        protsessiVaheetapp.getChildren().addAll(protsessivaheetappNimi, protsessivaheetappTaust);

        Rectangle protsessiLõppTaust = new Rectangle(140, 70, Color.TRANSPARENT);
        protsessiLõppTaust.setStroke(Color.AQUA);
        protsessiLõppTaust.setStrokeWidth(10);
        Text protsessiLõppNimi = new Text("Protsessi Lõpp");
        Group protsessiLõpp = new Group();
        protsessiLõpp.getChildren().addAll(protsessiLõppNimi, protsessiLõppTaust);

        VBox menuu = new VBox(8);
        menuu.setSpacing(10);
        menuu.getChildren().addAll(protsessiAlgusTaust, protsessiEtapp, protsessiVaheetapp, protsessiLõpp);
        menuu.setStyle("-fx-background-color: #C0C0C0;");

        /*Juhtimisakna seadistamine*/
        TextField etapp = new TextField();
        TextField kirjeldus = new TextField();
        ComboBox<String> aegAlgus = new ComboBox<>();
        ComboBox<String> aegLõpp = new ComboBox<>();
        Button kinnita = new Button();

        VBox juhtPaneel = new VBox(4);
        juhtPaneel.setSpacing(5);
        kirjeldus.setPromptText("kirjeldus");
        etapp.setPromptText("etapp");
        aegAlgus.setPromptText("Protsessi algus");
        aegLõpp.setPromptText("Protsessi lõpp");
        kinnita.setText("Kinnita");

        juhtPaneel.getChildren().addAll(etapp, kirjeldus, aegAlgus, aegLõpp, kinnita);

        TextArea loggiraamat=new TextArea();

        /**/
        BorderPane tooaken = new BorderPane();
        tooaken.setCenter(kaart);
        tooaken.setLeft(menuu);
        tooaken.setRight(juhtPaneel);
        tooaken.setBottom(loggiraamat);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(tooaken, 600, 600));
        primaryStage.show();

            /*hiire koordinaatide saamine ja kuvamine*/
        tooaken.setOnMouseMoved(event -> {
            xx = event.getScreenX();
            yy = event.getScreenY();

            koordinaadid.setTranslateX(xx+30);
            koordinaadid.setTranslateY(yy-260);
            koordinaadid.setPrefSize(5,10);
            koordinaadid.setText("x=" + xx + "  y=" + yy);

        });
        /*kasti lohistamine töölauale,lihtsalt lohistamiseks*/
        protsessiAlgusTaust.setOnMouseDragged(event -> {
            xx = event.getSceneX();
            yy = event.getSceneY();
            protsessiAlgusTaust.setTranslateX(xx);
            protsessiAlgusTaust.setTranslateY(yy);

        });
        /*uue objekti loomine töölauale*/
        protsessiAlgusTaust.setOnMouseReleased(viskamine -> {
            xx = protsessiAlgusTaust.getTranslateX();
            yy = protsessiAlgusTaust.getTranslateY();
            counterRuut = counterRuut +1;
            algusobjekt.id= counterRuut;
            counterLiide=counterLiide+1;

            aOliide1.liiteID=counterLiide;
            aOliide2.liiteID=counterLiide;

            /*Kirjutamine*/
            Stage popuptäitmiseks = new Stage();
            VBox popupKere = new VBox();
            TextField eetapp = new TextField();
            TextField eekirjeldus = new TextField();
            DatePicker eeaegLõpp = new DatePicker();
            DatePicker eeaegAlgus = new DatePicker();
            Button eekinnita = new Button();
            TextField eexx=new TextField("x-koordinaadid");
            TextField eeyy=new TextField("y-koordinaadid");
            eetapp.setPromptText("etapp");
            eekirjeldus.setPromptText("kirjeldus");
            eekirjeldus.setPrefSize(100,40);
            eeaegAlgus.setPromptText("algus");
            eeaegLõpp.setPromptText("Lõpp");
            eekinnita.setText("Kinnita");

            eexx.setText(String.valueOf(xx));
            eeyy.setText(String.valueOf(yy));

            popuptäitmiseks.initModality(Modality.APPLICATION_MODAL);
            popupKere.getChildren().addAll(eexx,eeyy,eetapp, eekirjeldus, eeaegAlgus, eeaegLõpp, eekinnita);
            Scene popup = new Scene(popupKere, 300, 300);
            popuptäitmiseks.setScene(popup);
            Label popupPealkiri=new Label("Popup Window");
            popupPealkiri.setText(String.valueOf(counterRuut));
            popuptäitmiseks.show();

            eekinnita.setOnAction(event -> {
                eNimi=eetapp.getText();
                eKirjeldus=eekirjeldus.getText();
                alustamine=eeaegAlgus.getValue();
                lõpetamine=eeaegLõpp.getValue();

                algusobjekt=Objektid.etapiLisamine(protsessiAlgusTaust, counterRuut,eNimi,eKirjeldus);

                Text protsessialgusNimi = new Text("Protsessi algus");
                protsessialgusNimi.setText(String.valueOf(eNimi));
                protsessialgusNimi.setTranslateX(algusobjekt.getLocalToParentTransform().getTx());
                protsessialgusNimi.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()-algusobjekt.getHeight()/3+10);
                protsessialgusNimi.setX(0);
                protsessialgusNimi.setY(0);

                Text protsessialgusKirjeldus=new Text("Protsessi kirjeldus");
                protsessialgusKirjeldus.setText(String.valueOf(eKirjeldus));
                protsessialgusKirjeldus.setTranslateX(algusobjekt.getLocalToParentTransform().getTx());
                protsessialgusKirjeldus.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()-algusobjekt.getHeight()/3+20);
                protsessialgusKirjeldus.setX(0);
                protsessialgusKirjeldus.setY(0);

                Text protsessialgusAeg=new Text("alustamine");
                protsessialgusAeg.setText(String.valueOf(alustamine));
                protsessialgusAeg.setTranslateX(algusobjekt.getLocalToParentTransform().getTx()-27);
                protsessialgusAeg.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()-algusobjekt.getHeight()/5*2+40);

                Text protsessilõppAeg=new Text("lõpetamine");
                protsessilõppAeg.setText(String.valueOf(alustamine));
                protsessilõppAeg.setTranslateX(algusobjekt.getLocalToParentTransform().getTx()+27);
                protsessilõppAeg.setTranslateY(algusobjekt.getLocalToParentTransform().getTy()-algusobjekt.getHeight()/5*2+50);

                aOliide1 =Objektid.ylemine(algusobjekt,counterLiide);
                aOliide2 =Objektid.alumine(algusobjekt,counterLiide);


                kaart.getChildren().addAll(algusobjekt, aOliide1,aOliide2,protsessialgusNimi,protsessialgusKirjeldus,protsessialgusAeg,protsessilõppAeg);
                System.out.println(algusobjekt.id);
                popuptäitmiseks.hide();
                xx=0;
                yy=0;
                protsessiAlgusTaust.setTranslateX(0);
                protsessiAlgusTaust.setTranslateY(0);
            });

        });

        kaart.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            /*KAARDIL OPEREERIMINE*/
            @Override
            public void handle(MouseEvent mouseEvent) {

                /*Hiire klickiga täidab paremal olevad väljad*/
                kaart.setOnMouseClicked((MouseEvent event) -> {

                    Object aktiivne = event.getTarget();
                    /*Kui tegemist on Rectangle objektiga siis tahan selle andmeid lugeda ja muuta*/
                       if (aktiivne instanceof Kast){
                            etapp.setText(String.valueOf(((Kast)aktiivne).kastNimi));
                            kirjeldus.setText(String.valueOf(((Kast)aktiivne).kastKirjeldus));
                            kinnita.setOnAction(ülekirjutamine->{
                                ((Kast) aktiivne).kastNimi=etapp.getText();
                                ((Kast) aktiivne).kastKirjeldus=kirjeldus.getText();

                                /*aeg ka veel vaja lisada*/
                            });

                        }
                        else{
                        }
                });
                /*OBJEKTI KIRJELDUSTE LUGEMINE*/
                kaart.setOnMouseMoved(event -> {
                    Object aktiivne = event.getTarget();
                    if (aktiivne instanceof Kast){
                        loggiraamat.setText("kirjeldus on"+((Kast)aktiivne).kastKirjeldus);
                        loggiraamat.setText("KAst: " + ((Kast)aktiivne).id);
                        loggiraamat.setText("etapinimi on"+((Kast)aktiivne).kastNimi);

                    }
                });

                /*OBJEKTIDE LIITMINE*/

            }


        });
    }


    public static void main(String[] args) {
        launch(args);
    }


}


