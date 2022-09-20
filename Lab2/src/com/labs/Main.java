package com.labs;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main {
    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) {
        jFrame.add(jPanel);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu mainInfo = new JMenu("Main Info");
        JMenu task = new JMenu("Task");

        initialMain();

        mainInfo.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                initialMain();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        task.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                initialTask();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });


        jMenuBar.add(mainInfo);
        jMenuBar.add(task);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.revalidate();
    }

    private static void initialMain() {
        jPanel.removeAll();
        JLabel info = new JLabel("""
                <html><pre>Лабораторна робота_2
                з дисципліни Алгоритми та методи обчислень
                Виконала студентка групи ІО-02
                Кожемяко Ярослава Романівна
                Номер залікової книжки: 0215
                Варіант: 15

                **Додаткова інформація про формат вводу даних**
                - Елементи масивів потрібно записувати через пробіл
                - Індексація елементів здійснюється з нуля
                - Довжину будь-якого масиву визначає змінна 'n'
                Приклад: n = 4,     a[1,n] = 1 2 3 4
                - Можливий ввід цілих та дійсних чисел</pre></html>""");

        info.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        jPanel.add(info);
        jPanel.updateUI();
    }

    private static void initialTask(){
        jPanel.removeAll();
        JTextField arrayInString = new JTextField();
        JButton sortButton = new JButton("Sort array");
        JButton downloadArrayButton = new JButton("Download from file");
        jPanel.add(arrayInString);
        jPanel.add(sortButton);
        jPanel.add(downloadArrayButton);

        jPanel.updateUI();
    }


    private static void uploadValueFromFile(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        if (fileChooser.showOpenDialog(jPanel) == 0) {
            File file = fileChooser.getSelectedFile();
            try {
                FileReader reader = new FileReader(file);
                Scanner scan = new Scanner(reader);
                textField.setText(scan.nextLine());
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }


    static JFrame getFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Lab 1");
        jFrame.setVisible(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Dimension dimensionOfFrame = new Dimension();
        dimensionOfFrame.setSize(750, 400);
        jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, dimensionOfFrame.width, dimensionOfFrame.height);
        return jFrame;
    }
}
