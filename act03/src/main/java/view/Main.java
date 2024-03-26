package view;

import config.Db4oHelper;
import test.TestData;

public class Main {
    public static void main(String[] args) {
        TestData.start(Db4oHelper.openDB());
        Db4oHelper.closeDB();
    }
}
