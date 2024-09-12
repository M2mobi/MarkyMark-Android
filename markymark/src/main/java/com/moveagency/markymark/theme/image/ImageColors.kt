/*
 * Copyright © 2024 Move
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

@file:Suppress("DataClassPrivateConstructor", "MemberVisibilityCanBePrivate")

package com.moveagency.markymark.theme.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for images.
 *
 * @property caption The color of the caption text for an image.
 */
@Immutable
data class ImageColors private constructor(
    val caption: Color,
) {

    /**
     * Builder class for constructing instances of [ImageColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The color of the caption text. Default is [Black].
         */
        var caption = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            caption = builder.caption
        }

        /**
         * Includes the configuration from an existing [ImageColors] object into this builder.
         *
         * This will override all properties with the ones from the provided [colors].
         *
         * @param colors The `ImageColors` instance whose configuration should be included.
         */
        fun include(colors: ImageColors) {
            caption = colors.caption
        }

        /**
         * Builds a new [ImageColors] instance with the current configuration.
         *
         * @return An `ImageColors` object with the set properties.
         */
        internal fun build() = ImageColors(
            caption = caption,
        )
    }
}
