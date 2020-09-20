package com.test.testEmail.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

public class MailObjectFactory {

	private WebDriver driver;

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

	@FindBy(how= How.XPATH, xpath="/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[1]/div[2]/div[1]/h2[1]")
	WebElement emailSubject;

	public MailObjectFactory(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterEmail(String emailID) {
		waitForVisible(driver, emailField);
		Actions actions=new Actions(driver);
		actions.moveToElement(emailField);
		actions.click();
		actions.sendKeys(emailID + Keys.ENTER);
		actions.build().perform();
		System.out.println("Email entered");	
	}

	public void enterPassword() throws GeneralSecurityException, IOException {
        String password = "1zKtui9Hgu86rt/GAhmeTw==:IJISEt5uwXSdIs4bbqUY3g==";
     
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = PasswordUtils.createSecretKey("12345qwert#".toCharArray(),
                salt, iterationCount, keyLength);
        String decryptedPassword = PasswordUtils.decrypt(password, key);
        
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

	public void clickOnFirstEmail(){
		waitForVisible(driver, firstEmail);
		Actions actions=new Actions(driver);
		actions.click(firstEmail);
		actions.build().perform();
		System.out.println("First email opened");

	}

	public String  getFirstEmailSubject(){
		waitForVisible(driver, emailSubject);
		String subject=emailSubject.getText();
		return subject;
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
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
