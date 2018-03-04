package gumtree.api;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@cucumber.api.CucumberOptions(tags = {"@gumtree"},
    features = "src/test/resources",
    glue = {"gumtree.api.stepdefs"},
    strict = true,
    format = {"html:cucumber-html-reports",
        "json:cucumber-reports-with-handlebars/cucumber-report.json"})
public class TestRunner {

}