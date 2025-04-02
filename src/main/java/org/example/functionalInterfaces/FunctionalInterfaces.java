package org.example.functionalInterfaces;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Using a lambda expression to implement the functional interface
        MyFunctionalInterface myFunc = () -> System.out.println("Hello, World!");
        myFunc.doSomething();

        // Using a method reference to call a default method
        myFunc.defaultMethod();

        // Using a method reference to call a static method
        MyFunctionalInterface.printMessage();

        // Prebuilt functional interfaces
        // Using Predicate
        java.util.function.Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Is empty: " + isEmpty.test(""));
        // Using BiPredicate
        java.util.function.BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("Starts with 'Hello': " + startsWith.test("Hello, World!", "Hello"));


        // Using Consumer
        java.util.function.Consumer<String> print = System.out::println;
        print.accept("Hello from Consumer!");
        // Using BiConsumer
        java.util.function.BiConsumer<String, String> printConcat = (s1, s2) -> System.out.println(s1 + s2);
        printConcat.accept("Hello, ", "World!");

        // Using ToIntFunction
        java.util.function.ToIntFunction<String> stringToInt = String::length;
        System.out.println("Length of 'Hello': " + stringToInt.applyAsInt("Hello"));
        // Using ToDoubleFunction
        java.util.function.ToDoubleFunction<String> stringToDouble = Double::parseDouble;
        System.out.println("String to double: " + stringToDouble.applyAsDouble("3.14"));
        // Using ToLongFunction
        java.util.function.ToLongFunction<String> stringToLong = Long::parseLong;
        System.out.println("String to long: " + stringToLong.applyAsLong("123456789"));

        // Using IntToDoubleFunction
        java.util.function.IntToDoubleFunction intToDouble = (int value) -> (double) value;
        System.out.println("Int to double: " + intToDouble.applyAsDouble(5));
        // Using DoubleToIntFunction
        java.util.function.DoubleToIntFunction doubleToInt = (double value) -> (int) value;
        System.out.println("Double to int: " + doubleToInt.applyAsInt(3.14));
        // Using LongToIntFunction
        java.util.function.LongToIntFunction longToInt = (long value) -> (int) value;
        System.out.println("Long to int: " + longToInt.applyAsInt(123456789L));
        // Using IntToLongFunction
        java.util.function.IntToLongFunction intToLong = (int value) -> (long) value;
        System.out.println("Int to long: " + intToLong.applyAsLong(5));
        // Using DoubleToLongFunction
        java.util.function.DoubleToLongFunction doubleToLong = (double value) -> (long) value;
        System.out.println("Double to long: " + doubleToLong.applyAsLong(3.14));
        // Using LongToDoubleFunction
        java.util.function.LongToDoubleFunction longToDouble = (long value) -> (double) value;
        System.out.println("Long to double: " + longToDouble.applyAsDouble(123456789L));
        // Using IntUnaryOperator
        java.util.function.IntUnaryOperator intUnaryOperator = (int value) -> value * 2;
        System.out.println("Int unary operator: " + intUnaryOperator.applyAsInt(5));
        // Using IntBinaryOperator
        java.util.function.IntBinaryOperator intBinaryOperator = (int value1, int value2) -> value1 + value2;
        System.out.println("Int binary operator: " + intBinaryOperator.applyAsInt(5, 10));
        // Using DoubleUnaryOperator
        java.util.function.DoubleUnaryOperator doubleUnaryOperator = (double value) -> value * 2;
        System.out.println("Double unary operator: " + doubleUnaryOperator.applyAsDouble(3.14));
        // Using DoubleBinaryOperator
        java.util.function.DoubleBinaryOperator doubleBinaryOperator = (double value1, double value2) -> value1 + value2;
        System.out.println("Double binary operator: " + doubleBinaryOperator.applyAsDouble(3.14, 2.71));
        // Using LongUnaryOperator
        java.util.function.LongUnaryOperator longUnaryOperator = (long value) -> value * 2;
        System.out.println("Long unary operator: " + longUnaryOperator.applyAsLong(123456789L));
        // Using LongBinaryOperator
        java.util.function.LongBinaryOperator longBinaryOperator = (long value1, long value2) -> value1 + value2;
        System.out.println("Long binary operator: " + longBinaryOperator.applyAsLong(123456789L, 987654321L));
        // Using IntConsumer
        java.util.function.IntConsumer intConsumer = (int value) -> System.out.println("Int consumer: " + value);
        intConsumer.accept(5);
        // Using DoubleConsumer
        java.util.function.DoubleConsumer doubleConsumer = (double value) -> System.out.println("Double consumer: " + value);
        doubleConsumer.accept(3.14);
        // Using LongConsumer
        java.util.function.LongConsumer longConsumer = (long value) -> System.out.println("Long consumer: " + value);
        longConsumer.accept(123456789L);
        // Using ObjIntConsumer
        java.util.function.ObjIntConsumer<String> objIntConsumer = (String str, int value) -> System.out.println(str + ": " + value);
        objIntConsumer.accept("ObjIntConsumer", 5);
        // Using ObjDoubleConsumer
        java.util.function.ObjDoubleConsumer<String> objDoubleConsumer = (String str, double value) -> System.out.println(str + ": " + value);
        objDoubleConsumer.accept("ObjDoubleConsumer", 3.14);
        // Using ObjLongConsumer
        java.util.function.ObjLongConsumer<String> objLongConsumer = (String str, long value) -> System.out.println(str + ": " + value);
        objLongConsumer.accept("ObjLongConsumer", 123456789L);
        // Using IntSupplier
        java.util.function.IntSupplier intSupplier = () -> 42;
        System.out.println("Int supplier: " + intSupplier.getAsInt());
        // Using DoubleSupplier
        java.util.function.DoubleSupplier doubleSupplier = () -> 3.14;
        System.out.println("Double supplier: " + doubleSupplier.getAsDouble());
        // Using LongSupplier
        java.util.function.LongSupplier longSupplier = () -> 123456789L;
        System.out.println("Long supplier: " + longSupplier.getAsLong());
        // Using BooleanSupplier
        java.util.function.BooleanSupplier booleanSupplier = () -> true;
        System.out.println("Boolean supplier: " + booleanSupplier.getAsBoolean());






        // Using Supplier
        java.util.function.Supplier<String> supplier = () -> "Hello from Supplier!";
        System.out.println(supplier.get());


        // Using Function
        java.util.function.Function<String, Integer> stringLength = String::length;
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        // Using BiFunction
        java.util.function.BiFunction<String, String, String> concatenate = String::concat;
        System.out.println("Concatenated: " + concatenate.apply("Hello, ", "World!"));


        // Using UnaryOperator
        java.util.function.UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println("Uppercase: " + toUpperCase.apply("hello"));
        // Using BinaryOperator
        java.util.function.BinaryOperator<Integer> add = Integer::sum;
        System.out.println("Sum: " + add.apply(5, 10));



        // Using IntStream
        java.util.stream.IntStream.range(1, 5)
                .forEach(System.out::println); // Prints numbers from 1 to 4
        // Using Optional
        java.util.Optional<String> optional = java.util.Optional.of("Hello");
        optional.ifPresent(System.out::println); // Prints "Hello"
        // Using Stream
        java.util.List<String> list = java.util.Arrays.asList("A", "B", "C");
        list.stream()
                .map(String::toLowerCase)
                .forEach(System.out::println); // Prints "a", "b", "c"
        // Using CompletableFuture
        java.util.concurrent.CompletableFuture<String> future = java.util.concurrent.CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
        future.thenAccept(System.out::println); // Prints "Hello from CompletableFuture!"
        // Using Optional with method reference
        java.util.Optional<String> optionalValue = java.util.Optional.of("Hello");
        optionalValue.ifPresent(value -> System.out.println("Optional value: " + value)); // Prints "Optional value: Hello"
        // Using Stream with method reference
        java.util.List<String> names = java.util.Arrays.asList("Alice", "Bob", "Charlie");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println); // Prints "Alice"
        // Using CompletableFuture with method reference
        java.util.concurrent.CompletableFuture<String> futureValue = java.util.concurrent.CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
        futureValue.thenAccept(value -> System.out.println("Future value: " + value)); // Prints "Future value: Hello from CompletableFuture!"
        // Using Stream with method reference
        java.util.List<String> numbers = java.util.Arrays.asList("1", "2", "3");
        numbers.stream()
                .map(Integer::parseInt)
                .forEach(System.out::println); // Prints 1, 2, 3
        // Using CompletableFuture with method reference
        java.util.concurrent.CompletableFuture<String> futureResult = java.util.concurrent.CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
        futureResult.thenAccept(result -> System.out.println("Future result: " + result)); // Prints "Future result: Hello from CompletableFuture!"
        // Using Stream with method reference
        java.util.List<String> items = java.util.Arrays.asList("apple", "banana", "cherry");
        items.stream()
                .filter(item -> item.startsWith("a"))
                .forEach(System.out::println); // Prints "apple"
        // Using CompletableFuture with method reference
        java.util.concurrent.CompletableFuture<String> futureItem = java.util.concurrent.CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
        futureItem.thenAccept(item -> System.out.println("Future item: " + item)); // Prints "Future item: Hello from CompletableFuture!"


    }

}
