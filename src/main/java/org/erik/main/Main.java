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
 * 最简单的Java拖拽代码示例
 * @author caiwd
 * 2014-5-26
 */
public class Main extends JFrame
{

    JPanel panel;//要接受拖拽的面板
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

        Label label = new Label("输出文件夹：");

        configPanal.add(label);
        text=new JTextField(20);
        text.setText("D:\\ali\\");
        configPanal.add(text);

        getContentPane().add(configPanal,BorderLayout.PAGE_END);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("EXCEL转化：拖拽文件到下面");
        drag();//启用拖拽


    }
    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//设置皮肤
        new Main().setVisible(true);
    }
    public void drag()//定义的拖拽方法
    {
        //panel表示要接受拖拽的控件
        new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)//重写适配器的drop方法
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        String temp="";
                        for(File file:list)
                            temp+=file.getAbsolutePath()+";\n";

                        Result result = ExcelConvert.convert(list, text.getText());
                        if(result.isSuccess()){
                            JOptionPane.showMessageDialog(null, "转换成功");
                        }else{
                            JOptionPane.showMessageDialog(null, "转换失败，原因："+result.getResultMsg());
                        }

                        dtde.dropComplete(true);//指示拖拽操作已完成
                    }
                    else
                    {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
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
