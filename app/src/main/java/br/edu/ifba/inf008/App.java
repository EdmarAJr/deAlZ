package br.edu.ifba.inf008;

import br.edu.ifba.inf008.data.FileRegister;
import br.edu.ifba.inf008.shell.UserUi;

public class App {
    public static void main(String[] args) throws Exception {

        FileRegister.loadFile();
        UserUi app = new UserUi();
        app.start();
        FileRegister.saveFile();
    }
}
