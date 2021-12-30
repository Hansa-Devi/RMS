
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public  abstract class Admin extends Student {
        //admin method to welcome admin and show all records in database and
        // select option(action) to perform
        //admin have to enter correct(admin123) password

        static public void admin () {
            System.out.println("**** Welcome to admin Panel ****");
            System.out.println("Enter password:");
            Scanner ip = new Scanner(System.in);
            String pass = ip.nextLine();
            String password="";

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                Statement statement = connection.createStatement();
                ResultSet rs =  statement.executeQuery("SELECT password FROM login_admin WHERE user = \"adm\"");
                while (rs.next())
                password = rs.getString(1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (pass.equals(password)) {
                System.out.println("**** Welcome to admin Panel ****");
                int noOfStudent = 0;
                int noOfSubject = 0;
                int noOfClasses = 0;
                int noOfResult = 0;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    ResultSet rs=statement.executeQuery("SELECT COUNT(idsubject) FROM subject;");
                    rs.next();
                    noOfSubject = rs.getByte(1);
                    ResultSet rs2=statement.executeQuery("SELECT COUNT(batch) FROM class;");
                    rs2.next();
                    noOfClasses = rs2.getByte(1);
                    ResultSet rs3=statement.executeQuery("SELECT COUNT(studentid) FROM result;");
                    rs3.next();
                    noOfResult = rs3.getByte(1);
                    ResultSet rs4=statement.executeQuery("SELECT COUNT(id) FROM student;");
                    rs4.next();
                    noOfStudent = rs4.getByte(1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("No of register user:"+noOfStudent);
                System.out.println("No of subject list:"+noOfSubject);
                System.out.println("Total no of classes:"+noOfClasses);
                System.out.println("Result Declared:"+noOfResult);
                System.out.println("");
                System.out.println("Select Option:");
                options();
            }
            else {
                System.out.println("Invalid Password:");
            }
        }

        // options(action) list admin can perform:
        public static void options() {
            System.out.println("1: Add/Update Class:");
            System.out.println("2: Add/Update Subject:");
            System.out.println("3: Add/Update Subject Combination");
            System.out.println("4: Register/Edit Student");
            System.out.println("5: Declare/edit Result");
            System.out.println("6: Change Password");
            System.out.println("7: Exit");
            Scanner inp = new Scanner(System.in);
            int op = inp.nextInt();
            switch (op) {
                case 1:
                    classs();
                    break;
                case 2:
                    subject();
                    break;
                case 3:
                    subjectCombination();
                    break;
                case 4:
                    studentUp();
                    break;
                case 5:
                    result();
                    break;
                case 6:
                    changePassword();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid Input");
                    options();
            }
        }

        //option(action) 1: add/update class
        public static void classs () {
            System.out.println("Add/Update Class");
            System.out.println("You want add new Class OR Update Class");
            Scanner ipClass = new Scanner(System.in);
            String addUpCLass = ipClass.nextLine();
            if (addUpCLass.equalsIgnoreCase("Add"))
                addNewClass();
            else if (addUpCLass.equalsIgnoreCase("Update"))
                updateClass();
            else {
                System.out.println("Invalid Input");
                classs();
            }

        }

        // add new class(batch)
        public static void addNewClass () {
            System.out.println("Enter new batch/class");
            Scanner classAdd = new Scanner(System.in);
            int newClass = classAdd.nextInt();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO class\n" +
                        "VALUES (" + newClass + ")");
                System.out.println("Class have successfully added");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update class(batch)
        public static void updateClass () {
            System.out.println("You want to delete class/batch?");
            Scanner ip = new Scanner(System.in);
            String update = ip.nextLine();
            if (update.equalsIgnoreCase("yes")) {
                System.out.println("Enter class/batch to delete");
                Scanner classDel = new Scanner(System.in);
                int delClass = classDel.nextInt();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("DELETE FROM class WHERE batch = " + delClass);
                    System.out.println("Class have successfully deleted");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                System.out.println("Invalid input");
                updateClass();
            }

        }


        // option(action) 2 add/update/edit Subject
        public static void subject () {
            System.out.println("Add/Update Subject");
            System.out.println("You want add new Subject OR Update Subject");
            Scanner ipSub = new Scanner(System.in);
            String addUpSubj = ipSub.nextLine();
            if (addUpSubj.equalsIgnoreCase("Add"))
                addNewSubj();
            else if (addUpSubj.equalsIgnoreCase("Update"))
                updateSubj();
            else {
                System.out.println("Invalid Input");
                student();
            }
        }

        // add new subject
        public static void addNewSubj () {
            System.out.println("Enter new subject name");
            Scanner subAdd = new Scanner(System.in);
            String subName = subAdd.nextLine();
            System.out.println("Enter new subject id");
            String subId = subAdd.nextLine();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO subject (idsubject, subjectname)" + "VALUES(\""+ subId + "\",\"" + subName + "\")");
                System.out.println("Subject have successfully added");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update subject
        public static void updateSubj () {
            System.out.println("You want to edit/delete subject");
            Scanner subupd = new Scanner(System.in);
            String delEdit = subupd.nextLine();
            if (delEdit.equalsIgnoreCase("delete")){
                System.out.println("Enter subject name:");
                String subDelName = subupd.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("DELETE from subject where subjectname = \""+subDelName+"\"");
                    System.out.println("Subject have successfully deleted");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (delEdit.equalsIgnoreCase("edit")){
                System.out.println("Enter subject name to edit:");
                String subNewName = subupd.nextLine();
                System.out.println("Enter subject id:");
                String subID = subupd.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("UPDATE subject SET subjectname =\""+subNewName+"\"WHERE idsubject =\""+subID+"\"");
                    System.out.println("Subject name have successfully edited with id : "+subID);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Invalid input");
                updateSubj();
            }
        }

        // option(action) 3 add/update SubjectCombination
        public static void subjectCombination () {
            System.out.println("Add/Update SubjectCombination");
            System.out.println("You want add new SubjectCombination OR Update SubjectCombination");
            Scanner ipSubCom = new Scanner(System.in);
            String addUpSubjCombo = ipSubCom.nextLine();
            if (addUpSubjCombo.equalsIgnoreCase("Add"))
                addNewSubjCombo();
            else if (addUpSubjCombo.equalsIgnoreCase("Update"))
                updateSubjCombo();
            else {
                System.out.println("Invalid Input");
                subjectCombination();
            }
        }

        // add new subjectCombination
        public static void addNewSubjCombo () {
            System.out.println("Enter new batch");
            Scanner subCombo = new Scanner(System.in);
            int batchNo = subCombo.nextInt();
            System.out.println("Enter Subject ID: ");
            Scanner subComboo = new Scanner(System.in);
            String subId = subComboo.nextLine();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                Statement statement = connection.createStatement();
                statement.executeUpdate("UPDATE subject SET batch_no = "+batchNo+" WHERE idsubject =\"" +subId+ "\"");
                System.out.println("New SubjectCombination have added");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update subjectCombination
        public static void updateSubjCombo () {
            System.out.println("You want to edit/delete subject_combination");
            Scanner ip = new Scanner(System.in);
            String subIp = ip.nextLine();
            if (subIp.equalsIgnoreCase("delete")){
                System.out.println("Enter Subject ID: ");
                Scanner subComboo = new Scanner(System.in);
                String subId = subComboo.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("UPDATE subject SET batch_no = \" \" WHERE idsubject =\"" +subId+ "\"");
                    System.out.println("SubjectCombination have deleted");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (subIp.equalsIgnoreCase("edit")){
                System.out.println("Enter new batch");
                Scanner subCombo = new Scanner(System.in);
                int batchNo = subCombo.nextInt();
                System.out.println("Enter Subject ID: ");
                Scanner subComboo = new Scanner(System.in);
                String subId = subComboo.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("UPDATE subject SET batch_no = "+batchNo+" WHERE idsubject =\"" +subId+ "\"");
                    System.out.println("SubjectCombination have been updated");

                } catch (Exception e) {
                    System.out.println( e.getStackTrace()[0]);
                }
            }
            else {
                System.out.println("Invalid input");
                updateSubjCombo();
            }
        }


        // option(action) 4 register/update Student
        public static void studentUp () {
            System.out.println("Add/Update Student");
            System.out.println("You want register new Student OR Update Student Data");
            Scanner ipStd = new Scanner(System.in);
            String regStd = ipStd.nextLine();
            if (regStd.equalsIgnoreCase("Register"))
                registerStd();
            else if (regStd.equalsIgnoreCase("Update"))
                updateStd();
            else
                System.out.println("Invalid Input");
        }

        // register new Student
        public static void registerStd () {
            System.out.println("Enter Student rollNo i.e;17sw12");
            Scanner stdInfo = new Scanner(System.in);
            String rollNO = stdInfo.nextLine();
            System.out.println("Enter Student name");
            Scanner stdInfo3 = new Scanner(System.in);
            String name = stdInfo3.nextLine();
            System.out.println("Enter Student Father Name");
            Scanner stdInfo4 = new Scanner(System.in);
            String fatherName = stdInfo4.nextLine();
            System.out.println("Enter Subject ID: i.e; batch_no = 17");
            Scanner stdInfo2 = new Scanner(System.in);
            int subId = stdInfo2.nextInt();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO student "+
                        "VALUES (\""+rollNO+"\",\""+name+"\",\""+fatherName+"\","+subId+")");
                System.out.println("Student have registered");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update Student Data
        public static void updateStd () {
            System.out.println("You want to edit/delete student info");
            Scanner ip = new Scanner(System.in);
            String subIp = ip.nextLine();
            if (subIp.equalsIgnoreCase("delete")) {
                System.out.println("Enter Subject Roll No: ");
                Scanner std = new Scanner(System.in);
                String stdRollNo = std.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("DELETE FROM student WHERE id = \"" + stdRollNo + "\"");
                    System.out.println("Student data have successfully deleted");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (subIp.equalsIgnoreCase("edit")) {
                System.out.println("Enter Student RollN0:");
                Scanner std = new Scanner(System.in);
                String stdRollNo = std.nextLine();
                System.out.println("Enter Student Name: ");
                Scanner std2 = new Scanner(System.in);
                String stdName = std2.nextLine();
                System.out.println("Enter Student Father Name: ");
                Scanner std3 = new Scanner(System.in);
                String stdFatherName = std3.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("UPDATE student SET firstName = \"" +stdName + "\",fatherNAme =\"" + stdFatherName + "\""+
                                    "WHERE id = \""+stdRollNo+"\"");
                    System.out.println("Student having rollNo "+stdRollNo+" data have successfully updated.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

            // option(action) 5 update/declare result
            public static void result () {
                System.out.println("Add/Update Result");
                System.out.println("You want update/declare result?");
                Scanner ipResult = new Scanner(System.in);
                String result = ipResult.nextLine();
                if (result.equalsIgnoreCase("Declare"))
                    declareResult();
                else if (result.equalsIgnoreCase("Update"))
                    updateResult();
                else {
                    System.out.println("Invalid Input");
                    result();
                }
            }
            //declare Result
            public static void declareResult () {
                System.out.println("Enter new student Roll No:");
                Scanner stdRlt = new Scanner(System.in);
                String stdRollNo = stdRlt.nextLine();
                System.out.println("Enter new subject batch");
                int stdBatch = stdRlt.nextInt();
                System.out.println("Enter Total Marks:");
                int totalMArks = stdRlt.nextInt();
                System.out.println("Enter Obtained Marks:");
                int obtMarks = stdRlt.nextInt();
                System.out.println("Enter Percentage:");
                int per = stdRlt.nextInt();
                Scanner stdRlt3 = new Scanner(System.in);
                System.out.println("Enter Result:");
                String result = stdRlt3.nextLine();
                Scanner stdRlt2 = new Scanner(System.in);
                System.out.println("Enter Grade:");
                String grade = stdRlt2.nextLine();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("INSERT INTO result VALUES(\""+stdRollNo + "\"," +stdBatch+ "," +totalMArks+ "," +obtMarks+ "," +per+
                            ",\"" +result+ "\",\"" +grade+ "\")");
                    System.out.println("Student " +stdRollNo+" result have declared");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //update Result
            public static void updateResult () {
                System.out.println("You want to delete/update student result?");
                Scanner stdIp = new Scanner(System.in);
                String stdRlt = stdIp.nextLine();
                if (stdRlt.equalsIgnoreCase("update")){
                    System.out.println("Enter new student Roll No:");
                    Scanner stdRlt2 = new Scanner(System.in);
                    String stdRollNo = stdRlt2.nextLine();
                    System.out.println("Enter Total Marks:");
                    int totalMArks = stdRlt2.nextInt();
                    System.out.println("Enter Obtained Marks:");
                    int obtMarks = stdRlt2.nextInt();
                    System.out.println("Enter Percentage:");
                    int per = stdRlt2.nextInt();
                    Scanner stdRlt3 = new Scanner(System.in);
                    System.out.println("Enter Result:");
                    String result = stdRlt3.nextLine();
                    Scanner stdRlt4 = new Scanner(System.in);
                    System.out.println("Enter Grade:");
                    String grade = stdRlt4.nextLine();
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("UPDATE result SET total_marks = "+totalMArks+ ",obtained_marks = " +obtMarks+ ", percentage = " +per+
                                ", result = \"" +result+ "\" ,grade =  \"" +grade+ "\" WHERE studentid = \""+stdRollNo+"\"");
                        System.out.println("Student " +stdRollNo+" result have updated");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (stdRlt.equalsIgnoreCase("delete")){
                    System.out.println("Enter new student Roll No:");
                    Scanner stdRlt3 = new Scanner(System.in);
                    String stdRollNo = stdRlt3.nextLine();
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("DELETE FROM result WHERE studentid = \"" +stdRollNo+ "\"");
                        System.out.println("Student " +stdRollNo+" result have deleted");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Invalid input");
                    updateResult();
                }

            }



             //option(action) 6 edit password
             public static void changePassword() {
                  System.out.println("Enter Your Password:");
                  Scanner passWord = new Scanner(System.in);
                  String passInp = passWord.nextLine();
                  String dataPass="";

                 try {
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                     Statement statement = connection.createStatement();
                     ResultSet rs =  statement.executeQuery("SELECT password FROM login_admin WHERE user = \"admin\" ");
                     while (rs.next())
                     dataPass = rs.getString(1);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

                  if (passInp.equalsIgnoreCase(dataPass)){
                       System.out.println("Enter new Password:");
                       String newPassword = passWord.nextLine();
                      try {
                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practie", "root", "root56");
                          Statement statement = connection.createStatement();
                          statement.executeUpdate("UPDATE login_admin SET password = \""+ newPassword+ "\"  WHERE user = \"admin\"");
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                  }
                  else {
                        System.out.println("Invalid Password");
                        changePassword();
                  }

             }

}


