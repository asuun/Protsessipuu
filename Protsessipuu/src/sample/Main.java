package sample;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;



public class Main extends Application{
    /*üle vaadata*/
    Stage Aken = new Stage();
    Button vaade = new Button("vaate muutmine");
    Button nupp = new Button("sisesta");
    double xx;
    double yy;
    int counter = 0;

    Rectangle algusobjekt = new Rectangle();
    Text nimetus;


    @Override
    public void start(Stage primaryStage) throws Exception {


        StackPane kaart = new StackPane();
        kaart.setPrefSize(600, 600);
        TextField koordinaadid = new TextField();
        kaart.getChildren().addAll(koordinaadid);

        /*menuu objektide defineerimine*/
        Rectangle protsessiAlgusTaust = new Rectangle(140, 70, Color.TRANSPARENT);
        protsessiAlgusTaust.setStroke(Color.BLUEVIOLET);
        protsessiAlgusTaust.setStrokeWidth(10);
        Text protsessialgusNimi = new Text("Protsessi algus");
        Group protsessialgus = new Group();
        protsessialgus.getChildren().addAll(protsessialgusNimi, protsessiAlgusTaust);
        protsessialgus.setUserData(protsessialgusNimi);

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
        menuu.getChildren().addAll(protsessialgus, protsessiEtapp, protsessiVaheetapp, protsessiLõpp);
        menuu.setStyle("-fx-background-color: #C0C0C0;");

        /*See tuleb maha võtta*/
        double menuuxx = menuu.getWidth();

        /*Juhtimisakna seadistamine*/
        TextField etapp = new TextField();
        TextField kirjeldus = new TextField();
        ComboBox aegAlgus = new ComboBox();
        ComboBox aegLõpp = new ComboBox();
        Button kinnita = new Button();

        VBox juhtPaneel = new VBox(4);
        juhtPaneel.setSpacing(5);
        kirjeldus.setPromptText("kirjeldus");
        etapp.setPromptText("etapp");
        aegAlgus.setPromptText("Protsessi algus");
        aegLõpp.setPromptText("Protsessi lõpp");
        kinnita.setText("Kinnita");
        nupp.setText("sisesta etapi kirjeldus");
        juhtPaneel.getChildren().addAll(etapp, kirjeldus, aegAlgus, aegLõpp, kinnita);


        /*SEE TULEB HILJEM, AJATELG */
        /*
        HBox juhtimine=new HBox(3);
        juhtimine.setStyle("-fx-background:#7FFFD4");
        juhtimine.setPrefSize(300,40);
        juhtimine.getChildren().add(vaade);
        */
        /**/
        BorderPane tooaken = new BorderPane();
        tooaken.setCenter(kaart);
        tooaken.setLeft(menuu);
        tooaken.setRight(juhtPaneel);
        //tooaken.setTop(juhtimine);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(tooaken, 600, 600));
        primaryStage.show();

            /*hiire koordinaatide saamine ja kuvamine*/
        kaart.setOnMouseMoved(event -> {
            double xx = event.getSceneX();
            double yy = event.getSceneY();
            koordinaadid.setTranslateX(xx);
            koordinaadid.setTranslateY(yy);
            koordinaadid.setText("x=" + xx + "  y=" + yy);
        });
        /*kasti lohistamine töölauale*/
        protsessialgus.setOnMouseDragged(event -> {
            double xx = event.getSceneX();
            double yy = event.getSceneY();
            protsessiAlgusTaust.setTranslateX(xx);
            protsessiAlgusTaust.setTranslateY(yy);
        });
        /*uue objekti loomine töölauale*/
        protsessialgus.setOnMouseReleased(viskamine -> {
            //protsessialgus.setTranslateX(30);
            //protsessialgus.setTranslateY(30);
            double xx = viskamine.getSceneX();
            double yy = viskamine.getSceneY();
            etapp.setFocusTraversable(true);
            counter = counter + 1;                      //abimuutuja, mille järgi antakse objektide nimedele järgunumber, nt etapp(counter)
            algusobjekt = Objektid.ringLisamine(xx, yy, menuuxx, etapp);        //Meetod ringilisamine tekitab uue ruudu töölauale
            nimetus = Objektid.ringiNimi(counter, xx, yy, menuuxx);             // Meetod ringilisamine tekitab uuele ruudule ka nime
            algusobjekt.setId(nimetus.toString());
            kaart.getChildren().addAll(algusobjekt, nimetus);
            //System.out.println(nimetus);
        });
/*
     nupp.setOnAction(event -> {
         etapp.setText("tehtud");
         Text l=new Text();
         l=Kirjeldus.proov(etapp,kirjeldus);
         System.out.println(l);
     });*/
 /*Objektile klickides saab selle andmed kätte*/
/*
     vaade.setOnAction(event -> {           //Nupp aktiveerib vaikimisi tekstid, see tuleb ära muuta
         VBox infoks = new VBox(20);
         Text uustekst = new Text("töötas");
         tooaken.setRight(infoks);
         TextField etappNimi=new TextField();
         etappNimi.setPromptText("\"etappi nimi\"");
         TextField etapiKirjeldus=new TextField();
         etapiKirjeldus.setPromptText("etapi kirjeldus");
         TextField etapiMuutujad=new TextField();
         etapiMuutujad.setPromptText("muutujad");
         String eNimi=etappNimi.getText();
         System.out.println(eNimi);
         infoks.getChildren().addAll(uustekst,etapiKirjeldus,etapiMuutujad,etappNimi);
     });
*/
        kaart.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            /*KAARDIL OLEVATE OBJEKTIDE ÄRA TUNDMINE*/
            @Override
            public void handle(MouseEvent mouseEvent) {

                kaart.setOnMouseClicked((MouseEvent event) -> {
                    Object aktiivne = event.getTarget();
                    System.out.println(event.getTarget());

                    if (aktiivne instanceof TextField) {
                        String lol = ((TextField) aktiivne).getText().toString();
                    }
                    /*Kui tegemist on Rectangle objektiga siis tahan selle andmeid lugeda ja muuta*/
                    else if (aktiivne instanceof Rectangle){
                        System.out.println("ruut");
                        String nimi=((Rectangle)aktiivne).getId();
                        System.out.println(nimi);
                        ((Rectangle)aktiivne).setUserData("sds");
                        System.out.println(((Rectangle)aktiivne).getUserData());
                        etapp.requestFocus();
                    /*töölaual olevate objektide asukoha muutmine*/
                        ((Rectangle)aktiivne).setOnMouseDragged(event1 -> {
                            double xx=event1.getSceneX();
                            double yy=event1.getSceneY();
                            System.out.println("vedamine");
                            ((Rectangle)aktiivne).setTranslateX(xx);
                            ((Rectangle)aktiivne).setTranslateY(yy);
                                });
                    }
                    else if(aktiivne instanceof Group){
                        System.out.println("päriselt ongi grupp");
                    }
                    else if(aktiivne instanceof StackPane){
                        System.out.println(counter=counter+1);
                    }
                    else if(aktiivne instanceof Text){
                        System.out.println("kiri");
                    }
                    else if(aktiivne instanceof Node){
                        System.out.println("node");
                    }
                    else{//(aktiivne instanceof Group)
                        System.out.println("midagi muud");
                    }
                });
/*
                    System.out.println(sitt.lookupAll(pask));
                    String sourceID = event.getSource().toString();
                    sourceID = sourceID.substring(sourceID.indexOf('=') + 1, sourceID.indexOf(','));
                    Node mingi= sitt.lookup(sourceID);
                   Group mingis=(Group)mingi.getUserData();
                   mingis.setUserData("yoyo");
                    Group junn=(Group)sitt.lookup("#"+sourceID);
                    kaart.lookup(sourceID);
                    kaart.getChildren().removeAll(kaart.lookup(sourceID));
                    System.out.println(mingi.getUserData());
                    //System.out.println(pask);
                    //System.out.println(event.getTarget());
                            String identi=event.getTarget().toString();
                            System.out.println(identi);
                            final Popup kinnitus=new Popup();
                            kinnitus.setX(600);
                            kinnitus.setY(300);
                            kinnitus.setHeight(300);
                            kinnitus.setWidth(400);
                            Button kustuta=new Button();
                            kustuta.setText("Kustuta");
                            Button tagasi=new Button();
                            tagasi.setText("Tagasi");
                            tagasi.setLayoutX(200);
                            kustuta.setLayoutX(100);
                            HBox kustutad=new HBox(2);
                            Circle ummargune=new Circle(100,Color.AZURE);       //praegu on ümmargune ring lihtaslt tehtud

                            kustutad.getChildren().addAll(kustuta,tagasi, ummargune);
                            //primaryStage.setScene(new Scene(kustutad,500,500));
                            EventTarget kustutamiseks=event.getTarget();
                            kinnitus.getContent().addAll(kustutad);
                            kinnitus.show(primaryStage);

                    kustuta.setOnAction(event1 -> {
                                kaart.getChildren().remove(kustutamiseks);
                                kaart.getChildren().remove(event.getTarget());
                                kinnitus.hide();
                            });
                            tagasi.setOnAction(event1 -> {
                                kinnitus.hide();

                            });
                     */
            }

            ;


        });
    }


    public static void main(String[] args) {
        launch(args);
    }

}


