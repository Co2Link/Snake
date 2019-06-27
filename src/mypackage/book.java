package mypackage;

public class book {
    public String name;
    protected String name_pt;
    String name_nr;

    public static void main(String[] args) {
        book bk = new book();
        System.out.println(book.class.getName());
    }
}

