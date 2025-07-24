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

package com.moveagency.markymark.theme.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Styling configuration for image captions in markdown.
 *
 * This class defines the visual appearance of captions that accompany images,
 * including spacing, typography, and horizontal alignment.
 *
 * *Note: Captions do not support inline formatting.*
 *
 * @property padding The spacing around the caption text.
 * @property textStyle The typography style applied to the caption text.
 * @property alignment The horizontal alignment of the caption (start, center, or end).
 */
@Immutable
data class ImageCaptionStyle(
    val padding: Padding,
    val textStyle: TextStyle,
    val alignment: Alignment.Horizontal,
) {

    /**
     * Builder class for constructing instances of [ImageCaptionStyle].
     *
     * This builder provides methods to configure the styling for image captions
     * in a type-safe, fluent manner.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the caption text.
         */
        private var padding = Padding.Builder()

        /**
         * The typography style applied to the caption text.
         */
        var textStyle = TextStyle()

        /**
         * The horizontal alignment of the caption. Default is [Start].
         */
        var alignment = Start

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            textStyle = builder.textStyle
            alignment = builder.alignment
        }

        /**
         * Includes the configuration from an existing [ImageCaptionStyle] object into this builder.
         *
         * This will include the padding, text style, and alignment from the provided [style].
         *
         * @param style The [ImageCaptionStyle] instance whose configuration should be included.
         */
        fun include(style: ImageCaptionStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
            alignment = style.alignment
        }

        /**
         * Configures the padding around the caption text.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [ImageCaptionStyle] instance with the current configuration.
         *
         * @return An [ImageCaptionStyle] object with the configured properties.
         */
        internal fun build() = ImageCaptionStyle(
            padding = padding.build(),
            textStyle = textStyle,
            alignment = alignment,
        )
    }
}
