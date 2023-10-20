package sq;
import java.util.EmptyStackException;

public class Stack<T> {
  private static class StackNode<T> {
    private T data;
    private StackNode<T> next;

    public StackNode(T data) {
      this.data = data;
    }
  }

  private StackNode<T> top;
  
  public T pop() {
    if (top == null) throw new EmptyStackException();
    T item = top.data;
    top = top.next;
    return item;
  }
  
  public void push(T item) {
    StackNode<T> t = new StackNode<T>(item);
    t.next = top;
    top = t;
  }
  
  public T peek() {
    if (top == null) throw new EmptyStackException();
    return top.data;
  }
  
  public boolean isEmpty() {
    return top == null;
  }

  public void sort(Stack<Integer> s) {
    Stack<Integer> r = new Stack<Integer>();
    while (!s.isEmpty()) {
      int tmp = s.pop();
      while (!r.isEmpty() && r.peek() < tmp) s.push(r.pop());
      r.push(tmp);
    }
    while (!r.isEmpty()) s.push(r.pop());
  }
  
}
