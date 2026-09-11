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
        String[] elementos = directorio.list();

        if (directorio.exists() && directorio.isDirectory() && elementos != null && elementos.length == 0) {
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
        String baseDir = "/home/dam26/IdeaProjects/AD/arquivosdir";
        String subdir = baseDir + "/subdir";

        System.out.println("=== 1. Crear arquivosdir e comprobar ===");
        creaDirectorio(baseDir);
        System.out.println(eDirectorio(baseDir));

        System.out.println("\n=== 2. Crear Products1.txt e comprobar ===");
        creaFicheiro(baseDir, "Products1.txt");
        System.out.println(eFicheiro(baseDir + "/Products1.txt"));

        System.out.println("\n=== 3. Crear subdir e Products2.txt ===");
        creaDirectorio(subdir);
        creaFicheiro(subdir, "Products2.txt");

        System.out.println("\n=== 4. Mostrar contido de primeiro nivel ===");
        mContido(baseDir);

        System.out.println("\n=== 5. Informacion de Products1.txt ===");
        modoAcceso(baseDir, "Products1.txt");
        calculaLonxitude(baseDir, "Products1.txt");

        System.out.println("\n=== 6. Forzar so lectura ===");
        mLectura(baseDir, "Products1.txt");
        modoAcceso(baseDir, "Products1.txt");

        System.out.println("\n=== 7. Forzar escritura ===");
        mEscritura(baseDir, "Products1.txt");
        modoAcceso(baseDir, "Products1.txt");

        System.out.println("\n=== 8. Borrar Products1.txt ===");
        borraFicheiro(baseDir, "Products1.txt");

        System.out.println("\n=== 9. Borrar o resto ===");
        borraFicheiro(subdir, "Products2.txt");
        borraDirectorio(subdir);
        borraDirectorio(baseDir);

        System.out.println("\n=== 10. Amosar recursivo (opcional) ===");
        // recur(new File(baseDir));
    }
}