package client;

import client.MockClass.MainCtrlInterface;
import client.scenes.PairCollector;
import commons.Event;
import commons.Expense;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestMainCtrl implements MainCtrlInterface {

    private String currentScene;
    private final List<String> scenes = new ArrayList<>();

    /**
     * @return current scene
     */
    public String getCurrentScene() {
        return currentScene;
    }

    /**
     * @return scene history
     */
    public List<String> getScenes() {
        return scenes;
    }
    /**
     * Initializes the UI
     *
     * @param primaryStage  stage
     * @param pairCollector collector for all of pairs
     */
    @Override
    public void initialize(Stage primaryStage, PairCollector pairCollector) {

    }

    /**
     * Display start screen
     */
    @Override
    public void showStartScreen() {
        currentScene = "StartScreen";
        scenes.add("StartScreen");
    }

    /**
     * Shows the change
     *
     * @param event current event
     */
    @Override
    public void showEditTitle(Event event) {
        scenes.add("EditTitle");
    }

    /**
     * Display admin login
     */
    @Override
    public void showAdminLogin() {
        currentScene = "AdminLogin";
        scenes.add("AdminLogin");
    }

    /**
     * shows the event page
     *
     * @param eventToShow the event to display
     */
    @Override
    public void showEventPage(Event eventToShow) {
        currentScene = "EventPage";
        scenes.add("EventPage");
    }

    /**
     * this method is used to switch back to the event
     * page from the participant/expense editors
     *
     * @param event the event to show
     */
    @Override
    public void goBackToEventPage(Event event) {
        currentScene = "EventPage";
        scenes.add("EventPage");
    }

    /**
     * shows the participant editor page
     *
     * @param eventToShow the event to show the participant editor for
     */
    @Override
    public void showEditParticipantsPage(Event eventToShow) {
        currentScene = "EditParticipantsPage";
        scenes.add("EditParticipantsPage");
    }

    /**
     * shows the admin overview
     *
     * @param password admin password
     * @param timeOut  time out time in ms
     */
    @Override
    public void showAdminOverview(String password, long timeOut) {
        currentScene = "AdminOverview";
        scenes.add("AdminOverview");
    }

    /**
     * Show error popup for general usage
     *
     * @param code        Error code of the error as found in ErrorCode enum in ErrorPopupCtrl
     *                    Check ErrorPopupCtrl for more detailed documentation
     * @param stringToken String token to be used as a variable in the error text
     * @param intToken    int token to be used as a variable in the error text
     */
    @Override
    public void showErrorPopup(String code, String stringToken, int intToken) {

    }

    /**
     * Opens the system file chooser to save something
     *
     * @param fileChooser file chooser
     * @return opened file
     */
    @Override
    public File showSaveFileDialog(FileChooser fileChooser) {
        return null;
    }

    /**
     * Opens the system file chooser to open multiple files
     *
     * @param fileChooser file chooser
     * @return selected files
     */
    @Override
    public List<File> showOpenMultipleFileDialog(FileChooser fileChooser) {
        return List.of();
    }

    /**
     * shows the add/edit expense page
     *
     * @param eventToShow the event to show the participant editor for
     */
    @Override
    public void showAddExpensePage(Event eventToShow) {
        currentScene = "AddExpensePage";
        scenes.add("AddExpensePage");
    }

    /**
     * show the add tag page
     *
     * @param event
     */
    @Override
    public void showAddTagPage(Event event) {
        scenes.add("AddTagPage");
    }

    /**
     * Handle editing an expense.
     *
     * @param exp The expense to edit.
     * @param ev  The event associated with the expense.
     */
    @Override
    public void handleEditExpense(Expense exp, Event ev) {
        currentScene = "AddExpensePage";
        scenes.add("AddExpensePage");
    }
}
