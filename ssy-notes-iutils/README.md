# iUtils
> 工具类，开箱即用

- __AESUtils__ ： AES加解密工具类，返回 Base64
    ```
    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密码
     * @return
     */
    AESUtils.encrypt(String data, String key);
    
    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return
     */
    AESUtils.decrypt(String data, String key);
    ```

- __CsvUtils__ ： Csv文件操作工具类（生成.CSV文件）
    ```
    // 生成 .csv 文件
    
   /**
     * 写出CSV文件，每一行数据为一个List<String>集合
     * @param data  List<String>
     * @param header    第一行标题
     * @param filePath  写出文件的绝对路径
     * @param split     CSV文件分隔符，一般为","
     */
   CsvUtils.writeCSVFromList(List<List<String>> data, List<String> header, String filePath, String split);
    
    /**
     * 写出CSV文件，每一行数据为一个Object对象
     * @param data  List<Object>
     * @param header    第一行标题
     * @param filePath  写出文件的绝对路径
     * @param split     CSV文件分隔符，一般为","
     * @throws Exception
     */
   CsvUtils.writeCSV(List<?> data, List<String> header, String filePath, String split);
    ```
    
- __TarUtils__ ： Tar文件压缩工具类
    ```
    /**
     * 压缩成TAR 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    TarUtils.toTar(String srcDir, OutputStream out, boolean KeepDirStructure)
    
    /**
     * 压缩成TAR 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    TarUtils.toTar(List<File> srcFiles, OutputStream out)
    
    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param tos              tar输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    TarUtils.compress(File sourceFile, TarOutputStream tos, String name, boolean KeepDirStructure)
    ```
    
- __ExcelExportUtil__ ： 使用SXSSFWorkbook向Excel中写入数据
    ```
    /**
     *
     * @param title
     *            表头
     * @param sheetName
     *            左下角标题
     * @param officeVersion
     *            excel版本
     * 创建工作薄 工作表
     */
    ExcelExporterUtil excelExporter = new ExcelExporterUtil(title, sheetName, ExcelExporterUtil.OfficeVersion.OFFICE_07);
     
     /**
      * For循环操作：↓ ↓ ↓
      *
      * 创建行内容(每一行的数据装在list中)
      *
      * @param dataMap
      *            每一行的数据
      * @param dataRow
      *            行 key 值
      * @param rowIndex
      *            行号(从1开始)
      */
     excelExporter.createTableRow(Map<String, String> dataMap, List<String> dataRow, int rowIndex)
     
     /**
      * 将数据写出到excel中
      *
      * 1. 直接生成文件
      *
      * @param outputStream
      */
     excelExporter.exportExcel(OutputStream outputStream)
     
     // 例如 ： ↓ ↓ ↓
     // 获取当前操作系统桌面路径
     File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
     String desktopPath = desktopDir.getAbsolutePath();
     String desktopFilePath = desktopPath.concat("/文件名").concat(".xlsx");

     try {
           excelExporter.exportExcel(new FileOutputStream(new File(desktopFilePath)));
     } catch (FileNotFoundException e) {
           if (logger.isErrorEnabled()) {
               logger.error("文件导出失败！ 异常原因：", e);
         }
         return false;
     }
     return true;
     
    /**
     * 将数据写出到excel中
     * 2. 将数据写出到 byte[] 中，返回给前端处理
     * @return byte[]
     */
    byte[] bytes = excelExporter.exportByte(excelExporter.workBook);
    return bytes;
    ```
    
- __RecursiveSearchUtils__ : 递归搜索 - 某种后缀类型文件 & 文件全路径 & 写入Excel文件
    ```
    /**
     * 初始化构造器参数
     * fileType : 文件类型（后缀）
     * sheetName : Excel文件页脚标注
     * dataRow : 每行数据对应的 key 值 （ Map<K, V> ）
     * tableStr : 标题Table首行（列名）
     */
    RecursiveSearchUtils recursiveSearchUtils = new RecursiveSearchUtils(fileType, sheetName, dataRow, tableStr);
    /**
     * 写入文件
     * directoryUtl : 需要检索的文件夹（完整路径）
     * desktopFilePath : 保存路径 & 文件命名
     */
    recursiveSearchUtils.recursiveSearch(directoryUtl, desktopFilePath);
    ```