package mct.multiplechoicetest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Callback;


public class DScauhoiController implements Initializable {
    @FXML
    private ComboBox<TreeItem<String>> selectCateBox;


    public TreeItem<String> listCategories(){
        TreeItem<String> rootItem = new TreeItem<>("Course: IT");
        TreeItem<String> topItem = new TreeItem<>("Top for IT");
        TreeItem<String> cate1Item = new TreeItem<>("Default for IT");
        TreeItem<String> cate2Item = new TreeItem<>("Sinh học kỳ 2 L7");
        TreeItem<String> cate3Item = new TreeItem<>("Sử địa giữa kỳ 2 L7");
        rootItem.getChildren().addAll(topItem);
        topItem.getChildren().addAll(cate1Item, cate2Item, cate3Item);
        rootItem.setExpanded(true);
        return rootItem;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectCateBox.getItems().add(listCategories());
    }
}
