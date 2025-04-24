package org.example.practices;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FileAndDirectoryOperations {

    public static void main(String[] args) throws IOException {
        // Define the file path using a platform-independent approach
        Path file = Paths.get("src/main/resources/ExampleFile.txt");

        // Define the directory path using a platform-independent approach
        Path directory = Paths.get("src/main/resources/ExampleDirectory");

        // File operations
        createFile(file); // Create a file if it does not already exist
        writeFile(file, "Hello, World!\n"); // Write content to the file (overwrites existing content)
        appendToFile(file, "Appending this line.\n"); // Append additional content to the file
        readFile(file); // Read and print the content of the file
        renameFile(file, Paths.get("src/main/resources/RenamedFile.txt")); // Rename the file to a new name
        copyFile(Paths.get("src/main/resources/RenamedFile.txt"), Paths.get("src/main/resources/CopiedFile.txt")); // Copy the renamed file to a new location
        moveFile(Paths.get("src/main/resources/CopiedFile.txt"), Paths.get("src/main/resources/MovedFile.txt")); // Move the copied file to a new location

        // Directory operations
        createDirectory(directory); // Create a directory if it does not already exist
        listFiles(directory); // List all files in the specified directory
        renameDirectory(directory, Paths.get("src/main/resources/RenamedDirectory")); // Rename the directory to a new name
        copyDirectory(Paths.get("src/main/resources/RenamedDirectory"), Paths.get("src/main/resources/CopiedDirectory")); // Copy the renamed directory to a new location
        moveDirectory(Paths.get("src/main/resources/CopiedDirectory"), Paths.get("src/main/resources/MovedDirectory")); // Move the copied directory to a new location
        listDirectories(Paths.get("src/main/resources/MovedDirectory")); // List all subdirectories in the specified directory
    }

    // Example of exception handling for file creation
    public static void createFile(Path file) {
        try {
            if (!Files.exists(file)) { // Check if the file already exists
                Files.createFile(file); // Create the file
            } else {
                System.out.println("File already exists: " + file);
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + file);
            e.printStackTrace();
        }
    }

    // Write content to a file (overwrites existing content)
    public static void writeFile(Path file, String content) throws IOException {
        Files.writeString(file, content, StandardOpenOption.WRITE); // Write the specified content to the file
    }

    // Append content to a file
    public static void appendToFile(Path file, String content) throws IOException {
        Files.writeString(file, content, StandardOpenOption.APPEND); // Append the specified content to the file
    }

    // Read and print the content of a file using try-with-resources
    public static void readFile(Path file) {
        if (Files.exists(file)) { // Check if the file exists
            try (Stream<String> lines = Files.lines(file)) { // Open the file stream
                lines.forEach(System.out::println); // Read and print each line of the file
            } catch (IOException e) {
                System.err.println("Error reading file: " + file);
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist: " + file);
        }
    }

    // Rename a file
    public static void renameFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source file exists
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING); // Rename the file, replacing the target if it exists
        }
    }

    // Copy a file
    public static void copyFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source file exists
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); // Copy the file, replacing the target if it exists
        }
    }

    // Move a file
    public static void moveFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source file exists
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING); // Move the file, replacing the target if it exists
        }
    }

    // Create a directory if it does not exist
    public static void createDirectory(Path directory) throws IOException {
        if (!Files.exists(directory)) { // Check if the directory already exists
            Files.createDirectory(directory); // Create the directory
        }
    }

    // List all files in a directory
    public static void listFiles(Path directory) throws IOException {
        if (Files.exists(directory)) { // Check if the directory exists
            try (Stream<Path> files = Files.list(directory)) { // Open a stream of files in the directory
                files.forEach(System.out::println); // Print each file path
            }
        }
    }

    // Rename a directory
    public static void renameDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source directory exists
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING); // Rename the directory, replacing the target if it exists
        }
    }

    // Copy a directory recursively
    public static void copyDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source directory exists
            Files.walkFileTree(source, new SimpleFileVisitor<>() { // Traverse the directory tree
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = target.resolve(source.relativize(dir)); // Resolve the target directory path
                    Files.createDirectories(targetDir); // Create the target directory
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING); // Copy each file
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    // Move a directory
    public static void moveDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) { // Check if the source directory exists
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING); // Move the directory, replacing the target if it exists
        }
    }

    // List all subdirectories in a directory
    public static void listDirectories(Path directory) throws IOException {
        if (Files.exists(directory)) { // Check if the directory exists
            try (Stream<Path> directories = Files.list(directory)) { // Open a stream of paths in the directory
                directories.filter(Files::isDirectory).forEach(System.out::println); // Print each subdirectory path
            }
        }
    }
}