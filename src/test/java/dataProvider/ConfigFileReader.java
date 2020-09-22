package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "config//Configuration.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}

	public String getApplicationUrl() {
		String url = properties.getProperty("baseUrl");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getSendersEmial() {
		String url = properties.getProperty("sendermail");
		if (url != null)
			return url;
		else
			throw new RuntimeException("sendermail not specified in the Configuration.properties file.");
	}
	
	public String getReceipientEmial() {
		String url = properties.getProperty("receipientEmail");
		if (url != null)
			return url;
		else
			throw new RuntimeException("receipientEmail not specified in the Configuration.properties file.");
	}
	
	public String getAutoItExePath() {
		String url = properties.getProperty("autoitExePath");
		if (url != null)
			return url;
		else
			throw new RuntimeException("autoitExePath not specified in the Configuration.properties file.");
	}
	
	public String getPassword() {
		String url = properties.getProperty("password");
		if (url != null)
			return url;
		else
			throw new RuntimeException("autoitExePath not specified in the Configuration.properties file.");
	}

}