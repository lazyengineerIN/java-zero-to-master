import java.sql.*;

void main() throws SQLException {
    var db = DriverManager.getConnection("jdbc:h2:mem:shop");
    db.createStatement().execute(
        "CREATE TABLE menu(item VARCHAR, price INT)");
    var add = db.prepareStatement(
        "INSERT INTO menu VALUES (?, ?)");
    add.setString(1, "chai");
    add.setInt(2, 15);
    add.execute();
    var find = db.prepareStatement(
        "SELECT price FROM menu WHERE item = ?");
    find.setString(1, "chai");
    var row = find.executeQuery();
    row.next();
    IO.println(row.getInt("price"));
}
