package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FormMain extends JFrame {
    private JPanel panelMain;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JButton Mode;
    private JButton q1EnqueueButton;
    private JButton q1DequeueButton;
    private JButton q2EnqueueButton;
    private JButton q2DequeueButton;
    private JButton q1FileButton;
    private JButton q2FileButton;
    private JButton solveButton;
    private JTable tableInputQ1;
    private JTable tableInputQ2;
    private JTextField textField1;
    private JTable tableOutputQ2;
    private JTable tableOutputQ1;
    private JTextField q1EnqueueField;
    private JTextField q2EnqueueField;


    public FormMain() {
        this.setTitle("FormMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        //------------------------------

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");
        textField1.setText("Mode: Inbuilt       ---do not switch modes midway");


        Queue<Integer> Q1
                = new LinkedList<Integer>();
        Queue<Integer> Q2
                = new LinkedList<Integer>();
        SimpleQueue<Integer> Q1S = new SimpleLinkedListQueue<Integer>();
        SimpleQueue<Integer> Q2S = new SimpleLinkedListQueue<Integer>();
        final boolean[] mode = {true};


        Mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try
                {
                    if(mode[0]) {
                        mode[0] = false;
                        textField1.setText("Mode: Custom       ---do not switch modes midway");

                    } else
                    {
                        mode[0] = true;
                        textField1.setText("Mode: Inbuilt       ---do not switch modes midway");

                    }
                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });


        q1FileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            Scanner scanner = new Scanner(new File(fileChooserOpen.getSelectedFile().getPath()), "UTF-8");
                            while (scanner.hasNext()) {
                                Q1.add(scanner.nextInt());
                            }
                            scanner.close();


                            JTableUtils.writeArrayToJTable(tableInputQ1, ArrayUtils.toPrimitive(Arrays.asList(Q1.toArray())
                                    .toArray(new Integer[0])));

                        }
                    }
                    else {
                        if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            Scanner scanner = new Scanner(new File(fileChooserOpen.getSelectedFile().getPath()), "UTF-8");
                            while (scanner.hasNext()) {
                                Q1S.add(scanner.nextInt());
                            }
                            scanner.close();


                            int[] arr = new int[Q1S.count()];
                            int i = 0;
                            for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1S).iterator(); it.hasNext(); ) {
                                Integer o = (int)it.next();
                                arr[i]=o;
                                i++;
                            }


                            JTableUtils.writeArrayToJTable(tableInputQ1, arr);
                        }
                    }

                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
            });


        q2FileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            Scanner scanner = new Scanner(new File(fileChooserOpen.getSelectedFile().getPath()), "UTF-8");
                            while (scanner.hasNext()) {
                                Q2.add(scanner.nextInt());
                            }
                            scanner.close();


                            JTableUtils.writeArrayToJTable(tableInputQ2, ArrayUtils.toPrimitive(Arrays.asList(Q2.toArray())
                                    .toArray(new Integer[0])));

                        }
                    }
                    else {
                        if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            Scanner scanner = new Scanner(new File(fileChooserOpen.getSelectedFile().getPath()), "UTF-8");
                            while (scanner.hasNext()) {
                                Q2S.add(scanner.nextInt());
                            }
                            scanner.close();


                            int[] arr = new int[Q2S.count()];
                            int i = 0;
                            for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q2S).iterator(); it.hasNext(); ) {
                                Integer o = (int)it.next();
                                arr[i]=o;
                                i++;
                            }


                            JTableUtils.writeArrayToJTable(tableInputQ2, arr);
                        }
                    }

                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(mode[0]) {
                        int trigger = 0;
                        int sizeofq1 = Q1.size();
                        Q1.addAll(Q2);
                        for (Integer item : Q1) {
                            if (trigger < sizeofq1) Q2.add(item);
                            trigger++;
                            //System.out.println(item);
                        }

                        JTableUtils.writeArrayToJTable(tableOutputQ1, ArrayUtils.toPrimitive(Arrays.asList(Q1.toArray())
                                .toArray(new Integer[0])));
                        JTableUtils.writeArrayToJTable(tableOutputQ2, ArrayUtils.toPrimitive(Arrays.asList(Q2.toArray())
                                .toArray(new Integer[0])));
                    } else
                    {
                        int trigger = 0;
                        int sizeofq1 = Q1S.count();
                        //--------------------------------------------

                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q2S).iterator(); it.hasNext(); ) {
                            Integer o = it.next();
                            Q1S.add(o);
                        }

                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1S).iterator(); it.hasNext(); ) {

                            Integer o = it.next();
                            if (trigger < sizeofq1) Q2S.add(o);
                            trigger++;
                        }



                        int[] arr = new int[Q1S.count()];
                        int i = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr[i]=o;
                            i++;
                        }


                        JTableUtils.writeArrayToJTable(tableOutputQ1, arr);

                        int[] arr2 = new int[Q2S.count()];
                        int i2 = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q2S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr2[i2]=o;
                            i2++;
                        }


                        JTableUtils.writeArrayToJTable(tableOutputQ2, arr2);




                        /*Q1.addAll(Q2);
                        for (Integer item : Q1) {
                            if (trigger < sizeofq1) Q2.add(item);
                            trigger++;
                            //System.out.println(item);
                        }*/
                    }

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });

        q1EnqueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        Q1.add(Integer.parseInt(q1EnqueueField.getText()));
                        JTableUtils.writeArrayToJTable(tableInputQ1, ArrayUtils.toPrimitive(Arrays.asList(Q1.toArray())
                                .toArray(new Integer[0])));
                    }
                    else
                    {
                        Q1S.add(Integer.parseInt(q1EnqueueField.getText()));
                        int[] arr = new int[Q1S.count()];
                        int i = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr[i]=o;
                            i++;
                        }


                        JTableUtils.writeArrayToJTable(tableInputQ1, arr);

                    }

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });

        q2EnqueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        Q2.add(Integer.parseInt(q2EnqueueField.getText()));
                        JTableUtils.writeArrayToJTable(tableInputQ2, ArrayUtils.toPrimitive(Arrays.asList(Q2.toArray())
                                .toArray(new Integer[0])));
                    }
                    else
                    {
                        Q1S.add(Integer.parseInt(q1EnqueueField.getText()));
                        int[] arr = new int[Q2S.count()];
                        int i = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q2S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr[i]=o;
                            i++;
                        }


                        JTableUtils.writeArrayToJTable(tableInputQ2, arr);

                    }

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });


        q1DequeueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        Q1.remove();
                        JTableUtils.writeArrayToJTable(tableInputQ1, ArrayUtils.toPrimitive(Arrays.asList(Q1.toArray())
                                .toArray(new Integer[0])));
                    }
                    else
                    {
                        Q1S.remove();
                        int[] arr = new int[Q1S.count()];
                        int i = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr[i]=o;
                            i++;
                        }


                        JTableUtils.writeArrayToJTable(tableInputQ1, arr);

                    }

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });

        q2DequeueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (mode[0]) {
                        Q2.remove();
                        JTableUtils.writeArrayToJTable(tableInputQ2, ArrayUtils.toPrimitive(Arrays.asList(Q2.toArray())
                                .toArray(new Integer[0])));
                    }
                    else
                    {
                        Q2S.remove();
                        int[] arr = new int[Q2S.count()];
                        int i = 0;
                        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q2S).iterator(); it.hasNext(); ) {
                            Integer o = (int)it.next();
                            arr[i]=o;
                            i++;
                        }


                        JTableUtils.writeArrayToJTable(tableInputQ2, arr);

                    }

                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }


            }
        });






    }
}
