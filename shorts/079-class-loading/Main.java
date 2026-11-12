class Helper {
    static { IO.println("Helper loaded"); }
    static void help() { IO.println("Helping"); }
}

void main() {
    IO.println("Start");
    Helper.help();
    Helper.help();
}
