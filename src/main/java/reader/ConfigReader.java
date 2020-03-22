package reader;

import java.io.IOException;

/**
 * @author - suraj.s
 * @date - 2020-03-22
 */
public interface ConfigReader<T> {

    /**
     * Function contract to read configuration from the file.
     * @return return the configurations in string form.
     * @throws IOException - Throw in case of any I/O exception.
     */
    String readerConfigInStringFrom(T i) throws Exception;
}
