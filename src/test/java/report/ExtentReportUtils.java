package report;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtils extends BaseUtils{

	String  fileName= reportLocation + "extentreport.html";
	
	public void ExtentReport() {
		
		extent=new ExtentReports();
		
		ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Test report for email test ");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report");
		
		extent.attachReporter(htmlReporter);
	}
	
	public void flushReport() {
	extent.flush();	
	}
}
