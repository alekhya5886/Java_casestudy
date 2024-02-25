import java.util.Scanner;

public class ATM{
    int[] uid = {123, 456, 789};
    int[] upwd = {333, 444, 555};
    float[] balance = {5000.00f, 6000.00f, 7000.00f};
    int euid, eupwd, flag = 0, attempt = 1, rtch = 0, ch;
    Scanner sc = new Scanner(System.in);

    void loginCheck() {
        for (attempt = 1; attempt <= 3; attempt++) {
            System.out.println("Enter ID: ");
            euid = sc.nextInt();
            System.out.println("Enter password: ");
            eupwd = sc.nextInt();

            for (int i = 0; i < uid.length; i++) {
                if (euid == uid[i] && eupwd == upwd[i]) {
                    flag = 1;
                    break;
                } else {
                    System.out.println("Check Your Credentials !!");
                }
            }

            if (flag == 1) {
                break;
            }
        }

        if (attempt > 3) {
            System.out.println("Your account has been blocked for 24 hr");
        }
    }

    void options() {
        if (flag == 1) {
            do {
                System.out.println("1 : Balance check");
                System.out.println("2 : Withdraw");
                System.out.println("3 : Deposit");
                System.out.println("4 : EXIT");

                System.out.print("Enter Your Choice : ");
                ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        int index = getIndex(euid);
                        if (index != -1) {
                            System.out.println("Curr Balance : " + balance[index]);
                        } else {
                            System.out.println("User not found");
                        }
                        break;

                    case 2:
                        int withdrawIndex = getIndex(euid);
                        if (withdrawIndex != -1) {
                            System.out.println("Enter the amount : ");
                            int withdrawAmount = sc.nextInt();
                            if (balance[withdrawIndex] - withdrawAmount >= 0) {
                                balance[withdrawIndex] -= withdrawAmount;
                                System.out.println("Ava Bal : " + balance[withdrawIndex]);
                            } else {
                                System.out.println("Transaction can't be processed");
                            }
                        } else {
                            System.out.println("User not found");
                        }
                        break;

                    case 3:
                        int depositIndex = getIndex(euid);
                        if (depositIndex != -1) {
                            System.out.println("Enter the amount : ");
                            int depositAmount = sc.nextInt();
                            balance[depositIndex] += depositAmount;
                            System.out.println("Ava Bal : " + balance[depositIndex]);
                        } else {
                            System.out.println("User not found");
                        }
                        break;

                    case 4:
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }

                System.out.println("Want to do another transaction 1/0");
                rtch = sc.nextInt();
            } while (rtch == 1);
        }
    }

   int getIndex(int userId) {
        for (int i = 0; i < uid.length; i++) {
            if (userId == uid[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        ATM t = new ATM();
        t.loginCheck();
        t.options();
    }
}
