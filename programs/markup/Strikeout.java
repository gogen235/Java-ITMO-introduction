package markup;

import java.util.List;

public class Strikeout extends AbstractMark implements MarkText{
    public Strikeout(List<MarkText> strings) {
        super("\\textst{", "}", "~", List.copyOf(strings));
    }
}
