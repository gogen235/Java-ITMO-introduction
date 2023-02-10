package md2html.markHtml;

import java.util.List;

public class Strong extends AbstractMark implements AllElements {
    public Strong(List<AllElements> strings) {
        super("strong", strings);
    }
}
