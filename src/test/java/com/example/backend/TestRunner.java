package com.example.backend;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/com/example/backend"},
        glue = {"com.example.backend"},
        tags= "@Customer"
)
public class TestRunner {

}
