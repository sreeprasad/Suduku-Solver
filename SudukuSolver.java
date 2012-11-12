import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
public class SudukuSolver {
	

	int UNASSIGNED=-1;
	int gridLength=9;
	int gridBreath=9;
	int gridSize=9;		
	int [][] grid = new int[gridSize][gridSize];
	String fileName;
	
	 	public void showGrid(){
		
				for(int i=0;i<gridSize;i++){
					for(int j=0;j<gridSize;j++){
						if(grid[i][j]==-1){
							System.out.print("_|");
						}else{
							System.out.print(grid[i][j]+"|");					
						}
					}
						System.out.println("");
				}
		
		}
	
	
		public void writeFile(){
		try{
			
			File file = new File("solvedSuduku.txt");
			//File file = new File(fileName);			
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
 			
			for(int i=0;i<gridSize;i++){
				for(int j=0;j<gridSize;j++){
					//System.out.print("writing "+grid[i][j]);
					bw.write(grid[i][j]+"|");					
				}
 					bw.write("\n");
			}

			bw.close();
			System.out.println("Done");
			
			}catch(IOException e){
				System.out.println(e.toString());
			}			
		}
	
	
	
		public void readFile(String fileName){
			try{
				
				this.fileName=fileName;
				FileInputStream in = new FileInputStream(fileName);
				BufferedReader buf = new BufferedReader(new InputStreamReader(in));
				String st="";
				int row=0;
				while((st=buf.readLine())!=null){
 
					String []array=st.split("\\|");
					int Narray = array.length;
 
  					
					for(int i=0;i<Narray;i++){
						if(array[i].equals("_")){
  							grid[row][i]=UNASSIGNED;
							//System.out.print("adding "+(-1)+" to grid");
						}else{
							//System.out.print("adding "+array[i]+" to grid");
  							grid[row][i]=Integer.parseInt(array[i]);
						}
 					}
					//System.out.println("");
					row++;
				}
 
			}catch(IOException e){
				System.out.println(e.toString());
			}
			
			
		}
		
		
		public boolean usedInRow(int[][] grid, int row, int num){
		//	System.out.println("checking for "+num+" in row "+row);
			
			for(int i=0;i<gridSize;i++){
				if(grid[row][i]==num){
		//	System.out.println(num + "found in  row " +row);			
					return true;
				}
			}	
		//	System.out.println("no "+num+" found in  row "+row);			
			return false;
		}
		
		public boolean usedInCol(int[][] grid,int col,int num){
				//		System.out.println("checking for "+num+" in column "+col);
			for(int i=0;i<gridSize;i++){
				if(grid[i][col]==num){
					return true;
				}
			}
			return false;
		}
				
		public boolean usedInGrid(int [][] grid, int rowStart, int colStart, int num){
		//	System.out.println("checking for "+num+" in rowStart "+rowStart+" and col start "+colStart);
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(grid[i+rowStart][j+colStart]==num){
						return true;
					}
				}
			}
			
			return false;
		}		
				
				
		public boolean noConflicts(int [][]grid,int row, int col,int num){
			
 			 if (usedInRow(grid,row,num)){
				//System.out.println("returning false because "+num+" was found in row "+row);
				return false;
			}else if (usedInCol(grid,col,num)) {
				//System.out.println("returning false because "+num+" was found in col "+col);				
				return false;
			}else if (usedInGrid(grid,row-row%3,col-col%3,num)){
				//System.out.println("returning false because "+num+" was found in grid with row "+(row-row%3)+" and col "+(col-col%3));
				return false;
			}
			return true;
		}
		
 
 
		
		
		public boolean solveSuduku(int [][] grid){
 				
 				/** base case */
				/*if(!findUnAssignedLocationInGrid(grid)){
					return true;
				}*/

				int row=-1,col=-1;
				
				/** find unassinged cell */
				
				for(int i=0;i<gridSize;i++){
					for(int j=0;j<gridSize;j++){
						if(grid[i][j]==UNASSIGNED){
							//System.out.println("found "+i+" and "+j);
							row=i;
							col=j;
							break;
						}
					}
					if((row!=-1) && (col!=-1))
						break;
				}
				
				if((row==-1)&&(col==-1)){
					//System.out.println("no row and col left untouched");
					return true;
					}

 					//System.out.println("adding number to "+row+" and "+col);

 				for(int i=1;i<=9;i++){
					if(noConflicts(grid,row,col,i)){
						//System.out.println("adding value "+i+" to grid at "+row+","+col);
						grid[row][col]=i;
						if(solveSuduku(grid)){
							return true;
						}
						grid[row][col]=UNASSIGNED;
					}
				}
			 
			return false;
		}
	
	
		 
	public boolean findUnAssignedLocationInGrid(int[][] grid){
			
			for(int i=0;i<gridSize;i++){
				for(int j=0;j<gridSize;j++){
 					if(grid[i][j]==UNASSIGNED){
							//System.out.println("unassigned location is "+grid[i][j]+" at position "+i+","+j);
						return true;
					}
				}
			}
	 	return false;
	}
}