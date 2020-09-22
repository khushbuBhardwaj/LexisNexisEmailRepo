package com.test.testEmail.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class MailObjectFactory {

	private WebDriver driver;
	private ConfigFileReader configFileReader;

	@FindBy(how= How.XPATH, xpath="//input[@id='identifierId']")
    WebElement emailField;

	@FindBy(how= How.XPATH, xpath="//input[@name='password']")
    WebElement passwordField;

	@FindBy(how= How.ID, id="identifierNext")
	WebElement nextButton;

	@FindBy(how= How.ID, id="passwordNext")
	WebElement submitLogin;

	@FindBy(how= How.XPATH, xpath="//span[@class='bog']")
	List<WebElement> emailThreads;

	@FindBy(how= How.XPATH, xpath="//span[@class='gb_bb gbii']")
    WebElement profileLogo;

	@FindBy(how= How.XPATH, xpath="//input[@placeHolder='Search mail']")
	WebElement searchUnread;

	@FindBy(how= How.XPATH, xpath="/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/table[1]/tbody[1]/tr[1]")
	WebElement firstEmail;

	@FindBy(how= How.CLASS_NAME, className="hP")
	WebElement emailSubject;
	
	@FindBy(how= How.CLASS_NAME, className="gD")
	WebElement emailBodyText;
	
	@FindBy(how= How.CLASS_NAME, className="gD")
	WebElement emailFrom;

	@FindBy(how= How.XPATH, xpath="/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
	WebElement composeMail;
	
	@FindBy(how= How.CSS, css=".a1.aaA.aMZ")
	WebElement attachDoc;
			
	@FindBy(how= How.CLASS_NAME, className="vO")
	WebElement enterRcipient;
	
	@FindBy(how= How.ID_OR_NAME, name="subjectbox")
	WebElement enterSubject;
	
	@FindBy(how= How.XPATH, xpath="//div[@aria-label='Message Body']")
	WebElement emailBody;
	
	@FindBy(how= How.CLASS_NAME, className="dC")
	WebElement sendEmailBtn;

	public MailObjectFactory(WebDriver driver) {
		this.driver=driver;
		configFileReader=new ConfigFileReader();
	}
	
	public void enterEmail() {
		waitForVisible(driver, emailField);
		Actions actions=new Actions(driver);
		actions.moveToElement(emailField);
		actions.click();
		actions.sendKeys(configFileReader.getSendersEmial() + Keys.ENTER);
		actions.build().perform();
		System.out.println("Email entered");	
	}

	public void enterPassword() throws GeneralSecurityException, IOException {
     
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = PasswordUtils.createSecretKey("12345qwert#".toCharArray(),
                salt, iterationCount, keyLength);
        String decryptedPassword = PasswordUtils.decrypt(configFileReader.getPassword(), key);
        
		waitForVisible(driver, passwordField);
		Actions actions=new Actions(driver);
		actions.moveToElement(passwordField);
		actions.click();
		actions.sendKeys(decryptedPassword + Keys.ENTER);
		actions.build().perform();
		System.out.println("Password entered");
	}

	public void searchFirstUnreadEmail(){
		waitForVisible(driver, searchUnread);
		Actions actions=new Actions(driver);
		actions.moveToElement(searchUnread);
		actions.click();
		actions.sendKeys("label:unread" + Keys.ENTER);
		actions.build().perform();
		System.out.println("Unread email searched");
	}
	
	public void clickOnComposeMail(){
		waitForVisible(driver, composeMail);
		Actions actions=new Actions(driver);
		actions.click(composeMail);
		actions.build().perform();
		System.out.println("composeMail opened");
	}
	
	public void enterRecipient(){
		waitForVisible(driver, enterRcipient);
		Actions actions=new Actions(driver);
		actions.moveToElement(enterRcipient);
		actions.click();
		actions.sendKeys(configFileReader.getReceipientEmial());
		actions.build().perform();
		System.out.println("Entered recipient");
	}
	
	public void enterSubject(){
		waitForVisible(driver, enterSubject);
		Actions actions=new Actions(driver);
		actions.moveToElement(enterSubject);
		actions.click();
		actions.sendKeys("Test Subject");
		actions.build().perform();
		System.out.println("Entered Subject");
	}
	
	public void enterBody(){
		waitForVisible(driver, emailBody);
		Actions actions=new Actions(driver);
		actions.moveToElement(emailBody);
		actions.click();
		actions.sendKeys("Test Body");
		actions.build().perform();
		System.out.println("Entered Body");
	}
	
	public void sendEail(){
		waitForVisible(driver, sendEmailBtn);
		Actions actions=new Actions(driver);
		actions.click(sendEmailBtn);
		actions.build().perform();
		System.out.println("Email send btn click");
	}
	
	public void attachDoc(){
		waitForVisible(driver, attachDoc);
		Actions actions=new Actions(driver);
		actions.click(attachDoc);
		actions.build().perform();
		System.out.println("attach clicked");
	}

	public void clickOnFirstEmail(){
		waitForVisible(driver, firstEmail);
		Actions actions=new Actions(driver);
		actions.click(firstEmail);
		actions.build().perform();
		System.out.println("First email opened");

	}
	
	public void quitDriver() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}

	public String  getFirstEmailSubject(){
		waitForVisible(driver, emailSubject);
		String subject=emailSubject.getText();
		System.out.println("Subject:::"+subject);
		return subject;
	}
	
	public String  getFirstEmailFrom(){
		waitForVisible(driver, emailFrom);
		String from=emailFrom.getText();
		System.out.println("From:::"+from);
		return from;
	}
	
	public String  getFirstEmailBody(){
		waitForVisible(driver, emailBodyText);
		String body=emailBodyText.getText();
		System.out.println("Body::"+body);
		return body;
	}


	public void clickEmail(String emailSubject) {
		waitForVisible(driver, profileLogo);

		for (int i = 0; i < emailThreads.size(); i++) {
			
			if (emailThreads.get(i).getText().contains(emailSubject)) {
				emailThreads.get(i).click();
				System.out.println("email clicked");
				break;
			}
		}
	}
	public void waitForVisible(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
			System.out.println("Waiting for element visibility");
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, configFileReader.getImplicitlyWait());
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
