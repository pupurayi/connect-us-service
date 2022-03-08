package zw.co.connectus.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CheckDto {

	private boolean success;
	private UUID    userId;
	private String msisdn;
}