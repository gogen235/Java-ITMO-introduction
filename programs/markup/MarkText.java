package markup;

public interface MarkText extends AllElements {
    void toMarkdown(StringBuilder str);
    void toTex(StringBuilder str);
}
