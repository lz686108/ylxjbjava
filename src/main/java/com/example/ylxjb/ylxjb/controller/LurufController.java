package com.example.ylxjb.ylxjb.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ylxjb.technology.entity.Unconventionality;
import com.example.ylxjb.technology.mapper.UnconventionalityMapper;
import com.example.ylxjb.ylxjb.entity.*;
import com.example.ylxjb.ylxjb.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lizhen
 * @since 2021-12-01
 */
@RestController
@CrossOrigin
@RequestMapping("/ylxjb/luruf")
@Component
public class LurufController {
    @Autowired
    public LurufMapper lurufMapper;
    @Autowired
    public ButvaluMapper butvaluMapper;
    @Autowired
    public LurufcopMapper lurufcopMapper;
    @Autowired
    public LurusMapper lurusMapper;
    @Autowired
    public ParseczMapper parseczMapper;
    @Autowired
    public UnconventionalityMapper unconventionalityMapper;
    @Autowired
    public BizhbMapper bizhbMapper;
    @Autowired
    public UnconerpMapper unconerpMapper;
    @Autowired
    public BzbiaoMapper bzbiaoMapper;
    @Autowired
    public UpcattalbeMapper upcattalbeMapper;
    @Autowired
    public JcwyMapper jcwyMapper;
    @Autowired
    public Ucp1Mapper ucp1Mapper;
    @Autowired
    public JcwyCopy1Mapper jcwyCopy1Mapper;
    @Autowired
    public JtbizhbCopy1Mapper jtbizhbCopy1Mapper;
    @Autowired
    public JcwyCopy1Copy1Mapper jcwyCopy1Copy1Mapper;
    @Autowired
    public JtgrsMapper jtgrsMapper;
    @Autowired
    public TamplanteMapper tamplanteMapper;
    @Autowired
    public WymdcwhMapper wymdcwhMapper;
    @Autowired
    public WymjcMapper wymjcMapper;
    @Autowired
    public YmjcMapper ymjcMapper;
    @Autowired
    public WymfwMapper wymfwMapper;
    @Autowired
    public YmfwMapper ymfwMapper;
    @Autowired
    public WymdcwhCopy1Mapper wymdcwhCopy1Mapper;
    @Autowired
    public YmdcwhCopy2Mapper ymdcwhCopy2Mapper;
    @Autowired
    public GcilmaMapper gcilmaMapper;
    @Autowired
    public JfdcwhMapper jfdcwhMapper;
    @Autowired
    public JfdcwhCopy1Mapper jfdcwhCopy1Mapper;
    @Autowired
    public DbcsMapper dbcsMapper;
    @Autowired
    public DbcsCopy1Mapper dbcsCopy1Mapper;
    @Autowired
    public NhxnMapper nhxnMapper;
    @Autowired
    public BgqhsjMapper bgqhsjMapper;


    final String gysmclista[] = {"北票合兴实业有限公司", "鞍钢集团矿业弓长岭有限公司", "鞍钢集团矿业有限公司", "罕王实业集团（抚顺）矿业有限公司", "本溪盛源矿业有限责任公司",
            "鞍山金和矿业有限公司", "本溪同成铁选有限公司", "灯塔市众鑫选矿厂", "辽阳顺锋钢铁有限公司", "铁岭县红印铁矿有限公司", "本溪鑫盛矿业有限责任公司",
            "本溪同成铁选有限公司", "清原满族自治县鑫福经贸有限公司", "本溪永亿铁选有限公司", "桓仁矿业有限公司", "沈阳东洋炼钢公用设施有限公司", "沈阳浑南矿业有限责任公司",
            "本溪东方三家子矿业有限公司", "抚顺莱河矿业有限公司", "本溪智华矿产品有限公司", "抚顺县鑫隆铁矿石粉碎厂", "抚顺诚泰矿业贸易有限公司",
            "本溪市惠兴矿业有限责任公司", "本溪鹏达矿业有限公司", "本溪思山岭云新矿业有限公司", "本溪龙新矿业有限公司", "本溪鹏达矿业有限公司",
            "辽阳市文圣区宏光炉料加工厂", "灯塔市银盛建衡矿业有限公司", "西钢集团灯塔矿业有限公司", "后英集团鞍山活龙矿业有限公司", "本溪市永安铁选厂",
            "辽阳县天利矿业有限公司"
    };
    final String gysmclistb[] = {"FMG超特粉", "塞拉利昂粉", "PB粉", "FMG混合粉", "印度粉", "罗伊山粉", "阿提斯粉", "金布巴粉", "麦克粉",
            "纽曼粉", "巴西卡粉", "WPF粉"};

    final String iglist[] = {
            "-2.78", "-2.4", "-2.4", "-2.7", "-2.34", "-2.34", "-2.66", "-2.8", "-2.3", "-2.73", "-2.8", "-2.66", "-2.2", "-2.54",
            "-2.4", "-2.2", "-2.54", "-2.4", "-2.2", "-2.66", "-2.73", "-2.2", "-2.63", "-2.4", "-2.4", "-2.2", "-2.8", "-2.8",
            "-2.45", "-2.88", "-2.84", "-2.45", "-2.54"
    };

    final String gyiglist[] = {"9", "7.43", "4.5", "8.5", "4.5", "6", "5.05", "5.05", "6", "4.58", "4.58"};

    /**
     * @param materialname
     * @param distributorname
     * @param sterilise
     * @param contractprice
     * @param monthamount
     * @return
     */
    /*添加数据至前端表格*/
    @RequestMapping("insertallofluruf")
    public List<Luruf> insertallofluruf(String materialname, String distributorname, String sterilise, String contractprice, String monthamount) {
        Luruf luruf = new Luruf();
        luruf.setMaterialName(materialname);
        luruf.setDistributorName(distributorname);
        luruf.setSterilise(sterilise);
        luruf.setContractPrice(contractprice);
        luruf.setMonthAmount(monthamount);
        lurufMapper.insert(luruf);
        List<Luruf> lurufs = lurufMapper.selectList(null);
        return lurufs;
    }

    /**
     * @param attributename
     * @param propertyvalue
     * @return
     */
    /*属性按钮添加属性值*/
    @RequestMapping("insertattribute")
    public int insertattribute(String attributename, String propertyvalue) {
        Butvalu butvalu = new Butvalu();
        butvalu.setAttributeName(attributename);
        butvalu.setPropertyValue(propertyvalue);
        int insert = butvaluMapper.insert(butvalu);
        return insert;
    }

    /**
     * @return
     */
    /*根据属性名查找属性值*/
    @RequestMapping("selectattributevalue")
    public Map<String, List<String>> selectattributevalue() {
        List<Butvalu> butvalus = butvaluMapper.selectList(null);
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        //供应商名称
        ArrayList<String> strings = new ArrayList<>();
        //物料名称
        ArrayList<String> strings1 = new ArrayList<>();
        //料种
        ArrayList<String> strings2 = new ArrayList<>();
        //燃料类型
        ArrayList<String> strings3 = new ArrayList<>();
        //燃料名称
        ArrayList<String> strings4 = new ArrayList<>();
        //无烟煤名称
        ArrayList<String> strings5 = new ArrayList<>();
        //烟煤名称
        ArrayList<String> strings6 = new ArrayList<>();
        //焦粉名称
        ArrayList<String> strings7 = new ArrayList<>();
        for (int i = 0; i < butvalus.size(); i++) {
            String attributeName = butvalus.get(i).getAttributeName();
            switch (attributeName) {
                case "供应商名称":
                    strings.add(butvalus.get(i).getPropertyValue());
                    break;
                case "物料名称":
                    strings1.add(butvalus.get(i).getPropertyValue());
                    break;
                case "料种":
                    strings2.add(butvalus.get(i).getPropertyValue());
                    break;
                case "燃料类型":
                    strings3.add(butvalus.get(i).getPropertyValue());
                    break;
                case "燃料名称":
                    strings4.add(butvalus.get(i).getPropertyValue());
                    break;
                case "无烟煤名称":
                    strings5.add(butvalus.get(i).getPropertyValue());
                    break;
                case "烟煤名称":
                    strings6.add(butvalus.get(i).getPropertyValue());
                    break;
                case "焦粉名称":
                    strings7.add(butvalus.get(i).getPropertyValue());
                    break;
                default:
            }
            stringListHashMap.put("gysmc", strings);
            stringListHashMap.put("wlmc", strings1);
            stringListHashMap.put("lzmc", strings2);
            stringListHashMap.put("rllx", strings3);
            stringListHashMap.put("rlmc", strings4);
            stringListHashMap.put("wymmc", strings5);
            stringListHashMap.put("ymmc", strings6);
            stringListHashMap.put("jfmc", strings7);
        }
        return stringListHashMap;
    }

    /**
     * @return
     */
    /*初始化查询变革数据*/
    @RequestMapping("selectofluruf")
    public List<Luruf> selectofluruf() {
        List<Luruf> lurufs = lurufMapper.selectList(null);
        return lurufs;
    }

    /**
     * @param id
     * @return
     */
    /*全部字段加上版本号添加数据库*/
    @RequestMapping("insertversons")
    public int insertversons(int id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        QueryWrapper<Luruf> lw = new QueryWrapper<>();
        lw.eq("id", id);
        List<Luruf> lurufs = lurufMapper.selectList(lw);
        if (!ObjectUtils.isEmpty(lurufs)) {
            Luruf luruf = lurufs.get(0);
            Lurufcop lurufcop = new Lurufcop();
            lurufcop.setContractPrice(luruf.getContractPrice());
            lurufcop.setDistributorName(luruf.getDistributorName());
            lurufcop.setMaterialName(luruf.getMaterialName());
            lurufcop.setSterilise(luruf.getSterilise());
            lurufcop.setMonthAmount(luruf.getMonthAmount());
            lurufcop.setVersions(format);
            lurufcopMapper.insert(lurufcop);
        }
        return 1;
    }

    /**
     * @param materialname
     * @param distributorname
     * @param sterilise
     * @param contractprice
     * @param monthamount
     * @param id
     * @return
     */
    /*修改信息*/
    @RequestMapping("updatecontext")
    public int updatecontext(String materialname, String distributorname,
                             String sterilise, String contractprice, String monthamount, String id) {
        Luruf luruf = new Luruf();
        luruf.setMaterialName(materialname);
        luruf.setDistributorName(distributorname);
        luruf.setSterilise(sterilise);
        luruf.setContractPrice(contractprice);
        luruf.setMonthAmount(monthamount);
        QueryWrapper<Luruf> lw = new QueryWrapper<>();
        lw.eq("id", id);
        int update = lurufMapper.update(luruf, lw);
        return update;
    }

    /**
     * @param versiondat
     * @return
     */
    /*分组查询luruf表*/
    @RequestMapping("selectsidall")
    public List<Lurus> selectsidall(String versiondat) {
        QueryWrapper<Lurufcop> lw = new QueryWrapper<>();
        lw.groupBy("material_name", "distributor_name", "sterilise", "versions");
        List<Lurufcop> lurufs = lurufcopMapper.selectList(lw);
        List<Lurus> seleinsert = seleinsert(lurufs, versiondat);
        return seleinsert;
    }

    /**
     * @param lurufs
     * @param versiondat
     * @return
     */
    /*查询数据添加至lurus表中*/
    public List<Lurus> seleinsert(List<Lurufcop> lurufs, String versiondat) {
        for (int i = 0; i < lurufs.size(); i++) {
            QueryWrapper<Lurus> lurusQueryWrapper = new QueryWrapper<>();
            lurusQueryWrapper.eq("distributor_name", lurufs.get(i).getDistributorName());
            lurusQueryWrapper.eq("material_name", lurufs.get(i).getMaterialName());
            lurusQueryWrapper.eq("sterilise", lurufs.get(i).getSterilise());
            lurusQueryWrapper.eq("versions", lurufs.get(i).getVersions());
            List<Lurus> luruses1 = lurusMapper.selectList(lurusQueryWrapper);
            if (!ObjectUtils.isEmpty(luruses1)) {
                continue;
            }
            QueryWrapper<Lurus> lw = new QueryWrapper<>();
            lw.eq("distributor_name", lurufs.get(i).getDistributorName());
            lw.eq("material_name", lurufs.get(i).getMaterialName());
            lw.eq("sterilise", lurufs.get(i).getSterilise());
            List<Lurus> luruses = lurusMapper.selectList(lw);
            ArrayList<Lurus> le = new ArrayList<>();
            for (int i1 = 0; i1 < luruses.size(); i1++) {
                if (ObjectUtils.isEmpty(luruses.get(i1).getChemotropism())) {
                    continue;
                }
                le.add(luruses.get(i1));
                break;
            }
            if (!ObjectUtils.isEmpty(le)) {
                Lurus lurus1 = le.get(0);
                Lurus lurus = new Lurus();
                lurus.setMaterialName(lurufs.get(i).getMaterialName());
                lurus.setDistributorName(lurufs.get(i).getDistributorName());
                lurus.setSterilise(lurufs.get(i).getSterilise());
                lurus.setVersions(lurufs.get(i).getVersions());
                lurus.setChemotropism(lurus1.getChemotropism());
                lurus.setCrystalStock(lurus1.getCrystalStock());
                lurus.setLiquidFluidity(lurus1.getLiquidFluidity());
                lurus.setPhaseStrength(lurus1.getPhaseStrength());
                lurus.setPricingType(lurus1.getPricingType());
                lurus.setPurchasingQuality(lurus1.getPurchasingQuality());
                lurusMapper.insert(lurus);
            } else {
                Lurus lurus = new Lurus();
                lurus.setMaterialName(lurufs.get(i).getMaterialName());
                lurus.setDistributorName(lurufs.get(i).getDistributorName());
                lurus.setSterilise(lurufs.get(i).getSterilise());
                lurus.setVersions(lurufs.get(i).getVersions());
                lurusMapper.insert(lurus);
            }
        }
        QueryWrapper<Lurus> lurusQueryWrapper = new QueryWrapper<>();
        lurusQueryWrapper.eq("versions", versiondat);
        List<Lurus> lurufcops = lurusMapper.selectList(lurusQueryWrapper);
        return lurufcops;
    }

    /**
     * @param luruslist
     * @param ddyh
     * @return
     */
    /*批量添加*/
    @RequestMapping("insertslurus")
    public List<Lurus> insertslurus(String luruslist, String ddyh) {
        JSONArray jsonArray = JSONArray.parseArray(luruslist);
        for (int i = 0; i < jsonArray.size(); i++) {
            Lurus lurus = new Lurus();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            lurus.setDistributorName(((String) jsonObject.get("distributorName")));
            lurus.setMaterialName(((String) jsonObject.get("materialName")));
            lurus.setSterilise(((String) jsonObject.get("sterilise")));
            lurus.setChemotropism(((String) jsonObject.get("chemotropism")));
            lurus.setCrystalStock(((String) jsonObject.get("crystalStock")));
            lurus.setLiquidFluidity(((String) jsonObject.get("liquidFluidity")));
            lurus.setPhaseStrength(((String) jsonObject.get("phaseStrength")));
            lurus.setPricingType(((String) jsonObject.get("pricingType")));
            lurus.setPurchasingQuality(((String) jsonObject.get("purchasingQuality")));
            QueryWrapper<Lurus> lw = new QueryWrapper<>();
            lw.eq("id", jsonObject.get("id"));
            lurusMapper.update(lurus, lw);
        }
        QueryWrapper<Lurus> lurusQueryWrapper = new QueryWrapper<>();
        lurusQueryWrapper.eq("versions", ddyh);
        List<Lurus> luruses = lurusMapper.selectList(lurusQueryWrapper);
        return luruses;
    }

    /**
     * @return
     */
    /*初始化查询版本信息*/
    @RequestMapping("selectxubut")
    public List<String> selectxubut() {
        QueryWrapper<Lurufcop> lw = new QueryWrapper<>();
        lw.groupBy("versions");
        List<Lurufcop> lurufcops = lurufcopMapper.selectList(lw);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < lurufcops.size(); i++) {
            strings.add(lurufcops.get(i).getVersions());
        }
        return strings;
    }

    /**
     * @param tabli
     * @param htjgzj
     * @return
     */
    /*three页合同价格同加*/
    @RequestMapping("inserthtjgtjia")
    public List<Luruf> inserthtjgtjia(String tabli, String htjgzj) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgzj);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Luruf> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Luruf luruf = new Luruf();
            luruf.setContractPrice(String.valueOf(b[i] + zjjg));
            lurufMapper.update(luruf, lw);
        }
        List<Luruf> lurufs = lurufMapper.selectList(null);
        return lurufs;
    }

    /**
     * @param tabli
     * @param htjgjs
     * @return
     */
    /*three页合同价格同减*/
    @RequestMapping("inserthtjgjian")
    public List<Luruf> inserthtjgjian(String tabli, String htjgjs) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjs = Integer.valueOf(htjgjs);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Luruf> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Luruf luruf = new Luruf();
            luruf.setContractPrice(String.valueOf(b[i] - zjjs));
            lurufMapper.update(luruf, lw);
        }
        List<Luruf> lurufs = lurufMapper.selectList(null);
        return lurufs;
    }

    /**
     * @param banbenh 版本号
     * @param pxgz    排序规则
     * @return
     */
    @RequestMapping("selectTableBybbh")
    public Object selectTableBybbhsy(String banbenh, int pxgz) {
        QueryWrapper<Ucp1> lw = new QueryWrapper<>();


        switch (pxgz) {
            case 0:
                lw.eq("bbh", banbenh);
                break;
            case 1:
                lw.eq("bbh", banbenh);
                lw.eq("sterilise", "精粉");
                break;
            case 2:
                lw.eq("bbh", banbenh);
                lw.eq("sterilise", "外粉");
                break;
            default:
        }
        List<Ucp1> upcattalbes = ucp1Mapper.selectList(lw);
        if (ObjectUtils.isEmpty(upcattalbes)) {
            return selectTableBybbh(banbenh, pxgz);
        }
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < upcattalbes.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("gysmc", upcattalbes.get(i).getGysmc());
            hashMap.put("htjg", upcattalbes.get(i).getHtjg());
            hashMap.put("yxpw", upcattalbes.get(i).getYxpw());
            hashMap.put("yxpwduj", upcattalbes.get(i).getYxpwduj());
            hashMap.put("jhdxl", upcattalbes.get(i).getJhdxl());
            hashMap.put("sjdxl", upcattalbes.get(i).getSjdxl());
            hashMap.put("tfe", upcattalbes.get(i).getTfe());
            hashMap.put("CaO", upcattalbes.get(i).getCao());
            hashMap.put("sio2", upcattalbes.get(i).getSio2());
            hashMap.put("MgO", upcattalbes.get(i).getMgo());
            hashMap.put("Al2O3", upcattalbes.get(i).getAl2o3());
            hashMap.put("tio2", upcattalbes.get(i).getTio2());
            hashMap.put("P", upcattalbes.get(i).getP());
            hashMap.put("K2O", upcattalbes.get(i).getK2o());
            hashMap.put("Na2O", upcattalbes.get(i).getNa2o());
            hashMap.put("ZnO", upcattalbes.get(i).getZno());
            hashMap.put("MnO", upcattalbes.get(i).getMno());
            hashMap.put("shuifen", upcattalbes.get(i).getShuifen());
            hashMap.put("Ig", upcattalbes.get(i).getIg());
            hashMap.put("liu", upcattalbes.get(i).getLiu());
            hashMap.put("thxwd", upcattalbes.get(i).getThxwd());
            hashMap.put("ljqd", upcattalbes.get(i).getLjqd());
            hashMap.put("yxldx", upcattalbes.get(i).getYxldx());
            hashMap.put("zjxqd", upcattalbes.get(i).getZjxqd());
            maps.add(hashMap);
        }
        return maps;
    }

    public Object selectTableBybbh(String banbenh, int pxgz) {
        QueryWrapper<Lurus> lw = new QueryWrapper<>();
        switch (pxgz) {
            case 0:
                lw.eq("versions", banbenh);
                break;
            case 1:
                lw.eq("versions", banbenh);
                lw.eq("sterilise", "精粉");
                break;
            case 2:
                lw.eq("versions", banbenh);
                lw.eq("sterilise", "外粉");
                break;
            default:
        }
        List<Lurus> lurusList = lurusMapper.selectList(lw);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < lurusList.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            /*供应商名称*/
            List<String> strings1 = Arrays.asList(gysmclistb);
            boolean contains1 = strings1.contains(lurusList.get(i).getMaterialName());
            /*判断是否是外粉,如果是外粉供应商名称写物料名称*/
            if (contains1) {
                hashMap.put("gysmc", lurusList.get(i).getMaterialName());
            } else {
                hashMap.put("gysmc", lurusList.get(i).getDistributorName());
            }
            /*去lurufcop表查询对应的合同价格*/
            QueryWrapper<Lurufcop> lqw = new QueryWrapper<>();
            lqw.eq("versions", lurusList.get(i).getVersions());
            lqw.eq("material_name", lurusList.get(i).getMaterialName());
            lqw.eq("distributor_name", lurusList.get(i).getDistributorName());
            List<Lurufcop> lurufcops = lurufcopMapper.selectList(lqw);
            Lurufcop lurufcop = new Lurufcop();
            if (!ObjectUtils.isEmpty(lurufcops)) {
                lurufcop = lurufcops.get(0);
            }
            hashMap.put("htjg", lurufcop.getContractPrice());
            /*计划兑现率
            当期天数/月天数（如5月10日测算，计划兑现率为10/31）*/
            String dates = banbenh.substring(8, 10);
            String mouths = banbenh.substring(5, 7);
            String years = banbenh.substring(0, 4);
            if (dates.indexOf(0) == 0) {
                dates = banbenh.substring(9, 10);
            }
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(years));
            cal.set(Calendar.MONTH, Integer.parseInt(mouths));
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            /*set(int field, int value) - 是用来设置"年/月/日/小时/分钟/秒/微秒"等值*/
            /*add(int field, int amount)  add 可以对 Calendar 的字段进行计算。如果需要减去值，那么使用负数值就可以了*/
            Date lastDate = cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(lastDate);
            String semounth = format.substring(8, 10);
            if (semounth.indexOf(0) == 0) {
                semounth = format.substring(9, 10);
            }
            DecimalFormat df = new DecimalFormat("0.00");
            Double aDouble = Double.valueOf(dates);
            Double aDouble1 = Double.valueOf(semounth);
            hashMap.put("jhdxl", df.format((aDouble / aDouble1) * 100) + "" + '%');
            /*重新获取物料和样品名称*/
            String materialName = lurusList.get(i).getMaterialName();
            String distributorName = lurusList.get(i).getDistributorName();
            /*判断数组种是否包含物料名称*/
            List<String> strings2 = Arrays.asList(gysmclistb);
            boolean contains2 = strings2.contains(materialName);
            /*新建的非常规表里取元素品位*/
            /*线给hashmap存入非常规的excel元素名称,如果新的非常规表中有值则会替换hashmap中的值*/
            QueryWrapper<Bizhb> bizhbQueryWrapper = new QueryWrapper<>();
            String tvb = distributorName;
            if (contains2) {
                tvb = materialName;
            }
            bizhbQueryWrapper.eq("gysmc", tvb);
            List<Bizhb> bizhbs1 = bizhbMapper.selectList(bizhbQueryWrapper);
            if (!ObjectUtils.isEmpty(bizhbs1)) {
                Bizhb bizhb = bizhbs1.get(0);
                /*常规*/
                hashMap.put("tfe", bizhb.getTfe());
                hashMap.put("sio2", bizhb.getSi02());
                hashMap.put("tio2", bizhb.getTio2());
                hashMap.put("shuifen", bizhb.getShuifen());
                hashMap.put("liu", bizhb.getLiu());
                /*非常规*/
                hashMap.put("CaO", bizhb.getCao());
                hashMap.put("MgO", bizhb.getMgo());
                hashMap.put("Al2O3", bizhb.getAl2o3());
                hashMap.put("P", bizhb.getP());
                hashMap.put("K2O", bizhb.getK2o());
                hashMap.put("Na2O", bizhb.getNa2o());
                hashMap.put("ZnO", bizhb.getZno());
                hashMap.put("MnO", bizhb.getMno());
                hashMap.put("Ig", bizhb.getIg());
            }
            /*常规数据的替换*/
            /*国粉根据公司和样品名称去常规表表取数据*/
            /*外粉则根据原料名称单独取常规表取数据,最新七天求平均数*/
            QueryWrapper<Parsecz> parseczqw = new QueryWrapper<>();
            parseczqw.eq("ylmc", materialName);
            if (!contains2) {
                parseczqw.eq("gysmc", distributorName);
            }
            parseczqw.last("limit 7");
            parseczqw.orderByDesc("jjrq");
            List<Parsecz> parseczs = parseczMapper.selectList(parseczqw);
            if (ObjectUtils.isEmpty(parseczs) && ObjectUtils.isEmpty(bizhbs1)) {
                /*同化性温度*/
                hashMap.put("thxwd", lurusList.get(i).getChemotropism());
                /*连晶强度*/
                hashMap.put("ljqd", lurusList.get(i).getCrystalStock());
                /*液相流动性*/
                hashMap.put("yxldx", lurusList.get(i).getLiquidFluidity());
                /*粘结相强度*/
                hashMap.put("zjxqd", lurusList.get(i).getPhaseStrength());
                maps.add(hashMap);
                continue;
            }
            /*取出来的集合取常规元素如果为空给默认值*/
            ArrayList<String> tfe = new ArrayList<>();
            ArrayList<String> sio2 = new ArrayList<>();
            ArrayList<String> tio2 = new ArrayList<>();
            ArrayList<String> s = new ArrayList<>();
            ArrayList<String> h2o = new ArrayList<>();
            for (int i1 = 0; i1 < parseczs.size(); i1++) {
                if (!ObjectUtils.isEmpty(parseczs.get(i1).getTfe())) {
                    tfe.add(parseczs.get(i1).getTfe());
                }
                if (!ObjectUtils.isEmpty(parseczs.get(i1).getSi02())) {
                    sio2.add(parseczs.get(i1).getSi02());
                }
                if (!ObjectUtils.isEmpty(parseczs.get(i1).getTio2())) {
                    tio2.add(parseczs.get(i1).getTio2());
                }
                if (!ObjectUtils.isEmpty(parseczs.get(i1).getLiu())) {
                    s.add(parseczs.get(i1).getLiu());
                }
                if (!ObjectUtils.isEmpty(parseczs.get(i1).getShuifen())) {
                    h2o.add(parseczs.get(i1).getShuifen());
                }
            }
            Double tfed = 0.0;
            Double sio2d = 0.0;
            Double tio2d = 0.0;
            Double sd = 0.0;
            Double h2od = 0.0;
            Double hjzl = 0.0;
            /*如果有为空的情况 去数据库查询元素的标注值*/
            List<Bizhb> bizhbs = bizhbMapper.selectList(null);
            for (int i1 = 0; i1 < tfe.size(); i1++) {
                tfed = tfed + Double.valueOf(tfe.get(i1));
            }
            if (ObjectUtils.isEmpty(tfe)) {
                for (int i1 = 0; i1 < bizhbs.size(); i1++) {
                    if (distributorName.equals(bizhbs.get(i1).getGysmc())) {
                        hashMap.put("tfe", bizhbs.get(i1).getTfe());
                        break;
                    }
                }
            } else {
                hashMap.put("tfe", df.format(tfed / tfe.size()));
            }
            for (int i1 = 0; i1 < sio2.size(); i1++) {
                sio2d = sio2d + Double.valueOf(sio2.get(i1));
            }
            if (ObjectUtils.isEmpty(sio2)) {
                for (int i1 = 0; i1 < bizhbs.size(); i1++) {
                    if (distributorName.equals(bizhbs.get(i1).getGysmc())) {
                        hashMap.put("sio2", bizhbs.get(i1).getSi02());
                        break;
                    }
                }
            } else {
                hashMap.put("sio2", df.format(sio2d / sio2.size()));
            }
            for (int i1 = 0; i1 < tio2.size(); i1++) {
                tio2d = tio2d + Double.valueOf(tio2.get(i1));
            }
            if (ObjectUtils.isEmpty(tio2)) {
                for (int i1 = 0; i1 < bizhbs.size(); i1++) {
                    if (distributorName.equals(bizhbs.get(i1).getGysmc())) {
                        hashMap.put("tio2", bizhbs.get(i1).getTio2());
                        break;
                    }
                }
            } else {
                hashMap.put("tio2", df.format(tio2d / tio2.size()));
            }
            for (int i1 = 0; i1 < s.size(); i1++) {
                sd = sd + Double.valueOf(s.get(i1));
            }
            if (ObjectUtils.isEmpty(s)) {
                for (int i1 = 0; i1 < bizhbs.size(); i1++) {
                    if (distributorName.equals(bizhbs.get(i1).getGysmc())) {
                        hashMap.put("liu", bizhbs.get(i1).getLiu());
                        break;
                    }
                }
            } else {
                hashMap.put("liu", df.format(sd / s.size()));
            }
            for (int i1 = 0; i1 < h2o.size(); i1++) {
                h2od = h2od + Double.valueOf(h2o.get(i1));
            }
            if (ObjectUtils.isEmpty(h2o)) {
                for (int i1 = 0; i1 < bizhbs.size(); i1++) {
                    if (distributorName.equals(bizhbs.get(i1).getGysmc())) {
                        hashMap.put("shuifen", bizhbs.get(i1).getShuifen());
                        break;
                    }
                }
            } else {
                hashMap.put("shuifen", df.format(h2od / h2o.size()));
            }
            /*混基重量当月从当月第一天开始垒加,最后除月计划量*/
            Calendar instance = Calendar.getInstance();
            instance.set(Calendar.YEAR, Integer.parseInt(banbenh.substring(0, 4)));
            instance.set(Calendar.MONTH, Integer.parseInt(banbenh.substring(5, 7)));
            instance.set(Calendar.DAY_OF_MONTH, 1);
            instance.add(Calendar.MONTH, -1);
            String format1 = sdf.format(instance.getTime());
            QueryWrapper<Parsecz> parseczqw1 = new QueryWrapper<>();
            parseczqw1.eq("ylmc", materialName);
            if (!contains2) {
                parseczqw1.eq("gysmc", distributorName);
            }
            parseczqw1.between("rkrq", format1, banbenh);
            List<Parsecz> parseczs1 = parseczMapper.selectList(parseczqw1);
            Double hjzls = 0.0;
            for (int i1 = 0; i1 < parseczs1.size(); i1++) {
                String hjzl1 = parseczs1.get(i1).getHjzl();
                if (ObjectUtils.isEmpty(hjzl1)) {
                    hjzls = hjzls + 0.0;
                } else {
                    hjzls = hjzls + Double.valueOf(parseczs1.get(i1).getHjzl());
                }
            }
            /*实际兑现率 实际到货量（爬取）/月计划量*/
            String monthAmount = lurufcop.getMonthAmount();
            if (!ObjectUtils.isEmpty(monthAmount)) {
                hashMap.put("sjdxl", df.format((hjzls / Double.valueOf(monthAmount)) * 100) + "" + "%");
            }
            /*新表的非常规数据转换*/
            QueryWrapper<Unconerp> uwq = new QueryWrapper<>();
            uwq.eq("ypmc", materialName);
            if (!contains2) {
                uwq.eq("gysmc", distributorName);
            }
            List<Unconerp> unconerps = unconerpMapper.selectList(uwq);
            if (ObjectUtils.isEmpty(unconerps) && ObjectUtils.isEmpty(bizhbs1)) {
                /*同化性温度*/
                hashMap.put("thxwd", lurusList.get(i).getChemotropism());
                /*连晶强度*/
                hashMap.put("ljqd", lurusList.get(i).getCrystalStock());
                /*液相流动性*/
                hashMap.put("yxldx", lurusList.get(i).getLiquidFluidity());
                /*粘结相强度*/
                hashMap.put("zjxqd", lurusList.get(i).getPhaseStrength());
                maps.add(hashMap);
                continue;
            }
            if (!ObjectUtils.isEmpty(unconerps)) {
                for (int i1 = 0; i1 < unconerps.size(); i1++) {
                    hashMap.put(unconerps.get(i1).getItem(), unconerps.get(i1).getPrice());
                }
            }
            /*同化性温度*/
            hashMap.put("thxwd", lurusList.get(i).getChemotropism());
            /*连晶强度*/
            hashMap.put("ljqd", lurusList.get(i).getCrystalStock());
            /*液相流动性*/
            hashMap.put("yxldx", lurusList.get(i).getLiquidFluidity());
            /*粘结相强度*/
            hashMap.put("zjxqd", lurusList.get(i).getPhaseStrength());
            /*有效品味*/
            Double tfe1 = Double.valueOf((String) hashMap.get("tfe"));
            Double sio21 = Double.valueOf((String) hashMap.get("sio2"));
            Double al2O31 = Double.valueOf((String) hashMap.get("Al2O3"));
            Double ig1 = Double.valueOf((String) hashMap.get("Ig"));
            Double caO1 = Double.valueOf((String) hashMap.get("CaO"));
            Double mgO1 = Double.valueOf((String) hashMap.get("MgO"));
            Double mn1 = Double.valueOf((String) hashMap.get("MnO"));
            Double p1 = Double.valueOf((String) hashMap.get("P"));
            Double tio21 = Double.valueOf((String) hashMap.get("tio2"));
            Double k2O1 = Double.valueOf((String) hashMap.get("K2O"));
            Double na2O1 = Double.valueOf((String) hashMap.get("Na2O"));
            Double znO1 = Double.valueOf((String) hashMap.get("ZnO"));
            Double s1 = Double.valueOf((String) hashMap.get("liu"));
            Double yxpw = tfe1 / (1 - ig1 / 100) / ((100 + 2 * 1.035 * (sio21 + al2O31) / (1 - ig1 / 100)) - 2 * (caO1 + mgO1 + mn1) /
                    (1 - ig1 / 100) + (3 * s1 + 2 * p1) / (1 - ig1 / 100) + 2 * tio21 / (1 - ig1 / 100) + 3 * (k2O1 + na2O1 + znO1) / (1 - ig1 / 100)) * 100;
            hashMap.put("yxpw", df.format(yxpw));
            /*有效吨度价*/
