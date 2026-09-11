import java.io.File;

public class Main110926 {

    public static String eDirectorio(String cadea) {
        File directorio = new File(cadea);

        if (directorio.isDirectory()) {
            return "é directorio";
        }

        return "non é directorio";
    }

    public static String eFicheiro(String cadea) {
        File ficheiro = new File(cadea);

        if (ficheiro.isFile()) {
            return "é ficheiro";
        }

        return "non é ficheiro";
    }

    public static void creaDirectorio(String dirName) {
        File directorio = new File(dirName);

        if (!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public static void creaFicheiro(String dirName, String failName) {
        File directorio = new File(dirName);

        if (!directorio.exists() || !directorio.isDirectory()) {
            return;
        }

        File ficheiro = new File(dirName, failName);

        if (!ficheiro.exists()) {
            try {
                ficheiro.createNewFile();
            } catch (Exception e) {
                System.out.println("Non se puido crear o ficheiro");
            }
        }
    }

    public static void modoAcceso(String dirName, String failName) {
        File ficheiro = new File(dirName, failName);

        if (ficheiro.canWrite()) {
            System.out.println("escritura si");
        } else {
            System.out.println("escritura non");
        }

        if (ficheiro.canRead()) {
            System.out.println("lectura si");
        } else {
            System.out.println("lectura non");
        }
    }

    public static void calculaLonxitude(String dirName, String failName) {
        File ficheiro = new File(dirName, failName);

        System.out.println(ficheiro.length() + " bytes");
    }

    public static void mLectura(String dirName, String failName) {
        File ficheiro = new File(dirName, failName);

        if (ficheiro.exists()) {
            ficheiro.setReadable(true);
            ficheiro.setWritable(false);
        }
    }

    public static void mEscritura(String dirName, String failName) {
        File ficheiro = new File(dirName, failName);

        if (ficheiro.exists()) {
            ficheiro.setWritable(true);
        }
    }

    public static void borraFicheiro(String dirName, String failName) {
        File ficheiro = new File(dirName, failName);

        if (ficheiro.exists() && ficheiro.isFile()) {
            ficheiro.delete();
        } else {
            System.out.println("ficheiro inexistente");
        }
    }

    public static void borraDirectorio(String dirName) {
        File directorio = new File(dirName);

        if (directorio.exists() && directorio.isDirectory() && directorio.list().length == 0) {
            directorio.delete();
        } else {
            System.out.println("ruta inexistente ou con descencencia");
        }
    }

    public static void mContido(String dirName) {
        File directorio = new File(dirName);

        if (directorio.exists() && directorio.isDirectory()) {
            File[] contido = directorio.listFiles();

            if (contido != null) {
                for (File elemento : contido) {
                    System.out.println(elemento.getName());
                }
            }
        }
    }

    public static void recur(File directorio) {
        if (directorio.exists() && directorio.isDirectory()) {
            File[] elementos = directorio.listFiles();

            if (elementos != null) {
                for (File elemento : elementos) {
                    System.out.println(elemento.getAbsolutePath());

                    if (elemento.isDirectory()) {
                        recur(elemento);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String dirName = "/tmp/proba";
        String failName = "exemplo.txt";

        creaDirectorio(dirName);
        creaFicheiro(dirName, failName);

        System.out.println(eDirectorio(dirName));
        System.out.println(eFicheiro(dirName + "/" + failName));

        modoAcceso(dirName, failName);
        calculaLonxitude(dirName, failName);

        mLectura(dirName, failName);
        modoAcceso(dirName, failName);

        mEscritura(dirName, failName);
        modoAcceso(dirName, failName);

        mContido(dirName);

        recur(new File(dirName));

        borraFicheiro(dirName, failName);
        borraDirectorio(dirName);
    }
}