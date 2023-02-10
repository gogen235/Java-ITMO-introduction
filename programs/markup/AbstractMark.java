package markup;

import java.util.List;

public abstract class AbstractMark implements AllElements {
    protected String signMark;
    protected String signTexB;
    protected String signTexE;
    protected List<AllElements> texts;
    protected AbstractMark(final String signTexB, final String signTexE, final String signMark, List<AllElements> texts) {
        this.texts = texts;
        this.signMark = signMark;
        this.signTexB = signTexB;
        this.signTexE = signTexE;
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(signMark);
        for (AllElements i : texts) {
            i.toMarkdown(str);
        }
        str.append(signMark);
    }

    @Override
    public void toTex(StringBuilder str) {
        str.append(signTexB);
        for (AllElements i : texts) {
            i.toTex(str);
        }
        str.append(signTexE);
    }
}
