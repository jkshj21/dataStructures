import java.util.Iterator;
import java.util.LinkedList;

public class AVLTree<AnyType extends Comparable<? super AnyType>>
{
  private static class Node<AnyType>
  {
    private AnyType data;
    private Node<AnyType> parent;
    private Node<AnyType> left;
    private Node<AnyType> right;
    private int height;

    public Node(AnyType d, Node<AnyType> p, Node<AnyType> l, Node<AnyType> r)
    {
      setData(d);
      setParent(p);
      setLeft(l);
      setRight(r);
      setHeight(0);
    }

    public AnyType getData() { return data; }
    public Node<AnyType> getParent() { return parent; }
    public Node<AnyType> getLeft() { return left; }
    public Node<AnyType> getRight() { return right; }
    public int getHeight() { return height; }

    public void setData(AnyType d) { data = d; }
    public void setParent(Node<AnyType> p) { parent = p; }
    public void setLeft(Node<AnyType> l) { left = l; }
    public void setRight(Node<AnyType> r) { right = r; }
    public void setHeight(int h) { height = h; }
  }

  private Node<AnyType> root;

  public AVLTree() { makeEmpty(); }

  public void makeEmpty() { root = null; } 

  public boolean isEmpty() { return (root == null); }

  public boolean contains(AnyType v) { return contains(v, root); }

  private boolean contains(AnyType v, Node<AnyType> t)
  {
    if (t == null) return false;

    int compareResult = v.compareTo(t.getData());

    if (compareResult < 0)
      return contains(v, t.getLeft());
    else
      if (compareResult > 0)
        return contains(v, t.getRight());
      else
        return true;
  }

  public AnyType findMin() throws IllegalStateException
  {
    if (isEmpty())
      throw new IllegalStateException("Search Tree is Empty.");

    Node<AnyType> minNode = findMin(root);
    return minNode.getData();
  }

  private Node<AnyType> findMin(Node<AnyType> t)
  {
    if (t == null)
      return null;
    else
      if (t.getLeft() == null)
        return t;

    return findMin(t.getLeft());
  }

  public AnyType findMax()throws IllegalStateException
  {
    if (isEmpty())
      throw new IllegalStateException("Search Tree is Empty.");

    Node<AnyType> maxNode = findMax(root);
    return maxNode.getData();
  }

  private Node<AnyType> findMax(Node<AnyType> t)
  {
    if (t == null)
        return null;
      else
        if (t.getRight() == null)
          return t;

    return findMax(t.getRight());
  }

  public void insert(AnyType v) { root = insert(v, null, root); }

  private Node<AnyType> insert(AnyType v, Node<AnyType> p, Node<AnyType> t)
  {
    if (t == null) return (new Node<>(v, p, null, null));

    int compareResult = v.compareTo(t.getData());

    if (compareResult < 0)
      t.setLeft(insert(v, t, t.getLeft()));
    else
      if (compareResult > 0)
        t.setRight(insert(v, t, t.getRight()));
      else
        ;  // Duplicate value, do nothing.

    return balance(t);
  }

  public void remove(AnyType v) { root = remove(v, root); }

  private Node<AnyType> remove(AnyType v, Node<AnyType> t)
  {
    if (t == null) return t;

    int compareResult = v.compareTo(t.getData());

    if (compareResult < 0)
      t.setLeft(remove(v, t.getLeft()));
    else
      if (compareResult > 0)
        t.setRight(remove(v, t.getRight()));
      else
        if (t.getLeft() != null && t.getRight() != null)
        {
          Node<AnyType> minNodeRightSubtree = findMin(t.getRight());
          AnyType minNodeRightSubtreeValue = minNodeRightSubtree.getData();
          t.setData(minNodeRightSubtreeValue);
          t.setRight(remove(minNodeRightSubtreeValue, t.getRight()));
        }
        else
        {
          Node<AnyType> parentOfT = t.getParent();
          Node<AnyType> childOfT = (t.getLeft() != null) ? t.getLeft() : t.getRight();
          if (childOfT != null) childOfT.setParent(parentOfT);
          t = childOfT;
        }

    return balance(t);
  }

  public Iterator<AnyType> iterator()
  {
    LinkedList<AnyType> snapshot = new LinkedList<>();

    inOrderTraversal(root, snapshot);
    return snapshot.iterator();
  }

  private void inOrderTraversal(Node<AnyType> t, LinkedList<AnyType> l)
  {
    if (t != null)
    {
      inOrderTraversal(t.getLeft(), l);
      l.add(t.getData());
      inOrderTraversal(t.getRight(), l);
    }
  }

  private static final int ALLOWED_IMBALANCE = 1;

  private int getHeight(Node<AnyType> t)
  {
    return ((t == null) ? -1 : t.getHeight());
  }

  private void setHeight(Node<AnyType> t)
  {
    int leftChildHeight = getHeight(t.getLeft());
    int rightChildHeight = getHeight(t.getRight());

    t.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);;
  }

  private Node<AnyType> balance(Node<AnyType> t)
  {
    if (t == null) return t;

    if (getHeight(t.getLeft()) - getHeight(t.getRight()) > ALLOWED_IMBALANCE)
    {
      Node<AnyType> leftChild = t.getLeft();
      if (getHeight(leftChild.getLeft()) >= getHeight(leftChild.getRight()))
        t = rotateWithLeftChild(t);
      else
        t = doubleWithLeftChild(t);
    }
    else
    {
      if (getHeight(t.getRight()) - getHeight(t.getLeft()) > ALLOWED_IMBALANCE)
      {
        Node<AnyType> rightChild = t.getRight();
        if (getHeight(rightChild.getRight()) >= getHeight(rightChild.getLeft()))
          t = rotateWithRightChild(t);
        else
          t = doubleWithRightChild(t);
      }
    }

    setHeight(t);
    return t;
  }

  private Node<AnyType> rotateWithLeftChild(Node<AnyType> k2)
  {
    Node<AnyType> k1 = k2.getLeft();
    Node<AnyType> k1sRightChild = k1.getRight();

    k2.setLeft(k1sRightChild);
    k1.setRight(k2);

    k1.setParent(k2.getParent());
    k2.setParent(k1);
    k1sRightChild.setParent(k2);

    setHeight(k2);
    setHeight(k1);

    return k1;
  }

  private Node<AnyType> rotateWithRightChild(Node<AnyType> k1)
  {
    Node<AnyType> k2 = k1.getRight();
    Node<AnyType> k2sLeftChild = k2.getLeft();

    k1.setRight(k2sLeftChild);
    k2.setLeft(k1);

    k2.setParent(k1.getParent());
    k1.setParent(k2);
    k2sLeftChild.setParent(k1);

    setHeight(k1);
    setHeight(k2);

    return k2;
  }

  private Node<AnyType> doubleWithLeftChild(Node<AnyType> k3)
  {
    k3.setLeft(rotateWithRightChild(k3.getLeft()));
    return rotateWithLeftChild(k3);
  }

  private Node<AnyType> doubleWithRightChild(Node<AnyType> k1)
  {
    k1.setRight(rotateWithLeftChild(k1.getRight()));
    return rotateWithRightChild(k1);
  }
}