package md2html.markHtml;

import java.util.List;

public class Strikeout extends AbstractMark implements AllElements {
    public Strikeout(List<AllElements> strings) {
        super("s", strings);
    }
}
