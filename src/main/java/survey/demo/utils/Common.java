package survey.demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import survey.demo.Constant.SecurityConstant;

import java.security.Key;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

@Service
public class Common {
	private static Logger logger = LogManager.getLogger(Common.class);
	
	public static String hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(SecurityConstant.PASSWORD_HASH_ALGORITHM);
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (Exception e) {
            logger.error("Has error. ", e);
            return null;
        }
    }
}
