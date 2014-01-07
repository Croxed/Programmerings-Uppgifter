package Backup;



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
		public void unMark () {
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
		System.out.println("  1  2  3  4  5  6  7  8");
		for(int i=0; i < NUMBER_OF_ROWS; i++)
			{

					System.out.print(Character.toChars(FIRST_ROW + i));
					System.out.print(" ");
					for(int i2=0; i2 < NUMBER_OF_COLUMNS; i2++)
					{
						System.out.print(fields[i][i2]);
						
					}
				System.out.println("\n");
			}
		return "";
	}
	
	/**
	 * @param isValidField Kollar om den angivna platsen finns på spelplanen
	 * @return true : false
	 */
	public boolean isValidField (char row, byte column) 
	{
		 if(row >= 97 && row <= 104 && column <= 7 && column >= 0)
		 {
			return true;
		}

			return false;
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
		
		/**
		 * @param color - Lägger till färg på spelpjäsen
		 * @param name - Sätter ett namn på pjäsen
		 */
		protected Chesspiece (char color, char name) {
			this.color = color;
			this.name = name;
		}
		
		/**
		 * @param Skriver ut färg och namn
		 * *return Returnar färg och namn i form av en sträng
		 */
		public String toString ()
		{
			return "" + color + name; 
		}
		
		/**
		 * @param Kollar om pjäsen kan finnas på spelplanen
		 * @return true : false 
		 */
		public boolean isOnBoard ()
		{
			return Chessboard.this.isValidField (row, column);
		}
		

		/**
		 * @param moveTo - Flyttar pjäsen till den platsen
		 * @throws NotValidFieldException - En exception om pjäsen är utanför spelplanen
		 */
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
		
		
		/**
		 * @param moveOut - Tar bort en s
		 */
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
            public Pawn(char color, char namn)
            {
                    super(color, namn);
            }

            /**
             * @param Markerar fälten för bonde
             */
            public void markReachableFields()
            {
                    char row = (char) (this.row + 1);
                    if (Chessboard.this.isValidField(row, column))
                    {
                            Chessboard.this.fields[row - FIRST_ROW][column].mark();
                    }
            }

            /**
             * @param Avmarkerar fälten för bonde
             */
            public void unmarkReachableFields()
            {
                    char row = (char) (this.row + 1);
                    if (Chessboard.this.isValidField(row, column))
                    {
                            Chessboard.this.fields[row - FIRST_ROW][column].unMark();
                    }
            }
    }
	
    public class Rook extends Chesspiece {

        public Rook(char color, char name) {
                super(color, name);
        }

        /**
         * @param Markerar fälten för torn
         */
        public void markReachableFields()
        {
                for(int i = 0; i <= ('h' - FIRST_ROW); i++)
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
         * @param Avarkerar fälten för bonde
         */
        public void unmarkReachableFields()
        {
                for(int i = 0; i <= ('h' - FIRST_ROW); i++)
                {
                        if (Chessboard.this.isValidField(row, (byte) i))
                        {
                                Chessboard.this.fields[row - FIRST_ROW][(byte) i].unMark();
                        }
                }

                for(int i2 = 0; i2 < 8; i2++)
                {
                        if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
                        {
                                Chessboard.this.fields[i2][this.column].unMark();
                        }
                }                
        }
}

public class Knight extends Chesspiece {

        public Knight(char color, char name) {
                super(color, name);
        }

        /**
         * @param Markerar fälten för häst
         */
        public void markReachableFields()
        {
                byte col = (byte) (column + 2);
                char markedRow = (char) (row + 1);
                if (Chessboard.this.isValidField((markedRow), col))
                {
                        Chessboard.this.fields[markedRow - FIRST_ROW][col].mark();
                }

                byte col2 = (byte) (column + 2);
                char markedRow2 = (char) (row - 1);
                if (Chessboard.this.isValidField((markedRow2), col2))
                {
                        Chessboard.this.fields[markedRow2 - FIRST_ROW][col2].mark();
                }

                byte col3 = (byte) (column + 1);
                char markedRow3 = (char) (row - 2);
                if (Chessboard.this.isValidField((markedRow3), col3))
                {
                        Chessboard.this.fields[markedRow3 - FIRST_ROW][col3].mark();
                }

                byte col4 = (byte) (column - 1);
                char markedRow4 = (char) (row - 2);
                if (Chessboard.this.isValidField((markedRow4), col4))
                {
                        Chessboard.this.fields[markedRow4 - FIRST_ROW][col4].mark();
                }

                byte col5 = (byte) (column - 2);
                char markedRow5 = (char) (row + 1);
                if (Chessboard.this.isValidField((markedRow5), col5))
                {
                        Chessboard.this.fields[markedRow5 - FIRST_ROW][col5].mark();
                }

                byte col6 = (byte) (column - 2);
                char markedRow6 = (char) (row - 1);
                if (Chessboard.this.isValidField((markedRow6), col6))
                {
                        Chessboard.this.fields[markedRow6 - FIRST_ROW][col6].mark();
                }

                byte col7 = (byte) (column + 1);
                char markedRow7 = (char) (row + 2);
                if (Chessboard.this.isValidField((markedRow7), col7))
                {
                        Chessboard.this.fields[markedRow7 - FIRST_ROW][col7].mark();
                }

                byte col8 = (byte) (column - 1);
                char markedRow8 = (char) (row + 2);
                if (Chessboard.this.isValidField((markedRow8), col8))
                {
                        Chessboard.this.fields[markedRow8 - FIRST_ROW][col8].mark();
                }

        }

        /**
         * @param Avmarkerar fälten för häst
         */
        public void unmarkReachableFields()
        {
            byte col = (byte) (column + 2);
            char markedRow = (char) (row + 1);
            if (Chessboard.this.isValidField((markedRow), col))
            {
                    Chessboard.this.fields[markedRow - FIRST_ROW][col].unMark();
            }

            byte col2 = (byte) (column + 2);
            char markedRow2 = (char) (row - 1);
            if (Chessboard.this.isValidField((markedRow2), col2))
            {
                    Chessboard.this.fields[markedRow2 - FIRST_ROW][col2].unMark();
            }

            byte col3 = (byte) (column + 1);
            char markedRow3 = (char) (row - 2);
            if (Chessboard.this.isValidField((markedRow3), col3))
            {
                    Chessboard.this.fields[markedRow3 - FIRST_ROW][col3].unMark();
            }

            byte col4 = (byte) (column - 1);
            char markedRow4 = (char) (row - 2);
            if (Chessboard.this.isValidField((markedRow4), col4))
            {
                    Chessboard.this.fields[markedRow4 - FIRST_ROW][col4].unMark();
            }

            byte col5 = (byte) (column - 2);
            char markedRow5 = (char) (row + 1);
            if (Chessboard.this.isValidField((markedRow5), col5))
            {
                    Chessboard.this.fields[markedRow5 - FIRST_ROW][col5].unMark();
            }

            byte col6 = (byte) (column - 2);
            char markedRow6 = (char) (row - 1);
            if (Chessboard.this.isValidField((markedRow6), col6))
            {
                    Chessboard.this.fields[markedRow6 - FIRST_ROW][col6].unMark();
            }

            byte col7 = (byte) (column + 1);
            char markedRow7 = (char) (row + 2);
            if (Chessboard.this.isValidField((markedRow7), col7))
            {
                    Chessboard.this.fields[markedRow7 - FIRST_ROW][col7].unMark();
            }

            byte col8 = (byte) (column - 1);
            char markedRow8 = (char) (row + 2);
            if (Chessboard.this.isValidField((markedRow8), col8))
            {
                    Chessboard.this.fields[markedRow8 - FIRST_ROW][col8].unMark();
            }
        }
}


public class Bishop extends Chesspiece {

        public Bishop(char color, char name) {
                super(color, name);
        }

        /**
         * @param Markerar fälten för löpare
         */
        public void markReachableFields()
        {

                int i = 1;
                byte col = (column);
                char markedRow = (row);

                while(Chessboard.this.isValidField((char) (markedRow + i),(byte) (col + i)))
                {
                        byte col1 = (byte) (column + i);
                        char markedRow1 = (char) (row + i);
                        Chessboard.this.fields[markedRow1 - FIRST_ROW][col1].mark();
                        i++;
                }



                int i1 = 1;
                byte col1 = (column);
                char markedRow1 = (row);

                while(Chessboard.this.isValidField((char) (markedRow1 - i1),(byte) (col1 - i1)))
                {
                        byte col11 = (byte) (column - i1);
                        char markedRow11 = (char) (row - i1);
                        Chessboard.this.fields[markedRow11 - FIRST_ROW][col11].mark();
                        i1++;
                }


                int i11 = 1;
                byte col11 = (column);
                char markedRow11 = (row);

                while(Chessboard.this.isValidField((char) (markedRow11 - i11),(byte) (col11 + i11)))
                {
                        byte col111 = (byte) (column + i11);
                        char markedRow111 = (char) (row - i11);
                        Chessboard.this.fields[markedRow111 - FIRST_ROW][col111].mark();
                        i11++;
                }


                int i111 = 1;
                byte col111 = (column);
                char markedRow111 = (row);

                while(Chessboard.this.isValidField((char) (markedRow111 + i111),(byte) (col111 - i111)))
                {
                        byte col1111 = (byte) (column - i111);
                        char markedRow1111 = (char) (row + i111);
                        Chessboard.this.fields[markedRow1111 - FIRST_ROW][col1111].mark();
                        i111++;
                }


        }

        /**
         * @param Avmarkerar fälten för löpare
         */
        public void unmarkReachableFields()
        {
                int i = 1;
                byte col = (column);
                char markedRow = (row);

                while(Chessboard.this.isValidField((char) (markedRow + i),(byte) (col + i)))
                {
                        byte col1 = (byte) (column + i);
                        char markedRow1 = (char) (row + i);
                        Chessboard.this.fields[markedRow1 - FIRST_ROW][col1].unMark();
                        i++;
                }



                int i1 = 1;
                byte col1 = (column);
                char markedRow1 = (row);

                while(Chessboard.this.isValidField((char) (markedRow1 - i1),(byte) (col1 - i1)))
                {
                        byte col11 = (byte) (column - i1);
                        char markedRow11 = (char) (row - i1);
                        Chessboard.this.fields[markedRow11 - FIRST_ROW][col11].unMark();
                        i1++;
                }


                int i11 = 1;
                byte col11 = (column);
                char markedRow11 = (row);

                while(Chessboard.this.isValidField((char) (markedRow11 - i11),(byte) (col11 + i11)))
                {
                        byte col111 = (byte) (column + i11);
                        char markedRow111 = (char) (row - i11);
                        Chessboard.this.fields[markedRow111 - FIRST_ROW][col111].unMark();
                        i11++;
                }


                int i111 = 1;
                byte col111 = (column);
                char markedRow111 = (row);

                while(Chessboard.this.isValidField((char) (markedRow111 + i111),(byte) (col111 - i111)))
                {
                        byte col1111 = (byte) (column - i111);
                        char markedRow1111 = (char) (row + i111);
                        Chessboard.this.fields[markedRow1111 - FIRST_ROW][col1111].unMark();
                        i111++;
                }                        
        }
	}

public class Queen extends Chesspiece {

        public Queen(char color, char name) {
                super(color, name);
        }

        /**
         * @param Markerar fälten för Drottning
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



                int i = 1;
                byte col = (column);
                char markedRow = (row);

                while(Chessboard.this.isValidField((char) (markedRow + i),(byte) (col + i)))
                {
                        byte col1 = (byte) (column + i);
                        char markedRow1 = (char) (row + i);
                        Chessboard.this.fields[markedRow1 - FIRST_ROW][col1].mark();
                        i++;
                }



                int i1 = 1;
                byte col1 = (column);
                char markedRow1 = (row);

                while(Chessboard.this.isValidField((char) (markedRow1 - i1),(byte) (col1 - i1)))
                {
                        byte col11 = (byte) (column - i1);
                        char markedRow11 = (char) (row - i1);
                        Chessboard.this.fields[markedRow11 - FIRST_ROW][col11].mark();
                        i1++;
                }


                int i11 = 1;
                byte col11 = (column);
                char markedRow11 = (row);

                while(Chessboard.this.isValidField((char) (markedRow11 - i11),(byte) (col11 + i11)))
                {
                        byte col111 = (byte) (column + i11);
                        char markedRow111 = (char) (row - i11);
                        Chessboard.this.fields[markedRow111 - FIRST_ROW][col111].mark();
                        i11++;
                }


                int i111 = 1;
                byte col111 = (column);
                char markedRow111 = (row);

                while(Chessboard.this.isValidField((char) (markedRow111 + i111),(byte) (col111 - i111)))
                {
                        byte col1111 = (byte) (column - i111);
                        char markedRow1111 = (char) (row + i111);
                        Chessboard.this.fields[markedRow1111 - FIRST_ROW][col1111].mark();
                        i111++;
                }

                System.out.println("Hej");
        }

        /**
         * @param Avmarkerar fälten för Drottning
         */
        public void unmarkReachableFields()
        {
                // Går längst med hela raden och märker den
                for(int i = 0; i <= ('h' - FIRST_ROW); i++)
                {
                        if (Chessboard.this.isValidField(row, (byte) i))
                        {
                                Chessboard.this.fields[row - FIRST_ROW][(byte) i].unMark();
                        }
                }

                // Går längst med hela kolumnen och märker den
                for(int i2 = 0; i2 < 8; i2++)
                {
                        if (Chessboard.this.isValidField((char) (FIRST_ROW + i2), this.column))
                        {
                                Chessboard.this.fields[i2][this.column].unMark();
                        }
                }                


                int i = 1;
                byte col = (column);
                char markedRow = (row);

                while(Chessboard.this.isValidField((char) (markedRow + i),(byte) (col + i)))
                {
                        byte col1 = (byte) (column + i);
                        char markedRow1 = (char) (row + i);
                        Chessboard.this.fields[markedRow1 - FIRST_ROW][col1].unMark();
                        i++;
                }



                int i1 = 1;
                byte col1 = (column);
                char markedRow1 = (row);

                while(Chessboard.this.isValidField((char) (markedRow1 - i1),(byte) (col1 - i1)))
                {
                        byte col11 = (byte) (column - i1);
                        char markedRow11 = (char) (row - i1);
                        Chessboard.this.fields[markedRow11 - FIRST_ROW][col11].unMark();
                        i1++;
                }


                int i11 = 1;
                byte col11 = (column);
                char markedRow11 = (row);

                while(Chessboard.this.isValidField((char) (markedRow11 - i11),(byte) (col11 + i11)))
                {
                        byte col111 = (byte) (column + i11);
                        char markedRow111 = (char) (row - i11);
                        Chessboard.this.fields[markedRow111 - FIRST_ROW][col111].unMark();
                        i11++;
                }


                int i111 = 1;
                byte col111 = (column);
                char markedRow111 = (row);

                while(Chessboard.this.isValidField((char) (markedRow111 + i111),(byte) (col111 - i111)))
                {
                        byte col1111 = (byte) (column - i111);
                        char markedRow1111 = (char) (row + i111);
                        Chessboard.this.fields[markedRow1111 - FIRST_ROW][col1111].unMark();
                        i111++;
                }                        
        	}
	}

public class King extends Chesspiece 
	{

        public King(char color, char name) {
                super(color, name);
        }

        /**
         * @param Markerar fälten för Kung
         */
        public void markReachableFields()
        {
                char row = (char) (this.row + 1);
                if (Chessboard.this.isValidField(row, column))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column].mark();
                }

                char row1 = (char) (this.row - 1);
                if (Chessboard.this.isValidField(row1, column))
                {
                        Chessboard.this.fields[row1 - FIRST_ROW][column].mark();
                }

                byte column = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(row, column))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column].mark();
                }

                byte column1 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row, column1))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column1].mark();
                }

                char row11 = (char) (this.row - 1);
                byte column11 = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(row11, column11))
                {
                        Chessboard.this.fields[row11 - FIRST_ROW][column11].mark();
                }

                char row111 = (char) (this.row - 1);
                byte column111 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row111, column111))
                {
                        Chessboard.this.fields[row111 - FIRST_ROW][column111].mark();
                }

                byte column1111 = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(this.row, column1111))
                {
                        Chessboard.this.fields[this.row - FIRST_ROW][column1111].mark();
                }

                byte column11111 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row, column11111))
                {
                        Chessboard.this.fields[this.row - FIRST_ROW][column11111].mark();
                }
        }

        /**
         * @param Avmarkerar fälten för Kung
         */
        public void unmarkReachableFields()
        {
                char row = (char) (this.row + 1);
                if (Chessboard.this.isValidField(row, column))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column].unMark();
                }

                char row1 = (char) (this.row - 1);
                if (Chessboard.this.isValidField(row1, column))
                {
                        Chessboard.this.fields[row1 - FIRST_ROW][column].unMark();
                }

                byte column = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(row, column))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column].unMark();
                }

                byte column1 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row, column1))
                {
                        Chessboard.this.fields[row - FIRST_ROW][column1].unMark();
                }

                char row11 = (char) (this.row - 1);
                byte column11 = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(row11, column11))
                {
                        Chessboard.this.fields[row11 - FIRST_ROW][column11].unMark();
                }

                char row111 = (char) (this.row - 1);
                byte column111 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row111, column111))
                {
                        Chessboard.this.fields[row111 - FIRST_ROW][column111].unMark();
                }

                byte column1111 = (byte) (this.column + 1);
                if(Chessboard.this.isValidField(this.row, column1111))
                {
                        Chessboard.this.fields[this.row - FIRST_ROW][column1111].unMark();
                }

                byte column11111 = (byte) (this.column - 1);
                if(Chessboard.this.isValidField(row, column11111))
                {
                        Chessboard.this.fields[this.row - FIRST_ROW][column11111].unMark();
                }
        }
	}
	
    
  }

	


