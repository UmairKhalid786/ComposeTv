package com.techlads.composetv.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import com.techlads.utils.testing.PRODUCT_DETAIL_BANNER_TAG
import com.techlads.utils.testing.SECTIONS_LIST_TAG
import com.techlads.utils.testing.SKIP_TAG
import com.techlads.utils.testing.tagForItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScrollLaunchBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.techlads.composetv",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        device.wait(Until.hasObject(By.res(SKIP_TAG)), 10000)
        skip()
        device.waitForIdle(1000)
        scrollThroughHomeSections()
    }
}

fun MacrobenchmarkScope.scrollThroughHomeSections() {
    val list = device.findObject(By.res(SECTIONS_LIST_TAG))
    device.waitForIdle()

    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.DOWN)

    device.waitForIdle()

    device.findObject(By.res(tagForItem(14, 1))).click()
    device.wait(Until.hasObject(By.res(PRODUCT_DETAIL_BANNER_TAG)), 10000)
}