package md2html.markHtml;

import java.util.List;

public class Paragraph extends AbstractMark implements AllElements {
    public Paragraph(List<AllElements> strings) {
        super("p", strings);
    }
}
