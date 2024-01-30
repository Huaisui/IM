package com.lld.im.tcp.register;

import com.lld.im.common.constant.Constants;
import org.I0Itec.zkclient.ZkClient;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
public class ZKit {

    private ZkClient zkClient;

    public ZKit(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    //im-coreRoot/tcp/ip:port
    public void createRootNode(){
        boolean exists = zkClient.exists(Constants.IM_CORE_ZK_ROOT);
        if(!exists){
            zkClient.createPersistent(Constants.IM_CORE_ZK_ROOT);
        }
        boolean tcpExists = zkClient.exists(Constants.IM_CORE_ZK_ROOT +
                Constants.IM_CORE_ZK_ROOT_TCP);
        if(!tcpExists){
            zkClient.createPersistent(Constants.IM_CORE_ZK_ROOT +
                    Constants.IM_CORE_ZK_ROOT_TCP);
        }

        boolean webExists = zkClient.exists(Constants.IM_CORE_ZK_ROOT +
                Constants.IM_CORE_ZK_ROOT_WEB);
        if(!tcpExists){
            zkClient.createPersistent(Constants.IM_CORE_ZK_ROOT +
                    Constants.IM_CORE_ZK_ROOT_WEB);
        }
    }

    //ip+port
    public void createNode(String path){
        if(!zkClient.exists(path)){
            zkClient.createPersistent(path);
        }
    }
}
