package client.scenes;

import client.utils.LanguageConf;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Event;
import commons.Expense;
import commons.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class AddTagCtrl {

    private MainCtrl mainCtrl;
    private ServerUtils server;
    private LanguageConf languageConf;

    @FXML
    private ComboBox<String> tags;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private ColorPicker cp;

    @FXML
    private TextField tagTextField;

    private Stage stage;
    private Event event;
    private Expense exp;
    private Color selectedColor;

    /**
     * @param mainCtrl main controller
     * @param server server utils
     * @param languageConf language config
     */
    @Inject
    public AddTagCtrl(MainCtrl mainCtrl, ServerUtils server,
                                    LanguageConf languageConf) {
        this.mainCtrl = mainCtrl;
        this.server = server;
        this.languageConf = languageConf;

    }

    /**
     * initiliaze method
     */
    public void initialize() {
        tags.getItems().clear();

    }

    /**
     * display the add tag page
     * @param event
     * @param stage
     */
    public void displayAddTagPage(Event event, Stage stage) {
        this.event = event;
        this.stage = stage;
        tags.getItems().clear();
        populateTypeBox(event);
        cp.setOnAction(e -> {
            selectedColor = cp.getValue();
        });
        add.setOnAction(e -> {
            addButton(event);
        });
        back.setOnAction(e -> {
            stage.close();
        });
    }


    /**
     * behaviour for add tag button
     * @param event
     */
    public void addButton(Event event) {
        String name = tagTextField.getText();
        if (!name.isEmpty()) {

            String clr = toHexString(selectedColor);
            Tag tag = new Tag(name, clr);
            int status = server.addTag(event.getId(), tag);
            if(status / 100 != 2) {
                System.out.println("server error: " + status);
            }
            //System.out.println(status);
//            List<Tag> temp = new ArrayList<>(event.getTags());
//            temp.add(tag);
//            event.setTags(temp);
            tagTextField.clear();
            populateTypeBox(event);
        }
    }




    /**
     * show the corresponding tags for expense
     *
     * @param ev the current event
     */
    public void populateTypeBox(Event ev) {
        setupTypeComboBox(ev);
    }

    private void setupTypeComboBox(Event ev) {
        tags.getItems().clear();
        for (Tag tag : ev.getTags()) {
            tags.getItems().add(tag.getName());
        }
        tags.setCellFactory(createTypeListCellFactory(ev));
        tags.setButtonCell(createTypeListCell(ev));
    }

    private Callback<ListView<String>, ListCell<String>> createTypeListCellFactory(Event ev) {
        return param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    Tag tag = findTagByName(item, ev.getTags());
                    if (tag != null) {
                        Label label = createLabelWithColor(item, hexToColor(tag.getColor()));
                        setGraphic(label);
                    }
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }

    private ListCell<String> createTypeListCell(Event ev) {
        return new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    Tag tag = findTagByName(item, ev.getTags());
                    if (tag != null) {
                        Label label = createLabelWithColor(item, hexToColor(tag.getColor()));
                        setGraphic(label);
                    }
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }

    private Label createLabelWithColor(String text, Color backgroundColor) {
        Label label = new Label(text);
        if (backgroundColor != null) {
            label.setStyle("-fx-background-color: #" + toHexString(backgroundColor)
                    + "; -fx-padding: 5px; -fx-text-fill: white;");
        }
        double textWidth = new Text(text).getLayoutBounds().getWidth();
        label.setMinWidth(textWidth + 10);
        return label;
    }

    private Tag findTagByName(String tagName, List<Tag> tags) {
        for (Tag tag : tags) {
            if (tag.getName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }


    /**
     * convert from color to string
     * @param color
     * @return the String color
     */
    private String toHexString(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * convert from string to color
     * @param hexCode
     * @return the Color color
     */
    public static Color hexToColor(String hexCode) {
        if (!hexCode.startsWith("#")) {
            hexCode = "#" + hexCode;
        }

        int red = Integer.parseInt(hexCode.substring(1, 3), 16);
        int green = Integer.parseInt(hexCode.substring(3, 5), 16);
        int blue = Integer.parseInt(hexCode.substring(5, 7), 16);

        return Color.rgb(red, green, blue);
    }


}
