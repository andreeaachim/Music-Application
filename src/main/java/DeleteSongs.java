import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;

public class DeleteSongs {
    public static void display(String title, String message) {
        final Stage window = new Stage();
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Label lbl = new Label(message);
        grid.add(lbl, 0, 1);

        TextField txtName = new TextField();
        txtName.setPromptText("Song name");
        grid.add(txtName, 1, 1);

        Button deleteSongs = new Button("Delete song");
        grid.add(deleteSongs, 1, 2);

        deleteSongs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = txtName.getText().toString();
                File inputFile = new File("songs.txt");
                File tempFile = new File("temp.txt");
                
                try {
                    String lineToRemove = name;
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            if (!line.equals(lineToRemove)) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                    }
                    if (inputFile.delete()) {
                        if (!tempFile.renameTo(inputFile)) {
                            throw new IOException("Error");
                        }
                    } else {
                        throw new IOException("Could ");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                window.close();
            }
        });

        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);
        window.show();
    }
}
