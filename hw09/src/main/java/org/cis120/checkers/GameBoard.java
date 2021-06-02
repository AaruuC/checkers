package org.cis120.checkers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel {

    private Checkers checkers;
    private Map<Integer, Checkers> gameState;
    private JLabel status;
    private JLabel mouseStatus;
    private boolean clickOne;
    private int x;
    private int y;

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    public GameBoard(JLabel statusInit, JLabel mouse) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        checkers = new Checkers();

        status = statusInit;

        clickOne = true;
        mouseStatus = mouse;
        gameState = new TreeMap<Integer, Checkers>();
        gameState.put(0, checkers);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (clickOne) {
                    Point p = e.getPoint();
                    x = p.x;
                    y = p.y;
                    clickOne = !clickOne;
                    updateMouse();
                } else {
                    Point p = e.getPoint();
                    // if (checkers.getCell(getasdY(),
                    // getasdX()).getColor().equals(checkers.getCell(p.y, p.x).getColor())) {
                    // x = p.x;
                    // y = p.y;
                    // clickOne = !clickOne;
                    // }

                    if (checkers.playTurn(
                            getasdX() / 100, getasdY() / 100,
                            p.x / 100, p.y / 100
                    ) || checkers.getGameOver()) {
                        Checkers checkers2 = new Checkers();
                        checkers2.setBoard(checkers.getBoard());
                        checkers2.setNumTurns(checkers.getNumTurns());
                        checkers2.setPlayerBlack(checkers.getCurrentPlayer());
                        checkers2.setGameOver(checkers.getGameOver());
                        gameState.put(checkers2.getNumTurns(), checkers2);
                        updateStatus();
                        repaint();
                    }
                    clickOne = !clickOne;
                    updateMouse();
                }
            }
        });
    }

    public GameBoard(JLabel statusInit, JLabel mouse, String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        checkers = new Checkers();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String[] split = reader.readLine().split(",");

            Piece[][] pieces = checkers.getBoard();
            int counter = 0;

            if (split[counter].equals("Black")) {
                checkers.setPlayerBlack(true);
            } else if (split[counter].equals("Red")) {
                checkers.setPlayerBlack(false);
            }

            counter++;

            if (split[counter].equals("true")) {
                checkers.setGameOver(true);
            } else if (split[counter].equals("false")) {
                checkers.setGameOver(false);
            }
            counter++;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    pieces[i][j].setColor(split[counter]);
                    counter++;

                    if (split[counter].equals("true")) {
                        pieces[i][j].setIsPiece(true);
                    } else if (split[counter].equals("false")) {
                        pieces[i][j].setIsPiece(false);
                    }
                    counter++;

                    if (split[counter].equals("true")) {
                        pieces[i][j].setIsKing(true);
                    } else if (split[counter].equals("false")) {
                        pieces[i][j].setIsKing(false);
                    }
                    counter++;
                }
            }
            checkers.setBoard(pieces);
            reader.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            throw new IllegalArgumentException("invalid file");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        status = statusInit;
        mouseStatus = mouse;
        updateStatus();
        // repaint();
        clickOne = true;
        gameState = new TreeMap<Integer, Checkers>();
        gameState.put(0, checkers);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (clickOne) {
                    Point p = e.getPoint();
                    x = p.x;
                    y = p.y;

                    clickOne = !clickOne;
                    updateMouse();
                } else {
                    Point p = e.getPoint();

                    if (checkers.playTurn(
                            getasdX() / 100, getasdY() / 100,
                            p.x / 100, p.y / 100
                    ) || checkers.getGameOver()) {
                        Checkers checkers2 = new Checkers();
                        checkers2.setBoard(checkers.getBoard());
                        checkers2.setNumTurns(checkers.getNumTurns());
                        checkers2.setPlayerBlack(checkers.getCurrentPlayer());
                        checkers2.setGameOver(checkers.getGameOver());
                        gameState.put(checkers2.getNumTurns(), checkers2);
                        updateStatus();

                        repaint();
                    }
                    clickOne = !clickOne;
                    updateMouse();
                }
            }
        });
    }

    public int getasdX() {
        return this.x;
    }

    public int getasdY() {
        return this.y;
    }

    public Checkers getCheckers() {
        return this.checkers;
    }

    public void undo() {
        Checkers tmp = gameState.get(checkers.getNumTurns() - 1);
        checkers.setBoard(tmp.getBoard());
        checkers.setNumTurns(tmp.getNumTurns());
        checkers.setPlayerBlack(tmp.getCurrentPlayer());
        checkers.setGameOver(tmp.getGameOver());
        updateStatus();
        repaint();
        requestFocusInWindow();
    }

    public void reset() {
        checkers.reset();
        status.setText("Black's Turn" + "  //  ");
        clickOne = true;
        repaint();
        requestFocusInWindow();
    }

    private void updateStatus() {
        if (checkers.getCurrentPlayer()) {
            status.setText("Black's Turn" + "  //  ");
        } else {
            status.setText("Red's Turn" + "  //  ");
        }

        if (checkers.checkWinnerBlack()) {
            status.setText("Black wins!!!" + "  //  ");
        } else if (checkers.checkWinnerRed()) {
            status.setText("Red wins!!!" + "  //  ");
        }
    }

    private void updateMouse() {
        if (clickOne) {
            mouseStatus.setText("Select a piece to select");
        } else {
            mouseStatus.setText("Select a square to move to");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        for (int i = 100; i < 800; i += 100) {
            g.drawLine(i, 0, i, 800);
        }
        for (int i = 100; i < 800; i += 100) {
            g.drawLine(0, i, 800, i);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = checkers.getCell(j, i);
                if (piece.getIsPiece()) {
                    if (piece.getColor().equals("Black")) {
                        g.setColor(Color.black);
                        g.fillOval(30 + 100 * j, 30 + 100 * i, 40, 40);
                    } else if (piece.getColor().equals("Red")) {
                        g.setColor(Color.red);
                        g.fillOval(30 + 100 * j, 30 + 100 * i, 40, 40);
                    }
                    if (piece.getIsKing()) {
                        g.setColor(Color.yellow);
                        g.fillOval(
                                40 + 100 * j, 40 + 100 * i,
                                20, 20
                        );
                    }
                }
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
