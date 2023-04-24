package tw.gov.idb.base.framework.support;

import tw.gov.idb.base.util.HttpUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class FileOutputView extends AbstractView {

    /**
     * PDF 檔的預設 ContentType
     */
    public static final String PDF_FILE = "application/pdf";

    /**
     * Excel 檔的預設 ContentType
     */
    public static final String EXCEL_FILE = "application/vnd.ms-excel";

    /**
     * 未知檔案類型時的預設 ContentType
     */
    public static final String GENERAL_FILE = "application/octet-stream";

    private ByteArrayOutputStream baoOutput;
    private String filename;
    private String contentType;

    /**
     * Constructor
     *
     * @param baoOutput   含檔案內容的 ByteArrayOutputStream
     * @param filename    檔名 (直接傳入即可，不需依不同瀏覽器進行相關轉碼等動作)
     * @param contentType 檔案的 ContentType
     */
    public FileOutputView(ByteArrayOutputStream baoOutput, String filename, String contentType) {
        this.baoOutput = baoOutput;
        this.filename = filename;
        this.contentType = contentType;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        // 設定回傳回 Client 端之檔案大小, 若不設定,
        // 若 User 使用的瀏覽器為 IE 則可能收到空白的畫面
        response.setHeader("Content-disposition", "attachment; filename=\"" + HttpUtility.getFilenameByBrowser(
                StringUtils.defaultString(filename, "")) + "\"");
        response.setContentType(contentType); // 設定MIME
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentLength(baoOutput.size());

        // Flush to HTTP response.
        writeToResponse(response, baoOutput);
    }
}
