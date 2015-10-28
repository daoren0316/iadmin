package cc.kokoko.server.common.util;

import cc.kokoko.server.commons.util.DateUtil;
import cc.kokoko.server.commons.util.StringUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 导出Execl工具类
 *
 * @author HHang
 * @version V1.0.1
 */
public class ExportExeclUtil {

    /**
     * 导出Execl
     *
     * @param topHeader    文件标题
     * @param columnHeader 域标题
     * @param title        列标题
     * @param list         数据集
     */
    public static void export(String topHeader, String columnHeader, String[] title, List list, HttpServletRequest request, HttpServletResponse response) {
        // 获取浏览器的类型
        String userAgent = request.getHeader("USER-AGENT");
        // 如果使用Firefox浏览器，则把编码设置为GBK
        String encodingType = userAgent.indexOf("Firefox") != -1 ? "GBK" : "UTF-8";
        // 设置列宽（默认为25）
        int columnView = 25;
        // 使用JXL方式，导出Execl工具类
        export(topHeader, encodingType, columnHeader, title, list, columnView, request, response);
    }

    /**
     * 使用JXL方式，导出Execl工具类
     *
     * @param topHeader    文件标题
     * @param encodingType 字符类型
     * @param header       域标题
     * @param title        列标题
     * @param list         数据集
     * @param columnView   列宽
     */
    public static void export(String topHeader, String encodingType,
                              String header, String[] title, List list, int columnView, HttpServletRequest request, HttpServletResponse response) {
        // 获取数据总条数
        int column = list.size();
        // 数据值编号（总列数）
        int count = title.length > 0 ? title.length : 0;
        // 验证字符类型是否为空
        encodingType = StringUtil.isEmpty(encodingType) ? "UTF-8" : encodingType;

        try {
            // 定义输出类型
            response.setContentType("application/vnd.ms-excel");
            String filename = topHeader + ".xls";
            if ("UTF-8".equals(encodingType)) {
                filename = URLEncoder.encode(topHeader + ".xls", encodingType);
            } else
                filename = DateUtil.getTime(new Date(), "yyyyMMddHHmmss") + ".xls";

            // 设定输出头文件
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            // 创建Excel
            OutputStream os = response.getOutputStream();
            // 创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            // 创建工作表
            WritableSheet sheet = workbook.createSheet(header, 0);
            // 设置头行
            WritableFont font = new WritableFont(WritableFont.ARIAL, 30, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GRAY_50);
            WritableCellFormat formatHead = new WritableCellFormat(font);
            formatHead.setAlignment(Alignment.CENTRE);
            // 设置时间行
            WritableFont timeFont = new WritableFont(WritableFont.ARIAL);
            WritableCellFormat timeFormatHead = new WritableCellFormat(timeFont);
            timeFormatHead.setAlignment(Alignment.RIGHT);

            // 设置标题格式
            WritableCellFormat formatTitle = new WritableCellFormat();
            formatTitle.setAlignment(Alignment.CENTRE);
            formatTitle.setBackground(Colour.GRAY_50);
            // 设置内容格式
            WritableCellFormat formatContent = new WritableCellFormat();
            formatContent.setAlignment(Alignment.CENTRE);
            formatContent.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 设置头部
            sheet.addCell(new Label(0, 0, header, formatHead));
            sheet.mergeCells(0, 0, title.length - 1, 0);

            // 设置时间行
            String lineHeader = "导出时间：" + DateUtil.getTime(new Date(), DateUtil.DATE_FORMAT_TIME);
            sheet.addCell(new Label(0, 1, lineHeader, timeFormatHead));
            sheet.mergeCells(0, 1, title.length - 1, 0);

            // 设置标题
            for (int i = 0; i < title.length; i++) {
                // 设置列宽
                sheet.setColumnView(i, columnView);
                // 设置标题
                sheet.addCell(new Label(i, 2, title[i], formatTitle));
            }
            // 设置内容
            for (int i = 0; i < column; i++) {
                Object[] obj = (Object[]) list.get(i);
                int j = 0;
                for (int k = 0; k < count; k++) {
                    String str = "";
                    if (obj[k] != null && !("").equals(obj[k])) {
                        str = obj[k].toString();
                    }
                    sheet.addCell(new Label(j++, i + 3, str, formatContent));
                }
            }

            workbook.write();
            workbook.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String toUnicode(String str) {
        char[] arChar = str.toCharArray();
        int iValue = 0;
        String uStr = "";
        for (int i = 0; i < arChar.length; i++) {
            iValue = (int) str.charAt(i);
            if (iValue <= 256) {
                uStr += "\\" + Integer.toHexString(iValue);
            } else {
                uStr += "\\u" + Integer.toHexString(iValue);
            }
        }
        return uStr;
    }
}
