/*
Name = Carlos Meza
Date = 5/9/15
SID: 0422793
Lab: Lab 17
Description: TicTacToe
 */
package lab_17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener, MouseListener
{

    Container content = this.getContentPane();
    JLabel[][] grid = new JLabel[3][3];
    char[][] game = new char [3][3];
    JButton restart = new JButton("Restart");
    JPanel p = new JPanel ();
    JLabel status = new JLabel("Welcome!", JLabel.CENTER);
    int numClicks = 0;
    boolean isDone = false;
    boolean isXTurn = true;
    
    TicTacToe()
    {
        this.createWindow();
        content.add(status, BorderLayout.NORTH);
        status.setForeground(Color.BLUE);
        status.setOpaque(true);
        status.setBackground(Color.YELLOW);
        status.setFont(new Font("helvetica", Font.BOLD, 12));
        p.setLayout( new GridLayout(3,3,3,3));
        p.setBackground(Color.BLACK);
        content.add(p);
        
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                JLabel tmp = new JLabel(" ", JLabel.CENTER );
                grid [row][col]=tmp;
                tmp.addMouseListener(this);
                tmp.setOpaque(true);
                tmp.setBackground(Color.WHITE);
                tmp.setFont(new Font("helvetica", Font.BOLD, 32));
                p.add(tmp);
                game[row][col] = ' ';
            }
        } 
        restart.addActionListener(this);
        content.add(restart, BorderLayout.SOUTH);
    }

    public void createWindow()
    {
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic Tac Toe");
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        this.resetGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(isDone) resetGame();
        JLabel clicked = (JLabel) e.getSource();
        
        next:
            for(int row = 0; row < 3; row++)
            {
                for(int col = 0; col < 3; col++)
                {
                    if(clicked == grid[row][col])
                    {
                        if(clicked.getText() != " ")
                        {
                            status.setText("Invalid Move");
                            break next;
                        }
                        else if(clicked.getText() == " " && isXTurn == true)
                        {
                            clicked.setText("X");
                            clicked.setForeground(Color.red);
                            game[row][col] = 'X';
                        }
                        else
                        {
                            clicked.setText("O");
                            clicked.setForeground(Color.blue);
                            game[row][col] = 'O';
                        }
                        isXTurn = !isXTurn;
                        numClicks++;
                        gameOver();
                        
                    } 
                }
            }
        
    }
    public void gameOver()
    {
       char winner = ' ';
       if(game[0][0]== game[1][1] && game[0][0]== game[2][2])
       {
           winner = game[0][0];
       }
       else if(game[2][0] == game[1][1] && game[2][0]== game[0][2])
       {
           winner = game[2][0];
       }
       for(int row = 0; row < 3; row++)
       {
           if(game[row][0] != ' ' && game[row][0]== game [row][1] && game[row][0]== game[row][2])
           {
               winner = game[row][0];
           }
           else if (game[0][row] != ' ' && game[0][row]== game [1][row] && game[0][row]== game[2][row])
           {
               winner = game[0][row];
           }
       }
       isDone = true;
       if(winner == ' ' && numClicks == 9)
       {
           status.setText("Tie Game");
       }
       else if (winner != ' ')
           status.setText("Game Over " + winner + " Won!!!");
       else
       {
           if(isXTurn)
               status.setText("X's Turn");
           else
           {
               status.setText("O's Turn");
           }
           isDone = false;       
       }
    }
    public void resetGame()
    {
       for(int row = 0; row <=2; row++)
       {
           for(int col = 0; col <=2; col++)
           {
               grid[row][col].setText(" ");
               game[row][col] = ' ';
               numClicks = 0;
               isXTurn = true;
           }
       }
    }
    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }
        public static void main(String[] args) 
    {
        TicTacToe ttc = new TicTacToe();
    }
}
