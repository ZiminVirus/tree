// Вставка в красно-черном дереве.
import java.util.Scanner;

class node {

  node left, right;
  int data;

  // красный true, черный false
  boolean color;

  node(int data) {
    this.data = data;
    left = null;
    right = null;

// Новый узел, всегда красного цвета.
    color = true;
  }
}

public class Main {

  private static node root = null;

  // Функция для поворота узла против часовой стрелки.
  node rotateLeft(node myNode) {
    System.out.printf("влево!!\n");
    node child = myNode.right;
    node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  }

  // Функция для поворота узла по часовой стрелке.
  node rotateRight(node myNode) {
    System.out.printf("вправо\n");
    node child = myNode.left;
    node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }

  // проверяем узел красного цвета или нет.
  boolean isRed(node myNode) {
    if (myNode == null) {
      return false;
    }
    return (myNode.color == true);
  }

  // Функция для изменения цвета двух узлы.
  void swapColors(node node1, node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  // вставка в левостороннее Красно-черное дерево.
  node insert(node myNode, int data) {

    if (myNode == null) {
      return new node(data);
    }

    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }

    // правый элемент красный, левый элемент черный или его нет.
    if (isRed(myNode.right) && !isRed(myNode.left)) {
      myNode = rotateLeft(myNode);
      swapColors(myNode, myNode.left);
    }

    // Левый дочерний ребенок, и его дочерний ребенок красные
    if (isRed(myNode.left) && isRed(myNode.left.left)) {
      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right);
    }

    // Левый, и правый дочерние элементы красные
    if (isRed(myNode.left) && isRed(myNode.right)) {
      myNode.color = !myNode.color;
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

  // Обход по порядку
  void inorder(node node) {
    if (node != null)
    {
      inorder(node.left);
      char c = '●';
      if (node.color == false)
        c = '◯';
      System.out.print(node.data + ""+c+" ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {

    Main node = new Main();
    Scanner scan = new Scanner(System.in);

    char ch;
    do {
      System.out.println("Введите целое число");

      int num = scan.nextInt();
      root = node.insert(root, num);

      node.inorder(root);
      System.out.println("\nВы хотите продолжить? (введите y или n)");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');
  }
}