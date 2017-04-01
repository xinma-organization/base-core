package com.xinma.base.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class KeyHelper {
	private final static Map<Integer, String> digestKeyMap = new HashMap<>();
	private static boolean isInitKeys = false;

	/**
	 * 初始化Digest keys
	 * 
	 * @throws IOException
	 */
	public synchronized static void initDigestKeyMap() throws IOException {
		if (isInitKeys) {
			// 保证key map只初始化一次
			return;
		}

		int i = 0;
		InputStream in = KeyHelper.class.getResourceAsStream("digest-keys.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String key = reader.readLine();
		while (key != null) {
			digestKeyMap.put(i, key);
			i++;
			key = reader.readLine();
		}

		isInitKeys = true;
		reader.close();

	}

	/**
	 * 随机获取一个key
	 * 
	 * @return
	 */
	public static int randomDigestKeyIndex() {
		return ThreadLocalRandom.current().nextInt(1000);
	}

	/**
	 * 获取index 指定的key
	 * 
	 * @param index
	 * @return
	 */
	public static String getSpecifiedKey(int index) {
		return digestKeyMap.get(index);
	}
}
