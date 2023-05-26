//Chunhao Li
//cli79
//cli79@u.rochester.edu

public class Queue extends Linkedlist{
    private Linkedlist theQ;




    public Queue(){
        theQ = new Linkedlist();
    }
    public void enqueue(Object o){
        theQ.addLast(o);
    }


    public Object dequeue(){
        Object a = theQ.RemoveFirst();
        return a;
    }


    public Object FV(){
        return theQ.getFirst();
    }

    public void printL(){
        theQ.printL();
    }
}
