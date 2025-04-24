package org.example.practices;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileAndDirectoryOperations {

    private static final Logger logger = Logger.getLogger(FileAndDirectoryOperations.class.getName());

  public static void main(String[] args) {
    // File paths
    Path exampleFile = Paths.get("src/main/resources/ExampleFile.txt");
    Path renamedFile = Paths.get("src/main/resources/RenamedFile.txt");
    Path copiedFile = Paths.get("src/main/resources/CopiedFile.txt");
    Path movedFile = Paths.get("src/main/resources/MovedFile.txt");

    // Directory paths
    Path exampleDirectory = Paths.get("src/main/resources/ExampleDirectory");
    Path renamedDirectory = Paths.get("src/main/resources/RenamedDirectory");
    Path copiedDirectory = Paths.get("src/main/resources/CopiedDirectory");
    Path movedDirectory = Paths.get("src/main/resources/MovedDirectory");

    // File operations
    createFile(exampleFile); // Create a new file
    writeFile(exampleFile, "Hello, World!\n"); // Write content to the file
    appendToFile(exampleFile, "Appending this line.\n"); // Append additional content to the file
    readFile(exampleFile); // Read and print the file content
    renameFile(exampleFile, renamedFile); // Rename the file
    copyFile(renamedFile, copiedFile); // Copy the renamed file
    moveFile(copiedFile, movedFile); // Move the copied file

    // Directory operations
    createDirectory(exampleDirectory); // Create a new directory
    listFiles(exampleDirectory).ifPresent(files -> files.forEach(System.out::println)); // List files in the directory
    renameDirectory(exampleDirectory, renamedDirectory); // Rename the directory
    copyDirectory(renamedDirectory, copiedDirectory); // Copy the renamed directory
    moveDirectory(copiedDirectory, movedDirectory); // Move the copied directory
    listDirectories(movedDirectory).ifPresent(dirs -> dirs.forEach(System.out::println)); // List subdirectories in the moved directory
}
    public static void createFile(Path file) {
        try {
            if (checkExists(file, true)) {
                Files.createFile(file);
                logger.info("File created: " + file);
            }
        } catch (IOException e) {
            throw new FileOperationException("Error creating file: " + file, e);
        }
    }

    public static void writeFile(Path file, String content) {
        try {
            Files.writeString(file, content, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new FileOperationException("Error writing to file: " + file, e);
        }
    }

    public static void appendToFile(Path file, String content) {
        try {
            Files.writeString(file, content, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileOperationException("Error appending to file: " + file, e);
        }
    }

    public static void readFile(Path file) {
        if (Files.exists(file)) {
            try (Stream<String> lines = Files.lines(file)) {
                lines.forEach(System.out::println);
            } catch (IOException e) {
                throw new FileOperationException("Error reading file: " + file, e);
            }
        } else {
            logger.warning("File does not exist: " + file);
        }
    }

    public static Optional<Stream<Path>> listFiles(Path directory) {
        if (Files.exists(directory)) {
            try {
                return Optional.of(Files.list(directory));
            } catch (IOException e) {
                throw new FileOperationException("Error listing files in directory: " + directory, e);
            }
        }
        return Optional.empty();
    }

    public static void renameFile(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File renamed from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error renaming file: " + source, e);
        }
    }

    public static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File copied from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error copying file: " + source, e);
        }
    }

    public static void moveFile(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File moved from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error moving file: " + source, e);
        }
    }

    public static void createDirectory(Path directory) {
        try {
            if (checkExists(directory, false)) {
                Files.createDirectory(directory);
                logger.info("Directory created: " + directory);
            }
        } catch (IOException e) {
            throw new FileOperationException("Error creating directory: " + directory, e);
        }
    }

    public static void renameDirectory(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Directory renamed from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error renaming directory: " + source, e);
        }
    }

    public static void copyDirectory(Path source, Path target) {
        try {
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
            logger.info("Directory copied from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error copying directory: " + source, e);
        }
    }

    public static void moveDirectory(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Directory moved from " + source + " to " + target);
        } catch (IOException e) {
            throw new FileOperationException("Error moving directory: " + source, e);
        }
    }

    public static Optional<Stream<Path>> listDirectories(Path directory) {
        if (Files.exists(directory)) {
            try {
                return Optional.of(Files.list(directory).filter(Files::isDirectory));
            } catch (IOException e) {
                throw new FileOperationException("Error listing directories in: " + directory, e);
            }
        }
        return Optional.empty();
    }

    private static boolean checkExists(Path path, boolean isFile) {
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            logger.info((isFile ? "File" : "Directory") + " already exists: " + path);
            return false;
        }
        return true;
    }
}