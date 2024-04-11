public class LoginValidation {

    private boolean containsWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true; // Found whitespace
            }
        }
        return false; // No whitespace found
    }

    public boolean isValidLogin(String username, String password){
        // Check if username or password contains whitespace
        return !containsWhitespace(username) && !containsWhitespace(password); // Username or password contains whitespace

    }



}
