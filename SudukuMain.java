 
public class SudukuMain{
	
	
	public static void main(String abd[]){
		
		SudukuSolver s = new SudukuSolver();
		
	 
			
	 	s.readFile("suduku.txt");
		System.out.println("-------");
		s.showGrid();
 		System.out.println(s.solveSuduku(s.grid));
		System.out.println("-------");
		s.showGrid();
		s.writeFile();
	}
	
	
}