package md2html.markHtml;

import java.util.List;

public class Code extends AbstractMark implements AllElements{
    public Code(List<AllElements> strings) {
        super("code", strings);
    }
}
