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
public class Wymdcwh implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 燃料类型
     */
    private String rllx;

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
     * 无烟煤运费价格
     */
    private String jsfm;


}
