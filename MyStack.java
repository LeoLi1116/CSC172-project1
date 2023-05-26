//Chunhao Li
//cli79
//cli79@u.rochester.edu

public class MyStack extends Linkedlist{
        private Linkedlist theStack;

        public MyStack(){
            theStack = new Linkedlist();
        }


        public void push(Object o){
            theStack.addFirst(o);
        }


        public Object pop(){
            Object a = theStack.RemoveFirst();
            return a;
        }

        public Object FV(){
            return theStack.getFirst();
        }

        public void printL(){
        theStack.printL();
        }





}
