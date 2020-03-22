package main;

import config.PropertiesReader;
import models.RequestedConfigFileMetadata;
import reader.ConfigReader;
import reader.impls.ConfigReaderImpl;

/**
 * @author - suraj.s
 * @date - 2020-03-21
 */
public class MainFunction {

    public static void main(String [] args) throws Exception {
        final PropertiesReader propertiesReader = PropertiesReader.getPropertiesReaderInstance();

//        System.out.println(propertiesReader.getConfigFolderRelativePath());
//
//        System.out.println(propertiesReader.getAllProperties());
//
//        System.out.println(Environments.values());
//
//        System.out.println(Environments.PROD);
//
//        System.out.println(Environments.PROD.toString());
//
//        System.out.println(Environments.PROD.getEnvironmentVariable());

        RequestedConfigFileMetadata metadata = RequestedConfigFileMetadata.builder()
                .fileFolderPath(propertiesReader.getConfigFolderRelativePath())
                .fileExtension("txt")
                .build();

        ConfigReader reader = new ConfigReaderImpl();

        System.out.println(reader.readerConfigInStringFrom(metadata));

    }
}
