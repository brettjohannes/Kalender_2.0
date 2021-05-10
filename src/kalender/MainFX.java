package kalender;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;

import java.io.IOException;

public class MainFX extends Application {

    Scene peaMenüü, kalendriVaade, sissekandeLisamine;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Kalender kalender = new Kalender();
        kalender.loeKalender("kalender.txt");
        primaryStage.setTitle("Kalender Extraordinaire 2021");

        //PEAMENÜÜ
        GridPane menüüPaneel = new GridPane();
        Label label1 = new Label("Mida soovite teha?");
        Button nNäitaKalender = new Button("Vaata kalendrit");
        nNäitaKalender.setOnAction(e -> primaryStage.setScene(kalendriVaade));
        Button nLisaSissekanne = new Button("Lisa sissekanne");
        nLisaSissekanne.setOnAction(e -> primaryStage.setScene(sissekandeLisamine));
        Button nVälju = new Button("Välju kalendrist");
        nVälju.setOnAction(e -> {
            kalender.salvestaKalender("kalender.txt");
            primaryStage.close();
        });
        ObservableList<Button> nupudPeamenüü = FXCollections.observableArrayList(nNäitaKalender, nLisaSissekanne, nVälju);
        ListView<Button> valikudPeamenüü = new ListView<>(nupudPeamenüü);
        menüüPaneel.setMinSize(400, 400);
        menüüPaneel.setPadding(new Insets(10, 10, 10, 10));
        menüüPaneel.setVgap(5);
        menüüPaneel.setHgap(5);
        menüüPaneel.setAlignment(Pos.CENTER);
        menüüPaneel.add(label1, 1, 0);
        menüüPaneel.add(nNäitaKalender, 0, 1);
        menüüPaneel.add(nLisaSissekanne, 1, 1);
        menüüPaneel.add(nVälju, 2, 1);
        peaMenüü = new Scene(menüüPaneel);

        //NÄITA KALENDRIT
        VBox kalendriPaneel = new VBox();
        HBox ülemisedNupud = new HBox();
        HBox alumisedNupud = new HBox();
        ObservableList<String> sündmused = kalender.tagastaSündmused();
        ObservableList<String> meeldetuletused = kalender.tagastaMeeldetuletused();
        ObservableList<String> ülesanded = kalender.tagastaÜlesanded();
        ObservableList<String> sissekanded = FXCollections.observableArrayList(sündmused);
        sissekanded.addAll(meeldetuletused);
        sissekanded.addAll(ülesanded);
        ListView<String> kõikSissekanded = new ListView<>();
        kõikSissekanded.setItems(sissekanded);
        Button kõikSissekandedNupp = new Button("Kõik");
        kõikSissekandedNupp.setOnAction(event -> {
            kõikSissekanded.setItems(sissekanded);
        });
        Button sündmusedNupp = new Button("Sündmused");
        sündmusedNupp.setOnAction(event -> {
            kõikSissekanded.setItems(sündmused);
        });
        Button meeldetuletusedNupp = new Button("Meeldetuletused");
        meeldetuletusedNupp.setOnAction(event -> {
            kõikSissekanded.setItems(meeldetuletused);
        });
        Button ülesandedNupp = new Button("Ülesanded");
        ülesandedNupp.setOnAction(event -> {
            kõikSissekanded.setItems(ülesanded);
        });
        Button kustutaNupp = new Button("Kustuta");
        kustutaNupp.setOnAction(event -> {
            //Sain abi siit:
            //https://gist.github.com/jewelsea/5559262
            int selectedIdx = kõikSissekanded.getSelectionModel().getSelectedIndex();
            if (selectedIdx != -1) {
                String itemToRemove = kõikSissekanded.getSelectionModel().getSelectedItem();

                int newSelectedIdx =
                        (selectedIdx == kõikSissekanded.getItems().size() - 1)
                                ? selectedIdx - 1
                                : selectedIdx;

                kõikSissekanded.getItems().remove(selectedIdx);
                kõikSissekanded.getSelectionModel().select(newSelectedIdx);

                kalender.eemaldaSissekanne(itemToRemove);
                kalender.väljastaSündmused();
            }
        });
        Button tagasiNupp = new Button("Tagasi");
        tagasiNupp.setOnAction(e -> primaryStage.setScene(peaMenüü));
        ülemisedNupud.getChildren().addAll(kõikSissekandedNupp, sündmusedNupp, meeldetuletusedNupp, ülesandedNupp);
        ülemisedNupud.setAlignment(Pos.CENTER);
        alumisedNupud.getChildren().addAll(kustutaNupp, tagasiNupp);
        alumisedNupud.setAlignment(Pos.CENTER);
        kalendriPaneel.setAlignment(Pos.CENTER);
        kalendriPaneel.getChildren().addAll(ülemisedNupud, kõikSissekanded, alumisedNupud);
        kalendriPaneel.setMinSize(400, 400);
        kalendriVaade = new Scene(kalendriPaneel);

