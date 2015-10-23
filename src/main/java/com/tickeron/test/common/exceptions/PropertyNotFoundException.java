package com.tickeron.test.common.exceptions;

/**
 * Created by slaviann on 22.10.15.
 */
public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(String propertyName) {
        super(String.format("Please check app.properties file. There is no property with name %s", propertyName));
    }
}
