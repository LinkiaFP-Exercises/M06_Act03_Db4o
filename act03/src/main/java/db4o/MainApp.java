package db4o;

import db4o.config.Db4oHelper;
import db4o.test.TestData;

public class MainApp {
    public static void main(String[] args) {
        TestData.start(Db4oHelper.openDB());
        Db4oHelper.closeDB();
    }
}
