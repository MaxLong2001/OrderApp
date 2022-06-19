package frontend;

import backend.AppException.AppException;
import backend.Customer;
import backend.Owner;
import frontend.Tool.FormItem;
import frontend.Tool.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用于添加菜品和下架菜品
 */
public class ModifyDish extends JPanel {
    Owner owner;
    String originDishName;

    int type;

    FormItem changeName;
    FormItem changIntro;
    FormItem changePrice;
    FormItem changType;

    public ModifyDish(Owner owner){
        this(owner, 1, null);
    }
    public ModifyDish(Owner owner, String originDishName){
        this(owner, 2, originDishName);
    }

    /**
     * @param type 1为新建菜品，2为修改当前菜品
     */
    public ModifyDish(Owner owner, int type, String originDishName){
        this.type = type;
        this.owner = owner;
        this.originDishName = originDishName;

        JLabel title;
        if(type == 1)
                title = new JLabel("新建菜品");
        else title = new JLabel("修改菜品");

        title.setHorizontalAlignment(SwingConstants.CENTER);

        changeName = new FormItem("菜品名称");
        changIntro = new FormItem("菜品介绍");
        changePrice = new FormItem("菜品价格");
        changType = new FormItem("菜品类型");

        JPanel btnPanel = new JPanel();
        MyButton btn;
        if(type == 1) btn = new MyButton("新建菜品");
        else btn = new MyButton("确认修改");
        btnPanel.add(btn);

        Box box = Box.createVerticalBox();
        box.add(title);
        box.add(Box.createVerticalStrut(30));
        box.add(changeName);
        box.add(changIntro);
        box.add(changePrice);
        if(type == 1) box.add(changType);
        box.add(Box.createVerticalStrut(30));
        box.add(btnPanel);

        add(box);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = changeName.getText();
                    String intro = changIntro.getText();
                    String typeStr = changType.getText();
                    Double price = 0.0;
                    try{
                        price = Double.parseDouble(changePrice.getText());
                    }catch (Exception exx){
                        if(type == 1) throw new AppException("价格格式错误");
                    }

                    if(type == 1){
                        owner.addDishes(owner.getName(), name, price, typeStr, intro);
                        JOptionPane.showConfirmDialog(ModifyDish.this,
                                "创建成功！", "创建成功", JOptionPane.DEFAULT_OPTION);
                    }else{
                        owner.modifyDishes(owner.getName(), originDishName, name, price, typeStr, intro);
                        JOptionPane.showConfirmDialog(ModifyDish.this,
                                "修改成功！", "修改成功", JOptionPane.DEFAULT_OPTION);
                    }
                }catch (AppException ex){
                    JOptionPane.showConfirmDialog(ModifyDish.this,
                            ex, "修改异常", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
    }
}
