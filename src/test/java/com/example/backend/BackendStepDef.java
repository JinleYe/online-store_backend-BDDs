package com.example.backend;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class BackendStepDef {


    private JsonObject requestPayload;
    private JsonObject responsePayload;

    private int id;
    private String restEndPoint;
    private String httpMethod;
    private int statusCode;


    private static final String requestURI = "http://localhost:8080%s/%s";

    @Given("use a valid account {int}")
    public void use_a_valid_account(int id){
        Assert.assertNotNull(id);
        this.id = id;
    }

    @When("get one customer with REST endpoint {string}")
    public void get_one_customer_with_rest_endpoint_customers(String endpoint){
        this.restEndPoint = endpoint;
    }

    @And("HTTP Method {string}")
    public void Http_Method(String method){
        this.httpMethod = method;
    }

    @When("executed")
    public void sendRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(String.format(requestURI, restEndPoint, String.valueOf(id))))
        .GET().build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        this.statusCode = response.statusCode();
        this.responsePayload = new JsonParser().parse(response.body()).getAsJsonObject();

        Assert.assertEquals(id, responsePayload.get("id").getAsInt());
        Assert.assertNotNull(responsePayload.get("name"));

    }
    @Then("HTTP status code should be {int}")
    public void HTTP_status_code_should_be(int value){
        Assert.assertEquals(value, statusCode);
    }




}
