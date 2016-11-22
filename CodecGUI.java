package pis.hue1;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;


/**
 * Created by Henrik on 20.11.2016.
 */
public class CodecGUI extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Codec code1 = new Wuerfel();
        Codec code2 = new Wuerfel();
        primaryStage.setTitle("Würfel");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 0));



        Label scenetitle = new Label("Würfel");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0);

        Label losunglabel1 = new Label("Losungswort1");
        grid.add(losunglabel1, 0, 1);

        TextField losungtext1 = new TextField();
        grid.add(losungtext1, 1, 1);

        Label losunglabel2 = new Label("Losungswort2");
        grid.add(losunglabel2, 0, 2);

        TextField losungtext2 = new TextField();
        grid.add(losungtext2, 1, 2);

        Button kodieren = new Button("Kodieren");
        HBox hbBtnkodieren = new HBox(10);
        hbBtnkodieren.getChildren().add(kodieren);
        grid.add(hbBtnkodieren, 0, 3);

        Button dekodieren = new Button("Dekodieren");
        HBox hbBtndekodieren = new HBox(10);
        hbBtndekodieren.getChildren().add(dekodieren);
        grid.add(hbBtndekodieren, 0, 4);

        Label klartextlabel = new Label("Bitte geben sie hier den zu verschlüsselnden oder zu entschlüsselnden Code ein:");
        grid.add(klartextlabel, 1, 3);

        TextArea klartextinput = new TextArea();
        grid.add(klartextinput, 1, 4);
        klartextinput.setId("klartext");

        Label error = new Label("");
        grid.add(error, 1, 0);
        error.setId("error");

        kodieren.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try{
                    code1.setzeLosung(losungtext1.getText());
                    code2.setzeLosung(losungtext2.getText());
                    error.setText("");
                }catch(IllegalArgumentException fehler){
                    error.setText(fehler.getMessage());
                }

                klartextinput.setText(code1.kodiere(code2.kodiere(klartextinput.getText())));
            }
        });

        dekodieren.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try{
                    code2.setzeLosung(losungtext2.getText());
                    code1.setzeLosung(losungtext1.getText());
                    error.setText("");
                }catch(IllegalArgumentException fehler){
                    error.setText(fehler.getMessage());
                }
                klartextinput.setText(code2.dekodiere(code1.dekodiere(klartextinput.getText())));
            }

        });

        Scene scene = new Scene(grid, 1200, 800);
        primaryStage.setScene(scene);

        scene.getStylesheets().add
                (Main.class.getResource("styles.css").toExternalForm());

        primaryStage.show();
    }




}
