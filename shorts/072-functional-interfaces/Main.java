void main() {
    Predicate<Integer> isAdult = age -> age >= 18;
    Function<Integer, Integer> doubleIt = x -> x * 2;
    Supplier<String> greet = () -> "Hello";
    Consumer<String> shout = s -> IO.println(s + "!");
    IO.println(isAdult.test(20));
    IO.println(doubleIt.apply(21));
    shout.accept(greet.get());
}
