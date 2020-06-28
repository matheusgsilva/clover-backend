package br.com.clover;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("clover.info")
public class AppProperties {
	private String version = "Version 1.0";
	private String banner = "\r\n" + 
			"   ________    ____ _    ____________        ____  ___   ________ __ _______   ______ \r\n" + 
			"  / ____/ /   / __ \\ |  / / ____/ __ \\      / __ )/   | / ____/ //_// ____/ | / / __ \\\r\n" + 
			" / /   / /   / / / / | / / __/ / /_/ /_____/ __  / /| |/ /   / ,<  / __/ /  |/ / / / /\r\n" + 
			"/ /___/ /___/ /_/ /| |/ / /___/ _, _/_____/ /_/ / ___ / /___/ /| |/ /___/ /|  / /_/ / \r\n" + 
			"\\____/_____/\\____/ |___/_____/_/ |_|     /_____/_/  |_\\____/_/ |_/_____/_/ |_/_____/  \r\n" + 
			"                                                                                      \r\n" + 
			"";

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

}
