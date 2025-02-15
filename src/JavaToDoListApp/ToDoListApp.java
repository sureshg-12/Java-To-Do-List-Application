package JavaToDoListApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
	  private static final String FILE_NAME = "tasks.txt";
	    private static List<String> tasks = new ArrayList<>();

	    public static void main(String[] args) {
	        loadTasks();
	        Scanner scanner = new Scanner(System.in);
	        int choice;
	        do {
	            System.out.println("\nTo-Do List Application");
	            System.out.println("1. Add Task");
	            System.out.println("2. View Tasks");
	            System.out.println("3. Delete Task");
	            System.out.println("4. Mark Task as Completed");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    addTask(scanner);
	                    break;
	                case 2:
	                    viewTasks();
	                    break;
	                case 3:
	                    deleteTask(scanner);
	                    break;
	                case 4:
	                    markTaskCompleted(scanner);
	                    break;
	                case 5:
	                    saveTasks();
	                    System.out.println("Exiting application...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        } while (choice != 5);
	    }

	    private static void addTask(Scanner scanner) {
	        System.out.print("Enter task: ");
	        String task = scanner.nextLine();
	        tasks.add(task);
	        System.out.println("Task added successfully.");
	    }

	    private static void viewTasks() {
	        if (tasks.isEmpty()) {
	            System.out.println("No tasks available.");
	        } else {
	            System.out.println("\nYour Tasks:");
	            for (int i = 0; i < tasks.size(); i++) {
	                System.out.println((i + 1) + ". " + tasks.get(i));
	            }
	        }
	    }

	    private static void deleteTask(Scanner scanner) {
	        viewTasks();
	        System.out.print("Enter task number to delete: ");
	        int index = scanner.nextInt() - 1;
	        if (index >= 0 && index < tasks.size()) {
	            tasks.remove(index);
	            System.out.println("Task deleted.");
	        } else {
	            System.out.println("Invalid task number.");
	        }
	    }

	    private static void markTaskCompleted(Scanner scanner) {
	        viewTasks();
	        System.out.print("Enter task number to mark as completed: ");
	        int index = scanner.nextInt() - 1;
	        if (index >= 0 && index < tasks.size()) {
	            tasks.set(index, tasks.get(index) + " (Completed)");
	            System.out.println("Task marked as completed.");
	        } else {
	            System.out.println("Invalid task number.");
	        }
	    }

	    private static void saveTasks() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
	            for (String task : tasks) {
	                writer.write(task);
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving tasks: " + e.getMessage());
	        }
	    }

	    private static void loadTasks() {
	        File file = new File(FILE_NAME);
	        if (file.exists()) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	                String task;
	                while ((task = reader.readLine()) != null) {
	                    tasks.add(task);
	                }
	            } catch (IOException e) {
	                System.out.println("Error loading tasks: " + e.getMessage());
	            }
	        }
	    }
	}

