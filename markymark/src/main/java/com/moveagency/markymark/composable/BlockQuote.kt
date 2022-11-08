/*
 * Copyright © 2022 Move
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

package com.moveagency.markymark.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.LayoutDirection.Rtl
import com.moveagency.markymark.composer.paddingHorizontal
import com.moveagency.markymark.theme.BlockQuoteStyle

fun Modifier.blockQuoteItem(
    style: BlockQuoteStyle,
) = paddingHorizontal(style.outerPadding)
    .drawWithCache {
        onDrawBehind {
            // draw background
            drawRect(
                color = style.background,
                size = Size(
                    width = size.width,
                    height = size.height,
                )
            )

            // draw indicator
            val thicknessPx = style.indicatorThickness.toPx()
            val indicatorOffset = when (layoutDirection) {
                Ltr -> Offset.Zero
                Rtl -> Offset(x = size.width - thicknessPx, y = 0F)
            }
            drawRect(
                color = style.indicatorTint,
                topLeft = indicatorOffset,
                size = Size(width = thicknessPx, height = size.height),
            )
        }
    }
    .padding(
        start = style.innerPadding.start + style.indicatorThickness,
        end = style.innerPadding.end,
    )