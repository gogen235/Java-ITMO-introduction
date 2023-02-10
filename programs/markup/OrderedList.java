package markup;

import java.util.List;

public class OrderedList extends AbstractMark implements ListItemElement {
    public OrderedList(List<ListItem> strings) {
        super("\\begin{enumerate}", "\\end{enumerate}", "", List.copyOf(strings));
    }

}
