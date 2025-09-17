package com.pages;

import testframework.PropertiesManager;
import testframework.core.BaseWebPage;

public class BasePage extends BaseWebPage {
    public BasePage(String pageSpecificUrl) {
        super(pageSpecificUrl);
    }

    @Override
    public String getBasePageUrl() {
        return  PropertiesManager.getConfigProperties().getProperty("baseUrl");
    }
}
