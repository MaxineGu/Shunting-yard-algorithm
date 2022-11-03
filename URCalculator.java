import java.util.Arrays;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;

public class URCalculator {
public static String[] StringToToken(String string_in) {
	char[] ch = string_in.toCharArray();
	URArrayList<String> token = new URArrayList<String>(10);
	String tem = "";	
	  for (int i = 0; i < ch.length; i++) {	
          if (ch[i] == ' ') {
        	  continue;
          }
          else if(Character.isDigit(ch[i])){
        	   
        		  tem = tem + ch[i];
        		  while((i+1 < ch.length)) {
        			  if((Character.isDigit(ch[i+1]) || ch[i+1] == '.') ) {
        			  i = i+1;
        			  tem = tem + ch[i];
        		  }
        			  else{
        				  break;
        			  }
        			  }        		  
        		  token.add(tem);
        		  tem = "";        		  
        	  }
          else if (ch[i] == 's' || ch[i] == 'c' || ch[i] == 't') {
        	  tem = tem + ch[i];
        	  for (int q = 0; q<2; q++) {
        		  i = i + 1;
        		  tem = tem + ch[i];        		  
        	  }
        	  token.add(tem);
    		  tem = "";
          }
          else{
        		  token.add(Character.toString(ch[i]));     		        	
        	  }        	  
          }        	  
	  Object[] Arr = token.toArray();
	  String[] token_out = new String[Arr.length];
    System.arraycopy(Arr, 0, token_out, 0, Arr.length);
//    System.out.println(Arrays.toString(token_out));
    return token_out;
}
public static int preced_operand(String a) {
	if (a.equals("*") || a.equals("/") || a.equals("%"))
		return 5;
	else if(a.equals("+") || a.equals("-"))
		return 4;
	else if(a.equals(">") || a.equals("<") || a.equals("="))
		return 3;
	else if (a.equals("!"))
		return 2;
	else if(a.equals("&") || a.equals("|"))
		return 1;
	else if (a.equals("^"))
		return 6;
	else if (a.equals("("))
		return 0;
//	else if (a == ")")
//		return 6;
	else
		return -1;
}

public static void WriteToFile(String[] arr,String fileName){
	try {
	FileWriter file=new FileWriter(fileName);
	for(int i=0;i<arr.length;i++) {
		file.write(arr[i]+"\n");
	}
	file.close();
	}catch(IOException e) {
	e.printStackTrace();
	}
	}

//reference for isNumeric: https://www.baeldung.com/java-check-string-number#:~:text=Perhaps%20the%20easiest%20and%20the,Float.
public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
public static int asso_operand(String a) {
	if (a.equals("^") ||a.equals("!"))
		return 0; //right
	else 
		return 1; //left
}
public static boolean function_val(String s) {
	if (s.equals("sin")|| s.equals("cos")||s.equals("tan")) {
		return true;
	}
	else return false;
}
public static boolean operand_val(String s) {
	if (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals(">")||s.equals("<")||s.equals("=")||s.equals("!")||s.equals("%"))
		return true;
	else if (s.equals("&")||s.equals("|")||s.equals("!")) return true;
	else return false;
}

public static boolean paren_val(String s) {
	if (s.equals("(")||s.equals(")")) return true;
	else return false;
}
public static boolean validation(String[] s) {
	int rig_paren = 0;
	int left_paren = 0;
	for (int i = 0;  i < s.length; i = i + 1) {
		if (isNumeric(s[i]) == false && operand_val(s[i]) == false && function_val(s[i]) == false && paren_val(s[i])== false) {
			System.out.println("Invalid input");
			return false;
		}
		else if (s[i] == "(") {
			left_paren ++;
		}
		else if (s[i] == ")") {
			rig_paren ++;
			if (operand_val(s[i-1]) == true) return false; 
		}
		else if (operand_val(s[i]) == true && operand_val(s[i+1]) == true){
			System.out.println("Two operator cannot put together");
			return false;
		}		
	}
	if (rig_paren != left_paren) return false;
	return true;
}
public static double Calculate_Function(String s, Double d) {
	double result;
	if (s.equals("sin")) 
		result = Math.sin(d);
	else if (s.equals("cos")) 
		result = Math.cos(d);
	else if (s.equals("tan")) 
		result = Math.tan(d);
	else return -1.0;
	return result;
	
}
public static int boo_to_int(boolean b1) {
	if (b1 == false)
		return 0;
	else return 1;
}

public static boolean double_to_boo (double d1) {
	if (d1 == 0) {
		return false;
	}
	else return true;
}

