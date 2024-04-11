public class LoginValidation {

    private static boolean containsWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true; // Found whitespace
            }
        }
        return false; // No whitespace found
    }

    public static boolean isValidLogin(String username, String password){
        // Check if username or password contains whitespace
        return !containsWhitespace(username) && !containsWhitespace(password); // Username or password contains whitespace

    }

    public static boolean isAdmin(String isAdmin) {
        String regex = "^[a-zA-Z]*$";

        // Return true if the string contains only letters and is not empty
        if(isAdmin != null && isAdmin.matches(regex)){
            if (isAdmin.equals("t") || isAdmin.equals("T") || isAdmin.equals("f") || isAdmin.equals("F")){
                return true;
            }
        }
            return false;
    }




}