//            hashMap.put("yxddj", lurufcop.getContractPrice());
            /*品味加价后价格*/
            Double tfe2 = Double.valueOf(String.valueOf(hashMap.get("tfe")));
            Double purqua = Double.valueOf(0);
            if (!ObjectUtils.isEmpty(lurusList.get(i).getPurchasingQuality())) {
                purqua = Double.valueOf(lurusList.get(i).getPurchasingQuality());
            }
            Double s2 = Double.valueOf(String.valueOf(hashMap.get("liu")));
            Double tio22 = Double.valueOf(String.valueOf(hashMap.get("tio2")));
            if (s2 > 1) {
                s2 = 25 + (s2 - 1) * 100;
            }
            if (0.5 < s2 && s2 < 1) {
                s2 = (s2 - 0.5) * 50;
            }
            if (s2 < 0.5) {
                s2 = 0.0;
            }
            if (tio22 < 0.4) {
                tio22 = 0.0;
            } else {
                tio22 = (tio22 - 0.4) * 200;
            }
            Double jjhjg = (tfe2 - purqua) * 10 + Double.valueOf(lurufcop.getContractPrice()) - s2 - tio22;
            if (contains2 || "一单户".equals(lurusList.get(i).getPricingType())) {
                /*有效吨度价*/
                hashMap.put("yxpwduj", df.format(Double.valueOf(lurufcop.getContractPrice()) / yxpw));
            } else {
                hashMap.put("yxpwduj", df.format(jjhjg / yxpw));
            }
            maps.add(hashMap);
        }
        Collections.sort(maps, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double name1 = 0.00;
                Double name2 = 0.00;
                if (ObjectUtils.isEmpty(o1.get("yxpwduj")) || ObjectUtils.isEmpty(o2.get("yxpwduj"))) {
                    name1 = 0.00;
                    name2 = 0.00;
                } else {
                    name1 = Double.parseDouble((String) o1.get("yxpwduj"));
                    name2 = Double.parseDouble((String) o2.get("yxpwduj"));
                }
                return name1.compareTo(name2);

            }
        });
        return maps;
    }

    //    折叠弹窗页面数据@
    @RequestMapping("selecttcymsj")
    public Lurus selecttcymsj(String bbh, String gysmc) {
        List<String> strings = Arrays.asList(gysmclistb);
        boolean contains = strings.contains(gysmc);
        QueryWrapper<Lurus> lurusQueryWrapper = new QueryWrapper<>();
        lurusQueryWrapper.eq("versions", bbh);
        if (contains) {
            lurusQueryWrapper.eq("material_name", gysmc);
        } else {
            lurusQueryWrapper.eq("distributor_name", gysmc);
        }
        List<Lurus> lurusList = lurusMapper.selectList(lurusQueryWrapper);
        Lurus lurus = lurusList.get(0);
        return lurus;
    }

    //折叠弹窗的柱状图

    public Object selecttypeofzut(String ysmc, String gysmc) {
        String cgyl[] = {"TFe", "TiO2", "SiO2", "S", "H2O"};
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> strings = Arrays.asList(gysmclistb);
        boolean contains = strings.contains(gysmc);
        String a[] = new String[3];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowdate = format.format(new Date());
        String nowyear = nowdate.substring(0, 4);
        Integer integer = new Integer(nowyear);
        a[0] = nowyear;
        a[1] = String.valueOf(integer - 1);
        a[2] = String.valueOf(integer - 2);
        HashMap<String, ArrayList<String>> sal = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            ArrayList<String> na = new ArrayList<>();
            for (int i1 = 1; i1 <= 12; i1++) {
                String s = a[i] + "" + "-";
                StringBuilder sb = new StringBuilder(s);
                if (i1 < 10) {
                    sb.append("0");
                    sb.append(String.valueOf(i1));
                } else {
                    sb.append(String.valueOf(i1));
                }
                String s1 = sb.toString();
                Calendar cal = Calendar.getInstance();
                //设置年份
                cal.set(Calendar.YEAR, Integer.parseInt(a[i]));
                //设置月份
                cal.set(Calendar.MONTH, i1 - 1);
                //获取某月最小天数
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                //设置日历中月份的最小天数
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                //格式化日期
                String firstDayOfMonth = format.format(cal.getTime());
                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                String lastDayOfMonth = format.format(cal.getTime());
                List<String> ss = Arrays.asList(cgyl);
                boolean cs = ss.contains(ysmc);
                if (cs) {
                    QueryWrapper<Parsecz> pw = new QueryWrapper<>();
                    pw.between("rkrq", firstDayOfMonth, lastDayOfMonth);
                    if (contains) {
                        pw.eq("ylmc", gysmc);
                    } else {
                        pw.eq("gysmc", gysmc);
                    }
                    List<Parsecz> pz = parseczMapper.selectList(pw);
                    if (ObjectUtils.isEmpty(pz)) {
                        na.add(String.valueOf(0.0));
                        continue;
                    }
                    int num = 0;
                    Double item = 0.0;
                    Double retex = 0.0;
                    switch (ysmc) {
                        case "TFe":
                            for (int i2 = 0; i2 < pz.size(); i2++) {
                                String tfe = pz.get(i2).getTfe();
                                if (!ObjectUtils.isEmpty(tfe)) {
                                    item = item + Double.valueOf(tfe);
                                    num++;
                                }
                            }
                            retex = item == 0.0 ? 0.0 : item / num;
                            na.add(df.format(retex));
                            break;
                        case "TiO2":
                            for (int i2 = 0; i2 < pz.size(); i2++) {
                                String tio2 = pz.get(i2).getTio2();
                                if (!ObjectUtils.isEmpty(tio2)) {
                                    item = item + Double.valueOf(tio2);
                                    num++;
                                }
                            }
                            retex = item == 0.0 ? 0.0 : item / num;
                            na.add(df.format(retex));
                            break;
                        case "SiO2":
                            for (int i2 = 0; i2 < pz.size(); i2++) {
                                String sio2 = pz.get(i2).getSi02();
                                if (!ObjectUtils.isEmpty(sio2)) {
                                    item = item + Double.valueOf(sio2);
                                    num++;
                                }
                            }
                            retex = item == 0.0 ? 0.0 : item / num;
                            na.add(df.format(retex));
                            break;
                        case "S":
                            for (int i2 = 0; i2 < pz.size(); i2++) {
                                String sliu = pz.get(i2).getLiu();
                                if (!ObjectUtils.isEmpty(s)) {
                                    item = item + Double.valueOf(sliu);
                                    num++;
                                }
                            }
                            retex = item == 0.0 ? 0.0 : item / num;
                            na.add(df.format(retex));
                            break;
                        case "H2O":
                            for (int i2 = 0; i2 < pz.size(); i2++) {
                                String h2o = pz.get(i2).getShuifen();
                                if (!ObjectUtils.isEmpty(h2o)) {
                                    item = item + Double.valueOf(h2o);
                                    num++;
                                }
                            }
                            retex = item == 0.0 ? 0.0 : item / num;
                            na.add(df.format(retex));
                            break;
                        default:
                    }
                } else {
                    QueryWrapper<Unconerp> uw = new QueryWrapper<>();
                    uw.between("date", firstDayOfMonth, lastDayOfMonth);
                    if (contains) {
                        uw.eq("ypmc", gysmc);
                    } else {
                        uw.eq("gysmc", gysmc);
                    }
                    uw.eq("item", ysmc);
                    List<Unconerp> ups = unconerpMapper.selectList(uw);
                    if (ObjectUtils.isEmpty(ups)) {
                        na.add("0");
                    } else {
                        Double as = 0.0;
                        for (int i2 = 0; i2 < ups.size(); i2++) {
                            as = as + Double.valueOf(ups.get(i2).getPrice());
                        }
                        na.add(df.format(as / ups.size()));
                    }
                }
            }
            sal.put(a[i], na);
        }
        return sal;
    }

    @RequestMapping("selecttypeofzut")
    public List<Map<String, Object>> selectesmou(String ysmc, String gysmc) {
        String cgyl[] = {"TFe", "TiO2", "SiO2", "S", "H2O"};
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> strings = Arrays.asList(gysmclistb);
        boolean contains = strings.contains(gysmc);
//        根据当月往前推24个月
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM");
        ArrayList<String> as = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -i);
            String format = dfs.format(c.getTime());
            as.add(format);
        }
//       判断参数元素名称是常规还是非常规
        List<String> ss = Arrays.asList(cgyl);
        boolean cs = ss.contains(ysmc);
//        24个月年月的集合as
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        if (cs) {
            for (int i = 0; i < as.size(); i++) {
                Calendar cal = Calendar.getInstance();
                //设置年份
                cal.set(Calendar.YEAR, Integer.parseInt(as.get(i).substring(0, 4)));
                //设置月份
                cal.set(Calendar.MONTH, Integer.parseInt(as.get(i).substring(5, 7)) - 1);
                //获取某月最小天数
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                //设置日历中月份的最小天数
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                //格式化日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String firstDayOfMonth = sdf.format(cal.getTime());
                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                String lastDayOfMonth = sdf.format(cal.getTime());
                QueryWrapper<Parsecz> pw = new QueryWrapper<>();
                pw.between("rkrq", firstDayOfMonth, lastDayOfMonth);
                if (contains) {
                    pw.eq("ylmc", gysmc);
                } else {
                    pw.eq("gysmc", gysmc);
                }
                List<Parsecz> pz = parseczMapper.selectList(pw);
                if (ObjectUtils.isEmpty(pz)) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("date", as.get(i));
                    hashMap.put("value", 0);
                    maps.add(hashMap);
                    continue;
                }
                int num = 0;
                Double item = 0.0;
                Double retex = 0.0;
                HashMap<String, Object> hashMap = new HashMap<>();
                switch (ysmc) {
                    case "TFe":
                        for (int i2 = 0; i2 < pz.size(); i2++) {
                            String tfe = pz.get(i2).getTfe();
                            if (!ObjectUtils.isEmpty(tfe)) {
                                item = item + Double.valueOf(tfe);
                                num++;
                            }
                        }
                        retex = item == 0.0 ? 0.0 : item / num;
                        hashMap.put("date", as.get(i));
                        hashMap.put("value", df.format(retex));
                        maps.add(hashMap);
                        break;
                    case "TiO2":
                        for (int i2 = 0; i2 < pz.size(); i2++) {
                            String tio2 = pz.get(i2).getTio2();
                            if (!ObjectUtils.isEmpty(tio2)) {
                                item = item + Double.valueOf(tio2);
                                num++;
                            }
                        }
                        retex = item == 0.0 ? 0.0 : item / num;
                        hashMap.put("date", as.get(i));
                        hashMap.put("value", df.format(retex));
                        maps.add(hashMap);
                        break;
                    case "SiO2":
                        for (int i2 = 0; i2 < pz.size(); i2++) {
                            String sio2 = pz.get(i2).getSi02();
                            if (!ObjectUtils.isEmpty(sio2)) {
                                item = item + Double.valueOf(sio2);
                                num++;
                            }
                        }
                        retex = item == 0.0 ? 0.0 : item / num;
                        hashMap.put("date", as.get(i));
                        hashMap.put("value", df.format(retex));
                        maps.add(hashMap);
                        break;
                    case "S":
                        for (int i2 = 0; i2 < pz.size(); i2++) {
                            String sliu = pz.get(i2).getLiu();
                            if (!ObjectUtils.isEmpty(sliu)) {
                                item = item + Double.valueOf(sliu);
                                num++;
                            }
                        }
                        retex = item == 0.0 ? 0.0 : item / num;
                        hashMap.put("date", as.get(i));
                        hashMap.put("value", df.format(retex));
                        maps.add(hashMap);
                        break;
                    case "H2O":
                        for (int i2 = 0; i2 < pz.size(); i2++) {
                            String h2o = pz.get(i2).getShuifen();
                            if (!ObjectUtils.isEmpty(h2o)) {
                                item = item + Double.valueOf(h2o);
                                num++;
                            }
                        }
                        retex = item == 0.0 ? 0.0 : item / num;
                        hashMap.put("date", as.get(i));
                        hashMap.put("value", df.format(retex));
                        maps.add(hashMap);
                        break;
                    default:
                }
            }

            return maps;
        } else {
            for (int i = 0; i < as.size(); i++) {
                Calendar cal = Calendar.getInstance();
                //设置年份
                cal.set(Calendar.YEAR, Integer.parseInt(as.get(i).substring(0, 4)));
                //设置月份
                cal.set(Calendar.MONTH, Integer.parseInt(as.get(i).substring(5, 7)) - 1);
                //获取某月最小天数
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                //设置日历中月份的最小天数
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                //格式化日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String firstDayOfMonth = sdf.format(cal.getTime());
                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                String lastDayOfMonth = sdf.format(cal.getTime());
                QueryWrapper<Unconerp> uw = new QueryWrapper<>();
                uw.between("date", firstDayOfMonth, lastDayOfMonth);
                if (contains) {
                    uw.eq("ypmc", gysmc);
                } else {
                    uw.eq("gysmc", gysmc);
                }
                uw.eq("item", ysmc);
                List<Unconerp> ups = unconerpMapper.selectList(uw);
                HashMap<String, Object> hashMap = new HashMap<>();
                if (ObjectUtils.isEmpty(ups)) {
                    hashMap.put("date", as.get(i));
                    hashMap.put("value", 0);
                    maps.add(hashMap);
                } else {
                    Double ass = 0.0;
                    for (int i2 = 0; i2 < ups.size(); i2++) {
                        ass = ass + Double.valueOf(ups.get(i2).getPrice());
                    }
                    hashMap.put("date", as.get(i));
                    hashMap.put("value", df.format(ass / ups.size()));
                    maps.add(hashMap);
                }
            }
        }
        return maps;
    }

    //    折叠弹窗查询标准值信息
    @RequestMapping("selectbzzxx")
    public List<Map<String, Object>> selectbzzxx() {
        List<Bzbiao> bzbiaos = bzbiaoMapper.selectList(null);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < bzbiaos.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", bzbiaos.get(i).getId());
            hashMap.put("item", bzbiaos.get(i).getItem());
            hashMap.put("price1", bzbiaos.get(i).getPrice1());
            hashMap.put("price2", bzbiaos.get(i).getPrice2());
            maps.add(hashMap);
        }
        return maps;
    }

    //修改标准信息
    @RequestMapping("tastedupdate")
    public void tastedupdate(String bzlist) {
        JSONArray jsonArray = JSONArray.parseArray(bzlist);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);
            Integer id = new Integer((Integer) o.get("id"));
            Bzbiao bzbiao = new Bzbiao();
            bzbiao.setItem((String) o.get("item"));
            bzbiao.setPrice1((String) o.get("price1"));
            bzbiao.setPrice2((String) o.get("price2"));
            QueryWrapper<Bzbiao> bq = new QueryWrapper<>();
            bq.eq("id", id);
            bzbiaoMapper.update(bzbiao, bq);
        }
    }

    @RequestMapping("selectbbtsalsec")
    public List<Bizhb> selectbbtsalsec() {
        List<Bizhb> bizhbs = bizhbMapper.selectList(null);
        return bizhbs;
    }

    @RequestMapping("updatebzjhcsl")
    public Object updatebzjhcsl(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);
            Bizhb bizhb = new Bizhb();
            bizhb.setGysmc(o.getString("gysmc"));
            bizhb.setTfe(o.getString("tfe"));
            bizhb.setCao(o.getString("cao"));
            bizhb.setSi02(o.getString("si02"));
            bizhb.setMgo(o.getString("mgo"));
            bizhb.setAl2o3(o.getString("al2o3"));
            bizhb.setTio2(o.getString("tio2"));
            bizhb.setP(o.getString("p"));
            bizhb.setK2o(o.getString("k2o"));
            bizhb.setNa2o(o.getString("na2o"));
            bizhb.setZno(o.getString("zno"));
            bizhb.setMno(o.getString("mno"));
            bizhb.setShuifen(o.getString("shuifen"));
            bizhb.setIg(o.getString("ig"));
            bizhb.setLiu(o.getString("liu"));
            QueryWrapper<Bizhb> bqw = new QueryWrapper<>();
            bqw.eq("id", o.getString("id"));
            bizhbMapper.update(bizhb, bqw);
        }
        List<Bizhb> bizhbs = bizhbMapper.selectList(null);
        return bizhbs;
    }

    @RequestMapping("inserttghslsd")
    public List<Bizhb> inserttghslsd(String mc, String TFe, String CaO, String Si02,
                                     String MgO, String Al2O3, String Tio2, String P, String K2O, String Na2O, String ZnO,
                                     String MnO, String H20, String Ig, String S
    ) {
        Bizhb bizhb = new Bizhb();
        bizhb.setGysmc(mc);
        bizhb.setTfe(TFe);
        bizhb.setCao(CaO);
        bizhb.setSi02(Si02);
        bizhb.setMgo(MgO);
        bizhb.setAl2o3(Al2O3);
        bizhb.setTio2(Tio2);
        bizhb.setP(P);
        bizhb.setK2o(K2O);
        bizhb.setNa2o(Na2O);
        bizhb.setZno(ZnO);
        bizhb.setMno(MnO);
        bizhb.setShuifen(H20);
        bizhb.setIg(Ig);
        bizhb.setLiu(S);
        bizhbMapper.insert(bizhb);
        List<Bizhb> bizhbs = bizhbMapper.selectList(null);
        return bizhbs;
    }

    @RequestMapping("insertjcwh")
    public List<Jcwy> insertjcwh(String material_name, String distributor_name, String sterilise, String contract_price,
                                 String month_amount, String jssf, String jsfm, String gsdgls) {
        QueryWrapper<Jcwy> jqw = new QueryWrapper<>();
        ArrayList<Jcwy> jcwies1 = new ArrayList<>();
        jqw.eq("material_name", material_name);
        jqw.eq("distributor_name", distributor_name);
        jqw.eq("sterilise", sterilise);
        jqw.eq("contract_price", contract_price);
        jqw.eq("month_amount", month_amount);
        jqw.eq("jssf", jssf);
        jqw.eq("jsfm", jsfm);
        jqw.eq("gsdgil", gsdgls);
        List<Jcwy> jcwies = jcwyMapper.selectList(jqw);
        if (!ObjectUtils.isEmpty(jcwies)) {
            return jcwies1;
        }
        Jcwy jcwy = new Jcwy();
        jcwy.setMaterialName(material_name);
        jcwy.setDistributorName(distributor_name);
        jcwy.setSterilise(sterilise);
        jcwy.setContractPrice(contract_price);
        jcwy.setMonthAmount(month_amount);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        jcwy.setGsdgil(gsdgls);
        jcwyMapper.insert(jcwy);
        List<Jcwy> jcwies2 = jcwyMapper.selectList(null);
        return jcwies2;
    }

    //   初始化查询焦炭维护信息
    @RequestMapping("selectjcwh")
    public List<Jcwy> selectjcwh() {
        List<Jcwy> jcwies = jcwyMapper.selectList(null);
        return jcwies;
    }

    //焦炭维护页价格同加
    @RequestMapping("updatejcmonthprice")
    public List<Jcwy> sds(String tabli, String htjgzj) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgzj);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Jcwy> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Jcwy jcwy = new Jcwy();
            jcwy.setContractPrice(String.valueOf(b[i] + zjjg));
            jcwyMapper.update(jcwy, lw);
        }
        List<Jcwy> jcwies = jcwyMapper.selectList(null);
        return jcwies;
    }

    //合同价格同减
    @RequestMapping("updatedatejctj")
    public List<Jcwy> updatedatejctj(String tabli, String htjgjs) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgjs);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Jcwy> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Jcwy jcwy = new Jcwy();
            jcwy.setContractPrice(String.valueOf(b[i] - zjjg));
            jcwyMapper.update(jcwy, lw);
        }
        List<Jcwy> jcwies = jcwyMapper.selectList(null);
        return jcwies;
    }

    /*修改信息*/
    @RequestMapping("updatejcwhxs")
    public List<Jcwy> updatejcwhxs(String wlmc, String gysmc, String htjg, String yjhl, String fkfs, String jssf, String jsfm, String id, String gsdgls) {
        Jcwy jcwy = new Jcwy();
        jcwy.setMaterialName(wlmc);
        jcwy.setDistributorName(gysmc);
        jcwy.setSterilise(fkfs);
        jcwy.setContractPrice(htjg);
        jcwy.setMonthAmount(yjhl);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        jcwy.setGsdgil(gsdgls);
        QueryWrapper<Jcwy> lw = new QueryWrapper<>();
        lw.eq("id", id);
        int update = jcwyMapper.update(jcwy, lw);
        List<Jcwy> jcwies = jcwyMapper.selectList(null);
        return jcwies;
    }

    /**
     * @param idrs 数组 根据数组查询jcwy数据 然后加上时间信息存为版本号
     */
    //    焦炭维护页信息加上版本号存入数据库
    @RequestMapping("insertversiontocopy")
    public void insertversiontocopy(String idrs, String jzzaniu) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        JSONArray jsonArray = JSONArray.parseArray(idrs);
        for (int i = 0; i < jsonArray.size(); i++) {
            int id = (int) jsonArray.get(i);
            QueryWrapper<Jcwy> jw = new QueryWrapper<>();
            jw.eq("id", id);
            List<Jcwy> jcwies = jcwyMapper.selectList(jw);
            Jcwy jcwy = jcwies.get(0);
            JcwyCopy1 jcwyCopy1 = new JcwyCopy1();
            jcwyCopy1.setMaterialName(jcwy.getMaterialName());
            jcwyCopy1.setDistributorName(jcwy.getDistributorName());
            jcwyCopy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1.setJssf(jcwy.getJssf());
            jcwyCopy1.setJsfm(jcwy.getJsfm());
            jcwyCopy1.setGsdgils(jcwy.getGsdgil());
            jcwyCopy1.setVersion(format);
            jcwyCopy1.setJzzaniu("2");
            if (String.valueOf(id).equals(jzzaniu)) {
                jcwyCopy1.setJzzaniu("1");
            }
            jcwyCopy1Mapper.insert(jcwyCopy1);
        }
    }

    //   焦炭基础信息维护页面
    @RequestMapping("allinputsofjt")
    public List<JtbizhbCopy1> allinputsofjt() {
        List<JtbizhbCopy1> jtbizhbCopy1s = jtbizhbCopy1Mapper.selectList(null);
        return jtbizhbCopy1s;
    }

    //焦炭基础信息维护页面新增标准
    @RequestMapping("insertnesjtbz")
    public List<JtbizhbCopy1> insertnesjtbz(String mc, String TFe, String CaO, String Si02,
                                            String MgO, String Al2O3, String Tio2, String P, String K2O, String Na2O, String ZnO,
                                            String MnO) {
        JtbizhbCopy1 jtb = new JtbizhbCopy1();
        jtb.setGysmc(mc);
        jtb.setAd(TFe);
        jtb.setVdaf(CaO);
        jtb.setFcad(Si02);
        jtb.setStd(MgO);
        jtb.setM25(Al2O3);
        jtb.setCri(Tio2);
        jtb.setCsr(P);
        jtb.setMt(K2O);
        jtb.setK(Na2O);
        jtb.setNa(ZnO);
        jtb.setZn(MnO);
        jtbizhbCopy1Mapper.insert(jtb);
        List<JtbizhbCopy1> jtbizhbCopy1s = jtbizhbCopy1Mapper.selectList(null);
        return jtbizhbCopy1s;
    }

    //焦炭基础数据统一保存
    @RequestMapping("updatealltojtjcsj")
    public List<JtbizhbCopy1> updatealltojtjcsj(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JtbizhbCopy1 jc1 = new JtbizhbCopy1();
            jc1.setGysmc(jsonObject.getString("gysmc"));
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setFcad(jsonObject.getString("fcad"));
            jc1.setStd(jsonObject.getString("std"));
            jc1.setM25(jsonObject.getString("m25"));
            jc1.setCri(jsonObject.getString("cri"));
            jc1.setCsr(jsonObject.getString("csr"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setK(jsonObject.getString("k"));
            jc1.setNa(jsonObject.getString("na"));
            jc1.setZn(jsonObject.getString("zn"));
            QueryWrapper<JtbizhbCopy1> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            jtbizhbCopy1Mapper.update(jc1, jqw);
        }
        List<JtbizhbCopy1> jp1s = jtbizhbCopy1Mapper.selectList(null);
        return jp1s;
    }

    /**
     * 全查询版本号
     */
    @RequestMapping("selectbehed")
    public List<String> selectbehed() {
        QueryWrapper<JcwyCopy1> jf = new QueryWrapper<>();
        jf.orderByDesc("version");
        jf.select("DISTINCT version");
        jf.last("limit 20");
        //jf.select("limit 20");
        List<JcwyCopy1> jc = jcwyCopy1Mapper.selectList(jf);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = jc.size() - 1; i >= 0; i--) {
            strings.add(jc.get(i).getVersion());
        }
        return strings;
    }

    /**
     * @param 基准值+Ad*2/100*基准值+Vdaf*5+Std*10*2/100*基准值-M25*0.75/100*基准值-CSR*0.75/100*基准值+CRI*0.53/100*基准值+K/0.05*0.5/100*基准值
     * @author
     */
    @RequestMapping("selecttocopycopy")
    public List<JcwyCopy1Copy1> selecttocopycopy(String bbh, String tml, String tal) {
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.eq("date", bbh.substring(0, 10));
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        /*新增步骤先去copy1表查询基准值为1的信息,然后查询该条信息的各个元素的7天平均数,之后根据这个平局数做差计算顶层的数据*/
        JcwyCopy1Copy1 selectjzxxsjgls = selectjzxxsjgls(bbh, shm);
        QueryWrapper<JcwyCopy1> jw = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(tml) && ObjectUtils.isEmpty(tal)) {
            jw.eq("version", bbh);
        } else {
            // HH:mm:ss
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                jw.between("version", sd.parse(tml), sd.parse(tal));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<JcwyCopy1> jcwyCopy1s = jcwyCopy1Mapper.selectList(jw);
        ArrayList<JcwyCopy1Copy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            JcwyCopy1Copy1 jcwyCopy1Copy1 = new JcwyCopy1Copy1();
            jcwyCopy1Copy1.setMaterialName(jcwyCopy1s.get(i).getMaterialName());
            jcwyCopy1Copy1.setDistributorName(jcwyCopy1s.get(i).getDistributorName());
            jcwyCopy1Copy1.setSterilise(jcwyCopy1s.get(i).getSterilise());
            jcwyCopy1Copy1.setContractPrice(jcwyCopy1s.get(i).getContractPrice());
            jcwyCopy1Copy1.setMonthAmount(jcwyCopy1s.get(i).getMonthAmount());
            jcwyCopy1Copy1.setJssf(jcwyCopy1s.get(i).getJssf());
            jcwyCopy1Copy1.setJsfm(jcwyCopy1s.get(i).getJsfm());
            jcwyCopy1Copy1.setVersion(jcwyCopy1s.get(i).getVersion());
            jcwyCopy1Copy1.setGsdgils(jcwyCopy1s.get(i).getGsdgils());
            QueryWrapper<JtbizhbCopy1> jtwq = new QueryWrapper<>();
            jtwq.eq("gysmc", jcwyCopy1s.get(i).getDistributorName());
            List<JtbizhbCopy1> jtbizhbCopy1s = jtbizhbCopy1Mapper.selectList(jtwq);
            DecimalFormat df = new DecimalFormat("0.00");
            JtbizhbCopy1 jtbizhbCopy1 = jtbizhbCopy1s.get(0);
            jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
            jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
            jcwyCopy1Copy1.setFcad(df.format(Double.valueOf(jtbizhbCopy1.getFcad())));
            jcwyCopy1Copy1.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStd())));
            jcwyCopy1Copy1.setM25(df.format(Double.valueOf(jtbizhbCopy1.getM25())));
            jcwyCopy1Copy1.setCri(df.format(Double.valueOf(jtbizhbCopy1.getCri())));
            jcwyCopy1Copy1.setCsr(df.format(Double.valueOf(jtbizhbCopy1.getCsr())));
            jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
            jcwyCopy1Copy1.setK(df.format(Double.valueOf(jtbizhbCopy1.getK())));
            jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jtbizhbCopy1.getNa())));
            jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jtbizhbCopy1.getZn())));
