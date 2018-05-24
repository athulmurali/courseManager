package com.example.neucourseManager.utilities.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class EMailConfigUtil {


    //name of the config file
    private static final String FILE_NAME = "emailConfig.properties";

    //logger instance
    private static final Logger logger = LoggerFactory.getLogger(EMailConfigUtil.class);


    /**
     * Private constructor to hide implicit public constructor
     */
    public EMailConfigUtil() {
    }

    /**
     * @return an obj of Properties required for email config
     */
    public static Properties returnProperties() throws IOException {

        logger.debug("Loading emailConfig.properties");
        Properties emailProp = new Properties();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        File file = new File(classloader.getResource(FILE_NAME).getFile());
        FileInputStream fileInput = new FileInputStream(file);

        emailProp.load(fileInput);
        return emailProp;
    }

}


