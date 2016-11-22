package pis.hue1;

public class Main {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(CodecGUI.class);
            }
        }.start();
    }
}
