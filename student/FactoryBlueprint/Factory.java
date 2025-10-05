public class Factory {
    public Product createProduct(String type) {
        if ("A".equals(type)) return new ConcreteProductA();
        if ("B".equals(type)) return new ConcreteProductB();
        return null; 
    }
}