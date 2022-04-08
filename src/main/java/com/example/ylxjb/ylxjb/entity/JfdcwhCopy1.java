package com.example.ylxjb.ylxjb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class JfdcwhCopy1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String ad;

    private String std;

    private String qgrd;

    private String vdaf;

    private String fcd;

    private String mt;

    private String mad;

    /**
     * 出厂价格
     */
    private String ccjg;

    /**
     * 运费价格
     */
    private String yfjg;

    /**
     * 到场价格
     */
    private String dcjg;

    /**
     * 付款方式
     */
    private String fkfs;

    /**
     * 结算水分
     */
    private String jssf;

    /**
     * 不含税价格
     */
    private String bhsjg;

    /**
     * 烧结固体单耗
     */
    private String sjgtdh;

    /**
     * 吨烧燃耗单位成本
     */
    private String dsrhdwcb;

    /**
     * 供应商名称
     */
    private String gysmc;

    private String version;


}
