package stepDefinition;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import report.BaseUtils;

public class Hook {
	private BaseUtils base;

    public Hook(BaseUtils base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.scenarioDef = base.features.createNode(scenario.getName());
        ClassLoader classLoader=getClass().getClassLoader();
    	System.setProperty("webdriver.chrome.driver", classLoader.getResource("/browsers/chromedriver.exe").getFile());
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.google.com/");

        base.driver = driver;
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        base.driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());

        //((PickleStep)((PickleStepTestStep)
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        //System.out.println("Before every step " + stepTestStep.getId());
    }


}
