package md2html.markHtml;

import java.io.BufferedWriter;
import java.io.IOException;

public interface AllElements {
    void toHtml(BufferedWriter writer) throws IOException;
}
