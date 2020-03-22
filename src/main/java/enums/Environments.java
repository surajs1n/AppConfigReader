package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - suraj.s
 * @date - 2020-03-22
 */
public enum Environments {
    STAGE("stage"),
    PROD("prod");

    private String environmentVariable;

    Environments(final String environmentVariable) {
        this.environmentVariable = environmentVariable;
    }

    public String getEnvironmentVariable() {
        return this.environmentVariable;
    }

    private static Map<String, Environments> environmentsMap = new HashMap<>();

    static {
        for (Environments environments : Environments.values()) {
            environmentsMap.put(environments.getEnvironmentVariable(), environments);
        }
    }

    public Environments getEnvironmentFromString(final String value) {
        return environmentsMap.get(value);
    }
}
