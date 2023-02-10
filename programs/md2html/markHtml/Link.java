package md2html.markHtml;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Link implements AllElements {
    private final String link;
    private final List<AllElements> texts;

    public Link(final String link, List<AllElements> texts) {
        this.texts = texts;
        this.link = link;
    }

    @Override
    public void toHtml(BufferedWriter writer) throws IOException {
        writer.write("<a href='" + link + "'>");
        for (AllElements i : texts) {
            i.toHtml(writer);
        }
        writer.write("</a>");
    }
}
