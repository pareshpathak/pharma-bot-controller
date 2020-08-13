package com.ibm.hello.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private String connectionTimeout;
    private String readTimeout;
	private String botConfigServiceUrl;
	
	private String abondonTimeOut;
	private String piMaskerFlag;
	public String getLastSessionValidity() {
		return lastSessionValidity;
	}
	public void setLastSessionValidity(String lastSessionValidity) {
		this.lastSessionValidity = lastSessionValidity;
	}
	private String lastSessionValidity;
	
	public String getAbondonTimeOut() {
		return abondonTimeOut;
	}
	public void setAbondonTimeOut(String abondonTimeOut) {
		this.abondonTimeOut = abondonTimeOut;
	}
	public String getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public String getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(String readTimeout) {
		this.readTimeout = readTimeout;
	}
	public String getBotConfigServiceUrl() {
		return botConfigServiceUrl;
	}
	public void setBotConfigServiceUrl(String botConfigServiceUrl) {
		this.botConfigServiceUrl = botConfigServiceUrl;
	}
	public String getPiMaskerFlag() {
		return piMaskerFlag;
	}
	public void setPiMaskerFlag(String piMaskerFlag) {
		this.piMaskerFlag = piMaskerFlag;
	}
	@Override
	public String toString() {
		StringBuilder appPropString = new StringBuilder();
		appPropString.append("ApplicationProperties [connectionTimeout=");
		appPropString.append(connectionTimeout);
		appPropString.append(", readTimeout=");
		appPropString.append(readTimeout);
		appPropString.append(", botConfigServiceUrl=");
		appPropString.append(botConfigServiceUrl);
		appPropString.append("]");
		
		return appPropString.toString();
	}
	
	
	
	
}
