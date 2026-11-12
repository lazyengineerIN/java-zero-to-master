void main() {
    var songs = new LinkedList<String>();
    songs.add("Song B");
    songs.add("Song C");
    songs.addFirst("Song A");
    IO.println(songs);
    IO.println(songs.getFirst());
}
