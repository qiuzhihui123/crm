package test;

import com.qiuhui.entity.Staff;

public class test {

	public static void main(String[] args) {
		/*int sum = 0;
		
		for (int i = 1; i < 1000; i *= 2){
			
			for (int j = 0; j < 1000; j++){
				
				sum++;
			}
		}
		System.out.println(sum);*/
		
//		String str = exR1(6);
//		System.out.println(str);
		
		System.out.println(true && false || true && true);
		
	}
	
	public static String exR1(int n){
		if (n <= 0) {
		return "";
		}
		return exR1(n-3) + n + exR1(n-2) + n;
	}
	
	
	
	
}
