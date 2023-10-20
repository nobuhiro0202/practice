package sq;
public class StackWithMin extends Stack<NodeWithMin> {
  public void push(int value) {
    int newMin = Math.min(value, min());
    NodeWithMin a = new NodeWithMin(value, newMin);
    super.push(a);
  }

  public int min() {
    if (this.isEmpty()) return Integer.MAX_VALUE;
    else return peek().min;
  }
}
