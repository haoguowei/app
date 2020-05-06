package com.hao.app.commons.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dicts {

    public static Map<Integer, String> genderMap = new LinkedHashMap<>();
    public static Map<Integer, String> minzuMap = new LinkedHashMap<>();
    public static Map<Integer, String> xueliMap = new LinkedHashMap<>();
    public static Map<Integer, String> hukouTypeMap = new LinkedHashMap<>();

    public static Map<Integer, String> employeeStatusMap = new LinkedHashMap<>();
    public static Map<Integer, String> employeeJobTypeMap = new LinkedHashMap<>();

    public static Map<Integer, String> assetsTypeMap = new LinkedHashMap<>();
    public static Map<Integer, String> engineNumberTypeMap = new LinkedHashMap<>();

    public static Map<Integer, String> costStatusMap = new LinkedHashMap<>();
    public static Map<Integer, String> otherPayTypeMap = new LinkedHashMap<>();
    public static Map<Integer, String> yunyingPayTypeMap = new LinkedHashMap<>();

    public static Map<Integer, String> assetsNameMap = new LinkedHashMap<>();
    public static Map<Integer, String> brandMap = new LinkedHashMap<>();
    public static Map<Integer, String> carTypeMap = new LinkedHashMap<>();


    static {
        //吊装压缩车、封闭转运车、勾臂转运车、后挂压缩车、侧挂压缩车、翻斗收运车、
        //低压洒水车、高压清洗车、洗扫车、扫路车
        assetsNameMap.put(1, "吊装压缩车");
        assetsNameMap.put(2, "封闭转运车");
        assetsNameMap.put(3, "勾臂转运车");
        assetsNameMap.put(4, "后挂压缩车");
        assetsNameMap.put(5, "侧挂压缩车");
        assetsNameMap.put(6, "翻斗收运车");
        assetsNameMap.put(7, "低压洒水车");
        assetsNameMap.put(8, "高压清洗车");
        assetsNameMap.put(9, "洗扫车");
        assetsNameMap.put(10, "扫路车");
        assetsNameMap.put(100, "其他");

        brandMap.put(1, "徐工");
        brandMap.put(2, "中联");
        brandMap.put(3, "龙马");
        brandMap.put(4, "森源");
        brandMap.put(5, "五征");
        brandMap.put(6, "程力");
        brandMap.put(7, "合力");
        brandMap.put(8, "东风");
        brandMap.put(100, "其他");

        //大型、中型、小型、微型
        carTypeMap.put(1, "大型");
        carTypeMap.put(2, "中型");
        carTypeMap.put(3, "小型");
        carTypeMap.put(4, "微型");

        otherPayTypeMap.put(1, "办公用品");
        otherPayTypeMap.put(2, "办公维修");
        otherPayTypeMap.put(3, "煤水电");
        otherPayTypeMap.put(4, "差旅费");
        otherPayTypeMap.put(5, "房租-租赁费");
        otherPayTypeMap.put(6, "房租-物业费");
        otherPayTypeMap.put(7, "房租-采暖费");
        otherPayTypeMap.put(100, "其他办公费用");

        yunyingPayTypeMap.put(1, "扫保工具");
        yunyingPayTypeMap.put(2, "运营水电费");
        yunyingPayTypeMap.put(3, "劳保费用");
        yunyingPayTypeMap.put(4, "临时费用");
        yunyingPayTypeMap.put(5, "外包费用");
        yunyingPayTypeMap.put(100, "其他运营费用");

        engineNumberTypeMap.put(1, "小型车");
        engineNumberTypeMap.put(2, "大型车");
        engineNumberTypeMap.put(3, "低速车");
        engineNumberTypeMap.put(4, "其他");

        hukouTypeMap.put(1, "农村");
        hukouTypeMap.put(2, "城镇");

        xueliMap.put(1, "小学");
        xueliMap.put(2, "初中");
        xueliMap.put(3, "高中");
        xueliMap.put(4, "大学");

        genderMap.put(0, "男");
        genderMap.put(1, "女");

        employeeStatusMap.put(0, "在职");
        employeeStatusMap.put(1, "离职");

        employeeJobTypeMap.put(1, "经理");
        employeeJobTypeMap.put(2, "主管");
        employeeJobTypeMap.put(3, "文员");
        employeeJobTypeMap.put(4, "助理");
        employeeJobTypeMap.put(5, "队长");
        employeeJobTypeMap.put(6, "司机");
        employeeJobTypeMap.put(7, "装卸工");
        employeeJobTypeMap.put(8, "保洁员");

        assetsTypeMap.put(1, "电动人力");
        assetsTypeMap.put(2, "箱");
        assetsTypeMap.put(3, "桶");
        assetsTypeMap.put(4, "辅助类");

        costStatusMap.put(0, "正常");
        costStatusMap.put(1, "上报");

        minzuMap.put(1, "汉族");
        minzuMap.put(2, "满族");
        minzuMap.put(3, "蒙古族");
        minzuMap.put(4, "回族");
        minzuMap.put(5, "藏族");
        minzuMap.put(6, "维吾尔族");
        minzuMap.put(7, "苗族");
        minzuMap.put(8, "彝族");
        minzuMap.put(9, "壮族");
        minzuMap.put(10, "布依族");

        minzuMap.put(11, "朝鲜族");
        minzuMap.put(12, "侗族");
        minzuMap.put(13, "白族");
        minzuMap.put(14, "土家族");
        minzuMap.put(15, "汉族");
        minzuMap.put(16, "哈尼族");
        minzuMap.put(17, "哈萨克族");
        minzuMap.put(18, "傣族");
        minzuMap.put(19, "黎族");
        minzuMap.put(20, "傈僳族");

        minzuMap.put(21, "佤族");
        minzuMap.put(22, "畲族");
        minzuMap.put(23, "高山族");
        minzuMap.put(24, "拉祜族");
        minzuMap.put(25, "水族");
        minzuMap.put(26, "东乡族");
        minzuMap.put(27, "纳西族");
        minzuMap.put(28, "景颇族");
        minzuMap.put(29, "柯尔克孜族");
        minzuMap.put(30, "土族");

        minzuMap.put(31, "达斡尔族");
        minzuMap.put(32, "仫佬族");
        minzuMap.put(33, "羌族");
        minzuMap.put(34, "布朗族");
        minzuMap.put(35, "撒拉族");
        minzuMap.put(36, "毛南族");
        minzuMap.put(37, "仡佬族");
        minzuMap.put(38, "锡伯族");
        minzuMap.put(39, "阿昌族");
        minzuMap.put(40, "普米族");

        minzuMap.put(41, "瑶族");
        minzuMap.put(42, "塔吉克族");
        minzuMap.put(43, "怒族");
        minzuMap.put(44, "乌孜别克族");
        minzuMap.put(45, "俄罗斯族");
        minzuMap.put(46, "鄂温克族");
        minzuMap.put(47, "保安族");
        minzuMap.put(48, "裕固族");
        minzuMap.put(49, "京族");
        minzuMap.put(50, "塔塔尔族");

        minzuMap.put(51, "独龙族");
        minzuMap.put(52, "鄂伦春族");
        minzuMap.put(53, "赫哲族");
        minzuMap.put(54, "门巴族");
        minzuMap.put(55, "珞巴族");
        minzuMap.put(56, "基诺族");


    }
}
