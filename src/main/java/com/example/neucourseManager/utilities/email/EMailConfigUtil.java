package com.example.neucourseManager.utilities.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

        try {
            File file = ResourceUtils.getFile("classpath:emailConfig.properties");
            InputStream in = new FileInputStream(file);
            emailProp.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return emailProp;
    }

}


