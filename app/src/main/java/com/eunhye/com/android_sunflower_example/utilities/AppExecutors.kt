package com.eunhye.com.android_sunflower_example.utilities

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun runOnIoThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}