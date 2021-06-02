package org.cis120.checkers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RunCheckers implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Checkers");
        frame.setLocation(0, 0);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        final JLabel mouseStatus = new JLabel("Select a piece to select");
        status_panel.add(status);
        status_panel.add(mouseStatus);

        final GameBoard board;
        // if no reader, do this
        Path p = Paths.get("src/main/java/org/cis120/checkers/savedState.txt");
        boolean exists = Files.exists(p);
        if (exists) {
            board = new GameBoard(
                    status, mouseStatus, "src/main/java/org/cis120/checkers/savedState.txt"
            );
        } else {
            board = new GameBoard(status, mouseStatus);
            board.reset();
        }
        // else final GameBoard board = new GameBoard(filename)
        // make new constructor with csv information for checkers/gamestate/status
        frame.add(board, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });

        control_panel.add(reset);

        frame.add(control_panel, BorderLayout.NORTH);

        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.undo();
            }
        });

        control_panel.add(undo);

        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        instructions, "Click a piece to select it and "
                                + "click a diagonal cell to move it. \n "
                                + "To capture another piece, click on the piece you"
                                + " want to move and then the PIECE you want to "
                                + "capture. \n There must be an empty space behind "
                                + "the piece you want to capture. \n When a piece "
                                + "reaches the other side of the board, it becomes"
                                + " a king piece and gains the ability to move "
                                + "backwards. \n Capture all of your opponent's "
                                + "pieces to win the game!"
                );
            }
        });
        control_panel.add(instructions);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (!board.getCheckers().getGameOver()) {
                    // TODO Auto-generated method stub
                    try {
                        File file = new File("src/main/java/org/cis120/checkers/savedState.txt");
                        FileWriter writer = new FileWriter(file);
                        writer.write(board.getCheckers().currPlayerToString() + ",");
                        writer.write(board.getCheckers().gameOverToString() + ",");
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                Piece piece = board.getCheckers().getCell(j, i);
                                writer.write(piece.getColor() + ",");
                                writer.write(piece.isPieceToString() + ",");
                                writer.write(piece.isKingToString() + ",");
                            }
                        }
                        writer.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    new File("src/main/java/org/cis120/checkers/savedState.txt").delete();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub

            }

        });

    }
}
