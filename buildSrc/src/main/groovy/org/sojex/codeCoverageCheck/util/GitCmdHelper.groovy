package org.sojex.codeCoverageCheck.util

/**
 * @author: mao
 * @date: 21-2-26
 * @desc: 执行git相关的命令
 */
class GitCmdHelper{

    public static void exe(String[] cmds){
        if (cmds!=null&&cmds.length>0){
            Runtime.getRuntime().exec(cmds)
        }
    }
}