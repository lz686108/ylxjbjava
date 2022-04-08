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
public class JtbizhbCopy1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名称
     */
    private String gysmc;

    @TableField("Ad")
    private String ad;

    @TableField("Vdaf")
    private String vdaf;

    @TableField("Fcad")
    private String fcad;

    @TableField("Std")
    private String std;

    @TableField("M25")
    private String m25;

    @TableField("CRI")
    private String cri;

    @TableField("CSR")
    private String csr;

    @TableField("Mt")
    private String mt;

    @TableField("K")
    private String k;

    @TableField("Na")
    private String na;

    @TableField("Zn")
    private String zn;


}
