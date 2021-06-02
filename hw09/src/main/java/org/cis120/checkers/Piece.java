package org.cis120.checkers;

public class Piece {
    private String color;
    private boolean isPiece;
    private boolean isKing;

    public Piece(String color, boolean isPiece, boolean isKing) {
        this.color = color;
        this.isPiece = isPiece;
        this.isKing = isKing;
    }

    public void flipPiece() {
        if (this.isPiece) {
            this.isPiece = false;
        } else {
            this.isPiece = true;
        }
    }

    public void promote() {
        this.isKing = true;
    }

    public boolean getIsKing() {
        return this.isKing;
    }

    public String getColor() {
        return this.color;
    }

    public boolean getIsPiece() {
        return this.isPiece;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIsPiece(boolean isPiece) {
        this.isPiece = isPiece;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }

    public String isPieceToString() {
        if (this.isPiece) {
            return "true";
        } else {
            return "false";
        }
    }

    public String isKingToString() {
        if (this.isKing) {
            return "true";
        } else {
            return "false";
        }
    }
}
