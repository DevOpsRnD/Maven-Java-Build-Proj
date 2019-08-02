package com.cnk.siapi.common;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class MyResponseErrorHandler implements ResponseErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(MyResponseErrorHandler.class);

    @SuppressWarnings({ "static-access" })
	@Override
    public void handleError(ClientHttpResponse response) throws IOException {
    	
    	try {
    		String theString = IOUtils.toString(response.getBody(), "UTF-8");
    		JSONObject json = (JSONObject) new JSONParser().parse(theString);
    		new LoadClassesAuto().responseJson= json;
			log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
		} catch (ParseException e) {}
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }
}
