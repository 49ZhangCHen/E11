import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DiaryWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "Diary.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Enter your diary entries (type END to finish):");

            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Diary saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing diary: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}    