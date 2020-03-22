package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Final class to read the static details present in "app.properties" file.
 * @author - suraj.s
 * @date - 2020-03-21
 */
public final class PropertiesReader {

    private static final String APP_PROPERTIES_FILE_PATH = "./src/main/resources/configs/app.properties";
    private static final String CONFIG_FOLDER_RELATIVE_PATH = "CONFIG_FOLDER_RELATIVE_PATH";
    private static final String CONFIG_FILE_EXTENSION = "CONFIG_FILE_EXTENSION";
    private static PropertiesReader propertiesReader = null;

    /**
     * To restrict creation of its object outside.
     */
    private PropertiesReader() {

    }

    /**
     * Get one and only one copy of PropertiesReader object.
     * @return {@link PropertiesReader} object.
     */
    public static PropertiesReader getPropertiesReaderInstance() {
        if (propertiesReader == null) {
            return new PropertiesReader();
        }

        return propertiesReader;
    }

    /**
     * Compile and return all the attributes in Properties objects.
     * @return Properties object.
     * @throws IOException - Throw in case of IO exception.
     */
    public Properties getAllProperties() throws IOException {
        final Properties properties = new Properties();

        try (final FileReader reader = new FileReader(APP_PROPERTIES_FILE_PATH)) {
            properties.load(reader);
        }

        return properties;
    }

    /**
     * Return the relative path of folder containing configurations to be read.
     * @return Relative path of configuration folder.
     * @throws IOException - Throw in case of IO exception.
     */
    public String getConfigFolderRelativePath() throws IOException {
        final Properties properties = new Properties();

        try (final FileReader reader = new FileReader(APP_PROPERTIES_FILE_PATH)) {
            properties.load(reader);
        }

        return properties.getProperty(CONFIG_FOLDER_RELATIVE_PATH,"./configurations");
    }

    /**
     * Returns the extension of configuration file which is supposed to be fetched.
     * @return Extension of configuration file.
     * @throws IOException - Throw in case of I/O exception.
     */
    public String getConfigFileExtension() throws IOException {
        final String configFileExtension = System.getenv(CONFIG_FILE_EXTENSION);
        if (configFileExtension == null) {
            final Properties properties = new Properties();
            try (final FileReader reader = new FileReader(APP_PROPERTIES_FILE_PATH)) {
                properties.load(reader);
            }

            return properties.getProperty(CONFIG_FILE_EXTENSION, "json");
        }

        return configFileExtension.toLowerCase(Locale.ENGLISH);
    }


}
