import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public static boolean function (User user, List<User> allUsers) {
        if (user==null || user.getPassword()==null || user.getEmail()==null){  //1
            throw new RuntimeException("Mandatory information missing!"); //2
        }

        if (user.getUsername()==null){//3
            user.setUsername(user.getEmail());//4
        }

        int same = 1;//5
        if (user.getEmail().contains("@") && user.getEmail().contains(".")) {//6
            same = 0;//7
            for (int i=0;i<allUsers.size();i++) {//8
                User existingUser = allUsers.get(i);//9
                if (existingUser.getEmail() == user.getEmail()) {//10
                    same += 1;//11
                }
                if (existingUser.getUsername() == user.getUsername()) {//12
                    same += 1;//13
                }
            }
        }

        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}";//14
        String password = user.getPassword();//15
        String passwordLower = password.toLowerCase();//16

        if (passwordLower.contains(user.getUsername().toLowerCase()) || password.length()<8) {//17
            return false;//18
        }
        else {
            if (!passwordLower.contains(" ")) {//19
                for (int i = 0; i < specialCharacters.length(); i++) {//20
                    if (password.contains(String.valueOf(specialCharacters.charAt(i)))) {//21
                        return same == 0;//22
                    }
                }
            }
        }
        return false;//23

    }//24
    public static void main(String[] args) {
        // Create some sample user objects
        User user1 = new User("JohnDoe", "password123", "john.doe@example.com");
        User user2 = new User("JaneSmith", "pass123", "jane.smith@example.com");
        User user3 = new User("BobJohnson", "password", "bob.johnson@example.com");

        // Create a list of all users
        List<User> allUsers = new ArrayList<>(Arrays.asList(user1, user2, user3));

        // Call the function method with a user and the list of all users
        boolean result = function(user1, allUsers);

        // Print the result
        System.out.println("Result: " + result);
    }

}