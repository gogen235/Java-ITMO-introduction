package markup;

import java.util.List;

public class Strong extends AbstractMark implements MarkText{
    public Strong(List<MarkText> strings) {
        super("\\textbf{", "}","__", List.copyOf(strings));
    }
}
