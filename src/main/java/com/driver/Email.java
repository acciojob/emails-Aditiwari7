package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(!oldPassword.equals(newPassword)){
            if(checkcriteria(newPassword)){
                this.password = newPassword;
            }
        }
    }

    private boolean checkcriteria(String currPswrd){
        boolean checkUCLetter = false;
        boolean checkLCLetter = false;
        boolean checkDigit = false;
        boolean checkSpecialChar = false;

        if(currPswrd.length() < 8) return false;

        for(int i=0; i<currPswrd.length(); i++){
            char ch = currPswrd.charAt(i);
            if(Character.isLetter(ch)){
                if(Character.isLowerCase(ch)){
                    checkLCLetter = true;
                }
                if(Character.isUpperCase(ch)){
                    checkUCLetter = true;
                }
            }
            else if(Character.isDigit(ch)){
                checkDigit = true;
            } else if(!Character.isWhitespace(ch)) {
                checkSpecialChar = true;
            }
        }

        if((checkLCLetter==true && checkUCLetter==true) && (checkDigit==true && checkSpecialChar==true))
            return true;
        return false;
    }
}
