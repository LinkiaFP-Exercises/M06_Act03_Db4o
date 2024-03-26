package db4o.config;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oException;

import java.io.File;

public class Db4oHelper {

    private static final String DB4O_DIRECTORY = "db";
    private static final String DB4O_FILE_NAME = "db4o.yap";
    private static ObjectContainer db = openDB();

    public static ObjectContainer getDb() {
        return db;
    }

    private static ObjectContainer openDB() {
        try {
            String projectPath = System.getProperty("user.dir");
            String dbPath = projectPath + File.separator + DB4O_DIRECTORY;
            File directory = new File(dbPath);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new Exception("No se pudo crear el directorio para la base de datos en: " + dbPath);
            }
            String dbFilePath = dbPath + File.separator + DB4O_FILE_NAME;
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            db = Db4oEmbedded.openFile(config, dbFilePath);
            return db;
        } catch (Db4oException e) {
            System.err.println("Db4oException al abrir la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception al abrir la base de datos: " + e.getMessage());
        }
        return null;
    }

    public static void closeDB() {
        if (db != null) {
            db.close();
        }
    }

}
