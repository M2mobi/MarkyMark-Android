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

@file:Suppress("DataClassPrivateConstructor", "unused")

package com.moveagency.markymark.theme.list

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultCap
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultJoin
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.moveagency.markymark.model.composable.ListBlock
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.IndicatorDrawStyle.Stroke
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.Shape.Rectangle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

/**
 * Theming attributes used when rendering [ListBlock.ListItemType.Unordered].
 *
 * @property padding The padding around the unordered list item.
 * @property textStyle The text style for the unordered list item text.
 * @property indicatorPadding The padding around the unordered list item indicator.
 * @property indicators A list of [Indicator]s. These will be used in a modulo fashion, wrapping back around when the
 *                      nesting level exceeds the size of the list.
 */
@Immutable
data class UnorderedListItemStyle private constructor(
    override val padding: Padding,
    override val textStyle: TextStyle,
    override val indicatorPadding: Padding,
    val indicators: ImmutableList<Indicator>,
) : ListItemStyle {

    /**
     * Theming attributes used when rendering an indicator for [ListBlock.ListItemType.Unordered].
     *
     * @property shape The shape of the indicator.
     * @property size The size of the indicator, defined as a [DpSize].
     * @property style The drawing style of the indicator.
     * @property rotation The rotation angle of the indicator, in degrees (from `0` to `360`).
     */
    @Immutable
    data class Indicator private constructor(
        val shape: Shape,
        val size: DpSize,
        val style: IndicatorDrawStyle,
        @FloatRange(from = 0.0, to = 360.0) val rotation: Float,
    ) {

        /**
         * The shape of the indicator. Only supports ovals, rectangles, and triangles.
         *
         * - Lines can be achieved by specifying a [Rectangle] with a height much smaller than its width.
         * - Diamonds can be achieved by specifying a [Rectangle] with equal height and width (a square) and rotating it
         *   45°.
         *
         * More shapes can be achieved by adjusting the [size] and [rotation] of these shapes. uses your imagination!
         */
        enum class Shape {

            Oval,
            Rectangle,
            Triangle,
        }

        /**
         * The style in which the indicator is drawn. Analogue of [DrawStyle] except it asks for a [Dp] value for
         * [Stroke.width].
         */
        @Stable
        sealed class IndicatorDrawStyle {

            /**
             * Analogue of [androidx.compose.ui.graphics.drawscope.Fill]
             */
            @Immutable
            data object Fill : IndicatorDrawStyle()

            /**
             * Analogue of [androidx.compose.ui.graphics.drawscope.Stroke] except asks for [Dp] value for [width].
             */
            @Immutable
            data class Stroke(
                val width: Dp,
                val miter: Float = DefaultMiter,
                val cap: StrokeCap = DefaultCap,
                val join: StrokeJoin = DefaultJoin,
                val pathEffect: PathEffect? = null,
            ) : IndicatorDrawStyle()
        }

        /**
         * Builder class for constructing instances of [Indicator].
         */
        @MarkyMarkThemeBuilderMarker
        class Builder {

            /**
             * The shape of the indicator. Default is [Shape.Oval].
             */
            var shape = Shape.Oval

            /**
             * The size of the indicator. Default is `6.dp x 6.dp`.
             */
            var size = DpSize(width = 6.dp, height = 6.dp)

            /**
             * The drawing style of the indicator. Default is [IndicatorDrawStyle.Fill].
             */
            var style: IndicatorDrawStyle = IndicatorDrawStyle.Fill

            /**
             * The rotation angle of the indicator, in degrees. Default is `0F`.
             */
            @FloatRange(from = 0.0, to = 360.0)
            var rotation: Float = 0F

            /**
             * Includes another [Builder] instance's configuration into `this` builder.
             *
             * This will override all properties with the ones from the provided [builder].
             *
             * @param builder The builder whose configuration should be included.
             */
            fun include(builder: Builder) {
                shape = builder.shape
                size = builder.size
                style = builder.style
                rotation = builder.rotation
            }

            /**
             * Includes the configuration from an existing [Indicator] object into this builder.
             *
             * This will override all properties with the ones from the provided [style].
             *
             * @param style The [Indicator] instance whose configuration should be included.
             */
            fun include(style: Indicator) {
                shape = style.shape
                size = style.size
                this.style = style.style
                rotation = style.rotation
            }

            /**
             * Builds a new [Indicator] instance with the current configuration.
             *
             * @return An [Indicator] object with the set properties.
             */
            internal fun build() = Indicator(
                shape = shape,
                size = size,
                style = style,
                rotation = rotation,
            )
        }
    }

    /**
     * Builder class for constructing a list of [Indicator] objects used in an unordered list.
     */
    @MarkyMarkThemeBuilderMarker
    class UnorderedListIndicatorsBuilder {

        private val indicators = mutableListOf<Indicator.Builder>()

        /**
         * Includes another [UnorderedListIndicatorsBuilder] instance's configuration into `this` builder.
         *
         * This will add all indicators from the provided [builder].
         *
         * @param builder The builder whose indicators should be included.
         */
        fun include(builder: UnorderedListIndicatorsBuilder) {
            indicators.addAll(builder.indicators)
        }

        /**
         * Includes the configuration from an existing list of [Indicator] objects into this builder.
         *
         * This will add all indicators from the provided list.
         *
         * @param indicators The list of [Indicator] instances to be included.
         */
        fun include(indicators: List<Indicator>) {
            this.indicators.addAll(
                indicators.map {
                    Indicator.Builder().apply { include(it) }
                }
            )
        }

        /**
         * Remove all indicators that might have been added by [include].
         */
        fun clear() = indicators.clear()

        /**
         * Adds a new indicator to the unordered list.
         *
         * @param block A lambda to configure the [Indicator.Builder].
         */
        fun indicator(block: Indicator.Builder.() -> Unit) {
            indicators.add(Indicator.Builder().apply(block))
        }

        /**
         * Builds a list of [Indicator] objects with the current configuration.
         *
         * @return An immutable list of [Indicator] objects.
         */
        internal fun build() = indicators.map { it.build() }.toImmutableList()
    }

    /**
     * Builder class for constructing instances of [UnorderedListItemStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the unordered list item.
         */
        private var padding = Padding.Builder()

        /**
         * The text style for the unordered list item text.
         */
        var textStyle = TextStyle()

        /**
         * Builder for configuring the padding around the unordered list item indicator.
         */
        private var indicatorPadding = Padding.Builder()

        /**
         * Builder for constructing the list of indicators for the unordered list item.
         */
        private var indicators = UnorderedListIndicatorsBuilder()

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
            indicatorPadding = builder.indicatorPadding
            indicators.include(builder.indicators)
        }

        /**
         * Includes the configuration from an existing [UnorderedListItemStyle] object into this builder.
         *
         * This will include the padding, text style, indicator padding, and indicators from the provided [style].
         *
         * @param style The [UnorderedListItemStyle] instance whose configuration should be included.
         */
        fun include(style: UnorderedListItemStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
            indicatorPadding.include(style.indicatorPadding)
            indicators.include(style.indicators)
        }

        /**
         * Configures the padding around the unordered list item.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Configures the padding around the unordered list item indicator.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun indicatorPadding(block: Padding.Builder.() -> Unit) = block(indicatorPadding)

        /**
         * Configures the list of indicators for the unordered list item.
         *
         * @param block A lambda to configure the [UnorderedListIndicatorsBuilder].
         */
        fun indicators(block: UnorderedListIndicatorsBuilder.() -> Unit) = block(indicators)

        /**
         * Builds a new [UnorderedListItemStyle] instance with the current configuration.
         *
         * @return An [UnorderedListItemStyle] object with the set properties.
         */
        internal fun build() = UnorderedListItemStyle(
            padding = padding.build(),
            textStyle = textStyle,
            indicatorPadding = indicatorPadding.build(),
            indicators = indicators.build(),
        )
    }
}
