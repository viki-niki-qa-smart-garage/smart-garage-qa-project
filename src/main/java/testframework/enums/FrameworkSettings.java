package testframework.enums;

import lombok.Getter;

@Getter
public enum FrameworkSettings {
    BROWSER_TYPE("browserType"),
    BROWSER_MODE("browserMode"),
    DEFAULT_TIMEOUT_SECONDS("defaultTimeoutSeconds");

    FrameworkSettings(String propName) {
        value = propName;
    }

    final String value;
}
