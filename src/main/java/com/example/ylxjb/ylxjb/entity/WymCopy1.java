package com.example.ylxjb.ylxjb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lizhen
 * @since 2022-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WymCopy1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 燃料类型
     */
    private String materialName;

    /**
     * 燃料名称
     */
    private String distributorName;

    /**
     * 付款方式
     */
    private String sterilise;

    /**
     * 合同价格
     */
    private String contractPrice;

    /**
     * 月计划量
     */
    private String monthAmount;

    /**
     * 结算水分
     */
    private String jssf;

    /**
     * 结算粉末
     */
    private String jsfm;

    /**
     * 版本号
     */
    private String version;

    /**
     * 不含税价格
     */
    private String bhsjg;

    /**
     * 采购量
     */
    private String cgl;

    /**
     * 采购占比
     */
    private String cgzb;

    @TableField("Ad")
    private String ad;

    private String fcd;

    private String mad;

    private String mt;

    private String qgrD;

    private String qnetVAr;

    private String stD;

    private String vdaf;

    @TableField("K")
    private String k;

    @TableField("Na")
    private String na;

    @TableField("Zn")
    private String zn;

    /**
     * 有效热量
     */
    private String yxrl;

    /**
     * 出厂价格
     */
    private String ccjg;

    /**
     * 运费价格
     */
    private String yfjg;

    /**
     * 到厂价格
     */
    private String dcjg;

    /**
     * 单位有效热成本
     */
    private String dwyxrcb;

    /**
     * 兑现率
     */
    private String dxl;


}
