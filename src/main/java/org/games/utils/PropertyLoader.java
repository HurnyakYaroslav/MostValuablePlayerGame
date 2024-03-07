package org.games.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.games.exceptions.PropertyFileParsingException;

public class PropertyLoader {

    private static final String PROPERTY_FILE_NAME = "application.properties";

    public static PropertiesConfiguration getPropertyConfiguration(){
        var config = new PropertiesConfiguration();
        try {
            config.load(PROPERTY_FILE_NAME);
        } catch (ConfigurationException e) {
            throw new PropertyFileParsingException(e.getMessage());
        }
        return config;
    }
}
