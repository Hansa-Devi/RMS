import java.util.Scanner;

//Result Management system:
//main class to run this application:
// Two Panels (admin/student)

public class MainLogin extends Admin{
    public static void main(String[] args) {
        System.out.println("*****Login Page*****");
        System.out.println("Enter which panel to login, admin or student:");
        Scanner ip = new Scanner(System.in);
        String panel = ip.nextLine();
        if (panel.equalsIgnoreCase("admin"))
            admin();
        else if (panel.equalsIgnoreCase("student"))
            student();
        else
            System.out.println("Invalid User");
    }
}
