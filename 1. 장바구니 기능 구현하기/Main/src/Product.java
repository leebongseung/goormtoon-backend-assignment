import java.util.Objects;

// HashSet을 이용해서 상품 목록 만들기
public class Product {
    private int key;
    private String name;
    private int price;

    public Product(int key, String name, int price) {
        this.key = key;
        this.name = name;
        this.price = price;
    }
    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Product product = (Product) object;
        return key == product.key && price == product.price && java.util.Objects.equals(name, product.name);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), key, name, price);
    }
}
