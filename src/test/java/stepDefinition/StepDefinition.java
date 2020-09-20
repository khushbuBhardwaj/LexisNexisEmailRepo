package stepDefinition;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.aventstack.extentreports.GherkinKeyword;
import com.test.testEmail.excelUtils.ExcelWriter;
import com.test.testEmail.utils.MailObjectFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import report.BaseUtils;

public class StepDefinition extends BaseUtils {

	private String from;
	private String subject;
	private String body;
	private MailObjectFactory mailObjectFactory;
	private BaseUtils base;
	
	public StepDefinition(BaseUtils base){
		this.base=base;
	}
	

	@Given("hit the gmail signin URL in chorome broswer and login successfully")
	public void hit_the_gmail_signin_url_in_chorome_broswer_and_login_successfully() throws GeneralSecurityException, IOException {
//
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\khushbu bhardwaj\\chromedriver.exe");
//		ChromeDriver driver=new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://mail.google.com/");
		String s=App.class.getResource("/browsers/chromedriver.exe").getFile();
		mailObjectFactory= PageFactory.initElements(driver, MailObjectFactory.class);

		mailObjectFactory.enterEmail("2020testqwe@gmail.com");//Replace with your email id
		mailObjectFactory.enterPassword();//Replace with your password
	}

	@Given("go the first unread mail from inbox")
	public void go_the_first_unread_mail_from_inbox() throws ClassNotFoundException {
        base.scenarioDef.createNode(new GherkinKeyword("Given"),"go the first unread mail from inbox");
		mailObjectFactory.searchFirstUnreadEmail();
		mailObjectFactory.clickOnFirstEmail();
  }

	@When("fetch the details if that email")
	public void fetch_the_details_if_that_email() {
		 subject=mailObjectFactory.getFirstEmailSubject();
	}

	@Then("put the fetch details in excel")
	public void put_the_fetch_details_in_excel() throws IOException {
		ExcelWriter excelWriter=new ExcelWriter(from,subject,body);
		excelWriter.readWriteExcel();
	}

	@Given("compose a new email to send")
	public void compose_a_new_email_to_send() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("put all the neccessary details and add one attachment")
	public void put_all_the_neccessary_details_and_add_one_attachment() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("send the email")
	public void send_the_email() {
	    // Write code here that turns the phrase above into concrete actions
	}

}
