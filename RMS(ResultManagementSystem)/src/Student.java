
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class Student {
// method to welcome student.
// Student have to enter his/her rollNo
        //Student can view and download his/her result:
        public static <SomeException> void student () {
            TreeMap<String, String> rollNoName = new TreeMap<>();
            rollNoName.put("17sw12", "Hasnain");
            rollNoName.put("17sw56", "Hansa");
            rollNoName.put("17sw61", "Mushal");
            System.out.println("******* Welcome to Student Panel *******");
            System.out.println("Enter your roll No:\'i.e:17sw01\'");
            Scanner ip = new Scanner(System.in);
            String rollNo = ip.nextLine();
            if (rollNoName.containsKey(rollNo)) {
                System.out.println("Welcome " + rollNoName.get(rollNo));
                System.out.println("Want to View the Result or Download?");
                String input = ip.nextLine();

                //if student enter view , application will display his/her rollNo , name , grade

                if (input.equalsIgnoreCase("view")) {
                    String query = "SELECT s.id , s.firstname, r.grade FROM \n" +
                            "student s Inner join Result r\n" +
                            "on s.id = r.studentid\n" + "where s.id = " + "\"" + rollNo + "\"";
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                        Statement statement = connection.createStatement();
                        ResultSet set = statement.executeQuery(query);
                        while (set.next()) {
                            String data = "Student RollNo: " + (set.getString("id") + " \nName: " + set.getString("firstname") + " \nResult: " + set.getString("grade") + "\n");
                            System.out.println(data);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());

                    }

                }
                //if student enter download , application will create new file and  display his/her rollNo , name , grade in it.

                else if (input.equalsIgnoreCase("Download")) {
                    String data = " ";
                    String query = "SELECT s.id , s.firstname, r.grade FROM \n" +
                            "student s Inner join Result r\n" +
                            "on s.id = r.studentid\n" + "where s.id = " + "\"" + rollNo + "\"";

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                        Statement statement = connection.createStatement();
                        ResultSet set = statement.executeQuery(query);
                        while (set.next()) {
                            data = "Student RollNo: " + (set.getString("id") + " \nName: " + set.getString("firstname") + "\nResult: " + set.getString("grade") + "\n");
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exception occur");
                    }

                    try {
                        FileWriter resultFile = new FileWriter("resultFile.txt");
                        resultFile.write(data);
                        resultFile.close();
                        System.out.println("Successfully downloaded file");
                    }
                    catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }
                else {
                    System.out.println("Invalid Input");
                    student();
                }
            }
            else {
                System.out.println("Invalid RollNo:");
                student();
            }

        }
    }
