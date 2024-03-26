package db4o;

import db4o.config.Db4oHelper;
import db4o.test.TestData;
import db4o.view.GestionGeneral;

public class MainApp {
    public static void main(String[] args) {
        TestData.start(Db4oHelper.getDb());
        GestionGeneral.start();
        Db4oHelper.closeDB();
    }
}
