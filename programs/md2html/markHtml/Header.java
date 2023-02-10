package md2html.markHtml;

import java.util.List;

public class Header extends AbstractMark implements AllElements {
    public Header(List<AllElements> strings, int num) {
        super("h" + num, strings);
    }

}
