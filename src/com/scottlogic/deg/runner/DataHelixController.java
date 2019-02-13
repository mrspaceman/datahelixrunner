package com.scottlogic.deg.runner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DataHelixController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtProfileJson"
    private TextArea txtProfileJson; // Value injected by FXMLLoader

    @FXML // fx:id="slideNbrRowsOutput"
    private Slider slideNbrRowsOutput; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutputLog"
    private TextFlow txtOutputLog; // Value injected by FXMLLoader

    @FXML // fx:id="choiceGenerateType"
    private ChoiceBox<?> choiceGenerateType; // Value injected by FXMLLoader

    @FXML // fx:id="btnGenerate"
    private Button btnGenerate; // Value injected by FXMLLoader

    @FXML // fx:id="chkOverwrite"
    private CheckBox chkOverwrite; // Value injected by FXMLLoader

    @FXML // fx:id="txtProfileFilename"
    private TextArea txtProfileFilename; // Value injected by FXMLLoader

    @FXML // fx:id="btnVerify"
    private Button btnVerify; // Value injected by FXMLLoader

    @FXML // fx:id="chkViolate"
    private CheckBox chkViolate; // Value injected by FXMLLoader

    @FXML // fx:id="choiceWalker"
    private ChoiceBox<?> choiceWalker; // Value injected by FXMLLoader

    @FXML // fx:id="lblNbrRowsOutput"
    private Label lblNbrRowsOutput; // Value injected by FXMLLoader

    @FXML // fx:id="txtGeneratorJarFilename"
    private TextField txtGeneratorJarFilename; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutputFilename"
    private TextField txtOutputFilename; // Value injected by FXMLLoader

    @FXML
    void onBtnGenerate(ActionEvent event) {
        executeGenerateCommand();
    }

    @FXML
    void onBtnVerify(ActionEvent event) {

    }

    @FXML
    void onBtnSelectGeneratorJar(ActionEvent event) {
        chooseGeneratorJar();
    }

    @FXML
    void onBtnSelectProfile(ActionEvent event) {
        chooseProfileFile();
    }

    @FXML
    void onBtnSelectOutput(ActionEvent event) {
        chooseOutputTarget();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtProfileJson != null : "fx:id=\"txtProfileJson\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert slideNbrRowsOutput != null : "fx:id=\"slideNbrRowsOutput\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert txtOutputLog != null : "fx:id=\"txtOutputLog\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert choiceGenerateType != null : "fx:id=\"choiceGenerateType\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert chkOverwrite != null : "fx:id=\"chkOverwrite\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert txtProfileFilename != null : "fx:id=\"txtProfileFilename\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert btnVerify != null : "fx:id=\"btnVerify\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert chkViolate != null : "fx:id=\"chkViolate\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert choiceWalker != null : "fx:id=\"choiceWalker\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert lblNbrRowsOutput != null : "fx:id=\"lblNbrRowsOutput\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert txtGeneratorJarFilename != null : "fx:id=\"txtGeneratorJarFilename\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";
        assert txtOutputFilename != null : "fx:id=\"txtOutputFilename\" was not injected: check your FXML file 'DataHelixRunner.fxml'.";

        //Retrieving the observable list of the TextFlow Pane
        listLogMessages = txtOutputLog.getChildren();

        txtGeneratorJarFilename.setText("C:\\Users\\aaspellc\\src\\datahelix-0.0.0\\generator.jar");
        txtProfileFilename.setText("C:\\Users\\aaspellc\\src\\datahelix\\examples\\actor-names\\profile.json");
        txtOutputFilename.setText("C:\\Users\\aaspellc\\src\\datahelix\\examples\\actor-names\\profile.json.csv");
    }

    private ObservableList listLogMessages;
    private Stage stage;

    void setStageAndSetupListeners(Stage primaryStage) {
        this.stage = primaryStage;
    }


    private void chooseGeneratorJar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            txtGeneratorJarFilename.setText(file.getAbsolutePath());
        }
    }


    private void chooseProfileFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            txtProfileFilename.setText(file.getAbsolutePath());
            if (txtOutputFilename.getText().trim().isEmpty()) {
                txtOutputFilename.setText(file.getAbsolutePath() + ".csv");
            }
            readProfileIntoTextArea();
        }
    }

    public void readProfileIntoTextArea() {
        txtProfileJson.clear();
        File f = new File(txtProfileFilename.getText());
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while ((line = br.readLine()) != null) {
                txtProfileJson.appendText(line + "\n");
            }
            br.close();
        } catch (IOException ex) {
            listLogMessages.add(getTextNodeError("Error reading profile file [" + ex.getMessage() + "]"));
        }
    }


    private void chooseOutputTarget() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            txtOutputFilename.setText(file.getAbsolutePath());
        }
    }

    /**
     * execute the generator command
     * <p>
     * <code>java -jar ..\release-0.0.0\generator.jar generate bug-526-profile-2.json bug-526-profile-2.csv -w REDUCTIVE -t RANDOM</code>
     * </p>
     */
    void executeGenerateCommand() {
        try {
            Runtime rt = Runtime.getRuntime();
            StringBuilder cmdString = new StringBuilder("java -jar ");
            cmdString.append(txtGeneratorJarFilename.getText());
            cmdString.append(" generate ");
            cmdString.append(txtProfileFilename.getText());
            cmdString.append(" ");
            cmdString.append(txtOutputFilename.getText());
            cmdString.append(" -w ");
            cmdString.append((String) choiceWalker.getValue());
            cmdString.append(" -t ");
            cmdString.append((String) choiceGenerateType.getValue());

            if (chkOverwrite.isSelected()) {
                cmdString.append(" --overwrite ");
            }

            System.out.println(cmdString);
            Process pr = rt.exec(cmdString.toString());

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(pr.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                listLogMessages.add(getTextNode(s));
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                listLogMessages.add(getTextNodeError(s));
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    private Text getTextNodeError(String s) {
        Text t = getTextNode(s);
        t.setFill(Color.DARKRED);
        return t;
    }

    private Text getTextNode(String s) {
        //Creating text objects
        Text t = new Text(s);

        //Setting font to the text
        t.setFont(new Font(15));

        //Setting color to the text
        t.setFill(Color.DARKSLATEBLUE);

        return t;
    }
}

