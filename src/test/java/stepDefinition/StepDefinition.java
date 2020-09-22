package stepDefinition;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.test.testEmail.excelUtils.ExcelWriter;
import com.test.testEmail.utils.MailObjectFactory;

import dataProvider.ConfigFileReader;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

	private String from;
	private String subject;
	private String body;
	private MailObjectFactory mailObjectFactory;
	private boolean unreadEmailExists=true;
	private ConfigFileReader configFileReader;

	@Given("hit the gmail signin URL in chorome broswer and login successfully")
	public void hit_the_gmail_signin_url_in_chorome_broswer_and_login_successfully() throws GeneralSecurityException, IOException {
		configFileReader=new ConfigFileReader();
		
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(configFileReader.getApplicationUrl());
		mailObjectFactory= PageFactory.initElements(driver, MailObjectFactory.class);

		mailObjectFactory.enterEmail();//Replace with your email id
		mailObjectFactory.enterPassword();//Replace with your password
	}

	@Given("go the first unread mail from inbox")
	public void go_the_first_unread_mail_from_inbox() throws ClassNotFoundException {
		mailObjectFactory.searchFirstUnreadEmail();
		try {
			mailObjectFactory.clickOnFirstEmail();
		}catch(Exception e) {
			unreadEmailExists=false;
		}
  }

	@When("fetch the details if that email")
	public void fetch_the_details_if_that_email() {
		if(unreadEmailExists) {
			 subject=mailObjectFactory.getFirstEmailSubject();
			 from=mailObjectFactory.getFirstEmailFrom();
		}	
	}

	@Then("put the fetch details in excel")
	public void put_the_fetch_details_in_excel() throws IOException {
		if(unreadEmailExists) {
			//Body code not implemented yet
			ExcelWriter excelWriter=new ExcelWriter(from,subject,body);
			excelWriter.readWriteExcel();	
		}	
	}

	@Given("compose a new email to send")
	public void compose_a_new_email_to_send() {
		mailObjectFactory.clickOnComposeMail();
	}
	
	
	@When("put all the neccessary details and add one attachment")
	public void put_all_the_neccessary_details_and_add_one_attachment() throws InterruptedException,IOException {
		mailObjectFactory.enterRecipient();
		mailObjectFactory.enterBody();
		mailObjectFactory.enterSubject();
		mailObjectFactory.attachDoc();
		Thread.sleep(8000);
		Runtime.getRuntime().exec(configFileReader.getAutoItExePath());
	}
	
	
	@Then("send the email")
	public void send_the_email() throws InterruptedException {
		Thread.sleep(15000);
		mailObjectFactory.sendEail();
		mailObjectFactory.quitDriver();
	}

}
