package report;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseUtils {
  public WebDriver driver;
  public ExtentReports extent;
  public static ExtentTest scenarioDef;
  public static ExtentTest features;
  public static String reportLocation="/Lexisnexis/report/";
}
