package enhlishDictPrefixTree;

public class Word {

    public final String english;
    public final String transcription;
    public final String russian;

    public Word(String english, String transcr, String russian) {
        this.english = english;
        this.transcription = transcr;
        this.russian = russian;
    }

    public Word(String line) {
        String[] parts = line.split("\\t");
        this.english = parts[0];
        this.transcription = parts[1];
        this.russian = parts[2];
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", english, transcription ,russian);
    }
}
