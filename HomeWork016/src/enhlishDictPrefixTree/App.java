package enhlishDictPrefixTree;

public class App {

    public static void main(String[] args) {
        App app = new App();
        FReader fr = new FReader();
        String[] lines = fr.readAllLines("english-19350-4a22b7.txt");
        PrefixTree book = new PrefixTree();
        app.load(lines, book);
        System.out.println(book.get("Wednesday"));
    }

    void load(String[] lines, PrefixTree book) {
        for (String l : lines) {
            Word w = new Word(l);
            book.put(w.english, w);
        }
    }

}
