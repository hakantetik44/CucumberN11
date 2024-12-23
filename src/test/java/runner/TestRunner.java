package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",
                "utilities.formatter.PrettyReports:target/cucumber-pretty-reports"},
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@smoke",
        dryRun = false
)
public class TestRunner {
}