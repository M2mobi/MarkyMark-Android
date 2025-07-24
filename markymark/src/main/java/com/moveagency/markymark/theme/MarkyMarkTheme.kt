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

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal containing the current [MarkyMarkTheme].
 *
 * This provides access to the theme's styling properties throughout the composition hierarchy.
 * The default value is a minimal theme created with an empty [markyMarkTheme] builder.
 */
val LocalMarkyMarkTheme = staticCompositionLocalOf { markyMarkTheme {} }

/**
 * Theme for styling markdown content in Compose applications.
 *
 * This class aggregates all the styling components needed to render markdown content
 * with a consistent visual appearance. It includes root-level styling, element-specific
 * styles, and color schemes.
 *
 * @property root Root-level styling properties that apply to the entire markdown content.
 * @property styles Collection of style configurations for all markdown elements.
 * @property colors Color scheme used for rendering markdown elements.
 */
@Immutable
data class MarkyMarkTheme(
    val root: RootStyle,
    val styles: MarkyMarkStyles,
    val colors: MarkyMarkColors,
)
