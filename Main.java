package vehicle.inventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Pos;
import javafx.stage.Modality;

/**
 * Programmers: Jhed Factolerin && Nathen Gay <br>
 Program: Main.java <br>
 * Date: April 2016 <br>
 * version 1.0
 */

public class Main extends Application {

    /**TextField that displays Car Model**/
    private final TextField txtCarModel = new TextField();
    /**TextField that displays Car Maker**/
    private final TextField txtMaker = new TextField();
    /**TextField that displays Body Type**/
    private final TextField txtBodyType = new TextField();
    /**TextField that displays Number of Seats**/
    private final TextField txtNumSeats = new TextField();
    /**TextField that displays Year**/
    private final TextField txtYear = new TextField();
    /**TextField that displays Price**/
    private final TextField txtPrice = new TextField();
    /**TextField that displays Number of Doors**/
    private final TextField txtNumDoor = new TextField();
    /**TextField that takes Search Inputs**/
    private final TextField txtSearch = new TextField();
    /**Button displays the first record**/
    private final Button btnFirst = new Button("First");
    /**Button displays the next record**/
    private final Button btnNext = new Button("Next");
    /**Button displays the previous record**/
    private final Button btnPrevious = new Button("Previous");
    /**Button displays the last record**/
    private final Button btnLast = new Button("Last");
    /**Button that displays the addRecordStage**/
    private final Button btnAdd = new Button("Add");
    /**Button that makes the TextFields editable**/
    private final Button btnEdit = new Button("Edit");
    /**Button that removes record**/
    private final Button btnRemove = new Button("Remove");
    /**Button that searches record**/
    private final Button btnSearch = new Button("Search");
     /**Button that clears the Search TextField**/
    private final Button btnClear = new Button("Clear");
    /**Button that saves when the user is done editing**/
    private final Button btnSave = new Button("Save");
    /**Button that confirms that user's decision**/
    private final Button btnYes = new Button("Yes");
    /**Button that cancels if the user is not sure*/
    private final Button btnNo = new Button("No");
    /**Button that closes the primaryStage**/
    private final Button btnExit = new Button("Exit");
    /**Stage that confirms user's action**/
    private final Stage confirmationStage = new Stage();
    /**Stage that displays the addRecord Form**/
    private final Stage addRecordStage = new Stage();
    /**Stage that displays the exception message**/
    private final Stage stage = new Stage();
    /**ArrayList that stores all the record from the sequential file**/
    private ArrayList<String> record = new ArrayList<>();
    /**ArrayList that stores the model names of the cars**/
    private ArrayList<String> modelListView = new ArrayList<>();
    /**ArrayList that stores the search result**/
    private ArrayList<String> searchResult = new ArrayList<>();  
    /**ListView that displays all the CarModel that can navigate based on user's choice**/
    private ListView<String> listBox = new ListView<>();
    /**String stores the record to be added to the ArrayList then to the file**/
    private String sAdd = new String();
    /**Integer that keep tracks of the current index of the record to be displayed**/
    private int index = 0;
    /**Boolean that determines whether the current state of the record is editable or not **/
    private Boolean isTrue = false;
    /**ObservableList for the ComboBox**/
    private ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Model",
                    "Maker",
                    "BodyType",
                    "No. of Seats",
                    "Year",
                    "Price",
                    "No. of Doors"
            );
    /**ComboBox that displays the options for the user to choose for the search field**/
    private final ComboBox comboBox = new ComboBox(options);
    /**TableView to display the Search Result**/
    private TableView table;
    /**ObservableList that stores the search result**/
    private ObservableList data;
    
    /**
      * Displays the main stage
      **/
    @Override
    public void start(Stage primaryStage) {
        //Labels for the textfields
        Label lblTitle = new Label("Vehicle Inventory");
        Label lblModel = new Label("Car Model: ");
        Label lblMaker = new Label("Maker: ");
        Label lblBodyType = new Label("Body Type: ");
        Label lblnumSeats = new Label("No. of Seats: ");
        Label lblYear = new Label("Year: ");
        Label lblPrice = new Label("Price: ");
        Label lblNumDoor = new Label("No. of Door: ");
        Label lblSearch = new Label("Search: ");
        Label lblList = new Label("Pick a Model:");

        //Styling Labels
        setFont(lblModel);
        setFont(lblMaker);
        setFont(lblBodyType);
        setFont(lblnumSeats);
        setFont(lblYear);
        setFont(lblPrice);
        setFont(lblNumDoor);
        setFont(lblList);
        setFont(lblSearch);
        Font font = new Font("Arial Black", 30);
        lblTitle.setFont(font);

        //Positioning the Title
        lblTitle.setPadding(new Insets(10, 0, 20, -60));
        lblList.setPadding(new Insets(0, 0, -10, 2));
        
        //Setting ComboBox's initial value
        comboBox.setPromptText("Search By:");

        //HBoxes for TextFields and their Labels
        HBox modelHBox = new HBox();
        modelHBox.getChildren().addAll(lblModel, txtCarModel);
        modelHBox.setPadding(new Insets(10, 15, 10, 33));

        HBox makerHBox = new HBox();
        makerHBox.getChildren().addAll(lblMaker, txtMaker);
        makerHBox.setPadding(new Insets(10, 15, 10, 65));

        HBox bodyTypeHBox = new HBox();
        bodyTypeHBox.getChildren().addAll(lblBodyType, txtBodyType);
        bodyTypeHBox.setPadding(new Insets(10, 15, 10, 30));

        HBox numSeatsHBox = new HBox();
        numSeatsHBox.getChildren().addAll(lblnumSeats, txtNumSeats);
        numSeatsHBox.setPadding(new Insets(10, 15, 10, 15));

        HBox yearHBox = new HBox();
        yearHBox.getChildren().addAll(lblYear, txtYear);
        yearHBox.setPadding(new Insets(10, 15, 10, 76));

        HBox priceHBox = new HBox();
        priceHBox.getChildren().addAll(lblPrice, txtPrice);
        priceHBox.setPadding(new Insets(10, 15, 10, 73));

        HBox numDoorHBox = new HBox();
        numDoorHBox.getChildren().addAll(lblNumDoor, txtNumDoor);
        numDoorHBox.setPadding(new Insets(10, 15, 30, 25));

        HBox searchHBox = new HBox(5);
        searchHBox.getChildren().addAll(lblSearch, txtSearch, comboBox);
        searchHBox.setPadding(new Insets(10, 15, 0, -15));

        HBox searchButton = new HBox(10);
        searchButton.getChildren().addAll(btnSearch, btnClear);
        searchButton.setPadding(new Insets(0, 0, 15, 65));

        //VBox for the HBoxes of the TextFields and Labels
        VBox txtFieldsVBox = new VBox();
        txtFieldsVBox.getChildren().addAll(modelHBox, makerHBox, bodyTypeHBox,
                numSeatsHBox, yearHBox, priceHBox, numDoorHBox);

        //VBox for Add, Edit, Remove, and Search Buttons
        VBox vBox = new VBox(15);
        vBox.setPrefWidth(110);
        vBox.getChildren().addAll(btnAdd, btnRemove, btnEdit, btnSave, btnExit);
        vBox.setPadding(new Insets(10, 0, 0, 0));

        //VBox for Search Section
        VBox searchVBox = new VBox(10);
        searchVBox.getChildren().addAll(searchHBox, searchButton, lblList, listBox);
        searchVBox.setPadding(new Insets(0, 0, 20, 0));

        //Setting Size for Buttons
        btnAdd.setMinWidth(vBox.getPrefWidth());
        btnEdit.setMinWidth(vBox.getPrefWidth());
        btnRemove.setMinWidth(vBox.getPrefWidth());
        btnSearch.setMinWidth(vBox.getPrefWidth());
        btnSave.setMinWidth(vBox.getPrefWidth());
        btnExit.setMinWidth(vBox.getPrefWidth());
        btnClear.setMinWidth(vBox.getPrefWidth());

        //HBoxes for the Navigation Buttons
        HBox navigateHBox = new HBox(5);
        navigateHBox.setPrefWidth(70);
        navigateHBox.getChildren().addAll(btnFirst, btnPrevious, btnNext, btnLast);
        navigateHBox.setPadding(new Insets(-50, 0, 0, 80));

        //Setting Size for Buttons
        btnFirst.setMinWidth(navigateHBox.getPrefWidth());
        btnNext.setMinWidth(navigateHBox.getPrefWidth());
        btnPrevious.setMinWidth(navigateHBox.getPrefWidth());
        btnLast.setMinWidth(navigateHBox.getPrefWidth());
        btnSave.setDisable(true);

        //Resizing TextFields
        resizeTextField(txtCarModel);
        resizeTextField(txtMaker);
        resizeTextField(txtBodyType);
        resizeTextField(txtNumSeats);
        resizeTextField(txtYear);
        resizeTextField(txtPrice);
        resizeTextField(txtNumDoor);
        
        //Making error window the only active window
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        addRecordStage.initOwner(primaryStage);
        addRecordStage.initModality(Modality.WINDOW_MODAL);
        confirmationStage.initOwner(primaryStage);
        confirmationStage.initModality(Modality.WINDOW_MODAL);

        //Implementing the methods
        setEditable(false);
        readData(record);
        display();

        //ListView
        getCarModelList();
        ObservableList<String> items = FXCollections.observableArrayList(modelListView);
        listBox.setItems(items);

        //Resizing ListView
        listBox.setMaxHeight(280);
        listBox.setMaxWidth(320);

        //EvenHandling for Button Next
        btnNext.setOnAction((ActionEvent e) -> {
            if (index < record.size() - 1) {
                index++;
                display();
            }
        });
        //EventHandling for Button Previous
        btnPrevious.setOnAction((ActionEvent e) -> {
            if (index > 0) {
                index--;
                display();
            }
        });
        //EventHandling for Button First
        btnFirst.setOnAction((ActionEvent e) -> {
            index = 0;
            display();
        });
        //EventHandling for Button Last
        btnLast.setOnAction((ActionEvent e) -> {
            index = (record.size() - 1);
            display();
        });

        //EventHandling for Button Add
        btnAdd.setOnAction((ActionEvent e) -> {
            addRecord();
        });
        //EventHandling for Button Remove
        btnRemove.setOnAction((ActionEvent e) -> {
            confirmation("Are you sure you want to delete this data?");
            btnYes.setOnAction((ActionEvent a) -> {
                confirmationStage.close();
                FileHandler.removeData(record, index);
                clearTextFields();
                if (index != 0) {
                    index--;
                }
                refreshListView();
                display();
            });

            btnNo.setOnAction((ActionEvent a) -> {
                confirmationStage.close();
            });

        });

        //EventHandling for Button Edit
        btnEdit.setOnAction((ActionEvent a) -> {
            btnSave.setDisable(false);
            //if the current state of the buttons are not editable
            if (isTrue == false) {
                setEditable(true);
                //removes the dollar sign
                txtPrice.setText(Format.removeDollar(txtPrice.getText()));
                //btn to save the edited record 
                btnSave.setOnAction((ActionEvent e) -> {
                    checkValues();
                    btnYes.setOnAction((ActionEvent c) -> {
                        btnSave.setDisable(true);
                        editData();
                        FileHandler.writeToFile(record);
                        setEditable(false);
                        confirmationStage.close();
                        display();
                        refreshListView();
                    });
                    btnNo.setOnAction((ActionEvent c) -> {
                        confirmationStage.close();
                        display();
                        txtPrice.setText(Format.removeDollar(txtPrice.getText()));
                    });
                });

                //if the current state of the buttons are editable
            } else {
                btnSave.setDisable(true);
                setEditable(false);
                display();
            }
        });

        //Exit button EventHandler
        btnExit.setOnAction((ActionEvent e) -> {
            confirmation("Are you sure you want to quit?");
            confirmation("Are you sure you want to quit?");
            btnYes.setOnAction((ActionEvent a) -> {
                confirmationStage.close();
                primaryStage.close();
            });
            btnNo.setOnAction((ActionEvent a) -> {
                confirmationStage.close();
            });
        });
        //EventHandler for Search Button
        btnSearch.setOnAction((ActionEvent e) -> {
            searchResult.clear();
            searchData();

        });
        
        btnClear.setOnAction((ActionEvent e) -> {
            txtSearch.clear();
        });

        //EventHandler for the list of CarModel
        listBox.setOnMouseClicked((Event args0) -> {
            String selected = listBox.getSelectionModel().getSelectedItem();
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer model = new StringTokenizer(record.get(sub), ",");
                if (selected.equalsIgnoreCase(model.nextToken())) {
                    index = sub;
                    display();
                    break;
                }
            }
        });

        //GridPane to positon Nodes
        GridPane pane = new GridPane();
        pane.add(lblTitle, 1, 0);
        pane.add(searchVBox, 2, 1);
        pane.add(txtFieldsVBox, 0, 1);
        pane.add(vBox, 1, 1);
        pane.add(navigateHBox, 0, 2);

        Scene scene = new Scene(pane, 890, 500);
        primaryStage.setTitle("Vehicle Inventory!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method that implements the readFile method from the FileHandler class
     * @param record ArrayList where the record is stored
     */
    private void readData(ArrayList<String> record) {
        try {
            FileHandler.readFile(record);
        } catch (FileNotFoundException e) {
            showStage(e.getMessage());
        } catch (IOException e) {
            showStage(e.getMessage());
        }
    }

    /**
     * Method that gets the model of the car
     */
    private void getCarModelList() {
        modelListView.clear();
        for (int sub = 0; sub < record.size(); sub++) {
            StringTokenizer modelToken = new StringTokenizer(record.get(sub), ",");
            modelListView.add(modelToken.nextToken());
        }
    }
    
    /**
     * Refreshes the ListView if the user added or removed a record
     */
    private void refreshListView() {
        getCarModelList();
        String empty = new String();
        ObservableList<String> items = FXCollections.observableArrayList(modelListView);
        ObservableList<String> emp = FXCollections.observableArrayList(empty);
        listBox.setItems(emp);
        listBox.setItems(items);
    }

    /**
     * Displays a window where a form is displayed for the user to fill up with record to be added
     */
    private void addRecord() {
        TextField addCarModel = new TextField();
        TextField addMaker = new TextField();
        TextField addBodyType = new TextField();
        TextField addNumSeats = new TextField();
        TextField addYear = new TextField();
        TextField addPrice = new TextField();
        TextField addNumDoor = new TextField();

        Label title = new Label("Add a Record");
        Label model = new Label("Car Model: ");
        Label maker = new Label("Maker: ");
        Label bodyType = new Label("Body Type: ");
        Label numSeats = new Label("No. of Seats: ");
        Label year = new Label("Year: ");
        Label price = new Label("Price: ");
        Label numDoor = new Label("No. of Door: ");

        //Buttons
        Button add = new Button("Add");
        Button exit = new Button("Exit");

        //Styling Labels
        setFont(model);
        setFont(maker);
        setFont(bodyType);
        setFont(numSeats);
        setFont(year);
        setFont(price);
        setFont(numDoor);
        setFont(title);

        //Resizing TextFields
        resizeTextField(addCarModel);
        resizeTextField(addMaker);
        resizeTextField(addBodyType);
        resizeTextField(addNumSeats);
        resizeTextField(addYear);
        resizeTextField(addPrice);
        resizeTextField(addNumDoor);

        title.setPadding(new Insets(10, 0, 10, 140));

        //HBoxes for TextFields and their Labels
        HBox modelHBox = new HBox();
        modelHBox.getChildren().addAll(model, addCarModel);
        modelHBox.setPadding(new Insets(10, 15, 10, 33));

        HBox makerHBox = new HBox();
        makerHBox.getChildren().addAll(maker, addMaker);
        makerHBox.setPadding(new Insets(10, 15, 10, 65));

        HBox bodyTypeHBox = new HBox();
        bodyTypeHBox.getChildren().addAll(bodyType, addBodyType);
        bodyTypeHBox.setPadding(new Insets(10, 15, 10, 30));

        HBox numSeatsHBox = new HBox();
        numSeatsHBox.getChildren().addAll(numSeats, addNumSeats);
        numSeatsHBox.setPadding(new Insets(10, 15, 10, 15));

        HBox yearHBox = new HBox();
        yearHBox.getChildren().addAll(year, addYear);
        yearHBox.setPadding(new Insets(10, 15, 10, 76));

        HBox priceHBox = new HBox();
        priceHBox.getChildren().addAll(price, addPrice);
        priceHBox.setPadding(new Insets(10, 15, 10, 73));

        HBox numDoorHBox = new HBox();
        numDoorHBox.getChildren().addAll(numDoor, addNumDoor);
        numDoorHBox.setPadding(new Insets(10, 15, 30, 25));

        //VBoxes for the HBoxes of the TextFields and Labels
        VBox txtFieldsVBox = new VBox();
        txtFieldsVBox.getChildren().addAll(modelHBox, makerHBox, bodyTypeHBox,
                numSeatsHBox, yearHBox, priceHBox, numDoorHBox);

        HBox navigateHBox = new HBox(5);
        navigateHBox.getChildren().addAll(add, exit);
        navigateHBox.setPadding(new Insets(0, 0, 0, 100));

        //Resizing Add Button and Exit Button
        add.setMaxWidth(90);
        exit.setMaxWidth(90);
        add.setMinWidth(90);
        exit.setMinWidth(90);

        GridPane pane = new GridPane();
        pane.add(title, 0, 0);
        pane.add(txtFieldsVBox, 0, 1);
        pane.add(navigateHBox, 0, 2);

        exit.setOnAction((ActionEvent e) -> {
            addRecordStage.close();
        });

        add.setOnAction((ActionEvent e) -> {
            try {
                CheckInput.checkCarModel(addCarModel.getText());
                CheckInput.checkMaker(addMaker.getText());
                CheckInput.checkBodyType(addBodyType.getText());
                CheckInput.checkNumberSeats(addNumSeats.getText());
                CheckInput.checkYear(addYear.getText());
                CheckInput.checkPrice(addPrice.getText());
                CheckInput.checkNumberDoors(addNumDoor.getText());
                confirmation("Are you sure you want to add this data?");
            } catch (Exception z) {
                showStage(z.getMessage());
            }
            btnYes.setOnAction((ActionEvent a) -> {
                sAdd = Format.capitalizeFirstLetter(addCarModel.getText()) + ","
                        + Format.capitalizeFirstLetter(addMaker.getText()) + ","
                        + Format.capitalizeFirstLetter(addBodyType.getText()) + ","
                        + addNumSeats.getText() + "," + addYear.getText() + ","
                        + Format.currencyFormat(addPrice.getText()) + "," + addNumDoor.getText();
                record.add(sAdd);
                FileHandler.writeToFile(record);
                confirmationStage.close();
                addRecordStage.close();
                index = (record.size() - 1);
                display();
                refreshListView();

            });

            btnNo.setOnAction((ActionEvent a) -> {
                confirmationStage.close();
            });

        });

        Scene scene = new Scene(pane, 400, 500);
        addRecordStage.setTitle("Add a Record");
        addRecordStage.setScene(scene);
        addRecordStage.show();

    }

    /**
     * Method that allows user to modify a record
     */
    private void editData() {
        sAdd = Format.capitalizeFirstLetter(txtCarModel.getText()) + ","
                + Format.capitalizeFirstLetter(txtMaker.getText()) + ","
                + Format.capitalizeFirstLetter(txtBodyType.getText()) + ","
                + txtNumSeats.getText() + "," + txtYear.getText() + ","
                + Format.removeDollar(txtPrice.getText()) + "," + txtNumDoor.getText();
        record.add(index, sAdd);
        record.remove(index + 1);
    }

    /**
     * Method that searches for the record that the user wanted to search for
     */
    private void searchData() {
        if (comboBox.getSelectionModel().isEmpty()) {
            showStage("Please Select a Particular Field to Search In");
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("Model")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer model = new StringTokenizer(record.get(sub), ",");
                if (txtSearch.getText().equalsIgnoreCase(model.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("Maker")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer maker = new StringTokenizer(record.get(sub), ",");
                maker.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(maker.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("BodyType")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer bodyType = new StringTokenizer(record.get(sub), ",");
                bodyType.nextToken();
                bodyType.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(bodyType.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("No. of Seats")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer seats = new StringTokenizer(record.get(sub), ",");
                seats.nextToken();
                seats.nextToken();
                seats.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(seats.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("Year")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer year = new StringTokenizer(record.get(sub), ",");
                year.nextToken();
                year.nextToken();
                year.nextToken();
                year.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(year.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("Price")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer price = new StringTokenizer(record.get(sub), ",");
                price.nextToken();
                price.nextToken();
                price.nextToken();
                price.nextToken();
                price.nextToken();
                String p = price.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(p)
                        || Format.removeDollar(txtSearch.getText()).equalsIgnoreCase(p)) {
                    searchResult.add(record.get(sub));
                }
            }
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("No. of Doors")) {
            for (int sub = 0; sub < record.size(); sub++) {
                StringTokenizer doors = new StringTokenizer(record.get(sub), ",");
                doors.nextToken();
                doors.nextToken();
                doors.nextToken();
                doors.nextToken();
                doors.nextToken();
                doors.nextToken();
                if (txtSearch.getText().equalsIgnoreCase(doors.nextToken())) {
                    searchResult.add(record.get(sub));
                }
            }
        }
        if (txtSearch.getText().isEmpty()){
            showStage("Please enter something");
        }else if (searchResult.isEmpty() && !(comboBox.getSelectionModel().isEmpty())) {
            showStage("No record found");
        } else if (!searchResult.isEmpty()) {
            displaySearchResult();
        }
    }
    
    /**
     * Window that displays the table that contains the Search Result
     */
    private void displaySearchResult() {
        Stage stage = new Stage();
        Label result = new Label("Search Result");
        Button btnClose = new Button("Close");

        setFont(result);
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(result);

        table = new TableView<>();
        data = getInitialTableData();
        table.setItems(data);

        btnClose.setPrefWidth(100);

        TableColumn model = new TableColumn("Model");
        model.setCellValueFactory(new PropertyValueFactory("model"));
        TableColumn maker = new TableColumn("Maker");
        maker.setCellValueFactory(new PropertyValueFactory("maker"));
        TableColumn bodyType = new TableColumn("BodyType");
        bodyType.setCellValueFactory(new PropertyValueFactory("bodyType"));
        TableColumn numSeat = new TableColumn("Seats");
        numSeat.setCellValueFactory(new PropertyValueFactory("seats"));
        TableColumn year = new TableColumn("Year");
        year.setCellValueFactory(new PropertyValueFactory("year"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory("price"));
        TableColumn numDoor = new TableColumn("Doors");
        numDoor.setCellValueFactory(new PropertyValueFactory("doors"));

        table.getColumns().setAll(model, maker, bodyType, numSeat, year, price, numDoor);
        table.setPrefWidth(750);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        btnClose.setOnAction((ActionEvent e) -> {
            stage.close();
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 25, 20, 25));
        vbox.getChildren().addAll(hb, table, btnClose);

        Scene scene = new Scene(vbox, 800, 300);

        stage.setTitle("Search Result");
        stage.setScene(scene);
        stage.show();

    }
    
    /**
     * Stores the Search Result to be displayed in the TableView
     */
    private ObservableList getInitialTableData() {
        ArrayList<String> list = new ArrayList<>();
        ObservableList data = FXCollections.observableArrayList();

        for (int sub = 0; sub < searchResult.size(); sub++) {
            StringTokenizer carToken = new StringTokenizer(searchResult.get(sub), ",");
            String model = carToken.nextToken();
            String maker = carToken.nextToken();
            String bodyType = carToken.nextToken();
            String seats = carToken.nextToken();
            String year = carToken.nextToken();
            String price = carToken.nextToken();
            String doors = carToken.nextToken();

            data.add(new Record(model, maker, bodyType, seats, year, Format.addDollar(price), doors));
        }
        return data;

    }
    
    /**
     * Displays the error window if invalid inputs are entered
     * @param message The error message/String
     */
    private void showStage(String message) {
        Button okay = new Button("OK");
        okay.setPadding(new Insets(5, 25, 5, 25));

        //HBox for button okay
        HBox confirm = new HBox();
        confirm.getChildren().addAll(okay);
        confirm.setAlignment(Pos.CENTER);
        confirm.setPadding(new Insets(-15, 0, 10, 0));

        //Text object and setting font style
        Text error = new Text(message);
        Font font = new Font("Verdana", 17);
        Font okayFont = new Font("Verdana", 15);
        error.setFont(font);
        okay.setFont(okayFont);

        //BorderPane for message and button
        BorderPane pane = new BorderPane();
        pane.setCenter(error);
        pane.setBottom(confirm);

        okay.setOnAction((ActionEvent e) -> {
            stage.close();
        });

        Scene scene = new Scene(pane, 400, 120);
        stage.setTitle("Error");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that checks and validates user inputs
     */
    private void checkValues() {
        try {
            CheckInput.checkCarModel(txtCarModel.getText());
            CheckInput.checkMaker(txtMaker.getText());
            CheckInput.checkBodyType(txtBodyType.getText());
            CheckInput.checkNumberSeats(txtNumSeats.getText());
            CheckInput.checkYear(txtYear.getText());
            CheckInput.checkPrice(txtPrice.getText());
            CheckInput.checkNumberDoors(txtNumDoor.getText());
            confirmation("Want to save this changes?");
        } catch (Exception z) {
            showStage(z.getMessage());
        }
    }

    /**
     * Window that asks user for confirmation of their action
     * @param message Question to be asked to the user
     */
    private void confirmation(String message) {

        Text isContinue = new Text(message);
        btnYes.setPadding(new Insets(5, 25, 5, 25));
        btnNo.setPadding(new Insets(5, 25, 5, 25));

        //GridPane for Buttons in continue window
        GridPane confirm = new GridPane();
        confirm.add(btnYes, 1, 0);
        confirm.add(btnNo, 2, 0);
        confirm.setPadding(new Insets(0, 10, 15, 125));
        confirm.setHgap(30);

        //Setting font style
        Font font = new Font("Verdana", 18);
        Font okayFont = new Font("Verdana", 15);
        isContinue.setFont(font);
        btnYes.setFont(okayFont);
        btnNo.setFont(okayFont);

        //BorderPane for isContinue text and buttons
        BorderPane pane = new BorderPane();
        pane.setCenter(isContinue);
        pane.setBottom(confirm);

        Scene scene = new Scene(pane, 500, 130);
        confirmationStage.setTitle(message);
        confirmationStage.centerOnScreen();
        confirmationStage.setScene(scene);
        confirmationStage.show();
    }
    
    /**
     * Method the sets the text to display for each TextFields
     */
    private void display() {
        StringTokenizer carToken = new StringTokenizer(record.get(index), ",");
        txtCarModel.setText(carToken.nextToken());
        txtMaker.setText(carToken.nextToken());
        txtBodyType.setText(carToken.nextToken());
        txtNumSeats.setText(carToken.nextToken());
        txtYear.setText(carToken.nextToken());
        txtPrice.setText(Format.addDollar(carToken.nextToken()));
        txtNumDoor.setText(carToken.nextToken());
    }

    /**
     * Method that sets TextField to be editable or not
     * @param a makes the TextField editable if false; otherwise not editable
     */
    private void setEditable(Boolean a) {
        txtCarModel.setEditable(a);
        txtMaker.setEditable(a);
        txtBodyType.setEditable(a);
        txtNumSeats.setEditable(a);
        txtYear.setEditable(a);
        txtPrice.setEditable(a);
        txtNumDoor.setEditable(a);

        if (a == true) {
            isTrue = true;
        } else {
            isTrue = false;
        }
    }

    /**
     * Method that clears TextFields
     */
    private void clearTextFields() {
        txtCarModel.clear();
        txtMaker.clear();
        txtBodyType.clear();
        txtYear.clear();
        txtNumSeats.clear();
        txtPrice.clear();
        txtNumDoor.clear();

    }

    /**
     * Method that resizes TextFields
     * @param textField TextField to be resized
     */
    private void resizeTextField(TextField textField) {
        textField.setMinWidth(50);
        textField.setPrefWidth(200);
        textField.setMaxWidth(400);
        textField.setPrefHeight(30);
    }

    /**
     * Method that sets the font style of labels
     * @param l Label to be styled
     */
    private void setFont(Label l) {
        Font font = new Font("Arial Black", 15);
        l.setFont(font);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
