package org.mklinkj.taojwp.common.util

import java.nio.file.Paths

object CommonUtils {
    @JvmStatic
    fun ifNullToNullString(string: String?): String {
        return string ?: "null"
    }

    @JvmStatic
    fun fileNameOnly(fileName: String?): String {
        return Paths.get(fileName).fileName.toString()
    }
}
