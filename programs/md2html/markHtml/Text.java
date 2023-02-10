package md2html.markHtml;

import java.io.BufferedWriter;
import java.io.IOException;

public class Text implements AllElements {
    private final String text; // NOTE модификаторы доступа
    public Text(String str) {
        text = str;
    }
    @Override
    public void toHtml(BufferedWriter writer) throws IOException {
        writer.write(text);
    }
}
