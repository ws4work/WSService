package personal.ws.myservice.excel.export;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.ws.util.date.DateUtils;
import personal.ws.util.string.StrUtil;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class ExcelExportService {

    public boolean finishExcel;
    protected List<String> filenamesList = new ArrayList<String>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询数据总条数
     *
     * @return
     */
    abstract public Long queryCount();

    /**
     * 查询数据
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    abstract public List queryDataList();

    /**
     * 分页查询数据
     *
     * @param pageNo   页数
     * @param pageSize 每页条数
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    abstract public List queryDataListByPage(String pageNo, String pageSize);

    /**
     * 查询数据总条数
     *
     * @param beginFormat 开始时间
     * @param endFormat   结束时间
     *
     * @return
     */
    abstract public Long queryCountDaily(String beginFormat, String endFormat);

    /**
     * 查询数据
     *
     * @param beginFormat 开始时间
     * @param endFormat   结束时间
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    abstract public List queryDataListDaily(String beginFormat, String endFormat);

    /**
     * 分页查询数据
     *
     * @param beginFormat 开始时间
     * @param endFormat   结束时间
     * @param pageNo      页数
     * @param pageSize    每页条数
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    abstract public List queryDataListByPageDaily(String beginFormat, String endFormat, String pageNo, String pageSize);

    /**
     * 生成订单查询excel
     *
     * @param excelNameBeginString 导出excel文件名开头
     * @param title                excel标题
     * @param path                 导出excel路径
     * @param excelname            导出excel名
     * @param exceltype            导出类型.xlsx
     */
    public void excelSingleFile(final String excelNameBeginString, final String[] title, final int[] excelwidths, final String path, final String excelname, final String exceltype, final String sheetname) {
        try {
            //增加原子变量,线程数+1
            long size = 65534;
            //查询数据总条数
            Long orderTotalCount = queryCount();
            long checkflag = orderTotalCount / 65534;
            if(checkflag > 0){
                for (long j = 0L; j <= checkflag; j++) {
                    //不分页查询
                    //TODO
                    @SuppressWarnings("unchecked")
                    List<Object> dataList = queryDataListByPage((j + 1) + "", size + "");
                    //转换数据
                    List<ArrayList<String>> excelList = new ArrayList<ArrayList<String>>();
                    if(dataList.size() > 0){
                        excelList = dataTransformer(dataList);
                    }
                    excel(excelList, title, excelwidths, path, excelNameBeginString, excelname, DateUtils.getToday(), exceltype, sheetname, 1, j);
                }
            } else {
                @SuppressWarnings("unchecked")
                List<Object> dataList = queryDataList();
                //转换数据
                List<ArrayList<String>> excelList = new ArrayList<ArrayList<String>>();
                if(dataList.size() > 0){
                    excelList = dataTransformer(dataList);
                }
                excel(excelList, title, excelwidths, path, excelNameBeginString, excelname, DateUtils.getToday().replace("-", ""), exceltype, sheetname, 1, 0L);
            }
        } catch (Exception e) {
            logger.info(e.getStackTrace().toString());
        }
    }

    /**
     * 生成订单查询excel
     *
     * @param excelNameBeginString 导出excel文件名开头
     * @param title                excel标题
     * @param path                 导出excel路径
     * @param excelname            导出excel名
     * @param exceltype            导出类型.xlsx
     * @param beginDatestr         导出开始时间
     * @param endDatestr           导出结束时间
     */
    public void excelExportWithdaily(final String excelNameBeginString, final String[] title, final int[] excelwidths, final String path, final String excelname, final String exceltype, final String beginDatestr, final String endDatestr, final String sheetname) {
        //转化日期格式
        SimpleDateFormat beginningFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat enddingFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        final Date beginDate = DateUtils.StrToDate(beginDatestr);
        List<String> betweenDates = DateUtils.getBetweenDates(beginDatestr, endDatestr);
        Date endDate = DateUtils.StrToDate(endDatestr);
        Calendar begincal = Calendar.getInstance();
        begincal.setTime(beginDate);
        Calendar endcal = Calendar.getInstance();
        endcal.setTime(endDate);
        //开始到结束共有多少天
        final int daysNum = betweenDates.size();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
        //原子变量:用来统计线程是否结束
        final AtomicInteger number = new AtomicInteger(0);
        //每天进行循环查询输出excel
        for (int i = 0; i < daysNum; i++) {
            final int num = i;
            Calendar thisCal = Calendar.getInstance();
            thisCal.setTime(beginDate);
            thisCal.add(Calendar.DATE, i);
            final Date date = thisCal.getTime();
            //根据当前时间段判断每一段时间起止时间
            String format1 = "";
            String format2 = "";
            if(num == 0){//第一天
                format1 = DateUtils.DateToStr(beginDate);
                if(daysNum == 1){
                    format2 = DateUtils.DateToStr(endDate);
                } else {
                    format2 = enddingFormat.format(date);
                }
            } else if(num == daysNum - 1){//最后一天
                format2 = DateUtils.DateToStr(endDate);
                if(daysNum == 1){
                    format1 = DateUtils.DateToStr(beginDate);
                } else {
                    format1 = beginningFormat.format(date);
                }
            } else {
                format1 = beginningFormat.format(date);
                format2 = enddingFormat.format(date);
            }
            final String beginFormat = format1;
            final String endFormat = format2;
            //执行线程
            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        //增加原子变量,线程数+1
                        number.getAndIncrement();
                        long size = 65534;
                        //查询数据总条数
                        Long orderTotalCount = queryCountDaily(beginDatestr, endDatestr);
                        long checkflag = orderTotalCount / 65534;
                        if(checkflag > 0){
                            for (long j = 0L; j <= checkflag; j++) {
                                //不分页查询
                                //TODO
                                @SuppressWarnings("unchecked")
                                List<Object> dataList = queryDataListByPageDaily(beginFormat, endFormat, (j + 1) + "", size + "");
                                //转换数据
                                List<ArrayList<String>> excelList = new ArrayList<ArrayList<String>>();
                                if(dataList.size() > 0){
                                    excelList = dataTransformer(dataList);
                                }
                                excel(excelList, title, excelwidths, path, excelNameBeginString, excelname, beginFormat.substring(0, 10).replace("-", ""), exceltype, sheetname, num, j);
                            }
                            if(filenamesList.size() < num){
                                finishExcel = false;
                            } else {
                                finishExcel = true;
                            }
                        } else {
                            @SuppressWarnings("unchecked")
                            List<Object> dataList = queryDataListDaily(beginFormat, endFormat);
                            //转换数据
                            List<ArrayList<String>> excelList = new ArrayList<ArrayList<String>>();
                            if(dataList.size() > 0){
                                excelList = dataTransformer(dataList);
                            }
                            excel(excelList, title, excelwidths, path, excelNameBeginString, excelname, beginFormat.substring(0, 10).replace("-", ""), exceltype, sheetname, 1, 0L);
                            if(filenamesList.size() < num){
                                finishExcel = false;
                            } else {
                                finishExcel = true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        number.getAndDecrement();
                    }
                }
            });
        }
        boolean b = number.get() == 0;
        while (!b) {
            b = number.get() == 0;
        }
    }

    /**
     * 从list中转换成输出excel的list<ArrayList>,并转换数据
     *
     * @param list
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    //TODO
    public List<ArrayList<String>> dataTransformer(List list) {
        List<ArrayList<String>> datasList = new ArrayList<ArrayList<String>>();
        Object object = list.get(0);
        Class obj = object.getClass();
        //Method[] methods = obj.getMethods();
        Field[] fields = obj.getDeclaredFields();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> dataList = new ArrayList<String>();
            for (int j = 0; j < fields.length; j++) {
                //属性名
                String name = fields[j].getName();
                if(!"serialVersionUID".equals(name)){
                    try {
                        //方法
                        Method method = obj.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
                        //值
                        Object value = method.invoke(list.get(i));
                        //System.out.println(name + ":" + StrUtil.obj2str(value));
                        Class type = fields[j].getType();
                        //数据转换,按需要修改
                        //TODO
                        if(null != value){
                            value = typeDealer(value, type);
                            value = nameDealer(name, value);
                        }
                        dataList.add(StrUtil.obj2str(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            datasList.add(dataList);
        }
        return datasList;
    }

    /**
     * 根据属性名处理数据
     *
     * @param name
     * @param value
     *
     * @return
     */
    public abstract Object nameDealer(String name, Object value);

    /**
     * 根据类型进行数据处理
     *
     * @param value
     * @param type
     *
     * @return
     */
    public abstract Object typeDealer(Object value, Class type);

    /**
     * 根据属性名处理数据
     *
     * @param name
     * @param value
     *
     * @return
     */
