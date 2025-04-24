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

        // Create a file if it does not exist
        if (!Files.exists(file)) {
            Files.createFile(file); // Ensure the file is created only if it doesn't already exist
        }

        // Write content to the file (overwrites existing content)
        Files.writeString(file, "Hello, World!\n", StandardOpenOption.WRITE); // Use StandardOpenOption.WRITE for overwriting

        // Append content to the file
        Files.writeString(file, "Appending this line.\n", StandardOpenOption.APPEND); // Use StandardOpenOption.APPEND to add content

        // Rename a file
        Path renamedFile = Paths.get("src/main/resources/RenamedFile.txt");
        if (Files.exists(file)) {
            Files.move(file, renamedFile, StandardCopyOption.REPLACE_EXISTING); // Rename the file, replacing if it already exists
        }

        // Copy a file
        Path copiedFile = Paths.get("src/main/resources/CopiedFile.txt");
        if (Files.exists(renamedFile)) {
            Files.copy(renamedFile, copiedFile, StandardCopyOption.REPLACE_EXISTING); // Copy the file, replacing if it already exists
        }

        // Move a file
        Path movedFile = Paths.get("src/main/resources/MovedFile.txt");
        if (Files.exists(copiedFile)) {
            Files.move(copiedFile, movedFile, StandardCopyOption.REPLACE_EXISTING); // Move the file, replacing if it already exists
        }

        // List all files in a directory
        if (Files.exists(directory)) {
            try (Stream<Path> files = Files.list(directory)) {
                files.forEach(System.out::println); // Print all files in the directory
            }
        }

        // Create a directory if it does not exist
        if (!Files.exists(directory)) {
            Files.createDirectory(directory); // Create the directory only if it doesn't already exist
        }

        // Rename a directory
        Path renamedDirectory = Paths.get("src/main/resources/RenamedDirectory");
        if (Files.exists(directory)) {
            Files.move(directory, renamedDirectory, StandardCopyOption.REPLACE_EXISTING); // Rename the directory, replacing if it already exists
        }

        // Copy a directory recursively
        Path copiedDirectory = Paths.get("src/main/resources/CopiedDirectory");
        if (Files.exists(renamedDirectory)) {
            Files.walkFileTree(renamedDirectory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = copiedDirectory.resolve(renamedDirectory.relativize(dir));
                    Files.createDirectories(targetDir); // Create target directories
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, copiedDirectory.resolve(renamedDirectory.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        // Move a directory
        Path movedDirectory = Paths.get("src/main/resources/MovedDirectory");
        if (Files.exists(copiedDirectory)) {
            Files.move(copiedDirectory, movedDirectory, StandardCopyOption.REPLACE_EXISTING); // Move the directory, replacing if it already exists
        }

        // List all subdirectories in a directory
        if (Files.exists(movedDirectory)) {
            try (Stream<Path> directories = Files.list(movedDirectory)) {
                directories.filter(Files::isDirectory).forEach(System.out::println); // Print all subdirectories
            }
        }
    }
}