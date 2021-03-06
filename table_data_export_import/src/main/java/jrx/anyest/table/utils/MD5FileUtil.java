package jrx.anyest.table.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5FileUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(MD5FileUtil.class);
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5FileUtil messagedigest初始化失败", e);
		}
	}

	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
//		FileChannel ch = in.getChannel();
//		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
//				file.length());
//		messagedigest.update(byteBuffer);
//		ch.close();
		
		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(in)) ;
//		String md5 = bufferToHex(messagedigest.digest());
		in.close();
		return md5;//bufferToHex(messagedigest.digest());
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static void main(String[] args) throws IOException {
//		long begin = System.currentTimeMillis();
//
//		File big = new File("D:\\temp\\jre-7u11-linux-i586.tar.gz");
//		String md5 = getFileMD5String(big);
//
//		long end = System.currentTimeMillis();
//		System.out.println("md5:" + md5);
//		System.out.println("time:" + ((end - begin) / 1000) + "s");


//		String md5str = "userName";
//		System.out.println(getMD5String(md5str));
//		System.out.println();

		Map map = new HashMap<>();
		map.put("aaa","bbbd");
		byte[] bytes = map.toString().getBytes();
		String md5String = getMD5String(bytes);
		System.out.println(md5String);
		System.out.println(getMD5String(map.toString()));


	}

}
