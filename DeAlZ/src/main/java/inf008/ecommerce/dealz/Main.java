package inf008.ecomerce.dealz;

public class Main {
    public static void main(String[] args) throws Exception {

        FileRegister.loadFile();
        UserUi app = new UserUi();
        app.start();
        FileRegister.saveFile();
    }
}
