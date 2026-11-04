public class Main {
    public static void main(String[] args) {
        String phone = null;
        System.out.println(phone == null);
        if (phone != null) {
            System.out.println(phone.length());
        }
        System.out.println("Safe");
    }
}
