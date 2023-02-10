package expression;

public abstract class BinaryOperation implements AllExpression {
    private final AllExpression first;
    private final AllExpression a;
    String operator;
    private final int priority;
    private final boolean commutative;
    BinaryOperation(AllExpression first, AllExpression second, String operator, int priority, boolean commutative) {
        this.first = first;
        this.a = second;
        this.operator = operator;
        this.priority = priority;
        this.commutative = commutative;
    }

    public AllExpression getFirst() {
        return first;
    }

    public AllExpression getSecond() {
        return a;
    }

    public int getPriority() {
        return priority;
    }
    public boolean getCommutative() {
        return commutative;
    }

    @Override
    public int evaluate(int var) {
        return makeAction(first.evaluate(var), a.evaluate(var));
    }
    public abstract int makeAction(int first, int second);
    public abstract double makeAction(double first, double second);
    @Override
    public int evaluate(int x, int y, int z) {
        return makeAction(first.evaluate(x, y, z), a.evaluate(x, y, z));
    }
    @Override
    public double evaluate(double var) {
        return makeAction(first.evaluate(var), a.evaluate(var));
    }
    @Override
    public String toString() {
        return "(" + first.toString() + " " + operator + " " + a.toString() + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder str = new StringBuilder();
        checkBracket(this.priority > first.getPriority(), first, str);
        str.append(" ");
        str.append(operator);
        str.append(" ");
        checkBracket(this.priority > a.getPriority() ||
                !this.commutative && this.priority == a.getPriority() ||
                this.priority == 3 && a.getPriority() == 3 && !a.getCommutative() ||
                (this instanceof GCD && a instanceof LCM || this instanceof LCM && a instanceof GCD), a, str);
        return str.toString();
    }

    private void checkBracket(boolean isBracket, AllExpression exp, StringBuilder str) {
        if (isBracket) {
            pushInStr("(", ")", str, exp);
        } else {
            pushInStr("", "", str, exp);
        }
    }
    private void pushInStr(String lBracket, String rBracket, StringBuilder str, AllExpression exp) {
            str.append(lBracket);
            str.append(exp.toMiniString());
            str.append(rBracket);
    }
    @Override
    public boolean equals(Object elem) {
        if (elem instanceof BinaryOperation newElem) {
            return first.equals(newElem.getFirst()) && a.equals(newElem.getSecond()) &&
                    this.getClass().equals(newElem.getClass());
        }
        return false;
    }
}
