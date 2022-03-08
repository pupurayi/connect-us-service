package zw.co.connectus.service.model;

public class JWT {

	private String tokenType;
	private String accessToken;
	private long   expiresIn;

	public JWT(String tokenType, String accessToken, long expiresIn) {

		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getTokenType() {

		return tokenType;
	}

	public void setTokenType(String tokenType) {

		this.tokenType = tokenType;
	}

	public String getAccessToken() {

		return accessToken;
	}

	public void setAccessToken(String accessToken) {

		this.accessToken = accessToken;
	}

	public long getExpiresIn() {

		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {

		this.expiresIn = expiresIn;
	}
}
