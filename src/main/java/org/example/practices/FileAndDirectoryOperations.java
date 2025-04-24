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
        Path file = Paths.get("src/main/resources/ExampleFile.txt");
        Path directory = Paths.get("src/main/resources/ExampleDirectory");

        createFile(file);
        writeFile(file, "Hello, World!\n");
        appendToFile(file, "Appending this line.\n");
        readFile(file);
        renameFile(file, Paths.get("src/main/resources/RenamedFile.txt"));
        copyFile(Paths.get("src/main/resources/RenamedFile.txt"), Paths.get("src/main/resources/CopiedFile.txt"));
        moveFile(Paths.get("src/main/resources/CopiedFile.txt"), Paths.get("src/main/resources/MovedFile.txt"));

        createDirectory(directory);
        listFiles(directory).ifPresent(files -> files.forEach(System.out::println));
        renameDirectory(directory, Paths.get("src/main/resources/RenamedDirectory"));
        copyDirectory(Paths.get("src/main/resources/RenamedDirectory"), Paths.get("src/main/resources/CopiedDirectory"));
        moveDirectory(Paths.get("src/main/resources/CopiedDirectory"), Paths.get("src/main/resources/MovedDirectory"));
        listDirectories(Paths.get("src/main/resources/MovedDirectory")).ifPresent(dirs -> dirs.forEach(dir -> System.out.println(dir)));
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