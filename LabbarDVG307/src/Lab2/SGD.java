package Lab2;

public class SGD {
	public int iterativSGD(int num1,int num2) {
		int rest = num1%num2;
		while (rest != 0) {
			num1 = num2;
			num2 = rest;
			rest = num1%num2;	
		}
		return num2;
	}
	
	public int rekursivSGD(int num1,int num2){
		int rest = num1%num2;
		if(rest==0) return num2;
		else return rekursivSGD(num2, rest);
	}
}
