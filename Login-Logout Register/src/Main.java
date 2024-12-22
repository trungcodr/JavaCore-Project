import entites.User;
import menu.Menu;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        UserService userService = new UserService();
        Menu menu = new Menu();
        do {
            menu.displayMenu(scanner,userService);
        }
        while (true);
    }
}