package com.pranav.wishlist;

import com.pranav.wishlist.DTO.requestDTO.WishlistRequest;
import com.pranav.wishlist.configFiles.CustomUserDetailsService;
import com.pranav.wishlist.model.UserInformation;
import com.pranav.wishlist.model.Wishlist;
import com.pranav.wishlist.model.config.User;
import com.pranav.wishlist.repository.UserInformationRepo;
import com.pranav.wishlist.repository.WishlistRepo;
import com.pranav.wishlist.repository.config.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration
//@DataJpaTest
class WishlistApplicationTests {

	@Autowired
	Service service;

	@Autowired
	UserInformationRepo userInformationRepo;

	@Autowired
	WishlistRepo wishlistRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	Controller controller;

	@Autowired
	CustomUserDetailsService customUserDetailsService;


	@Test
	public void addWishlistItemTest()
	{
		//1.Authenticate temp user
		service.addUser("user1","user1");
		customUserDetailsService.loadUserByUsername("user1");

		//2.
		//give input
		WishlistRequest wishlistRequest=new WishlistRequest("pr1",500.0);

		//response of function with given temp. values
		ResponseEntity responseEntity=controller.addWishlistItem(wishlistRequest);

		//req. actual response
		ResponseEntity actualResponseEntity=new ResponseEntity<>("User account created successfully,\n Now login to access the application", HttpStatus.CREATED);

		//check output
		Assertions.assertEquals(responseEntity.getStatusCode(),actualResponseEntity.getStatusCode());
	}

	@Test
	public void getWishList()
	{
		//1.Authenticate temp user
		service.addUser("user1","user1");
		customUserDetailsService.loadUserByUsername("user1");

		//2.
		//add a temp wishlist item
		WishlistRequest wishlistRequest=new WishlistRequest("pr1",500.0);
		controller.addWishlistItem(wishlistRequest);

		//store its output
		ResponseEntity responseEntity=controller.getWishList();

		//get req. actual output
		List<Wishlist> wishlistList=new ArrayList<>();
		wishlistList.add(wishlistRepo.findByProductName("pr1"));
		ResponseEntity actualResponseEntity=new ResponseEntity<>(wishlistList,HttpStatus.FOUND);

		//check the responses
		Assertions.assertEquals(responseEntity,actualResponseEntity);
	}

	@Test
	public void deleteWishlistItemTest()
	{
		//1.Authenticate temp user
		service.addUser("user1","user1");
		customUserDetailsService.loadUserByUsername("user1");

		//2.
		//add a temp wishlist item
		WishlistRequest wishlistRequest=new WishlistRequest("pr1",500.0);
		controller.addWishlistItem(wishlistRequest);

		//the response for deleting that given temp. item
		ResponseEntity responseEntity=controller.deleteWishlistItem(wishlistRepo.findByProductName("pr1").getProductId());

		//req. actual response
		ResponseEntity actualResponseEntity=new ResponseEntity<>("Item deleted successfully",HttpStatus.ACCEPTED);

		//check output
		Assertions.assertEquals(responseEntity.getStatusCode(),actualResponseEntity.getStatusCode());

	}

	@Test
	public void addUserTest()
	{
		ResponseEntity responseEntity=service.addUser("user1","user1");

		ResponseEntity actualResponseEntity=new ResponseEntity<>("User account created successfully,\n Now login to access the application", HttpStatus.CREATED);

		Assertions.assertEquals(responseEntity.getStatusCode(),actualResponseEntity.getStatusCode());
	}

	@Test
	void contextLoads() {
	}

}
