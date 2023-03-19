/*
 * Copyright 2023 Tarsin Norbin
 *
 * This file is part of EhViewer
 *
 * EhViewer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * EhViewer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with EhViewer.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package com.hippo.yorozuya

import okhttp3.ResponseBody
import java.io.File

fun ResponseBody.copyToFile(file: File) {
    val os = file.outputStream()
    val channel = os.channel
    val source = source()
    try {
        channel.transferFrom(source, 0, Long.MAX_VALUE)
    } catch (e: Throwable) {
        throw e
    } finally {
        source.close()
        channel.close()
        os.close()
    }
}