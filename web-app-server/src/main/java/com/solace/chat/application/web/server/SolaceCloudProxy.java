package com.solace.chat.application.web.server;


import com.google.gson.Gson;
import com.solace.chat.application.common.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * The SolaceCloudProxy simply acts as a REST-ful proxy to the SolaceCloudInstace.
 * @author Thomas Kunnumpurath
 */
@Controller
public class SolaceCloudProxy {

    //Gson object for serializing/deserializing json objects
    Gson gson = new Gson();

    //Properties are read from resources/application.properties
    @Value("${solace.rest.host}")
    private String solaceRESTHost;

    @Value("${solace.username}")
    private String solaceUsername;

    @Value("${solace.password}")
    private String solacePassword;

    //HttpHeader for the http post
    private HttpHeaders httpHeaders;

    //Setting up the header for the Solace Request
    @PostConstruct
    public void init() {
        httpHeaders = new HttpHeaders() {{
            String auth = solaceUsername + ":" + solacePassword;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
            set("Content-Type","application/json");
        }};
    }

    //Function that makes a REST-ful call to Solace
    @RequestMapping(value = "/solace/cloud/proxy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity SendLoginRequetOverSolace(@RequestBody UserObject userObject) {
        //Rest Request goes here
     
    }
}