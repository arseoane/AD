import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main170926 {
    static class Pt1 {
        File texto1 = new File("/home/dam26/texto1.txt");
        File texto2 = new File("/home/dam26/texto2.txt");

        public void copiar() throws IOException {
            FileInputStream entrada = new FileInputStream(texto1);
            FileOutputStream salida = new FileOutputStream(texto2);

            int byteLeido;

            while ((byteLeido = entrada.read()) != -1) {
                salida.write(byteLeido);
            }

            entrada.close();
            salida.close();
        }

        public void engadir() throws IOException {
            FileInputStream entrada = new FileInputStream(texto1);
            FileOutputStream salida = new FileOutputStream(texto2, true);

            int byteLeido;

            while ((byteLeido = entrada.read()) != -1) {
                salida.write(byteLeido);
            }

            entrada.close();
            salida.close();
        }
    }

    static class Pt2 {
        File foto1 = new File("/home/dam26/foto.jpg");
        File foto2 = new File("/home/dam26/foto2.jpg");


    }

    public static void main(String[] args) throws IOException {
        Pt1 ejercicio = new Pt1();
        ejercicio.copiar();
        ejercicio.engadir();
    }
}