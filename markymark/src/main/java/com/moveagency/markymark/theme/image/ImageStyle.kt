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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used for rendering images in markdown content.
 *
 * This class defines styling properties for images and their captions.
 *
 * *Note: Captions do not support inline formatting.*
 *
 * @property shape The shape of the image, such as a rectangle or other custom shape.
 * @property fullWidth Whether the image should take up the full width of its container.
 * @property padding The padding around the image.
 * @property caption Styling configuration for the image caption.
 */
@Immutable
data class ImageStyle(
    val shape: Shape,
    val fullWidth: Boolean,
    val padding: Padding,
    val caption: ImageCaptionStyle,
) {

    /**
     * Builder class for constructing instances of [ImageStyle].
     *
     * This builder provides methods to configure the styling for images
     * in a type-safe, fluent manner.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The shape of the image. Default is [RectangleShape].
         */
        var shape = RectangleShape

        /**
         * Whether the image should take up the full width of its container. Default is `false`.
         */
        var fullWidth = false

        /**
         * Builder for configuring the padding around the image.
         */
        private var padding = Padding.Builder()

        /**
         * Builder for configuring the image caption style.
         */
        private var caption = ImageCaptionStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            shape = builder.shape
            fullWidth = builder.fullWidth
            padding = builder.padding
            caption = builder.caption
        }

        /**
         * Includes the configuration from an existing [ImageStyle] object into this builder.
         *
         * This will include the shape, fullWidth setting, padding, and caption style from the provided [style].
         *
         * @param style The [ImageStyle] instance whose configuration should be included.
         */
        fun include(style: ImageStyle) {
            shape = style.shape
            fullWidth = style.fullWidth
            padding.include(style.padding)
            caption.include(style.caption)
        }

        /**
         * Configures the padding around the image.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Configures the image caption style.
         *
         * @param block A lambda to configure the [ImageCaptionStyleBuilder].
         */
        fun caption(block: ImageCaptionStyle.Builder.() -> Unit) = block(caption)

        /**
         * Builds a new [ImageStyle] instance with the current configuration.
         *
         * @return An [ImageStyle] object with the configured properties.
         */
        internal fun build() = ImageStyle(
            shape = shape,
            fullWidth = fullWidth,
            padding = padding.build(),
            caption = caption.build(),
        )
    }
}
