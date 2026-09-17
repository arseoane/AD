import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main170926 {
    static class Ex1 {
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
    }

    public static void main(String[] args) throws IOException {
        Ex1 ejercicio = new Ex1();
        ejercicio.copiar();
    }
}