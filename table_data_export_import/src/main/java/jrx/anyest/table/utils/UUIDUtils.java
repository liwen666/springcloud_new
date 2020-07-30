package jrx.anyest.table.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String generateUuid(int length) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		length = length < uuid.length() ? length : uuid.length();
		return uuid.substring(0, length);
	}

	public static String generateUuid() {
		return generateUuid(32);
	}
}
