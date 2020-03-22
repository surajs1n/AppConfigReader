package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class used to fetch requested config file.
 * @author - suraj.s
 * @date - 2020-03-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestedConfigFileMetadata {
    private String fileFolderPath;
    private String fileExtension;
}
