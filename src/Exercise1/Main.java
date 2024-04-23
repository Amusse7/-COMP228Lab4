package Exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Student Info"); // Title

        BorderPane root = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        // Grid pane
        TextField nameField = new TextField();
        nameField.setPrefWidth(300);
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(new Label("  "), 1, 0);
        gridPane.add(nameField, 2, 0);

        // Labels and text
        TextField addressField = new TextField();
        gridPane.add(new Label("Address:"), 0, 1);
        gridPane.add(new Label("  "), 1, 1);
        gridPane.add(addressField, 2, 1);

        TextField provinceField = new TextField();
        gridPane.add(new Label("Province:"), 0, 2);
        gridPane.add(new Label("  "), 1, 2);
        gridPane.add(provinceField, 2, 2);

        TextField cityField = new TextField();
        gridPane.add(new Label("City:"), 0, 3);
        gridPane.add(new Label("  "), 1, 3);
        gridPane.add(cityField, 2, 3);

        TextField postalCodeField = new TextField();
        gridPane.add(new Label("Postal Code:"), 0, 4);
        gridPane.add(new Label("  "), 1, 4);
        gridPane.add(postalCodeField, 2, 4);

        TextField phoneNumberField = new TextField();
        gridPane.add(new Label("Phone Number:"), 0, 5);
        gridPane.add(new Label("  "), 1, 5);
        gridPane.add(phoneNumberField, 2, 5);

        TextField emailField = new TextField();
        gridPane.add(new Label("Email:"), 0, 6);
        gridPane.add(new Label("  "), 1, 6);
        gridPane.add(emailField, 2, 6);

        CheckBox studentCouncil = new CheckBox("Student Council");
        gridPane.add(studentCouncil, 3, 1);

        CheckBox volunteerWork = new CheckBox("Volunteer Work");
        gridPane.add(volunteerWork, 3, 5);

        RadioButton csRButton = new RadioButton("Computer Science");
        gridPane.add(csRButton, 5, 0);
        RadioButton businessRButton = new RadioButton("Business");
        gridPane.add(businessRButton, 6, 0);

        ToggleGroup majorGroup = new ToggleGroup();
        csRButton.setToggleGroup(majorGroup);
        businessRButton.setToggleGroup(majorGroup);

        ComboBox<String> coursesBox = new ComboBox<>();
        ListView<String> selectedCoursesList = new ListView<>();

        coursesBox.prefWidthProperty().bind(selectedCoursesList.widthProperty());
        coursesBox.setPrefHeight(100);
        selectedCoursesList.setPrefHeight(200);

        HBox radioButtonBox = new HBox(csRButton, businessRButton);
        radioButtonBox.setSpacing(15);
        radioButtonBox.setPadding(new Insets(25, 0, 0, 0));

        VBox comboBoxListViewBox = new VBox(coursesBox, selectedCoursesList);
        comboBoxListViewBox.setSpacing(5);

        VBox majorBox = new VBox(radioButtonBox, comboBoxListViewBox);
        majorBox.setSpacing(30);

        root.setRight(majorBox);

        Button displayButton = new Button("Show Results");
        displayButton.setPrefWidth(100);

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(200);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(displayButton, textArea);

        root.setBottom(vbox);

        root.setCenter(gridPane);

        csRButton.setOnAction(e -> {
            coursesBox.getItems().clear();
            coursesBox.getItems().addAll("COMP-100  Programming  I","MATH-185  Discrete Mathematics","COMP-301  Unix/Linux Operating Systems", "COMP-254  Data Structures and Algorithms", "MATH-210  Linear Algebra and Statistics" );
            selectedCoursesList.getItems().clear();
        });

        businessRButton.setOnAction(e -> {
            coursesBox.getItems().clear();
            coursesBox.getItems().addAll("BUSN-119  Fundamental of Business", "BUSN-331  Business Law", "INTL-220  International Business Concepts");
            selectedCoursesList.getItems().clear();
        });

        coursesBox.setOnAction(e -> {
            String selectedCourse = coursesBox.getSelectionModel().getSelectedItem();
            if (selectedCourse != null && !selectedCoursesList.getItems().contains(selectedCourse)) {
                selectedCoursesList.getItems().add(selectedCourse);
            }
        });

        displayButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    provinceField.getText().isEmpty() || cityField.getText().isEmpty() ||
                    postalCodeField.getText().isEmpty() || phoneNumberField.getText().isEmpty() ||
                    emailField.getText().isEmpty()) {
                textArea.setText("Fill In The Fields");
            }
            else if (selectedCoursesList.getItems().isEmpty()) {
                textArea.setText("Select A Course");
            }
            else if (!phoneNumberField.getText().matches("[0-9()\\- ]*"))
            {
                textArea.setText("Please Enter a Real Number");
            }
            else if (!emailField.getText().contains("@") || !emailField.getText().contains(".") || emailField.getText().endsWith(".")) {
                textArea.setText("Email Address Needs To Be Real.");
            }
            else {
                StringBuilder studentInfo = new StringBuilder("Student Name: " + nameField.getText() + ",  "
                        + "Address: " + addressField.getText() + ",  "
                        + "Province: " + provinceField.getText() + ",  "
                        + "City: " + cityField.getText() + ",  "
                        + "Postal Code: " + postalCodeField.getText() + ",  "
                        + "Phone Number: " + phoneNumberField.getText() + ",  "
                        + "Email: " + emailField.getText() + "\n"
                        + "Student Major: " + ((RadioButton) majorGroup.getSelectedToggle()).getText() + "\n"
                        + "Student Council: " + (studentCouncil.isSelected() ? "Yes" : "No") + "\n"
                        + "Volunteer Work: " + (volunteerWork.isSelected() ? "Yes" : "No"));

                studentInfo.append("\nStudent Courses:\n");
                for (String course : selectedCoursesList.getItems()) {
                    studentInfo.append(course).append("\n");
                }
                textArea.setText(studentInfo.toString());
            }
        });

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
