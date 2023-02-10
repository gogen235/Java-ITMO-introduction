package markup;

import java.util.List;

public class ListItem extends AbstractMark {
    public ListItem(List<ListItemElement> strings) {
        super("\\item ", "", "", List.copyOf(strings));
    }
}
