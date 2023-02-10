package markup;

import java.util.List;

public class Emphasis extends AbstractMark implements MarkText{
    public Emphasis(List<MarkText> strings) {
        super("\\emph{", "}", "*", List.copyOf(strings));
    }
}
