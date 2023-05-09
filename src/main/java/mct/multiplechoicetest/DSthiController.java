package mct.multiplechoicetest;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DSthiController implements Initializable {
    @FXML
    private HBox hboxQuestion;
    @FXML
    private ImageView avatar;

    @FXML
    void changePage(MouseEvent event) {
        Parent root;
        Scene scene;
        Stage stage;
        try {
            root = FXMLLoader.load(getClass().getResource("DScauhoi-view.fxml"));
            stage = (Stage) (avatar.getScene().getWindow());
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PopOver popOver;

    @FXML
    void openSetting(MouseEvent event) {
        if (popOver != null && popOver.isShowing()) {
            popOver.hide();
            return;
        }
        hboxQuestion.setVisible(true);
        popOver = new PopOver();
        popOver.setArrowLocation(ArrowLocation.TOP_RIGHT);
        popOver.setContentNode(hboxQuestion);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        popOver.setOnHidden((e) -> hboxQuestion.setVisible(false));
        popOver.show((Node) event.getSource());
        popOver.setAnchorX(popOver.getAnchorX() + 15);
    }

    @FXML
    public void setButtonHoverEffect(MouseEvent event) {
        Button button = (Button) event.getSource();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.1);
        button.setEffect(colorAdjust);
        button.setCursor(Cursor.HAND);
    }

    @FXML
    public void setButtonExitEffect(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setEffect(null);
        button.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void setLabelUnderline(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setUnderline(true);
        label.setCursor(Cursor.HAND);
    }

    @FXML
    void setLabelExit(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setUnderline(false);
        label.setCursor(Cursor.DEFAULT);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources){
        hboxQuestion.setVisible(false);
    }




}