        //LISA SISSEKANNE
        GridPane sissekandePaneel = new GridPane();
        //dropdown menüü
        ObservableList<String> sissekandeTüüp = FXCollections.observableArrayList(
                "Meeldetuletus",
                "Sündmus",
                "Ülesanne"
        );
        final ComboBox sissekandeDropDown = new ComboBox(sissekandeTüüp);
        //infotekst
        Text sissekanneTeavitus = new Text ("Vali sissekande tüüp:");
        //kirjeldus
        TextField kirjelduseKast = new TextField("Kirjeldus...");
        //algusaeg
        TextField kpKast1 = new TextField("DD/MM");
        TextField aegKast1 = new TextField("HH:MM");
        //lõppaeg
        TextField kpKast2 = new TextField("Lõpp: DD/MM");
        TextField aegKast2 = new TextField("HH:MM");
        kpKast2.setVisible(false);
        aegKast2.setVisible(false);
        //kinnitusnupp ja tagasinupp
        Button kinnitaSissekanne = new Button("Kinnita");
        Button tagasiNupp2 = new Button("Tagasi");
        tagasiNupp2.setOnAction(e -> primaryStage.setScene(peaMenüü));
        //dropdown menüü muutused (lõppaegade nähtavus)
        sissekandeDropDown.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    if (newValue.equals(0)) { //kui valitud 'Meeldetuletused'
                        kpKast2.setVisible(false);
                        aegKast2.setVisible(false);
                    } else if (newValue.equals(1)) { //kui valitud 'Sündmused'
                        kpKast2.setVisible(true);
                        aegKast2.setVisible(true);
                    } else if (newValue.equals(2)) { //kui valitud 'Ülesanded'
                        kpKast2.setVisible(false);
                        aegKast2.setVisible(false);
                    } else { //kui muu
                        kpKast2.setVisible(false);
                        aegKast2.setVisible(false);
                    }
                });
        //kinnituse nupu põhjal sissekande loomine
        kinnitaSissekanne.setOnAction(e -> {
            String valik = (String) sissekandeDropDown.getSelectionModel().getSelectedItem();
            if (valik.equals("Sündmus")) {
                try {
                    Sündmus sdLisa = new Sündmus(kirjelduseKast.getText(), kpKast1.getText(), aegKast1.getText(), kpKast2.getText(), aegKast2.getText());
                    kalender.lisaSissekanne(sdLisa);
                    System.out.println("Lisasin " + sdLisa.toString());
                    kalender.väljastaSündmused();
                } catch (Exception lisamisError) {
                    //error tegevus
                    System.out.println("Tekkis error - SÜNDMUSED");
                }
            } else if (valik.equals("Meeldetuletus")) {
                try {
                    Meeldetuletus mtLisa = new Meeldetuletus(kirjelduseKast.getText(), kpKast1.getText(), aegKast1.getText());
                    kalender.lisaSissekanne(mtLisa);
                    System.out.println("Lisasin " + mtLisa.toString());
                    kalender.väljastaMeeldetuletused();
                } catch (Exception lisamisError) {
                    //error tegevus
                    System.out.println("Tekkis error - MEELDETULETUSED");
                }
            } else if (valik.equals("Ülesanne")) {
                try {
                    Ülesanne ülLisa = new Ülesanne(kirjelduseKast.getText(), kpKast1.getText(), aegKast1.getText());
                    kalender.lisaSissekanne(ülLisa);
                    System.out.println("Lisasin: " + ülLisa.toString());
                    kalender.väljastaÜlesanded();
                } catch (Exception lisamisError) {
                    //error tegevus
                    System.out.println("Tekkis error - ÜLESANDED");
                }
            } else {
                //ei tee midagi..?
            }
        });

        sissekandePaneel.add(sissekanneTeavitus, 0, 0);
        sissekandePaneel.add(sissekandeDropDown, 1, 0);
        sissekandePaneel.add(kirjelduseKast, 0, 1);
        sissekandePaneel.add(kpKast1, 1, 1);
        sissekandePaneel.add(aegKast1, 2, 1);
        sissekandePaneel.add(kpKast2, 1, 2);
        sissekandePaneel.add(aegKast2, 2, 2);
        sissekandePaneel.add(kinnitaSissekanne, 2, 0);
        sissekandePaneel.add(tagasiNupp2,0,2);

        sissekandeLisamine = new Scene(sissekandePaneel);

        primaryStage.setScene(peaMenüü);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
