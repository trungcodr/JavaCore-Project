package service;

import entites.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {
    List<User> users = new ArrayList<>();
    //Ham kiem tra Email hop le
    public boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex,email);
    }

    //Ham kiem tra mat khau hop le
    public boolean isValidPassword(String password){
        return password.length() >= 7 && password.length() <= 15 && password.matches(".*[A-Z].*") &&
                password.matches(".*[.,\\-_;].*");
    }

    //Ham tim tai khoan theo username hoac email
    public User findAccount(String key,String value){
        for (User user : users){
            if ((key.equals("username") && user.getUserName().equals(value)) ||
                    (key.equals("email") && user.getEmail().equals(value))) {
                return user;
            }
        }
        return null;
    }

    //Ham dang ky tai khoan moi
    public void register(Scanner scanner) {
        System.out.println("----*----Dang ky----*----");
        while (true){
            System.out.print("Moi ban nhap username: ");
            String username = scanner.nextLine();
            if (findAccount("username",username) != null){
                System.out.println("Username da ton tai, vui long thu lai.");
                continue;
            }

            System.out.print("Moi ban nhap email: ");
            String email = scanner.nextLine();
            if(!isValidEmail(email)){
                System.out.println("Email khong hop le, ui long thu lai.");
                continue;
            }
            if (findAccount("email",email) != null){
                System.out.println("Email dan ton tai, vui long thu lai.");
                continue;
            }

            System.out.print("Moi ban nhap password: ");
            String password = scanner.nextLine();
            if (!isValidPassword(password)){
                System.out.println("Password khong hop le, moi ban nhap lai.");
                continue;
            }

            User user = new User(username,password,email);
            users.add(user);
            System.out.println("Dang ky thanh cong!");
            break;
        }
    }

    //Ham dang nhap
    public void login(Scanner scanner ) {
        System.out.println("----*----Dang nhap----*----");
        while (true){
            System.out.print("Moi ban nhap username: ");
            String username = scanner.nextLine();
            User user  = findAccount("username",username);
            if (user == null){
                System.out.println("Kiem tra lai ten dang nhap");
            }

            System.out.print("Moi ban nhap password: ");
            String password = scanner.nextLine();
            if (user.getPassWord().equals(password)){
                System.out.println("Chao mung " + username + " ban co the thuc hien cong viec sau: ");
                loginMenu(scanner,user);

                break;
            } else {
                System.out.println("Sai password!");
                System.out.println("1. Dang nhap lai\n" +
                                    "2. Quen mat khau");
                System.out.print("Moi ban lua chon: ");
                String option = scanner.nextLine();
                if (option.equals("2")){
                    resetPassword(scanner);
                    break;
                } else if (!option.equals("1")) {
                    System.out.println("Nhap khong hop le, vui long thu lai.");
                }
            }
        }
    }

    //Ham quen mat khau
    public void resetPassword(Scanner scanner){
        System.out.println("Quen mat khau");
        System.out.print("Moi ban nhap email: ");
        String email = scanner.nextLine();
        User user = findAccount("email", email);
        if (user == null) {
            System.out.println("Email khong ton tai tren he thong.");
        } else {
            System.out.print("Nhap mat khau moi: ");
            String newPassword = scanner.nextLine();
            if (isValidPassword(newPassword)){
                user.setPassWord(newPassword);
                System.out.println("Doi mat khau thanh cong. Moi ban dang nhap lai.");
            } else {
                System.out.println("Mat khau khong hop le.");
            }
        }
    }

    //Menu sau khi dang nhap
    public void loginMenu(Scanner scanner,User user){
        while (true){
            System.out.println("1. Thay doi username");
            System.out.println("2. Thay doi email");
            System.out.println("3. Thay doi mat khau");
            System.out.println("4. Dang xuat");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Moi ban nhap lua chon:");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    System.out.print("Nhap username moi: ");
                    String newUsername = scanner.nextLine();
                    if (findAccount("username", newUsername) != null) {
                        System.out.println("Username da ton tai, vui long thu lai.");
                    } else {
                        user.setUserName(newUsername);
                        System.out.println("Thay doi username thanh cong.");
                    }
                    break;
                case 2:
                    System.out.print("Nhap email moi:");
                    String newEmail = scanner.nextLine();
                    if (!isValidEmail(newEmail)){
                        System.out.println("Email khong hop le, vui long thu lai.");
                    }
                    else if (findAccount("email", newEmail) != null){
                        System.out.println("Email da ton tai,vui long thu lai.");
                    } else {
                        user.setEmail(newEmail);
                        System.out.println("Thay doi email thanh cong.");
                    }
                    break;
                case 3:
                    System.out.print("Nhap mat khau moi: ");
                    String newPassword  = scanner.nextLine();
                    if (!isValidPassword(newPassword)){
                        System.out.println("Password khong hop le, vui long thu lai.");
                    } else {
                        user.setPassWord(newPassword);
                        System.out.println("Thay doi mat khau thanh cong.");
                    }
                    break;
                case 4:
                    System.out.println("Dang xuat thanh cong.");
                    return;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }

}
