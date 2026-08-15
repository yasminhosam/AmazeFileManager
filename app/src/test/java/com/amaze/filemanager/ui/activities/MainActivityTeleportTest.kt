/*
 * Copyright (C) 2014-2026 Arpit Khurana <arpitkh96@gmail.com>, Vishal Nehra <vishalmeham2@gmail.com>,
 * Emmanuel Messulam<emmanuelbendavid@gmail.com>, Raymond Lai <airwave209gt at gmail.com> and Contributors.
 *
 * This file is part of Amaze File Manager.
 *
 * Amaze File Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.amaze.filemanager.ui.activities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Tests for [MainActivity.splitParentAndFileName], used by the search
 * "teleport to file" feature to navigate to a file's parent folder.
 */
class MainActivityTeleportTest {

    /** Verifies a normal file path is split into its parent directory and file name. */
    @Test
    fun `splits a normal file path into parent and file name`() {
        val result = MainActivity.splitParentAndFileName("/storage/emulated/0/Documents/report.pdf")

        assertEquals("/storage/emulated/0/Documents", result?.parentPath)
        assertEquals("report.pdf", result?.fileName)
    }

    /** Verifies a path with only one directory level splits correctly. */
    @Test
    fun `splits a path with a single directory level`() {
        val result = MainActivity.splitParentAndFileName("/storage/file.txt")

        assertEquals("/storage", result?.parentPath)
        assertEquals("file.txt", result?.fileName)
    }

    /** Verifies null is returned when the path has no parent directory. */
    @Test
    fun `returns null when path has no parent directory`() {
        val result = MainActivity.splitParentAndFileName("file.txt")

        assertNull(result)
    }

    /** Verifies file names with spaces and special characters split correctly. */
    @Test
    fun `handles file names containing spaces and special characters`() {
        val result =
            MainActivity.splitParentAndFileName(
                "/storage/emulated/0/WhatsApp/Media/Yasmin Hosam (1).pdf",
            )

        assertEquals("/storage/emulated/0/WhatsApp/Media", result?.parentPath)
        assertEquals("Yasmin Hosam (1).pdf", result?.fileName)
    }
}
