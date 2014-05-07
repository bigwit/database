package com.database.web.autorization;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.database.data.domain.User;

public class GridData {

	private ConcurrentHashMap<String, User> users;
	private ConcurrentHashMap<Date, String> accessTimes;

	private static GridData instance = null;

	private GridData() {
		this.users = new ConcurrentHashMap<>();
		accessTimes = new ConcurrentHashMap<>();
		Thread t = new Thread(new Cleaner());
		t.start();
	}

	public static GridData getInstance() {
		if (instance == null) {
			instance = new GridData();
		}
		return instance;
	}

	/**
	 * Возвращает имя куки, которуя надо добавить в запрос
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String addUser(User user) {
		if(user == null) {
			return null;
		}
		String secureСookie = user.getHashPasswd() + getMD5(user.getLogin());
		users.put(secureСookie, user);
		accessTimes.put(new Date(), secureСookie);
		return secureСookie;
	}
	
	private void update(String cookie) {
		for(Date d : accessTimes.keySet()) {
			if(accessTimes.get(d).equals(cookie)) {
				accessTimes.put(new Date(), cookie);
				return;
			}
		}
	}

	public User getUser(String cookie) {
		if (cookie == null) {
			return null;
		}
		User u = users.get(cookie);
		if(u != null) {
			update(cookie);
		}
		return u;
	}

	public static String getMD5(String source) {
		byte[] thedigest = null;
		try {
			byte[] bytesOfMessage = source.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			thedigest = md.digest(bytesOfMessage);
			return URLEncoder.encode(new String(thedigest), "UTF-8");
		} catch (Exception e) {
			return source;
		}
	}

	private class Cleaner implements Runnable {

		@Override
		public void run() {
			while (true) {
				Date currentDate = new Date();
				for (Date d : accessTimes.keySet()) {
					// сессия пользователя 30 минут
					if (currentDate.getMinutes() - d.getMinutes() > 30) {
						users.remove(accessTimes.get(d));
						accessTimes.remove(d);
					}
				}
				try {
					Thread.sleep(1000000L); // ~ 17 min
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
