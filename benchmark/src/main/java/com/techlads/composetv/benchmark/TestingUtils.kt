package com.techlads.composetv.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By


fun MacrobenchmarkScope.skip() {
    device.findObject(By.res("Skip")).click()
}
