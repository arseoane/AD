import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

        public void engadir() throws IOException {
            FileInputStream entrada = new FileInputStream(foto1);
            FileOutputStream salida = new FileOutputStream(foto2, true);

            int byteLeido;

            while ((byteLeido = entrada.read()) != -1) {
                salida.write(byteLeido);
            }

            entrada.close();
            salida.close();
        }
    }

    static class Pt2Buffered {
        private File foto1 = new File("/home/dam26/foto.jpg");
        private File foto2 = new File("/home/dam26/foto2.jpg");

        public void engadir() throws IOException {
            // Envolvemos os streams orixinais nos BufferedStreams
            BufferedInputStream entrada = new BufferedInputStream(new FileInputStream(foto1));
            BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream(foto2, true));

            int byteLeido;

            // O método read() e write() úsanse exactamente igual
            while ((byteLeido = entrada.read()) != -1) {
                salida.write(byteLeido);
            }

            // Ao pechar os BufferedStreams pechanse tamén os FileInputStream/FileOutputStream internos
            // e faise o peche/volcado (flush) dos datos pendentes.
            entrada.close();
            salida.close();
        }
    }

    static class XestionTextoDataStream {

        private final String RUTA_FICHEIRO = "texto3.txt";
        private final String TEXTO = "o tempo está xélido";

        public static void main(String[] args) {
            XestionTextoDataStream app = new XestionTextoDataStream();
            try {
                app.escribirCadeas();
                app.lerCadeas();
            } catch (IOException e) {
                System.err.println("Erro ao procesar o ficheiro: " + e.getMessage());
            }
        }

        // Método para escribir a cadea 3 veces consecutivas
        public void escribirCadeas() throws IOException {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(RUTA_FICHEIRO))) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("escribindo a cadea: " + TEXTO);
                    dos.writeUTF(TEXTO);
                    System.out.println("tamano do ficheiro: " + dos.size() + " bytes");
                }
                System.out.println("tamano final do ficheiro: " + dos.size() + " bytes");
            }
        }

        // Método para ler as cadeas do ficheiro ata que non quede nada por ler
        public void lerCadeas() throws IOException {
            File ficheiro = new File(RUTA_FICHEIRO);

            try (DataInputStream dis = new DataInputStream(new FileInputStream(ficheiro))) {
                while (dis.available() > 0) {
                    System.out.println("quedan: " + dis.available() + " bytes por ler");
                    String cadeaLeida = dis.readUTF();
                    System.out.println("cadea: " + cadeaLeida);
                }
                System.out.println("Xa non queda nada por ler");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Pt1 ejercicio1 = new Pt1();
        ejercicio1.copiar();
        ejercicio1.engadir();

        Pt2 ejercicio2 = new Pt2();
        ejercicio2.engadir();

        Pt2Buffered buffered = new Pt2Buffered();
        buffered.engadir();
    }
}