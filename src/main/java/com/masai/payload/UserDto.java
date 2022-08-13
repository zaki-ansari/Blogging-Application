package com.masai.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String about;

}
