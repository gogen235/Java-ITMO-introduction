package markup;

public class Text implements MarkText {
    private final String text; // NOTE модификаторы доступа

    public Text(String str) {
        text = str;
    }
    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(text);
    }
    @Override
    public void toTex(StringBuilder str) {
        str.append(text);
    }
}
