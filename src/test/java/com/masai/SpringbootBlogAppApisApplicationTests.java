package com.masai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.masai.repository.UserRepo;

@SpringBootTest
class SpringbootBlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest()
	{
		System.out.println(userRepo.getClass().getName());
		System.out.println(userRepo.getClass().getPackageName());
		
	}
}
