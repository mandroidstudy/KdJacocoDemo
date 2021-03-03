package org.sojex.codeCoverageCheck.visitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author: mao
 * @date: 21-3-3
 * @desc:
 */
public class CodeCoverageMethodVisitor extends MethodVisitor {

    public CodeCoverageMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
