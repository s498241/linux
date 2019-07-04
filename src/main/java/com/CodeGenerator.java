package com;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

public class CodeGenerator {


     static String outputDir = System.getProperty("user.dir")+"/src/main/java";

        public static void main(String[] args) {
            AutoGenerator mpg = new AutoGenerator();
            setDatasource(mpg);
            setGlobleConfig(mpg);
            setClassTableInfo(mpg);

            setHtmlTemplateInfoOutputFile(mpg);
            TemplateConfig tc = getTemplateJavaFile();
            mpg.setTemplate(tc);
            mpg.execute();
        }

    private static TemplateConfig getTemplateJavaFile() {
        // 关闭默认 xml 生成，调整生成 至 根目录
            /*TemplateConfig tc = new TemplateConfig();
            tc.setXml(null);
            mpg.setTemplate(tc);*/

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templatesMybatis/controller.java.vm");
        tc.setService("/templatesMybatis/service.java.vm");
        tc.setServiceImpl("/templatesMybatis/serviceImpl.java.vm");
        tc.setEntity("/templatesMybatis/entity.java.vm");
        tc.setMapper("/templatesMybatis/mapper.java.vm");
        tc.setXml("/templatesMybatis/mapper.xml.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        return tc;
    }

    private static void setHtmlTemplateInfoOutputFile(AutoGenerator mpg) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        // 自定义 xxListIndex.html 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templatesMybatis/list.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "E://test//html//" + tableInfo.getEntityName() + "ListIndex.html";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义  xxAdd.html 生成
        focList.add(new FileOutConfig("/templatesMybatis/add.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "E://test//html//" + tableInfo.getEntityName() + "Add.html";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //  自定义 xxUpdate.html生成
        focList.add(new FileOutConfig("/templatesMybatis/update.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "E://test//html//" + tableInfo.getEntityName() + "Update.html";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    private static void setClassTableInfo(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        strategy.setInclude("user_info");//需要包含的表名，允许正则表达式
        strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符
        strategy.setTablePrefix("");//表前缀

        //strategy.setSuperEntityColumns("id");//自定义基础的Entity类，公共字段
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");//自定义继承的Entity类全称，带包名
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");//自定义继承的Controller类全称，带包名
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);

        mpg.setStrategy(strategy);


    }

    private static void setGlobleConfig(AutoGenerator mpg) {
        // 代码生成器全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);//生成文件的输出目录
        gc.setAuthor("ss");//开发人员
        gc.setOpen(true);//是否打开输出目录
        gc.setServiceName("%sService");//service 命名方式
        gc.setServiceImplName("%sServiceImpl");//service impl 命名方式
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList

        // 包配置StrategyConfig
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));//父包模块名
        pc.setParent("com");//父包名。// 自定义包路径  如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");//设置控制器包名
        mpg.setPackageInfo(pc);
        mpg.setGlobalConfig(gc);
    }

    private static void setDatasource(AutoGenerator mpg) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.43.110:3306/master1?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public"); 数据库 schema name
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123123");
        mpg.setDataSource(dsc);
    }


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
}

