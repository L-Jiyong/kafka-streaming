package com.example.dataType;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class account_info {
	private String account_id;
	private String role_id;
	private String user_name;
	private String user_description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
	private String update_date;

}
