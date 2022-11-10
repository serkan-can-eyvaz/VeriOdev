import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UndoAndRedoOperations extends JFrame {
    JButton b1, b2, b3,b4,b5,b6;
    JTextArea area;
    JScrollPane pane;
    JPanel p;
    Font myFont = new Font("Courier", Font.ITALIC,20);
    UndoManager manager = new UndoManager();

    public UndoAndRedoOperations()
    {
        p=new JPanel();
        area=new JTextArea(5,30);
        pane= new JScrollPane(area);
        manager= new UndoManager();
        b1=new JButton("Geri");
        b2=new JButton("İleri");
        b3=new JButton("Exit");
        b4=new JButton("İtalic");
        b5 =new JButton("background");
        b6=new JButton("Bold");
        area.addKeyListener(new KeyListener() {
            int i=0;
            int sonindex=0;

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar()==26)
                {
                    manager.undo();
                }
                if (e.getKeyChar()==25)
                {
                    manager.redo();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (Exception ex)
                {
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.redo();
                }catch (Exception ex)
                {
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setFont(new Font("İtalic", Font.ITALIC,20));
                area.add(b4);
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setBackground(Color.red);
            }
        });
       b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setFont(new Font("bond",Font.BOLD,20));
                area.add(b6);
            }
        });
        area.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });

        p.add(pane);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        add(p);
        setVisible(true);
        pack();
    }
}
