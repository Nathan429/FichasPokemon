module com.nwintle.fichaspokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.google.gson;

    opens com.nwintle.fichaspokemon to javafx.fxml;
    exports com.nwintle.fichaspokemon;
    exports com.nwintle.fichaspokemon.api;
    opens com.nwintle.fichaspokemon.api to javafx.fxml;
    opens com.nwintle.fichaspokemon.models to com.google.gson;
    exports com.nwintle.fichaspokemon.controllers;
    opens com.nwintle.fichaspokemon.controllers to javafx.fxml;
}