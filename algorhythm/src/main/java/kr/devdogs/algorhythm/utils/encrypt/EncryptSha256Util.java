package kr.devdogs.algorhythm.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service("sha256util")
public class EncryptSha256Util implements EncryptUtil {
	public String encoding(String str) throws RuntimeException {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte bs[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bs.length; i++) {
				sb.append(Integer.toString((bs[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
