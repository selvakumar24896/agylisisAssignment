package com.agilysis.assignment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Service;
import com.agilysis.assignment.model.Friend;

@Service
public class FriendService {

	public boolean validateFriend(List<Friend> friends) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		df.setLenient(false);
		try {
			Calendar currentDt = Calendar.getInstance();
			currentDt.setTime(new Date());
			int currentYear = currentDt.get(1);
			String year = String.valueOf(currentYear);
			for (Friend friend : friends) {
				df.parse(friend.getBirthDate()+"-"+year);
			}
			return true;
		} catch (ParseException ex) {
			System.out.println("Date Parse Exception : "+ex.getMessage());
			return false;
		}
		
	}

	/**
	 * sort the friend list by upcoming birthday
	 * 
	 * @param friends
	 * @return
	 */
	public List<Friend> sortByBirthDate(List<Friend> friends) {
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
					nList.add(fr); // Adds bithday after present Date
				} else {
					pList.add(fr); // Adds bithday before present Date
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
		private int year = 0;

		public SortbyDate(int year) {
			this.year = year;
		}

		@Override
		public int compare(Friend f1, Friend f2) {
			return new Date(f1.getBirthDate() + "-" + year).compareTo(new Date(f2.getBirthDate() + "-" + year));
		}
	}
}
