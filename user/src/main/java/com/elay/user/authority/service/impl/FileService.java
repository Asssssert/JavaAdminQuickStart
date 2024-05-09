package com.elay.user.authority.service.impl;

import com.elay.user.authority.service.IFileService;
import com.elay.user.authority.entity.File;
import com.elay.user.authority.mapper.FileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储文件表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class FileService extends ServiceImpl<FileMapper, File> implements IFileService {

}
