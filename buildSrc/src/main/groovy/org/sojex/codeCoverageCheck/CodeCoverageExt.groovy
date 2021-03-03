package org.sojex.codeCoverageCheck

/**
 * @author: mao
 * @date: 21-2-26
 * @desc: 配置属性
 */
class CodeCoverageExt {
    public boolean enable
    //配置当前的git分支
    public String currBranch
    //配置目标的git分支
    public String destBranch
    //指定生成的class的目录
    public String classesDir

    public void printInfo(){
        println(toString())
    }

    @Override
    public String toString() {
        return "CodeCoverageExt{" +
                "enable=" + enable +
                ", currBranch='" + currBranch + '\'' +
                ", destBranch='" + destBranch + '\'' +
                ", classesDir='" + classesDir + '\'' +
                '}';
    }
}
