package com.example.pm;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis-plus代码生成器
 *  使用该类需要添加以下依赖，在此之前请移除所有与mybatis有关的其他依赖，防止冲突
 *   <dependency>
 *      <groupId>com.baomidou</groupId>
 *       <artifactId>mybatis-plus-generator</artifactId>
 *       <version>3.1.2</version>
 *  </dependency>
 *
 *  <dependency>
 *        <groupId>com.baomidou</groupId>
 *        <artifactId>mybatis-plus-boot-starter</artifactId>
 *        <version>3.1.2</version>
 *   </dependency>
 *
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 全局配置


        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setMapperName("%sDao");
//        gc.setXmlName("%sMapper");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImap");
//        gc.setControllerName("%sController");
        //生成的代码存放到某个路径下，这里是E盘，
//        gc.setOutputDir("E://");
        //生成的代码位置为当前项目
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("seya");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        //%s是实体类类名占位符，不配置这行的话，对于User会生成IUserService,配置后即可生成UserService;
        gc.setServiceName("%sService");
        // XML 二级缓存
//      gc.setEnableCache(true);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        //
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();


//        dsc.setSchemaName("public");
        dsc.setUrl("jdbc:mysql://localhost:3306/kettle?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("xiaoM1017");

//        dsc.setUrl("jdbc:mysql://localhost:3306/cloud_mould?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
//        dsc.setUsername("root");
//        dsc.setPassword("Ziyu1026!@");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //若果需要在Parent（此处即com.example.plus）下新建模块时打开下面注释，后续在控制台提示输入模块时，输入想要新建的模块名就可以
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.pm");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //以下为两种模板来生成*mapper.xml文件,任选一种即可，不同的模板对应不同的依赖
        // 如果模板引擎是 freemarker，请添加以下依赖。
        /**
         *         <dependency>
         *             <groupId>org.freemarker</groupId>
         *             <artifactId>freemarker</artifactId>
         *             <version>2.3.23</version>
         *         </dependency>
         */
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity 请添加以下依赖。
        /**
         *         <dependency>
         *             <groupId>org.apache.velocity</groupId>
         *             <artifactId>velocity-engine-core</artifactId>
         *             <version>2.0</version>
         *         </dependency>
         */
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                if(pc.getModuleName() == null){
                    return projectPath + "/src/main/resources/mapper/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }else{
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }

            }

        });

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                File file = new File(filePath);
                boolean exist = file.exists();
                if(exist){
                    if (filePath.endsWith("Mapper.xml")||FileType.ENTITY==fileType){
                        return true;
                    }else {
                        return false;
                    }
                }
                //不存在的文件都需要创建
                return  true;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        // templateConfig.setEntity("templates/entity2.java");
//        // templateConfig.setService();
//        // templateConfig.setController();
//
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //若想要生成的实体类继承某个类，则可打开下面注释。写上需要继承的类的位置即可
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        //【实体】是否为lombok模型（默认 false）
//        strategy.setEntityLombokModel(true);
        //对控制器生成 @RestController 注解
        strategy.setRestControllerStyle(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setEntitySerialVersionUID(false)//加此行不生成生成实体类序列化编号，不加默认生成
        //若想要生成的实体类继承某个Controller，则可打开下面注释。写上需要继承的Controller的位置即可
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        //此处user是表名，多个英文逗号分割
        strategy.setInclude("sys_user");
//        strategy.setExclude();//数据库表全生成
//        strategy.setInclude(scanner("user").split(","));//表名，多个英文逗号分割
        strategy.setControllerMappingHyphenStyle(true);
        //数据库表前缀，不配置这行的话，生成的类会带有T如:TUser,配置后即可将前缀去掉
//        strategy.setTablePrefix("tb_");
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