//    private Object nameDealer(String name, Object value) {
//
//        if("result".equals(name)){
//            switch (StrUtil.obj2str(value)) {
//                case "10":
//                    value = "未扣款";
//                    break;
//                case "20":
//                    value = "扣款成功";
//                    break;
//                case "30":
//                    value = "扣款失败";
//                    break;
//                default:
//                    value = "";
//                    break;
//            }
//        }
//        if("busitype".equals(name)){
//            BusiTypeEnum typeEnum = BusiTypeEnum.getByCode(StrUtil.obj2str(value));
//            value = StrUtil.obj2str(typeEnum.getDesc());
//        }
//        if("dc".equals(name)){
//            switch (StrUtil.obj2str(value)) {
//                case "1":
//                    value = "入";
//                    break;
//                case "-1":
//                    value = "出";
//                    break;
//                default:
//                    value = "";
//                    break;
//            }
//        }
//        if("acctype".equals(name)){
//            switch (StrUtil.obj2str(value)) {
//                case "0":
//                    value = "钻石";
//                    break;
//                case "1":
//                    value = "豆包";
//                    break;
//                default:
//                    value = "未知";
//                    break;
//            }
//        }
//        return value;
//    }

    /**
     * 根据类型进行数据处理
     *
     * @param value
     * @param type
     *
     * @return
     */
