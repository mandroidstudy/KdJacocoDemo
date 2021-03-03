package org.sojex.codeCoverageCheck.util;

import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.OfflineInstrumentationAccessGenerator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: mao
 * @date: 21-3-3
 * @desc:
 */
public class JacocoUtil {
    /**
     * 往class文件插入jacoco探针
     * @param fileIn
     * @param fileOut
     */
    public static void injectProbe(File fileIn, File fileOut){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(fileIn));
            final Instrumenter instr = new Instrumenter(new OfflineInstrumentationAccessGenerator());
            final byte[] instrumented = instr.instrument(is, fileIn.getName());

            os = new BufferedOutputStream(new FileOutputStream(fileOut));
            os.write(instrumented);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(os);
            CloseUtil.closeQuietly(is);
        }
    }
}
