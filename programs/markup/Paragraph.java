package markup;

import java.util.List;

public class Paragraph extends AbstractMark implements ListItemElement {
    public Paragraph(List<MarkText> strings) {
        super("", "", "", List.copyOf(strings));
    }
}
