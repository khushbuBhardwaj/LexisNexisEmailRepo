Feature: email read and compose new email in gmail

 Scenario: Fetch the first unread e-mail from inbox and print the content of email in an excel
  Given hit the gmail signin URL in chorome broswer and login successfully
  Given go the first unread mail from inbox
  When fetch the details if that email
  Then put the fetch details in excel
    
    
 #Scenario: Compose a new mail with attchment and send it to some recipient
 # Given compose a new email to send 
 # When put all the neccessary details and add one attachment
  #Then send the email
    
    