//          爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
            HashMap<String, String> jsqtpjz = jsqtpjz(jcwyCopy1s.get(i).getDistributorName(), bbh, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcad")) {
                jcwyCopy1Copy1.setFcad(df.format(Double.valueOf(jsqtpjz.get("Fcad"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("M25")) {
                jcwyCopy1Copy1.setM25(df.format(Double.valueOf(jsqtpjz.get("M25"))));
            }
            if (jsqtpjz.containsKey("CRI")) {
                jcwyCopy1Copy1.setCri(df.format(Double.valueOf(jsqtpjz.get("CRI"))));
            }
            if (jsqtpjz.containsKey("CSR")) {
                jcwyCopy1Copy1.setCsr(df.format(Double.valueOf(jsqtpjz.get("CSR"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//           非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            //基准值查询
            QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
            butvaluQueryWrapper.eq("attribute_name", "基准值修改");
            List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
            String propertyValue = butvalus.get(0).getPropertyValue();
            //合同价格(折成承兑) 现汇=合同价        承兑=现汇*1.025
            jcwyCopy1Copy1.setJshtjg(df.format(Double.valueOf(jcwyCopy1s.get(i).getContractPrice()) * 1.025));
            //不含税价格 合同价格/1.13*95/(100-结算水分)              - Double.valueOf(selectfcgtys.get("ZnO"))
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1s.get(i).getContractPrice()) / 1.13 * 95 / (100 - Double.valueOf(jcwyCopy1s.get(i).getJssf()))));
            //干焦比 基准值+Ad*2/100*基准值+Vdaf*5+Std*10*2/100*基准值-M25*0.75/100*基准值-CSR*0.75/100*基准值+CRI*0.53/100*基准值+K/0.05*0.5/100*基准值
            String k20 = "";
            Double format = Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getAd()) - Double.valueOf(selectjzxxsjgls.getAd())) * 2 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getVdaf()) - Double.valueOf(selectjzxxsjgls.getVdaf())) * 5 +
                    (Double.valueOf(jcwyCopy1Copy1.getStd()) - Double.valueOf(selectjzxxsjgls.getStd())) * 10 * 2 / 100 * Double.valueOf(propertyValue) - (Double.valueOf(jcwyCopy1Copy1.getM25()) - Double.valueOf(selectjzxxsjgls.getM25())) * 0.75 / 100 * Double.valueOf(propertyValue) -
                    (Double.valueOf(jcwyCopy1Copy1.getCsr()) - Double.valueOf(selectjzxxsjgls.getCsr())) * 0.75 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getCri()) - Double.valueOf(selectjzxxsjgls.getCri())) * 0.53 / 100 * Double.valueOf(propertyValue) +
                    (Double.valueOf(jcwyCopy1Copy1.getK()) - Double.valueOf(selectjzxxsjgls.getK())) / 0.05 * 0.5 / 100 * Double.valueOf(propertyValue);
            jcwyCopy1Copy1.setGjb(df.format(format));
            //基准值
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            if ("1".equals(jcwyCopy1s.get(i).getJzzaniu())) {
                jcwyCopy1Copy1.setGjb(propertyValue);
                //净焦比
                String format1 = df.format(Double.valueOf(propertyValue) / (1 - Double.valueOf(jcwyCopy1s.get(i).getJssf()) / 100));
                jcwyCopy1Copy1.setJjb(format1);
                //毛焦比
                String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1s.get(i).getJsfm()) / 100));
                jcwyCopy1Copy1.setMjb(format2);
                //毛焦成本
                String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
                jcwyCopy1Copy1.setMjcb(format3);
                //兑现率 月实际累计到货量/月计划量
                Calendar cal = Calendar.getInstance();
                //设置年份
                cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
                //设置月份
                cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
                //获取某月最小天数
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                //设置日历中月份的最小天数
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                //格式化日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String firstDayOfMonth = sdf.format(cal.getTime());
                QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
                jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
                List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
                Double sum = 0.0;
                for (int i1 = 0; i1 < jtgrs.size(); i1++) {
                    sum = sum + Double.valueOf(jtgrs.get(i1).getSjzl());
                }
                String gmail = "";
                String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("∞") || decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("�") ? String.valueOf(0) : df.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())));
                //差异 毛焦成本减基准值的毛焦成本 propertyValue
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(jcwyCopy1Copy1.getMjcb()) - Double.valueOf(jcwyCopy1Copy1.getMjcb())));
                //单焦种的采购量/焦炭总采购量
                jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
                continue;
            }
            //净焦比 干焦比/(1-结算水分/100)
            String format1 = df.format(Double.valueOf(format) / (1 - Double.valueOf(jcwyCopy1s.get(i).getJssf()) / 100));
            jcwyCopy1Copy1.setJjb(format1);
            //毛焦比 净焦比/(1-结算粉末/100)
            String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1s.get(i).getJsfm()) / 100));
            jcwyCopy1Copy1.setMjb(format2);
            //毛焦成本 毛焦比*不含税价格/1000
            String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
            jcwyCopy1Copy1.setMjcb(format3);
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String firstDayOfMonth = sdf.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
            Double sum = 0.0;
            for (int i1 = 0; i1 < jtgrs.size(); i1++) {
                sum = sum + Double.valueOf(jtgrs.get(i1).getSjzl());
            }
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("∞") || decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("�") ? String.valueOf(0) : decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())));
            //差异 毛焦成本减基准值的毛焦成本 propertyValue
            //jcwyCopy1Copy1.setCy(df.format(Double.valueOf(jcwyCopy1Copy1.getMjcb()) - Double.valueOf(propertyValue)));
            if ("1".equals(jcwyCopy1s.get(i).getJzzaniu())) {
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(propertyValue) - Double.valueOf(propertyValue)));
            }
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }
        return jcwyCopy1Copy1s1;
    }

    /**
     * 区间查询总图信息
     *
     * @param tml
     * @param tal
     * @param bbh
     * @return
     * @throws ParseException
     */
    @RequestMapping("selecttacileglsmat")
    public List<JcwyCopy1Copy1> selecttacileglsmat(String tml, String tal, String bbh) throws ParseException {
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        /*新增步骤先去copy1表查询基准值为1的信息,然后查询该条信息的各个元素的7天平均数,之后根据这个平局数做差计算顶层的数据*/
        //标准值为最新一笔
        JcwyCopy1Copy1 selectjzxxsjgls = selectjzxxsjgls1(bbh, tml, tal, shm);
        //查询区间内所有的供应商
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.select("distinct distributor_name");
        //最近修改区间查询从原来的版本号更改为倒叙全查询
        //jqw.orderByDesc("version");
        jqw.between("version", tml, tal);
        //最近修改
        List<JcwyCopy1> jcwyCopy1s = jcwyCopy1Mapper.selectList(jqw);
        ArrayList<JcwyCopy1Copy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            //查询当前供应商在标准表中的最新一笔数据
            JcwyCopy1 jcwyCopy1 = new JcwyCopy1();
            QueryWrapper<JcwyCopy1> jqw1 = new QueryWrapper<>();
            jqw1.eq("distributor_name", jcwyCopy1s.get(i).getDistributorName());
            jqw1.orderByDesc("version");
            List<JcwyCopy1> jcwyCopy1s2 = jcwyCopy1Mapper.selectList(jqw1);
            //最新一笔数据
            JcwyCopy1 jcwyCopy11 = jcwyCopy1s2.get(0);
            //开始把当前对象(最新一笔)的物料名称,合同价格,结算水分,结算粉末,归属地,燃料类型,月计划量,版本号,付款方式存放至jcwyCopy1Copy1对象中
            JcwyCopy1Copy1 jcwyCopy1Copy1 = new JcwyCopy1Copy1();
            jcwyCopy1Copy1.setDistributorName(jcwyCopy11.getDistributorName());
            jcwyCopy1Copy1.setContractPrice(jcwyCopy11.getContractPrice());
            jcwyCopy1Copy1.setGsdgils(jcwyCopy11.getGsdgils());
            jcwyCopy1Copy1.setJssf(jcwyCopy11.getJssf());
            jcwyCopy1Copy1.setJsfm(jcwyCopy11.getJsfm());
            jcwyCopy1Copy1.setMaterialName(jcwyCopy11.getMaterialName());
            jcwyCopy1Copy1.setSterilise(jcwyCopy11.getSterilise());
            jcwyCopy1Copy1.setMonthAmount(jcwyCopy11.getMonthAmount());
            jcwyCopy1Copy1.setVersion(jcwyCopy11.getVersion());
            //查询焦炭维护的标准表中的数据(防止常规非常规中没有数据)
            QueryWrapper<JtbizhbCopy1> jtwq = new QueryWrapper<>();
            jtwq.eq("gysmc", jcwyCopy1s.get(i).getDistributorName());
            List<JtbizhbCopy1> jtbizhbCopy1s = jtbizhbCopy1Mapper.selectList(jtwq);
            DecimalFormat df = new DecimalFormat("0.00");
            JtbizhbCopy1 jtbizhbCopy1 = jtbizhbCopy1s.get(0);
            jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
            jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
            jcwyCopy1Copy1.setFcad(df.format(Double.valueOf(jtbizhbCopy1.getFcad())));
            jcwyCopy1Copy1.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStd())));
            jcwyCopy1Copy1.setM25(df.format(Double.valueOf(jtbizhbCopy1.getM25())));
            jcwyCopy1Copy1.setCri(df.format(Double.valueOf(jtbizhbCopy1.getCri())));
            jcwyCopy1Copy1.setCsr(df.format(Double.valueOf(jtbizhbCopy1.getCsr())));
            jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
            jcwyCopy1Copy1.setK(df.format(Double.valueOf(jtbizhbCopy1.getK())));
            jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jtbizhbCopy1.getNa())));
            jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jtbizhbCopy1.getZn())));
            //常规表根据区间查询平均值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sd1.parse(tml);
            Date d2 = sd1.parse(tal);
            int i1 = daysBetween(d1, d2);
            HashMap<String, String> jsqtpjz = jsqtpjz1rs(jcwyCopy1s.get(i).getDistributorName(), tal, i1, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcad")) {
                jcwyCopy1Copy1.setFcad(df.format(Double.valueOf(jsqtpjz.get("Fcad"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("M25")) {
                jcwyCopy1Copy1.setM25(df.format(Double.valueOf(jsqtpjz.get("M25"))));
            }
            if (jsqtpjz.containsKey("CRI")) {
                jcwyCopy1Copy1.setCri(df.format(Double.valueOf(jsqtpjz.get("CRI"))));
            }
            if (jsqtpjz.containsKey("CSR")) {
                jcwyCopy1Copy1.setCsr(df.format(Double.valueOf(jsqtpjz.get("CSR"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//          非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            //基准值查询
            QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
            butvaluQueryWrapper.eq("attribute_name", "基准值修改");
            List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
            String propertyValue = butvalus.get(0).getPropertyValue();
            //合同价格(折成承兑) 现汇=合同价        承兑=现汇*1.025
            jcwyCopy1Copy1.setJshtjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) * 1.025));
            //不含税价格 合同价格/1.13*95/(100-结算水分)              - Double.valueOf(selectfcgtys.get("ZnO"))
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 * 95 / (100 - Double.valueOf(jcwyCopy1Copy1.getJssf()))));
            //干焦比 基准值+Ad*2/100*基准值+Vdaf*5+Std*10*2/100*基准值-M25*0.75/100*基准值-CSR*0.75/100*基准值+CRI*0.53/100*基准值+K/0.05*0.5/100*基准值
            String k20 = "";
            Double format = Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getAd()) - Double.valueOf(selectjzxxsjgls.getAd())) * 2 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getVdaf()) - Double.valueOf(selectjzxxsjgls.getVdaf())) * 5 +
                    (Double.valueOf(jcwyCopy1Copy1.getStd()) - Double.valueOf(selectjzxxsjgls.getStd())) * 10 * 2 / 100 * Double.valueOf(propertyValue) - (Double.valueOf(jcwyCopy1Copy1.getM25()) - Double.valueOf(selectjzxxsjgls.getM25())) * 0.75 / 100 * Double.valueOf(propertyValue) -
                    (Double.valueOf(jcwyCopy1Copy1.getCsr()) - Double.valueOf(selectjzxxsjgls.getCsr())) * 0.75 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getCri()) - Double.valueOf(selectjzxxsjgls.getCri())) * 0.53 / 100 * Double.valueOf(propertyValue) +
                    (Double.valueOf(jcwyCopy1Copy1.getK()) - Double.valueOf(selectjzxxsjgls.getK())) / 0.05 * 0.5 / 100 * Double.valueOf(propertyValue);
            jcwyCopy1Copy1.setGjb(df.format(format));
            //基准值
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            if (selectjzxxsjgls.getDistributorName().equals(jcwyCopy1s.get(i).getDistributorName())) {
                jcwyCopy1Copy1.setGjb(propertyValue);
                //净焦比
                String format1 = df.format(Double.valueOf(propertyValue) / (1 - Double.valueOf(jcwyCopy1Copy1.getJssf()) / 100));
                jcwyCopy1Copy1.setJjb(format1);
                //毛焦比
                String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1Copy1.getJsfm()) / 100));
                jcwyCopy1Copy1.setMjb(format2);
                //毛焦成本
                String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
                jcwyCopy1Copy1.setMjcb(format3);
                //兑现率 月实际累计到货量/月计划量
                Calendar cal = Calendar.getInstance();
                //设置年份
                cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
                //设置月份
                cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
                //获取某月最小天数
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                //设置日历中月份的最小天数
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                //格式化日期
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                String firstDayOfMonth = sdf1.format(cal.getTime());
                QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
                jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
                List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
                Double sum = 0.0;
                for (int i2 = 0; i2 < jtgrs.size(); i2++) {
                    sum = sum + Double.valueOf(jtgrs.get(i2).getSjzl());
                }
                String gmail = "";
                String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("∞") || decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("�") ? String.valueOf(0) : df.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())));
                //差异 毛焦成本减基准值的毛焦成本 propertyValue
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(jcwyCopy1Copy1.getMjcb()) - Double.valueOf(jcwyCopy1Copy1.getMjcb())));
                //单焦种的采购量/焦炭总采购量
                jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
                continue;
            }
            //净焦比 干焦比/(1-结算水分/100)
            String format1 = df.format(Double.valueOf(format) / (1 - Double.valueOf(jcwyCopy1Copy1.getJssf()) / 100));
            jcwyCopy1Copy1.setJjb(format1);
            //毛焦比 净焦比/(1-结算粉末/100)
            String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1Copy1.getJsfm()) / 100));
            jcwyCopy1Copy1.setMjb(format2);
            //毛焦成本 毛焦比*不含税价格/1000
            String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
            jcwyCopy1Copy1.setMjcb(format3);
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String firstDayOfMonth = sdf1.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
            Double sum = 0.0;
            for (int i3 = 0; i3 < jtgrs.size(); i3++) {
                sum = sum + Double.valueOf(jtgrs.get(i3).getSjzl());
            }
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("∞") || decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("�") ? String.valueOf(0) : decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())));
            //差异 毛焦成本减基准值的毛焦成本 propertyValue
            if (selectjzxxsjgls.getDistributorName().equals(jcwyCopy1s.get(i).getDistributorName())) {
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(propertyValue) - Double.valueOf(propertyValue)));
            }
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }
        return jcwyCopy1Copy1s1;
    }

    @RequestMapping("qjcxgcils")
    public Object qjcxgcils(String tml, String tal, String bbh) throws ParseException {
        DecimalFormat df = new DecimalFormat("0.00");
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        //标准值为最新一笔
        JcwyCopy1Copy1 selectjzxxsjgls = selectjzxxsjgls1(bbh, tml, tal, shm);
        //去除重复查询常规表
        QueryWrapper<Jtgrs> jtgrs = new QueryWrapper<>();
        jtgrs.select("distinct gysmc,fhdd");
        jtgrs.between("jjrq", tml, tal);
        List<Jtgrs> jtgrsList = jtgrsMapper.selectList(jtgrs);
        ArrayList<JcwyCopy1Copy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        //根据日期区间查询焦炭常规表,湿基重量为空的不要
        for (int i = 0; i < jtgrsList.size(); i++) {
            JcwyCopy1Copy1 jcwyCopy1Copy1 = new JcwyCopy1Copy1();
            //根据供应商名称去jtbizhb_copy1查询各个元素的默认值,如果常规表中有该供应商的元素值则替换掉jtbizhb_copy1的元素值
            QueryWrapper<JtbizhbCopy1> jtbizhbCopy1QueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc());
            }
            JtbizhbCopy1 jtbizhbCopy1 = new JtbizhbCopy1();
            List<JtbizhbCopy1> jtbizhbCopy1s = jtbizhbCopy1Mapper.selectList(jtbizhbCopy1QueryWrapper);
            if (!ObjectUtils.isEmpty(jtbizhbCopy1s)) {
                jtbizhbCopy1 = jtbizhbCopy1Mapper.selectList(jtbizhbCopy1QueryWrapper).get(0);
                jcwyCopy1Copy1.setAd(jtbizhbCopy1.getAd());
                jcwyCopy1Copy1.setVdaf(jtbizhbCopy1.getVdaf());
                jcwyCopy1Copy1.setFcad(jtbizhbCopy1.getFcad());
                jcwyCopy1Copy1.setStd(jtbizhbCopy1.getStd());
                jcwyCopy1Copy1.setM25(jtbizhbCopy1.getM25());
                jcwyCopy1Copy1.setCri(jtbizhbCopy1.getCri());
                jcwyCopy1Copy1.setCsr(jtbizhbCopy1.getCsr());
                jcwyCopy1Copy1.setMt(jtbizhbCopy1.getMt());
                jcwyCopy1Copy1.setK(jtbizhbCopy1.getK());
                jcwyCopy1Copy1.setNa(jtbizhbCopy1.getNa());
                jcwyCopy1Copy1.setZn(jtbizhbCopy1.getZn());
            } else {
                continue;
            }
            String namers = "";
            namers = jtgrsList.get(i).getGysmc();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                namers = jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd();
            }
            //常规表根据区间查询平均值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sd1.parse(tml);
            Date d2 = sd1.parse(tal);
            int i1 = daysBetween(d1, d2);
            HashMap<String, String> jsqtpjz = jsqtpjz1rs(namers, tal, i1, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcad")) {
                jcwyCopy1Copy1.setFcad(df.format(Double.valueOf(jsqtpjz.get("Fcad"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("M25")) {
                jcwyCopy1Copy1.setM25(df.format(Double.valueOf(jsqtpjz.get("M25"))));
            }
            if (jsqtpjz.containsKey("CRI")) {
                jcwyCopy1Copy1.setCri(df.format(Double.valueOf(jsqtpjz.get("CRI"))));
            }
            if (jsqtpjz.containsKey("CSR")) {
                jcwyCopy1Copy1.setCsr(df.format(Double.valueOf(jsqtpjz.get("CSR"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            QueryWrapper<Jtgrs> jtgrsnes = new QueryWrapper<>();
            jtgrsnes.between("jjrq", tml, tal);
            //jtgrsnes.ne("sjzl", "");
            jtgrsnes.eq("gysmc", jtgrsList.get(i).getGysmc());
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtgrsnes.eq("fhdd", jtgrsList.get(i).getFhdd());
            } else {
                jtgrsnes.eq("fhdd", "");
                jtgrsnes.ne("ylmc", "喷吹无烟煤");
                jtgrsnes.ne("ylmc", "喷吹烟煤");
            }
            jtgrsnes.select("SUM(sjzl)");
            Map<String, Object> maps = jtgrsMapper.selectMaps(jtgrsnes).get(0);
            jcwyCopy1Copy1.setCgl(df.format(maps.get("SUM(sjzl)")));
//          非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            //根据供应商名称查询jcwy表数据
            QueryWrapper<Jcwy> jcwyQueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc());
            }
            Jcwy jcwy = jcwyMapper.selectList(jcwyQueryWrapper).get(0);
            jcwyCopy1Copy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1Copy1.setGsdgils(jcwy.getGsdgil());
            jcwyCopy1Copy1.setJssf(jcwy.getJssf());
            jcwyCopy1Copy1.setJsfm(jcwy.getJsfm());
            jcwyCopy1Copy1.setMaterialName(jcwy.getMaterialName());
            jcwyCopy1Copy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1Copy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1Copy1.setDistributorName(jcwy.getDistributorName());
            //合同价格(折成承兑) 现汇=合同价        承兑=现汇*1.025
            jcwyCopy1Copy1.setJshtjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) * 1.025));
            //不含税价格 合同价格/1.13*95/(100-结算水分)              - Double.valueOf(selectfcgtys.get("ZnO"))
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 * 95 / (100 - Double.valueOf(jcwyCopy1Copy1.getJssf()))));
            //如果当前供应商名称相等于selectjzxxsjgls中的供应商名称,则吧当前供应商设为基准值
            //基准值查询
            QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
            butvaluQueryWrapper.eq("attribute_name", "基准值修改");
            List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
            String propertyValue = butvalus.get(0).getPropertyValue();
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            if (namers.equals(selectjzxxsjgls.getDistributorName())) {
                jcwyCopy1Copy1.setAd(selectjzxxsjgls.getAd());
                jcwyCopy1Copy1.setVdaf(selectjzxxsjgls.getVdaf());
                jcwyCopy1Copy1.setFcad(selectjzxxsjgls.getFcad());
                jcwyCopy1Copy1.setStd(selectjzxxsjgls.getStd());
                jcwyCopy1Copy1.setM25(selectjzxxsjgls.getM25());
                jcwyCopy1Copy1.setCri(selectjzxxsjgls.getCri());
                jcwyCopy1Copy1.setCsr(selectjzxxsjgls.getCsr());
                jcwyCopy1Copy1.setMt(selectjzxxsjgls.getMt());
                jcwyCopy1Copy1.setGjb(propertyValue);
                //净焦比
                String format1 = df.format(Double.valueOf(propertyValue) / (1 - Double.valueOf(jcwyCopy1Copy1.getJssf()) / 100));
                jcwyCopy1Copy1.setJjb(format1);
                //毛焦比
                String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1Copy1.getJsfm()) / 100));
                jcwyCopy1Copy1.setMjb(format2);
                //毛焦成本
                String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
                jcwyCopy1Copy1.setMjcb(format3);
                String format4 = decimalFormat.format(Double.valueOf(jcwyCopy1Copy1.getCgl()) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount()));
                jcwyCopy1Copy1.setDxl("∞".equals(format4) ? "0.0" : format4);
                //差异 毛焦成本减基准值的毛焦成本 propertyValue
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(jcwyCopy1Copy1.getMjcb()) - Double.valueOf(jcwyCopy1Copy1.getMjcb())));
                //单焦种的采购量/焦炭总采购量
                jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
                continue;
            }
            //合同价格(折成承兑) 现汇=合同价        承兑=现汇*1.025
            jcwyCopy1Copy1.setJshtjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) * 1.025));
            //不含税价格 合同价格/1.13*95/(100-结算水分)              - Double.valueOf(selectfcgtys.get("ZnO"))
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 * 95 / (100 - Double.valueOf(jcwyCopy1Copy1.getJssf()))));
            //干焦比 基准值+Ad*2/100*基准值+Vdaf*5+Std*10*2/100*基准值-M25*0.75/100*基准值-CSR*0.75/100*基准值+CRI*0.53/100*基准值+K/0.05*0.5/100*基准值
            String k20 = "";
            Double format = Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getAd()) - Double.valueOf(selectjzxxsjgls.getAd())) * 2 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getVdaf()) - Double.valueOf(selectjzxxsjgls.getVdaf())) * 5 +
                    (Double.valueOf(jcwyCopy1Copy1.getStd()) - Double.valueOf(selectjzxxsjgls.getStd())) * 10 * 2 / 100 * Double.valueOf(propertyValue) - (Double.valueOf(jcwyCopy1Copy1.getM25()) - Double.valueOf(selectjzxxsjgls.getM25())) * 0.75 / 100 * Double.valueOf(propertyValue) -
                    (Double.valueOf(jcwyCopy1Copy1.getCsr()) - Double.valueOf(selectjzxxsjgls.getCsr())) * 0.75 / 100 * Double.valueOf(propertyValue) + (Double.valueOf(jcwyCopy1Copy1.getCri()) - Double.valueOf(selectjzxxsjgls.getCri())) * 0.53 / 100 * Double.valueOf(propertyValue) +
                    (Double.valueOf(jcwyCopy1Copy1.getK()) - Double.valueOf(selectjzxxsjgls.getK())) / 0.05 * 0.5 / 100 * Double.valueOf(propertyValue);
            jcwyCopy1Copy1.setGjb(df.format(format));
            //净焦比 干焦比/(1-结算水分/100)
            String format1 = df.format(Double.valueOf(format) / (1 - Double.valueOf(jcwyCopy1Copy1.getJssf()) / 100));
            jcwyCopy1Copy1.setJjb(format1);
            //毛焦比 净焦比/(1-结算粉末/100)
            String format2 = df.format(Double.valueOf(format1) / (1 - Double.valueOf(jcwyCopy1Copy1.getJsfm()) / 100));
            jcwyCopy1Copy1.setMjb(format2);
            //毛焦成本 毛焦比*不含税价格/1000
            String format3 = df.format(Double.valueOf(format2) * Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / 1000);
            jcwyCopy1Copy1.setMjcb(format3);
            //兑现率 月实际累计到货量/月计划量
            String gmail = "";
            String format4 = decimalFormat.format(Double.valueOf(jcwyCopy1Copy1.getCgl()) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount()));
            jcwyCopy1Copy1.setDxl("∞".equals(format4) ? "0.0" : format4);
            //差异 毛焦成本减基准值的毛焦成本 propertyValue
            if (namers.equals(selectjzxxsjgls.getDistributorName())) {
                jcwyCopy1Copy1.setCy(df.format(Double.valueOf(propertyValue) - Double.valueOf(propertyValue)));
            }
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }

        return jcwyCopy1Copy1s1;
    }

    public int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * @param bbh
     * @return
     */
    /*新增步骤先去copy1表查询基准值为1的信息,然后查询该条信息的各个元素的7天平均数,之后根据这个平局数做差计算顶层的数据*/
    public JcwyCopy1Copy1 selectjzxxsjgls(String bbh, Map<String, String> shm) {
        JcwyCopy1Copy1 jcwyCopy1Copy1 = new JcwyCopy1Copy1();
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.eq("jzzaniu", "1");
        jqw.eq("version", bbh);
        List<JcwyCopy1> jc1s = jcwyCopy1Mapper.selectList(jqw);
        JcwyCopy1 jcwyCopy1 = jc1s.get(0);
        String distributorName = jcwyCopy1.getDistributorName();
        String version = jcwyCopy1.getVersion();
        QueryWrapper<JtbizhbCopy1> j1qw = new QueryWrapper<>();
        j1qw.eq("gysmc", distributorName);
        List<JtbizhbCopy1> jt1s = jtbizhbCopy1Mapper.selectList(j1qw);
        JtbizhbCopy1 jtbizhbCopy1 = jt1s.get(0);
        jcwyCopy1Copy1.setDistributorName(distributorName);
        jcwyCopy1Copy1.setAd(jtbizhbCopy1.getAd());
        jcwyCopy1Copy1.setVdaf(jtbizhbCopy1.getVdaf());
        jcwyCopy1Copy1.setFcad(jtbizhbCopy1.getFcad());
        jcwyCopy1Copy1.setStd(jtbizhbCopy1.getStd());
        jcwyCopy1Copy1.setM25(jtbizhbCopy1.getM25());
        jcwyCopy1Copy1.setCri(jtbizhbCopy1.getCri());
        jcwyCopy1Copy1.setCsr(jtbizhbCopy1.getCsr());
        jcwyCopy1Copy1.setMt(jtbizhbCopy1.getMt());
        jcwyCopy1Copy1.setK(jtbizhbCopy1.getK());
        jcwyCopy1Copy1.setNa(jtbizhbCopy1.getNa());
        jcwyCopy1Copy1.setZn(jtbizhbCopy1.getZn());
        HashMap<String, String> jsqtpjz = jsqtpjz(distributorName, version, shm);
        if (jsqtpjz.containsKey("Ad")) {
            jcwyCopy1Copy1.setAd(jsqtpjz.get("Ad"));
        }
        if (jsqtpjz.containsKey("Vdaf")) {
            jcwyCopy1Copy1.setVdaf(jsqtpjz.get("Vdaf"));
        }
        if (jsqtpjz.containsKey("Fcad")) {
            jcwyCopy1Copy1.setFcad(jsqtpjz.get("Fcad"));
        }
        if (jsqtpjz.containsKey("Std")) {
            jcwyCopy1Copy1.setStd(jsqtpjz.get("Std"));
        }
        if (jsqtpjz.containsKey("M25")) {
            jcwyCopy1Copy1.setM25(jsqtpjz.get("M25"));
        }
        if (jsqtpjz.containsKey("CRI")) {
            jcwyCopy1Copy1.setCri(jsqtpjz.get("CRI"));
        }
        if (jsqtpjz.containsKey("CSR")) {
            jcwyCopy1Copy1.setCsr(jsqtpjz.get("CSR"));
        }
        if (jsqtpjz.containsKey("Mt")) {
            jcwyCopy1Copy1.setMt(jsqtpjz.get("Mt"));
        }
        if (jsqtpjz.containsKey("K2O")) {
            jcwyCopy1Copy1.setK(jsqtpjz.get("K2O"));
        }
        if (jsqtpjz.containsKey("Na2O")) {
            jcwyCopy1Copy1.setK(jsqtpjz.get("Na2O"));
        }
        if (jsqtpjz.containsKey("ZnO")) {
            jcwyCopy1Copy1.setK(jsqtpjz.get("ZnO"));
        }
        jcwyCopy1Copy1.setDistributorName(distributorName);
        jcwyCopy1Copy1.setVersion(version);
        return jcwyCopy1Copy1;
    }

    /*新增步骤先去copy1表查询基准值为1的信息,然后查询该条信息的各个元素的7天平均数,之后根据这个平局数做差计算顶层的数据*/
    public JcwyCopy1Copy1 selectjzxxsjgls1(String bbh, String tml, String tal, HashMap<String, String> shm) throws ParseException {
        JcwyCopy1Copy1 jcwyCopy1Copy1 = new JcwyCopy1Copy1();
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.eq("jzzaniu", "1");
        jqw.eq("version", bbh);
        List<JcwyCopy1> jc1s = jcwyCopy1Mapper.selectList(jqw);
        JcwyCopy1 jcwyCopy1 = jc1s.get(0);
        String distributorName = jcwyCopy1.getDistributorName();
        String version = jcwyCopy1.getVersion();
        QueryWrapper<JtbizhbCopy1> j1qw = new QueryWrapper<>();
        j1qw.eq("gysmc", distributorName);
        List<JtbizhbCopy1> jt1s = jtbizhbCopy1Mapper.selectList(j1qw);
        JtbizhbCopy1 jtbizhbCopy1 = jt1s.get(0);
        jcwyCopy1Copy1.setDistributorName(distributorName);
        jcwyCopy1Copy1.setAd(jtbizhbCopy1.getAd());
        jcwyCopy1Copy1.setVdaf(jtbizhbCopy1.getVdaf());
        jcwyCopy1Copy1.setFcad(jtbizhbCopy1.getFcad());
        jcwyCopy1Copy1.setStd(jtbizhbCopy1.getStd());
        jcwyCopy1Copy1.setM25(jtbizhbCopy1.getM25());
        jcwyCopy1Copy1.setCri(jtbizhbCopy1.getCri());
        jcwyCopy1Copy1.setCsr(jtbizhbCopy1.getCsr());
        jcwyCopy1Copy1.setMt(jtbizhbCopy1.getMt());
        jcwyCopy1Copy1.setK(jtbizhbCopy1.getK());
        jcwyCopy1Copy1.setNa(jtbizhbCopy1.getNa());
        jcwyCopy1Copy1.setZn(jtbizhbCopy1.getZn());
        //HashMap<String, String> jsqtpjz = jsqtpjz(distributorName, version);
        //常规表根据区间查询平均值
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sd1.parse(tml);
        Date d2 = sd1.parse(tal);
        int i1 = daysBetween(d1, d2);
        HashMap<String, String> jsqtpjz = jsqtpjz1rs(distributorName, tal, i1, shm);
        if (jsqtpjz.containsKey("Ad")) {
            jcwyCopy1Copy1.setAd(jsqtpjz.get("Ad"));
        }
        if (jsqtpjz.containsKey("Vdaf")) {
            jcwyCopy1Copy1.setVdaf(jsqtpjz.get("Vdaf"));
        }
        if (jsqtpjz.containsKey("Fcad")) {
            jcwyCopy1Copy1.setFcad(jsqtpjz.get("Fcad"));
        }
        if (jsqtpjz.containsKey("Std")) {
            jcwyCopy1Copy1.setStd(jsqtpjz.get("Std"));
        }
        if (jsqtpjz.containsKey("M25")) {
            jcwyCopy1Copy1.setM25(jsqtpjz.get("M25"));
        }
        if (jsqtpjz.containsKey("CRI")) {
            jcwyCopy1Copy1.setCri(jsqtpjz.get("CRI"));
        }
        if (jsqtpjz.containsKey("CSR")) {
            jcwyCopy1Copy1.setCsr(jsqtpjz.get("CSR"));
        }
        if (jsqtpjz.containsKey("Mt")) {
            jcwyCopy1Copy1.setMt(jsqtpjz.get("Mt"));
        }
        if (jsqtpjz.containsKey("K2O")) {
            jcwyCopy1Copy1.setK(jsqtpjz.get("K2O"));
        }
        if (jsqtpjz.containsKey("Na2O")) {
            jcwyCopy1Copy1.setNa(jsqtpjz.get("Na2O"));
        }
        if (jsqtpjz.containsKey("ZnO")) {
            jcwyCopy1Copy1.setZn(jsqtpjz.get("ZnO"));
        }
        jcwyCopy1Copy1.setDistributorName(distributorName);
        jcwyCopy1Copy1.setVersion(version);
        return jcwyCopy1Copy1;
    }

    //根据日期查询非常规数据 k2O对应k Na2O对应na ZnO对应zn
    public HashMap<String, String> selectfcgtys(String bbh) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat();
        String tals[] = {"k2O", "Na2O", "ZnO"};
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
        calendar.add(Calendar.DATE, -7);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        for (int i = 0; i < tals.length; i++) {
            QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
            unconventionalityQueryWrapper.eq("item", tals[i]);
            unconventionalityQueryWrapper.between("date", daytose, bbh);
            unconventionalityQueryWrapper.like("number", "fs");
            List<Unconventionality> unconventionalities = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
            ArrayList<Double> doubles = new ArrayList<>();
            Double sum = 0.0;
            for (int i1 = 0; i1 < unconventionalities.size(); i1++) {
                Double aDouble = Double.valueOf(unconventionalities.get(i1).getPrice());
                sum = sum + aDouble;
            }
            if (sum != 0.0) {
                stringStringHashMap.put(tals[i], decimalFormat.format(sum / unconventionalities.size()));
            }
        }
        return stringStringHashMap;
    }

    //根据名称和版本号查询七天的元素平均值
    public HashMap<String, String> jsqtpjz(String gysmc, String bbh, Map<String, String> shm) {
        HashMap<String, String> stringDoubleHashMap = new HashMap<>();
//      爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
        calendar.add(Calendar.DATE, -7);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
        jtgrsQueryWrapper.between("jjrq", daytose, bbh);
        if (gysmc.indexOf("/") != -1) {
            jtgrsQueryWrapper.eq("gysmc", gysmc.split("/")[0]);
            jtgrsQueryWrapper.eq("fhdd", gysmc.split("/")[1]);
        } else {
            jtgrsQueryWrapper.eq("gysmc", gysmc);
            jtgrsQueryWrapper.eq("fhdd", "");
        }
        jtgrsQueryWrapper.ne("ylmc", "喷吹无烟煤");
        jtgrsQueryWrapper.ne("ylmc", "喷吹烟煤");
        List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
        DecimalFormat df = new DecimalFormat("0.00");
        Double tad = 0.00;
        int sum = 0;
        ArrayList<Double> Ad = new ArrayList<>();
        ArrayList<Double> Vdaf = new ArrayList<>();
        ArrayList<Double> Fcad = new ArrayList<>();
        ArrayList<Double> Std = new ArrayList<>();
        ArrayList<Double> M25 = new ArrayList<>();
        ArrayList<Double> CRI = new ArrayList<>();
        ArrayList<Double> CSR = new ArrayList<>();
        ArrayList<Double> Mt = new ArrayList<>();
        ArrayList<Double> sjzl = new ArrayList<>();
        ArrayList<Double> k = new ArrayList<>();
        ArrayList<Double> na = new ArrayList<>();
        ArrayList<Double> zn = new ArrayList<>();
        for (int i = 0; i < jtgrs.size(); i++) {
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getAd())) {
                Ad.add(Double.valueOf(jtgrs.get(i).getAd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getVdaf())) {
                Vdaf.add(Double.valueOf(jtgrs.get(i).getVdaf()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getFcad())) {
                Fcad.add(Double.valueOf(jtgrs.get(i).getFcad()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getStD())) {
                Std.add(Double.valueOf(jtgrs.get(i).getStD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getM25())) {
                M25.add(Double.valueOf(jtgrs.get(i).getM25()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getCri())) {
                CRI.add(Double.valueOf(jtgrs.get(i).getCri()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getCsr())) {
                CSR.add(Double.valueOf(jtgrs.get(i).getCsr()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMt())) {
                Mt.add(Double.valueOf(jtgrs.get(i).getMt()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getSjzl())) {
                sjzl.add(Double.valueOf(jtgrs.get(i).getSjzl()));
            }
            if (!ObjectUtils.isEmpty(shm.get(jtgrs.get(i).getPh()))) {
                k.add(Double.valueOf(shm.get(jtgrs.get(i).getPh())));
            }
        }
        Double Ad1 = 0.00;
        Double Vdaf1 = 0.00;
        Double Fcad1 = 0.00;
        Double Std1 = 0.00;
        Double M251 = 0.00;
        Double CRI1 = 0.00;
        Double CSR1 = 0.00;
        Double Mt1 = 0.00;
        Double sjzl1 = 0.00;
        Double k1 = 0.0;
        Double na1 = 0.0;
        Double zn1 = 0.0;
        for (int i = 0; i < k.size(); i++) {
            k1 = k1 + k.get(i);
        }
        for (int i = 0; i < Ad.size(); i++) {
            Ad1 = Ad1 + Ad.get(i);
        }
        for (int i = 0; i < Vdaf.size(); i++) {
            Vdaf1 = Vdaf1 + Vdaf.get(i);
        }
        for (int i = 0; i < Fcad.size(); i++) {
            Fcad1 = Fcad1 + Fcad.get(i);
        }
        for (int i = 0; i < Std.size(); i++) {
            Std1 = Std1 + Std.get(i);
        }
        for (int i = 0; i < M25.size(); i++) {
            M251 = M251 + M25.get(i);
        }
        for (int i = 0; i < CRI.size(); i++) {
            CRI1 = CRI1 + CRI.get(i);
        }
        for (int i = 0; i < CSR.size(); i++) {
            CSR1 = CSR1 + CSR.get(i);
        }
        for (int i = 0; i < Mt.size(); i++) {
            Mt1 = Mt1 + Mt.get(i);
        }
        for (int i = 0; i < sjzl.size(); i++) {
            sjzl1 = sjzl1 + sjzl.get(i);
        }
        if (Ad1 != 0.0) {
            stringDoubleHashMap.put("Ad", df.format(Ad1 / Ad.size()));
        }
        if (Vdaf1 != 0.0) {
            stringDoubleHashMap.put("Vdaf", df.format(Vdaf1 / Vdaf.size()));
        }
        if (Fcad1 != 0.0) {
            stringDoubleHashMap.put("Fcad", df.format(Fcad1 / Fcad.size()));
        }
        if (Std1 != 0.0) {
            stringDoubleHashMap.put("Std", df.format(Std1 / Std.size()));
        }
        if (M251 != 0.0) {
            stringDoubleHashMap.put("M25", df.format(M251 / M25.size()));
        }
        if (CRI1 != 0.0) {
            stringDoubleHashMap.put("CRI", df.format(CRI1 / M25.size()));
        }
        if (CSR1 != 0.0) {
            stringDoubleHashMap.put("CSR", df.format(CSR1 / CSR.size()));
        }
        if (Mt1 != 0.0) {
            stringDoubleHashMap.put("Mt", df.format(Mt1 / Mt.size()));
        }
        if (sjzl1 != 0.0) {
            stringDoubleHashMap.put("sjzl", df.format(sjzl1));
        }
        if (k1 != 0.0) {
            stringDoubleHashMap.put("K2O", df.format(k1 / k.size()));
        }
        return stringDoubleHashMap;
    }

    /**
     * 区间的非常规元素平均值(新)
     */
    public HashMap<String, String> selectfcgtys(String bbh, int i1s) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat();
        String tals[] = {"k2O", "Na2O", "ZnO"};
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
        calendar.add(Calendar.DATE, -i1s);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        for (int i = 0; i < tals.length; i++) {
            QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
            unconventionalityQueryWrapper.eq("item", tals[i]);
            unconventionalityQueryWrapper.between("date", daytose, bbh);
            unconventionalityQueryWrapper.like("number", "fs");
            List<Unconventionality> unconventionalities = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
            ArrayList<Double> doubles = new ArrayList<>();
            Double sum = 0.0;
            for (int i1 = 0; i1 < unconventionalities.size(); i1++) {
                Double aDouble = Double.valueOf(unconventionalities.get(i1).getPrice());
                sum = sum + aDouble;
            }
            if (sum != 0.0) {
                stringStringHashMap.put(tals[i], decimalFormat.format(sum / unconventionalities.size()));
            }
        }
        return stringStringHashMap;
    }

    /**
     * 根据fs码查询非常规元素(新)
     *
     * @param bbh
     * @param i1s
     * @return
     */
    public HashMap<String, String> selectfcgtys1rs(String bbh) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
        calendar.add(Calendar.DATE, -7);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.between("date", daytose, bbh);
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> unconventionalities = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        for (int i = 0; i < unconventionalities.size(); i++) {

        }
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        return stringStringHashMap;
    }

    /**
     * 区间的常规元素平均值(新)
     *
     * @return
     */
    public HashMap<String, String> jsqtpjz1rs(String gysmc, String tal, int rqc, HashMap<String, String> shm) {
        //selectfcgtys1rs
        HashMap<String, String> stringDoubleHashMap = new HashMap<>();
//      爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(tal.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(tal.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(tal.substring(8, 10)));
        calendar.add(Calendar.DATE, -rqc);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
        jtgrsQueryWrapper.between("jjrq", daytose, tal);
        if (gysmc.indexOf("/") != -1) {
            jtgrsQueryWrapper.eq("gysmc", gysmc.split("/")[0]);
            jtgrsQueryWrapper.eq("fhdd", gysmc.split("/")[1]);
        } else {
            jtgrsQueryWrapper.eq("gysmc", gysmc.split("/")[0]);
        }
        jtgrsQueryWrapper.ne("ylmc", "喷吹无烟煤");
        jtgrsQueryWrapper.ne("ylmc", "喷吹烟煤");
        List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
        DecimalFormat df = new DecimalFormat("0.00");
        Double tad = 0.00;
        int sum = 0;
        ArrayList<Double> Ad = new ArrayList<>();
        ArrayList<Double> Vdaf = new ArrayList<>();
        ArrayList<Double> Fcad = new ArrayList<>();
        ArrayList<Double> Std = new ArrayList<>();
        ArrayList<Double> M25 = new ArrayList<>();
        ArrayList<Double> CRI = new ArrayList<>();
        ArrayList<Double> CSR = new ArrayList<>();
        ArrayList<Double> Mt = new ArrayList<>();
        ArrayList<Double> sjzl = new ArrayList<>();
        ArrayList<Double> k = new ArrayList<>();
        ArrayList<Double> na = new ArrayList<>();
        ArrayList<Double> zn = new ArrayList<>();
        for (int i = 0; i < jtgrs.size(); i++) {
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getAd())) {
                Ad.add(Double.valueOf(jtgrs.get(i).getAd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getVdaf())) {
                Vdaf.add(Double.valueOf(jtgrs.get(i).getVdaf()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getFcad())) {
                Fcad.add(Double.valueOf(jtgrs.get(i).getFcad()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getStD())) {
                Std.add(Double.valueOf(jtgrs.get(i).getStD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getM25())) {
                M25.add(Double.valueOf(jtgrs.get(i).getM25()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getCri())) {
                CRI.add(Double.valueOf(jtgrs.get(i).getCri()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getCsr())) {
                CSR.add(Double.valueOf(jtgrs.get(i).getCsr()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMt())) {
                Mt.add(Double.valueOf(jtgrs.get(i).getMt()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getSjzl())) {
                sjzl.add(Double.valueOf(jtgrs.get(i).getSjzl()));
            }
            //  HashMap<String, String> stringStringHashMap = selectfcgtys1rs(jtgrs.get(i).getPh());
            if (!ObjectUtils.isEmpty(shm.get(jtgrs.get(i).getPh()))) {
                k.add(Double.valueOf(shm.get(jtgrs.get(i).getPh())));
            }
        }
        Double Ad1 = 0.00;
        Double Vdaf1 = 0.00;
        Double Fcad1 = 0.00;
        Double Std1 = 0.00;
        Double M251 = 0.00;
        Double CRI1 = 0.00;
        Double CSR1 = 0.00;
        Double Mt1 = 0.00;
        Double sjzl1 = 0.00;
        Double k1 = 0.0;
        for (int i = 0; i < k.size(); i++) {
            k1 = k1 + k.get(i);
        }
        for (int i = 0; i < Ad.size(); i++) {
            Ad1 = Ad1 + Ad.get(i);
        }
        for (int i = 0; i < Vdaf.size(); i++) {
            Vdaf1 = Vdaf1 + Vdaf.get(i);
        }
        for (int i = 0; i < Fcad.size(); i++) {
            Fcad1 = Fcad1 + Fcad.get(i);
        }
        for (int i = 0; i < Std.size(); i++) {
            Std1 = Std1 + Std.get(i);
        }
        for (int i = 0; i < M25.size(); i++) {
            M251 = M251 + M25.get(i);
        }
        for (int i = 0; i < CRI.size(); i++) {
            CRI1 = CRI1 + CRI.get(i);
        }
        for (int i = 0; i < CSR.size(); i++) {
            CSR1 = CSR1 + CSR.get(i);
        }
        for (int i = 0; i < Mt.size(); i++) {
            Mt1 = Mt1 + Mt.get(i);
        }
        for (int i = 0; i < sjzl.size(); i++) {
            sjzl1 = sjzl1 + sjzl.get(i);
        }
        if (Ad1 != 0.0) {
            stringDoubleHashMap.put("Ad", df.format(Ad1 / Ad.size()));
        }
        if (Vdaf1 != 0.0) {
            stringDoubleHashMap.put("Vdaf", df.format(Vdaf1 / Vdaf.size()));
        }
        if (Fcad1 != 0.0) {
            stringDoubleHashMap.put("Fcad", df.format(Fcad1 / Fcad.size()));
        }
        if (Std1 != 0.0) {
            stringDoubleHashMap.put("Std", df.format(Std1 / Std.size()));
        }
        if (M251 != 0.0) {
            stringDoubleHashMap.put("M25", df.format(M251 / M25.size()));
        }
        if (CRI1 != 0.0) {
            stringDoubleHashMap.put("CRI", df.format(CRI1 / M25.size()));
        }
        if (CSR1 != 0.0) {
            stringDoubleHashMap.put("CSR", df.format(CSR1 / CSR.size()));
        }
        if (Mt1 != 0.0) {
            stringDoubleHashMap.put("Mt", df.format(Mt1 / Mt.size()));
        }
        if (sjzl1 != 0.0) {
            stringDoubleHashMap.put("sjzl", df.format(sjzl1));
        }
        if (k1 != 0.0) {
            stringDoubleHashMap.put("K2O", df.format(k1 / k.size()));
        }
        return stringDoubleHashMap;
    }

    //基准值查询
    @RequestMapping("selectjzz")
    public String selectjzz() {
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "基准值修改");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        return butvalus.get(0).getPropertyValue();
    }

    //基准值修改
    @RequestMapping("updatejzz")
    public String updatejzz(String jzzxg) {
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "基准值修改");
        Butvalu butvalu = new Butvalu();
        butvalu.setPropertyValue(jzzxg);
        butvaluMapper.update(butvalu, butvaluQueryWrapper);
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        String propertyValue = butvalus.get(0).getPropertyValue();
        return propertyValue;
    }

    /**
     * @param lieseletone(元素名称)
     * @param distributorNames(供应商名称) 历史弹窗柱状图数据(根据元素和供应商名称查询)
     */
    @RequestMapping("selectsixmtachr")
    public Object selectsixmtachr(String lieseletone, String distributorNames) {
        String ta[] = {"Ad", "Vdaf", "Fcad", "Std", "M25", "CRI", "CSR", "Mt"};
        String tb[] = {"K", "Na", "Zn"};
        String a[] = new String[6];
        String b[] = new String[6];
        for (int i = 0; i < a.length; i++) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //获取前月的第一天
            Calendar cal_1 = Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.MONTH, -i);
            cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String firstDay = format.format(cal_1.getTime());
            a[i] = firstDay;
            //获取前月的最后一天
            //获取当前月第一天：
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -i + 1);
            c.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
            String lastDay = format.format(c.getTime());
            b[i] = lastDay;
        }
        ArrayList<Map<String, String>> clist = new ArrayList<>();
        List<String> strings = Arrays.asList(ta);
        for (int i = 0; i < a.length; i++) {
            HashMap<String, String> sshm = new HashMap<>();
            QueryWrapper<Jtgrs> jqw = new QueryWrapper<>();
            jqw.between("jjrq", a[i], b[i]);
            jqw.like("gysmc", distributorNames);
            List<Jtgrs> jtgrs = jtgrsMapper.selectList(jqw);
            String gallcrs = "";
            if (ObjectUtils.isEmpty(jtgrs)) {
                sshm.put("cme", gallcrs);
                sshm.put("cme1", a[i]);
                clist.add(sshm);
                continue;
            }
            switch (lieseletone) {
                case "K":
                    gallcrs = jtgrs.get(0).getAd();
                    break;
                case "Vdaf":
                    gallcrs = jtgrs.get(0).getVdaf();
                    break;
                case "Fcad":
                    gallcrs = jtgrs.get(0).getFcad();
                    break;
                case "Std":
                    gallcrs = jtgrs.get(0).getStD();
                    break;
                case "M25":
                    gallcrs = jtgrs.get(0).getM25();
                    break;
                case "CRI":
                    gallcrs = jtgrs.get(0).getCri();
                    break;
                case "CSR":
                    gallcrs = jtgrs.get(0).getCsr();
                    break;
                case "Mt":
                    gallcrs = jtgrs.get(0).getMt();
                    break;
                default:
                    break;

            }
            sshm.put("cme", gallcrs);
            sshm.put("cme1", a[i]);
            clist.add(sshm);
        }
        return clist;
    }

    /**
     * 饼图数据查询
     *
     * @param bbh(版本号) jcwy_copy1
     */
    @RequestMapping("selectbtcopy1")
    public HashMap<String, String> selectbtcopy1(String bbh) {
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.eq("version", bbh);
        List<JcwyCopy1> jcwyCopy1s = jcwyCopy1Mapper.selectList(jqw);
        //总和
        Double zh = 0.0;
        //山西
        Double sx = 0.0;
        //内蒙
        Double nm = 0.0;
        //黑龙江
        Double hlj = 0.0;
        //辽宁
        Double ln = 0.0;
        //吉林
        Double jl = 0.0;
        //河北
        Double hb = 0.0;
        //进口
        Double jk = 0.0;
        HashMap<String, String> stmp = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            switch (jcwyCopy1s.get(i).getGsdgils()) {
                case ("山西"):
                    sx = sx + 1;
                    break;
                case ("内蒙"):
                    nm = nm + 1;
                    break;
                case ("黑龙江"):
                    hlj = hlj + 1;
                    break;
                case ("辽宁"):
                    ln = ln + 1;
                    break;
                case ("吉林"):
                    jl = jl + 1;
                    break;
                case ("河北"):
                    hb = hb + 1;
                    break;
                case ("进口"):
                    jk = jk + 1;
                    break;
                default:
                    break;
            }
        }
        Double tmail = sx + nm + hlj + ln + jl + hb + jk;
        stmp.put("sx", df.format(sx / tmail * 100));
        stmp.put("nm", df.format(nm / tmail * 100));
        stmp.put("hlj", df.format(hlj / tmail * 100));
        stmp.put("ln", df.format(ln / tmail * 100));
        stmp.put("jl", df.format(jl / tmail * 100));
        stmp.put("hb", df.format(hb / tmail * 100));
        stmp.put("jk", df.format(jk / tmail * 100));
        return stmp;
    }

    /**
     * 饼图数据查询
     *
     * @param bbh(区间) jcwy_copy1
     */
    @RequestMapping("selectbtcopy1qj")
    public HashMap<String, String> selectbtcopy1qj(String tml, String tal) {
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.between("version", tml, tal);
        List<JcwyCopy1> jcwyCopy1s = jcwyCopy1Mapper.selectList(jqw);
        //总和
        Double zh = 0.0;
        //山西
        Double sx = 0.0;
        //内蒙
        Double nm = 0.0;
        //黑龙江
        Double hlj = 0.0;
        //辽宁
        Double ln = 0.0;
        //吉林
        Double jl = 0.0;
        //河北
        Double hb = 0.0;
        //进口
        Double jk = 0.0;
        HashMap<String, String> stmp = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            if (ObjectUtils.isEmpty(jcwyCopy1s.get(i).getGsdgils())) {
                continue;
            }
            switch (jcwyCopy1s.get(i).getGsdgils()) {
                case ("山西"):
                    sx = sx + 1;
                    break;
                case ("内蒙"):
                    nm = nm + 1;
                    break;
                case ("黑龙江"):
                    hlj = hlj + 1;
                    break;
                case ("辽宁"):
                    ln = ln + 1;
                    break;
                case ("吉林"):
                    jl = jl + 1;
                    break;
                case ("河北"):
                    hb = hb + 1;
                    break;
                case ("进口"):
                    jk = jk + 1;
                    break;
                default:
                    break;
            }
        }
        Double tmail = sx + nm + hlj + ln + jl + hb + jk;
        String sx1 = "";
        String nm1 = "";
        String hlj1 = "";
        String ln1 = "";
        String jl1 = "";
        String hb1 = "";
        String jk1 = "";
        stmp.put("sx", sx1 = df.format(sx / tmail * 100).equals("�") ? "0" : df.format(sx / tmail * 100));
        stmp.put("nm", nm1 = df.format(nm / tmail * 100).equals("�") ? "0" : df.format(nm / tmail * 100));
        stmp.put("hlj", hlj1 = df.format(hlj / tmail * 100).equals("�") ? "0" : df.format(hlj / tmail * 100));
        stmp.put("ln", ln1 = df.format(ln / tmail * 100).equals("�") ? "0" : df.format(ln / tmail * 100));
        stmp.put("jl", jl1 = df.format(jl / tmail * 100).equals("�") ? "0" : df.format(jl / tmail * 100));
        stmp.put("hb", hb1 = df.format(hb / tmail * 100).equals("�") ? "0" : df.format(hb / tmail * 100));
        stmp.put("jk", jk1 = df.format(jk / tmail * 100).equals("�") ? "0" : df.format(jk / tmail * 100));
        return stmp;
    }

    /**
     * 删除版本号信息
     */
    @RequestMapping("deletebbhintersns")
    public void deletebbhintersns(String bbh) {
        QueryWrapper<JcwyCopy1> jqw = new QueryWrapper<>();
        jqw.eq("version", bbh);
        jcwyCopy1Mapper.delete(jqw);
    }
    /**
     * 焦炭测算的燃料类型的标准值
     */
//    @RequestMapping("bzzxxgls")
//    public List<Map<String, Object>> bzzxxgls(){
//        List<Tamplante> tamplantes = tamplanteMapper.selectList(null);
//        ArrayList<Map<String, Object>> maps = new ArrayList<>();
//        for (int i = 0; i < tamplantes.size(); i++) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("id", tamplantes.get(i).getId());
//            hashMap.put("item", tamplantes.get(i).getItems());
//            hashMap.put("price1", tamplantes.get(i).getPrices1());
//            hashMap.put("price2", tamplantes.get(i).getPrices2());
//            maps.add(hashMap);
//        }
//        return maps;
//    }
    /**
     * 焦炭测算修改燃料类型的标准值
     */
//    @RequestMapping("updatebzzxxgls")
//    public void updatebzzxxgls(String bzlist){
//        JSONArray jsonArray = JSONArray.parseArray(bzlist);
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject o = (JSONObject) jsonArray.get(i);
//            Integer id = new Integer((Integer) o.get("id"));
//            Tamplante tamplante = new Tamplante();
//            tamplante.setItems((String) o.get("item"));
//            tamplante.setPrices1((String) o.get("price1"));
//            tamplante.setPrices2((String) o.get("price2"));
//            QueryWrapper<Tamplante> bq = new QueryWrapper<>();
//            bq.eq("id", id);
//            tamplanteMapper.update(tamplante,bq);
//        }
//    }

    /**
     * 初始化查询焦炭顶层标准维护
     */
    @RequestMapping("selectjtdcxxwh")
    public List<Tamplante> selectjtdcxxwh() {
        List<Tamplante> tamplantes = tamplanteMapper.selectList(null);
        return tamplantes;
    }

    /**
     * 焦炭顶层标准维护
     */
    @RequestMapping("updatealltojtjcsjrs")
    public List<Tamplante> updatealltojtjcsjrs(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Tamplante jc1 = new Tamplante();
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setFcad(jsonObject.getString("fcad"));
            jc1.setStd(jsonObject.getString("std"));
            jc1.setM25(jsonObject.getString("m25"));
            jc1.setCri(jsonObject.getString("cri"));
            jc1.setCsr(jsonObject.getString("csr"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setK(jsonObject.getString("k"));
            jc1.setNa(jsonObject.getString("na"));
            jc1.setZn(jsonObject.getString("zn"));
            QueryWrapper<Tamplante> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            tamplanteMapper.update(jc1, jqw);
        }
        List<Tamplante> jp1s = tamplanteMapper.selectList(null);
        return jp1s;
    }

    /**
     * 无烟煤顶层维护
     *
     * @param distributor_name
     * @param sterilise
     * @param contract_price
     * @param month_amount
     * @param jssf
     * @param jsfm
     * @return
     */
    @RequestMapping("insertwymdcwh")
    public List<Wymdcwh> insertwymdcwh(String distributor_name, String sterilise, String contract_price,
                                       String month_amount, String jssf, String jsfm) {
        QueryWrapper<Wymdcwh> jqw = new QueryWrapper<>();
        ArrayList<Wymdcwh> jcwies1 = new ArrayList<>();
        jqw.eq("rllx", "无烟煤");
        jqw.eq("distributor_name", distributor_name);
        jqw.eq("sterilise", sterilise);
        jqw.eq("contract_price", contract_price);
        jqw.eq("month_amount", month_amount);
        jqw.eq("jssf", jssf);
        jqw.eq("jsfm", jsfm);
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(jqw);
        if (!ObjectUtils.isEmpty(jcwies)) {
            return jcwies1;
        }
        Wymdcwh jcwy = new Wymdcwh();
        jcwy.setRllx("无烟煤");
        jcwy.setDistributorName(distributor_name);
        jcwy.setSterilise(sterilise);
        jcwy.setContractPrice(contract_price);
        jcwy.setMonthAmount(month_amount);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        wymdcwhMapper.insert(jcwy);
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "无烟煤");
        List<Wymdcwh> jcwies2 = wymdcwhMapper.selectList(wqw);
        return jcwies2;
    }

    //   初始化查询无烟煤维护信息
    @RequestMapping("selectwymdccx")
    public List<Wymdcwh> selectwymdccx() {
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "无烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqw);
        return jcwies;
    }

    //   初始化查询烟煤维护信息
    @RequestMapping("selectymdccx")
    public List<Wymdcwh> selectymdccx() {
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqw);
        return jcwies;
    }

    /**
     * 无烟煤维护页价格同加
     *
     * @param tabli
     * @param htjgzj
     * @return
     */
    @RequestMapping("updatewymdcwhprice")
    public List<Wymdcwh> updatewymdcwhprice(String tabli, String htjgzj) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgzj);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Wymdcwh jcwy = new Wymdcwh();
            jcwy.setContractPrice(String.valueOf(b[i] + zjjg));
            wymdcwhMapper.update(jcwy, lw);

        }
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "无烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqw);
        return jcwies;
    }

    /**
     * 合同价格同减
     *
     * @param tabli
     * @param htjgjs
     * @return
     */
    @RequestMapping("updatetjwym")
    public List<Wymdcwh> updatetjwym(String tabli, String htjgjs) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgjs);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Wymdcwh jcwy = new Wymdcwh();
            jcwy.setContractPrice(String.valueOf(b[i] - zjjg));
            wymdcwhMapper.update(jcwy, lw);
        }
        QueryWrapper<Wymdcwh> wqq = new QueryWrapper<>();
        wqq.eq("rllx", "无烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqq);
        return jcwies;
    }

    /**
     * 无烟煤顶层维护页修改信息
     *
     * @param gysmc
     * @param htjg
     * @param yjhl
     * @param fkfs
     * @param jssf
     * @param jsfm
     * @param id
     * @return
     */
    @RequestMapping("updatewymwhxg")
    public List<Wymdcwh> updatewymwhxg(String gysmc, String htjg, String yjhl, String fkfs, String jssf, String jsfm, String id) {
        Wymdcwh jcwy = new Wymdcwh();
        jcwy.setDistributorName(gysmc);
        jcwy.setSterilise(fkfs);
        jcwy.setContractPrice(htjg);
        jcwy.setMonthAmount(yjhl);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
        lw.eq("id", id);
        int update = wymdcwhMapper.update(jcwy, lw);
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(null);
        return jcwies;
    }

    /**
     * 删除供应商
     *
     * @param gysmc
     */
    @RequestMapping("deletewymgysmc")
    public void deletewymgysmc(String gysmc) {
        QueryWrapper<Butvalu> bqw = new QueryWrapper<>();
        bqw.eq("attribute_name", "无烟煤名称");
        bqw.eq("property_value", gysmc);
        butvaluMapper.delete(bqw);
    }

    /**
     * 删除无烟煤顶层维护信息
     *
     * @param id
     * @return
     */
    @RequestMapping("deletewymdcwhxxy")
    public List<Wymdcwh> deletewymdcwhxxy(String id) {
        QueryWrapper<Wymdcwh> wymdcwhQueryWrapper = new QueryWrapper<>();
        wymdcwhQueryWrapper.eq("id", id);
        List<Wymdcwh> wymdcwhs1 = wymdcwhMapper.selectList(wymdcwhQueryWrapper);
        Wymdcwh wymdcwh = wymdcwhs1.get(0);
        wymdcwhMapper.delete(wymdcwhQueryWrapper);
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "无烟煤名称");
        butvaluQueryWrapper.eq("property_value", wymdcwh.getDistributorName());
        butvaluMapper.delete(butvaluQueryWrapper);
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "无烟煤");
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(wqw);
        return wymdcwhs;
    }

    /**
     * 烟煤顶层维护
     *
     * @param distributor_name
     * @param sterilise
     * @param contract_price
     * @param month_amount
     * @param jssf
     * @param jsfm
     * @return
     */
    @RequestMapping("insertymdcwh")
    public List<Wymdcwh> insertymdcwh(String distributor_name, String sterilise, String contract_price,
                                      String month_amount, String jssf, String jsfm) {
        QueryWrapper<Wymdcwh> jqw = new QueryWrapper<>();
        ArrayList<Wymdcwh> jcwies1 = new ArrayList<>();
        jqw.eq("rllx", "烟煤");
        jqw.eq("distributor_name", distributor_name);
        jqw.eq("sterilise", sterilise);
        jqw.eq("contract_price", contract_price);
        jqw.eq("month_amount", month_amount);
        jqw.eq("jssf", jssf);
        jqw.eq("jsfm", jsfm);
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(jqw);
        if (!ObjectUtils.isEmpty(jcwies)) {
            return jcwies1;
        }
        Wymdcwh jcwy = new Wymdcwh();
        jcwy.setRllx("烟煤");
        jcwy.setDistributorName(distributor_name);
        jcwy.setSterilise(sterilise);
        jcwy.setContractPrice(contract_price);
        jcwy.setMonthAmount(month_amount);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        wymdcwhMapper.insert(jcwy);
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "烟煤");
        List<Wymdcwh> jcwies2 = wymdcwhMapper.selectList(wqw);
        return jcwies2;
    }

    /**
     * 烟煤维护页价格同加
     *
     * @param tabli
     * @param htjgzj
     * @return
     */
    @RequestMapping("updateymdcwhprice")
    public List<Wymdcwh> updateymdcwhprice(String tabli, String htjgzj) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgzj);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Wymdcwh jcwy = new Wymdcwh();
            jcwy.setContractPrice(String.valueOf(b[i] + zjjg));
            wymdcwhMapper.update(jcwy, lw);

        }
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqw);
        return jcwies;
    }

    /**
     * 烟煤价格同减
     *
     * @param tabli
     * @param htjgjs
     * @return
     */
    @RequestMapping("updatetjym")
    public List<Wymdcwh> updatetjym(String tabli, String htjgjs) {
        JSONArray jsonArray = JSONArray.parseArray(tabli);
        int a[] = new int[jsonArray.size()];
        Integer b[] = new Integer[a.length];
        Integer zjjg = Integer.valueOf(htjgjs);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int id = (int) jsonObject.get("id");
            a[i] = id;
            String ss = (String) jsonObject.get("contractPrice");
            if (ss.indexOf(" ") != -1) {
                ss = ss.replace(" ", "");
            }
            Integer contractPrice = Integer.valueOf(ss);
            b[i] = contractPrice;
        }
        for (int i = 0; i < a.length; i++) {
            QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
            lw.eq("id", a[i]);
            Wymdcwh jcwy = new Wymdcwh();
            jcwy.setContractPrice(String.valueOf(b[i] - zjjg));
            wymdcwhMapper.update(jcwy, lw);
        }
        QueryWrapper<Wymdcwh> wqq = new QueryWrapper<>();
        wqq.eq("rllx", "烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqq);
        return jcwies;
    }

    /**
     * 烟煤顶层维护页修改信息
     *
     * @param gysmc
     * @param htjg
     * @param yjhl
     * @param fkfs
     * @param jssf
     * @param jsfm
     * @param id
     * @return
     */
    @RequestMapping("updateymwhxg")
    public List<Wymdcwh> updateymwhxg(String gysmc, String htjg, String yjhl, String fkfs, String jssf, String jsfm, String id) {
        Wymdcwh jcwy = new Wymdcwh();
        jcwy.setDistributorName(gysmc);
        jcwy.setSterilise(fkfs);
        jcwy.setContractPrice(htjg);
        jcwy.setMonthAmount(yjhl);
        jcwy.setJssf(jssf);
        jcwy.setJsfm(jsfm);
        QueryWrapper<Wymdcwh> lw = new QueryWrapper<>();
        lw.eq("id", id);
        int update = wymdcwhMapper.update(jcwy, lw);
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "烟煤");
        List<Wymdcwh> jcwies = wymdcwhMapper.selectList(wqw);
        return jcwies;
    }

    /**
     * 删除烟煤顶层维护信息
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteymdcwhxxy")
    public List<Wymdcwh> deleteymdcwhxxy(String id) {
        QueryWrapper<Wymdcwh> wymdcwhQueryWrapper = new QueryWrapper<>();
        wymdcwhQueryWrapper.eq("id", id);
        List<Wymdcwh> wymdcwhs1 = wymdcwhMapper.selectList(wymdcwhQueryWrapper);
        Wymdcwh wymdcwh = wymdcwhs1.get(0);
        wymdcwhMapper.delete(wymdcwhQueryWrapper);
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "烟煤名称");
        butvaluQueryWrapper.eq("property_value", wymdcwh.getDistributorName());
        butvaluMapper.delete(butvaluQueryWrapper);
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "烟煤");
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(wqw);
        return wymdcwhs;
    }

    /**
     * 删除供应商
     *
     * @param gysmc
     */
    @RequestMapping("deleteymgysmc")
    public void deleteymgysmc(String gysmc) {
        QueryWrapper<Butvalu> bqw = new QueryWrapper<>();
        bqw.eq("attribute_name", "烟煤名称");
        bqw.eq("property_value", gysmc);
        butvaluMapper.delete(bqw);
    }

    /**
     * 焦炭基础信息维护页面
     *
     * @return
     */
    @RequestMapping("allinputsofjtwym")
    public List<Wymjc> allinputsofjtwym() {
        List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(null);
        return jtbizhbCopy1s;
    }

    /**
     * 无烟煤基础数据统一保存
     *
     * @param bzjhcsl
     * @return
     */
    @RequestMapping("updatealltojtjcsjwym")
    public List<Wymjc> updatealltojtjcsjwym(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Wymjc jc1 = new Wymjc();
            jc1.setGysmc(jsonObject.getString("gysmc"));
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setQgrD(jsonObject.getString("qgrD"));
            jc1.setStD(jsonObject.getString("stD"));
            jc1.setFcd(jsonObject.getString("fcd"));
            jc1.setMad(jsonObject.getString("mad"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setKo(jsonObject.getString("ko"));
            jc1.setNao(jsonObject.getString("nao"));
            jc1.setZno(jsonObject.getString("zno"));
            QueryWrapper<Wymjc> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            wymjcMapper.update(jc1, jqw);
        }
        List<Wymjc> jp1s = wymjcMapper.selectList(null);
        return jp1s;
    }

    /**
     * 无烟煤基础信息维护页面新增标准
     *
     * @param mc
     * @param TFe
     * @param CaO
     * @param Si02
     * @param MgO
     * @param Al2O3
     * @param Tio2
     * @param P
     * @param K2O
     * @param Na2O
     * @param ZnO
     * @param MnO
     * @return
     */
    @RequestMapping("insertnesjtbzwym")
    public List<Wymjc> insertnesjtbzwym(String mc, String TFe, String CaO, String Si02,
                                        String MgO, String Al2O3, String Tio2, String P, String K2O, String Na2O, String ZnO,
                                        String MnO) {
        Wymjc jtb = new Wymjc();
        jtb.setGysmc(mc);
        jtb.setAd(TFe);
        jtb.setStD(CaO);
        jtb.setQgrD(Si02);
        jtb.setVdaf(MgO);
        jtb.setFcd(Al2O3);
        jtb.setMt(Tio2);
        jtb.setMad(P);
        jtb.setKo(K2O);
        jtb.setNao(Na2O);
        jtb.setZno(ZnO);
        wymjcMapper.insert(jtb);
        List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(null);
        return jtbizhbCopy1s;
    }

    /**
     * 初始化查询无烟煤范围维护
     */
    @RequestMapping("selectjtdcxxwhwym")
    public List<Wymfw> selectjtdcxxwhwym() {
        List<Wymfw> tamplantes = wymfwMapper.selectList(null);
        return tamplantes;
    }

    /**
     * 无烟煤范围维护
     */
    @RequestMapping("updatealltojtjcsjrswym")
    public List<Wymfw> updatealltojtjcsjrswym(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Wymfw jc1 = new Wymfw();
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setStD(jsonObject.getString("stD"));
            jc1.setQgrD(jsonObject.getString("qgrD"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setFcd(jsonObject.getString("fcd"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setMad(jsonObject.getString("mad"));
            jc1.setKo(jsonObject.getString("ko"));
            jc1.setNao(jsonObject.getString("nao"));
            jc1.setZno(jsonObject.getString("zno"));
            QueryWrapper<Wymfw> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            wymfwMapper.update(jc1, jqw);
        }
        List<Wymfw> jp1s = wymfwMapper.selectList(null);
        return jp1s;
    }

    /**
     * 烟煤基础信息维护页面
     *
     * @return
     */
    @RequestMapping("allinputsofjtym")
    public List<Ymjc> allinputsofjtym() {
        List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(null);
        return jtbizhbCopy1s;
    }

    /**
     * 烟煤基础数据统一保存
     *
     * @param bzjhcsl
     * @return
     */
    @RequestMapping("updatealltojtjcsjym")
    public List<Ymjc> updatealltojtjcsjym(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Ymjc jc1 = new Ymjc();
            jc1.setGysmc(jsonObject.getString("gysmc"));
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setQgrD(jsonObject.getString("qgrD"));
            jc1.setStD(jsonObject.getString("stD"));
            jc1.setFcd(jsonObject.getString("fcd"));
            jc1.setMad(jsonObject.getString("mad"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setKo(jsonObject.getString("ko"));
            jc1.setNao(jsonObject.getString("nao"));
            jc1.setZno(jsonObject.getString("zno"));
            QueryWrapper<Ymjc> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            ymjcMapper.update(jc1, jqw);
        }
        List<Ymjc> jp1s = ymjcMapper.selectList(null);
        return jp1s;
    }

    /**
     * 烟煤基础信息维护页面新增标准
     *
     * @param mc
     * @param TFe
     * @param CaO
     * @param Si02
     * @param MgO
     * @param Al2O3
     * @param Tio2
     * @param P
     * @param K2O
     * @param Na2O
     * @param ZnO
     * @param MnO
     * @return
     */
    @RequestMapping("insertnesjtbzym")
    public List<Ymjc> insertnesjtbzym(String mc, String TFe, String CaO, String Si02,
                                      String MgO, String Al2O3, String Tio2, String P, String K2O, String Na2O, String ZnO,
                                      String MnO) {
        Ymjc jtb = new Ymjc();
        jtb.setGysmc(mc);
        jtb.setAd(TFe);
        jtb.setStD(CaO);
        jtb.setQgrD(Si02);
        jtb.setVdaf(MgO);
        jtb.setFcd(Al2O3);
        jtb.setMt(Tio2);
        jtb.setMad(P);
        jtb.setKo(K2O);
        jtb.setNao(Na2O);
        jtb.setZno(ZnO);
        ymjcMapper.insert(jtb);
        List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(null);
        return jtbizhbCopy1s;
    }

    /**
     * 初始化查询烟煤范围维护
     */
    @RequestMapping("selectjtdcxxwhym")
    public List<Ymfw> selectjtdcxxwhym() {
        List<Ymfw> tamplantes = ymfwMapper.selectList(null);
        return tamplantes;
    }

    /**
     * 烟煤范围维护
     */
    @RequestMapping("updatealltojtjcsjrsym")
    public List<Ymfw> updatealltojtjcsjrsym(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Ymfw jc1 = new Ymfw();
            jc1.setAd(jsonObject.getString("ad"));
            jc1.setStD(jsonObject.getString("stD"));
            jc1.setQgrD(jsonObject.getString("qgrD"));
            jc1.setVdaf(jsonObject.getString("vdaf"));
            jc1.setFcd(jsonObject.getString("fcd"));
            jc1.setMt(jsonObject.getString("mt"));
            jc1.setMad(jsonObject.getString("mad"));
            jc1.setKo(jsonObject.getString("ko"));
            jc1.setNao(jsonObject.getString("nao"));
            jc1.setZno(jsonObject.getString("zno"));
            QueryWrapper<Ymfw> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            ymfwMapper.update(jc1, jqw);
        }
        List<Ymfw> jp1s = ymfwMapper.selectList(null);
        return jp1s;
    }

    /**
     * @param idrs 数组 根据数组查询jcwy数据 然后加上时间信息存为版本号
     */
    //    焦炭维护页信息加上版本号存入数据库
    @RequestMapping("insertwymversions")
    public void insertwymversions(String idrs, String jzzaniu) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        JSONArray jsonArray = JSONArray.parseArray(idrs);
        for (int i = 0; i < jsonArray.size(); i++) {
            int id = (int) jsonArray.get(i);
            QueryWrapper<Wymdcwh> jw = new QueryWrapper<>();
            jw.eq("id", id);
            List<Wymdcwh> jcwies = wymdcwhMapper.selectList(jw);
            Wymdcwh jcwy = jcwies.get(0);
            WymdcwhCopy1 jcwyCopy1 = new WymdcwhCopy1();
            jcwyCopy1.setRllx("无烟煤");
            jcwyCopy1.setDistributorName(jcwy.getDistributorName());
            jcwyCopy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1.setJssf(jcwy.getJssf());
            jcwyCopy1.setJsfm(jcwy.getJsfm());
            jcwyCopy1.setVersion(format);
            wymdcwhCopy1Mapper.insert(jcwyCopy1);
        }
    }

    /**
     * 无烟煤全查询版本号
     */
    @RequestMapping("selectbehedwym")
    public List<String> selectbehedwym() {
        QueryWrapper<WymdcwhCopy1> jqw = new QueryWrapper<>();
        jqw.groupBy("version");
        List<WymdcwhCopy1> jc = wymdcwhCopy1Mapper.selectList(jqw);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < jc.size(); i++) {
            strings.add(jc.get(i).getVersion());
        }
        return strings;
    }

    /**
     * 无烟煤顶层测速
     *
     * @param bbh
     * @return
     */
//    @RequestMapping("cswym")
//    public void cswym(String bbh) {
//        //根据版本号查询当前版本号数据
//        QueryWrapper<WymdcwhCopy1> wymdcwhCopy1QueryWrapper = new QueryWrapper<>();
//        wymdcwhCopy1QueryWrapper.eq("version", bbh.substring(0, 10));
//        List<WymdcwhCopy1> wymdcwhCopy1s = wymdcwhCopy1Mapper.selectList(wymdcwhCopy1QueryWrapper);
//        //七天之内的数据
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
//        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
//        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
//        calendar.add(Calendar.DATE, -7);
//        Date dateTime = calendar.getTime();
//        String daytose = sdf.format(dateTime);
//        DecimalFormat df = new DecimalFormat("0.00");
//        //先查询非常规表中的所有数据存入map集合
//        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
//        //unconventionalityQueryWrapper.eq("item", "K2O");
//        unconventionalityQueryWrapper.between("date", daytose, bbh);
//        unconventionalityQueryWrapper.like("number", "fs");
//        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
//        HashMap<String, String> k20 = new HashMap<>();
//        HashMap<String, String> zno = new HashMap<>();
//        HashMap<String, String> na2o = new HashMap<>();
//        for (int i = 0; i < uci.size(); i++) {
//            switch (uci.get(i).getItem()) {
//                case "K2O":
//                    k20.put(uci.get(i).getNumber(), uci.get(i).getPrice());
//                    break;
//                case "ZnO":
//                    zno.put(uci.get(i).getNumber(), uci.get(i).getPrice());
//                    break;
//                case "Na2O":
//                    na2o.put(uci.get(i).getNumber(), uci.get(i).getPrice());
//                    break;
//                default:
//                    break;
//            }
//        }
//        for (int i = 0; i < wymdcwhCopy1s.size(); i++) {
//            WymCopy1 wymCopy1 = new WymCopy1();
//            QueryWrapper<Wymjc> wymjcQueryWrapper = new QueryWrapper<>();
//            wymjcQueryWrapper.eq("gysmc", wymdcwhCopy1s.get(i).getDistributorName());
//            List<Wymjc> wymjcs = wymjcMapper.selectList(wymjcQueryWrapper);
//            wymCopy1.setDistributorName(wymdcwhCopy1s.get(i).getDistributorName());
//            wymCopy1.setSterilise(wymdcwhCopy1s.get(i).getSterilise());
//            wymCopy1.setContractPrice(wymdcwhCopy1s.get(i).getContractPrice());
//            wymCopy1.setMonthAmount(wymdcwhCopy1s.get(i).getMonthAmount());
//            wymCopy1.setJssf(wymdcwhCopy1s.get(i).getJssf());
//            wymCopy1.setJsfm(wymdcwhCopy1s.get(i).getJsfm());
//            if (!ObjectUtils.isEmpty(wymjcs)) {
//                Wymjc wymjc = wymjcs.get(0);
//                wymCopy1.setAd(wymjc.getAd());
//                wymCopy1.setStD(wymjc.getStD());
//                wymCopy1.setQgrD(wymjc.getQgrD());
//                wymCopy1.setVdaf(wymjc.getVdaf());
//                wymCopy1.setFcd(wymjc.getFcd());
//                wymCopy1.setMt(wymjc.getMt());
//                wymCopy1.setMad(wymjc.getMad());
//                wymCopy1.setK(wymjc.getKo());
//                wymCopy1.setNa(wymjc.getNao());
//                wymCopy1.setZn(wymjc.getZno());
//            }
//            QueryWrapper<Gcilma> jtgrsnes = new QueryWrapper<>();
//            jtgrsnes.between("jjrq", "2022-03-01", "2022-03-10");
//            jtgrsnes.ne("sjzl", "");
//            jtgrsnes.eq("gysmc", "内蒙古力源煤炭有限公司");
//            jtgrsnes.groupBy("ph");
//            jtgrsnes.select("AVG(case when ad != '' and ad is not null then ad end) as ad ",
//                    "AVG(case when fcd != '' and fcd is not null then fcd end) as fcd",
//                    "AVG(case when mad != '' and mad is not null then mad end) as mad",
//                    "AVG(case when mt != '' and mt is not null then mt end) as mt",
//                    "AVG(case when qgr_d != '' and qgr_d is not null then qgr_d end) as qgr_d",
//                    "AVG(case when qnet_v_ar != '' and qnet_v_ar is not null then qnet_v_ar end) as qnet_v_ar",
//                    "AVG(case when st_d != '' and st_d is not null then st_d end) as st_d",
//                    "AVG(case when vdaf != '' and vdaf is not null then vdaf end) as vdaf",
//                    "sum(sjzl)", "ph");
//            List<Map<String, Object>> maps = gcilmaMapper.selectMaps(jtgrsnes);
//            for (int i1 = 0; i1 < maps.size(); i1++) {
//                Map<String, Object> stringObjectMap = maps.get(0);
//                String ph = (String) stringObjectMap.get("ph");
//
//            }
//        }
//
//    }
    @RequestMapping("selecttocopycopywym")
    public List<WymCopy1> selecttocopycopywym(String bbh, String tml, String tal) {
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.eq("date", bbh.substring(0, 10));
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        QueryWrapper<WymdcwhCopy1> jw = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(tml) && ObjectUtils.isEmpty(tal)) {
            jw.eq("version", bbh);
        } else {
            // HH:mm:ss
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                jw.between("version", sd.parse(tml), sd.parse(tal));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<WymdcwhCopy1> jcwyCopy1s = wymdcwhCopy1Mapper.selectList(jw);
        ArrayList<WymCopy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            WymCopy1 jcwyCopy1Copy1 = new WymCopy1();
            jcwyCopy1Copy1.setDistributorName(jcwyCopy1s.get(i).getDistributorName());
            jcwyCopy1Copy1.setSterilise(jcwyCopy1s.get(i).getSterilise());
            jcwyCopy1Copy1.setContractPrice(jcwyCopy1s.get(i).getContractPrice());
            jcwyCopy1Copy1.setMonthAmount(jcwyCopy1s.get(i).getMonthAmount());
            jcwyCopy1Copy1.setJssf(jcwyCopy1s.get(i).getJssf());
            jcwyCopy1Copy1.setYfjg(jcwyCopy1s.get(i).getJsfm());
            jcwyCopy1Copy1.setVersion(jcwyCopy1s.get(i).getVersion());
            jcwyCopy1Copy1.setCcjg(jcwyCopy1s.get(i).getContractPrice());
            QueryWrapper<Wymjc> jtwq = new QueryWrapper<>();
            jtwq.eq("gysmc", jcwyCopy1s.get(i).getDistributorName());
            List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtwq);
            Wymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
            jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
            jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
            jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
            jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
            jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
            jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
            jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            jcwyCopy1Copy1.setK(df.format(Double.valueOf(jtbizhbCopy1.getKo())));
            jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jtbizhbCopy1.getNao())));
            jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jtbizhbCopy1.getZno())));
