//Chunhao Li
//cli79
//cli79@u.rochester.edu


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class main {

    DecimalFormat df = new DecimalFormat("0.00");
    public static int  EO(String a){
        int P = -1;

        if (a.compareTo("=")==0 ||a.compareTo("<")==0 ||a.compareTo(">")==0   ){
            P = 0;
        }
        if (a.compareTo("+") == 0 || a.compareTo("-") == 0){
            P = 1;
        }
        if (a.compareTo("*") == 0 || a.compareTo("/") == 0){
            P = 2;
        }
        if (a.compareTo("^") == 0 || a.compareTo("!") == 0
                || a.compareTo("sin")==0 || a.compareTo("cos")==0
                || a.compareTo("tan")==0 || a.compareTo("%") == 0){
            P = 3;
        }
        if (a.compareTo("(") == 0 || a.compareTo(")") == 0){
            P = 4;
        }


        return P;
    }

    public static boolean precedence(String a, String b){
        boolean x = false;

        if (EO(a) > EO(b)){
            x= true;
        }

        return x;
    }


    public static Double calculation (Double a, Double b, String c){
        Double solution = null;
        if (c.compareTo("&")==0){
            if (a == 1.0 && b == 1.0){
                solution = 1.0;
            }
            else {
                solution = 0.0;
            }
        }
        if (c.compareTo("|")==0){
            if (a ==1.0 ||  b== 1.0){
                solution = 1.0;
            }
            else {
                solution = 0.0;
            }
        }
        if (c.compareTo("=")==0){
            if ( (a - b) < 0.000000000001){
                solution = 1.0;
            }
            else {
                solution = 0.0;
            }
        }
        if (c.compareTo(">")==0){
            if (a < b){
                solution = 1.0;
            }
            else {
                solution = 0.0;
            }
        }
        if (c.compareTo("<")==0){
            if (a > b){
                solution = 1.0;
            }
            else {
                solution = 0.0;
            }
        }

        if (c.compareTo("+")==0){
            solution = a + b;
        }
        if (c.compareTo("-")==0){
            solution = b - a;
        }
        if (c.compareTo("*")==0){
            solution = a * b;
        }
        if (c.compareTo("/")==0){
            solution = b / a;
        }
        if (c.compareTo("^") == 0){
            solution = Math.pow(b,a);
        }
        if (c.compareTo("%") == 0){
            solution = b % a;
        }


        return solution;
    }

    public static Double calculationF (Double a, String c){
        Double solution = null;
        if (c.compareTo("!")==0) {

            if (a == 0) {
                solution = 1.0;
            }
            if (a == 1) {
                solution = 0.0;
            }

        }

        if (c.compareTo("cos") == 0){
            solution = Math.cos(a);
        }
        if (c.compareTo("tan") == 0){
            solution = Math.tan(a);
        }
        if (c.compareTo("sin")==0){
            solution = Math.sin(a);
        }


        return solution;
    }

    public static void main(String[] args){

        DecimalFormat df= new DecimalFormat("0.00");

        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter input file path");
            String inputP = s.nextLine();
            System.out.println("Enter out put file path");
            String outputp = s.nextLine();
            File test = new File(inputP);
            Scanner fileR = new Scanner(test);
            FileWriter fileW = new FileWriter(outputp);

            while (fileR.hasNextLine()){
                String expression = fileR.nextLine();
                Scanner lineR = new Scanner(expression);

                Queue output = new Queue();
                MyStack Operator = new MyStack();


                while (lineR.hasNext()){

                    String data = lineR.next();

                    try{
                        Double x = Double.parseDouble(data);
                        output.enqueue(x);
                    }
                    catch (Exception e){

                           //facing ()
                           if (data.compareTo(")") == 0) {

                               while (Operator.FV() != null) {
                                   if (Operator.FV().toString().compareTo("(") == 0) {
                                       Operator.pop();
                                        break;
                                   } else {
                                       output.enqueue(Operator.pop());
                                   }
                               }

                           }
                           else {
                               while (Operator.FV() != null) {


                                   if (precedence(Operator.FV().toString(), data)) {
                                       if (Operator.FV().toString().compareTo("(") == 0){
                                           break;
                                       }
                                       else {
                                           output.enqueue(Operator.pop());
                                       }
                                   } else {
                                       break;
                                   }


                               }

                               Operator.push(data);

                           }

                    }
                }

                while (Operator.FV() != null){
                    if (Operator.FV().toString().compareTo("(") == 0){
                        Operator.pop();
                    }
                    else {
                        output.enqueue(Operator.pop());
                    }
                }


                output.printL();


               MyStack value = new MyStack();

                while (output.FV() != null){


                    try{
                        Double x = Double.parseDouble(output.FV().toString());
                        value.push(output.dequeue());
                    }
                    catch (Exception e){
                        try {


                            if (output.FV().toString().compareTo("!") == 0 || output.FV().toString().compareTo("sin") == 0
                                    || output.FV().toString().compareTo("cos") == 0 || output.FV().toString().compareTo("tan") == 0) {
                                value.push(calculationF(Double.parseDouble(value.pop().toString()), output.dequeue().toString()));
                            } else {

                                value.push(calculation(Double.parseDouble(value.pop().toString()),
                                        Double.parseDouble(value.pop().toString()),
                                        output.dequeue().toString()));
                            }

                        }
                        catch (Exception a){
                            break;
                        }

                    }



              }

                value.printL();
                try {
                    fileW.write(df.format(Double.parseDouble(value.FV().toString())));
                    fileW.write("\n");
                }
                catch (Exception b){
                    fileW.write("This input is invalid\n");
                }












            }



            fileR.close();
            fileW.close();
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        } catch (IOException e) {
            System.out.println("does not work");
            e.printStackTrace();
        }


    }




}
