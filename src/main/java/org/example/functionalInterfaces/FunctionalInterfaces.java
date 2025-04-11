package org.example.functionalInterfaces;

    import java.util.*;
    import java.util.concurrent.CompletableFuture;
    import java.util.function.*;
    import java.util.stream.IntStream;

    public class FunctionalInterfaces {
        public static void main(String[] args) {
            demonstrateCustomFunctionalInterface();
            demonstratePrebuiltFunctionalInterfaces();
            demonstrateStreamAndOptional();
            demonstrateCompletableFuture();
        }

        private static void demonstrateCustomFunctionalInterface() {
            System.out.println("=== Custom Functional Interface ===");

            // Using a lambda expression to implement the functional interface
            MyFunctionalInterface myFunc = () -> System.out.println("Hello, World!");
            myFunc.execute();

            // Using a default method
            myFunc.defaultMethod();

            // Using a static method
            MyFunctionalInterface.printMessage();
        }

        private static void demonstratePrebuiltFunctionalInterfaces() {
            System.out.println("\n=== Prebuilt Functional Interfaces ===");

            // Predicate and BiPredicate
            Predicate<String> isEmpty = String::isEmpty;
            System.out.println("Is empty: " + isEmpty.test(""));
            BiPredicate<String, String> startsWith = String::startsWith;
            System.out.println("Starts with 'Hello': " + startsWith.test("Hello, World!", "Hello"));

            // Consumer and BiConsumer
            Consumer<String> print = System.out::println;
            print.accept("Hello from Consumer!");
            BiConsumer<String, String> printConcat = (s1, s2) -> System.out.println(s1 + s2);
            printConcat.accept("Hello, ", "World!");

            // Function and BiFunction
            Function<String, Integer> stringLength = String::length;
            System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
            BiFunction<String, String, String> concatenate = String::concat;
            System.out.println("Concatenated: " + concatenate.apply("Hello, ", "World!"));

            // UnaryOperator and BinaryOperator
            UnaryOperator<String> toUpperCase = String::toUpperCase;
            System.out.println("Uppercase: " + toUpperCase.apply("hello"));
            BinaryOperator<Integer> add = Integer::sum;
            System.out.println("Sum: " + add.apply(5, 10));

            // Supplier
            Supplier<String> supplier = () -> "Hello from Supplier!";
            System.out.println(supplier.get());

            // IntSupplier, DoubleSupplier, LongSupplier, BooleanSupplier
            IntSupplier intSupplier = () -> 42;
            System.out.println("Int supplier: " + intSupplier.getAsInt());
            DoubleSupplier doubleSupplier = () -> 3.14;
            System.out.println("Double supplier: " + doubleSupplier.getAsDouble());
            LongSupplier longSupplier = () -> 123456789L;
            System.out.println("Long supplier: " + longSupplier.getAsLong());
            BooleanSupplier booleanSupplier = () -> true;
            System.out.println("Boolean supplier: " + booleanSupplier.getAsBoolean());
        }

        private static void demonstrateStreamAndOptional() {
            System.out.println("\n=== Stream and Optional ===");

            // Using IntStream
            IntStream.range(1, 5).forEach(System.out::println);

            // Using Optional
            Optional<String> optional = Optional.of("Hello");
            optional.ifPresent(System.out::println);

            // Using Stream
            List<String> list = Arrays.asList("A", "B", "C");
            list.stream()
                .map(String::toLowerCase)
                .forEach(System.out::println);
        }

        private static void demonstrateCompletableFuture() {
            System.out.println("\n=== CompletableFuture ===");

            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
            future.thenAccept(System.out::println);

            // Using Stream with CompletableFuture
            List<String> items = Arrays.asList("apple", "banana", "cherry");
            items.stream()
                .filter(item -> item.startsWith("a"))
                .forEach(System.out::println);
        }
    }