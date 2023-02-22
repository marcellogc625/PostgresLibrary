module postgreslibrary {
    opens postgreslibrary.gui.controllers to javafx.fxml;
    opens postgreslibrary.gui.listeners to javafx.fxml;
    opens postgreslibrary.gui.utils to javafx.fxml;

    opens postgreslibrary.model to org.hibernate.orm.core;
    opens postgreslibrary.model.controllers to org.hibernate.orm.core;
    opens postgreslibrary.model.entities to org.hibernate.orm.core;

    exports postgreslibrary.model;
    exports postgreslibrary.gui.controllers;
    exports postgreslibrary.model.controllers;
    exports postgreslibrary.model.entities;
    exports postgreslibrary.application;
    exports postgreslibrary.gui.listeners;
    exports postgreslibrary.gui.utils;
    exports postgreslibrary.gui.wrappers;
    exports postgreslibrary.model.dao;

    requires jakarta.persistence;
    requires java.naming;
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive org.hibernate.orm.core;
}
