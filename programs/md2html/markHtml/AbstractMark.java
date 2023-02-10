package md2html.markHtml;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public abstract class AbstractMark implements AllElements {
    protected String signHtml;
    protected List<AllElements> texts;
    protected AbstractMark(final String signHtml, List<AllElements> texts) {
        this.texts = texts;
        this.signHtml = signHtml;
    }
    @Override
    public void toHtml(BufferedWriter writer) throws IOException {
        writer.write("<" + signHtml + ">");
        for (AllElements i : texts) {
            i.toHtml(writer);
        }
        writer.write("</" + signHtml + ">");
    }
}
