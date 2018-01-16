import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Gerador {
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("Expressoes.in");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("Expressoes.out");
		BufferedWriter bw = new BufferedWriter(fw);
		String expr,sub;
		String[] subs;
		Vector vec;  
		int[][] table;
		char[] code,aux; 
		boolean cond, cond2, cond3, cond4,stop;
		int n = Integer.parseInt(br.readLine()),cont,start,end,balance,i=0, j=0, k, l,t=0,pow;
		while(n>0) {
			n--;
			t++;
			i=0; j=0;
			expr = br.readLine();
			vec = new Vector();
			code = expr.toCharArray(); 
			aux = expr.toCharArray(); 
			while(i<code.length) {
				cond = true;
				for(;i<code.length&&cond;i++) {
					if(code[i]==')') {
						cond = false;
					}
				}
				i--;
				cond = true;
				for(j=i;j>=0&&cond;j--){
					if(code[j]=='(') {
						code[j]='*';
						cond = false;
					}
				}
				j++;
				sub = String.copyValueOf(aux, j, i-j+1);
				vec.add(sub);
				i++;
			}
			if(expr.length()==1) vec.clear();
			subs = new String[vec.size()];
			vec.toArray(subs);
			sort(subs);
			vec.clear();
			cond = true;
			cond2 = true;
			cond3 = true;
			cond4 =true;
			for(i=0;i<aux.length;i++) {
				if(aux[i]=='x'&&cond) cond = false; 
				if(aux[i]=='y'&&cond2) cond2 = false; 
				if(aux[i]=='z'&&cond3) cond3 = false; 
				if(aux[i]=='t'&&cond4) cond4 = false; 
			}
			cont = 0;
			if(!cond) {vec.add("x");cont++;}
			if(!cond2) {vec.add("y");cont++;}
			if(!cond3) {vec.add("z");cont++;}
			if(!cond4) { vec.add("t");cont++;}
			for(i=0;i<subs.length;i++) {
				if(!subs[i].equals("#")) vec.add(subs[i]);
			}
			pow = pow(cont);
			subs = new String[vec.size()];
			vec.toArray(subs);
			table = new int[pow][subs.length];
			int v = 0;
			if(cont==1) {
				for(i=0;i<2;i++) {
					table[i][0]=i;
				}
			} else if(cont==2) {
				for(i=0;i<2;i++) {
					for(j=0;j<2;j++){
						table[v][0]=i;
						table[v][1]=j;
						v++;
					}
				}
			} else if(cont==3) {
				for(i=0;i<2;i++) {
					for(j=0;j<2;j++){
						for(k=0;k<2;k++) {
							table[v][0]=i;
							table[v][1]=j;
							table[v][2]=k;
							v++;
						}
					}
				}
			} else {
				for(i=0;i<2;i++) {
					for(j=0;j<2;j++){
						for(k=0;k<2;k++) {
							for(l=0;l<2;l++) {
								table[v][0]=i;
								table[v][1]=j;
								table[v][2]=k;
								table[v][3]=l;
								v++;
							}
						}
					}
				}
			}
			for(i=cont;i<subs.length;i++) {
				balance = 0;
				stop = true;
				for(j=1;j<subs[i].length()-1&&stop;j++) {
					if(subs[i].charAt(j)=='(') {balance--;}
					if(subs[i].charAt(j)==')') {balance++;}
					if((subs[i].charAt(j)=='+'||subs[i].charAt(j)=='-'||subs[i].charAt(j)=='>'||subs[i].charAt(j)=='.')&&balance==0){
						stop = false;
					}
				}
				j--;
				start = value(subs, subs[i].substring(1, j),i);
				end = value(subs, subs[i].substring(j+1, subs[i].length()-1),i);
				if(subs[i].charAt(j)=='-') {
					for(k=0;k<pow;k++) table[k][i] = not(table[k][end]);
				} else if(subs[i].charAt(j)=='+') {
					for(k=0;k<pow;k++) table[k][i] = or(table[k][start],table[k][end]);
				} else if(subs[i].charAt(j)=='.') {
					for(k=0;k<pow;k++) table[k][i] = and(table[k][start],table[k][end]);
				} else {
					for(k=0;k<pow;k++) table[k][i] = ifthen(table[k][start],table[k][end]);
				}
			}
			bw.write("Tabela #"+t);bw.newLine();
			dash(subs, subs.length, bw);
			bw.write("|");
			for(i=0;i<subs.length;i++) {
				bw.write(subs[i]+"|");
			}
			bw.newLine();
			dash(subs, subs.length, bw);
			for(i=0;i<pow(cont);i++) {
				bw.write("|");
				for(j=0;j<subs.length;j++) {
					for(k=0;k<subs[j].length()-1;k++){
						bw.write(" ");
					}
					bw.write(""+table[i][j]);
					bw.write("|");
				}
				bw.newLine();
				dash(subs, subs.length, bw);	
			}
			logic(table,pow,subs.length,bw);
		}
		bw.close();
		br.close();
		fw.close();
		fr.close();
	}
	public static void dash(String[] subs, int length,BufferedWriter  bw) throws IOException {
		bw.write("-");
		for(int j=0;j<length;j++) {
			for(int k=0;k<subs[j].length();k++){
				bw.write("-");
			}
			bw.write("-");
		}
		bw.newLine();
	}
	public static void logic(int[][] array,int cont,int length,BufferedWriter bw) throws IOException {
		int zero=0, one=0;
		for(int i=0;i<cont;i++){
			if(array[i][length-1]==0)zero++;
			else one++;
		}
		if(one==0) {
			bw.write("insatisfativel e refutavel");bw.newLine();
		} else if(zero==0) {
			bw.write("satisfativel e tautologia");bw.newLine();
		} else {
			bw.write("satisfativel e refutavel");bw.newLine();
		}
		bw.newLine();
	}
	public static int value(String[] subs, String sub, int index) {
		for(int i=0;i<index;i++) {
			if(subs[i].equals(sub)) {
				return i;
			}
		}
		return -2;
	}

	public static int not(int a) {
		if(a==0) return 1;
		return 0;
	}
	public static int or(int a, int b) {
		if(a==1||b==1) return 1;
		return 0;
	}
	public static int and(int a, int b) {
		if(a==1&&b==1) return 1;
		return 0;
	}
	public static int ifthen(int a, int b) {
		if(a==0||b==1) return 1;
		return 0;
	}
	public static int pow(int n) {
		if(n==1) return 2;
		if(n==2) return 4;
		if(n==3) return 8;
		if(n==4) return 16;
		return -1;
	}
	public static void sort(String[] subs) {
		String aux;
		for(int i=0;i<subs.length;i++) {
			for(int j=0;j<subs.length-1;j++) {
				if(subs[j].length()>subs[j+1].length()) {
					aux = subs[j];
					subs[j] = subs[j+1];
					subs[j+1] = aux;
				} else if(subs[j].equals(subs[j+1])) {
					subs[j+1] = "#";
				} else if(subs[j].length()==subs[j+1].length()) { 
					for(int k=0;k<subs[j].length();k++) {
						if(subs[j].charAt(k)>subs[j+1].charAt(k)) {
							aux = subs[j];
							subs[j] = subs[j+1];
							subs[j+1] = aux;
							k = subs[j].length();
						} else if(subs[j].charAt(k)<subs[j+1].charAt(k)) {
							k = subs[j].length();
						}
					}
				}
			}
		}
	}
}