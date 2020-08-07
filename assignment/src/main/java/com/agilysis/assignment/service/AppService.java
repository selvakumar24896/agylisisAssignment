package com.agilysis.assignment.service;

import java.text.SimpleDateFormat;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agilysis.assignment.dal.AppRepo;
import com.agilysis.assignment.model.Friend;

@Service
public class AppService {

	@Autowired
	private AppRepo appRepo;

	public List<Friend> getFriends() {
		List<Friend> friends = null;
		try {
			friends = sortByBirthDate(appRepo.findAll());
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
		return friends;
	}

	/**
	 * sort the friend list by upcoming birthday
	 * 
	 * @param friends
	 * @return
	 */
	private List<Friend> sortByBirthDate(List<Friend> friends) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar bDate = Calendar.getInstance();
		Calendar currentDt = Calendar.getInstance();
		currentDt.setTime(new Date());
		int currentYear = currentDt.get(1);
		String year = String.valueOf(currentYear);

		List<Friend> nList = new ArrayList<Friend>();
		List<Friend> pList = new ArrayList<Friend>();
		try {
			for (Friend fr : friends) {
				bDate.setTime(df.parse(fr.getBirthDate() + "-" + year));
				
				if (currentDt.before(bDate)) {
					nList.add(fr);			// Adds bithday after present Date
				} else {
					pList.add(fr);			// Adds bithday before present Date
				}
			}
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
		nList.sort(new SortbyDate(currentYear));
		pList.sort(new SortbyDate(currentYear));
		friends.clear();
		friends.addAll(nList);
		friends.addAll(pList);
		return friends;
	}

	class SortbyDate implements Comparator<Friend> {
		int year = 0;

		public SortbyDate(int year) {
			this.year = year;
		}

		@Override
		public int compare(Friend f1, Friend f2) {
			return new Date(f1.getBirthDate() + "-" + year).compareTo(new Date(f2.getBirthDate() + "-" + year));
		}
	}

	public void addFriend(Friend friend) {
		try {
			appRepo.save(friend);
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
	}

	public void addFriend(List<Friend> friend) {
		try {
			appRepo.saveAll(friend);
		} catch (Exception ex) {
			System.out.println("!!!Exception : " + ex);
		}
	}

}
