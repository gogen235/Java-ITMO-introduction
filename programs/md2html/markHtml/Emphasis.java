package md2html.markHtml;

import java.util.List;

public class Emphasis extends AbstractMark implements AllElements{
    public Emphasis(List<AllElements> strings) {
        super("em", strings);
    }
}
