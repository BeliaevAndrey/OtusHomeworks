package substringSearch;

public interface ISearcher {

    void init(String string, String substring);

    int search();
    void prepare();

    int getCompares();

    String getHeader();
}
