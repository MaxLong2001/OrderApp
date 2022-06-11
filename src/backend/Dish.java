package backend;

public class Dish {
    public String name;
    public int remainQuantity;
    public int salesQuantity;
    public double price;
    public String type;
    public String introduction;

    /**
     * 这个方法用于返回菜品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 这个方法用于设置菜品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 这个方法用于返回菜品剩余数量
     */
    public int getRemainQuantity() {
        return remainQuantity;
    }

    /**
     * 这个方法用于设置菜品剩余数量
     */
    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    /**
     * 这个方法用于返回菜品销量
     */
    public int getSalesQuantity() {
        return salesQuantity;
    }

    /**
     * 这个方法用于设置菜品的销量
     */
    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    /**
     * 这个方法用于返回菜品价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 这个方法用于设置菜品价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 这个方法用于返回菜品分类
     */
    public String getType() {
        return type;
    }

    /**
     * 这个方法用于设置菜品的分离
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 这个方法用于返回菜品的简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 这个方法用于设置菜品的简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
