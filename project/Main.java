package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    static class MainWindow extends JFrame {
        MainWindow(){
            // wejście tekstu - label
            Label textinputlab = new Label("Tekst wejściowy: ");
            textinputlab.setBounds(40,10,150,30);
            add(textinputlab);
            // wejście tekstu
            JTextArea textinput = new JTextArea();
            JScrollPane textscroll = new JScrollPane(textinput);
            textscroll.setBounds(205, 10, 200, 60);
            textinput.setLineWrap(true);
            textinput.setWrapStyleWord(true);
            add(textscroll);
            // szyfrowanie/deszyfrowanie - radiobuton
            ButtonGroup rbgroup = new ButtonGroup();
            JRadioButton encrypt = new JRadioButton("Szyfruj", true);
            rbgroup.add(encrypt);
            JRadioButton decrypt = new JRadioButton("Deszyfruj");
            rbgroup.add(decrypt);
            // panel przechowujący radiobuttony
            JPanel radiopanel = new JPanel();
            radiopanel.setLayout(new GridLayout(1, 2));
            radiopanel.add(encrypt);
            radiopanel.add(decrypt);
            radiopanel.setBounds(130,155,200,30);
            add(radiopanel);
            // wynik labelka
            Label result = new Label("Tekst wyjściowy: ");
            result.setBounds(40, 80, 150, 30);
            add(result);
            // wynik textarea,scroll
            JTextArea resulttext = new JTextArea();
            JScrollPane resultscroll = new JScrollPane(resulttext);
            resultscroll.setBounds(205, 80, 200, 60);
            resulttext.setLineWrap(true);
            resulttext.setWrapStyleWord(true);
            resulttext.setEditable(false);
            add(resultscroll);
            // przycisk
            Button b = new Button("Konwertuj");
            b.setBounds(185, 200, 100, 30);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String text = textinput.getText();
                    if(encrypt.isSelected()){
                        resulttext.setText(Cipher.encode(text));
                    }
                    else{
                        resulttext.setText(Cipher.decode(text));
                    }
                }
            });
            add(b);
            // okno
            setSize(450,300);
            setLayout(null);
            setVisible(true);
            setResizable(false);
            setTitle("Szyfrowanie Staszkowe");
            addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent w){
                    System.exit(0);
                }
            });
        }
    }
    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }
}