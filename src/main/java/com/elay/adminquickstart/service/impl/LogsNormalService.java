package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.LogsNormal;
import com.elay.adminquickstart.mapper.LogsNormalMapper;
import com.elay.adminquickstart.service.ILogsNormalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 - 正常操作日志 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class LogsNormalService extends ServiceImpl<LogsNormalMapper, LogsNormal> implements ILogsNormalService {

}
