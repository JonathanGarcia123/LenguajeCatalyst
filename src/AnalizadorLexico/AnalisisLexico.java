import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;

public class AnalisisLexico {
    public static void main(String[] args) {
        // Abre el selector de archivos (JFileChooser)
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo de código fuente de Alquimia");
        
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("== Analisis Lexico ==");
            System.out.println("Archivo: " + selectedFile.getAbsolutePath() + "\n");
            
            try {
                // Prepara el flujo de lectura del archivo seleccionado
                FileInputStream fileStream = new FileInputStream(selectedFile);
                SimpleCharStream charStream = new SimpleCharStream(fileStream);
                AlquimiaParserTokenManager lexer = new AlquimiaParserTokenManager(charStream);
                
                // Recorre y muestra cada token reconocido
                Token t = lexer.getNextToken();
                while (t.kind != AlquimiaParserConstants.EOF) {
                    System.out.printf("TOKEN: %-15s <%d>\n", t.image, t.kind);
                    t = lexer.getNextToken();
                }
                
                System.out.println("\nSe analizo correctamente la gramatica.");
                fileStream.close();
                
            } catch (Exception e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            } catch (TokenMgrError e) {
                System.err.println("Error lexico: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
}