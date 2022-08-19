package com.masai.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters...")
	private String name;
	
	@NotEmpty
	@Size(min=3, max=10,message = "Password must be min of 3 chars and max of 10 chars !!")
	@Pattern(regexp = "[0-9]{3,10}")
	private String password;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotNull
	private String about;

}