	public static double logic_calculate (String s1, double d1, double d2) {
		boolean result;
		boolean b1;
		boolean b2;
		b1 = double_to_boo(d1);
		b2 = double_to_boo(d2);
		double convert_result;
		if (s1.equals("&")) {
			result = b1 && b2;			
		}
		else if (s1.equals("|")) {
			result = b1||b2;	
	}
		else if (s1.equals("=")) {
			result = (d1 == d2);
		}
		else if (s1.equals(">")) {
			result = (d1 > d2);
		}
		else if (s1.equals("<")) {
			result = (d1 < d2);
		}
		else return -1;
		convert_result = boo_to_int(result);
		return convert_result;
	}
	
public static double Calculator(String[] token) {
	int cur_pre;
	int top_pre;
	Stack <String> operand = new Stack <String>();
	Queue <String> post = new Queue <String>();
	for (int i = 0; i < token.length; i++) {
//		System.out.println(i);
		if (isNumeric(token[i])) { //if it is an operand 
			post.enqueue(token[i]);
//			System.out.println();			
		}
		else if (function_val(token[i])==true) { //if it is a function
			operand.push(token[i]);
		}
		else if (token[i].equals("(")) { // if it is left parenthesis
			operand.push("(");
//			operand.printStack();
		}
		else if (token[i].equals(")")) {  //if it is a right parenthesis
//			System.out.println("process");
			
			while (operand.get_top().equals("(") == false) { //push all operands before left parenthesis
//				operand.printStack();
//				System.out.println();
				post.enqueue(operand.pop());
//				post.printqueue();
//				System.out.println();
//				operand.printStack();
//				System.out.println(operand.get_top());
			}
			
			if (operand.get_top() != "(")
				System.out.println("What happens????");				
			
			else{
			operand.pop();
		}
		if(operand.isEmpty() == false){
			if(function_val(operand.get_top())== true) {
			post.enqueue(operand.pop());
		}
		}
	}
		else{  //it's an operator
			if (operand.isEmpty()){
				operand.push(token[i]);
			}
			else {
		cur_pre = preced_operand(token[i]);
		top_pre = preced_operand(operand.get_top());
		while (top_pre > cur_pre || (top_pre == cur_pre && asso_operand(token[i])== 1 )) {
			post.enqueue(operand.pop());
			if(operand.isEmpty()) {
				break;
			}
			else
				top_pre = preced_operand(operand.get_top());		
		}
		operand.push(token[i]);	
	}
}
//		operand.printStack();
//		System.out.println();
//		post.printqueue();
//		System.out.println();
}
	while (operand.isEmpty() == false) {		
	post.enqueue(operand.pop());	
	}
//	System.out.println("process here");
//	post.printqueue();
	System.out.println();
	//postfix evaluation
	Stack <Double> eval = new Stack <Double>();
	Double s1;
	Double s2;
	boolean b1;
	boolean b2;
	boolean boo_sol;
	double solution;
	String operator;
//	System.out.println("process here");
	while (post.isEmpty() == false)
	{
		if (isNumeric(post.get_first())) {
			eval.push(Double.parseDouble(post.dequeue()));
//			eval.printStack();
//			System.out.println();
		}
		else {
			operator = post.dequeue();
//			System.out.println(operator);
			if (operator.equals("!")) {
				s1 = eval.pop();
				b1 = double_to_boo(s1);
				boo_sol = !b1;
				solution = boo_to_int(boo_sol);
				}
			else if (function_val(operator) == true) {
				s1 = eval.pop();
				solution = Calculate_Function(operator, s1);
			}
		else {
			s1 = eval.pop();
			s2 = eval.pop();
			if (operator.equals("&")) {			
			solution = logic_calculate("&",s1,s2);
		}
		else if (operator.equals("|")) {
			solution = logic_calculate("|",s1,s2);
		}
		else if (operator.equals(">"))
			solution = logic_calculate(">",s2,s1);
		else if (operator.equals("<"))
			solution = logic_calculate("<",s2,s1);
		else if (operator.equals("=")) {
			solution = logic_calculate("=",s1,s2);
		}
		else if (operator.equals("-")) {
			solution = s2 - s1;
		}
		else if (operator.equals("+"))
			solution = s2 + s1;
		else if (operator.equals("%"))
			solution = s2 % s1;
		else if (operator.equals("*"))
			solution = s2 * s1;
		else if (operator.equals("/"))
			solution = s2 / s1;
		else if (operator.equals("^"))
			solution = Math.pow(s2, s1);
		else {
			System.out.println("Error! cannot recognize this character!");
			break;}
		}
			eval.push(solution);			
		}
		}
	double final_solution = eval.pop();
	if (eval.isEmpty() == false) {
		System.out.println("Error!");
	}
//	System.out.println(final_solution);
	return final_solution;
	}


public static void main(String args[]){  

	In in = new In(args[0]);
	double result;
	String result_str;
	String[] a = in.readAllLines();
	String [] token;
	String[] output = new String[a.length];
	for (int i = 0; i<a.length; i++) {
		token = StringToToken(a[i]);
		boolean val = validation(token);
		if (val == true) {
		result = Calculator(token);
		result_str = String.format("%1.2f", result);
		output[i] = result_str;
		}
		else {
			output[i] = "Invalid input";
		}
	}
	String FileName = args[1];
	WriteToFile(output, FileName); 
}
}
