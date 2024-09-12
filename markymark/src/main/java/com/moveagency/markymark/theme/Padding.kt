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

@file:Suppress("unused", "MemberVisibilityCanBePrivate", "DataClassPrivateConstructor")

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * MarkyMark version of Jetpack Compose's [PaddingValues][androidx.compose.foundation.layout.PaddingValues].
 * This is needed as [androidx.compose.foundation.layout.PaddingValues] only allows the retrieval of its values from a
 * density scope.
 *
 * @property start The padding value for the start edge.
 * @property top The padding value for the top edge.
 * @property end The padding value for the end edge.
 * @property bottom The padding value for the bottom edge.
 */
@Immutable
data class Padding private constructor(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
) {

    /**
     * Builder class for constructing instances of [Padding].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The padding value for the start edge. If not set, defaults to [horizontal] or [all].
         */
        var start: Dp? = null

        /**
         * The padding value for the top edge. If not set, defaults to [vertical] or [all].
         */
        var top: Dp? = null

        /**
         * The padding value for the end edge. If not set, defaults to [horizontal] or [all].
         */
        var end: Dp? = null

        /**
         * The padding value for the bottom edge. If not set, defaults to [vertical] or [all].
         */
        var bottom: Dp? = null

        /**
         * The default padding value for the horizontal edges. If not set, defaults to [all].
         */
        var horizontal: Dp? = null
            set(value) {
                field = value
                start = value
                end = value
            }

        /**
         * The default padding value for the vertical edges. If not set, defaults to [all].
         */
        var vertical: Dp? = null
            set(value) {
                field = value
                top = value
                bottom = value
            }

        /**
         * The default padding value to be applied to all edges if not individually specified. Default is `0.dp`.
         */
        var all: Dp = 0.dp
            set(value) {
                field = value
                horizontal = value
                vertical = value
            }

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            start = builder.start
            top = builder.top
            end = builder.end
            bottom = builder.bottom
            horizontal = builder.horizontal
        }

        /**
         * Includes the configuration from an existing [Padding] object into this builder.
         *
         * If all sides of the provided [padding] are the same, [all] will be set. If this is not the case however we
         * check if the axes match.
         *
         * If [Padding.start] and [Padding.end] are the same then [horizontal] will be set. If they are not the same
         * [start] and [end] will be set individually.
         *
         * If [Padding.top] and [Padding.bottom] are the same then [vertical] will be set. If they are not the same
         * [top] and [bottom] will be set individually.
         *
         * @param padding The [Padding] instance whose configuration should be included.
         */
        fun include(padding: Padding) {
            if (padding.start == padding.top && padding.start == padding.end && padding.start == padding.bottom) {
                all = padding.start
            } else {
                if (padding.start == padding.end) {
                    horizontal = padding.start
                } else {
                    start = padding.start
                    end = padding.end
                }

                if (padding.top == padding.bottom) {
                    vertical = padding.top
                } else {
                    top = padding.top
                    bottom = padding.bottom
                }
            }
        }

        /**
         * Builds a new [Padding] instance with the current configuration.
         *
         * If the [start] or [end] padding values are not set, they will default to the [horizontal] value.
         * If the [top] or [bottom] padding values are not set, they will default to the [vertical] value.
         * If [horizontal] and/or [vertical] are not set, they will default to [all].
         *
         * @return A [Padding] object with the set properties.
         */
        internal fun build() = Padding(
            start = start ?: horizontal ?: all,
            top = top ?: vertical ?: all,
            end = end ?: horizontal ?: all,
            bottom = bottom ?: vertical ?: all,
        )
    }
}
