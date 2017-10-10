package personal.ws.learning.algorithms.tree;

import personal.ws.util.Console;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/22
 * @project：WSService
 */
public class RedBlackTree {

    final public static boolean RED = true;
    final public static boolean BLACK = false;
    final public static Integer LEFT = -1;
    final public static Integer CENTER = 0;
    final public static Integer RIGHT = 1;
    final public static Random random = new Random();

    public static void main(String args[]) {
        Node root = new Node(null, CENTER, random.nextInt(200), BLACK);

        Node l1 = new Node(root, LEFT, random.nextInt(200), BLACK);
        Node r1 = new Node(root, RIGHT, random.nextInt(200), BLACK);

//        Node l11 = new Node(null, CENTER, 4, BLACK);
//        l11.setFather(l1, LEFT);
//        Node r11 = new Node(r1, CENTER, 6, BLACK);
//        r11.setFather(l1, LEFT);
//        Node l12 = new Node(null, CENTER, 5, BLACK);
//        l12.setFather(l1, RIGHT);

        Node l11 = new Node(l1, LEFT, random.nextInt(200), BLACK);
        Node l12 = new Node(l1, RIGHT, random.nextInt(200), BLACK);
        Node r11 = new Node(r1, LEFT, random.nextInt(200), BLACK);
        Node r12 = new Node(r1, RIGHT, random.nextInt(200), BLACK);

        Node l111 = new Node(l11, LEFT, random.nextInt(200), BLACK);
        Node l112 = new Node(l11, RIGHT, random.nextInt(200), BLACK);
        Node l121 = new Node(l12, LEFT, random.nextInt(200), BLACK);
        Node l122 = new Node(l12, RIGHT, random.nextInt(200), BLACK);

        Node r111 = new Node(r11, LEFT, random.nextInt(200), BLACK);
        Node r112 = new Node(r11, RIGHT, random.nextInt(200), BLACK);
        Node r121 = new Node(r12, LEFT, random.nextInt(200), BLACK);
        Node r122 = new Node(r12, RIGHT, random.nextInt(200), BLACK);

        printColor(root);
        printTree(root, 1);
    }

    public static void printColor(Node current) {
        Node root = getRoot(current);
        root.setRed(BLACK);
        printLoop(root, 0);
    }

    public static void printLoop(Node current, int range) {
        Node[] children = current.getChildren();
        for (Node node : children) {
            if (null != node) {
                node.setRed(range % 2 == 0 ? BLACK : RED);
                printLoop(node, range + 1);
            }
        }
    }

    //获取根节点
    public static Node getRoot(Node current) {
        Node father = current.getFather();
        if (null != father) {
            getRoot(father);
        }
        return current;
    }

    //打印树
    public static void printTree(Node current, Integer range) {
        if (current.hasChild()) {
            Node left = current.getLeft();
            Node right = current.getRight();
            Console.____purple("==第" + range + "层==");
            Console.____green("当前节点:" + current.toString());
            Console.____red("颜色:" + (current.isRed() ? "红" : "黑") + " " + current.getValue());
            Console.____red("--左:" + left.toString() + " " + left.getValue());
            Console.____blue("--右:" + right.toString() + " " + +right.getValue());
            Console.____green("-----开始查找第" + (range + 1) + "层左子节点:");
            printTree(left, range + 1);
            Console.____blue("-----开始查找第" + (range + 1) + "层右子节点:");
            printTree(right, range + 1);
        } else {
            Console.____cyan("当前节点" + current.toString() + "第" + (range) + "层无子节点");
        }
    }
}

class Node {
    private Node father;
    private Node left;
    private Node right;
    private int value;
    private boolean isRed;

    public Node(Node father, int type, Integer value, boolean isRed) {
        this.father = father;
        if (type == -1) {
            father.setLeft(this);
        } else if (type == 1) {
            father.setRight(this);
        } else {

        }
        this.value = value;
        this.isRed = isRed;
    }

    //判断是否是root节点
    public boolean isRoot() {
        return this.father == null ? true : false;
    }

    //添加左孩子节点
    public void addLeft(Node left) {
        assert null == this.left;
        this.left = left;
        left.setFather(this, -1);
    }

    //添加右孩子节点
    public void addRight(Node right) {
        assert null == this.right;
        this.right = right;
        right.setFather(this, 1);
    }

    //添加子节点
    public void addChildren(Node left, Node right) {
        addLeft(left);
        addRight(right);
    }

    //判断是否有孩子节点
    public boolean hasChild() {
        if (null == this.left && null == right) return false;
        return true;
    }

    //获取孩子节点们
    public Node[] getChildren() {
        return new Node[]{(null != this.left) ? this.left : null, (null != this.right) ? this.right : null};
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father, Integer position) {
        this.father = father;
        if (null != this.father) {
            switch (position) {
                case -1:
                    if (null == this.father.getLeft())
                        this.father.setLeft(this);
                    break;
                case 0:
                    System.out.println("Position Error!");
                    break;
                case 1:
                    if (null == this.father.getRight())
                        this.father.setRight(this);
                    break;
                default:
                    System.out.println("Position Error!");
                    break;
            }
        }
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    //判断节点是否是红色
    public boolean isRed() {
        assert (this.isRoot() == true && this.isRed == false) || (this.isRoot() == false);
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    @Override
    public String toString() {
        return super.toString().replaceAll("personal.ws.learning.algorithms.tree.Node@", "");
    }
}