package alias;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    private Author writer;
    private String second;
    private List entries = new ArrayList();

    public Blog(Author writer, String second) {
            this.writer = writer;
            this.second = second;
    }

    public void add(Entry entry) {
            entries.add(entry);
    }

    public List getContent() {
            return entries;
    }
}
