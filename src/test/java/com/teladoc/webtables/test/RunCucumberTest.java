package com.teladoc.webtables.test;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.teladoc.webtables.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class RunCucumberTest {
}

