# Splitty - an user-friendly app that tracks expenses and eases the process of managing them in a group of people

While the project can be run out-of-the-box with Gradle, running it from within your IDE (Eclipse/IntelliJ) requires setting up OpenJFX.

First download (and unzip) an OpenJFX SDK from https://openjfx.io that matches your Java JDK, then add the following *VM* commands to your run configurations:

    --module-path="/path/to/javafx-sdk/lib" --add-modules=javafx.controls,javafx.fxml

Tip: Make sure you adapt the path to the lib(!) directory (not just the directory that you unzipped).
Tip: Double check that the path is correct. You will receive abstract error messages otherwise.

# To run the project without an IDE or downloading the JavaFX:
- Open a terminal in the root directory of the project
- For the first time, type: `./gradlew build`
- To start the server, type: `./gradlew bootRun`
- To start the client, type: `./gradlew -a run`

The server will output the admin password in the terminal while starting, additionally executing the command `pass` in the server terminal will print the password again

# Features

- When the client enters an event, the websockets subscribe to that event and receive all the updates related to it so the event overview and its child pages automatically refresh the contents when any changes happen.
- Long-polling is used in admin to refresh the event list whenever there is a change in the database.
- All pages are navigable with only a keyboard, some additional shortcuts include:
    - ESC to go back
    - ENTER on some text fields to confirm (creating and joining an event, admin login, edit title)
    - CTRL + S to save settings while in options
    - On Mac, the keyboard shortcut functionality of pressing enter can be achieved by pressing space instead
- The choice box for currency can be filtered by clicking on it and typing.
- Some bigger errors will play a beep, like failing to connect to a server.
- To add a language, rename the template file to 'languages_##.properties' where ## is the locale code of the language. Drop this file in /client/build/resources/main/ then the flag in the location specified in the template flag property and add the locale code to locale attribute of config.properties.
- All the extensions have been implemented
- When cancelling with unsaved options in the options page, it will show a confirmation pop-up and if accepted will revert the changes.
- All the options are persisted in the config file.
- The currency numbers are formatted according to the system locale. For example, it will use a dot or a comma depending on the locale of the user.
- Currency text fields will only let type floating point numbers, the join text field will auto capitalize the code while writing it.
- Some fields for names and titles have a character limiter which will inform the user when the limit is reached.
- In the event overview the invite code in the top right can be clicked to be copied to the system's clipboard for easier invite code sharing.
- In the debts page, it's possible to add a custom transaction in case some people already paid their debts during the event or only want to settle a debt partially.
- The choice boxes can be opened with ENTER and the date selector with SHIFT
- High contrast mode can be toggled in the options page (the page is accessible from the start screen)
