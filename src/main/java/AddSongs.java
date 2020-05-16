import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class AddSongs {
    public static void display(String title, String message){
        final Stage window = new Stage();
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Label lbl = new Label(message);
        grid.add(lbl,0,1);

        TextField txtName = new TextField();
        txtName.setPromptText("Song name");
        grid.add(txtName,1,1);

        Button addSongs = new Button("Add song");
        grid.add(addSongs,1,2);

        addSongs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = txtName.getText().toString();
                try(BufferedWriter bw = new BufferedWriter(new FileWriter("songs.txt",true))){
                    bw.write(name);
                    bw.newLine();
                    window.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(grid,500,500);
        window.setScene(scene);
        window.show();

    }
}