//    @SuppressWarnings("rawtypes")
//    private Object typeDealer(Object value, Class type) {
//        //除去日期格式末尾.0
//        if(type == Date.class){
//            value = DateUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
//        }
//        //BigDecimal保留两位小数
//        if(type == BigDecimal.class){
//            value = StrUtil.obj2big(value).setScale(2, BigDecimal.ROUND_HALF_UP);
//        }
//        if(type == com.dss.common.model.admin.vo.UserVo.class){
//            value = StrUtil.obj2str(((com.dss.common.model.admin.vo.UserVo)value).getLogin_name());
//        }
//        return value;
//    }

    /**
     * 输出excel
     *
     * @param list
     * @param path
     * @param username
     * @param excelDate
     * @param excelsheetnum
     *
     * @return
     */
    public void excel(List<ArrayList<String>> list, String[] title, int excelwidths[], String path, String username, String excelname, String excelDate, String exceltype, String sheetname, Integer excelnum, long excelsheetnum) {
        if(list.size() <= 0 && excelnum > 1){
            filenamesList.add("");
            return;
        }
        SXSSFWorkbook hssfWorkbook = new SXSSFWorkbook(100);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        //List list = CreateSimpleExcelToDisk.getStudent();
        try {
            for (int num = 0; num < (list.size() / 65535) + 1; num++) {
                Sheet sheet = hssfWorkbook.createSheet(sheetname);
                for (int i = 0; i < excelwidths.length; i++) {
                    sheet.setColumnWidth(i, excelwidths[i]);
                }
                // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
                Row row = sheet.createRow((int) 0);
                // 第四步，创建单元格，并设置值表头 设置表头居中
                CellStyle style = hssfWorkbook.createCellStyle();
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
                Cell cell;
                for (int i = 0; i < title.length; i++) {
                    cell = row.createCell((short) i);
                    cell.setCellValue(title[i]);
                    cell.setCellStyle(style);
                }
                int rowcount = 0;
                if(num < (list.size() / 65534)){
                    rowcount = 65534;
                } else if(num == (list.size() / 65534)){
                    rowcount = list.size() % 65534;
                }
                //填充Excel单元格数据
                getExcelData(list, num, sheet, rowcount);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e.getStackTrace()));
        }
        //服务器临时文件地址
        FileOutputStream fout = null;
        File file = null;
        try {
            if(excelsheetnum != 0){
                if(StringUtils.isNotEmpty(excelDate)){
                    file = new File(path, username + "-" + excelDate + "-" + excelname + "(" + excelsheetnum + ")" + exceltype);
                } else {
                    file = new File(path, username + "-" + excelname + "(" + excelsheetnum + ")" + exceltype);
                }
            } else {
                if(StringUtils.isNotEmpty(excelDate)){
                    file = new File(path, username + "-" + excelDate + "-" + excelname + exceltype);
                } else {
                    file = new File(path, username + "-" + excelname + exceltype);
                }
            }
            logger.info("路径:" + file.getName());
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //删除服务器临时文件
            if(file.exists()){
                logger.info("删除旧文件:" + file.getName());
                file.delete();
            }
            file.createNewFile();
            logger.info("创建新文件:" + file.getName());
            System.out.println(file.getAbsolutePath());
            fout = new FileOutputStream(file);
            hssfWorkbook.write(fout);
            fout.flush();
        } catch (Exception e) {
            logger.info(String.valueOf(e.getStackTrace()));
        } finally {
            try {
                if(null != fout){
                    fout.close();
                }
            } catch (IOException e) {
                logger.info(String.valueOf(e.getStackTrace()));
            }
            filenamesList.add(file.getName());
        }
        logger.info("------excel导出完毕");
    }

    /**
     * 填充Excel单元格数据
     *
     * @param list
     * @param num
     * @param sheet
     * @param rowcount
     */
    private void getExcelData(List<ArrayList<String>> list, int num, Sheet sheet, int rowcount) {
        Row row;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            ArrayList<String> beanList = list.get(i);
            //序号
            String istr = (i + 1 + 65534 * num) + "";
            row.createCell((short) 0).setCellValue(istr);
            for (int j = 0; j < beanList.size(); j++) {
                row.createCell((short) (j + 1)).setCellValue(StrUtil.obj2str(beanList.get(j)));
            }
        }
    }

    /**
     * 删除目录下所有的文件;
     */
    public boolean deleteExcelPath(String beginWithString, File directory, String endsWithString) {
        String[] files = null;
        logger.info("删除文件:删除文件路径:" + directory.getName());
        if(directory != null){
            files = directory.list();
        }
        if(directory.isDirectory()){
            logger.info(directory.getName() + "是文件夹");
            for (int i = 0; i < files.length; i++) {
                if(files[i].startsWith(beginWithString) && files[i].endsWith(endsWithString)){
                    logger.info("开始删除子文件");
                    deleteExcelPath(beginWithString, new File(directory, files[i]), endsWithString);
                }
            }
            logger.info(directory.getName() + "删除完毕");
            return false;
        } else {
            logger.info(directory.getName() + "正在删除");
            return directory.delete();
        }
    }

    /**
     * 生成.zip文件;
     * 需要在返回值上添加导出文件的目录
     *
     * @param path
     *
     * @exception IOException
     */
    public String createZipPath(String path, String deleteBeginString, String deleteEndString) {
        ZipOutputStream zipOutputStream = null;
        //文件名
        String filename = deleteBeginString + "-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".zip";
        String pathname = path + File.separator + filename;
        int count = 0;
        try {
            logger.info("打压缩包文件路径:" + pathname);
            File file = new File(pathname);
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            File[] files = new File(path).listFiles();
            FileInputStream fileInputStream = null;
            byte[] buf = new byte[1024];
            int len = 0;
            if(files != null && files.length > 0){
                for (File excelFile : files) {
                    String fileName = excelFile.getName();
                    if(fileName.startsWith(deleteBeginString) && fileName.endsWith(deleteEndString)){
                        logger.info("正在压缩:" + fileName);
                        fileInputStream = new FileInputStream(excelFile);
                        // 放入压缩zip包中;
                        zipOutputStream.putNextEntry(new ZipEntry(fileName));
                        // 读取文件;
                        while ((len = fileInputStream.read(buf)) > 0) {
                            zipOutputStream.write(buf, 0, len);
                        }
                        // 关闭;
                        zipOutputStream.closeEntry();
                        if(fileInputStream != null){
                            fileInputStream.close();
                        }
                        //压缩文件数+1
                        count++;
                    }
                }
            }
            if(zipOutputStream != null){
                zipOutputStream.close();
            }
            if(count == 0){
                deleteExcelPath(deleteBeginString, file, deleteEndString);
                return "NoneExcelError";
            }
            logger.info("----导出文件打包成功!----");
        } catch (FileNotFoundException e) {
            logger.info(String.valueOf(e.getStackTrace()));
            return "FileError";
        } catch (IOException e) {
            logger.info(String.valueOf(e.getStackTrace()));
            return "IOError";
        }
        return File.separator + filename;
    }


    public boolean isFinishExcel() {
        return finishExcel;
    }

    public void setFinishExcel(boolean finishExcel) {
        this.finishExcel = finishExcel;
    }

    public List<String> getFilenamesList() {
        return filenamesList;
    }

    public void setFilenamesList(List<String> filenamesList) {
        this.filenamesList = filenamesList;
    }
}
