package com.techlads.composetv.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import com.techlads.utils.testing.SKIP_TAG


fun MacrobenchmarkScope.skip() {
    device.findObject(By.res(SKIP_TAG)).click()
}
