package com.nbrt.kybao.homecare.vo.server;

import com.nbrt.kybao.homecare.vo.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.ServerListVo;
import lombok.Data;

import java.util.List;

@Data
public class ServerChangeVo {
    private List<ServerListVo> serverListVos;
    private List<ServerItemSaveVo> serverItemSaveVos;
}
