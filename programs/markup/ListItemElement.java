package markup;

public interface ListItemElement extends AllElements { // NOTE что-то странное навзание
    void toTex(StringBuilder str);
    void toMarkdown(StringBuilder str);
}
