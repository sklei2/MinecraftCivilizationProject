package com.minecraftcivproject.mcp.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceFileHelper {

    public ResourceFileHelper(){

    }

    public String readFileAsString(String resourceFilePath) {


        URL resourceUrl = Resources.getResource(resourceFilePath);

        try {
            return Resources.toString(resourceUrl, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
