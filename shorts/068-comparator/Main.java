record Player(String name, int runs) {}

void main() {
    var team = new ArrayList<>(List.of(
        new Player("Ravi", 45),
        new Player("Arun", 82),
        new Player("Meena", 67)));
    var byRuns = Comparator.comparing(Player::runs);
    team.sort(byRuns);
    IO.println(team.getFirst().name());
    team.sort(byRuns.reversed());
    IO.println(team.getFirst().name());
}
