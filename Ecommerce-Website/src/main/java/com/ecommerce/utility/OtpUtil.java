package com.ecommerce.utility;

import java.util.Random;

/*
 * This class is used for otp generation
 */
public class OtpUtil {
	public static String generateOtp() {
		StringBuilder otp=new StringBuilder(LoginConstants.OTP_LENGTH);
		Random random=new Random();
		for(int i=0;i<LoginConstants.OTP_LENGTH;i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}

}
