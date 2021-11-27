package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.InfrastructureDepartment;

public class Main {
	
	public static final BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	public static final InfrastructureDepartment id= new InfrastructureDepartment();
	public static final Scanner sc=new Scanner(System.in);
	public static final String FILE_IMPORT = "data/BillboardDataExported.csv";
	public static final String FILE_EXPORT_TXT_PATH="data/exportedDataDanger.txt";

	public static void main(String[] args) throws IOException {
		
		
		boolean continues=true;
		boolean conti2=true;
		
		
		
		while(continues){
			System.out.println("\nChoose the menu that you want to use\n");
			System.out.println("1:Add a billbord");
			System.out.println("2:Show billbords");
			System.out.println("3:Export hazard report");
			System.out.println("4:Exit");
			int option=sc.nextInt();
			sc.nextLine();
			
 
			while(conti2) {

				switch(option) {

				case 1:
					menu();
					conti2=false;
					break;

				case 2:
					menu2();
					conti2=false;
					break;
					
				case 3:
					menu3();
					conti2=false;
					break;
					
				case 4:
					conti2=false;
					continues=false;
					
				default:
					System.out.println("This option is unvalid, try again");
					conti2=false;
				}
				
			}

		}
		
		
	}
	
	public static void menu() throws IOException {
		System.out.println("Use this notation to enter the data"+"\nheigth++weigth++(true if in use, false if not)++brand");
		String answer=br.readLine();
		String[] parts=answer.split("++");
		double w= Double.parseDouble(parts[1]);
		double h= Double.parseDouble(parts[0]);
		boolean iu=Boolean.parseBoolean(parts[2]);
		String b=parts[3];
		
		id.addBillboard(w,h,iu,b);
		id.importData(FILE_IMPORT);
	}
		
	public static void menu2() throws IOException {
		id.toString();
	}
	
	public static void menu3()throws IOException{
		id.exportDangerousBillboardsReport(FILE_EXPORT_TXT_PATH);
	}
}
