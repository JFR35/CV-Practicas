
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import com.itextpdf.text.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
public class HtmlToPdf {
    public static void main(String[] args) throws IOException {
        File htmlFile = new File("C:\\\\Users\\\\juanf\\\\OneDrive\\\\Documents\\\\DAW 1º\\\\CV Practicas\\\\index.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (OutputStream os = new FileOutputStream("C:\\\\Users\\\\juanf\\\\OneDrive\\\\Documents\\\\DAW 1º\\\\CV Practicas\\\\output2.pdf")) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext cntxt = renderer.getSharedContext();
            cntxt.setPrint(true);
            cntxt.setInteractive(false);
            String baseUrl = FileSystems.getDefault().getPath("/home/demo/Documents/html2pdf")
                    .toUri().toURL().toString();
            renderer.setDocumentFromString(doc.html(), baseUrl);
            renderer.layout();
            renderer.createPDF(os);
            System.out.println("Convertido");
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}