package org.example.practices;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FileAndDirectoryOperations {

    public static void main(String[] args) throws IOException {
        Path file = Paths.get("src/main/resources/ExampleFile.txt");
        Path directory = Paths.get("src/main/resources/ExampleDirectory");

        // File operations
        createFile(file);
        writeFile(file, "Hello, World!\n");
        appendToFile(file, "Appending this line.\n");
        renameFile(file, Paths.get("src/main/resources/RenamedFile.txt"));
        copyFile(Paths.get("src/main/resources/RenamedFile.txt"), Paths.get("src/main/resources/CopiedFile.txt"));
        moveFile(Paths.get("src/main/resources/CopiedFile.txt"), Paths.get("src/main/resources/MovedFile.txt"));

        // Directory operations
        createDirectory(directory);
        listFiles(directory);
        renameDirectory(directory, Paths.get("src/main/resources/RenamedDirectory"));
        copyDirectory(Paths.get("src/main/resources/RenamedDirectory"), Paths.get("src/main/resources/CopiedDirectory"));
        moveDirectory(Paths.get("src/main/resources/CopiedDirectory"), Paths.get("src/main/resources/MovedDirectory"));
        listDirectories(Paths.get("src/main/resources/MovedDirectory"));
    }

    // Create a file
    public static void createFile(Path file) throws IOException {
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
    }

    // Write content to a file
    public static void writeFile(Path file, String content) throws IOException {
        Files.writeString(file, content, StandardOpenOption.WRITE);
    }

    // Append content to a file
    public static void appendToFile(Path file, String content) throws IOException {
        Files.writeString(file, content, StandardOpenOption.APPEND);
    }

    // Rename a file
    public static void renameFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Copy a file
    public static void copyFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Move a file
    public static void moveFile(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Create a directory
    public static void createDirectory(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
    }

    // List all files in a directory
    public static void listFiles(Path directory) throws IOException {
        if (Files.exists(directory)) {
            try (Stream<Path> files = Files.list(directory)) {
                files.forEach(System.out::println);
            }
        }
    }

    // Rename a directory
    public static void renameDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Copy a directory recursively
    public static void copyDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = target.resolve(source.relativize(dir));
                    Files.createDirectories(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    // Move a directory
    public static void moveDirectory(Path source, Path target) throws IOException {
        if (Files.exists(source)) {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // List all subdirectories in a directory
    public static void listDirectories(Path directory) throws IOException {
        if (Files.exists(directory)) {
            try (Stream<Path> directories = Files.list(directory)) {
                directories.filter(Files::isDirectory).forEach(System.out::println);
            }
        }
    }
}