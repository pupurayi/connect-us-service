package zw.co.connectus.service.model;

import java.util.UUID;

public class CheckDto {

	private boolean success;
	private UUID    userId;
	private String msisdn;

	public CheckDto(boolean success, UUID userId, String msisdn) {

		this.success = success;
		this.userId = userId;
		this.msisdn = msisdn;
	}

	public boolean isSuccess() {

		return success;
	}

	public void setSuccess(boolean success) {

		this.success = success;
	}

	public UUID getUserId() {

		return userId;
	}

	public void setUserId(UUID userId) {

		this.userId = userId;
	}

	public String getMsisdn() {

		return msisdn;
	}

	public void setMsisdn(String msisdn) {

		this.msisdn = msisdn;
	}
}