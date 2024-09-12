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

@file:Suppress("unused", "DataClassPrivateConstructor")

package com.moveagency.markymark.theme.code

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.composable.MarkyMarkCode
import com.moveagency.markymark.model.composable.CodeBlock
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used for rendering [CodeBlock]s with [MarkyMarkCode].
 *
 * @property innerPadding The padding inside the code block, between the content and the edge.
 * @property outerPadding The padding outside the code block, surrounding the entire block.
 * @property shape The shape of the code block, such as a rectangle or other custom shape.
 * @property textStyle The text style applied to the content inside the code block.
 */
@Immutable
data class CodeBlockStyle private constructor(
    val innerPadding: Padding,
    val outerPadding: Padding,
    val shape: Shape,
    val textStyle: TextStyle,
) {

    /**
     * Builder class for constructing instances of [CodeBlockStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the inner padding inside the code block.
         */
        private var innerPadding = Padding.Builder()

        /**
         * Builder for configuring the outer padding outside the code block.
         */
        private var outerPadding = Padding.Builder()

        /**
         * The shape of the code block. Default is [RectangleShape].
         */
        var shape = RectangleShape

        /**
         * The text style applied to the content inside the code block. Default is an empty [TextStyle].
         */
        var textStyle = TextStyle()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            innerPadding = builder.innerPadding
            outerPadding = builder.outerPadding
            shape = builder.shape
            textStyle = builder.textStyle
        }

        /**
         * Includes the configuration from an existing [CodeBlockStyle] object into this builder.
         *
         * This will include the padding, shape, and text style from the provided [style].
         *
         * @param style The [CodeBlockStyle] instance whose configuration should be included.
         */
        fun include(style: CodeBlockStyle) {
            innerPadding.include(style.innerPadding)
            outerPadding.include(style.outerPadding)
            shape = style.shape
            textStyle = style.textStyle
        }

        /**
         * Configures the inner padding inside the code block.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun innerPadding(block: Padding.Builder.() -> Unit) = block(innerPadding)

        /**
         * Configures the outer padding outside the code block.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun outerPadding(block: Padding.Builder.() -> Unit) = block(outerPadding)

        /**
         * Builds a new [CodeBlockStyle] instance with the current configuration.
         *
         * @return A [CodeBlockStyle] object with the set properties for the code block.
         */
        internal fun build() = CodeBlockStyle(
            innerPadding = innerPadding.build(),
            outerPadding = outerPadding.build(),
            shape = shape,
            textStyle = textStyle,
        )
    }
}
