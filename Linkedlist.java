//Chunhao Li
//cli79
//cli79@u.rochester.edu

public class Linkedlist {
    Node head;
    Node tail;


    public void addFirst(Object a){
            Node myNode = new Node();
            myNode.data = a;
            myNode.next = head;
            head = myNode;

    }

    public Object RemoveFirst(){
        Object o = head.data;
        head = head.next;

        return o;
    }

    public Object getFirst(){
        if (head == null
        ){
            return null;
        }
        else {
            return head.data;
        }
    }


    public void addLast(Object a){
        Node newLink = new Node();
        newLink.data = a;
        if (head == null){
            head = newLink;
        }
        else {
            tail.next = newLink;
        }
        tail = newLink;
    }








    public void printL(){
        printL(head);
    }

    public void printL(Node front){
        while (front != null){
            System.out.print(front.data + " ");
            front=front.next;
        }
        System.out.println();
    }







}
