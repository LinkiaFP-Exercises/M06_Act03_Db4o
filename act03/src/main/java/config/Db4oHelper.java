package config;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oException;

import java.io.File;

public class Db4oHelper {

    private static final String DB4O_DIRECTORY = "db";
    private static final String DB4O_FILE_NAME = "db4o.yap";
    private static ObjectContainer db = null;

    public static ObjectContainer openDB() {
        try {
            // Construir la ruta al directorio de la base de datos en la raíz del proyecto
            String projectPath = System.getProperty("user.dir");
            String dbPath = projectPath + File.separator + DB4O_DIRECTORY;

            // Crear el directorio si no existe
            File directory = new File(dbPath);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new Exception("No se pudo crear el directorio para la base de datos en: " + dbPath);
            }

            // Construir la ruta completa al archivo de base de datos
            String dbFilePath = dbPath + File.separator + DB4O_FILE_NAME;

            // Configuración adicional de db4o si es necesario
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            // config.common(). ... // Aquí puedes agregar configuraciones específicas

            // Abrir o crear la base de datos en la ruta especificada
            db = Db4oEmbedded.openFile(config, dbFilePath);
            return db;
        } catch (Db4oException e) {
            // Capturar excepciones específicas de db4o
            System.err.println("Db4oException al abrir la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Capturar excepciones generales de Java
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
