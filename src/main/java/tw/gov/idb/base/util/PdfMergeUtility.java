package tw.gov.idb.base.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * easyReport內建的 PdfUtility.mergeFile 效能太差.
 * 有大量檔案合併改用這個. 不要一次產生大量file在記憶體中.
 * 一次產生一個要merge的file. merge完畢後即可釋放資源.
 * 範例:
 *  PdfMergeUtility pdfMerge = new PdfMergeUtility();
 *  pdfMerge.merge(file1)
 *  pdfMerge.merge(file2)
 *  pdfMerge.merge(file3)
 *  ....
 *  pdfMerge.getFile();
 */
public class PdfMergeUtility {
    Document document;
    PdfCopy copy;
    ByteArrayOutputStream outFile;

    public PdfMergeUtility() throws DocumentException {
        document = new Document();
        outFile = new ByteArrayOutputStream();
        copy = new PdfSmartCopy(document, outFile);
        document.open();
    }

    public void merge(byte[] file1) throws IOException, DocumentException {
        PdfReader reader;
        if (file1 != null) {
            reader = new PdfReader(file1);
            copy.addDocument(reader);
            copy.freeReader(reader);
            reader.close();
        }
    }

    public byte[] getFile() {
        document.close();
        return outFile.toByteArray();
    }
}
