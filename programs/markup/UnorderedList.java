package markup;

import java.util.List;

public class UnorderedList extends AbstractMark implements ListItemElement {
    public UnorderedList(List<ListItem> strings) {
        super("\\begin{itemize}", "\\end{itemize}", "", List.copyOf(strings));
    }
}
