package com.iisigroup.easyreport.pdf.Helper;

import com.iisigroup.easyreport.pdf.exception.ReportException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeCodabar;
import com.itextpdf.text.pdf.BarcodeDatamatrix;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeEANSUPP;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodePostnet;
import com.itextpdf.text.pdf.PdfContentByte;
import com.swetake.util.Qrcode;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 產製 Barcode 圖檔，支援：<br>
 * <br>
 * QR CODE<br>
 * Code 39<br>
 * Code 39 extend<br>
 * Code 128、UCC/EAN-128、RAW<br>
 * EAN.UCC-13<br>
 * UCC-12 (UPC-A)<br>
 * EAN.UCC-8<br>
 * UPC-E<br>
 * ISBN 13、EAN.SUPP<br>
 * Code 25 – Interleaved 2 of 5<br>
 * Codabar<br>
 * POSTNET<br>
 * PLANET<br>
 * PDF417<br>
 * Datamatrix<br>
 *
 *  
 */
public class BarcodeHelper {

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code CODE
     * @return
     */
    public static Image createQRCode(String code) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, 100, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code  CODE
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createQRCode(String code, int scale) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, scale, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @return
     */
    public static Image createQRCodeWithVersion(String code, int version) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, 100, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param scale   縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createQRCodeWithVersion(String code, int version, int scale) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, scale, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code CODE
     * @param logo 要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCode(String code, java.awt.Image logo) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, 100, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCode(String code, java.awt.Image logo, int logoSize) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, 100, logo, logoSize);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code  CODE
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @param logo  要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCodeWithLogo(String code, int scale, java.awt.Image logo) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, scale, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogo(String code, int scale, java.awt.Image logo,
                                             int logoSize) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoScaleSize(code, version, scale, logo, logoSize);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param logo    要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCodeWithLogoAndVersion(String code, int version,
                                                       java.awt.Image logo) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, 100, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param version  QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogoAndVersion(String code, int version, java.awt.Image logo,
                                                       int logoSize) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, 100, logo, logoSize);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param scale   縮放百分比，如 <code>50</code>(%)
     * @param logo    要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCodeWithLogoAndVersion(String code, int version, int scale,
                                                       java.awt.Image logo) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, scale, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param version  QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogoAndVersion(String code, int version, int scale, java.awt.Image logo,
                                                       int logoSize) throws ReportException {
        return createQRCodeWithLogoScaleSize(code, version, scale, logo, logoSize);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param version  QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogoScaleSize(String code, int version, int scale, java.awt.Image logo,
                                                      int logoSize) throws ReportException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // Constructor Qrcode Object
            Qrcode qrcode = new Qrcode();
            // 容錯率L M Q H
            qrcode.setQrcodeErrorCorrect('M');
            // 字元模式, N 是數字, A 是英數文, 其它是 8 byte
            qrcode.setQrcodeEncodeMode('B');
            // 可使用的字串長短跟所設定的 QrcodeVersion 有關, 越大可設定的字越多, 0-40, 0 是自動
            // int version = Integer.parseInt(EnvHelper.getQrcodeVersion());

            int tempVersion = getQRCodeVersion(code);

            if (version < 1 || version > 40)
                version = tempVersion;

            if (version < tempVersion) // 如果設定的 QR CODE 版本無法承載那麼多資訊自動將版本設為可承載的版本
                version = tempVersion;

            qrcode.setQrcodeVersion(version);

            // getBytes
            byte[] d = StringUtils.defaultString(code).getBytes("UTF-8");

            // 設定圖檔寬度 Version 1 為 21 x 21 之後每大一版多 4
            int size = (21 + ((version - 1) * 4)) * 3 + 4;
            BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

            // createGraphics
            Graphics2D g = bi.createGraphics();

            // set background
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, size, size);

            // 設定字型顏色 => BLACK
            g.setColor(Color.BLACK);

            int pixoff = 2; // 設置偏移量，不設置將導致解析出錯

            // 轉出 Bytes
            if (d.length > 0) {
                boolean[][] s = qrcode.calQrcode(d);
                for (int i = 0; i < s.length; i++) {
                    for (int j = 0; j < s.length; j++) {
                        if (s[j][i]) {
                            g.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            }

            if (logo != null) {
                int logoWidth = calculateLogoWidth(size);
                if (logoSize > 0 && logoSize < logoWidth)
                    logoWidth = logoSize;
                int x = (size - logoWidth) / 2;
                g.drawImage(logo, x, x, logoWidth, logoWidth, null);
            }

            g.dispose();
            bi.flush();

            ImageIO.write(bi, "jpg", out);

            // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // encoder.encode(bi);

            byte[] imageBytes = out.toByteArray();

            out.close();

            Image image = Image.getInstance(imageBytes);

            if (scale > 0)
                image.scalePercent(scale);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code   CODE
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createQRCodeAbsoluteSize(String code, int width, int height) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoAbsoluteSize(code, version, width, height, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param width   圖片寬度
     * @param height  圖片高度
     * @return
     */
    public static Image createQRCodeAbsoluteSize(String code, int version, int width,
                                                 int height) throws ReportException {
        return createQRCodeWithLogoAbsoluteSize(code, version, width, height, null, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code   CODE
     * @param width  圖片寬度
     * @param height 圖片高度
     * @param logo   要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCodeWithLogoAbsoluteSize(String code, int width, int height,
                                                         java.awt.Image logo) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoAbsoluteSize(code, version, width, height, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogoAbsoluteSize(String code, int width, int height, java.awt.Image logo,
                                                         int logoSize) throws ReportException {
        int version = Integer.parseInt(EnvHelper.getQrcodeVersion());
        return createQRCodeWithLogoAbsoluteSize(code, version, width, height, logo, logoSize);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code    CODE
     * @param version QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param width   圖片寬度
     * @param height  圖片高度
     * @param logo    要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @return
     */
    public static Image createQRCodeWithLogoAbsoluteSize(String code, int version, int width, int height,
                                                         java.awt.Image logo) throws ReportException {
        return createQRCodeWithLogoAbsoluteSize(code, version, width, height, logo, 0);
    }

    /**
     * QR CODE
     * <p>
     * 產出來的 QR CODE 圖檔原始長、寬為 <code>(21 + ((version - 1) * 4)) * 3 + 4 px</code>
     *
     * @param code     CODE
     * @param version  QR CODE 的版本 (可傳 0 - 40, 0 表示自動）
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param logo     要放的 LOGO 圖檔 (只支援長寬相等的圖片)
     * @param logoSize LOGO 大小 (QR CODE 縮放前大小)，若指定的 LOGO 大小超過容錯率可接受的大小，將會以容錯率可接受的大小為主，傳入 0 表依容錯率可接受大小
     * @return
     */
    public static Image createQRCodeWithLogoAbsoluteSize(String code, int version, int width, int height,
                                                         java.awt.Image logo, int logoSize) throws ReportException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // Constructor Qrcode Object
            Qrcode qrcode = new Qrcode();
            // 容錯率L M Q H
            qrcode.setQrcodeErrorCorrect('M');
            // 字元模式, N 是數字, A 是英數文, 其它是 8 byte
            qrcode.setQrcodeEncodeMode('B');
            // 可使用的字串長短跟所設定的 QrcodeVersion 有關, 越大可設定的字越多, 0-40, 0 是自動
            // int version = Integer.parseInt(EnvHelper.getQrcodeVersion());

            int tempVersion = getQRCodeVersion(code);

            if (version < 1 || version > 40)
                version = tempVersion;

            if (version < tempVersion) // 如果設定的 QR CODE 版本無法承載那麼多資訊自動將版本設為可承載的版本
                version = tempVersion;

            qrcode.setQrcodeVersion(version);

            // getBytes
            byte[] d = StringUtils.defaultString(code).getBytes("UTF-8");

            // 設定圖檔寬度 Version 1 為 21 x 21 之後每大一版多 4
            int size = (21 + ((version - 1) * 4)) * 3 + 4;
            BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

            // createGraphics
            Graphics2D g = bi.createGraphics();

            // set background
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, size, size);

            // 設定字型顏色 => BLACK
            g.setColor(Color.BLACK);

            int pixoff = 2; // 設置偏移量，不設置將導致解析出錯

            // 轉出 Bytes
            if (d.length > 0) {
                boolean[][] s = qrcode.calQrcode(d);
                for (int i = 0; i < s.length; i++) {
                    for (int j = 0; j < s.length; j++) {
                        if (s[j][i]) {
                            g.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            }

            if (logo != null) {
                int logoWidth = calculateLogoWidth(size);
                if (logoSize > 0 && logoSize < logoWidth)
                    logoWidth = logoSize;
                int x = (size - logoWidth) / 2;
                g.drawImage(logo, x, x, logoWidth, logoWidth, null);
            }

            g.dispose();
            bi.flush();

            ImageIO.write(bi, "jpg", out);
            // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // encoder.encode(bi);

            byte[] imageBytes = out.toByteArray();

            out.close();

            Image image = Image.getInstance(imageBytes);

            image.scaleAbsoluteWidth(width);
            image.scaleAbsoluteHeight(height);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    /**
     * 依內容取得適合的 QR CODE 版本
     *
     * @param code CODE
     * @return
     */
    public static int getQRCodeVersion(String code) {
        if (StringUtils.isBlank(code))
            return 5;

        try {
            int nLength = code.getBytes("MS950").length;

            if (nLength >= 0 && nLength < 84)
                return 5;
            else if (nLength >= 84 && nLength < 106)
                return 6;
            else if (nLength >= 106 && nLength < 122)
                return 7;
            else if (nLength >= 122 && nLength < 152)
                return 8;
            else if (nLength >= 152 && nLength < 180)
                return 9;
            else if (nLength >= 180 && nLength < 213)
                return 10;
            else if (nLength >= 213 && nLength < 251)
                return 11;
            else if (nLength >= 251 && nLength < 287)
                return 12;
            else if (nLength >= 287 && nLength < 331)
                return 13;
            else if (nLength >= 331 && nLength < 362)
                return 14;
            else if (nLength >= 362 && nLength < 412)
                return 15;
            else if (nLength >= 412 && nLength < 450)
                return 16;
            else if (nLength >= 450 && nLength < 504)
                return 17;
            else if (nLength >= 504 && nLength < 560)
                return 18;
            else if (nLength >= 560 && nLength < 624)
                return 19;
            else if (nLength >= 624 && nLength < 666)
                return 20;
            else if (nLength >= 666 && nLength < 711)
                return 21;
            else if (nLength >= 711 && nLength < 779)
                return 22;
            else if (nLength >= 779 && nLength < 857)
                return 23;
            else if (nLength >= 857 && nLength < 911)
                return 24;
            else if (nLength >= 911 && nLength < 997)
                return 25;
            else if (nLength >= 997 && nLength < 1059)
                return 26;
            else if (nLength >= 1059 && nLength < 1125)
                return 27;
            else if (nLength >= 1125 && nLength < 1190)
                return 28;
            else if (nLength >= 1190 && nLength < 1264)
                return 29;
            else if (nLength >= 1264 && nLength < 1370)
                return 30;
            else if (nLength >= 1370 && nLength < 1452)
                return 31;
            else if (nLength >= 1452 && nLength < 1538)
                return 32;
            else if (nLength >= 1538 && nLength < 1628)
                return 33;
            else if (nLength >= 1628 && nLength < 1722)
                return 34;
            else if (nLength >= 1722 && nLength < 1809)
                return 35;
            else if (nLength >= 1809 && nLength < 1911)
                return 36;
            else if (nLength >= 1911 && nLength < 1989)
                return 37;
            else if (nLength >= 1989 && nLength < 2099)
                return 38;
            else if (nLength >= 2099 && nLength < 2213)
                return 39;
            else
                return 40;
        } catch (Exception e) {
            return 5;
        }
    }

    /**
     * 計算 QR CODE 可接受的 LOGO 圖檔寬度
     *
     * @param qrcodeWidth QR CODE 的圖檔寬度
     * @return
     */
    private static int calculateLogoWidth(int qrcodeWidth) {
        // 原本 容錯率 M 可容許被覆蓋的面積是 15%，但因為設為 15% 有時掃碼會失敗，因此這邊改成用 14% 來算
        int width = (int) Math.floor(Math.sqrt((qrcodeWidth * qrcodeWidth) * 0.14));
        if (width % 2 == 1)
            width -= 1;
        return width;
    }

    /**
     * Code 39
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb) {
        return createCode39(code, cb, 100, true, false, null, null);
    }

    /**
     * Code 39
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb, int scale) {
        return createCode39(code, cb, scale, true, false, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb, int scale, int[] barColor, int[] textColor) {
        return createCode39(code, cb, scale, true, false, barColor, textColor);
    }

    /**
     * Code 39
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb, int scale, boolean codeShow) {
        return createCode39(code, cb, scale, codeShow, false, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb, int scale, boolean codeShow, boolean codeAbove) {
        return createCode39(code, cb, scale, codeShow, codeAbove, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39(String code, PdfContentByte cb, int scale, boolean codeShow, boolean codeAbove,
                                     int[] barColor, int[] textColor) {
        Barcode39 code39 = new Barcode39();
        code39.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            code39.setFont(null);
        else {
            if (codeAbove)
                code39.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code39.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * Code 39
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createCode39AbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createCode39AbsoluteSize(code, cb, width, height, true, false, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39AbsoluteSize(String code, PdfContentByte cb, int width, int height, int[] barColor,
                                                 int[] textColor) {
        return createCode39AbsoluteSize(code, cb, width, height, true, false, barColor, textColor);
    }

    /**
     * Code 39
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode39AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean codeShow) {
        return createCode39AbsoluteSize(code, cb, width, height, codeShow, false, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode39AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean codeShow, boolean codeAbove) {
        return createCode39AbsoluteSize(code, cb, width, height, codeShow, codeAbove, null, null);
    }

    /**
     * Code 39
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean codeShow, boolean codeAbove, int[] barColor, int[] textColor) {
        Barcode39 code39 = new Barcode39();
        code39.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            code39.setFont(null);
        else {
            if (codeAbove)
                code39.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code39.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * Code 39 Extend
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb) {
        return createCode39Extend(code, cb, 100, true, false, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb, int scale) {
        return createCode39Extend(code, cb, scale, true, false, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb, int scale, int[] barColor, int[] textColor) {
        return createCode39Extend(code, cb, scale, true, false, barColor, textColor);
    }

    /**
     * Code 39 Extend
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb, int scale, boolean codeShow) {
        return createCode39Extend(code, cb, scale, codeShow, false, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb, int scale, boolean codeShow,
                                           boolean codeAbove) {
        return createCode39Extend(code, cb, scale, codeShow, codeAbove, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39Extend(String code, PdfContentByte cb, int scale, boolean codeShow,
                                           boolean codeAbove, int[] barColor, int[] textColor) {
        Barcode39 code39 = new Barcode39();
        code39.setCode(StringUtils.defaultString(code));

        code39.setExtended(true); // Code 39 Extend

        if (!codeShow)
            code39.setFont(null);
        else {
            if (codeAbove)
                code39.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code39.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * Code 39 Extend
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createCode39ExtendAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createCode39ExtendAbsoluteSize(code, cb, width, height, true, false, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39ExtendAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                       int[] barColor, int[] textColor) {
        return createCode39ExtendAbsoluteSize(code, cb, width, height, true, false, barColor, textColor);
    }

    /**
     * Code 39 Extend
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode39ExtendAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                       boolean codeShow) {
        return createCode39ExtendAbsoluteSize(code, cb, width, height, codeShow, false, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode39ExtendAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                       boolean codeShow, boolean codeAbove) {
        return createCode39ExtendAbsoluteSize(code, cb, width, height, codeShow, codeAbove, null, null);
    }

    /**
     * Code 39 Extend
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode39ExtendAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                       boolean codeShow, boolean codeAbove, int[] barColor,
                                                       int[] textColor) {
        Barcode39 code39 = new Barcode39();
        code39.setCode(StringUtils.defaultString(code));

        code39.setExtended(true); // Code 39 Extend

        if (!codeShow)
            code39.setFont(null);
        else {
            if (codeAbove)
                code39.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code39.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * Code 128
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb) {
        return createCode128(code, cb, 100, Barcode.CODE128, null, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale) {
        return createCode128(code, cb, scale, Barcode.CODE128, null, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeType 可傳入 Barcode.CODE128（預設）、Barcode.CODE128_UCC、Barcode.CODE128_RAW
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType) {
        return createCode128(code, cb, scale, codeType, null, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeType 可傳入 Barcode.CODE128（預設）、Barcode.CODE128_UCC、Barcode.CODE128_RAW
     * @param codeSet  可傳入 Barcode128.Barcode128CodeSet.A、Barcode128.Barcode128CodeSet.B（預設）、Barcode128.Barcode128CodeSet.C、Barcode128.Barcode128CodeSet.AUTO
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType,
                                      Barcode128.Barcode128CodeSet codeSet) {
        return createCode128(code, cb, scale, codeType, codeSet, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int[] barColor, int[] textColor) {
        return createCode128(code, cb, scale, Barcode.CODE128, null, true, false, barColor, textColor);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType, int[] barColor,
                                      int[] textColor) {
        return createCode128(code, cb, scale, codeType, null, true, false, barColor, textColor);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet   可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType,
                                      Barcode128.Barcode128CodeSet codeSet, int[] barColor, int[] textColor) {
        return createCode128(code, cb, scale, codeType, codeSet, true, false, barColor, textColor);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, boolean codeShow) {
        return createCode128(code, cb, scale, Barcode.CODE128, null, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeType 可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType, boolean codeShow) {
        return createCode128(code, cb, scale, codeType, null, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeType 可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet  可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType,
                                      Barcode128.Barcode128CodeSet codeSet, boolean codeShow) {
        return createCode128(code, cb, scale, codeType, codeSet, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, boolean codeShow, boolean codeAbove) {
        return createCode128(code, cb, scale, Barcode.CODE128, null, codeShow, codeAbove, null, null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet   可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128(String code, PdfContentByte cb, int scale, int codeType,
                                      Barcode128.Barcode128CodeSet codeSet, boolean codeShow, boolean codeAbove,
                                      int[] barColor, int[] textColor) {
        Barcode128 code128 = new Barcode128();

        if (codeType > 0)
            code128.setCodeType(codeType);

        if (codeSet != null) {
            code128.setCodeSet(codeSet);
        } else {
            String defaultCodeSet = EnvHelper.getCode128CodeSet();

            if (StringUtils.equalsIgnoreCase(defaultCodeSet, "A"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.A);
            else if (StringUtils.equalsIgnoreCase(defaultCodeSet, "B"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.B);
            else if (StringUtils.equalsIgnoreCase(defaultCodeSet, "C"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.C);
            else
                code128.setCodeSet(Barcode128.Barcode128CodeSet.B);
        }

        code128.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            code128.setFont(null);
        else {
            if (codeAbove)
                code128.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code128.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * Code 128
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createCode128AbsoluteSize(code, cb, width, height, Barcode.CODE128, null, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeType 可傳入 Barcode.CODE128（預設）、Barcode.CODE128_UCC、Barcode.CODE128_RAW
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, null, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeType 可傳入 Barcode.CODE128（預設）、Barcode.CODE128_UCC、Barcode.CODE128_RAW
     * @param codeSet  可傳入 Barcode128.Barcode128CodeSet.A、Barcode128.Barcode128CodeSet.B（預設）、Barcode128.Barcode128CodeSet.C、Barcode128.Barcode128CodeSet.AUTO
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  Barcode128.Barcode128CodeSet codeSet) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, codeSet, true, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int[] barColor,
                                                  int[] textColor) {
        return createCode128AbsoluteSize(code, cb, width, height, Barcode.CODE128, null, true, false, barColor,
                textColor);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  int[] barColor, int[] textColor) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, null, true, false, barColor, textColor);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet   可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  Barcode128.Barcode128CodeSet codeSet, int[] barColor,
                                                  int[] textColor) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, codeSet, true, false, barColor, textColor);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean codeShow) {
        return createCode128AbsoluteSize(code, cb, width, height, Barcode.CODE128, null, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeType 可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  boolean codeShow) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, null, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeType 可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet  可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  Barcode128.Barcode128CodeSet codeSet, boolean codeShow) {
        return createCode128AbsoluteSize(code, cb, width, height, codeType, codeSet, codeShow, false, null, null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean codeShow, boolean codeAbove) {
        return createCode128AbsoluteSize(code, cb, width, height, Barcode.CODE128, null, codeShow, codeAbove, null,
                null);
    }

    /**
     * Code 128
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeType  可傳入 <code>Barcode.CODE128</code>（預設）、<code>Barcode.CODE128_UCC</code>、<code>Barcode.CODE128_RAW</code>
     * @param codeSet   可傳入 <code>Barcode128.Barcode128CodeSet.A</code>、<code>Barcode128.Barcode128CodeSet.B</code>（預設）、<code>Barcode128.Barcode128CodeSet.C</code>、<code>Barcode128.Barcode128CodeSet.AUTO</code>
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode128AbsoluteSize(String code, PdfContentByte cb, int width, int height, int codeType,
                                                  Barcode128.Barcode128CodeSet codeSet, boolean codeShow,
                                                  boolean codeAbove, int[] barColor, int[] textColor) {
        Barcode128 code128 = new Barcode128();

        if (codeType > 0)
            code128.setCodeType(codeType);

        if (codeSet != null) {
            code128.setCodeSet(codeSet);
        } else {
            String defaultCodeSet = EnvHelper.getCode128CodeSet();

            if (StringUtils.equalsIgnoreCase(defaultCodeSet, "A"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.A);
            else if (StringUtils.equalsIgnoreCase(defaultCodeSet, "B"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.B);
            else if (StringUtils.equalsIgnoreCase(defaultCodeSet, "C"))
                code128.setCodeSet(Barcode128.Barcode128CodeSet.C);
            else
                code128.setCodeSet(Barcode128.Barcode128CodeSet.B);
        }

        code128.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            code128.setFont(null);
        else {
            if (codeAbove)
                code128.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code128.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * EAN.UCC-13
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb) {
        return createEANUCC13(code, cb, 100, true, true, false, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb, int scale) {
        return createEANUCC13(code, cb, scale, true, true, false, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb, int scale, boolean guardBars, int[] barColor,
                                       int[] textColor) {
        return createEANUCC13(code, cb, scale, guardBars, true, false, barColor, textColor);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow) {
        return createEANUCC13(code, cb, scale, guardBars, codeShow, false, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                       boolean codeAbove) {
        return createEANUCC13(code, cb, scale, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC13(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                       boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * EAN.UCC-13
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createEANUCC13AbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createEANUCC13AbsoluteSize(code, cb, width, height, true, true, false, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC13AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                   boolean guardBars, int[] barColor, int[] textColor) {
        return createEANUCC13AbsoluteSize(code, cb, width, height, guardBars, true, false, barColor, textColor);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createEANUCC13AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                   boolean guardBars, boolean codeShow) {
        return createEANUCC13AbsoluteSize(code, cb, width, height, guardBars, codeShow, false, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createEANUCC13AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                   boolean guardBars, boolean codeShow, boolean codeAbove) {
        return createEANUCC13AbsoluteSize(code, cb, width, height, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * EAN.UCC-13
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC13AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                   boolean guardBars, boolean codeShow, boolean codeAbove,
                                                   int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb) {
        return createUPCA(code, cb, 100, true, true, false, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb, int scale) {
        return createUPCA(code, cb, scale, true, true, false, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true<code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb, int scale, boolean guardBars, int[] barColor,
                                   int[] textColor) {
        return createUPCA(code, cb, scale, guardBars, true, false, barColor, textColor);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow) {
        return createUPCA(code, cb, scale, guardBars, codeShow, false, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                   boolean codeAbove) {
        return createUPCA(code, cb, scale, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCA(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                   boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.UPCA);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createUPCAAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createUPCAAbsoluteSize(code, cb, width, height, true, true, false, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true<code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCAAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               int[] barColor, int[] textColor) {
        return createUPCAAbsoluteSize(code, cb, width, height, guardBars, true, false, barColor, textColor);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createUPCAAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow) {
        return createUPCAAbsoluteSize(code, cb, width, height, guardBars, codeShow, false, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createUPCAAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow, boolean codeAbove) {
        return createUPCAAbsoluteSize(code, cb, width, height, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * UCC-12 (UPC-A)
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCAAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow, boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.UPCA);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * EAN.UCC-8
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb) {
        return createEANUCC8(code, cb, 100, true, true, false, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb, int scale) {
        return createEANUCC8(code, cb, scale, true, true, false, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb, int scale, boolean guardBars, int[] barColor,
                                      int[] textColor) {
        return createEANUCC8(code, cb, scale, guardBars, true, false, barColor, textColor);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow) {
        return createEANUCC8(code, cb, scale, guardBars, codeShow, false, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                      boolean codeAbove) {
        return createEANUCC8(code, cb, scale, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC8(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                      boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN8);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * EAN.UCC-8
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createEANUCC8AbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createEANUCC8AbsoluteSize(code, cb, width, height, true, true, false, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC8AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean guardBars, int[] barColor, int[] textColor) {
        return createEANUCC8AbsoluteSize(code, cb, width, height, guardBars, true, false, barColor, textColor);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createEANUCC8AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean guardBars, boolean codeShow) {
        return createEANUCC8AbsoluteSize(code, cb, width, height, guardBars, codeShow, false, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createEANUCC8AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean guardBars, boolean codeShow, boolean codeAbove) {
        return createEANUCC8AbsoluteSize(code, cb, width, height, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * EAN.UCC-8
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createEANUCC8AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean guardBars, boolean codeShow, boolean codeAbove,
                                                  int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN8);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * UPC-E
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb) {
        return createUPCE(code, cb, 100, true, true, false, null, null);
    }

    /**
     * UPC-E
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb, int scale) {
        return createUPCE(code, cb, scale, true, true, false, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb, int scale, boolean guardBars, int[] barColor,
                                   int[] textColor) {
        return createUPCE(code, cb, scale, guardBars, true, false, barColor, textColor);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow) {
        return createUPCE(code, cb, scale, guardBars, codeShow, false, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                   boolean codeAbove) {
        return createUPCE(code, cb, scale, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCE(String code, PdfContentByte cb, int scale, boolean guardBars, boolean codeShow,
                                   boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.UPCE);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * UPC-E
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createUPCEAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createUPCEAbsoluteSize(code, cb, width, height, true, true, false, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCEAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               int[] barColor, int[] textColor) {
        return createUPCEAbsoluteSize(code, cb, width, height, guardBars, true, false, barColor, textColor);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createUPCEAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow) {
        return createUPCEAbsoluteSize(code, cb, width, height, guardBars, codeShow, false, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createUPCEAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow, boolean codeAbove) {
        return createUPCEAbsoluteSize(code, cb, width, height, guardBars, codeShow, codeAbove, null, null);
    }

    /**
     * UPC-E
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param guardBars 是否要有凸出的 Guard Bars，<code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createUPCEAbsoluteSize(String code, PdfContentByte cb, int width, int height, boolean guardBars,
                                               boolean codeShow, boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.UPCE);

        codeEAN.setCode(StringUtils.defaultString(code));

        codeEAN.setGuardBars(guardBars);

        if (!codeShow)
            codeEAN.setFont(null);
        else {
            if (codeAbove)
                codeEAN.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codeEAN.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * ISBN 13
     *
     * @param code CODE
     * @param supp SUPP5 或 SUPP2 碼
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createISBN(String code, String supp, PdfContentByte cb) {
        return createISBN(code, supp, cb, 100, null, null);
    }

    /**
     * ISBN 13
     *
     * @param code  CODE
     * @param supp  SUPP5 或 SUPP2 碼
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createISBN(String code, String supp, PdfContentByte cb, int scale) {
        return createISBN(code, supp, cb, scale, null, null);
    }

    /**
     * ISBN 13
     *
     * @param code      CODE
     * @param supp      SUPP5 或 SUPP2 碼
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createISBN(String code, String supp, PdfContentByte cb, int scale, int[] barColor,
                                   int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode(StringUtils.defaultString(code));

        BarcodeEAN codeSUPP = null;

        if (StringUtils.isNotBlank(supp) && (StringUtils.length(supp) == 2 || StringUtils.length(supp) == 5)) {
            codeSUPP = new BarcodeEAN();

            if (StringUtils.length(supp) != 5)
                codeSUPP.setCodeType(Barcode.SUPP2);
            else
                codeSUPP.setCodeType(Barcode.SUPP5);

            codeSUPP.setCode(supp);
            codeSUPP.setBaseline(-2);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = null;

        if (codeSUPP == null) {
            image = codeEAN.createImageWithBarcode(cb, bColor, tColor);
        } else {
            BarcodeEANSUPP eanSupp = new BarcodeEANSUPP(codeEAN, codeSUPP);
            image = eanSupp.createImageWithBarcode(cb, bColor, tColor);
        }

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * ISBN 13
     *
     * @param code   CODE
     * @param supp   SUPP5 或 SUPP2 碼
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createISBNAbsoluteSize(String code, String supp, PdfContentByte cb, int width, int height) {
        return createISBNAbsoluteSize(code, supp, cb, width, height, null, null);
    }

    /**
     * ISBN 13
     *
     * @param code      CODE
     * @param supp      SUPP5 或 SUPP2 碼
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createISBNAbsoluteSize(String code, String supp, PdfContentByte cb, int width, int height,
                                               int[] barColor, int[] textColor) {
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode(StringUtils.defaultString(code));

        BarcodeEAN codeSUPP = null;

        if (StringUtils.isNotBlank(supp) && (StringUtils.length(supp) == 2 || StringUtils.length(supp) == 5)) {
            codeSUPP = new BarcodeEAN();

            if (StringUtils.length(supp) != 5)
                codeSUPP.setCodeType(Barcode.SUPP2);
            else
                codeSUPP.setCodeType(Barcode.SUPP5);

            codeSUPP.setCode(supp);
            codeSUPP.setBaseline(-2);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = null;

        if (codeSUPP == null) {
            image = codeEAN.createImageWithBarcode(cb, bColor, tColor);
        } else {
            BarcodeEANSUPP eanSupp = new BarcodeEANSUPP(codeEAN, codeSUPP);
            image = eanSupp.createImageWithBarcode(cb, bColor, tColor);
        }

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb) {
        return createCode25(code, cb, 100, false, false, true, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale) {
        return createCode25(code, cb, scale, false, false, true, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param scale        縮放百分比，如 <code>50</code>(%)
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale, boolean genChecksum,
                                     boolean checksumText) {
        return createCode25(code, cb, scale, genChecksum, checksumText, true, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param scale        縮放百分比，如 <code>50</code>(%)
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param barColor     BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor    CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale, boolean genChecksum,
                                     boolean checksumText, int[] barColor, int[] textColor) {
        return createCode25(code, cb, scale, genChecksum, checksumText, true, false, barColor, textColor);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param scale        縮放百分比，如 <code>50</code>(%)
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale, boolean genChecksum,
                                     boolean checksumText, boolean codeShow) {
        return createCode25(code, cb, scale, genChecksum, checksumText, codeShow, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param scale        縮放百分比，如 <code>50</code>(%)
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove    CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale, boolean genChecksum,
                                     boolean checksumText, boolean codeShow, boolean codeAbove) {
        return createCode25(code, cb, scale, genChecksum, checksumText, codeShow, codeAbove, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param scale        縮放百分比，如 <code>50</code>(%)
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove    CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor     BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor    CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode25(String code, PdfContentByte cb, int scale, boolean genChecksum,
                                     boolean checksumText, boolean codeShow, boolean codeAbove, int[] barColor,
                                     int[] textColor) {
        BarcodeInter25 code25 = new BarcodeInter25();

        code25.setGenerateChecksum(genChecksum);

        code25.setCode(StringUtils.defaultString(code));

        if (!codeShow) {
            code25.setFont(null);
        } else {
            if (codeAbove)
                code25.setBaseline(-1f);

            if (genChecksum && checksumText)
                code25.setChecksumText(true);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code25.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createCode25AbsoluteSize(code, cb, width, height, false, false, true, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param width        圖片寬度
     * @param height       圖片高度
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean genChecksum, boolean checksumText) {
        return createCode25AbsoluteSize(code, cb, width, height, genChecksum, checksumText, true, false, null, null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param width        圖片寬度
     * @param height       圖片高度
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param barColor     BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor    CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean genChecksum, boolean checksumText, int[] barColor,
                                                 int[] textColor) {
        return createCode25AbsoluteSize(code, cb, width, height, genChecksum, checksumText, true, false, barColor,
                textColor);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param width        圖片寬度
     * @param height       圖片高度
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean genChecksum, boolean checksumText, boolean codeShow) {
        return createCode25AbsoluteSize(code, cb, width, height, genChecksum, checksumText, codeShow, false, null,
                null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param width        圖片寬度
     * @param height       圖片高度
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove    CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean genChecksum, boolean checksumText, boolean codeShow,
                                                 boolean codeAbove) {
        return createCode25AbsoluteSize(code, cb, width, height, genChecksum, checksumText, codeShow, codeAbove, null,
                null);
    }

    /**
     * Code 25 – Interleaved 2 of 5
     *
     * @param code         CODE
     * @param cb           <code>PdfContentByte</code> 物件
     * @param width        圖片寬度
     * @param height       圖片高度
     * @param genChecksum  是否產生 Checksum 碼（預設為不產生 <code>false</code>）
     * @param checksumText 是否顯示 Checksum 碼（預設為不顯示 <code>false</code>，僅在 <code>genChecksum</code> 及 <code>codeShow</code> 為 <code>true</code> 時才有意義）
     * @param codeShow     CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove    CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor     BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor    CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCode25AbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 boolean genChecksum, boolean checksumText, boolean codeShow,
                                                 boolean codeAbove, int[] barColor, int[] textColor) {
        BarcodeInter25 code25 = new BarcodeInter25();

        code25.setGenerateChecksum(genChecksum);

        code25.setCode(StringUtils.defaultString(code));

        if (!codeShow) {
            code25.setFont(null);
        } else {
            if (codeAbove)
                code25.setBaseline(-1f);

            if (genChecksum && checksumText)
                code25.setChecksumText(true);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = code25.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * POSTNET
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createPOSTNET(String code, PdfContentByte cb) {
        return createPOSTNET(code, cb, 100, null);
    }

    /**
     * POSTNET
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createPOSTNET(String code, PdfContentByte cb, int scale) {
        return createPOSTNET(code, cb, scale, null);
    }

    /**
     * POSTNET
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param barColor BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createPOSTNET(String code, PdfContentByte cb, int scale, int[] barColor) {
        BarcodePostnet codePost = new BarcodePostnet();
        codePost.setCode(StringUtils.defaultString(code));

        BaseColor bColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        Image image = codePost.createImageWithBarcode(cb, bColor, null);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * POSTNET
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createPOSTNETAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createPOSTNETAbsoluteSize(code, cb, width, height, null);
    }

    /**
     * POSTNET
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param barColor BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createPOSTNETAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  int[] barColor) {
        BarcodePostnet codePost = new BarcodePostnet();
        codePost.setCode(StringUtils.defaultString(code));

        BaseColor bColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        Image image = codePost.createImageWithBarcode(cb, bColor, null);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * PLANET
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createPLANET(String code, PdfContentByte cb) {
        return createPLANET(code, cb, 100, null);
    }

    /**
     * PLANET
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createPLANET(String code, PdfContentByte cb, int scale) {
        return createPLANET(code, cb, scale, null);
    }

    /**
     * PLANET
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param barColor BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createPLANET(String code, PdfContentByte cb, int scale, int[] barColor) {
        BarcodePostnet codePlanet = new BarcodePostnet();
        codePlanet.setCode(StringUtils.defaultString(code));

        codePlanet.setCodeType(Barcode.PLANET);

        BaseColor bColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        Image image = codePlanet.createImageWithBarcode(cb, bColor, null);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * PLANET
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createPLANETAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createPLANETAbsoluteSize(code, cb, width, height, null);
    }

    /**
     * PLANET
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param barColor BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createPLANETAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                 int[] barColor) {
        BarcodePostnet codePlanet = new BarcodePostnet();
        codePlanet.setCode(StringUtils.defaultString(code));

        codePlanet.setCodeType(Barcode.PLANET);

        BaseColor bColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        Image image = codePlanet.createImageWithBarcode(cb, bColor, null);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * CODABAR
     *
     * @param code CODE
     * @param cb   <code>PdfContentByte</code> 物件
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb) {
        return createCODABAR(code, cb, 100, true, false, null, null);
    }

    /**
     * CODABAR
     *
     * @param code  CODE
     * @param cb    <code>PdfContentByte</code> 物件
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb, int scale) {
        return createCODABAR(code, cb, scale, true, false, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb, int scale, int[] barColor, int[] textColor) {
        return createCODABAR(code, cb, scale, true, false, barColor, textColor);
    }

    /**
     * CODABAR
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param scale    縮放百分比，如 <code>50</code>(%)
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb, int scale, boolean codeShow) {
        return createCODABAR(code, cb, scale, codeShow, false, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb, int scale, boolean codeShow, boolean codeAbove) {
        return createCODABAR(code, cb, scale, codeShow, codeAbove, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param scale     縮放百分比，如 <code>50</code>(%)
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCODABAR(String code, PdfContentByte cb, int scale, boolean codeShow, boolean codeAbove,
                                      int[] barColor, int[] textColor) {
        BarcodeCodabar codabar = new BarcodeCodabar();
        codabar.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            codabar.setFont(null);
        else {
            if (codeAbove)
                codabar.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codabar.createImageWithBarcode(cb, bColor, tColor);

        if (scale > 0)
            image.scalePercent(scale);

        return image;
    }

    /**
     * CODABAR
     *
     * @param code   CODE
     * @param cb     <code>PdfContentByte</code> 物件
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createCODABARAbsoluteSize(String code, PdfContentByte cb, int width, int height) {
        return createCODABARAbsoluteSize(code, cb, width, height, true, false, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCODABARAbsoluteSize(String code, PdfContentByte cb, int width, int height, int[] barColor,
                                                  int[] textColor) {
        return createCODABARAbsoluteSize(code, cb, width, height, true, false, barColor, textColor);
    }

    /**
     * CODABAR
     *
     * @param code     CODE
     * @param cb       <code>PdfContentByte</code> 物件
     * @param width    圖片寬度
     * @param height   圖片高度
     * @param codeShow CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @return
     */
    public static Image createCODABARAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean codeShow) {
        return createCODABARAbsoluteSize(code, cb, width, height, codeShow, false, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @return
     */
    public static Image createCODABARAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean codeShow, boolean codeAbove) {
        return createCODABARAbsoluteSize(code, cb, width, height, codeShow, codeAbove, null, null);
    }

    /**
     * CODABAR
     *
     * @param code      CODE
     * @param cb        <code>PdfContentByte</code> 物件
     * @param width     圖片寬度
     * @param height    圖片高度
     * @param codeShow  CODE 文字是否顯示 <code>true</code> 顯示；<code>false</code> 不顯示
     * @param codeAbove CODE 文字顯示於 BAR 的上方或下方，僅在 <code>codeShow</code> 為 <code>true</code> 時才有作用，<code>true</code> 顯示於上方；<code>false</code> 顯示於下方
     * @param barColor  BAR 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @param textColor CODE 色彩 RGB 值（如: <code>{255, 255, 200}</code>），若不指定則傳入 <code>null</code>
     * @return
     */
    public static Image createCODABARAbsoluteSize(String code, PdfContentByte cb, int width, int height,
                                                  boolean codeShow, boolean codeAbove, int[] barColor,
                                                  int[] textColor) {
        BarcodeCodabar codabar = new BarcodeCodabar();
        codabar.setCode(StringUtils.defaultString(code));

        if (!codeShow)
            codabar.setFont(null);
        else {
            if (codeAbove)
                codabar.setBaseline(-1f);
        }

        BaseColor bColor = null;
        BaseColor tColor = null;

        if (barColor != null && barColor.length == 3)
            bColor = new BaseColor(barColor[0], barColor[1], barColor[2]);

        if (textColor != null && textColor.length == 3)
            tColor = new BaseColor(textColor[0], textColor[1], textColor[2]);

        Image image = codabar.createImageWithBarcode(cb, bColor, tColor);

        image.scaleAbsoluteWidth(width);
        image.scaleAbsoluteHeight(height);

        return image;
    }

    /**
     * PDF417
     *
     * @param text 內容
     * @return
     */
    public static Image createPDF417(String text) throws ReportException {
        return createPDF417(text, 100);
    }

    /**
     * PDF417
     *
     * @param text  內容
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createPDF417(String text, int scale) throws ReportException {
        try {
            BarcodePDF417 pdf417 = new BarcodePDF417();
            pdf417.setText(StringUtils.defaultString(text));

            Image image = pdf417.getImage();

            if (scale > 0)
                image.scalePercent(scale);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    /**
     * PDF417
     *
     * @param text   內容
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createPDF417AbsoluteSize(String text, int width, int height) throws ReportException {
        try {
            BarcodePDF417 pdf417 = new BarcodePDF417();
            pdf417.setText(StringUtils.defaultString(text));

            Image image = pdf417.getImage();

            image.scaleAbsoluteWidth(width);
            image.scaleAbsoluteHeight(height);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    /**
     * Datamatrix
     *
     * @param text 內容
     * @return
     */
    public static Image createDatamatrix(String text) throws ReportException {
        return createDatamatrix(text, 100);
    }

    /**
     * Datamatrix
     *
     * @param text  內容
     * @param scale 縮放百分比，如 <code>50</code>(%)
     * @return
     */
    public static Image createDatamatrix(String text, int scale) throws ReportException {
        try {
            BarcodeDatamatrix datamatrix = new BarcodeDatamatrix();
            datamatrix.generate(StringUtils.defaultString(text));

            Image image = datamatrix.createImage();

            if (scale > 0)
                image.scalePercent(scale);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    /**
     * Datamatrix
     *
     * @param text   內容
     * @param width  圖片寬度
     * @param height 圖片高度
     * @return
     */
    public static Image createDatamatrixAbsoluteSize(String text, int width, int height) throws ReportException {
        try {
            BarcodeDatamatrix datamatrix = new BarcodeDatamatrix();
            datamatrix.generate(StringUtils.defaultString(text));

            Image image = datamatrix.createImage();

            image.scaleAbsoluteWidth(width);
            image.scaleAbsoluteHeight(height);

            return image;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

}
