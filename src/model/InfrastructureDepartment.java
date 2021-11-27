package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InfrastructureDepartment {

	private final static int DANGEROUS_SIZE = 160;
	public String FILE_SAVE_PATH="data/billboard.dat";
	
	private ArrayList<Billboard> billboardList;
	private String[]title;
	
	public InfrastructureDepartment() {
		title=null;
		billboardList= new ArrayList<Billboard>();
	}
	
	public void addBillboard(double w, double h, boolean iu, String b) {
		
		billboardList.add(new Billboard(w,h,iu,b));
		
	}
	
	@SuppressWarnings("unused")
	private void saveBillboards() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(billboardList);
		oos.close();
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loadBillboards() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			billboardList = (ArrayList<Billboard>) ois.readObject();
			ois.close();
		}
		
	}
	
	public void exportDangerousBillboardsReport(String fn) throws IOException {
		
		FileWriter fw= new FileWriter(fn,false);//false-> Reemplazar el archivo, true->Adicionar elementos al archivo
		fw.write("      ==========================="+"\n");
		fw.write("      DANGEROUS BILLBOARD REPORT"+"\n");
		fw.write("      ==========================="+"\n");
		
		fw.write("The dangerous billboard are:"+"\n");
		int count=0;
		int order=1;
		for(int i=0;i<billboardList.size();i++) {
			if(billboardList.get(i).calculateArea()>DANGEROUS_SIZE) {
				fw.write("      "+order+".Billboard "+billboardList.get(i).getBrand()+" with area: "+billboardList.get(i).calculateArea()+"\n");
				count++;
				order++;
			}
		}
		
		fw.close();
	}
	
	public void importData(String fn) throws IOException {
		
		if(billboardList==null) {
			try {
				BufferedReader br=new BufferedReader(new FileReader(fn));
				String line = br.readLine();
				int times=1;
				while(line !=null) {
					
					if(times!=1) {
						String[] attributes= line.split("|");
						double w=Double.parseDouble(attributes[0]);
						double h=Double.parseDouble(attributes[1]);
						boolean ui=Boolean.parseBoolean(attributes[2]);
						String b=attributes[3];
						addBillboard(w,h,ui,b);
					}
					else {
						title= line.split("|");
					}
			
				}
				br.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else {
			FileWriter fw= new FileWriter(fn,true);
			int i=billboardList.size()-1;
			Billboard myBillboard=billboardList.get(i);
			fw.write(myBillboard.getWeight()+"|"+myBillboard.getHeigth()+"|"+myBillboard.isInUse()+"|"+myBillboard.getBrand()+"\n");
			fw.close();
			
			saveBillboards();
		}
		
	}
	
	public void toString(String message) {
		
		System.out.println("      "+title[0]+"   "+title[1]+"   "+title[2]+"   "+title[3]);
		System.out.println();

		int count=0;
		for(int i=0;i<billboardList.size();i++) {
				System.out.println("      "+billboardList.get(i).getWeight()+"      "+billboardList.get(i).getHeigth()+"      "+billboardList.get(i).isInUse()+"      "+billboardList.get(i).getBrand());
				count++;
		}
		System.out.println("Total: "+count+" vallas");
	}
}
