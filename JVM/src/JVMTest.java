class Person{

}

public class JVMTest {
    public static void main(String[] args) {
        Object o = new Object();
        Person p= new Person();
        System.out.println(o.getClass().getClassLoader());
        System.out.println(p.getClass().getClassLoader().getParent().getParent());
        System.out.println(p.getClass().getClassLoader().getParent());
        System.out.println(p.getClass().getClassLoader());
    }
}
