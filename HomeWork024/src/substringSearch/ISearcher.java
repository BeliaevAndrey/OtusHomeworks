package substringSearch;

public interface ISearcher {

    void init(String string, String substring);

    int search();

    int getCompares();

    String getHeader();
}
