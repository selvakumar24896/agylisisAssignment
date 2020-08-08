package com.agilysis.assignment.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agilysis.assignment.dal.AppRepo;
import com.agilysis.assignment.model.Friend;
import com.agilysis.assignment.util.FriendService;

@Service
public class AppService {

	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private FriendService friendService;
	
	private static String invalidDate="Date is invalid. Date must be in dd-MMM : 01-Jan";
	private static String successMsg="Success";

	public List<Friend> getFriends() {
		List<Friend> friends = null;
		try {
			friends = friendService.sortByBirthDate(appRepo.findAll());
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
		return friends;
	}	

	public String addFriend(Friend friend) {
		try {
			if(friendService.validateFriend(Arrays.asList(friend)))
			{
				appRepo.save(friend);
				return successMsg;
			}
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
		return invalidDate;
		}

	public String addFriend(List<Friend> friends) {
		try {
			if(friendService.validateFriend(friends))
			{
				appRepo.saveAll(friends);
				return successMsg;
			}
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
		return invalidDate;
	}

}
