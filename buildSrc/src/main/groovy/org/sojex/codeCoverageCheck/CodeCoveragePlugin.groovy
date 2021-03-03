package org.sojex.codeCoverageCheck

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author: mao
 * @date: 21-2-26
 * @desc: 代码覆盖率插件
 */
class CodeCoveragePlugin implements Plugin<Project>{

    public static final String EXT_NAME = "kdCodeCoverageConfig"

    @Override
    void apply(Project project) {
        println("apply CodeCoveragePlugin....")
        project.extensions.create(EXT_NAME,CodeCoverageExt)
        project.afterEvaluate {
            CodeCoverageExt ext=project.extensions.getByName(EXT_NAME)
            if (ext!=null&&ext.enable){
                def android = project.extensions.getByType(AppExtension)
                android.registerTransform(new CodeCoverageTransform(ext))
            }
        }
    }
}