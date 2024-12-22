package menu;

import service.UserService;

import java.util.Scanner;

public class Menu {
    //Hien thi Menu chinh
    public void displayMenu(Scanner scanner,UserService userService) {
        System.out.println("1. Dang nhap");
        System.out.println("2. Dang ky");
        System.out.println("3. Thoat chuong trinh");
        selectMenu(scanner,userService);
    }
    public void selectMenu(Scanner scanner, UserService userService) {
        System.out.print("Moi ban nhap lua chon: ");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                userService.login(scanner);
                break;
            case 2:
                userService.register(scanner);
                break;
            case 3:
                System.exit(0);

        }
    }
}
