package frontend;

import backend.AppException.AppException;
import backend.Customer;
import backend.Owner;
import frontend.Tool.FormItem;
import frontend.Tool.MyButton;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyView extends MyView {
    Customer customer;
    Owner owner;

    FormItem changeName;
    FormItem changPwd;
    FormItem confirmPwd;
    FormItem changeIntro;
    public ModifyView(Customer customer){
        this(customer, null);
    }
    public ModifyView(Owner owner){
        this(null, owner);
    }
    public ModifyView(Customer customer, Owner owner){
        this.customer = customer;
        this.owner = owner;

        JLabel title = new JLabel("修改个人信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        changeName = new FormItem("修改用户名");
        changPwd = new FormItem("修改密码", true);
        confirmPwd = new FormItem("确认新密码", true);
        changeIntro = new FormItem("修改简介");




        JPanel btnPanel = new JPanel();
        MyButton btn = new MyButton("确认修改");
        btnPanel.add(btn);

        Box box = Box.createVerticalBox();
        box.add(title);
        box.add(Box.createVerticalStrut(30));
        box.add(changeName);
        box.add(changPwd);
        box.add(confirmPwd);
        if(owner != null){
            box.add(changeIntro);
        }
        box.add(Box.createVerticalStrut(30));
        box.add(btnPanel);

        add(box);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(!(changeName.getText() == null || changeName.getText().equals(""))){
                        if(owner == null)customer.modifyName(changeName.getText());
                        else  owner.modifyName(changeName.getText());
                    }
                    if(!(changPwd.getText() == null || changPwd.getText().equals(""))){
                        if(confirmPwd.getText() == null || !confirmPwd.getText().equals(changPwd.getText()))
                        if(owner == null)customer.modifyPwd(changPwd.getText());
                        else  owner.modifyPwd(changPwd.getText());
                    }
                    if(!(changeIntro.getText() == null || changeIntro.getText().equals(""))){
                        if(owner != null)owner.updateIntroduction(owner.getName(), changeIntro.getText());
                    }
                    JOptionPane.showConfirmDialog(ModifyView.this,
                            "修改成功！", "修改成功", JOptionPane.DEFAULT_OPTION);

                }catch (AppException ex){
                    JOptionPane.showConfirmDialog(ModifyView.this,
                            ex, "修改异常", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
    }
}
