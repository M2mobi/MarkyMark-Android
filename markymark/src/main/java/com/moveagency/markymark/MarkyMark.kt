/*
 * Copyright © 2025 Framna
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the “Software”), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.moveagency.markymark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import com.moveagency.markymark.theme.MarkyMarkTheme

/**
 * Central access point for MarkyMark configuration and theming.
 *
 * This object provides access to the current [MarkyMarkOptions] and [MarkyMarkTheme]
 * through composition locals, making them available throughout the composition hierarchy.
 * It serves as a convenient way to access markdown rendering configuration and styling
 * from any composable function.
 */
object MarkyMark {

    /**
     * The current markdown rendering options.
     *
     * This property provides access to the current [MarkyMarkOptions] from the [LocalMarkyMarkOptions]
     * composition local. It includes configuration for the FlexMark parser, custom composers,
     * and annotators used for rendering markdown content.
     */
    @Suppress("ObjectPropertyNaming")
    val options: MarkyMarkOptions
        @Composable
        @ReadOnlyComposable
        get() = LocalMarkyMarkOptions.current

    /**
     * The current markdown styling theme.
     *
     * This property provides access to the current [MarkyMarkTheme] from the [LocalMarkyMarkTheme]
     * composition local. It includes styling for all markdown elements, including root-level
     * styling, element-specific styles, and color schemes.
     */
    @Suppress("ObjectPropertyNaming")
    val theme: MarkyMarkTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalMarkyMarkTheme.current
}
