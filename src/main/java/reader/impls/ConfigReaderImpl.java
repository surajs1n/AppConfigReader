package reader.impls;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import models.RequestedConfigFileMetadata;
import reader.ConfigReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author - suraj.s
 * @date - 2020-03-22
 */
@AllArgsConstructor
public class ConfigReaderImpl implements ConfigReader<RequestedConfigFileMetadata> {

    private static final String SERVICE_ENVIRONMENT = "SERVICE_ENVIRONMENT";
    private static final String SLASH_CHARACTER = "/";

    @Override
    public String readerConfigInStringFrom(final RequestedConfigFileMetadata fileMetadata) throws Exception {
        final String serviceEnvironment = System.getenv(SERVICE_ENVIRONMENT).toLowerCase(Locale.ENGLISH);
        final String finalFolderPath = fileMetadata.getFileFolderPath() + SLASH_CHARACTER + serviceEnvironment;
        final StringBuffer stringBuffer = new StringBuffer(8192); //It is equal to buffer size of BufferedReader
        final File folder = new File(finalFolderPath);
        if (folder.isDirectory()) {
            final String fileName = getRightFileName(folder.list(), fileMetadata.getFileExtension());
            final String finalConfigFilePath = finalFolderPath + SLASH_CHARACTER + fileName;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(finalConfigFilePath))) {
                String fetchedString;
                while ((fetchedString = bufferedReader.readLine()) != null) {
                    stringBuffer.append(fetchedString).append("\n");
                }
            }

        } else {
            throw new Exception(String.format("%s is not a directory", finalFolderPath));
        }

        return stringBuffer.toString();
    }

    private String getRightFileName(@NonNull final String [] fileNames, @NonNull final String extension) throws Exception {
        final List<String> filteredFiles = new ArrayList<>();
        for (String fileName : fileNames) {
            if (extension.equalsIgnoreCase(findFileExtension(fileName))) {
                filteredFiles.add(fileName);
            }
        }

        try {
            Preconditions.checkArgument(filteredFiles.size() == 1,
                    String.format("There are either zero or more than one files found of .%s extension.", extension));
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }

        return filteredFiles.get(0);
    }

    private String findFileExtension(final String fileName) throws Exception {
        int index = fileName.length() - 1;
        while (index >= 0 && fileName.charAt(index) != '.') {
            index--;
        }

        if (index < 0) {
            throw new Exception(String.format("%s doesn't have extension.", fileName));
        }

        final StringBuffer stringBuffer = new StringBuffer();
        for (index++; index < fileName.length(); index++) {
            stringBuffer.append(fileName.charAt(index));
        }
        return stringBuffer.toString();
    }
}
