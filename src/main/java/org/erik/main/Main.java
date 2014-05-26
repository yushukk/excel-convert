package org.erik.main;

import org.erik.Result;
import org.erik.convert.ExcelConvert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * ��򵥵�Java��ק����ʾ��
 * @author caiwd
 * 2014-5-26
 */
public class Main extends JFrame
{

    JPanel panel;//Ҫ������ק�����
    JPanel configPanal;
    JTextField  text;
    public Main()
    {
        panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setSize(500,200);
        getContentPane().add(panel, BorderLayout.CENTER);

        configPanal = new JPanel();
        configPanal.setBackground(Color.GREEN);
        configPanal.setSize(500,100);

        Label label = new Label("����ļ��У�");

        configPanal.add(label);
        text=new JTextField(20);
        text.setText("D:\\ali\\");
        configPanal.add(text);

        getContentPane().add(configPanal,BorderLayout.PAGE_END);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("EXCELת������ק�ļ�������");
        drag();//������ק


    }
    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//����Ƥ��
        new Main().setVisible(true);
    }
    public void drag()//�������ק����
    {
        //panel��ʾҪ������ק�Ŀؼ�
        new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)//��д��������drop����
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//���������ļ���ʽ��֧��
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        String temp="";
                        for(File file:list)
                            temp+=file.getAbsolutePath()+";\n";

                        Result result = ExcelConvert.convert(list, text.getText());
                        if(result.isSuccess()){
                            JOptionPane.showMessageDialog(null, "ת���ɹ�");
                        }else{
                            JOptionPane.showMessageDialog(null, "ת��ʧ�ܣ�ԭ��"+result.getResultMsg());
                        }

                        dtde.dropComplete(true);//ָʾ��ק���������
                    }
                    else
                    {
                        dtde.rejectDrop();//����ܾ���ק��������
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
