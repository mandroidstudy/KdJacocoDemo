package org.sojex.codeCoverageCheck.visitor;

import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.OfflineInstrumentationAccessGenerator;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author: mao
 * @date: 21-3-3
 * @desc:
 */
public class CodeCoverageClassVisitor extends ClassVisitor {

    public CodeCoverageClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
