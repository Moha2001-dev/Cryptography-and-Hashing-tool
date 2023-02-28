

package computersecurityproject;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextFormatter;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label Close;
    @FXML
    private Label Minimize;
    @FXML
    private AnchorPane Fotter;
    @FXML
    private AnchorPane Home;
    @FXML
    private AnchorPane Encryption;
    @FXML
    private AnchorPane Hashing;
    @FXML
    private AnchorPane About;
    @FXML
    private AnchorPane Enp1;
    @FXML
    private AnchorPane Enp2;
    @FXML
    private AnchorPane Done;
    @FXML
    private AnchorPane Headder1;
    @FXML
    private AnchorPane busy;
    // Encryption page 1
    @FXML
    private RadioButton encryptionMood;
    @FXML
    private RadioButton decryptionMood;
    @FXML
    private RadioButton DESalgorithem;
    @FXML
    private RadioButton AESalgorithem;
    // Encription Page 2
    @FXML
    private TextField keyTextField;
    @FXML
    private Label encryptionError;
    @FXML
    private Label pathLabel;
    private String url;
    // Home.Done
    @FXML
    private Label outputFile;
    //================
    int count = 0;
    String fileName;
    int fileChooserMode = 2;
    //==============
    @FXML
    private Label pathLabelHash;
    @FXML
    private Label hashError;
    @FXML
    private RadioButton SHA256;
    @FXML
    private RadioButton SHA512;
    //==============
    @FXML
    private Label HomeLabel;
    @FXML
    private Label AboutUsLabel;
    @FXML
    private Label EncryptionLabel;
    @FXML
    private Label HashingLabel;
    int lastIndex = 0;
    private FileInputStream file;
    @FXML
    private Label EncryptionPage2Label;
    @FXML
    private Button EncryptDecrept;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        home();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize() {
        Stage stage = (Stage) Minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void home() {
        HomeLabel.setStyle("-fx-font-weight: bold;"+"-fx-text-fill: #2D0063;");
        EncryptionLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        HashingLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        AboutUsLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        hashReset();
        EncryptionReset();
        translateXRX(Encryption);
        translateXRX(Hashing);
        translateXRX(About);
        translateXLR(Home);
        translateXLR(Fotter);
    }

    @FXML
    private void encryption() {
        HomeLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        EncryptionLabel.setStyle("-fx-font-weight: bold;"+"-fx-text-fill: #2D0063;");
        HashingLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        AboutUsLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        if (!Enp1.isVisible()) {
            Enp1.setVisible(true);
        }
        if (Enp2.isVisible()) {
            Enp2.setVisible(false);
        }
        hashReset();
        translateXRX(Home);
        translateXRX(Hashing);
        translateXRX(About);
        translateXRX(Fotter);
        translateXLR(Encryption);
    }

    @FXML
    private void hashing() {
        HomeLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        EncryptionLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        HashingLabel.setStyle("-fx-font-weight: bold;"+"-fx-text-fill: #2D0063;");
        AboutUsLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        hashReset();
        translateXRX(Home);
        translateXRX(Encryption);
        translateXRX(About);
        translateXRX(Fotter);
        translateXLR(Hashing);
        fileChooserMode = 3;
    }

    @FXML
    private void about() {
        HomeLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        EncryptionLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        HashingLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        AboutUsLabel.setStyle("-fx-font-weight: bold;"+"-fx-text-fill: #2D0063;");
        hashReset();
        EncryptionReset();
        translateXRX(Home);
        translateXRX(Encryption);
        translateXLR(About);
        translateXRX(Fotter);
        translateXRX(Hashing);
    }

    @FXML
    private void checkEncryptionPart1() {
        if (DESalgorithem.isSelected()) {
            keyTextField.setPromptText("8 length key");
            lastIndex = 8;
            EncryptionPage2Label.setText("DES");
        } else if (AESalgorithem.isSelected()) {
            keyTextField.setPromptText("24 length key");
            lastIndex = 24;
            EncryptionPage2Label.setText("AES");
        }
        encryptionDonePart1();
        EncryptionReset();
        if(decryptionMood.isSelected()){
            EncryptionPage2Label.setText(EncryptionPage2Label.getText()+"/Decrypt");
            EncryptDecrept.setText("Decrypt");
        }else{
            EncryptionPage2Label.setText(EncryptionPage2Label.getText()+"/Encrypt");
            EncryptDecrept.setText("Encrypt");
        }
    }
    private void encryptionDonePart1() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(Enp1);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Enp1.isVisible()) {
                    Enp1.setVisible(false);
                    Enp1.setOpacity(1);
                }

                if (!Enp2.isVisible()) {
                    Enp2.setOpacity(0);
                    Enp2.setVisible(true);
                    fade.setDuration(Duration.millis(300));
                    fade.setNode(Enp2);
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Enp2.setOpacity(1);
                        }
                    });
                    fade.play();
                }
            }
        });
        fade.play();
        if(decryptionMood.isSelected()){
            fileChooserMode = 1;
        }else if(encryptionMood.isSelected()){
            fileChooserMode = 2;
        }
        pathLabel.setText(null);
        keyTextField.setText("");
    }

    @FXML
    private void checkEncryptionPart2() throws Exception {
        if (url != null) {
            encryptionError.setText("");
            if (DESalgorithem.isSelected()) {
                if (keyTextField.getText().trim().length() == 8) {
                    encryptionError.setText("");
                    DES des = new DES(Base64.getEncoder().encodeToString(keyTextField.getText().getBytes()), url);
                    if (encryptionMood.isSelected()) {
                        des.encryption();
                        
                    } else if (decryptionMood.isSelected()) {
                        des.decryption();
                    }
                    if(des.badKey){
                        encryptionError.setText("make sure to enter the correct key!");
                    }else{
                    count = des.counter;
                    fileName = des.getOut();
                    des = null;
                    encryptionDonePart2();
                    file.close();
                    url = null;
                    }
                } else {
                    encryptionError.setText("make sure to enter 8 length key");
                }
            } else if (AESalgorithem.isSelected()) {
                if (keyTextField.getText().trim().length() == 24) {
                    encryptionError.setText("");
                    AES aes = new AES(Base64.getEncoder().encodeToString(keyTextField.getText().getBytes()), url);
                    if (encryptionMood.isSelected()) {
                        aes.encryption();
                    } else if (decryptionMood.isSelected()) {
                        aes.decryption();
                    }
                    if(aes.badKey){
                        encryptionError.setText("make sure to enter the correct key!");
                    }else{
                        count = aes.counter;
                    fileName = aes.getOut();
                    aes = null;
                        
                        encryptionDonePart2();
                        file.close();
                        url = null;
                    }
                    
                } else {
                    encryptionError.setText("make sure to enter 24 length key");
                }
            }
        } else {
            encryptionError.setText("make sure to choose a file to proceed");
        }
    }

    private void encryptionDonePart2() {
        if (Enp1.isVisible()) {
            Enp1.setVisible(false);
        }
        if (Enp2.isVisible()) {
            Enp2.setVisible(false);
        }
        if (!Done.isVisible()) {
            outputFile.setText("output file: "+fileName);
            Done.setVisible(true);
        }
        translateXLR(Home);
        translateXRX(Encryption);
        translateXRX(About);
        translateXLR(Fotter);
        translateXRX(Hashing);
    }

    @FXML
    private void checkHashing() throws Exception {
        pathLabelHash.setText("");
        hashError.setText("");
        if(url != null){
            Hash hash = new Hash(url);
            if(SHA256.isSelected()){
                hash.SHA256();
            }else if(SHA512.isSelected()){
                hash.SHA512();
            }
            fileName = hash.getOut();
            hash = null;
            hashingDone();
            file.close();
        }else{
            hashError.setText("make sure to choose a .txt file to proceed");
        }
    }

    private void hashingDone() {
        if (!Done.isVisible()) {
            Done.setVisible(true);
        }
        outputFile.setText("output file: "+fileName);
        translateXLR(Home);
        translateXRX(Encryption);
        translateXRX(About);
        translateXLR(Fotter);
        translateXRX(Hashing);
    }

    @FXML
    private void done() {
        outputFile.setText("output file:");
        if (Done.isVisible()) {
            Done.setVisible(false);
        }
        keyTextField.setText("");
        url = null;
        HomeLabel.setStyle("-fx-font-weight: bold;"+"-fx-text-fill: #2D0063;");
        EncryptionLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        HashingLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        AboutUsLabel.setStyle("-fx-font-weight: normal;"+"-fx-text-fill: #707070;");
        translateXLR(Home);
        translateXRX(Encryption);
        translateXRX(About);
        translateXLR(Fotter);
        translateXRX(Hashing);
    }

    private void translateXRX(AnchorPane ap) {
        if (ap.isVisible()) {
            double orX = ap.getLayoutX();
            TranslateTransition translate = new TranslateTransition();
            translate.setDuration(Duration.millis(300));
            translate.setNode(ap);
            translate.setInterpolator(Interpolator.EASE_BOTH);
            translate.setFromX(orX);
            translate.setToX(orX + 640);
            translate.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ap.setVisible(false);
                    ap.setLayoutX(orX);
                }
            });
            translate.play();
        }
    }

    private void translateXLR(AnchorPane ap) {
        if (!ap.isVisible()) {
            double orX = ap.getLayoutX();
            ap.setVisible(true);
            ap.setLayoutX(orX - 640);
            TranslateTransition translate = new TranslateTransition();
            translate.setDuration(Duration.millis(300));
            translate.setNode(ap);
            translate.setInterpolator(Interpolator.EASE_BOTH);
            translate.setFromX(orX - 640);
            translate.setByX(640);
            translate.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ap.setLayoutX(orX);
                }
            });
            translate.play();
        }
    }

    @FXML
    private void fileChooser(ActionEvent event) {
        if(!busy.isVisible()){
            busy.setVisible(true);
        }
        try{
            FileChooser fc = new FileChooser();
        switch(fileChooserMode){
            case 1: //decryption file chooser
                fc.getExtensionFilters().addAll(new ExtensionFilter("Encrypted Document", "*.encrypted"));
                break;
            case 2: //encryption file chooser
            case 3: //Hash file chooser
            default:
                fc.getExtensionFilters().addAll(new ExtensionFilter("Text Document", "*.txt"));
        }
        File selectedFile = fc.showOpenDialog(null);
        file = new FileInputStream(selectedFile);
        if (selectedFile != null) {
            url = selectedFile.getAbsolutePath();
            if(Hashing.isVisible()){
                pathLabelHash.setText(url);
            }else if(Encryption.isVisible()){
                pathLabel.setText(url);
            }
            encryptionError.setText("");
            hashError.setText("");
        } else {
            url = null;
        }
        }catch(Exception e){
            
        }finally{
            if(busy.isVisible()){
                busy.setVisible(false);
            }
        }
    }
    @FXML
    private void checkTextField(){
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if(c.getText().equals("")){    
                return c;
            }else if(keyTextField.getText().length()>lastIndex-1){
                return null;
            }else if (c.getText().matches("^[0-9a-zA-Z]+$")) {
                return c ;
            }
            return null;
        };
        keyTextField.setTextFormatter(new TextFormatter<String>(filter));
    }

    private void hashReset(){
        url = null;
        hashError.setText("");
        pathLabelHash.setText("");
        Hash.counter = 0;
    }

    private void EncryptionReset(){
        url = null;
        encryptionError.setText("");
        pathLabel.setText("");
    }

}