package org.cis120.checkers;

public class Checkers {
    private Piece[][] board;
    private int numTurns;
    private boolean playerBlack;
    private boolean gameOver;

    public Checkers() {
        reset();
    }

    public boolean playTurn(int c1, int r1, int c2, int r2) {
        boolean bool = false;
        if (!getCell(c1, r1).getIsPiece() || gameOver) {
            return false;
        }

        if (playerBlack) {
            bool = moveBlack(c1, r1, c2, r2);
        } else {
            bool = moveRed(c1, r1, c2, r2);
        }

        if (bool && !(checkWinnerRed() || checkWinnerBlack())) {
            playerBlack = !playerBlack;
            return true;
        }
        return false;
    }

    public boolean moveRed(int c1, int r1, int c2, int r2) {
        if (!board[r1][c1].getIsPiece()) {
            return false;
        }
        if (!board[r1][c1].getColor().equals("Red")) {
            return false;
        }
        if (r2 == r1 + 1 && (c2 == c1 - 1 || c2 == c1 + 1)) {
            if (!board[r2][c2].getIsPiece()) {
                Piece piece = getCell(c1, r1);
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                board[r2][c2] = piece2;
                board[r1][c1].flipPiece();
                numTurns++;
            } else if (board[r2][c2].getColor().equals("Black")) {
                if (!capture(c1, r1, c2, r2)) {
                    return false;
                }
            } else {
                return false;
            }
            if (r2 == board.length - 1) {
                board[r2][c2].promote();
            }
            return true;
        } else if (r2 == r1 - 1 && (c2 == c1 - 1 || c2 == c1 + 1) && board[r1][c1].getIsKing()) {
            if (!board[r2][c2].getIsPiece()) {
                Piece piece = getCell(c1, r1);
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                board[r2][c2] = piece2;
                board[r1][c1].flipPiece();
                numTurns++;
            } else if (board[r2][c2].getColor().equals("Black")) {
                if (!capture(c1, r1, c2, r2)) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean moveBlack(int c1, int r1, int c2, int r2) {
        if (!board[r1][c1].getIsPiece()) {
            return false;
        }
        if (!board[r1][c1].getColor().equals("Black")) {
            return false;
        }
        if ((r2 == r1 - 1) && ((c2 == c1 - 1) || (c2 == c1 + 1))) {
            if (!board[r2][c2].getIsPiece()) {
                Piece piece = getCell(c1, r1);
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                board[r2][c2] = piece2;
                board[r1][c1].flipPiece();
                numTurns++;
            } else if (board[r2][c2].getColor().equals("Red")) {
                if (!capture(c1, r1, c2, r2)) {
                    return false;
                }
            } else {
                return false;
            }
            if (r2 == 0) {
                board[r2][c2].promote();
            }
            return true;
        } else if (r2 == r1 + 1 && (c2 == c1 - 1 || c2 == c1 + 1) && board[r1][c1].getIsKing()) {
            if (!board[r2][c2].getIsPiece()) {
                Piece piece = getCell(c1, r1);
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                board[r2][c2] = piece2;
                board[r1][c1].flipPiece();
                numTurns++;
            } else if (board[r2][c2].getColor().equals("Red")) {
                if (!capture(c1, r1, c2, r2)) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean capture(int c1, int r1, int c2, int r2) {
        if (c2 != 0 && r2 != 0) {
            if (c2 == c1 + 1 && r2 == r1 + 1) {
                if (!board[r2 + 1][c2 + 1].getIsPiece()) {
                    board[r2][c2].flipPiece();
                    Piece piece = getCell(c1, r1);
                    Piece piece2 = new Piece(
                            piece.getColor(), piece.getIsPiece(), piece.getIsKing()
                    );
                    board[r2 + 1][c2 + 1] = piece2;
                    board[r1][c1].flipPiece();
                    if (r2 + 1 == board.length - 1 && !board[r2 + 1][c2 + 1].getIsKing()) {
                        board[r2 + 1][c2 + 1].promote();
                    }
                    numTurns++;
                    return true;
                }
            }
            if (c2 == c1 - 1 && r2 == r1 + 1) {
                if (!board[r2 + 1][c2 - 1].getIsPiece()) {
                    board[r2][c2].flipPiece();
                    Piece piece = getCell(c1, r1);
                    Piece piece2 = new Piece(
                            piece.getColor(), piece.getIsPiece(), piece.getIsKing()
                    );
                    board[r2 + 1][c2 - 1] = piece2;
                    board[r1][c1].flipPiece();
                    if (r2 + 1 == board.length - 1 && !board[r2 + 1][c2 - 1].getIsKing()) {
                        board[r2 + 1][c2 - 1].promote();
                    }
                    numTurns++;
                    return true;
                }
            }
            if (c2 == c1 + 1 && r2 == r1 - 1) {
                if (!board[r2 - 1][c2 + 1].getIsPiece()) {
                    board[r2][c2].flipPiece();
                    Piece piece = getCell(c1, r1);
                    Piece piece2 = new Piece(
                            piece.getColor(), piece.getIsPiece(), piece.getIsKing()
                    );
                    board[r2 - 1][c2 + 1] = piece2;
                    board[r1][c1].flipPiece();
                    if (r2 - 1 == 0 && !board[r2 - 1][c2 + 1].getIsKing()) {
                        board[r2 - 1][c2 + 1].promote();
                    }
                    numTurns++;
                    return true;
                }
            }
            if (c2 == c1 - 1 && r2 == r1 - 1) {
                if (!board[r2 - 1][c2 - 1].getIsPiece()) {
                    board[r2][c2].flipPiece();
                    Piece piece = getCell(c1, r1);
                    Piece piece2 = new Piece(
                            piece.getColor(), piece.getIsPiece(), piece.getIsKing()
                    );
                    board[r2 - 1][c2 - 1] = piece2;
                    board[r1][c1].flipPiece();
                    if (r2 - 1 == 0 && !board[r2 - 1][c2 - 1].getIsKing()) {
                        board[r2 - 1][c2 - 1].promote();
                    }
                    numTurns++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinnerRed() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getIsPiece()
                        && board[i][j].getColor().equals("Black")) {
                    return false;
                }
            }
        }
        gameOver = true;
        return true;
    }

    public boolean checkWinnerBlack() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getIsPiece()
                        && board[i][j].getColor().equals("Red")) {
                    return false;
                }
            }
        }
        gameOver = true;
        return true;
    }

    public void reset() {
        board = new Piece[8][8];
        numTurns = 0;
        playerBlack = true;
        gameOver = false;

        // Row 1, 3 Red Pieces
        for (int i = 0; i < 3; i = i + 2) {
            for (int j = 1; j < board.length; j = j + 2) {
                board[i][j] = new Piece("Red", true, false);
            }
        }

        // Row 2 Red Pieces
        for (int j = 0; j < board.length; j = j + 2) {
            board[1][j] = new Piece("Red", true, false);
        }

        // Row 6, 8 Black Pieces
        for (int i = 5; i < board.length; i = i + 2) {
            for (int j = 0; j < board.length; j = j + 2) {
                board[i][j] = new Piece("Black", true, false);
            }
        }

        // Row 7 Black Pieces
        for (int j = 1; j < board.length; j += 2) {
            board[6][j] = new Piece("Black", true, false);
        }

        // Row 1, 3, 5, 7 Empty
        for (int i = 0; i < board.length; i += 2) {
            for (int j = 0; j < board.length; j += 2) {
                board[i][j] = new Piece(" ", false, false);
            }
        }

        // Row 2, 4, 6, 8 Empty
        for (int i = 1; i < board.length; i += 2) {
            for (int j = 1; j < board.length; j += 2) {
                board[i][j] = new Piece(" ", false, false);
            }
        }

        // Row 4 Empty
        for (int j = 0; j < board.length; j++) {
            board[3][j] = new Piece(" ", false, false);
            board[4][j] = new Piece(" ", false, false);
        }
    }

    public boolean getCurrentPlayer() {
        return playerBlack;
    }

    public Piece getCell(int c, int r) {
        return board[r][c];
    }

    public int getNumTurns() {
        return this.numTurns;
    }

    public Piece[][] getBoard() {
        Piece[][] tmp = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                tmp[i][j] = piece2;
            }
        }
        return tmp;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setBoard(Piece[][] newBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = newBoard[i][j];
                Piece piece2 = new Piece(piece.getColor(), piece.getIsPiece(), piece.getIsKing());
                board[i][j] = piece2;
            }
        }
    }

    public void setNumTurns(int newNumTurns) {
        this.numTurns = newNumTurns;
    }

    public void setPlayerBlack(boolean currPlayer) {
        this.playerBlack = currPlayer;
    }

    public void setGameOver(boolean gameState) {
        this.gameOver = gameState;
    }

    public String currPlayerToString() {
        if (this.getCurrentPlayer()) {
            return "Black";
        } else {
            return "Red";
        }
    }

    public String gameOverToString() {
        if (this.getGameOver()) {
            return "true";
        } else {
            return "false";
        }
    }
}
