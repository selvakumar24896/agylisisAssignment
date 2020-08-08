package com.agilysis.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilysis.assignment.model.Friend;
import com.agilysis.assignment.service.AppService;

@RestController
@RequestMapping("/agilysis")
public class AppController {
	@Autowired
	private AppService appService;

	/**
	 * fetch all entries in Friend Document
	 * @return
	 */
	@GetMapping("/getFriends")
	List<Friend> getFriends() {
		List<Friend> friends = appService.getFriends();
		return friends;
	}

	/**
	 * add single friend Object to Document
	 * @param friend - name,birthDate
	 * @return
	 */
	@PostMapping("/addFriend")
	String addFriend(@RequestBody Friend friend) {
		return appService.addFriend(friend);
	}

	/**
	 * add list of friend Object to Document
	 * @param friend - name,birthDate
	 * @return
	 */
	@PostMapping("/addFriends")
	String addFriend(@RequestBody List<Friend> friend) {
		return appService.addFriend(friend);
	}
}
