package com.pjlaapps.estudobiblicoeespiritodeprofecia

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pjlaapps.botoescomlista.getTextoBiblicoFromJson

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.pjlaapps.estudobiblicoeespiritodeprofecia", appContext.packageName)
    }
    @Test
    fun testTextoBiblicoFromJson_existente() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val titulo = getTextoBiblicoFromJson(appContext,"GEN_1_1")
        assertEquals("No princípio criou Deus os céus e a terra.", titulo)
    }
}