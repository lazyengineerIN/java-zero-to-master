# Run this test

Needs `junit-platform-console-standalone-1.11.4.jar` (Maven Central).

    javac -cp junit-platform-console-standalone-1.11.4.jar -d out BillTest.java
    java -jar junit-platform-console-standalone-1.11.4.jar execute --class-path out --select-class BillTest
