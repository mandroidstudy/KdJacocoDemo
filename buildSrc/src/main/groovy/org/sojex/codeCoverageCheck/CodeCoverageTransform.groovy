package org.sojex.codeCoverageCheck

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project
import com.android.build.api.transform.Format
import org.apache.commons.io.FileUtils
import org.sojex.codeCoverageCheck.util.JacocoUtil
/**
 * @author: mao
 * @date: 21-2-26
 * @desc: 具体的任务
 */
class CodeCoverageTransform extends Transform{

    private CodeCoverageExt coverageExt;

    CodeCoverageTransform(CodeCoverageExt coverageExt){
        this.coverageExt=coverageExt
        coverageExt.printInfo()
    }

    @Override
    String getName() {
        return CodeCoverageTransform.simpleName
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }


    @Override
    boolean isIncremental() {
        //todo 增量编译优化
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        if (outputProvider != null)
            outputProvider.deleteAll()
        inputs.each { TransformInput transformInput ->

            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                handleDirectoryInput(directoryInput, outputProvider)
            }
        }
    }

    /**
     * 处理文件目录下的class文件
     */
    void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        if (directoryInput.file.isDirectory()) {
            directoryInput.file.eachFileRecurse { File file ->
                String name = file.name
                if (filterClass(name)) {
//                    ClassReader classReader = new ClassReader(file.bytes)
//                    ClassWriter classWriter = new ClassWriter(0 /* flags */)
//                    ClassVisitor classVisitor = new CodeCoverageClassVisitor(classWriter)
//                    classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
//                    byte[] code = classWriter.toByteArray()
//                    FileOutputStream fos = new FileOutputStream(
//                            file.parentFile.absolutePath + File.separator + name)
//                    fos.write(code)
//                    fos.close()
                    JacocoUtil.injectProbe(file,new File((file.parentFile.absolutePath + File.separator +name)))
                }
            }
        }
        def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                directoryInput.scopes, Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file, dest)
    }

    boolean filterClass(String className) {
        return (className.endsWith(".class") && !className.startsWith("R\$")
                && "R.class" != className && "BuildConfig.class" != className)
    }
}