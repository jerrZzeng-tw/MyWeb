package com.iisigroup.easyreport.xls.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class StyleUtility {
    // 水平位置
    public static final short ALIGN_CENTER = HSSFCellStyle.ALIGN_CENTER;
    public static final short ALIGN_CENTER_SELECTION = HSSFCellStyle.ALIGN_CENTER_SELECTION;
    public static final short ALIGN_FILL = HSSFCellStyle.ALIGN_FILL;
    public static final short ALIGN_GENERAL = HSSFCellStyle.ALIGN_GENERAL;
    public static final short ALIGN_JUSTIFY = HSSFCellStyle.ALIGN_JUSTIFY;
    public static final short ALIGN_LEFT = HSSFCellStyle.ALIGN_LEFT;
    public static final short ALIGN_RIGHT = HSSFCellStyle.ALIGN_RIGHT;

    // 垂直位置
    public final static short VERTICAL_BOTTOM = HSSFCellStyle.VERTICAL_BOTTOM;
    public final static short VERTICAL_CENTER = HSSFCellStyle.VERTICAL_CENTER;
    public final static short VERTICAL_JUSTIFY = HSSFCellStyle.VERTICAL_JUSTIFY;
    public final static short VERTICAL_TOP = HSSFCellStyle.VERTICAL_TOP;

    // 邊框線條寬度
    public final static short BORDER_TYPE_0 = 0;// 上x 下x 左x 右x
    public final static short BORDER_TYPE_1 = 1;// 上o 下x 左x 右x
    public final static short BORDER_TYPE_2 = 2;// 上x 下o 左x 右x
    public final static short BORDER_TYPE_3 = 3;// 上x 下x 左o 右x
    public final static short BORDER_TYPE_4 = 4;// 上x 下x 左x 右o
    public final static short BORDER_TYPE_5 = 5;// 上o 下o 左x 右x
    public final static short BORDER_TYPE_6 = 6;// 上o 下x 左o 右x
    public final static short BORDER_TYPE_7 = 7;// 上o 下x 左x 右o
    public final static short BORDER_TYPE_8 = 8;// 上x 下o 左o 右x
    public final static short BORDER_TYPE_9 = 9;// 上x 下o 左x 右o
    public final static short BORDER_TYPE_10 = 10;// 上x 下x 左o 右o
    public final static short BORDER_TYPE_11 = 11;// 上o 下o 左o 右x
    public final static short BORDER_TYPE_12 = 12;// 上o 下o 左x 右o
    public final static short BORDER_TYPE_13 = 13;// 上x 下o 左o 右o
    public final static short BORDER_TYPE_14 = 14;// 上o 下o 左o 右o

    // 邊框線條寬度
    public final static short BORDER_DASH_DOT = HSSFCellStyle.BORDER_DASH_DOT;
    public final static short BORDER_DASH_DOT_DOT = HSSFCellStyle.BORDER_DASH_DOT_DOT;
    public final static short BORDER_DASHED = HSSFCellStyle.BORDER_DASHED;
    public final static short BORDER_DOTTED = HSSFCellStyle.BORDER_DOTTED;
    public final static short BORDER_DOUBLE = HSSFCellStyle.BORDER_DOUBLE;
    public final static short BORDER_HAIR = HSSFCellStyle.BORDER_HAIR;
    public final static short BORDER_MEDIUM = HSSFCellStyle.BORDER_MEDIUM;
    public final static short BORDER_MEDIUM_DASH_DOT = HSSFCellStyle.BORDER_MEDIUM_DASH_DOT;
    public final static short BORDER_MEDIUM_DASH_DOT_DOT = HSSFCellStyle.BORDER_MEDIUM_DASH_DOT_DOT;
    public final static short BORDER_MEDIUM_DASHED = HSSFCellStyle.BORDER_MEDIUM_DASHED;
    public final static short BORDER_NONE = HSSFCellStyle.BORDER_NONE;
    public final static short BORDER_SLANTED_DASH_DOT = HSSFCellStyle.BORDER_SLANTED_DASH_DOT;
    public final static short BORDER_THICK = HSSFCellStyle.BORDER_THICK;
    public final static short BORDER_THIN = HSSFCellStyle.BORDER_THIN;

    // 填充格樣式
    public static final short FILL_PATTERN_BRICKS = HSSFCellStyle.BRICKS;
    public static final short FILL_PATTERN_DIAMONDS = HSSFCellStyle.DIAMONDS;
    public static final short FILL_PATTERN_FINE_DOTS = HSSFCellStyle.FINE_DOTS;
    public static final short FILL_PATTERN_LEAST_DOTS = HSSFCellStyle.LEAST_DOTS;
    public static final short FILL_PATTERN_LESS_DOTS = HSSFCellStyle.LESS_DOTS;
    public static final short FILL_PATTERN_NO_FILL = HSSFCellStyle.NO_FILL;
    public static final short FILL_PATTERN_SOLID_FOREGROUND = HSSFCellStyle.SOLID_FOREGROUND;
    public static final short FILL_PATTERN_SPARSE_DOTS = HSSFCellStyle.SPARSE_DOTS;
    public static final short FILL_PATTERN_SQUARES = HSSFCellStyle.SQUARES;
    public static final short FILL_PATTERN_THICK_BACKWARD_DIAG = HSSFCellStyle.THICK_BACKWARD_DIAG;
    public static final short FILL_PATTERN_THICK_FORWARD_DIAG = HSSFCellStyle.THICK_FORWARD_DIAG;
    public static final short FILL_PATTERN_THICK_HORZ_BANDS = HSSFCellStyle.THICK_HORZ_BANDS;
    public static final short FILL_PATTERN_THICK_VERT_BANDS = HSSFCellStyle.THICK_VERT_BANDS;
    public static final short FILL_PATTERN_THIN_BACKWARD_DIAG = HSSFCellStyle.THIN_BACKWARD_DIAG;
    public static final short FILL_PATTERN_THIN_FORWARD_DIAG = HSSFCellStyle.THIN_FORWARD_DIAG;
    public static final short FILL_PATTERN_THIN_HORZ_BANDS = HSSFCellStyle.THIN_HORZ_BANDS;
    public static final short FILL_PATTERN_THIN_VERT_BANDS = HSSFCellStyle.THIN_VERT_BANDS;

    // 字型底線樣式
    public static final short U_NONE = Font.U_NONE;
    public static final short U_SINGLE = Font.U_SINGLE;
    public static final short U_SINGLE_ACCOUNTING = Font.U_SINGLE_ACCOUNTING;
    public static final short U_DOUBLE = Font.U_DOUBLE;
    public static final short U_DOUBLE_ACCOUNTING = Font.U_DOUBLE_ACCOUNTING;

    /**
     * 設定 cellstyle
     *
     * @param workbook
     * @param fillPattern
     * @param fillBackgroundColor
     * @param fillForegroundColor
     * @param borderType<br>      0：上x 下x 左x 右x<br>
     *                            1：上o 下x 左x 右x<br>
     *                            2：上x 下o 左x 右x<br>
     *                            3：上x 下x 左o 右x<br>
     *                            4：上x 下x 左x 右o<br>
     *                            5：上o 下o 左x 右x<br>
     *                            6：上o 下x 左o 右x<br>
     *                            7：上o 下x 左x 右o<br>
     *                            8：上x 下o 左o 右x<br>
     *                            9：上x 下o 左x 右o<br>
     *                            10：上x 下x 左o 右o<br>
     *                            11：上o 下o 左o 右x<br>
     *                            12：上o 下o 左x 右o<br>
     *                            13：上x 下o 左o 右o<br>
     *                            14：上o 下o 左o 右o<br>
     *                            <br>
     * @param borderWidth
     * @param autoWarp
     * @param alignment
     * @param vAlignment
     * @param font
     * @param dataFormat
     * @return
     */
    public static CellStyle formatCellStyle(Workbook workbook, short fillPattern, short fillBackgroundColor,
                                            short fillForegroundColor, short borderType, short borderWidth,
                                            boolean autoWarp, short alignment, short vAlignment, Font font,
                                            String dataFormat) {
        CellStyle styleCell = workbook.createCellStyle();
        styleCell.setFillPattern(fillPattern); // 設置可填充儲存格底色
        styleCell.setFillBackgroundColor(fillBackgroundColor); // 指定圖樣顏色
        styleCell.setFillForegroundColor(fillForegroundColor); // 指定底色
        styleCell.setAlignment(alignment); // 水平置中
        styleCell.setVerticalAlignment(vAlignment); // 垂直置中

        // 設定框線
        switch (borderType) {
            case (short) BORDER_TYPE_0:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(BORDER_NONE);
            case (short) BORDER_TYPE_1:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_2:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_3:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_4:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_5:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_6:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_7:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_8:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_9:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_10:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(BORDER_NONE);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_11:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(BORDER_NONE);
                break;
            case (short) BORDER_TYPE_12:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(BORDER_NONE);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_13:
                styleCell.setBorderTop(BORDER_NONE);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(borderWidth);
                break;
            case (short) BORDER_TYPE_14:
                styleCell.setBorderTop(borderWidth);
                styleCell.setBorderBottom(borderWidth);
                styleCell.setBorderLeft(borderWidth);
                styleCell.setBorderRight(borderWidth);
                break;
        }

        // 自動換行
        styleCell.setWrapText(autoWarp);

        // 字型設定
        if (font != null) {
            styleCell.setFont(font); // 設定字體
        }

        // 設定資料格式
        if (StringUtils.isNotBlank(dataFormat)) {
            DataFormat format = workbook.createDataFormat();
            styleCell.setDataFormat(format.getFormat(dataFormat));
        }

        return styleCell;
    }

    /**
     * 設定 dataFormat 格式
     *
     * @param workbook
     * @param cellStyle
     * @param dataFormat
     * @return
     */
    public static CellStyle setDataFormat(Workbook workbook, CellStyle cellStyle, String dataFormat) {
        if (StringUtils.isNotBlank(dataFormat)) {
            DataFormat format = workbook.createDataFormat();
            cellStyle.setDataFormat(format.getFormat(dataFormat));
        }
        return cellStyle;
    }

    /**
     * 設定字形樣式便於設定CellStyle使用
     *
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param fontColor
     * @param isBold
     * @param isItalic
     * @param isStrikeout
     * @param underline
     * @return
     */
    public static Font formatFont(Workbook workbook, String fontName, short fontSize, short fontColor, boolean isBold,
                                  boolean isItalic, boolean isStrikeout, byte underline) {
        Font font = workbook.createFont();
        font.setFontName(fontName); // 設定字體
        font.setFontHeightInPoints(fontSize);// 設定字體大小
        font.setColor(fontColor); // 顏色
        if (isBold) {
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗體
        } else {
            font.setBoldweight(Font.BOLDWEIGHT_NORMAL);// 一般
        }
        font.setItalic(isItalic);// 斜體
        font.setStrikeout(isStrikeout);// 刪除線
        font.setUnderline(underline);// 底線
        return font;
    }
}
