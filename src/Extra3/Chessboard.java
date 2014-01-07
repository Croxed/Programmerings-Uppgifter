package Extra3;


public class Chessboard {
	/**
	 * 
	 * @author Oscar
	 *
	 */
	
	
	/**
	 * @param Kastar ett Exception för en otillåten plats på spelplanen
	 */
    private class NotValidFieldException extends Exception {
        public NotValidFieldException(){}
        public NotValidFieldException(String s) {
            super(s);
        }

    }
	
	public static class Field
	{
		private char row;
		private byte column;
		private Chesspiece piece = null; 
		private boolean marked = false;
		
		/**
		 * @param row för det fältet
		 * @param column för det fältet
		 */
		public Field (char row, byte column) {
			this.row = row;
			this.column = column;
		} 
		
		/**
		 * 
		 * @param Lägger till en spelpjäs
		 */
		public void put (Chesspiece piece) {
			this.piece = piece;
		} 
		
		/**
		 * @param Tar bort pjäs från spelplanen
		 * @return Borttagen pjäs
		 */
		public Chesspiece take () {
			Chesspiece deleted = this.piece;
			this.piece = null;
			return deleted;
		}
		
		/**
		 * @param Markerar en plats på spelplanen 
		 */
		public void mark () {
			marked = true;
		}
		
		/**
		 * @param Avmarkerar en plats på spelplanen
		 */
		public void unmark () {
			marked = false;
		}
		
		/**
		 * @param Skriver ut en markering om platsen är "marked"
		 * @param Annars skriver den ut pjäsen
		 * @return Sträng eller spelpjäs
		 */
		public String toString ()
		{
		String s = (marked)? "xx " : "-- ";
		return (piece == null)? s : piece.toString (); 
		}
	}

	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;
	public static final int FIRST_ROW = 'a';
	public static final int  FIRST_COLUMN = 1;
	 
	private Field[][] fields;
	
