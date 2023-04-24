package tw.gov.idb.base.base01.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base01.cases.Base0101Case;
import tw.gov.idb.base.base01.cases.Base0101Case.Base0101qCase;

@Slf4j
@Service
public class Base0101Service {

	@Autowired
    private ServletContext ctx;

    public static final String FILE_DIR = "/file/";

	public void query(Base0101Case caseData) throws Exception {
		caseData.setBase0101qCase(
				Base0101qCase.builder()
					.selectVal(caseData.getSelectVal())
					.checkboxVal(caseData.getCheckboxVal())
					.radioVal(caseData.getRadioVal())
					.inputVal(caseData.getInputVal()).build());
		
		//uploadFile
		String filename = caseData.getFileContent().getOriginalFilename();
		byte[] fileContent = caseData.getFileContent().getBytes();	
		
		if(fileContent != null && fileContent.length > 0) {
			uploadFile(filename,fileContent);
			caseData.setFileName(filename);
			caseData.setHaveFile(true);
		}
	}

	public void uploadFile(String filename,byte[] fileContent) {
		String filePath = ctx.getRealPath(StringUtils.join(FILE_DIR,filename));
		File outputFile = new File(filePath);

		try {
			File parentDir = outputFile.getParentFile();
		    if (!parentDir.exists() && !parentDir.mkdirs()) {
		        throw new RuntimeException("Failed to create directory: " + parentDir);
		    }

		    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
		        fos.write(fileContent);
		        fos.flush();
		    }

		    log.info("檔案已成功寫入：" + filePath);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public byte[] downloadFile(Base0101Case caseData) throws IOException {
		if(StringUtils.isNotBlank(caseData.getFileName())) {
			String filePath = ctx.getRealPath(StringUtils.join(FILE_DIR,caseData.getFileName()));
		    File file = new File(filePath);
		    if (!file.exists() || !file.isFile()) {
		        throw new FileNotFoundException("檔案不存在或不是一個檔案：" + filePath);
		    }
		    try (FileInputStream in = new FileInputStream(file)) {
		        return in.readAllBytes();
		    }
		}
		return null;
 	}
}
