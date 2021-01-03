package xu.problem.block;

public enum PieceColor {

    /** EMPTY: no piece.
     *  WHITE, BLACK: piece colors. */
    EMPTY,
    WHITE {
        @Override
        public PieceColor opposite() {
            return BLACK;
        }

        @Override
        public boolean isPiece() {
            return true;
        }
    },
    BLACK {
        @Override
        public PieceColor opposite() {
            return WHITE;
        }

        @Override
        public boolean isPiece() {
            return true;
        }
    };

    /** Return the piece color of my opponent, if defined. */
    public PieceColor opposite() {
        //throw new UnsupportedOperationException();
        return this.opposite();
    }

    /** Return true if I denote a piece rather than an empty square. */
    public boolean isPiece() {
        if(this == BLACK || this == WHITE)
            return true;
        else
            return false;
    }

    public String shortName() {
        return this == BLACK ? "B" : this == WHITE ? "W" : "E";
    }
    
    public byte byteValue() {
    	return this == BLACK ? (byte) 1 : this == WHITE ? (byte)2 : (byte)0;
    }
    
    public static int pieceCount() {
    	return 3; //棋子的个数（包括空格）
    }
    
}