//          爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
            HashMap<String, String> jsqtpjz = jsqtpjzwym(jcwyCopy1s.get(i).getDistributorName(), bbh, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//           非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            // 烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            jcwyCopy1Copy1.setYxrl(df.format((9400 * Double.valueOf(jcwyCopy1Copy1.getFcd())) - (2800 * Double.valueOf(jcwyCopy1Copy1.getAd())) -
                    (20000 * Double.valueOf(jcwyCopy1Copy1.getStD()))));
            //到厂价格 = 合同价格 + 运费价格
            jcwyCopy1Copy1.setDcjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) + Double.valueOf(jcwyCopy1Copy1.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                    (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            //单位有效热量成本 不含税价格/有效热量
            DecimalFormat df1 = new DecimalFormat("0.00000");
            jcwyCopy1Copy1.setDwyxrcb(df1.format(Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / Double.valueOf(jcwyCopy1Copy1.getYxrl())));
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String firstDayOfMonth = sdf.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            if (!ObjectUtils.isEmpty(jcwyCopy1s.get(i).getMonthAmount())) {
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) /
                        Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("∞") ||
                        decimalFormat.format(Double.valueOf(sjgils) /
                                Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("�") ? String.valueOf(0) :
                        decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())));
            } else {
                jcwyCopy1Copy1.setDxl("0");
            }
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }
        return jcwyCopy1Copy1s1;
    }

    /**
     * 无烟煤常规数据
     *
     * @param gysmc
     * @param bbh
     * @param shm
     * @return
     */
    public HashMap<String, String> jsqtpjzwym(String gysmc, String bbh, Map<String, String> shm) {
        HashMap<String, String> stringDoubleHashMap = new HashMap<>();
//      爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(bbh.substring(8, 10)));
        calendar.add(Calendar.DATE, -7);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        QueryWrapper<Gcilma> jtgrsQueryWrapper = new QueryWrapper<>();
        jtgrsQueryWrapper.between("jjrq", daytose, bbh);
        if (gysmc.indexOf("/") != -1) {
            jtgrsQueryWrapper.eq("gysmc", gysmc.split("/")[0]);
            jtgrsQueryWrapper.eq("fhdd", gysmc.split("/")[1]);
        } else {
            jtgrsQueryWrapper.eq("gysmc", gysmc);
        }
        List<Gcilma> jtgrs = gcilmaMapper.selectList(jtgrsQueryWrapper);
        DecimalFormat df = new DecimalFormat("0.00");
        Double tad = 0.00;
        int sum = 0;
        ArrayList<Double> Ad = new ArrayList<>();
        ArrayList<Double> Fcd = new ArrayList<>();
        ArrayList<Double> Mad = new ArrayList<>();
        ArrayList<Double> Mt = new ArrayList<>();
        ArrayList<Double> qgrd = new ArrayList<>();
        ArrayList<Double> Std = new ArrayList<>();
        ArrayList<Double> Vdaf = new ArrayList<>();
        ArrayList<Double> sjzl = new ArrayList<>();
        ArrayList<Double> k = new ArrayList<>();
        ArrayList<Double> na = new ArrayList<>();
        ArrayList<Double> zn = new ArrayList<>();
        for (int i = 0; i < jtgrs.size(); i++) {
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getAd())) {
                Ad.add(Double.valueOf(jtgrs.get(i).getAd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getFcd())) {
                Fcd.add(Double.valueOf(jtgrs.get(i).getFcd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMad())) {
                Mad.add(Double.valueOf(jtgrs.get(i).getMad()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMt())) {
                Mt.add(Double.valueOf(jtgrs.get(i).getMt()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getQgrD())) {
                qgrd.add(Double.valueOf(jtgrs.get(i).getQgrD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getStD())) {
                Std.add(Double.valueOf(jtgrs.get(i).getStD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getVdaf())) {
                Vdaf.add(Double.valueOf(jtgrs.get(i).getVdaf()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getSjzl())) {
                sjzl.add(Double.valueOf(jtgrs.get(i).getSjzl()));
            }
            if (!ObjectUtils.isEmpty(shm.get(jtgrs.get(i).getPh()))) {
                k.add(Double.valueOf(shm.get(jtgrs.get(i).getPh())));
            }
        }
        Double Ad1 = 0.00;
        Double Fcd1 = 0.00;
        Double Mad1 = 0.00;
        Double Mt1 = 0.00;
        Double qgrd1 = 0.00;
        Double Std1 = 0.00;
        Double Vdaf1 = 0.00;
        Double sjzl1 = 0.00;
        Double k1 = 0.0;
        Double na1 = 0.0;
        Double zn1 = 0.0;
        for (int i = 0; i < k.size(); i++) {
            k1 = k1 + k.get(i);
        }
        for (int i = 0; i < Ad.size(); i++) {
            Ad1 = Ad1 + Ad.get(i);
        }
        for (int i = 0; i < Fcd.size(); i++) {
            Fcd1 = Fcd1 + Fcd.get(i);
        }
        for (int i = 0; i < Mad.size(); i++) {
            Mad1 = Mad1 + Mad.get(i);
        }
        for (int i = 0; i < Mt.size(); i++) {
            Mt1 = Mt1 + Mt.get(i);
        }
        for (int i = 0; i < qgrd.size(); i++) {
            qgrd1 = qgrd1 + qgrd.get(i);
        }
        for (int i = 0; i < Std.size(); i++) {
            Std1 = Std1 + Std.get(i);
        }
        for (int i = 0; i < Vdaf.size(); i++) {
            Vdaf1 = Vdaf1 + Vdaf.get(i);
        }
        for (int i = 0; i < sjzl.size(); i++) {
            sjzl1 = sjzl1 + sjzl.get(i);
        }
        if (Ad1 != 0.0) {
            stringDoubleHashMap.put("Ad", df.format(Ad1 / Ad.size()));
        }
        if (Fcd1 != 0.0) {
            stringDoubleHashMap.put("Fcd", df.format(Fcd1 / Fcd.size()));
        }
        if (Mad1 != 0.0) {
            stringDoubleHashMap.put("Mad", df.format(Mad1 / Mad.size()));
        }
        if (Mt1 != 0.0) {
            stringDoubleHashMap.put("Mt", df.format(Mt1 / Mt.size()));
        }
        if (qgrd1 != 0.0) {
            stringDoubleHashMap.put("qgrd", df.format(qgrd1 / qgrd.size()));
        }
        if (Std1 != 0.0) {
            stringDoubleHashMap.put("Std", df.format(Std1 / Std.size()));
        }
        if (Vdaf1 != 0.0) {
            stringDoubleHashMap.put("vdaf", df.format(Vdaf1 / Std.size()));
        }
        if (sjzl1 != 0.00) {
            stringDoubleHashMap.put("sjzl", df.format(sjzl1));
        }
        if (k1 != 0.0) {
            stringDoubleHashMap.put("K2O", df.format(k1 / k.size()));
        }
        return stringDoubleHashMap;
    }

    /**
     * 无烟煤区间查询
     *
     * @param bbh
     * @param tml
     * @param tal
     * @return
     */
    @RequestMapping("selecttocopycopywymqj")
    public Object selecttocopycopywymqjqj(String tml, String tal, String bbh) throws ParseException {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat df1 = new DecimalFormat("0.00000");
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        //去除重复查询常规表
        QueryWrapper<Gcilma> jtgrs = new QueryWrapper<>();
        jtgrs.select("distinct gysmc,fhdd");
        jtgrs.between("jjrq", tml, tal);
        List<Gcilma> jtgrsList = gcilmaMapper.selectList(jtgrs);
        ArrayList<WymCopy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        //根据日期区间查询焦炭常规表,湿基重量为空的不要
        for (int i = 0; i < jtgrsList.size(); i++) {
            WymCopy1 jcwyCopy1Copy1 = new WymCopy1();
            //根据供应商名称去jtbizhb_copy1查询各个元素的默认值,如果常规表中有该供应商的元素值则替换掉jtbizhb_copy1的元素值
            QueryWrapper<Wymjc> jtbizhbCopy1QueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc());
            }
            Wymjc jtbizhbCopy1 = new Wymjc();
            List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtbizhbCopy1QueryWrapper);
            if (!ObjectUtils.isEmpty(jtbizhbCopy1s)) {
                jtbizhbCopy1 = wymjcMapper.selectList(jtbizhbCopy1QueryWrapper).get(0);
                jcwyCopy1Copy1.setAd(jtbizhbCopy1.getAd());
                jcwyCopy1Copy1.setFcd(jtbizhbCopy1.getFcd());
                jcwyCopy1Copy1.setMad(jtbizhbCopy1.getMad());
                jcwyCopy1Copy1.setMt(jtbizhbCopy1.getMt());
                jcwyCopy1Copy1.setQgrD(jtbizhbCopy1.getQgrD());
                jcwyCopy1Copy1.setStD(jtbizhbCopy1.getStD());
                jcwyCopy1Copy1.setVdaf(jtbizhbCopy1.getVdaf());
                jcwyCopy1Copy1.setK(jtbizhbCopy1.getKo());
                jcwyCopy1Copy1.setNa(jtbizhbCopy1.getNao());
                jcwyCopy1Copy1.setZn(jtbizhbCopy1.getZno());
            } else {
                continue;
            }
            String namers = "";
            namers = jtgrsList.get(i).getGysmc();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                namers = jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd();
            }
            //常规表根据区间查询平均值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sd1.parse(tml);
            Date d2 = sd1.parse(tal);
            int i1 = daysBetween(d1, d2);
            HashMap<String, String> jsqtpjz = jsqtpjz1rswym(namers, tal, i1, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//           非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            QueryWrapper<Gcilma> jtgrsnes = new QueryWrapper<>();
            jtgrsnes.between("jjrq", tml, tal);
            //jtgrsnes.ne("sjzl", "");
            jtgrsnes.eq("gysmc", jtgrsList.get(i).getGysmc());
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtgrsnes.eq("fhdd", jtgrsList.get(i).getFhdd());
            }
            jtgrsnes.select("SUM(sjzl)");
            Map<String, Object> maps = gcilmaMapper.selectMaps(jtgrsnes).get(0);
            jcwyCopy1Copy1.setCgl(df.format(maps.get("SUM(sjzl)")));
            //wymdcwh
            QueryWrapper<Wymdcwh> jcwyQueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc());
            }
            if (ObjectUtils.isEmpty(wymdcwhMapper.selectList(jcwyQueryWrapper))) {
                continue;
            }
            Wymdcwh jcwy = wymdcwhMapper.selectList(jcwyQueryWrapper).get(0);
            jcwyCopy1Copy1.setDistributorName(jcwy.getDistributorName());
            jcwyCopy1Copy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1Copy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1Copy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1Copy1.setJssf(jcwy.getJssf());
            jcwyCopy1Copy1.setYfjg(jcwy.getJsfm());
            jcwyCopy1Copy1.setCcjg(jcwy.getContractPrice());
            // 烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            jcwyCopy1Copy1.setYxrl(df.format((9400 * Double.valueOf(jcwyCopy1Copy1.getFcd())) - (2800 * Double.valueOf(jcwyCopy1Copy1.getAd())) -
                    (20000 * Double.valueOf(jcwyCopy1Copy1.getStD()))));
            //到厂价格 = 合同价格 + 运费价格
            jcwyCopy1Copy1.setDcjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) + Double.valueOf(jcwyCopy1Copy1.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                    (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            //单位有效热量成本 不含税价格/有效热量
            jcwyCopy1Copy1.setDwyxrcb(df1.format(Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / Double.valueOf(jcwyCopy1Copy1.getYxrl())));
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            String firstDayOfMonth = sdf.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrsfw = jtgrsMapper.selectList(jtgrsQueryWrapper);
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            if (!ObjectUtils.isEmpty(jcwyCopy1Copy1.getMonthAmount())) {
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) /
                        Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("∞") ||
                        decimalFormat.format(Double.valueOf(sjgils) /
                                Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("�") ? String.valueOf(0) :
                        decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())));
            } else {
                jcwyCopy1Copy1.setDxl("0");
            }
            //单焦种的采购量/焦炭总采购量
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }

        return jcwyCopy1Copy1s1;
    }

    public HashMap<String, String> jsqtpjz1rswym(String gysmc, String tal, int rqc, HashMap<String, String> shm) {
        HashMap<String, String> stringDoubleHashMap = new HashMap<>();
//      爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.YEAR, Integer.parseInt(tal.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(tal.substring(5, 7)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(tal.substring(8, 10)));
        calendar.add(Calendar.DATE, -rqc);
        Date dateTime = calendar.getTime();
        String daytose = sdf.format(dateTime);
        QueryWrapper<Gcilma> jtgrsQueryWrapper = new QueryWrapper<>();
        jtgrsQueryWrapper.between("jjrq", daytose, tal);
        if (gysmc.indexOf("/") != -1) {
            jtgrsQueryWrapper.eq("gysmc", gysmc.split("/")[0]);
            jtgrsQueryWrapper.eq("fhdd", gysmc.split("/")[1]);
        } else {
            jtgrsQueryWrapper.eq("gysmc", gysmc);
        }
        List<Gcilma> jtgrs = gcilmaMapper.selectList(jtgrsQueryWrapper);
        DecimalFormat df = new DecimalFormat("0.00");
        Double tad = 0.00;
        int sum = 0;
        ArrayList<Double> Ad = new ArrayList<>();
        ArrayList<Double> Fcd = new ArrayList<>();
        ArrayList<Double> Mad = new ArrayList<>();
        ArrayList<Double> Mt = new ArrayList<>();
        ArrayList<Double> qgrd = new ArrayList<>();
        ArrayList<Double> Std = new ArrayList<>();
        ArrayList<Double> Vdaf = new ArrayList<>();
        ArrayList<Double> sjzl = new ArrayList<>();
        ArrayList<Double> k = new ArrayList<>();
        ArrayList<Double> na = new ArrayList<>();
        ArrayList<Double> zn = new ArrayList<>();
        for (int i = 0; i < jtgrs.size(); i++) {
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getAd())) {
                Ad.add(Double.valueOf(jtgrs.get(i).getAd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getFcd())) {
                Fcd.add(Double.valueOf(jtgrs.get(i).getFcd()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMad())) {
                Mad.add(Double.valueOf(jtgrs.get(i).getMad()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getMt())) {
                Mt.add(Double.valueOf(jtgrs.get(i).getMt()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getQgrD())) {
                qgrd.add(Double.valueOf(jtgrs.get(i).getQgrD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getStD())) {
                Std.add(Double.valueOf(jtgrs.get(i).getStD()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getVdaf())) {
                Vdaf.add(Double.valueOf(jtgrs.get(i).getVdaf()));
            }
            if (!ObjectUtils.isEmpty(jtgrs.get(i).getSjzl())) {
                sjzl.add(Double.valueOf(jtgrs.get(i).getSjzl()));
            }
            if (!ObjectUtils.isEmpty(shm.get(jtgrs.get(i).getPh()))) {
                k.add(Double.valueOf(shm.get(jtgrs.get(i).getPh())));
            }
        }
        Double Ad1 = 0.00;
        Double Fcd1 = 0.00;
        Double Mad1 = 0.00;
        Double Mt1 = 0.00;
        Double qgrd1 = 0.00;
        Double Std1 = 0.00;
        Double Vdaf1 = 0.00;
        Double sjzl1 = 0.00;
        Double k1 = 0.0;
        Double na1 = 0.0;
        Double zn1 = 0.0;
        for (int i = 0; i < k.size(); i++) {
            k1 = k1 + k.get(i);
        }
        for (int i = 0; i < Ad.size(); i++) {
            Ad1 = Ad1 + Ad.get(i);
        }
        for (int i = 0; i < Fcd.size(); i++) {
            Fcd1 = Fcd1 + Fcd.get(i);
        }
        for (int i = 0; i < Mad.size(); i++) {
            Mad1 = Mad1 + Mad.get(i);
        }
        for (int i = 0; i < Mt.size(); i++) {
            Mt1 = Mt1 + Mt.get(i);
        }
        for (int i = 0; i < qgrd.size(); i++) {
            qgrd1 = qgrd1 + qgrd.get(i);
        }
        for (int i = 0; i < Std.size(); i++) {
            Std1 = Std1 + Std.get(i);
        }
        for (int i = 0; i < Vdaf.size(); i++) {
            Vdaf1 = Vdaf1 + Vdaf.get(i);
        }
        for (int i = 0; i < sjzl.size(); i++) {
            sjzl1 = sjzl1 + sjzl.get(i);
        }
        if (Ad1 != 0.0) {
            stringDoubleHashMap.put("Ad", df.format(Ad1 / Ad.size()));
        }
        if (Fcd1 != 0.0) {
            stringDoubleHashMap.put("Fcd", df.format(Fcd1 / Fcd.size()));
        }
        if (Mad1 != 0.0) {
            stringDoubleHashMap.put("Mad", df.format(Mad1 / Mad.size()));
        }
        if (Mt1 != 0.0) {
            stringDoubleHashMap.put("Mt", df.format(Mt1 / Mt.size()));
        }
        if (qgrd1 != 0.0) {
            stringDoubleHashMap.put("qgrd", df.format(qgrd1 / qgrd.size()));
        }
        if (Std1 != 0.0) {
            stringDoubleHashMap.put("Std", df.format(Std1 / Std.size()));
        }
        if (Vdaf1 != 0.0) {
            stringDoubleHashMap.put("vdaf", df.format(Vdaf1 / Std.size()));
        }
        if (sjzl1 != 0.00) {
            stringDoubleHashMap.put("sjzl", df.format(sjzl1));
        }
        if (k1 != 0.0) {
            stringDoubleHashMap.put("K2O", df.format(k1 / k.size()));
        }
        return stringDoubleHashMap;
    }

    // 烟煤新增版本号
    @RequestMapping("insertymversions")
    public void insertymversions(String idrs, String jzzaniu) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        JSONArray jsonArray = JSONArray.parseArray(idrs);
        for (int i = 0; i < jsonArray.size(); i++) {
            int id = (int) jsonArray.get(i);
            QueryWrapper<Wymdcwh> jw = new QueryWrapper<>();
            jw.eq("id", id);
            List<Wymdcwh> jcwies = wymdcwhMapper.selectList(jw);
            Wymdcwh jcwy = jcwies.get(0);
            YmdcwhCopy2 jcwyCopy1 = new YmdcwhCopy2();
            jcwyCopy1.setRllx("烟煤");
            jcwyCopy1.setDistributorName(jcwy.getDistributorName());
            jcwyCopy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1.setJssf(jcwy.getJssf());
            jcwyCopy1.setJsfm(jcwy.getJsfm());
            jcwyCopy1.setVersion(format);
            ymdcwhCopy2Mapper.insert(jcwyCopy1);
        }
    }

    /**
     * 烟煤全查询版本号
     */
    @RequestMapping("selectbehedym")
    public List<String> selectbehedym() {
        QueryWrapper<YmdcwhCopy2> jf = new QueryWrapper<>();
        jf.orderByDesc("version");
        jf.select("DISTINCT version");
        jf.last("limit 20");
        //jf.select("limit 20");
        List<YmdcwhCopy2> jc = ymdcwhCopy2Mapper.selectList(jf);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = jc.size() - 1; i >= 0; i--) {
            strings.add(jc.get(i).getVersion());
        }
        return strings;
    }

    /**
     * 烟煤顶层测算
     *
     * @param bbh
     * @param tml
     * @param tal
     * @return
     */
    @RequestMapping("selecttocopycopyym")
    public List<YmCopy1> selecttocopycopyym(String bbh, String tml, String tal) {
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.eq("date", bbh.substring(0, 10));
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        QueryWrapper<YmdcwhCopy2> jw = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(tml) && ObjectUtils.isEmpty(tal)) {
            jw.eq("version", bbh);
        } else {
            // HH:mm:ss
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                jw.between("version", sd.parse(tml), sd.parse(tal));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<YmdcwhCopy2> jcwyCopy1s = ymdcwhCopy2Mapper.selectList(jw);
        ArrayList<YmCopy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < jcwyCopy1s.size(); i++) {
            YmCopy1 jcwyCopy1Copy1 = new YmCopy1();
            jcwyCopy1Copy1.setDistributorName(jcwyCopy1s.get(i).getDistributorName());
            jcwyCopy1Copy1.setSterilise(jcwyCopy1s.get(i).getSterilise());
            jcwyCopy1Copy1.setContractPrice(jcwyCopy1s.get(i).getContractPrice());
            jcwyCopy1Copy1.setMonthAmount(jcwyCopy1s.get(i).getMonthAmount());
            jcwyCopy1Copy1.setJssf(jcwyCopy1s.get(i).getJssf());
            jcwyCopy1Copy1.setYfjg(jcwyCopy1s.get(i).getJsfm());
            jcwyCopy1Copy1.setVersion(jcwyCopy1s.get(i).getVersion());
            jcwyCopy1Copy1.setCcjg(jcwyCopy1s.get(i).getContractPrice());
            QueryWrapper<Ymjc> jtwq = new QueryWrapper<>();
            jtwq.eq("gysmc", jcwyCopy1s.get(i).getDistributorName());
            List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(jtwq);
            Ymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
            jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
            jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
            jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
            jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
            jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
            jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
            jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            jcwyCopy1Copy1.setK(df.format(Double.valueOf(jtbizhbCopy1.getKo())));
            jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jtbizhbCopy1.getNao())));
            jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jtbizhbCopy1.getZno())));
