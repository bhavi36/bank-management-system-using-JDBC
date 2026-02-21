package org.bank.validation;

public class Validation {
	public static boolean isValidMobile(long mobile) {
		String mob = String.valueOf(mobile);
        return mob != null && mob.matches("\\d{10}");
    }

    public static boolean isValidPin(int pin) {
        return String.valueOf(pin).matches("\\d{4}");
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
