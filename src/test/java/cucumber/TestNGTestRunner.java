package cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/java/cucumber",
    glue = "rahulshettyacademy.stepDefinations",
    monochrome=true,tags="@Regression",
    plugin = {"html:target/cucumber.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
