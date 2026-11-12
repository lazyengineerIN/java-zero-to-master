sealed interface Payment permits Cash, Card {}
record Cash(int amount) implements Payment {}
record Card(int amount, int fee) implements Payment {}

int total(Payment p) {
    return switch (p) {
        case Cash c -> c.amount();
        case Card c -> c.amount() + c.fee();
    };
}

void main() {
    IO.println(total(new Cash(100)));
    IO.println(total(new Card(100, 2)));
}
