package leetTask;

public class TestTrie {

    public static void main(String[] args) {
        TestTrie tt = new TestTrie();
        String[] commands = {
                "Trie", "insert",  "search",  "search", "startsWith", "insert", "search",
                "search", "startsWith", "insert", "startsWith", "startsWith", "search"
        };
        String[][] words  = {{},
                {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"},
                {"pixie"}, {"pixie"}, {"pixie"}, {"oops"}, {"p"}, {"pixie"}
        };
        Trie obj = new Trie();
        for (int i = 1; i < commands.length; i++) {
            System.out.println(
                    tt.test(obj, commands[i],words[i][0]));
        }
    }

    boolean test( Trie obj, String command, String string) {
        String[] commands = {"insert", "search", "startsWith"};
        int index = 0;
        for (; index < 4; index++) {
            if (commands[index].equals(command)) break;
        }
        switch (index) {
            case 0: System.out.printf("inserting %s ", string); obj.insert(string); return true;
            case 1: System.out.printf("search    %s ", string); return obj.search(string);
            case 2: System.out.printf("prefix    %s ", string); return obj.startsWith(string);
            default: throw new IllegalStateException("not a command!");
        }

    }
}
