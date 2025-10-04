package tutorialsninja.register;

import java.time.Duration;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TC_RF_002 {
	public static void main(String[] args) throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		//options.addArguments("--incognito");
        options.addArguments("--disable-webauthn");
        options.addArguments("--disable-features=PasswordManagerEnabled,AutofillEnableAccountStorage");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://amazon.in");
		driver.findElement(By.xpath("//span[normalize-space()='Hello, sign in']")).click();
		driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys("kjdhfkjshfkjs@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(10000);
		driver.findElement(By.linkText("Forgot password?")).click();
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		boolean value = driver.findElement(By.xpath("//span[normalize-space()='Enter verification code']")).isDisplayed();
		System.out.println(value);
		
		 String host = "imap.gmail.com";
		 String mailStoreType = "imap";
	        String user = "kjhkjhjk@mail.com";       // Your Gmail address
	        String password = "kjhkjh";      // 16-digit App Password (not your normal Gmail pwd)

	        try {
	            // Set IMAP properties
	            Properties props = new Properties();
	            props.put("mail.store.protocol", "imaps");
	            props.put("mail.imaps.host", host);
	            props.put("mail.imaps.port", "993");
	            props.put("mail.imaps.ssl.enable", "true");

	            // Create a session
	            Session session = Session.getInstance(props);

	            // Connect to Gmail IMAP
	            Store store = session.getStore("imaps");
	            store.connect(host, user, password);

	            // Open Inbox
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);

	            // Get all messages
	            Message[] messages = inbox.getMessages();

	            // Read the latest message
	            Message message = messages[messages.length - 1];

	            // Print From
	            Address[] froms = message.getFrom();
	            String from = (froms == null) ? null : ((InternetAddress) froms[0]).getAddress();
	            System.out.println("From: " + from);

	            // Print Subject
	            System.out.println("Subject: " + message.getSubject());

	            // Print Body
	            String body = getTextFromMessage(message);
	            System.out.println("Body: " + body);

	            // Close
	            inbox.close(false);
	            store.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	}

    // Helper method to extract text from email body
    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    return bodyPart.getContent().toString();
                } else if (bodyPart.isMimeType("text/html")) {
                    return bodyPart.getContent().toString();  // returns raw HTML
                }
            }
        }
        return "";
    }


	}


