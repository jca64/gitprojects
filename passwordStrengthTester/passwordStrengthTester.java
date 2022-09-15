package passwordStrengthTester;

import javax.swing.JOptionPane;

public class passwordStrengthTester {

    public static String passwordStrengthTester(String password) {

        String passwordScore = "";
    
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;
        boolean hasNumber = false;

        // Tests password conditions
        for(char i : password.toCharArray()) {
            if(Character.isUpperCase(i)) {
                hasUpper = true;
            }
            if(Character.isLowerCase(i)) {
                hasLower = true;
            }
            if(i == 33 || i == 64 || i == 35 || i == 36 || i == 38) {
                hasSpecial = true;
            }
            if(i >= 47 && i <= 58) {
                hasNumber = true;
            }
        }

        if((hasUpper && hasLower && hasSpecial && hasNumber) && password.length() > 15) {
            passwordScore = "This password is excellent!";
        }
        else if(((hasUpper && hasLower && hasSpecial) || (hasUpper && hasLower && hasNumber)) && password.length() > 7) {
            passwordScore = "This password is good";
        }
        else if((hasUpper && hasLower) && password.length() > 1) {
            passwordScore = "This password is bad";
        }
        else {
            passwordScore = "Information = free";
        }
        
        return passwordScore;
    }
    
}
