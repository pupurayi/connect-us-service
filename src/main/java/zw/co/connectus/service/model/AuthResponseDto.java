package zw.co.connectus.service.model;

public class AuthResponseDto {

	ProfileDto profile;
	JWT        jwt;

	public AuthResponseDto(ProfileDto profile, JWT jwt) {

		this.profile = profile;
		this.jwt = jwt;
	}

	public ProfileDto getProfile() {

		return profile;
	}

	public void setProfile(ProfileDto profile) {

		this.profile = profile;
	}

	public JWT getJwt() {

		return jwt;
	}

	public void setJwt(JWT jwt) {

		this.jwt = jwt;
	}
}
