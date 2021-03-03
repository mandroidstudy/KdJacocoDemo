package org.sojex.codeCoverageCheck.util;

import java.io.Closeable;

/**
 * @author: mao
 * @date: 21-3-3
 * @desc:
 */
public class CloseUtil {

    protected static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                // Ignored.
            }
        }
    }

}
