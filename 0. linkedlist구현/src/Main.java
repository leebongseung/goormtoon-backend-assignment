import java.util.*;


public class Main {
    public static class MyLinkedList<T> implements Iterable<T> {
        private static class Node<E> {
            private E item;
            private Node<E> next;

            Node(E element) {
                this.item = element;
                this.next = null;
            }
            public E getItem(){
                return item;
            }
            public void setNext(Node<E> next) {
                this.next = next;
            }

            // 다음 노드의 next의 주소값을 반환
            public Node<E> getNext(){
                return this.next;
            }
            public boolean hasNext(){
                return this.next != null;
            }
        }


        private int size = 0;
        private Node<T> header;

        public MyLinkedList() {};

        public void add(T element){
            if(size <= 0){
                header = new Node(element);
            } else{
                Node<T> newElement= new Node<>(element);
                if(header.hasNext()){
                    Node<T> nextElement = header.getNext();
                    while(true){
                        if(!nextElement.hasNext()) break;
                        nextElement = nextElement.getNext();
                    }
                    nextElement.setNext(newElement);
                } else{
                    header.setNext(newElement);
                }
            }
            size++;
        }

        public T get(int i){
            if(i < 0 || i >= size) {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds!");
            }
            int cnt = 0;
            Node<T> cur = header;
            while(cnt <i){
                cur = cur.getNext();
                cnt++;
            }
            return cur.getItem();
        }

        public T delete(int i){
            if(i < 0 || i >= size) {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds!");
            }

            if(i == 0){
                T deleteValue = header.getItem();
                header = header.getNext();
                return deleteValue;
            }

            int cnt = 0;
            Node<T> pre = header;
            while(cnt < i-1){
                pre = pre.getNext();
                cnt++;
            }
            // 이전 노드 찾음.
            // 삭제할 노드 다음꺼와 연결시켜주면됨
            Node<T> RemoveNode;
            Node<T> nextNode;
            RemoveNode = pre.getNext();
            nextNode = RemoveNode.getNext();
            pre.setNext(nextNode);

            size--;
            return RemoveNode.getItem();
        }

        @Override
        public Iterator<T> iterator() {
            return new MyIterator();
        }

        private class MyIterator implements Iterator<T>{
            private  int curIdx = 0;
            private Node<T> curnode = header;
            @Override
            public boolean hasNext() {
                return curIdx < size;
            }

            @Override
            public T next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                curIdx++;
                T curItm = curnode.getItem();
                curnode = curnode.getNext();
                return curItm;
            }
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> mylist = new MyLinkedList<>();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);

        System.out.println("get 메서드: ");
        System.out.println(mylist.get(0));
        System.out.println(mylist.get(1));
        System.out.println(mylist.get(2));
        System.out.println("------------------------------------");


        System.out.println("for-each문 : ");
        for(var a : mylist){
            System.out.println(a);
        }
        System.out.println("------------------------------------");

        System.out.println("delete 메서드 : ");
        System.out.println(mylist.delete(1));
        System.out.println(mylist.delete(0));
        System.out.println(mylist.delete(0));
        System.out.println("------------------------------------");

        //스택 구현 LIFO
        MyLinkedList<Integer> stack = new MyLinkedList<>();
        stack.add(0);
        stack.add(1);
        stack.add(2);

        stack.delete(2);
        stack.delete(1);
        stack.delete(0);

        //큐 구현 FIFO
        MyLinkedList<Integer> queue = new MyLinkedList<>();
        queue.add(0);
        queue.add(1);
        queue.add(2);

        queue.delete(0);
        queue.delete(0);
        queue.delete(0);
    }
}