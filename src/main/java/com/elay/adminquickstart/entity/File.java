package com.elay.adminquickstart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 存储文件表
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_file")
public class File implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件地址
     */
    private String filepath;

    /**
     * 文件类型
     */
    private String filetype;

    /**
     * 大小
     */
    private Long filesize;

    /**
     * 上传用户
     */
    private Integer uploadBy;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 原本文件名
     */
    private String oldFilename;


}
