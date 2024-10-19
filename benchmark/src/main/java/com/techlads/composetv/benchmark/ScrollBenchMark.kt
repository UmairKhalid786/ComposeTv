package com.techlads.composetv.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeScrollPerformanceBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun composeScrollPerformanc() = benchmarkRule.measureRepeated(
        packageName = "com.techlads.composetv",
        metrics = listOf(FrameTimingMetric()),
        // Try switching to different compilation modes to see the effect
        // it has on frame timing metrics.
        compilationMode = CompilationMode.None(),
        startupMode = StartupMode.WARM, // restarts activity each iteration
        iterations = 5,
        setupBlock = {
            pressHome()
            startActivityAndWait()

            device.waitForIdle(1000)
            device.wait(Until.hasObject(By.res("Skip")), 10_000)
            device.findObject(By.res("Skip")).click()

        }
    ) {
        scrollThroughHomeSections()
    }

    private fun MacrobenchmarkScope.scrollThroughHomeSections() {

        device.waitForIdle(1000)

        device.wait(Until.hasObject(By.res("sections_list")), 10_000)

        val recycler = device.findObject(By.res("sections_list"))
        // Set gesture margin to avoid triggering gesture navigation
        // with input events from automation.
        recycler.setGestureMargin(device.displayWidth / 5)

        // Scroll down several times
        repeat(3) { recycler.scroll(Direction.DOWN, 1f) }
    }
}