//          爬虫表采购量,Ad,Vdaf,Fcad,Std,M25,CRI,CSR,Mt
            HashMap<String, String> jsqtpjz = jsqtpjzwym(jcwyCopy1s.get(i).getDistributorName(), bbh, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//           非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            // 烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            jcwyCopy1Copy1.setYxrl(df.format((8400 * Double.valueOf(jcwyCopy1Copy1.getFcd())) - (2800 * Double.valueOf(jcwyCopy1Copy1.getAd())) -
                    (20000 * Double.valueOf(jcwyCopy1Copy1.getStD()))));
            //到厂价格 = 合同价格 + 运费价格
            jcwyCopy1Copy1.setDcjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) + Double.valueOf(jcwyCopy1Copy1.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            if (jcwyCopy1s.get(i).getSterilise().equals("承兑")) {
                jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.02 / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                        (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            } else {
                jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                        (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            }
            //单位有效热量成本 不含税价格/有效热量
            DecimalFormat df1 = new DecimalFormat("0.00000");
            jcwyCopy1Copy1.setDwyxrcb(df1.format(Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / Double.valueOf(jcwyCopy1Copy1.getYxrl())));
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String firstDayOfMonth = sdf.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrs = jtgrsMapper.selectList(jtgrsQueryWrapper);
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            if (!ObjectUtils.isEmpty(jcwyCopy1s.get(i).getMonthAmount())) {
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) /
                        Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("∞") ||
                        decimalFormat.format(Double.valueOf(sjgils) /
                                Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())).equals("�") ? String.valueOf(0) :
                        decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1s.get(i).getMonthAmount())));
            } else {
                jcwyCopy1Copy1.setDxl("0");
            }
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }
        return jcwyCopy1Copy1s1;
    }

    /**
     * 烟煤区间查询
     *
     * @param bbh
     * @param tml
     * @param tal
     * @return
     */
    @RequestMapping("selecttocopycopyymqj")
    public Object selecttocopycopyymqjqj(String tml, String tal, String bbh) throws ParseException {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat df1 = new DecimalFormat("0.00000");
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        //先查询非常规表中的所有数据存入map集合
        QueryWrapper<Unconventionality> unconventionalityQueryWrapper = new QueryWrapper<>();
        unconventionalityQueryWrapper.eq("item", "K2O");
        unconventionalityQueryWrapper.between("date", tml, tal);
        unconventionalityQueryWrapper.like("number", "fs");
        List<Unconventionality> uci = unconventionalityMapper.selectList(unconventionalityQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < uci.size(); i++) {
            shm.put(uci.get(i).getNumber(), uci.get(i).getPrice());
        }
        //去除重复查询常规表
        QueryWrapper<Gcilma> jtgrs = new QueryWrapper<>();
        jtgrs.select("distinct gysmc,fhdd");
        jtgrs.between("jjrq", tml, tal);
        List<Gcilma> jtgrsList = gcilmaMapper.selectList(jtgrs);
        ArrayList<YmCopy1> jcwyCopy1Copy1s1 = new ArrayList<>();
        //根据日期区间查询焦炭常规表,湿基重量为空的不要
        for (int i = 0; i < jtgrsList.size(); i++) {
            YmCopy1 jcwyCopy1Copy1 = new YmCopy1();
            //根据供应商名称去jtbizhb_copy1查询各个元素的默认值,如果常规表中有该供应商的元素值则替换掉jtbizhb_copy1的元素值
            QueryWrapper<Ymjc> jtbizhbCopy1QueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jtbizhbCopy1QueryWrapper.eq("gysmc", jtgrsList.get(i).getGysmc());
            }
            Ymjc jtbizhbCopy1 = new Ymjc();
            List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(jtbizhbCopy1QueryWrapper);
            if (!ObjectUtils.isEmpty(jtbizhbCopy1s)) {
                jtbizhbCopy1 = ymjcMapper.selectList(jtbizhbCopy1QueryWrapper).get(0);
                jcwyCopy1Copy1.setAd(jtbizhbCopy1.getAd());
                jcwyCopy1Copy1.setFcd(jtbizhbCopy1.getFcd());
                jcwyCopy1Copy1.setMad(jtbizhbCopy1.getMad());
                jcwyCopy1Copy1.setMt(jtbizhbCopy1.getMt());
                jcwyCopy1Copy1.setQgrD(jtbizhbCopy1.getQgrD());
                jcwyCopy1Copy1.setStD(jtbizhbCopy1.getStD());
                jcwyCopy1Copy1.setVdaf(jtbizhbCopy1.getVdaf());
                jcwyCopy1Copy1.setK(jtbizhbCopy1.getKo());
                jcwyCopy1Copy1.setNa(jtbizhbCopy1.getNao());
                jcwyCopy1Copy1.setZn(jtbizhbCopy1.getZno());
            } else {
                continue;
            }
            String namers = "";
            namers = jtgrsList.get(i).getGysmc();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                namers = jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd();
            }
            //常规表根据区间查询平均值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sd1.parse(tml);
            Date d2 = sd1.parse(tal);
            int i1 = daysBetween(d1, d2);
            HashMap<String, String> jsqtpjz = jsqtpjz1rswym(namers, tal, i1, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jcwyCopy1Copy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                jcwyCopy1Copy1.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                jcwyCopy1Copy1.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jcwyCopy1Copy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                jcwyCopy1Copy1.setQgrD(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jcwyCopy1Copy1.setStD(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                jcwyCopy1Copy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            if (jsqtpjz.containsKey("sjzl")) {
                jcwyCopy1Copy1.setCgl(jsqtpjz.get("sjzl"));
            }
//           非常规
            if (jsqtpjz.containsKey("K2O")) {
                jcwyCopy1Copy1.setK(df.format(Double.valueOf(jsqtpjz.get("K2O"))));
            }
            if (jsqtpjz.containsKey("Na2O")) {
                jcwyCopy1Copy1.setNa(df.format(Double.valueOf(jsqtpjz.get("Na2O"))));
            }
            if (jsqtpjz.containsKey("ZnO")) {
                jcwyCopy1Copy1.setZn(df.format(Double.valueOf(jsqtpjz.get("ZnO"))));
            }
            QueryWrapper<Gcilma> jtgrsnes = new QueryWrapper<>();
            jtgrsnes.between("jjrq", tml, tal);
            jtgrsnes.eq("gysmc", jtgrsList.get(i).getGysmc());
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jtgrsnes.eq("fhdd", jtgrsList.get(i).getFhdd());
            }
            jtgrsnes.select("SUM(sjzl)");
            Map<String, Object> maps = gcilmaMapper.selectMaps(jtgrsnes).get(0);
            jcwyCopy1Copy1.setCgl(df.format(maps.get("SUM(sjzl)")));
            //wymdcwh
            QueryWrapper<Wymdcwh> jcwyQueryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(jtgrsList.get(i).getFhdd())) {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc() + "/" + jtgrsList.get(i).getFhdd());
            } else {
                jcwyQueryWrapper.eq("distributor_name", jtgrsList.get(i).getGysmc());
            }
            if (ObjectUtils.isEmpty(wymdcwhMapper.selectList(jcwyQueryWrapper))) {
                continue;
            }
            Wymdcwh jcwy = wymdcwhMapper.selectList(jcwyQueryWrapper).get(0);
            jcwyCopy1Copy1.setDistributorName(jcwy.getDistributorName());
            jcwyCopy1Copy1.setSterilise(jcwy.getSterilise());
            jcwyCopy1Copy1.setContractPrice(jcwy.getContractPrice());
            jcwyCopy1Copy1.setMonthAmount(jcwy.getMonthAmount());
            jcwyCopy1Copy1.setJssf(jcwy.getJssf());
            jcwyCopy1Copy1.setYfjg(jcwy.getJsfm());
            jcwyCopy1Copy1.setCcjg(jcwy.getContractPrice());
            // 烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            jcwyCopy1Copy1.setYxrl(df.format((9400 * Double.valueOf(jcwyCopy1Copy1.getFcd())) - (2800 * Double.valueOf(jcwyCopy1Copy1.getAd())) -
                    (20000 * Double.valueOf(jcwyCopy1Copy1.getStD()))));
            //到厂价格 = 合同价格 + 运费价格
            jcwyCopy1Copy1.setDcjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) + Double.valueOf(jcwyCopy1Copy1.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            if (jcwy.getSterilise().equals("承兑")) {
                jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.02 / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                        (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            } else {
                jcwyCopy1Copy1.setBhsjg(df.format(Double.valueOf(jcwyCopy1Copy1.getContractPrice()) / 1.13 / ((100 - Double.valueOf(jcwyCopy1Copy1.getJssf())) / 100) +
                        (Double.valueOf(jcwyCopy1Copy1.getYfjg())) / 1.09));
            }
            //单位有效热量成本 不含税价格/有效热量
            jcwyCopy1Copy1.setDwyxrcb(df1.format(Double.valueOf(jcwyCopy1Copy1.getBhsjg()) / Double.valueOf(jcwyCopy1Copy1.getYxrl())));
            //兑现率 月实际累计到货量/月计划量
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, Integer.parseInt(bbh.substring(0, 4)));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(bbh.substring(5, 7)) - 1);
            //获取某月最小天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstDay);
            //格式化日期
            String firstDayOfMonth = sdf.format(cal.getTime());
            QueryWrapper<Jtgrs> jtgrsQueryWrapper = new QueryWrapper<>();
            jtgrsQueryWrapper.between("jjrq", firstDayOfMonth, bbh);
            List<Jtgrs> jtgrsfw = jtgrsMapper.selectList(jtgrsQueryWrapper);
            String gmail = "";
            String sjgils = ObjectUtils.isEmpty(jcwyCopy1Copy1.getCgl()) ? "0" : jcwyCopy1Copy1.getCgl();
            if (!ObjectUtils.isEmpty(jcwyCopy1Copy1.getMonthAmount())) {
                jcwyCopy1Copy1.setDxl(gmail = decimalFormat.format(Double.valueOf(sjgils) /
                        Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("∞") ||
                        decimalFormat.format(Double.valueOf(sjgils) /
                                Double.valueOf(jcwyCopy1Copy1.getMonthAmount())).equals("�") ? String.valueOf(0) :
                        decimalFormat.format(Double.valueOf(sjgils) / Double.valueOf(jcwyCopy1Copy1.getMonthAmount())));
            } else {
                jcwyCopy1Copy1.setDxl("0");
            }
            //单焦种的采购量/焦炭总采购量
            //单焦种的采购量/焦炭总采购量
            jcwyCopy1Copy1s1.add(jcwyCopy1Copy1);
        }

        return jcwyCopy1Copy1s1;
    }

    /**
     * 新增焦粉信息
     *
     * @param ad
     * @param vdaf
     * @param std
     * @param qgrd
     * @param fcd
     * @param mad
     * @param mt
     * @param ccjg
     * @param dcjg
     * @param fkfs
     * @param jssf
     * @param sjgtdh
     * @param gysmc
     * @param yfjg
     */
    @RequestMapping("cilma")
    public List<Jfdcwh> cilma(String ad, String vdaf, String std, String qgrd, String fcd,
                              String mad, String mt, String ccjg, String dcjg, String fkfs, String jssf,
                              String sjgtdh, String gysmc, String yfjg) {
        DecimalFormat df = new DecimalFormat("0.00");
        Jfdcwh jfdcwh = new Jfdcwh();
        jfdcwh.setAd(ad);
        jfdcwh.setStd(std);
        jfdcwh.setQgrd(qgrd);
        jfdcwh.setVdaf(vdaf);
        jfdcwh.setFcd(fcd);
        jfdcwh.setMt(mt);
        jfdcwh.setMad(mad);
        jfdcwh.setCcjg(ccjg);
        jfdcwh.setYfjg(yfjg);
        jfdcwh.setDcjg(dcjg);
        jfdcwh.setFkfs(fkfs);
        jfdcwh.setJssf(jssf);
        jfdcwh.setSjgtdh(sjgtdh);
        jfdcwh.setGysmc(gysmc);
        //出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
        jfdcwh.setBhsjg(df.format(Double.valueOf(jfdcwh.getCcjg()) / 1.13 / ((100 - Double.valueOf(jfdcwh.getJssf())) / 100) +
                (Double.valueOf(jfdcwh.getYfjg())) / 1.09));
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "焦粉固体燃耗");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        Butvalu butvalu = butvalus.get(0);
        Double aDouble = Double.valueOf(butvalu.getPropertyValue());
        jfdcwh.setDsrhdwcb(df.format(Double.valueOf(jfdcwh.getBhsjg()) / 1000 * aDouble));
        jfdcwhMapper.insert(jfdcwh);
        List<Jfdcwh> jfdcwhs = jfdcwhMapper.selectList(null);
        return jfdcwhs;
    }

    /**
     * 初始化固体单耗
     *
     * @param wymrh
     * @param jfrh
     * @return
     */
    @RequestMapping("cshgtrh")
    public Map<String, String> cshgtrh(String wymrh, String jfrh) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "无烟煤固体燃耗").or().eq("attribute_name", "焦粉固体燃耗");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        for (int i = 0; i < butvalus.size(); i++) {
            if (butvalus.get(i).getAttributeName().equals("无烟煤固体燃耗")) {
                stringStringHashMap.put("wymgtyh", butvalus.get(i).getPropertyValue());
            } else {
                stringStringHashMap.put("jfgtyh", butvalus.get(i).getPropertyValue());
            }
        }
        return stringStringHashMap;
    }

    @RequestMapping("updategtdh")
    public Map<String, String> updategtdh(String wymrh, String jfrh) {
        Butvalu butvalu = new Butvalu();
        butvalu.setPropertyValue(wymrh);
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "无烟煤固体燃耗");
        butvaluMapper.update(butvalu, butvaluQueryWrapper);
        Butvalu butvalu1 = new Butvalu();
        butvalu1.setPropertyValue(jfrh);
        QueryWrapper<Butvalu> butvaluQueryWrapper1 = new QueryWrapper<>();
        butvaluQueryWrapper1.eq("attribute_name", "焦粉固体燃耗");
        butvaluMapper.update(butvalu1, butvaluQueryWrapper1);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        QueryWrapper<Butvalu> butvaluQueryWrapper2 = new QueryWrapper<>();
        butvaluQueryWrapper2.eq("attribute_name", "无烟煤固体燃耗").or().eq("attribute_name", "焦粉固体燃耗");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper2);
        for (int i = 0; i < butvalus.size(); i++) {
            if (butvalus.get(i).getAttributeName().equals("无烟煤固体燃耗")) {
                stringStringHashMap.put("wymgtyh", butvalus.get(i).getPropertyValue());
            } else {
                stringStringHashMap.put("jfgtyh", butvalus.get(i).getPropertyValue());
            }
        }
        return stringStringHashMap;
    }

    @RequestMapping("selectjcdisall")
    public List<Jfdcwh> selectjcdisall() {
        List<Jfdcwh> jfdcwhs = jfdcwhMapper.selectList(null);
        return jfdcwhs;
    }

    @RequestMapping("updatejfbgwhels")
    public List<Jfdcwh> updatejfbgwhels(String ad, String std, String qgrd, String vdaf, String fcd, String mt,
                                        String ccjg, String yfjg, String dcjg, String fkfs, String jssf, String mad, String sjgtdh, String id, String gysmc) {
        DecimalFormat df = new DecimalFormat("0.00");
        Jfdcwh jfdcwh = new Jfdcwh();
        jfdcwh.setAd(ad);
        jfdcwh.setStd(std);
        jfdcwh.setQgrd(qgrd);
        jfdcwh.setVdaf(vdaf);
        jfdcwh.setFcd(fcd);
        jfdcwh.setMt(mt);
        jfdcwh.setCcjg(ccjg);
        jfdcwh.setYfjg(yfjg);
        jfdcwh.setDcjg(df.format(Double.valueOf(jfdcwh.getCcjg()) + Double.valueOf(jfdcwh.getYfjg())));
        jfdcwh.setFkfs(fkfs);
        jfdcwh.setJssf(jssf);
        jfdcwh.setMad(mad);
        jfdcwh.setGysmc(gysmc);
        //出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
        jfdcwh.setBhsjg(df.format(Double.valueOf(jfdcwh.getCcjg()) / 1.13 / ((100 - Double.valueOf(jfdcwh.getJssf())) / 100) +
                (Double.valueOf(jfdcwh.getYfjg())) / 1.09));
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "焦粉固体燃耗");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        Butvalu butvalu = butvalus.get(0);
        Double aDouble = Double.valueOf(butvalu.getPropertyValue());
        jfdcwh.setSjgtdh(String.valueOf(aDouble));
        jfdcwh.setDsrhdwcb(df.format(Double.valueOf(jfdcwh.getBhsjg()) / 1000 * aDouble));
        QueryWrapper<Jfdcwh> jf = new QueryWrapper<>();
        jf.eq("id", id);
        jfdcwhMapper.update(jfdcwh, jf);
        List<Jfdcwh> jfdcwhs = jfdcwhMapper.selectList(null);
        return jfdcwhs;
    }

    /**
     * 决策模型顶层数据
     *
     * @param idrs
     * @return
     */
    @RequestMapping("selectjcdisallarl")
    public String selectjcdisallarl(String idrs) {
        DecimalFormat df = new DecimalFormat("0.00");
        //查询焦粉和无烟煤的固体燃耗
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "无烟煤固体燃耗").or().eq("attribute_name", "焦粉固体燃耗");
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        //无烟煤
        String wymrh = "";
        //焦粉
        String jfrh = "";
        if (butvalus.get(0).getAttributeName().equals("焦粉固体燃耗")) {
            jfrh = butvalus.get(0).getPropertyValue();
            wymrh = butvalus.get(1).getPropertyValue();
        } else {
            jfrh = butvalus.get(1).getPropertyValue();
            wymrh = butvalus.get(0).getPropertyValue();
        }
        //获取当前时间
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String format = dfs.format(new Date());
        //处理传过来的id数组
        String replace = idrs.replace("[", "").replace("]", "");
        String[] split = replace.split(",");
        //查询焦粉信息
        List<Jfdcwh> jfdcwhs = jfdcwhMapper.selectList(null);
        Jfdcwh jfdcwh = jfdcwhs.get(0);
        JfdcwhCopy1 jfdcwhCopy11 = new JfdcwhCopy1();
        jfdcwhCopy11.setAd(jfdcwh.getAd());
        jfdcwhCopy11.setStd(jfdcwh.getStd());
        jfdcwhCopy11.setQgrd(jfdcwh.getQgrd());
        jfdcwhCopy11.setVdaf(jfdcwh.getVdaf());
        jfdcwhCopy11.setFcd(jfdcwh.getFcd());
        jfdcwhCopy11.setMt(jfdcwh.getMt());
        jfdcwhCopy11.setMad(jfdcwh.getMad());
        jfdcwhCopy11.setCcjg(jfdcwh.getCcjg());
        jfdcwhCopy11.setYfjg(jfdcwh.getYfjg());
        jfdcwhCopy11.setDcjg(jfdcwh.getDcjg());
        jfdcwhCopy11.setFkfs(jfdcwh.getFkfs());
        jfdcwhCopy11.setJssf(jfdcwh.getJssf());
        jfdcwhCopy11.setBhsjg(jfdcwh.getBhsjg());
        jfdcwhCopy11.setSjgtdh(jfdcwh.getSjgtdh());
        jfdcwhCopy11.setDsrhdwcb(jfdcwh.getDsrhdwcb());
        jfdcwhCopy11.setGysmc(jfdcwh.getGysmc());
        jfdcwhCopy11.setVersion(format);
        jfdcwhCopy1Mapper.insert(jfdcwhCopy11);
        for (int i = 0; i < split.length; i++) {
            JfdcwhCopy1 jfdcwhCopy1 = new JfdcwhCopy1();
            QueryWrapper<Wymdcwh> jfdcwhQueryWrapper = new QueryWrapper<>();
            jfdcwhQueryWrapper.eq("id", split[i]);
            List<Wymdcwh> jfdcwhs1 = wymdcwhMapper.selectList(jfdcwhQueryWrapper);
            Wymdcwh wymdcwh = jfdcwhs1.get(0);
            jfdcwhCopy1.setGysmc(wymdcwh.getDistributorName());
            jfdcwhCopy1.setCcjg(wymdcwh.getContractPrice());
            jfdcwhCopy1.setYfjg(wymdcwh.getJsfm());
            jfdcwhCopy1.setFkfs(wymdcwh.getSterilise());
            jfdcwhCopy1.setJssf(wymdcwh.getJssf());
            jfdcwhCopy1.setSjgtdh(wymrh);
            jfdcwhCopy1.setVersion(format);
            jfdcwhCopy1.setDcjg(df.format(Double.valueOf(jfdcwhCopy1.getCcjg()) + Double.valueOf(jfdcwhCopy1.getYfjg())));
            if (wymdcwh.getSterilise().equals("承兑")) {
                //出厂价格/1.02/1.13/（100-结算水分）/100+运费价格/1.09
                jfdcwhCopy1.setBhsjg(df.format(Double.valueOf(jfdcwhCopy1.getCcjg()) / 1.02 / 1.13 / ((100 - Double.valueOf(jfdcwhCopy1.getJssf())) / 100) +
                        (Double.valueOf(jfdcwhCopy1.getYfjg())) / 1.09));
            } else {
                //出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
                jfdcwhCopy1.setBhsjg(df.format(Double.valueOf(jfdcwhCopy1.getCcjg()) / 1.13 / ((100 - Double.valueOf(jfdcwhCopy1.getJssf())) / 100) +
                        (Double.valueOf(jfdcwhCopy1.getYfjg())) / 1.09));
            }
            //吨烧燃耗单位成本
            jfdcwhCopy1.setDsrhdwcb(df.format(Double.valueOf(jfdcwhCopy1.getBhsjg()) / 1000 * Double.valueOf(jfdcwhCopy1.getSjgtdh())));
            QueryWrapper<Wymjc> jtwq = new QueryWrapper<>();
            jtwq.eq("gysmc", jfdcwhCopy1.getGysmc());
            List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtwq);
            Wymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
            jfdcwhCopy1.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
            jfdcwhCopy1.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
            jfdcwhCopy1.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
            jfdcwhCopy1.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
            jfdcwhCopy1.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
            jfdcwhCopy1.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
            jfdcwhCopy1.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            Map<String, String> shm = new HashMap<>();
            HashMap<String, String> jsqtpjz = jsqtpjzwym(jfdcwhCopy1.getGysmc(), format, shm);
            if (jsqtpjz.containsKey("Ad")) {
                jfdcwhCopy1.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                jfdcwhCopy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                jfdcwhCopy1.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                jfdcwhCopy1.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                jfdcwhCopy1.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                jfdcwhCopy1.setQgrd(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                jfdcwhCopy1.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                jfdcwhCopy1.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            jfdcwhCopy1Mapper.insert(jfdcwhCopy1);
        }
        return format;
    }

    /**
     * 决策模型全查询版本号
     */
    @RequestMapping("selectbehedjcmx")
    public List<String> selectbehedjcmx() {
        QueryWrapper<JfdcwhCopy1> jf = new QueryWrapper<>();
        jf.orderByDesc("version");
        jf.select("DISTINCT version");
        jf.last("limit 20");
        //jf.select("limit 20");
        List<JfdcwhCopy1> jc = jfdcwhCopy1Mapper.selectList(jf);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = jc.size() - 1; i >= 0; i--) {
            strings.add(jc.get(i).getVersion());
        }
        return strings;
    }

    @RequestMapping("selectjcmxbbh")
    public List<JfdcwhCopy1> selectjcmxbbh(String bbh) {
        QueryWrapper<JfdcwhCopy1> jfdcwhCopy1QueryWrapper = new QueryWrapper<>();
        jfdcwhCopy1QueryWrapper.eq("version", bbh);
        List<JfdcwhCopy1> jfdcwhCopy1s = jfdcwhCopy1Mapper.selectList(jfdcwhCopy1QueryWrapper);
        return jfdcwhCopy1s;
    }

    @RequestMapping("routerair")
    public List<Dbcs> routerair(String ourbbhar) {
        //查询烟煤和无烟煤都在一起的表数据
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(null);
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat decimalFormat = new DecimalFormat("0.00000");
        for (int i = 0; i < wymdcwhs.size(); i++) {
            //对比测算过上面表的对象
            Dbcs dbcs = new Dbcs();
            dbcs.setLx(wymdcwhs.get(i).getRllx());
            dbcs.setGysmc(wymdcwhs.get(i).getDistributorName());
            dbcs.setFkfs(wymdcwhs.get(i).getSterilise());
            dbcs.setCcjg(wymdcwhs.get(i).getContractPrice());
            dbcs.setJssf(wymdcwhs.get(i).getJssf());
            dbcs.setYfjg(wymdcwhs.get(i).getJsfm());
            dbcs.setDcjg(df.format(Double.valueOf(dbcs.getCcjg()) + Double.valueOf(dbcs.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            dbcs.setBhsjg(df.format(Double.valueOf(dbcs.getCcjg()) / 1.13 / ((100 - Double.valueOf(dbcs.getJssf())) / 100) +
                    (Double.valueOf(dbcs.getYfjg())) / 1.09));
            if (dbcs.getLx().equals("无烟煤")) {
                //无烟煤基础数据
                QueryWrapper<Wymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtwq);
                Wymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            } else {
                //无烟煤基础数据
                QueryWrapper<Ymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(jtwq);
                Ymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            }
            HashMap<String, String> shm = new HashMap<>();
            HashMap<String, String> jsqtpjz = jsqtpjzwym(dbcs.getGysmc(), ourbbhar, shm);
            if (jsqtpjz.containsKey("Ad")) {
                dbcs.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                dbcs.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                dbcs.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                dbcs.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                dbcs.setQgrd(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                dbcs.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            //无烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            if (dbcs.getLx().equals("无烟煤")) {
                dbcs.setYxrl(df.format(9400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            } else {
                dbcs.setYxrl(df.format(8400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            }
            //单位有效热量成本 不含税价格/有效热量
            dbcs.setYxrcb(decimalFormat.format(Double.valueOf(dbcs.getBhsjg()) / Double.valueOf(dbcs.getYxrl())));
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
            dbcsCopy1QueryWrapper.eq("gysmc", dbcs.getGysmc());
            dbcsCopy1QueryWrapper.orderByDesc("version");
            List<DbcsCopy1> dbcsCopy1s = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper);
            dbcs.setPb(dbcsCopy1s.get(0).getPb());
            dbcs.setVersion(ourbbhar);
            dbcsMapper.insert(dbcs);
        }
        QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
        dbcsQueryWrapper.eq("version", ourbbhar);
        List<Dbcs> dbcs = dbcsMapper.selectList(dbcsQueryWrapper);
        return dbcs;
    }

    @RequestMapping("tacbere")
    public List<DbcsCopy1> tacbere(String courlist, String ourbbhar) {
        JSONArray jsonArray = JSONArray.parseArray(courlist);
        QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper1 = new QueryWrapper<>();
        dbcsCopy1QueryWrapper1.eq("version", jsonArray.getJSONObject(0).get("version"));
        List<DbcsCopy1> dbcsCopy1s1 = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper1);
        if (ObjectUtils.isEmpty(dbcsCopy1s1)) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Dbcs dbcs = new Dbcs();
                dbcs.setPb(jsonObject.getString("pb"));
                QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
                dbcsQueryWrapper.eq("id", jsonObject.getString("id"));
                dbcsMapper.update(dbcs, dbcsQueryWrapper);
                //存入下面表的数据库(没有当前版本号存 有则修改)
                DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
                dbcsCopy1.setYxrl(jsonObject.getString("yxrl"));
                dbcsCopy1.setStd(jsonObject.getString("std"));
                dbcsCopy1.setAd(jsonObject.getString("ad"));
                dbcsCopy1.setVdaf(jsonObject.getString("vdaf"));
                dbcsCopy1.setFcd(jsonObject.getString("fcd"));
                dbcsCopy1.setMt(jsonObject.getString("mt"));
                dbcsCopy1.setLx(jsonObject.getString("lx"));
                dbcsCopy1.setJssf(jsonObject.getString("jssf"));
                dbcsCopy1.setVersion(jsonObject.getString("version"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setYfjg(jsonObject.getString("yfjg"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setDcjg(jsonObject.getString("dcjg"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setPb(jsonObject.getString("pb"));
                dbcsCopy1.setCcjg(jsonObject.getString("ccjg"));
                dbcsCopy1.setGysmc(jsonObject.getString("gysmc"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setQgrd(jsonObject.getString("qgrd"));
                dbcsCopy1.setBhsjg(jsonObject.getString("bhsjg"));
                dbcsCopy1.setFkfs(jsonObject.getString("fkfs"));
                dbcsCopy1Mapper.insert(dbcsCopy1);
            }
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Dbcs dbcs = new Dbcs();
                dbcs.setPb(jsonObject.getString("pb"));
                QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
                dbcsQueryWrapper.eq("id", jsonObject.getString("id"));
                dbcsMapper.update(dbcs, dbcsQueryWrapper);
                //存入下面表的数据库(没有当前版本号存 有则修改)
                DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
                dbcsCopy1.setYxrl(jsonObject.getString("yxrl"));
                dbcsCopy1.setStd(jsonObject.getString("std"));
                dbcsCopy1.setAd(jsonObject.getString("ad"));
                dbcsCopy1.setVdaf(jsonObject.getString("vdaf"));
                dbcsCopy1.setFcd(jsonObject.getString("fcd"));
                dbcsCopy1.setMt(jsonObject.getString("mt"));
                dbcsCopy1.setLx(jsonObject.getString("lx"));
                dbcsCopy1.setJssf(jsonObject.getString("jssf"));
                dbcsCopy1.setVersion(jsonObject.getString("version"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setYfjg(jsonObject.getString("yfjg"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setDcjg(jsonObject.getString("dcjg"));
                dbcsCopy1.setMad(jsonObject.getString("mad"));
                dbcsCopy1.setPb(jsonObject.getString("pb"));
                dbcsCopy1.setCcjg(jsonObject.getString("ccjg"));
                dbcsCopy1.setGysmc(jsonObject.getString("gysmc"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setYxrcb(jsonObject.getString("yxrcb"));
                dbcsCopy1.setQgrd(jsonObject.getString("qgrd"));
                dbcsCopy1.setBhsjg(jsonObject.getString("bhsjg"));
                dbcsCopy1.setFkfs(jsonObject.getString("fkfs"));
                QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
                dbcsCopy1QueryWrapper.eq("gysmc", jsonObject.getString("gysmc"));
                dbcsCopy1QueryWrapper.eq("version", jsonObject.getString("version"));
                dbcsCopy1Mapper.update(dbcsCopy1, dbcsCopy1QueryWrapper);
            }

        }
        QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
        dbcsCopy1QueryWrapper.eq("version", ourbbhar);
        List<DbcsCopy1> dbcsCopy1s = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper);
        return dbcsCopy1s;
    }

    /**
     * 烟煤加无烟煤减少
     *
     * @param cetz
     * @param ourbbhar
     * @return
     */
    @RequestMapping("ymzjwymjs")
    public Object ymzjwymjs(String cetz, String ourbbhar) {
        ArrayList<DbcsCopy1> dbcsCopy1s = new ArrayList<>();
        String replace = cetz.replace("[", "").replace("]", "");
        String[] split = replace.split(",");
        for (int i = 0; i < split.length; i++) {
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
            dbcsCopy1QueryWrapper.eq("id", split[i]);
            DbcsCopy1 dbcsCopy1 = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper).get(0);
            dbcsCopy1s.add(dbcsCopy1);
        }
        if (dbcsCopy1s.get(0).getLx().equals("无烟煤") && dbcsCopy1s.get(1).getLx().equals("无烟煤")) {
            return 1;
        }
        if (dbcsCopy1s.get(0).getLx().equals("烟煤") && dbcsCopy1s.get(1).getLx().equals("烟煤")) {
            return 2;
        }
        if (dbcsCopy1s.get(0).getLx().equals("无烟煤")) {
            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
            dbcsCopy1.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(0).getPb()) - 1));
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
            dbcsCopy1QueryWrapper.eq("id", dbcsCopy1s.get(0).getId());
            dbcsCopy1Mapper.update(dbcsCopy1, dbcsCopy1QueryWrapper);
            DbcsCopy1 dbcsCopy11 = new DbcsCopy1();
            dbcsCopy11.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(1).getPb()) + 1));
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper1 = new QueryWrapper<>();
            dbcsCopy1QueryWrapper1.eq("id", dbcsCopy1s.get(1).getId());
            dbcsCopy1Mapper.update(dbcsCopy11, dbcsCopy1QueryWrapper1);
        } else {
            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
            dbcsCopy1.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(0).getPb()) + 1));
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
            dbcsCopy1QueryWrapper.eq("id", dbcsCopy1s.get(0).getId());
            dbcsCopy1Mapper.update(dbcsCopy1, dbcsCopy1QueryWrapper);
            DbcsCopy1 dbcsCopy11 = new DbcsCopy1();
            dbcsCopy11.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(1).getPb()) - 1));
            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper1 = new QueryWrapper<>();
            dbcsCopy1QueryWrapper1.eq("id", dbcsCopy1s.get(1).getId());
            dbcsCopy1Mapper.update(dbcsCopy11, dbcsCopy1QueryWrapper1);
        }
        QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
        dbcsCopy1QueryWrapper.eq("version", ourbbhar);
        List<DbcsCopy1> dbcsCopy1sr = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper);
        return dbcsCopy1sr;
    }

    /**
     * @param id
     * @param pb
     * @param ourbbhar
     * @return
     */
    @RequestMapping("ymjswymzj")
    public Object ymjswymzj(String cetz, String ourbbhar) {
        JSONArray jsonArray = JSONArray.parseArray(cetz);
        for (int i = 0; i < jsonArray.size(); i++) {
            String id = jsonArray.getJSONObject(i).getString("id");
            String pb = jsonArray.getJSONObject(i).getString("pb");
            QueryWrapper<DbcsCopy1> db = new QueryWrapper<>();
            db.eq("id", id);
            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
            dbcsCopy1.setPb(pb);
            dbcsCopy1Mapper.update(dbcsCopy1, db);
        }
        QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
        dbcsCopy1QueryWrapper.eq("version", ourbbhar);
        List<DbcsCopy1> dbcsCopy1sr = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper);
//        ArrayList<DbcsCopy1> dbcsCopy1s = new ArrayList<>();
//        String replace = cetz.replace("[", "").replace("]", "");
//        String[] split = replace.split(",");
//        for (int i = 0; i < split.length; i++) {
//            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
//            dbcsCopy1QueryWrapper.eq("id", split[i]);
//            DbcsCopy1 dbcsCopy1 = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper).get(0);
//            dbcsCopy1s.add(dbcsCopy1);
//        }
//        if (dbcsCopy1s.get(0).getLx().equals("无烟煤") && dbcsCopy1s.get(1).getLx().equals("无烟煤")) {
//            return 1;
//        }
//        if (dbcsCopy1s.get(0).getLx().equals("烟煤") && dbcsCopy1s.get(1).getLx().equals("烟煤")) {
//            return 2;
//        }
//        if (dbcsCopy1s.get(0).getLx().equals("无烟煤")) {
//            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
//            dbcsCopy1.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(0).getPb()) + 1));
//            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
//            dbcsCopy1QueryWrapper.eq("id", dbcsCopy1s.get(0).getId());
//            dbcsCopy1Mapper.update(dbcsCopy1, dbcsCopy1QueryWrapper);
//            DbcsCopy1 dbcsCopy11 = new DbcsCopy1();
//            dbcsCopy11.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(1).getPb()) - 1));
//            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper1 = new QueryWrapper<>();
//            dbcsCopy1QueryWrapper1.eq("id", dbcsCopy1s.get(1).getId());
//            dbcsCopy1Mapper.update(dbcsCopy11, dbcsCopy1QueryWrapper1);
//        } else {
//            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
//            dbcsCopy1.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(0).getPb()) - 1));
//            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
//            dbcsCopy1QueryWrapper.eq("id", dbcsCopy1s.get(0).getId());
//            dbcsCopy1Mapper.update(dbcsCopy1, dbcsCopy1QueryWrapper);
//            DbcsCopy1 dbcsCopy11 = new DbcsCopy1();
//            dbcsCopy11.setPb(String.valueOf(Integer.valueOf(dbcsCopy1s.get(1).getPb()) + 1));
//            QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper1 = new QueryWrapper<>();
//            dbcsCopy1QueryWrapper1.eq("id", dbcsCopy1s.get(1).getId());
//            dbcsCopy1Mapper.update(dbcsCopy11, dbcsCopy1QueryWrapper1);
//        }
//        QueryWrapper<DbcsCopy1> dbcsCopy1QueryWrapper = new QueryWrapper<>();
//        dbcsCopy1QueryWrapper.eq("version", ourbbhar);
//        List<DbcsCopy1> dbcsCopy1sr = dbcsCopy1Mapper.selectList(dbcsCopy1QueryWrapper);
        return dbcsCopy1sr;
    }

    /**
     * 修改基准值
     *
     * @param mby
     * @param jtyxrl
     * @param jtjg
     * @param zhxs
     * @return
     */
    @RequestMapping("brakeli")
    public Map<String, String> brakeli(String mby, String jtyxrl, String jtjg, String zhxs) {
        String a[] = {"焦炭有效热量", "煤比", "焦炭价格", "置换系数"};
        String b[] = {jtyxrl, mby, jtjg, zhxs};
        for (int i = 0; i < a.length; i++) {
            Butvalu butvalu = new Butvalu();
            butvalu.setPropertyValue(b[i]);
            QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
            butvaluQueryWrapper.eq("attribute_name", a[i]);
            butvaluMapper.update(butvalu, butvaluQueryWrapper);
        }
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", a[0]).or().eq("attribute_name", a[1]).or().eq("attribute_name", a[2]).or().eq("attribute_name", a[3]);
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < butvalus.size(); i++) {
            switch (butvalus.get(i).getAttributeName()) {
                case "焦炭有效热量":
                    shm.put("jtyxrl", butvalus.get(i).getPropertyValue());
                    break;
                case "煤比":
                    shm.put("mby", butvalus.get(i).getPropertyValue());
                    break;
                case "焦炭价格":
                    shm.put("jtjg", butvalus.get(i).getPropertyValue());
                    break;
                case "置换系数":
                    shm.put("zhxs", butvalus.get(i).getPropertyValue());
                    break;
                default:
                    break;
            }
        }
        return shm;
    }

    @RequestMapping("csujzz")
    public Map<String, String> csujzz() {
        String a[] = {"焦炭有效热量", "煤比", "焦炭价格", "置换系数"};
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", a[0]).or().eq("attribute_name", a[1]).or().eq("attribute_name", a[2]).or().eq("attribute_name", a[3]);
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        HashMap<String, String> shm = new HashMap<>();
        for (int i = 0; i < butvalus.size(); i++) {
            switch (butvalus.get(i).getAttributeName()) {
                case "焦炭有效热量":
                    shm.put("jtyxrl", butvalus.get(i).getPropertyValue());
                    break;
                case "煤比":
                    shm.put("mby", butvalus.get(i).getPropertyValue());
                    break;
                case "焦炭价格":
                    shm.put("jtjg", butvalus.get(i).getPropertyValue());
                    break;
                case "置换系数":
                    shm.put("zhxs", butvalus.get(i).getPropertyValue());
                    break;
                default:
                    break;
            }
        }
        return shm;
    }

    @RequestMapping("inserttscb")
    public void inserttscb(String tscb, String bbhs) {
        Butvalu butvalu = new Butvalu();
        butvalu.setAttributeName(bbhs);
        butvalu.setPropertyValue(tscb);
        butvaluMapper.insert(butvalu);
    }

    @RequestMapping("lhxnwym")
    public Object lhxnwym() {
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "无烟煤");
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        return nhxns;
    }

    @RequestMapping("updatelhxn")
    public List<Nhxn> updatelhxn(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Nhxn jc1 = new Nhxn();
            jc1.setGysmc(jsonObject.getString("gysmc"));
            jc1.setKmx(jsonObject.getString("kmx"));
            jc1.setZjx(jsonObject.getString("zjx"));
            jc1.setRsx(jsonObject.getString("rsx"));
            jc1.setFyx(jsonObject.getString("fyx"));
            jc1.setLdx(jsonObject.getString("ldx"));
            jc1.setPlx(jsonObject.getString("plx"));
            jc1.setHrd(jsonObject.getString("hrd"));
            jc1.setZhd(jsonObject.getString("zhd"));
            QueryWrapper<Nhxn> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            nhxnMapper.update(jc1, jqw);
        }
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "无烟煤");
        List<Nhxn> jp1s = nhxnMapper.selectList(nhxnQueryWrapper);
        return jp1s;
    }

    @RequestMapping("insertlhxn")
    public List<Nhxn> insertlhxn(String mc, String kmx, String zjx, String rsx,
                                 String fyx, String ldx, String plx, String hrd, String zhd) {
        Nhxn nhxn = new Nhxn();
        nhxn.setGysmc(mc);
        nhxn.setKmx(kmx);
        nhxn.setZjx(zjx);
        nhxn.setRsx(rsx);
        nhxn.setFyx(fyx);
        nhxn.setLdx(ldx);
        nhxn.setPlx(plx);
        nhxn.setHrd(hrd);
        nhxn.setZhd(zhd);
        nhxn.setLx("无烟煤");
        nhxnMapper.insert(nhxn);
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "无烟煤");
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        return nhxns;
    }

    @RequestMapping("selectlhxnlit")
    public Object selectlhxnlit(String wz) {
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "无烟煤");
        String cur = "";
        switch (wz) {
            case "可磨性":
                cur = "kmx";
                break;
            case "粘结性":
                cur = "zjx";
                break;
            case "燃烧性":
                cur = "rsx";
                break;
            case "反应性":
                cur = "fyx";
                break;
            case "流动性":
                cur = "ldx";
                break;
            case "喷流性":
                cur = "plx";
                break;
            case "灰熔点":
                cur = "hrd";
                break;
            case "着火点":
                cur = "zhd";
                break;
            default:
                break;
        }
        nhxnQueryWrapper.select("gysmc", cur);
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < nhxns.size(); i++) {
            String cop = "";
            switch (wz) {
                case "可磨性":
                    cop = nhxns.get(i).getKmx();
                    break;
                case "粘结性":
                    cop = nhxns.get(i).getZjx();
                    break;
                case "燃烧性":
                    cop = nhxns.get(i).getRsx();
                    break;
                case "反应性":
                    cop = nhxns.get(i).getFyx();
                    break;
                case "流动性":
                    cop = nhxns.get(i).getLdx();
                    break;
                case "喷流性":
                    cop = nhxns.get(i).getPlx();
                    break;
                case "灰熔点":
                    cop = nhxns.get(i).getHrd();
                    break;
                case "着火点":
                    cop = nhxns.get(i).getZhd();
                    break;
                default:
                    break;
            }
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("gysmc", nhxns.get(i).getGysmc());
            stringStringHashMap.put("value", cop);
            maps.add(stringStringHashMap);
        }
        return maps;
    }

    @RequestMapping("lhxnym")
    public Object lhxnym() {
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "烟煤");
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        return nhxns;
    }

    @RequestMapping("updatelhxna")
    public List<Nhxn> updatelhxna(String bzjhcsl) {
        JSONArray jsonArray = JSONArray.parseArray(bzjhcsl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Nhxn jc1 = new Nhxn();
            jc1.setGysmc(jsonObject.getString("gysmc"));
            jc1.setKmx(jsonObject.getString("kmx"));
            jc1.setZjx(jsonObject.getString("zjx"));
            jc1.setRsx(jsonObject.getString("rsx"));
            jc1.setFyx(jsonObject.getString("fyx"));
            jc1.setLdx(jsonObject.getString("ldx"));
            jc1.setPlx(jsonObject.getString("plx"));
            jc1.setHrd(jsonObject.getString("hrd"));
            jc1.setZhd(jsonObject.getString("zhd"));
            QueryWrapper<Nhxn> jqw = new QueryWrapper<>();
            jqw.eq("id", jsonObject.getString("id"));
            nhxnMapper.update(jc1, jqw);
        }
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "烟煤");
        List<Nhxn> jp1s = nhxnMapper.selectList(nhxnQueryWrapper);
        return jp1s;
    }

    @RequestMapping("insertlhxna")
    public List<Nhxn> insertlhxna(String mc, String kmx, String zjx, String rsx,
                                  String fyx, String ldx, String plx, String hrd, String zhd) {
        Nhxn nhxn = new Nhxn();
        nhxn.setGysmc(mc);
        nhxn.setKmx(kmx);
        nhxn.setZjx(zjx);
        nhxn.setRsx(rsx);
        nhxn.setFyx(fyx);
        nhxn.setLdx(ldx);
        nhxn.setPlx(plx);
        nhxn.setHrd(hrd);
        nhxn.setZhd(zhd);
        nhxn.setLx("烟煤");
        nhxnMapper.insert(nhxn);
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "烟煤");
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        return nhxns;
    }

    @RequestMapping("selectlhxnlitym")
    public Object selectlhxnlitym(String wz) {
        QueryWrapper<Nhxn> nhxnQueryWrapper = new QueryWrapper<>();
        nhxnQueryWrapper.eq("lx", "烟煤");
        String cur = "";
        switch (wz) {
            case "可磨性":
                cur = "kmx";
                break;
            case "粘结性":
                cur = "zjx";
                break;
            case "燃烧性":
                cur = "rsx";
                break;
            case "反应性":
                cur = "fyx";
                break;
            case "流动性":
                cur = "ldx";
                break;
            case "喷流性":
                cur = "plx";
                break;
            case "灰熔点":
                cur = "hrd";
                break;
            case "着火点":
                cur = "zhd";
                break;
            default:
                break;
        }
        nhxnQueryWrapper.select("gysmc", cur);
        List<Nhxn> nhxns = nhxnMapper.selectList(nhxnQueryWrapper);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < nhxns.size(); i++) {
            String cop = "";
            switch (wz) {
                case "可磨性":
                    cop = nhxns.get(i).getKmx();
                    break;
                case "粘结性":
                    cop = nhxns.get(i).getZjx();
                    break;
                case "燃烧性":
                    cop = nhxns.get(i).getRsx();
                    break;
                case "反应性":
                    cop = nhxns.get(i).getFyx();
                    break;
                case "流动性":
                    cop = nhxns.get(i).getLdx();
                    break;
                case "喷流性":
                    cop = nhxns.get(i).getPlx();
                    break;
                case "灰熔点":
                    cop = nhxns.get(i).getHrd();
                    break;
                case "着火点":
                    cop = nhxns.get(i).getZhd();
                    break;
                default:
                    break;
            }
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("gysmc", nhxns.get(i).getGysmc());
            stringStringHashMap.put("value", cop);
            maps.add(stringStringHashMap);
        }
        return maps;
    }

    /**
     * @param lieseletone(元素名称)
     * @param distributorNames(供应商名称) 历史弹窗柱状图数据(根据元素和供应商名称查询)
     */
    @RequestMapping("selectsixmtachrwym")
    public Object selectsixmtachrwym(String lieseletone, String distributorNames) {
        String ta[] = {"Ad", "Vdaf", "Fcad", "Std", "M25", "CRI", "CSR", "Mt"};
        String tb[] = {"K", "Na", "Zn"};
        String a[] = new String[6];
        String b[] = new String[6];
        for (int i = 0; i < a.length; i++) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //获取前月的第一天
            Calendar cal_1 = Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.MONTH, -i);
            cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String firstDay = format.format(cal_1.getTime());
            a[i] = firstDay;
            //获取前月的最后一天
            //获取当前月第一天：
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -i + 1);
            c.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
            String lastDay = format.format(c.getTime());
            b[i] = lastDay;
        }
        ArrayList<Map<String, String>> clist = new ArrayList<>();
        List<String> strings = Arrays.asList(ta);
        for (int i = 0; i < a.length; i++) {
            HashMap<String, String> sshm = new HashMap<>();
            QueryWrapper<Gcilma> jqw = new QueryWrapper<>();
            jqw.between("jjrq", a[i], b[i]);
            if (distributorNames.indexOf("/") != -1) {
                jqw.like("gysmc", distributorNames.split("/")[0]);
                jqw.like("fhdd", distributorNames.split("/")[1]);
            } else {
                jqw.like("gysmc", distributorNames);
            }
            List<Gcilma> jtgrs = gcilmaMapper.selectList(jqw);

            String gallcrs = "";
            if (ObjectUtils.isEmpty(jtgrs)) {
                sshm.put("cme", gallcrs);
                sshm.put("cme1", a[i]);
                clist.add(sshm);
                continue;
            }
            switch (lieseletone) {
                case "Ad":
                    gallcrs = jtgrs.get(0).getAd();
                    break;
                case "Vdaf":
                    gallcrs = jtgrs.get(0).getVdaf();
                    break;
                case "FCd":
                    gallcrs = jtgrs.get(0).getFcd();
                    break;
                case "St,d":
                    gallcrs = jtgrs.get(0).getStD();
                    break;
                case "Qgr,d":
                    gallcrs = jtgrs.get(0).getQgrD();
                    break;
                case "Mt":
                    gallcrs = jtgrs.get(0).getMt();
                    break;
                case "Mad":
                    gallcrs = jtgrs.get(0).getMad();
                    break;
                default:
                    break;

            }
            sshm.put("cme", gallcrs);
            sshm.put("cme1", a[i]);
            clist.add(sshm);
        }
        return clist;
    }

    @RequestMapping("routerairnw")
    public Map<String, List<Object>> routerairnw(String ourbbhar) {
        HashMap<String, List<Object>> stringListHashMap = new HashMap<>();
        //默认用上一个版本的配比 变更前
        QueryWrapper<Dbcs> dbcsQueryWrapper1 = new QueryWrapper<>();
        dbcsQueryWrapper1.groupBy("version");
        dbcsQueryWrapper1.orderByDesc("version");
        dbcsQueryWrapper1.last("limit 1");
        Dbcs dbcs1 = dbcsMapper.selectList(dbcsQueryWrapper1).get(0);
        QueryWrapper<Dbcs> dbcsQueryWrapper2 = new QueryWrapper<>();
        dbcsQueryWrapper2.eq("version", dbcs1.getVersion());
        List<Dbcs> dbcs2 = dbcsMapper.selectList(dbcsQueryWrapper2);
        HashMap<String, String> sgm = new HashMap<>();
        for (int i = 0; i < dbcs2.size(); i++) {
            sgm.put(dbcs2.get(i).getGysmc(), dbcs2.get(i).getPb());
        }
        //查询烟煤和无烟煤都在一起的表数据
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(null);
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat decimalFormat = new DecimalFormat("0.00000");
        for (int i = 0; i < wymdcwhs.size(); i++) {
            //对比测算过上面表的对象
            Dbcs dbcs = new Dbcs();
            dbcs.setLx(wymdcwhs.get(i).getRllx());
            dbcs.setGysmc(wymdcwhs.get(i).getDistributorName());
            dbcs.setFkfs(wymdcwhs.get(i).getSterilise());
            dbcs.setCcjg(wymdcwhs.get(i).getContractPrice());
            dbcs.setJssf(wymdcwhs.get(i).getJssf());
            dbcs.setYfjg(wymdcwhs.get(i).getJsfm());
            dbcs.setDcjg(df.format(Double.valueOf(dbcs.getCcjg()) + Double.valueOf(dbcs.getYfjg())));
            //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
            if (wymdcwhs.get(i).getSterilise().equals("承兑")) {
                dbcs.setBhsjg(df.format(Double.valueOf(dbcs.getCcjg()) / 1.02 / 1.13 / ((100 - Double.valueOf(dbcs.getJssf())) / 100) +
                        (Double.valueOf(dbcs.getYfjg())) / 1.09));
            } else {
                dbcs.setBhsjg(df.format(Double.valueOf(dbcs.getCcjg()) / 1.13 / ((100 - Double.valueOf(dbcs.getJssf())) / 100) +
                        (Double.valueOf(dbcs.getYfjg())) / 1.09));
            }
            if (dbcs.getLx().equals("无烟煤")) {
                //无烟煤基础数据
                QueryWrapper<Wymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtwq);
                Wymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            } else {
                //无烟煤基础数据
                QueryWrapper<Ymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(jtwq);
                Ymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            }
            HashMap<String, String> shm = new HashMap<>();
            HashMap<String, String> jsqtpjz = jsqtpjzwym(dbcs.getGysmc(), ourbbhar, shm);
            if (jsqtpjz.containsKey("Ad")) {
                dbcs.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                dbcs.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                dbcs.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                dbcs.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                dbcs.setQgrd(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                dbcs.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            //无烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            if (dbcs.getLx().equals("无烟煤")) {
                dbcs.setYxrl(df.format(9400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            } else {
                dbcs.setYxrl(df.format(8400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            }
            //单位有效热量成本 不含税价格/有效热量
            dbcs.setYxrcb(decimalFormat.format(Double.valueOf(dbcs.getBhsjg()) / Double.valueOf(dbcs.getYxrl())));
            dbcs.setPb(ObjectUtils.isEmpty(sgm.get(dbcs.getGysmc())) ? "0" : sgm.get(dbcs.getGysmc()));
            dbcs.setVersion(ourbbhar);
            dbcsMapper.insert(dbcs);
        }
        QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
        dbcsQueryWrapper.eq("version", ourbbhar);
        List<Dbcs> dbcs = dbcsMapper.selectList(dbcsQueryWrapper);
        //基准值信息
        Map<String, String> csujzz = csujzz();
        Bgqhsj bgqhsj = new Bgqhsj();
        //变更前有效热量
        Double bgqyxrl = 0.0;
        //变更前不含税价格
        Double bgqbhsjg = 0.0;
        //变更前置换比
        Double bgqzhb = 0.0;
        //变更后有效热量
        Double bghyxrl = 0.0;
        //变更后不含税价格
        Double bghbhsjg = 0.0;
        //变更后置换比
        Double bghzhb = 0.0;
        Double a1 = 0.0;
        Double b2 = 0.0;
        for (int i = 0; i < dbcs.size(); i++) {
            bgqbhsjg = bgqbhsjg + Double.valueOf(dbcs.get(i).getBhsjg()) * Double.valueOf(dbcs.get(i).getPb()) / 100;
            bgqyxrl = bgqyxrl + (Double.valueOf(dbcs.get(i).getPb()) / 100 * 22 * (100 - Double.valueOf(dbcs.get(i).getMt()) - Double.valueOf(dbcs.get(i).getMad())) * Double.valueOf(dbcs.get(i).getYxrl()));
            a1 = a1 + Double.valueOf(dbcs.get(i).getPb()) / 100 * 22 * (100 - Double.valueOf(dbcs.get(i).getMt()) - Double.valueOf(dbcs.get(i).getMad()));
        }
        bgqhsj.setBgqyxrl(df.format(bgqyxrl/a1));
        bgqhsj.setBgqbhsjg(df.format(bgqbhsjg));
        bgqhsj.setBgqzhb(df.format(bgqyxrl / Double.valueOf(csujzz.get("jtyxrl"))));
        List<DbcsCopy1> routerairnwh = routerairnwh(ourbbhar);
        for (int i = 0; i < routerairnwh.size(); i++) {
            bghyxrl = bghyxrl + (Double.valueOf(routerairnwh.get(i).getPb()) / 100 * 22 * (100 - Double.valueOf(routerairnwh.get(i).getMt()) - Double.valueOf(routerairnwh.get(i).getMad())) * Double.valueOf(routerairnwh.get(i).getYxrl()));
            bghbhsjg = bghbhsjg + Double.valueOf(routerairnwh.get(i).getBhsjg()) * Double.valueOf(routerairnwh.get(i).getPb()) / 100;
            b2 = b2 + Double.valueOf(routerairnwh.get(i).getPb()) / 100 * 22 * (100 - Double.valueOf(routerairnwh.get(i).getMt()) - Double.valueOf(routerairnwh.get(i).getMad()));

        }
        bgqhsj.setBghyxrl(df.format(bghyxrl/b2));
        bgqhsj.setBghbhsjg(df.format(bghbhsjg));
        bgqhsj.setBghzhb(df.format(bghyxrl / Double.valueOf(csujzz.get("jtyxrl"))));
        bgqhsj.setTscb(df.format((bghbhsjg - bgqbhsjg) * Double.valueOf(csujzz.get("mby")) / 1000 - (bghzhb - bgqzhb) * 0.7 * Double.valueOf(csujzz.get("mby")) / 1000 * Double.valueOf(csujzz.get("jtjg"))));
        bgqhsj.setVersion(ourbbhar);
        bgqhsjMapper.insert(bgqhsj);
        QueryWrapper<Bgqhsj> bgqhsjQueryWrapper = new QueryWrapper<>();
        bgqhsjQueryWrapper.eq("version", ourbbhar);
        Bgqhsj bgqhsj1 = bgqhsjMapper.selectList(bgqhsjQueryWrapper).get(0);
        //计算变更前平均值
        stringListHashMap.put("bgq", Collections.singletonList(dbcs));
        stringListHashMap.put("bgh", Collections.singletonList(routerairnwh));
        stringListHashMap.put("bgqhsjx", Collections.singletonList(bgqhsj1));
        //箭头指向
        //先判断but表是否有当前版本数据
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", ourbbhar);
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        if (ObjectUtils.isEmpty(butvalus)) {
            //变更前无烟煤配比
            Double bgqwympb = 0.0;
            //变更后无烟煤配比
            Double bghwympb = 0.0;
            for (int i = 0; i < dbcs.size(); i++) {
                if (dbcs.get(i).getLx().equals("无烟煤")) {
                    bgqwympb = bgqwympb + Double.valueOf(dbcs.get(i).getPb());
                }
            }
            for (int i = 0; i < routerairnwh.size(); i++) {
                if (routerairnwh.get(i).getLx().equals("无烟煤")) {
                    bghwympb = bghwympb + Double.valueOf(routerairnwh.get(i).getPb());
                }
            }
            Butvalu butvalu = new Butvalu();
            butvalu.setAttributeName(ourbbhar);
            if (Double.valueOf(bgqhsj.getTscb()) >= 0) {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("无烟煤");
                } else {
                    butvalu.setPropertyValue("烟煤");
                }
            } else {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("烟煤");
                } else {
                    butvalu.setPropertyValue("无烟煤");
                }
            }
            butvaluMapper.insert(butvalu);
        } else {
            QueryWrapper<Butvalu> butvaluQueryWrapper1 = new QueryWrapper<>();
            butvaluQueryWrapper1.eq("attribute_name", ourbbhar);
            //变更前无烟煤配比
            Double bgqwympb = 0.0;
            //变更后无烟煤配比
            Double bghwympb = 0.0;
            for (int i = 0; i < dbcs.size(); i++) {
                if (dbcs.get(i).getLx().equals("无烟煤")) {
                    bgqwympb = bgqwympb + Double.valueOf(dbcs.get(i).getPb());
                }
            }
            for (int i = 0; i < routerairnwh.size(); i++) {
                if (routerairnwh.get(i).getLx().equals("无烟煤")) {
                    bghwympb = bghwympb + Double.valueOf(routerairnwh.get(i).getPb());
                }
            }
            Butvalu butvalu = new Butvalu();
            if (Double.valueOf(bgqhsj.getTscb()) >= 0) {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("无烟煤");
                } else {
                    butvalu.setPropertyValue("烟煤");
                }
            } else {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("烟煤");
                } else {
                    butvalu.setPropertyValue("无烟煤");
                }
            }
            butvaluMapper.update(butvalu, butvaluQueryWrapper1);
        }
        return stringListHashMap;
    }

    @RequestMapping("routerairnwh")
    public List<DbcsCopy1> routerairnwh(String ourbbhar) {
        //默认用上一个版本的配比 变更后
        QueryWrapper<DbcsCopy1> dbcsQueryWrapper1 = new QueryWrapper<>();
        dbcsQueryWrapper1.groupBy("version");
        dbcsQueryWrapper1.orderByDesc("version");
        dbcsQueryWrapper1.last("limit 1");
        DbcsCopy1 dbcs1 = dbcsCopy1Mapper.selectList(dbcsQueryWrapper1).get(0);
        QueryWrapper<DbcsCopy1> dbcsQueryWrapper2 = new QueryWrapper<>();
        dbcsQueryWrapper2.eq("version", dbcs1.getVersion());
        List<DbcsCopy1> dbcs2 = dbcsCopy1Mapper.selectList(dbcsQueryWrapper2);
        HashMap<String, String> sgm = new HashMap<>();
        for (int i = 0; i < dbcs2.size(); i++) {
            sgm.put(dbcs2.get(i).getGysmc(), dbcs2.get(i).getPb());
        }
        //查询烟煤和无烟煤都在一起的表数据
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(null);
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat decimalFormat = new DecimalFormat("0.00000");
        for (int i = 0; i < wymdcwhs.size(); i++) {
            //对比测算过上面表的对象
            DbcsCopy1 dbcs = new DbcsCopy1();
            dbcs.setLx(wymdcwhs.get(i).getRllx());
            dbcs.setGysmc(wymdcwhs.get(i).getDistributorName());
            dbcs.setFkfs(wymdcwhs.get(i).getSterilise());
            dbcs.setCcjg(wymdcwhs.get(i).getContractPrice());
            dbcs.setJssf(wymdcwhs.get(i).getJssf());
            dbcs.setYfjg(wymdcwhs.get(i).getJsfm());
            dbcs.setDcjg(df.format(Double.valueOf(dbcs.getCcjg()) + Double.valueOf(dbcs.getYfjg())));
            if (wymdcwhs.get(i).getSterilise().equals("承兑")) {
                //不含税价格 出厂价格/1.02/1.13/（100-结算水分）/100+运费价格/1.09
                dbcs.setBhsjg(df.format(Double.valueOf(dbcs.getCcjg()) / 1.02 / 1.13 / ((100 - Double.valueOf(dbcs.getJssf())) / 100) +
                        (Double.valueOf(dbcs.getYfjg())) / 1.09));
            } else {
                //不含税价格 出厂价格/1.13/（100-结算水分）/100+运费价格/1.09
                dbcs.setBhsjg(df.format(Double.valueOf(dbcs.getCcjg()) / 1.13 / ((100 - Double.valueOf(dbcs.getJssf())) / 100) +
                        (Double.valueOf(dbcs.getYfjg())) / 1.09));
            }
            if (dbcs.getLx().equals("无烟煤")) {
                //无烟煤基础数据
                QueryWrapper<Wymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Wymjc> jtbizhbCopy1s = wymjcMapper.selectList(jtwq);
                Wymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            } else {
                //无烟煤基础数据
                QueryWrapper<Ymjc> jtwq = new QueryWrapper<>();
                jtwq.eq("gysmc", dbcs.getGysmc());
                List<Ymjc> jtbizhbCopy1s = ymjcMapper.selectList(jtwq);
                Ymjc jtbizhbCopy1 = jtbizhbCopy1s.get(0);
                dbcs.setAd(df.format(Double.valueOf(jtbizhbCopy1.getAd())));
                dbcs.setStd(df.format(Double.valueOf(jtbizhbCopy1.getStD())));
                dbcs.setQgrd(df.format(Double.valueOf(jtbizhbCopy1.getQgrD())));
                dbcs.setVdaf(df.format(Double.valueOf(jtbizhbCopy1.getVdaf())));
                dbcs.setFcd(df.format(Double.valueOf(jtbizhbCopy1.getFcd())));
                dbcs.setMt(df.format(Double.valueOf(jtbizhbCopy1.getMt())));
                dbcs.setMad(df.format(Double.valueOf(jtbizhbCopy1.getMad())));
            }
            HashMap<String, String> shm = new HashMap<>();
            HashMap<String, String> jsqtpjz = jsqtpjzwym(dbcs.getGysmc(), ourbbhar, shm);
            if (jsqtpjz.containsKey("Ad")) {
                dbcs.setAd(df.format(Double.valueOf(jsqtpjz.get("Ad"))));
            }
            if (jsqtpjz.containsKey("Vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("Vdaf"))));
            }
            if (jsqtpjz.containsKey("Fcd")) {
                dbcs.setFcd(df.format(Double.valueOf(jsqtpjz.get("Fcd"))));
            }
            if (jsqtpjz.containsKey("Mad")) {
                dbcs.setMad(df.format(Double.valueOf(jsqtpjz.get("Mad"))));
            }
            if (jsqtpjz.containsKey("Mt")) {
                dbcs.setMt(df.format(Double.valueOf(jsqtpjz.get("Mt"))));
            }
            if (jsqtpjz.containsKey("qgrd")) {
                dbcs.setQgrd(df.format(Double.valueOf(jsqtpjz.get("qgrd"))));
            }
            if (jsqtpjz.containsKey("Std")) {
                dbcs.setStd(df.format(Double.valueOf(jsqtpjz.get("Std"))));
            }
            if (jsqtpjz.containsKey("vdaf")) {
                dbcs.setVdaf(df.format(Double.valueOf(jsqtpjz.get("vdaf"))));
            }
            //无烟煤有效热量 9400*FCd-2800*Ad-20000*St,d
            if (dbcs.getLx().equals("无烟煤")) {
                dbcs.setYxrl(df.format(9400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            } else {
                dbcs.setYxrl(df.format(8400 * Double.valueOf(dbcs.getFcd()) - 2800 * Double.valueOf(dbcs.getAd())
                        - 20000 * Double.valueOf(dbcs.getStd())));
            }
            //单位有效热量成本 不含税价格/有效热量
            dbcs.setYxrcb(decimalFormat.format(Double.valueOf(dbcs.getBhsjg()) / Double.valueOf(dbcs.getYxrl())));
            dbcs.setPb(ObjectUtils.isEmpty(sgm.get(dbcs.getGysmc())) ? "0" : sgm.get(dbcs.getGysmc()));
            dbcs.setVersion(ourbbhar);
            dbcsCopy1Mapper.insert(dbcs);
        }
        QueryWrapper<DbcsCopy1> dbcsQueryWrapper = new QueryWrapper<>();
        dbcsQueryWrapper.eq("version", ourbbhar);
        List<DbcsCopy1> dbcs = dbcsCopy1Mapper.selectList(dbcsQueryWrapper);
        return dbcs;
    }

    @RequestMapping("togererork")
    public Object togererork(String ourbbhar, String topraslists, String upcatuicours) {

        JSONArray jsonArray = JSONArray.parseArray(topraslists);
        JSONArray jsonArray1 = JSONArray.parseArray(upcatuicours);
        for (int i = 0; i < jsonArray.size(); i++) {
            String gysmc = jsonArray.getJSONObject(i).getString("gysmc");
            if (gysmc.equals("变更前配煤指标") || gysmc.equals("变更后配煤指标") || gysmc.equals("配煤指标差异")) {
                continue;
            }
            String pb = jsonArray.getJSONObject(i).getString("pb");
            Dbcs dbcs = new Dbcs();
            dbcs.setPb(pb);
            QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
            dbcsQueryWrapper.eq("gysmc", gysmc);
            dbcsQueryWrapper.eq("version", ourbbhar);
            dbcsMapper.update(dbcs, dbcsQueryWrapper);
        }
        for (int i = 0; i < jsonArray1.size(); i++) {
            String gysmc = jsonArray1.getJSONObject(i).getString("gysmc");
            if (gysmc.equals("变更前配煤指标") || gysmc.equals("变更后配煤指标") || gysmc.equals("配煤指标差异")) {
                continue;
            }
            String pb = jsonArray1.getJSONObject(i).getString("pb");
            DbcsCopy1 dbcsCopy1 = new DbcsCopy1();
            dbcsCopy1.setPb(pb);
            QueryWrapper<DbcsCopy1> dbcsQueryWrapper = new QueryWrapper<>();
            dbcsQueryWrapper.eq("gysmc", gysmc);
            dbcsQueryWrapper.eq("version", ourbbhar);
            dbcsCopy1Mapper.update(dbcsCopy1, dbcsQueryWrapper);
        }
        HashMap<String, List<Object>> stringListHashMap = new HashMap<>();
        QueryWrapper<Dbcs> dbcsQueryWrapper = new QueryWrapper<>();
        dbcsQueryWrapper.eq("version", ourbbhar);
        List<Dbcs> dbcs = dbcsMapper.selectList(dbcsQueryWrapper);
        stringListHashMap.put("bgq", Collections.singletonList(dbcs));

        QueryWrapper<DbcsCopy1> dbcsQueryWrapper1 = new QueryWrapper<>();
        dbcsQueryWrapper1.eq("version", ourbbhar);
        List<DbcsCopy1> dbcsCopy1s = dbcsCopy1Mapper.selectList(dbcsQueryWrapper1);
        stringListHashMap.put("bgh", Collections.singletonList(dbcsCopy1s));
        DecimalFormat df = new DecimalFormat("0.00");
        //基准值信息
        Map<String, String> csujzz = csujzz();
        Bgqhsj bgqhsj = new Bgqhsj();
        //变更前有效热量
        Double bgqyxrl = 0.0;
        //变更前不含税价格
        Double bgqbhsjg = 0.0;
        //变更前置换比
        Double bgqzhb = 0.0;
        //变更后有效热量
        Double bghyxrl = 0.0;
        //变更后不含税价格
        Double bghbhsjg = 0.0;
        //变更后置换比
        Double bghzhb = 0.0;
        for (int i = 0; i < dbcs.size(); i++) {
            bgqyxrl = bgqyxrl + Double.valueOf(dbcs.get(i).getYxrl()) * Double.valueOf(dbcs.get(i).getPb()) / 100;
            bgqbhsjg = bgqbhsjg + Double.valueOf(dbcs.get(i).getBhsjg()) * Double.valueOf(dbcs.get(i).getPb()) / 100;
        }
        bgqhsj.setBgqyxrl(df.format(bgqyxrl));
        bgqhsj.setBgqbhsjg(df.format(bgqbhsjg));
        bgqhsj.setBgqzhb(df.format(bgqyxrl / Double.valueOf(csujzz.get("jtyxrl"))));
        //List<DbcsCopy1> routerairnwh = routerairnwh(ourbbhar);
        for (int i = 0; i < dbcsCopy1s.size(); i++) {
            bghyxrl = bghyxrl + Double.valueOf(dbcsCopy1s.get(i).getYxrl()) * Double.valueOf(dbcsCopy1s.get(i).getPb()) / 100;
            bghbhsjg = bghbhsjg + Double.valueOf(dbcsCopy1s.get(i).getBhsjg()) * Double.valueOf(dbcsCopy1s.get(i).getPb()) / 100;
        }
        bgqhsj.setBghyxrl(df.format(bghyxrl));
        bgqhsj.setBghbhsjg(df.format(bghbhsjg));
        bgqhsj.setBghzhb(df.format(bghyxrl / Double.valueOf(csujzz.get("jtyxrl"))));
        bgqhsj.setTscb(df.format((bghbhsjg - bgqbhsjg) * Double.valueOf(csujzz.get("mby")) / 1000 - (bghzhb - bgqzhb) * 0.7 * Double.valueOf(csujzz.get("mby")) / 1000 * Double.valueOf(csujzz.get("jtjg"))));
        bgqhsj.setVersion(ourbbhar);
        QueryWrapper<Bgqhsj> bgqhsjQueryWrapper1 = new QueryWrapper<>();
        bgqhsjQueryWrapper1.eq("version", ourbbhar);
        bgqhsjMapper.update(bgqhsj, bgqhsjQueryWrapper1);
        QueryWrapper<Bgqhsj> bgqhsjQueryWrapper = new QueryWrapper<>();
        bgqhsjQueryWrapper.eq("version", ourbbhar);
        Bgqhsj bgqhsj1 = bgqhsjMapper.selectList(bgqhsjQueryWrapper).get(0);
        stringListHashMap.put("bgqhsjx", Collections.singletonList(bgqhsj1));
        //箭头指向
        //先判断but表是否有当前版本数据
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", ourbbhar);
        List<Butvalu> butvalus = butvaluMapper.selectList(butvaluQueryWrapper);
        if (ObjectUtils.isEmpty(butvalus)) {
            //变更前无烟煤配比
            Double bgqwympb = 0.0;
            //变更后无烟煤配比
            Double bghwympb = 0.0;
            for (int i = 0; i < dbcs.size(); i++) {
                if (dbcs.get(i).getLx().equals("无烟煤")) {
                    bgqwympb = bgqwympb + Double.valueOf(dbcs.get(i).getPb());
                }
            }
            for (int i = 0; i < dbcsCopy1s.size(); i++) {
                if (dbcsCopy1s.get(i).getLx().equals("无烟煤")) {
                    bghwympb = bghwympb + Double.valueOf(dbcsCopy1s.get(i).getPb());
                }
            }
            Butvalu butvalu = new Butvalu();
            butvalu.setAttributeName(ourbbhar);
            if (Double.valueOf(bgqhsj.getTscb()) >= 0) {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("无烟煤");
                } else {
                    butvalu.setPropertyValue("烟煤");
                }
            } else {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("烟煤");
                } else {
                    butvalu.setPropertyValue("无烟煤");
                }
            }
            butvaluMapper.insert(butvalu);
        } else {
            QueryWrapper<Butvalu> butvaluQueryWrapper1 = new QueryWrapper<>();
            butvaluQueryWrapper1.eq("attribute_name", ourbbhar);
            //变更前无烟煤配比
            Double bgqwympb = 0.0;
            //变更后无烟煤配比
            Double bghwympb = 0.0;
            for (int i = 0; i < dbcs.size(); i++) {
                if (dbcs.get(i).getLx().equals("无烟煤")) {
                    bgqwympb = bgqwympb + Double.valueOf(dbcs.get(i).getPb());
                }
            }
            for (int i = 0; i < dbcsCopy1s.size(); i++) {
                if (dbcsCopy1s.get(i).getLx().equals("无烟煤")) {
                    bghwympb = bghwympb + Double.valueOf(dbcsCopy1s.get(i).getPb());
                }
            }
            Butvalu butvalu = new Butvalu();
            if (Double.valueOf(bgqhsj.getTscb()) >= 0) {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("无烟煤");
                } else {
                    butvalu.setPropertyValue("烟煤");
                }
            } else {
                if (bgqwympb > bghwympb) {
                    butvalu.setPropertyValue("烟煤");
                } else {
                    butvalu.setPropertyValue("无烟煤");
                }
            }
            butvaluMapper.update(butvalu, butvaluQueryWrapper1);
        }

        return stringListHashMap;
    }

    //采购决策 测算焦粉无烟煤和配比
    @RequestMapping("groupate")
    public String groupate(String idrs) {
        String ourbbhar = selectjcdisallarl(idrs);
        Map<String, List<Object>> routerairnw = routerairnw(ourbbhar);
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", ourbbhar);
        Butvalu butvalu = butvaluMapper.selectList(butvaluQueryWrapper).get(0);
        return butvalu.getPropertyValue();
    }

    @RequestMapping("seledctpdxzes")
    public String seledctpdxzes(String bbh) {
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", bbh);
        Butvalu butvalu = butvaluMapper.selectList(butvaluQueryWrapper).get(0);
        String propertyValue = butvalu.getPropertyValue();
        return propertyValue;
    }

    /**
     * 采购决策定时器
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void groupatedsqi() {
        QueryWrapper<Wymdcwh> wqw = new QueryWrapper<>();
        wqw.eq("rllx", "无烟煤");
        List<Wymdcwh> wymdcwhs = wymdcwhMapper.selectList(wqw);
        String a = "";
        for (int i = 0; i < wymdcwhs.size(); i++) {
            a = a + "," + wymdcwhs.get(i).getId();
        }
        String substring = a.substring(1);
        String idrs = "[" + substring + "]";
        String ourbbhar = selectjcdisallarl(idrs);
        Map<String, List<Object>> routerairnw = routerairnw(ourbbhar);
    }

    @RequestMapping("deletewymdcjc")
    public List<Jcwy> deletewymdcjc(String id) {
        QueryWrapper<Jcwy> wymdcwhQueryWrapper = new QueryWrapper<>();
        wymdcwhQueryWrapper.eq("id", id);
        List<Jcwy> wymdcwhs1 = jcwyMapper.selectList(wymdcwhQueryWrapper);
        Jcwy wymdcwh = wymdcwhs1.get(0);
        jcwyMapper.delete(wymdcwhQueryWrapper);
        QueryWrapper<Butvalu> butvaluQueryWrapper = new QueryWrapper<>();
        butvaluQueryWrapper.eq("attribute_name", "燃料名称");
        butvaluQueryWrapper.eq("property_value", wymdcwh.getDistributorName());
        butvaluMapper.delete(butvaluQueryWrapper);
        List<Jcwy> wymdcwhs = jcwyMapper.selectList(null);
        return wymdcwhs;
    }

    /**
     * 初始化查询无烟煤顶层标准维护
     */
    @RequestMapping("selectjtdcxxwhwymfw")
    public List<Wymfw> selectjtdcxxwhwymfw() {
        List<Wymfw> wymfws = wymfwMapper.selectList(null);
        return wymfws;
    }

    /**
     * 初始化查询烟煤顶层标准维护
     */
    @RequestMapping("selectjtdcxxwhymfw")
    public List<Ymfw> selectjtdcxxwhymfw() {
        List<Ymfw> wymfws = ymfwMapper.selectList(null);
        System.out.println(wymfws);
        return wymfws;
    }
}