	/**
	 * @param Chessboard Skapar en spelplan med x antal rader och kolumner
	 * @param Kolumner är tal
	 * @param Rader är bokstäver, börjar på a
	 */
	public Chessboard ()
	{ 
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS]; char row = 0;
		byte column = 0;
		for (int r = 0; r < NUMBER_OF_ROWS; r++)
		{
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
		
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) 
			{
				fields[r][c] = new Field (row, column);
				column++; 
			}
		} 
	}
	
	/**
	 * @param toString  - Skriver ut Chessboard
	 */
	public String toString () {
		for(int i=-1; i < NUMBER_OF_ROWS; i++)
			{
				if(i == -1)
				{
					System.out.println("  1  2  3  4  5  6  7  8");
				}
				
				else
				{
					System.out.print(Character.toChars(FIRST_ROW + i));
					System.out.print(" ");
					for(int i2=0; i2 < NUMBER_OF_COLUMNS; i2++)
					{
						System.out.print(fields[i][i2]);
						
					}
				System.out.println("\n");
				}

			}
		return "";
	}
	
	/**
	 * @param isValidField Kollar om den angivna platsen finns på spelplanen
	 * @return true : false
	 */
	public boolean isValidField (char row, byte column) 
	{
		if((char) row <= 104 && (char) row >= 94 && column < 8 && column >= 0){
			return true;
		}
		else 
		{
			return false;
		}
	}

	public abstract class Chesspiece
	{
		private char color; 
		// w - white, b - black
		
		private char name;
		// K - King, Q - Queen, R - Rook, B - Bishop, N - Knight, 
		// P – Pawn
		
		protected char row = 0; 
		protected byte column = -1;
		
		protected Chesspiece (char color, char name) {
			this.color = color;
			this.name = name;
		}
		
		public String toString ()
		{
			return "" + color + name; 
		}
		
		public boolean isOnBoard ()
		{
			return Chessboard.this.isValidField (row, column);
		}
		

		public void moveTo (char row, byte column)
				throws NotValidFieldException
				{
					if (!Chessboard.this.isValidField (row, column))
						throw new NotValidFieldException ("bad field: " + row + column);
					
						this.row = row; 
						this.column = column;
						int r = row - FIRST_ROW;
						int c = column - FIRST_COLUMN; 
						Chessboard.this.fields[r][c].put (this);
				}
		
		
		public void moveOut () {
			if(isOnBoard())
			{
				Chessboard.this.fields[row - FIRST_ROW][column].take();
			}
		}
		
		public abstract void markReachableFields ();
		public abstract void unmarkReachableFields (); 
	}
	
	public class Pawn extends Chesspiece
	{
		public Pawn (char color, char name)
		{
			super (color, name);
		}
		/**
		 * @param Markerar fälten för bonde
		 */
		public void markReachableFields ()
		{
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col)) 
			{
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN; 
				Chessboard.this.fields[r][c].mark ();
			} 
		}
		
		/**
		 * @param Avmarkerar fälten för bonde
		 */
		public void unmarkReachableFields ()
		{
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col)) 
			{
			int r = row - FIRST_ROW;
			int c = col - FIRST_COLUMN; 
			Chessboard.this.fields[r][c].unmark ();
			} 
		}
	}
	
	public class Rook extends Chesspiece {

		public Rook(char color, char name) 
		{
			super(color, name);
		}

		/**
		 * @param Markerar fälten för torn
		 */
		public void markReachableFields()
		{
			// Går längst med hela raden och märker den
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				if (Chessboard.this.isValidField(row, (byte) i))
				{
					Chessboard.this.fields[row - FIRST_ROW][(byte) i].mark();
				}
			}

			// Går längst med hela kolumnen och märker den
			for(int i2 = 0; i2 < 8; i2++)
			{
				if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
				{
					Chessboard.this.fields[i2][this.column].mark();
				}
			}
		}
		
		/**
		 * @param Avmarkerar fälten för torn
		 */
		public void unmarkReachableFields()
		{
			// Går längst med hela raden och märker den
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				if (Chessboard.this.isValidField(row, (byte) i))
				{
					Chessboard.this.fields[row - FIRST_ROW][(byte) i].unmark();
				}
			}

			// Går längst med hela kolumnen och märker den
			for(int i2 = 0; i2 < 8; i2++)
			{
				if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
				{
					Chessboard.this.fields[i2][this.column].unmark();
				}
			}		
		}
		
	}
	public class Knight extends Chesspiece {

		public Knight(char color, char name) 
		{
			super(color, name);
		}

		/**
		 * @param Markerar fälten för häst
		 */
		public void markReachableFields() {
			if(Chessboard.this.isValidField((char) (row + 2), (byte) (column + 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 2][column + 1].mark();
			}
			if(Chessboard.this.isValidField((char) (row + 2), (byte) (column - 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 2][column - 1].mark();
			}
			
			
			if(Chessboard.this.isValidField((char) (row - 2), (byte) (column + 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 2][column + 1].mark();
			}
			if(Chessboard.this.isValidField((char) (row - 2), (byte) (column - 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 2][column - 1].mark();
			}
			
			if(Chessboard.this.isValidField((char) (row + 1), (byte) (column + 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 1][column + 2].mark();
			}
			if(Chessboard.this.isValidField((char) (row + 1), (byte) (column - 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 1][column - 2].mark();
			}
			
			if(Chessboard.this.isValidField((char) (row - 1), (byte) (column + 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 1][column + 2].mark();
			}
			if(Chessboard.this.isValidField((char) (row - 1), (byte) (column - 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 1][column - 2].mark();
			}
		}

		/**
		 * @param Avmarkerar fälten för häst
		 */
		public void unmarkReachableFields() {
			if(Chessboard.this.isValidField((char) (row + 2), (byte) (column + 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 2][column + 1].unmark();
			}
			if(Chessboard.this.isValidField((char) (row + 2), (byte) (column - 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 2][column - 1].unmark();
			}
			
			
			if(Chessboard.this.isValidField((char) (row - 2), (byte) (column + 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 2][column + 1].unmark();
			}
			if(Chessboard.this.isValidField((char) (row - 2), (byte) (column - 1)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 2][column - 1].unmark();
			}
			
			if(Chessboard.this.isValidField((char) (row + 1), (byte) (column + 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 1][column + 2].unmark();
			}
			if(Chessboard.this.isValidField((char) (row + 1), (byte) (column - 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) + 1][column - 2].unmark();
			}
			
			if(Chessboard.this.isValidField((char) (row - 1), (byte) (column + 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 1][column + 2].unmark();
			}
			if(Chessboard.this.isValidField((char) (row - 1), (byte) (column - 2)))
			{
				Chessboard.this.fields[(row - FIRST_ROW) - 1][column - 2].unmark();
			}
			
		}
		
	}
	public class Bishop extends Chesspiece {

		public Bishop(char color, char name) 
		{
			super(color, name);
		}

		/**
		 * @param Markerar fälten för löpare
		 */
		public void markReachableFields() {
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				
				if(Chessboard.this.isValidField((char) (row + i), (byte)(column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column + i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column - i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row + i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column - i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column + i].mark();
				}
			}
		}

		/**
		 * @param Avmarkerar fälten för löpare
		 */
		public void unmarkReachableFields() {
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				
				if(Chessboard.this.isValidField((char) (row + i), (byte)(column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column + i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column - i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row + i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column - i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column + i].unmark();
				}
			}
		}
		
	}
	public class Queen extends Chesspiece {

		public Queen(char color, char name) 
		{
			super(color, name);
		}

		/**
		 * @param Markerar fälten för Drottning
		 */
		public void markReachableFields() {
			for(int i = 0; i < NUMBER_OF_ROWS; i++)
			{
				
				if(Chessboard.this.isValidField((char) (row + i), (byte)(column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column + i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column - i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row + i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column - i].mark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column + i].mark();
				}
			}
			
			for(int i = 0; i < NUMBER_OF_ROWS; i++)
			{
				if (Chessboard.this.isValidField(row, (byte) i))
				{
					Chessboard.this.fields[row - FIRST_ROW][(byte) i].mark();
				}
			}

			for(int i2 = 0; i2 < 8; i2++)
			{
				if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
				{
					Chessboard.this.fields[i2][this.column].mark();
				}
			}
		}

		/**
		 * @param Avmarkerar fälten för Drottning
		 */
		public void unmarkReachableFields() {
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				
				if(Chessboard.this.isValidField((char) (row + i), (byte)(column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column + i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column - i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row + i), (byte) (column - i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) + i][column - i].unmark();
				}
				
				if(Chessboard.this.isValidField((char) (row - i), (byte) (column + i)))
				{
					Chessboard.this.fields[(row - FIRST_ROW) - i][column + i].unmark();
				}
			}
			
			for(int i = 0; i <= ('h' - FIRST_ROW); i++)
			{
				if (Chessboard.this.isValidField(row, (byte) i))
				{
					Chessboard.this.fields[row - FIRST_ROW][(byte) i].unmark();
				}
			}

			for(int i2 = 0; i2 < 8; i2++)
			{
				if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
				{
					Chessboard.this.fields[i2][this.column].unmark();
				}
			}
		}
		
	}
	public class King extends Chesspiece {

		public King(char color, char name) 
		{
			super(color, name);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param Markerar fälten för kung
		 */
		public void markReachableFields() {
			// TODO Auto-generated method stub
			
		}

		/**
		 * @param Avmarkerar fälten för Kung
		 */
		public void unmarkReachableFields() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public static void main(String [] args) throws NotValidFieldException
	{
		Chessboard chessBoard = new Chessboard ();
		System.out.println (chessBoard + "\n");
		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6]; 
		pieces[0] = chessBoard.new Pawn ('w', 'P');
		pieces[0].moveTo((char) 'a', (byte) 4);
		System.out.println(chessBoard);
		pieces[1] = chessBoard.new Rook ('b', 'R');
		pieces[2] = chessBoard.new Queen ('w', 'Q');
		pieces[2].moveTo((char) 'b', (byte) 3);
		pieces[2].markReachableFields();
		System.out.println(chessBoard);
		pieces[3] = chessBoard.new Bishop ('w', 'B'); 
		pieces[4] = chessBoard.new King ('b', 'K'); 
		pieces[5] = chessBoard.new Knight ('w', 'N');
	}
	
}

	

