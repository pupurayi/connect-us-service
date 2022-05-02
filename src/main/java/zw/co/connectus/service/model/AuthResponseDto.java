package zw.co.connectus.service.model;

public class AuthResponseDto {

	UserDto user;
	JWT        jwt;

	public AuthResponseDto(UserDto user, JWT jwt) {
		this.user = user;
		this.jwt = jwt;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public JWT getJwt() {
		return jwt;
	}

	public void setJwt(JWT jwt) {
		this.jwt = jwt;
	}
}
