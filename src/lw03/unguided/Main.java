package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();

        Scanner registrationScanner = new Scanner(
            Main.class.getResourceAsStream("registrations.txt")
        );

        while (registrationScanner.hasNextLine()) {
            String studentId = registrationScanner.nextLine();
            registeredStudents.add(studentId);
        }

        registrationScanner.close();

        Scanner checkinScanner = new Scanner(
                Main.class.getResourceAsStream("checkins.txt")
        );

        int rejectedAttempts = 0;

        System.out.println("===== Event Check-In Results =====");

        while (checkinScanner.hasNextLine()) {
            String studentId = checkinScanner.nextLine();

            if (!registeredStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedInStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedInStudents.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }

        checkinScanner.close();

        int registeredStudentsCount = registeredStudents.size();
        int successfulCheckIns = checkedInStudents.size();
        int absentStudents = registeredStudentsCount - successfulCheckIns;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudentsCount);
        System.out.println("Successful check-ins: " + successfulCheckIns);
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}